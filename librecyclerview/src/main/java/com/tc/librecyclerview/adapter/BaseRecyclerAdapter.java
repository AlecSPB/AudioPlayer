package com.tc.librecyclerview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;

import com.tc.librecyclerview.config.ImageLoader;
import com.tc.librecyclerview.config.RecycerViewConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * Date:15/9/24
 * Time:14:45
 * Mail:pengliang02@meituan.com
 * Small improvements each day~
 */
public abstract class BaseRecyclerAdapter<D, VH extends RecyclerView.ViewHolder> extends Adapter<VH> {

    protected LayoutInflater mInflater;
    protected Context mContext;
    protected ImageLoader mImageLoader;
    private List<D> data;

    public BaseRecyclerAdapter(Context context) {
        this(context, new ArrayList());
    }

    public BaseRecyclerAdapter(Context context, List<D> data) {
        this.mContext = context;
        if (data == null) {
            this.data = new ArrayList<>();
        } else {
            this.data = data;
        }
        this.mImageLoader = RecycerViewConfig.getInstance().getImageLoader();
        this.mInflater = LayoutInflater.from(context);
    }

    public int getItemCount() {
        return this.data.size();
    }

    public long getItemId(int position) {
        return (long) position;
    }

    public void add(D item) {
        this.insert(item, this.data.size());
    }

    public void appendData(List<D> list) {
        if(list == null) {
            list = new ArrayList<>();
        }
        this.data.addAll(list);
        notifyDataSetChanged();
    }

    public void insert(D item, int position) {
        this.data.add(position, item);
        this.notifyItemInserted(position);
    }

    public D getItem(int position) {
        return this.data.get(position);
    }

    public void clear() {
        if(this.data != null) {
            this.data.clear();
        }
    }

    public List<D> getData() {
        return data;
    }

    public void setData(List<D> data) {
        this.data = data;
        this.notifyDataSetChanged();
    }

    public void remove(D d) {
        if(data != null) {
            data.remove(d);
            notifyDataSetChanged();
        }
    }

    public void removeAt(int pos) {
        if(data != null) {
            data.remove(pos);
            notifyItemRemoved(pos);
        }
    }
}
