package com.pandatem.jiyi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.pandatem.jiyi.RecycleView.HomeRecycleViewAdapter;

public abstract class MyInfoWindowAdapter implements AMap.InfoWindowAdapter,View.OnClickListener {

    private LatLng mLatLng;
    private String mSnippet;
    private Context mContext;
    private String mTitle;

    public MyInfoWindowAdapter(Context context) {
        mContext = context;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        View infoWindow = LayoutInflater.from(mContext).inflate(
                R.layout.info, null);
        render(marker, infoWindow);
        return infoWindow;
    }
    @Override
    public View getInfoContents(Marker marker) {
        return null;
    }


    public void render(Marker marker, View view) {
        String title = marker.getTitle();
        TextView titleUi = ((TextView) view.findViewById(R.id.tv_info_title));
        titleUi.setText(title);

        ImageView imageView = (ImageView)view.findViewById(R.id.imgv_info);
        imageView.setImageResource(R.mipmap.ic_launcher);
        imageView.setOnClickListener(this);

    }



    //点击事件


}
