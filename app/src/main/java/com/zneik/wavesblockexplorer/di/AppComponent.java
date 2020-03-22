package com.zneik.wavesblockexplorer.di;

import com.zneik.wavesblockexplorer.Fragment.BlockInfoFragment;
import com.zneik.wavesblockexplorer.Fragment.BlockListFragment;

import dagger.Component;

@Component(modules = {BlockListModule.class})
public interface AppComponent {
    void inject(BlockListFragment blockListFragment);

}
