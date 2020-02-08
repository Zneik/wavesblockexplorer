package com.zneik.wavesblockexplorer.Adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zneik.wavesblockexplorer.Helper.Helper;
import com.zneik.wavesblockexplorer.Model.Header;
import com.zneik.wavesblockexplorer.R;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class HeadersViewHolder extends RecyclerView.ViewHolder {
    private TextView tvBlockNTransaction;
    private TextView tvSignature;
    private TextView tvDate;

    public HeadersViewHolder(@NonNull View itemView) {
        super(itemView);

        tvBlockNTransaction = itemView.findViewById(R.id.tvBlockNTransaction);
        tvSignature = itemView.findViewById(R.id.tvSignature);
        tvDate = itemView.findViewById(R.id.tvDate);
    }

    public void bind(Header header) {
        tvBlockNTransaction.setText(itemView.getResources()
                .getString(R.string.blockNTransaction,
                        header.getBlocksize(),
                        header.getTransactionCount()));

        tvSignature.setText(itemView.getResources()
                .getString(R.string.signature, header.getSignature()));

        String tsFormat = Helper.getStringFromTimestamp(header.getTimestamp());
        tvDate.setText(itemView.getResources()
                .getString(R.string.date, tsFormat));

    }
}
