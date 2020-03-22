package com.zneik.wavesblockexplorer.di;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.zneik.wavesblockexplorer.Fragment.BlockInfoViewModel;

import dagger.Module;
import dagger.Provides;

@Module
public class BlockInfoModule {
    private ViewModelStoreOwner owner;

    public BlockInfoModule(ViewModelStoreOwner owner) {
        this.owner = owner;
    }

    @Provides
    public BlockInfoViewModel getblockInfoViewModel() {
        return new ViewModelProvider(owner).get(BlockInfoViewModel.class);
    }
}
