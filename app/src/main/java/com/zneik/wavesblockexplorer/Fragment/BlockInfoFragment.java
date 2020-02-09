package com.zneik.wavesblockexplorer.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.zneik.wavesblockexplorer.Helper.Helper;
import com.zneik.wavesblockexplorer.Model.BlockInfo.Transaction;
import com.zneik.wavesblockexplorer.R;

public class BlockInfoFragment extends Fragment {
    public static final String ARG_HEIGHT = "argHeight";

    //ui element
    private TextView tvHeight;
    private TextView tvVersion;
    private TextView tvTimestamp;
    private TextView tvTransactionCount;
    private TextView tvParentBlock;
    private TextView tvGenerator;
    private TextView tvSignature;
    private TextView tvSize;
    private TextView tvTotalFee;
    private TextView tvReward;

    private BlockInfoViewModel blockInfoViewModel;

    public static Fragment newInstance(Integer height) {
        Fragment f = new BlockInfoFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_HEIGHT, height);
        f.setArguments(bundle);
        return f;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        blockInfoViewModel = new ViewModelProvider(this).get(BlockInfoViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View fView = inflater.inflate(R.layout.fragment_block_info, container, false);

        initViewElement(fView);

        Bundle arguments = getArguments();
        if (arguments != null) {
            blockInfoViewModel.getHeight().setValue(arguments.getInt(ARG_HEIGHT));
        }

        blockInfoViewModel.loadBlockInfo();
        blockInfoViewModel.getBlockTransaction().observe(getViewLifecycleOwner(), this::setTransactionTV);

        return fView;
    }

    private void setTransactionTV(Transaction transaction) {
        tvHeight.setText(getString(R.string.block_height,
                String.valueOf(transaction.getHeight())));
        tvVersion.setText(getString(R.string.block_version,
                String.valueOf(transaction.getVersion())));
        tvTimestamp.setText(getString(R.string.block_timestamp,
                Helper.getStringFromTimestamp(transaction.getTimestamp())));
        tvTransactionCount.setText(getString(R.string.block_transactions,
                String.valueOf(transaction.getTransactionCount())));
        tvParentBlock.setText(getString(R.string.block_parent_block,
                String.valueOf(transaction.getReference())));
        tvGenerator.setText(getString(R.string.block_generator,
                String.valueOf(transaction.getGenerator())));
        tvSignature.setText(getString(R.string.block_signature,
                String.valueOf(transaction.getSignature())));
        tvSize.setText(getString(R.string.block_size,
                String.valueOf(transaction.getBlocksize())));
        tvTotalFee.setText(getString(R.string.block_total_fee,
                String.valueOf(transaction.getTotalFee())));
        tvReward.setText(getString(R.string.block_reward,
                String.valueOf(transaction.getReward())));
    }

    private void initViewElement(View view) {
        tvHeight = view.findViewById(R.id.tvHeight);
        tvVersion = view.findViewById(R.id.tvVersion);
        tvTimestamp = view.findViewById(R.id.tvTimestamp);
        tvTransactionCount = view.findViewById(R.id.tvTransactionCount);
        tvParentBlock = view.findViewById(R.id.tvParentBlock);
        tvGenerator = view.findViewById(R.id.tvGenerator);
        tvSignature = view.findViewById(R.id.tvSignature);
        tvSize = view.findViewById(R.id.tvSize);
        tvTotalFee = view.findViewById(R.id.tvTotalFee);
        tvReward = view.findViewById(R.id.tvReward);
    }

    public interface attachBlockInfoFragment {
        public void attachBlockInfoFragment(Integer height);
    }

}
