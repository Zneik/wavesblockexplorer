package com.zneik.wavesblockexplorer.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zneik.wavesblockexplorer.Fragment.BlockInfoFragment;
import com.zneik.wavesblockexplorer.Model.Header;
import com.zneik.wavesblockexplorer.R;

import java.util.List;

public class HeadersAdapter extends RecyclerView.Adapter<HeadersViewHolder> {
    private List<Header> headers;
    private BlockInfoFragment.attachBlockInfoFragment blockInfoListener;
    private EndScroll endScrollListener;

    public HeadersAdapter(BlockInfoFragment.attachBlockInfoFragment blockInfoListener) {
        headers = null;
        this.blockInfoListener =blockInfoListener;
    }

    public void setHeaders(List<Header> headers) {
        this.headers = headers;
        this.notifyDataSetChanged();
    }

    public void addHeaders(List<Header> headers) {
        if (this.headers == null)
            this.setHeaders(headers);
        else {
            this.headers.addAll(headers);
            this.notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public HeadersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_block_item, parent,false);
        return new HeadersViewHolder(view, blockInfoListener);
    }

    @Override
    public void onBindViewHolder(@NonNull HeadersViewHolder holder, int position) {
        if (position == headers.size() - 1)
            endScrollListener.loadOnEndScroll();
        holder.bind(headers.get(position));
    }

    @Override
    public int getItemCount() {
        return headers.size();
    }

    public void setEndScrollListener(EndScroll endScrollListener) {
        this.endScrollListener = endScrollListener;
    }

    public interface EndScroll {
        void loadOnEndScroll();
    }

}
