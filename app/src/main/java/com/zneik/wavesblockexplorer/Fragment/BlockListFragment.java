package com.zneik.wavesblockexplorer.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zneik.wavesblockexplorer.Adapter.HeadersAdapter;
import com.zneik.wavesblockexplorer.R;

public class BlockListFragment extends Fragment {
    private RecyclerView rvBlock;

    private BlockListViewModel blockListViewModel;

    private HeadersAdapter headersAdapter;

    public static Fragment newInstance() {
        return new BlockListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        headersAdapter = new HeadersAdapter();

        blockListViewModel = new ViewModelProvider(this).get(BlockListViewModel.class);
        blockListViewModel.getLastBlockHeight().observe(this, it -> {
//            Toast.makeText(getContext(), String.valueOf(it.getHeight()), Toast.LENGTH_SHORT).show();
            if (it != null)
                blockListViewModel.loadBlocksLast();
        });

        blockListViewModel.getHeadersList().observe(this, it -> {
//            Log.i("TTT", String.valueOf(it.size()));
            headersAdapter.setHeaders(it);
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

    public interface attachBlockListFragment {
        void attachBlockListFragment();
    }
}
