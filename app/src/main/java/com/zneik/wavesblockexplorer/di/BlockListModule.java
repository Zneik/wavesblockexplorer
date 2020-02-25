package com.zneik.wavesblockexplorer.di;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.zneik.wavesblockexplorer.Fragment.BlockListViewModel;

import dagger.Module;
import dagger.Provides;


@Module
public class BlockListModule {
    private ViewModelStoreOwner owner;

    public BlockListModule(ViewModelStoreOwner owner) {
        this.owner = owner;
    }

    @Provides
    public BlockListViewModel getBlockListViewModel() {
        return new ViewModelProvider(owner).get(BlockListViewModel.class);
    }
}
