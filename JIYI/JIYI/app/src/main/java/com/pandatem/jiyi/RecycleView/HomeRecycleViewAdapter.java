package com.pandatem.jiyi.RecycleView;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.AdapterView;

import java.util.List;

public abstract class HomeRecycleViewAdapter<T> extends RecyclerView.Adapter<HomeViewHolder> {

    private Context mContext;
    private int mLayoutId;
    private List<T> mData;
    private OnItemClickListener onItemClickListener  ;

    public HomeRecycleViewAdapter(@NonNull Context context, int layoutId, List data) {
        mContext = context;
        mData =data;
        mLayoutId = layoutId;
    }

    @Override
    public HomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        HomeViewHolder holder = HomeViewHolder.get(mContext,parent,mLayoutId);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int i) {
        convert(holder,mData.get(i));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public abstract void convert(HomeViewHolder holder,T t);


    public void setOnItemClickListener(OnItemClickListener _onItemClickListener){
        this.onItemClickListener = _onItemClickListener;
    }

    public interface  OnItemClickListener{
        void onClick(int position);
    }
}
