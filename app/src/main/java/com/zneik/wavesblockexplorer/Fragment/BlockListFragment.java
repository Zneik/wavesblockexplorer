package com.zneik.wavesblockexplorer.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.zneik.wavesblockexplorer.Activity.MainActivity;
import com.zneik.wavesblockexplorer.Adapter.HeadersAdapter;
import com.zneik.wavesblockexplorer.R;
import com.zneik.wavesblockexplorer.di.BlockListModule;
import com.zneik.wavesblockexplorer.di.DaggerAppComponent;

import javax.inject.Inject;

public class BlockListFragment extends Fragment
        implements HeadersAdapter.EndScroll {
    private RecyclerView rvBlock;

    @Inject
    public BlockListViewModel blockListViewModel;

    private HeadersAdapter headersAdapter;
    private BlockInfoFragment.attachBlockInfoFragment attachBlockInfoFragmentListener;
    private Snackbar loadingSnackbar;

    public static Fragment newInstance() {
        return new BlockListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        DaggerAppComponent.builder()
                .blockListModule(new BlockListModule(this))
                .build()
                .inject(this);
        super.onCreate(savedInstanceState);


        attachBlockInfoFragmentListener = (MainActivity) getActivity();
        headersAdapter = new HeadersAdapter(attachBlockInfoFragmentListener);
        headersAdapter.setEndScrollListener(this);

//        blockListViewModel = new ViewModelProvider(this).get(BlockListViewModel.class);
        blockListViewModel.getLastBlockHeight().observe(this, it -> {
            if (it != null)
                blockListViewModel.loadBlocks();
        });

        blockListViewModel.getHeadersList().observe(this, it -> {
            headersAdapter.addHeaders(it);
        });
        blockListViewModel.getIsDownloading().observe(this, it -> {
            if (it) {
                this.visibleLoadin();
            } else {
                if (loadingSnackbar != null)
                    this.hideLoadin();
            }
        });

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View fView = inflater.inflate(R.layout.fragment_block_list, container, false);
        initViewElement(fView);

        return fView;
    }

    private void initViewElement(View view) {
        this.rvBlock = view.findViewById(R.id.rvBlock);
        this.rvBlock.setLayoutManager(new LinearLayoutManager(getContext()));
        this.rvBlock.setAdapter(this.headersAdapter);
    }

    private void initListener() {
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        attachBlockInfoFragmentListener = null;
    }

    @Override
    public void loadOnEndScroll() {
        blockListViewModel.loadBlocks();
    }

    @SuppressLint("ResourceAsColor")
    private void visibleLoadin() {
        loadingSnackbar = Snackbar.make(getView(),
                Html.fromHtml("<font color=\"#ffffff\">Loading</font>"),
                Snackbar.LENGTH_LONG)
                .setDuration(10)
                .setActionTextColor(R.color.snackbarText);
        loadingSnackbar.show();
    }

    private void hideLoadin() {
    }

    public interface attachBlockListFragment {
        void attachBlockListFragment();
    }
}
