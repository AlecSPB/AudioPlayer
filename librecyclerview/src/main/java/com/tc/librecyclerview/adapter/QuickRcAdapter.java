package com.tc.librecyclerview.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Date:15/9/25
 * Time:10:53
 * Mail:pengliang02@meituan.com
 * Small improvements each day~
 */
public abstract class QuickRcAdapter<D> extends
        BaseRecyclerAdapter<D, RecyclerViewHolder>
        implements QuickRcOperation {

    protected OnItemClickListener onItemClickListener;
    protected OnItemLongClickListener onItemLongClickListener;

    public QuickRcAdapter(Context context) {
        super(context);
    }

    public QuickRcAdapter(Context context, List<D> data) {
        super(context, data);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, final int position) {
        bindItemClickListener(holder, position);
        bindItemData(holder, position);
    }

    @Override
    public final RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerViewHolder(inflaterItemView(parent, viewType));
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    protected void bindItemClickListener(RecyclerViewHolder holder, final int position) {
        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(v, position);
                }
            });
        }

        if (onItemLongClickListener != null) {
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onItemLongClickListener.onItemLongClick(v, position);
                    return true;
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View v, int position);
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(View v, int position);
    }
}
