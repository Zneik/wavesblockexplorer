package com.zneik.wavesblockexplorer.Fragment;

import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
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
    private LinearLayout llBlockInfo;
    private ProgressBar pbLoadBlockInfo;
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
//        blockInfoViewModel.getLoading().setValue(false);

        initViewElement(fView);

        Bundle arguments = getArguments();
        if (arguments != null) {
            blockInfoViewModel.getHeight().setValue(arguments.getInt(ARG_HEIGHT));
        }

        blockInfoViewModel.getBlockTransaction().observe(getViewLifecycleOwner(), this::setTransactionTV);
        blockInfoViewModel.getLoading().observe(getViewLifecycleOwner(), loaded -> {
            if (loaded) {
                llBlockInfo.setVisibility(View.GONE);
                pbLoadBlockInfo.setVisibility(View.VISIBLE);
            } else {
                pbLoadBlockInfo.setVisibility(View.GONE);
                llBlockInfo.setVisibility(View.VISIBLE);
            }
        });

        blockInfoViewModel.loadBlockInfo();
        return fView;
    }

    private void setBoldPrefix(TextView view, String source) {
        SpannableString ss = new SpannableString(source);
        StyleSpan boldSpan = new StyleSpan(Typeface.BOLD);
        ss.setSpan(boldSpan, 0, source.indexOf(":") + 1, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        view.setText(ss);
//        return ss.toString();
    }

    private void setTransactionTV(Transaction transaction) {
        setBoldPrefix(tvHeight, getString(R.string.block_height,
                String.valueOf(transaction.getHeight())));
        setBoldPrefix(tvVersion, getString(R.string.block_version,
                String.valueOf(transaction.getVersion())));
        setBoldPrefix(tvTimestamp, getString(R.string.block_timestamp,
                Helper.getStringFromTimestamp(transaction.getTimestamp())));
        setBoldPrefix(tvTransactionCount, getString(R.string.block_transactions,
                String.valueOf(transaction.getTransactionCount())));
        setBoldPrefix(tvParentBlock, getString(R.string.block_parent_block,
                String.valueOf(transaction.getReference())));
        setBoldPrefix(tvGenerator, getString(R.string.block_generator,
                String.valueOf(transaction.getGenerator())));
        setBoldPrefix(tvSignature, getString(R.string.block_signature,
                String.valueOf(transaction.getSignature())));
        setBoldPrefix(tvSize, getString(R.string.block_size,
                String.valueOf(transaction.getBlocksize())));
        setBoldPrefix(tvTotalFee, getString(R.string.block_total_fee,
                String.valueOf(transaction.getTotalFee())));
        setBoldPrefix(tvReward, getString(R.string.block_reward,
                String.valueOf(transaction.getReward())));
    }

    private void initViewElement(View view) {
        llBlockInfo = view.findViewById(R.id.llBlockInfo);
        pbLoadBlockInfo = view.findViewById(R.id.pbLoadBlockInfo);
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
