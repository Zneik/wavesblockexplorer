package com.zneik.wavesblockexplorer.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.zneik.wavesblockexplorer.Fragment.BlockInfoFragment;
import com.zneik.wavesblockexplorer.Fragment.BlockListFragment;
import com.zneik.wavesblockexplorer.R;

public class MainActivity extends AppCompatActivity
        implements BlockListFragment.attachBlockListFragment,
        BlockInfoFragment.attachBlockInfoFragment {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        setTitle("Waves block explorer");

        attachBlockListFragment();
    }

    @Override
    public void attachBlockListFragment() {
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction()
//                .addToBackStack(null)
                .replace(R.id.main_fragment_container, BlockListFragment.newInstance())
                .commit();
    }

    @Override
    public void attachBlockInfoFragment(Integer height) {
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction()
                .addToBackStack(null)
                .replace(R.id.main_fragment_container, BlockInfoFragment.newInstance(height))
                .commit();
    }
}
