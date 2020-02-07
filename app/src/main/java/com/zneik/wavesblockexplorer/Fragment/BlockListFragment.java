package com.zneik.wavesblockexplorer.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.zneik.wavesblockexplorer.R;

public class BlockListFragment extends Fragment {
    private RecyclerView rvBlock;

    private BlockListViewModel blockListViewModel;

    public static Fragment newInstance() {
        return new BlockListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        blockListViewModel = new ViewModelProvider(this).get(BlockListViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fView = inflater.inflate(R.layout.fragment_block_list, container, false);

        initViewElement(fView);


        return fView;
    }


    private void initViewElement(View view) {
        this.rvBlock = view.findViewById(R.id.rvBlock);
    }

    public interface attachBlockListFragment {
        void attachBlockListFragment();
    }
}
