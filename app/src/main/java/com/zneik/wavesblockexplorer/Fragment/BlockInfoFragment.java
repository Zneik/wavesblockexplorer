package com.zneik.wavesblockexplorer.Fragment;

import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.zneik.wavesblockexplorer.Helper.Helper;
import com.zneik.wavesblockexplorer.Model.BlockInfo.Transaction;
import com.zneik.wavesblockexplorer.R;
import com.zneik.wavesblockexplorer.base.BaseFragment;
import com.zneik.wavesblockexplorer.di.BlockInfoModule;
import com.zneik.wavesblockexplorer.di.DaggerAppComponent2;

import javax.inject.Inject;

public class BlockInfoFragment extends BaseFragment {
    /**
     * Arguments name
     */
    public static final String ARG_HEIGHT = "argHeight";

    /**
     * UI
     */
    private LinearLayout llBlockInfo;
    private ScrollView svBlockInfo;
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

    /**
     * Viewmodel
     */
    @Inject
    public BlockInfoViewModel blockInfoViewModel;

    public BlockInfoFragment() {
        super(R.layout.fragment_block_info);
    }

    /**
     * Create new instance
     *
     * @param height
     * @return
     */
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
        DaggerAppComponent2.builder()
                .blockInfoModule(new BlockInfoModule(this))
                .build()
                .inject(this);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViewElement(view);
        retrieveArguments(getArguments());
        initObserver();
        loadingInfo();
    }

    /**
     * loading blocks
     */
    private void loadingInfo() {
        blockInfoViewModel.loadBlockInfo();
    }

    /**
     * Init observer
     */
    private void initObserver() {
        blockInfoViewModel.getBlockTransaction().observe(getViewLifecycleOwner(), this::setTransactionTV);
        blockInfoViewModel.getLoading().observe(getViewLifecycleOwner(), loaded -> {
            if (loaded) {
                visibleLoading();
            } else {
                showInfo();
            }
        });
    }

    /**
     * Убрать prograss bar, показать информацию о блоке
     */
    private void showInfo() {
        pbLoadBlockInfo.setVisibility(View.GONE);
        svBlockInfo.setVisibility(View.VISIBLE);
    }

    /**
     * Пока загружается информацию показываем progress bar
     */
    private void visibleLoading() {
        svBlockInfo.setVisibility(View.GONE);
        pbLoadBlockInfo.setVisibility(View.VISIBLE);
    }

    /**
     * Достать аргументы
     *
     * @param arguments
     */
    private void retrieveArguments(Bundle arguments) {
        if (arguments != null) {
            blockInfoViewModel.getHeight().setValue(arguments.getInt(ARG_HEIGHT));
        }
    }

    /**
     * Выделение
     *
     * @param view
     * @param source
     */
    private void setBoldPrefix(TextView view, String source) {
        SpannableString ss = new SpannableString(source);
        StyleSpan boldSpan = new StyleSpan(Typeface.BOLD);
        ss.setSpan(boldSpan, 0, source.indexOf(":") + 1, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        view.setText(ss);
    }

    /**
     * Установить информацию о блоке в соответсвующие поля
     *
     * @param transaction
     */
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

    /**
     * Инициализировать UI
     *
     * @param view
     */
    private void initViewElement(View view) {
        svBlockInfo = view.findViewById(R.id.svBlockInfo);
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
        void attach(Integer height);
    }

}
