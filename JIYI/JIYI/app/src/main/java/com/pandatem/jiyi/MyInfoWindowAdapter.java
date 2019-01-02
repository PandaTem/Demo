package com.pandatem.jiyi;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
    private Card mCard;

    public MyInfoWindowAdapter(Context context,Card card) {
        mContext = context;
        mCard = card;
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

        TextView titleUi = ((TextView) view.findViewById(R.id.tv_info_title));
        titleUi.setText(mCard.getPerson().getName());

        TextView contentUi = ((TextView) view.findViewById(R.id.tv_info_content));
        contentUi.setText(mCard.getContent());

        ImageView imageView = (ImageView) view.findViewById(R.id.imgv_info);
        if(mCard.getCoverBitmapBytes() != null) {
            imageView.setVisibility(View.VISIBLE);
            byte[] bytes = mCard.getCoverBitmapBytes();
            Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);


            imageView.setImageBitmap(bmp);
            imageView.setOnClickListener(this);
        }else{
            imageView.setVisibility(View.GONE);
        }
    }



    //点击事件


}
