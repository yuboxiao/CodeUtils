package com.pisces.basequickadapter.example;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pisces.basequickadapter.quickadapter.BaseViewHolder;

import java.util.List;

/**
 * 作者: x00378851
 * 日期: 2018/11/25 17:07
 */

public abstract class MyBaseQuickAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {
    private Context mContext;
    private int mLayoutResID;
    private List<T> mData;

    public MyBaseQuickAdapter(Context context, int layoutResID, List<T> datas) {
        this.mLayoutResID = layoutResID;
        this.mContext = context;
        mData = datas;
    }


    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final BaseViewHolder holder;
        final View itemView = LayoutInflater.from(mContext).inflate(mLayoutResID, parent, false);
        holder = new BaseViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        convert(holder, mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    abstract void  convert(BaseViewHolder holder, T item);
}
