package com.zneik.wavesblockexplorer.Adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zneik.wavesblockexplorer.Fragment.BlockInfoFragment;
import com.zneik.wavesblockexplorer.Helper.Helper;
import com.zneik.wavesblockexplorer.Model.Header;
import com.zneik.wavesblockexplorer.R;

public class HeadersViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private TextView tvBlockNTransaction;
    private TextView tvSignature;
    private TextView tvDate;

    private Header header;
    private BlockInfoFragment.attachBlockInfoFragment openFragment;


    public HeadersViewHolder(@NonNull View itemView,
                             BlockInfoFragment.attachBlockInfoFragment openFragment) {
        super(itemView);
        this.openFragment = openFragment;
        initView(itemView);
        itemView.setOnClickListener(this);
    }

    private void initView(@NonNull View itemView) {
        tvBlockNTransaction = itemView.findViewById(R.id.tvBlockNTransaction);
        tvSignature = itemView.findViewById(R.id.tvSignature);
        tvDate = itemView.findViewById(R.id.tvDate);
    }

    public void bind(Header header) {
        this.header = header;
        tvBlockNTransaction.setText(itemView.getResources()
                .getString(R.string.blockNTransaction,
                        header.getHeight(),
                        header.getTransactionCount()));
        tvSignature.setText(itemView.getResources()
                .getString(R.string.signature, header.getSignature()));
        String tsFormat = Helper.getStringFromTimestamp(header.getTimestamp());
        tvDate.setText(itemView.getResources()
                .getString(R.string.date, tsFormat));
    }

    @Override
    public void onClick(View v) {
        openFragment.attach(this.header.getHeight());
    }

}
