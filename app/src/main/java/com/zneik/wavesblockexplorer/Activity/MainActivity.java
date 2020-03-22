package com.zneik.wavesblockexplorer.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.zneik.wavesblockexplorer.AppInit;
import com.zneik.wavesblockexplorer.Fragment.BlockInfoFragment;
import com.zneik.wavesblockexplorer.Fragment.BlockListFragment;
import com.zneik.wavesblockexplorer.Navigation.Screen.BlockInfoScreen;
import com.zneik.wavesblockexplorer.Navigation.Screen.BlockListScreen;
import com.zneik.wavesblockexplorer.R;

import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.android.support.SupportAppNavigator;

public class MainActivity extends AppCompatActivity
        implements BlockListFragment.attachBlockListFragment,
        BlockInfoFragment.attachBlockInfoFragment {

    private Navigator navigator = new SupportAppNavigator(this, R.id.main_fragment_container);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        attach();
    }

    @Override
    public void attach() {
        if (getSupportFragmentManager().getBackStackEntryCount() <= 0)
            AppInit.INSTANCE.getRouter().navigateTo((new BlockListScreen()));
    }

    @Override
    public void attach(Integer height) {
        AppInit.INSTANCE.getRouter().navigateTo((new BlockInfoScreen(height)));
    }

    @Override
    protected void onResume() {
        super.onResume();
        AppInit.INSTANCE.getNavigatorHolder().setNavigator(navigator);
    }

    @Override
    protected void onPause() {
        super.onPause();
        AppInit.INSTANCE.getNavigatorHolder().removeNavigator();
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 1)
            finish();
        super.onBackPressed();
    }
}
