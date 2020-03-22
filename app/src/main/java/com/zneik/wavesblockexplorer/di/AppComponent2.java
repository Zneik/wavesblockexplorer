package com.zneik.wavesblockexplorer.di;

import com.zneik.wavesblockexplorer.Fragment.BlockInfoFragment;

import dagger.Component;

@Component(modules = {BlockInfoModule.class})
public interface AppComponent2 {
    void inject(BlockInfoFragment blockInfoFragment);
}
