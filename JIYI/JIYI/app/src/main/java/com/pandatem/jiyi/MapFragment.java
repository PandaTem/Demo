package com.pandatem.jiyi;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.AMapCameraInfo;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;

import java.util.ArrayList;
import java.util.List;


public class MapFragment extends Fragment implements AMapLocationListener {

    Card card;
    MapView mMapView = null;
    List<Card> mCards;
    List<Marker> mMarkers;
    private AMap aMap ;
    private AMapLocationClient mLocationClient;
    private AMapLocationClientOption mLocationOption;
    public MapFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCards = new ArrayList<Card>();
        mMarkers = new ArrayList<Marker>();
        mLocationClient  =new AMapLocationClient(getContext());
        mLocationOption = new AMapLocationClientOption();
        card = new Card();

        mLocationClient.setLocationListener(this);
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Battery_Saving);
        mLocationClient.setLocationOption(mLocationOption);
        mLocationClient.startLocation();
    }

    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_map, null);
        mMapView = (MapView)view.findViewById(R.id.mapI);
        mMapView.onCreate(savedInstanceState);

        if(aMap == null){
            aMap = mMapView.getMap();
        }
        MyLocationStyle myLocationStyle;
        myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_FOLLOW);
        myLocationStyle.interval(1000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。am
        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
        aMap.getUiSettings().setMyLocationButtonEnabled(true);//设置默认定位按钮是否显示，非必需设置。
        aMap.getUiSettings().setZoomControlsEnabled(false);//设置地图缩放按钮
        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。


        aMap.setOnMapClickListener(new AMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                MarkerOptions markerOption = new MarkerOptions();
                markerOption.position(latLng);

                markerOption.title("位置"+latLng.toString()).snippet("");

                markerOption.draggable(false);//设置Marker可拖动
                //   markerOption.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                //            .decodeResource(getResources(),R.mipmap.img)));
                // 将Marker设置为贴地显示，可以双指下拉地图查看效果
                markerOption.setFlat(false);//设置marker平贴地图效果
                aMap.addMarker(markerOption);
                double x = latLng.latitude;
                double y =latLng.longitude;
                card.setLatLng_x(x);
                card.setLatLng_y(y);

            }
        });



        FloatingActionButton flb_add = (FloatingActionButton)view.findViewById(R.id.flb_add);
        flb_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getContext(), "flb", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(),DetailActivity.class);
                intent.putExtra("card",card);
                startActivityForResult(intent,1);
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data1){
        if (resultCode == 1){
            card = (Card) data1.getSerializableExtra("card");
            mCards.add(card);
            LatLng latLng = new LatLng(card.getLatLng_x(),card.getLatLng_y());
            MarkerOptions markerOption = new MarkerOptions();
            markerOption.position(latLng);
            markerOption.title("位置"+latLng.toString()).snippet(card.getContent());

            markerOption.draggable(false);//设置Marker可拖动
            //   markerOption.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
            //            .decodeResource(getResources(),R.mipmap.img)));
            // 将Marker设置为贴地显示，可以双指下拉地图查看效果
            markerOption.setFlat(false);//设置marker平贴地图效果
            aMap.addMarker(markerOption);



            MyInfoWindowAdapter info = new MyInfoWindowAdapter(getContext(),card) {
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
            aMap.setInfoWindowAdapter(info);

        }
    }


    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
                if(aMapLocation != null){
                    if(aMapLocation.getErrorCode() == 0){

                       card.setPosition(  aMapLocation.getAddress());
                       card.setLatLng_x(aMapLocation.getLatitude() );
                       card.setLatLng_y(aMapLocation.getLongitude());
                    }else {
                        //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                        Log.e("AmapError","location Error, ErrCode:"
                                + aMapLocation.getErrorCode() + ", errInfo:"
                                + aMapLocation.getErrorInfo());
                    }


                }
    }
}
