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
    private AdapterView.OnItemClickListener onItemClickListener  ;

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

    public abstract void convert(HomeViewHolder holder,T t);


}
