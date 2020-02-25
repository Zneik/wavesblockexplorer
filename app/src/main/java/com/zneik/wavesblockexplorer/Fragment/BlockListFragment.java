package com.zneik.wavesblockexplorer.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zneik.wavesblockexplorer.Activity.MainActivity;
import com.zneik.wavesblockexplorer.Adapter.HeadersAdapter;
import com.zneik.wavesblockexplorer.R;
import com.zneik.wavesblockexplorer.di.BlockListModule;
import com.zneik.wavesblockexplorer.di.DaggerAppComponent;

import javax.inject.Inject;

public class BlockListFragment extends Fragment
implements HeadersAdapter.EndScroll{
    private RecyclerView rvBlock;

    @Inject
    public BlockListViewModel blockListViewModel;

    private HeadersAdapter headersAdapter;
    private BlockInfoFragment.attachBlockInfoFragment attachBlockInfoFragmentListener;

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


        attachBlockInfoFragmentListener = (MainActivity)getActivity();
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
    public void loadOnEndScroll() {
        blockListViewModel.loadBlocks();
    }

    public interface attachBlockListFragment {
        void attachBlockListFragment();
    }
}
