package com.zneik.wavesblockexplorer.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;

import com.zneik.wavesblockexplorer.AppInit;
import com.zneik.wavesblockexplorer.Fragment.BlockInfoFragment;
import com.zneik.wavesblockexplorer.Fragment.BlockListFragment;
import com.zneik.wavesblockexplorer.Navigation.Screen.BlockInfoScreen;
import com.zneik.wavesblockexplorer.Navigation.Screen.BlockListScreen;
import com.zneik.wavesblockexplorer.R;

import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.android.pure.AppNavigator;
import ru.terrakok.cicerone.android.support.SupportAppNavigator;

public class MainActivity extends AppCompatActivity
        implements BlockListFragment.attachBlockListFragment,
        BlockInfoFragment.attachBlockInfoFragment {
    protected static final String BLOCK_LIST = "block_list";
    protected static final String BLOCK_INFO = "block_info";

    private Navigator navigator = new SupportAppNavigator(this, R.id.main_fragment_container);
//    private Navigator navigator = new AppNavigator(this, R.id.main_fragment_container) {
//        cre
//}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        attachBlockListFragment();
    }

    @Override
    public void attachBlockListFragment() {
        if (getSupportFragmentManager().getBackStackEntryCount() <= 0)
            AppInit.INSTANCE.getRouter().navigateTo((new BlockListScreen()));
//        FragmentManager fm = getSupportFragmentManager();
//        fm.beginTransaction()
//                .add(R.id.main_fragment_container, BlockListFragment.newInstance())
//                .commit();
    }

    @Override
    public void attachBlockInfoFragment(Integer height) {
        AppInit.INSTANCE.getRouter().navigateTo((new BlockInfoScreen(height)));
//        FragmentManager fm = getSupportFragmentManager();
//        fm.beginTransaction()
//                .addToBackStack(null)
//                .replace(R.id.main_fragment_container, BlockInfoFragment.newInstance(height))
//                .commit();
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
        if(getSupportFragmentManager().getBackStackEntryCount() == 1)
            finish();
        super.onBackPressed();
    }
}
