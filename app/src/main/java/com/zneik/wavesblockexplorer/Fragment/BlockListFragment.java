package com.zneik.wavesblockexplorer.Fragment;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.zneik.wavesblockexplorer.Activity.MainActivity;
import com.zneik.wavesblockexplorer.Adapter.HeadersAdapter;
import com.zneik.wavesblockexplorer.R;
import com.zneik.wavesblockexplorer.base.BaseFragment;
import com.zneik.wavesblockexplorer.di.BlockListModule;
import com.zneik.wavesblockexplorer.di.DaggerAppComponent;

import java.sql.Struct;

import javax.inject.Inject;

public class BlockListFragment extends BaseFragment
        implements HeadersAdapter.EndScroll {
    /**
     * UI
     */
    private RecyclerView rvBlock;
    private Snackbar loadingSnackbar;

    /**
     * Viewmodel
     */
    @Inject
    public BlockListViewModel blockListViewModel;

    private HeadersAdapter headersAdapter;
    private BlockInfoFragment.attachBlockInfoFragment attachBlockInfoFragmentListener;

    public BlockListFragment() {
        super(R.layout.fragment_block_list);
    }

    public static Fragment newInstance() {
        return new BlockListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerAppComponent.builder()
                .blockListModule(new BlockListModule(this))
                .build()
                .inject(this);

        attachBlockInfoFragmentListener = (MainActivity) getActivity();
        headersAdapter = new HeadersAdapter(attachBlockInfoFragmentListener);
        headersAdapter.setEndScrollListener(this);
        initObserver();

    }

    /**
     * Init observer
     */
    private void initObserver() {
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
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViewElement(view);
    }

    /**
     * Init UI
     *
     * @param view
     */
    private void initViewElement(View view) {
        this.rvBlock = view.findViewById(R.id.rvBlock);
        this.rvBlock.setLayoutManager(new LinearLayoutManager(getContext()));
        this.rvBlock.setAdapter(this.headersAdapter);
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
        SpannableString s = getSanckbarStr(R.string.loading);
        loadingSnackbar = Snackbar.make(requireView(),
                s,
                Snackbar.LENGTH_LONG)
                .setDuration(10)
                .setActionTextColor(R.color.snackbarText);
        loadingSnackbar.show();
    }

    private SpannableString getSanckbarStr(@StringRes int stringRes) {
        String loadingText = getString(stringRes);
        SpannableString s = new SpannableString(loadingText);
        s.setSpan(new ForegroundColorSpan(Color.WHITE),
                0, loadingText.length() - 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return s;
    }

    public interface attachBlockListFragment {
        void attach();
    }
}
