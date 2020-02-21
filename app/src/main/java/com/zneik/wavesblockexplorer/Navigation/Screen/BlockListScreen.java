package com.zneik.wavesblockexplorer.Navigation.Screen;



import androidx.fragment.app.Fragment;

import com.zneik.wavesblockexplorer.Fragment.BlockListFragment;

import ru.terrakok.cicerone.android.support.SupportAppScreen;


public class BlockListScreen extends SupportAppScreen {
    @Override
    public Fragment getFragment() {
        return BlockListFragment.newInstance();
    }
}
