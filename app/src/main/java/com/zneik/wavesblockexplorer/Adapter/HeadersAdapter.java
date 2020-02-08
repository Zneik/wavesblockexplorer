package com.zneik.wavesblockexplorer.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zneik.wavesblockexplorer.Model.Header;
import com.zneik.wavesblockexplorer.R;

import java.util.List;

public class HeadersAdapter extends RecyclerView.Adapter<HaedersViewHolder> {
    List<Header> headers;

    public HeadersAdapter() {
        headers = null;
    }

    public void setHeaders(List<Header> headers) {
        this.headers = headers;
        Log.i("TTT", "Adapter " + headers.size());
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HaedersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_block_item, parent,false);
        return new HaedersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HaedersViewHolder holder, int position) {
        holder.bind(headers.get(position));
    }

    @Override
    public int getItemCount() {
        return headers.size();
    }

}
