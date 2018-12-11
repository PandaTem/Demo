package com.pandatem.jiyi;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.AMapCameraInfo;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;

import java.util.List;


public class MapFragment extends Fragment {

    MapView mMapView = null;
    List mCards;

    public MapFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_map, null);
        mMapView = (MapView)view.findViewById(R.id.mapI);
        mMapView.onCreate(savedInstanceState);
        AMap aMap =null;
        if(aMap == null){
            aMap = mMapView.getMap();
        }
        MyLocationStyle myLocationStyle;
        myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_FOLLOW_NO_CENTER);
        myLocationStyle.interval(5000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。am
        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
        aMap.getUiSettings().setMyLocationButtonEnabled(true);//设置默认定位按钮是否显示，非必需设置。
        aMap.getUiSettings().setZoomControlsEnabled(false);//设置地图缩放按钮
        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。


        final AMap finalAMap = aMap;
        aMap.setOnMapClickListener(new AMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                MarkerOptions markerOption = new MarkerOptions();
                markerOption.position(latLng);
                markerOption.title("位置"+latLng.toString()).snippet("西安市：34.341568, 108.940174");

                markerOption.draggable(false);//设置Marker可拖动
             //   markerOption.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
            //            .decodeResource(getResources(),R.mipmap.img)));
                // 将Marker设置为贴地显示，可以双指下拉地图查看效果
                markerOption.setFlat(false);//设置marker平贴地图效果
                finalAMap.addMarker(markerOption);

            }
        });

        MyInfoWindowAdapter info = new MyInfoWindowAdapter(getContext()) {
            @Override
            public void onClick(View v) {
                int id = v.getId();
                if(id==R.id.imgv_info) {
                    Toast.makeText(getContext(), "img", Toast.LENGTH_SHORT).show();
                }
            }
        };

        aMap.setOnInfoWindowClickListener(new AMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                marker.hideInfoWindow();
            }
        });
        finalAMap.setInfoWindowAdapter(info);

        FloatingActionButton flb_add = (FloatingActionButton)view.findViewById(R.id.flb_add);
        flb_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "flb", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }







}
