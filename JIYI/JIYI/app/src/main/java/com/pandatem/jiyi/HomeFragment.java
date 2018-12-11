package com.pandatem.jiyi;


import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pandatem.jiyi.CircleImageView.CircleImageView;
import com.pandatem.jiyi.RecycleView.HomeRecycleViewAdapter;
import com.pandatem.jiyi.RecycleView.HomeViewHolder;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {
    private  View mView;
    private List mData;
    private  HomeRecycleViewAdapter homeAdapter;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, null);
        mView = view;
        mData = new ArrayList<Card>();
       for(int i=0;i<5;i++){
        Card card = new Card();
        mData.add(card);
       }
        initRecycleView();
        return view;
    }


    public void initRecycleView(){
       RecyclerView recyclerview =(RecyclerView) mView.findViewById(R.id.recyclerview);


        homeAdapter = new  HomeRecycleViewAdapter<Card>(getContext(),R.layout.item,mData) {

            @Override
            public void convert(HomeViewHolder holder, Card card) {
                CircleImageView img_cover  = (CircleImageView)holder.getView(R.id.img_cover);
                CircleImageView img_user_cover=(CircleImageView)holder.getView(R.id.img_person_cover);
                TextView tv_content = (TextView)holder.getView(R.id.tv_content);
                TextView tv_position = (TextView)holder.getView(R.id.tv_position);
                TextView tv_user_name=(TextView)holder.getView(R.id.tv_person_name);

                tv_content.setText(card.getContent());
                tv_position.setText(card.getPosition());
                tv_user_name.setText(card.getPerson().getName());

            }
        };

        homeAdapter.setOnItemClickListener(new HomeRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {

            }
        });

        recyclerview.setLayoutManager(new GridLayoutManager(getContext(),2));


        recyclerview.setAdapter(homeAdapter);

    }

}
