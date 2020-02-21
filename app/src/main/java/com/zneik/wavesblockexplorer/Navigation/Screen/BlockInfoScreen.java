package com.zneik.wavesblockexplorer.Navigation.Screen;

import androidx.fragment.app.Fragment;

import com.zneik.wavesblockexplorer.Fragment.BlockInfoFragment;

import ru.terrakok.cicerone.android.support.SupportAppScreen;

public class BlockInfoScreen extends SupportAppScreen {
    private Integer height;

    public BlockInfoScreen(Integer height) {
        this.height = height;
    }

    @Override
    public Fragment getFragment() {
        return BlockInfoFragment.newInstance(this.height);
    }
}
