package com.zneik.wavesblockexplorer.Activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;

import com.zneik.wavesblockexplorer.AppInit;
import com.zneik.wavesblockexplorer.Fragment.BlockInfoFragment;
import com.zneik.wavesblockexplorer.Fragment.BlockListFragment;
import com.zneik.wavesblockexplorer.Helper.Helper;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        MenuItem searchItem = menu.findItem(R.id.svSearchBlock);
        SearchView sv = (SearchView) searchItem.getActionView();
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Integer value = Helper.isInteger(query);
                if (value != null)
                    attach(Integer.valueOf(query));
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }

}
