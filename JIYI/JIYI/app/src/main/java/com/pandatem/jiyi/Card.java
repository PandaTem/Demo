package com.pandatem.jiyi;

import android.graphics.Bitmap;

import com.amap.api.maps.model.LatLng;

import java.io.Serializable;

public class Card implements Serializable {
    private Integer id;
    private Boolean isPrivate;
    private String cover;
    private Bitmap coverBitmap;
    private String title;
    private String content;
    private Person person;
    private LatLng latLng;
    private String position;

    public Card(){
        id=0;
        cover="Testcover";
        title="TestTitle";
        content="Test";
        person = new Person();
        position="北京";
        isPrivate = false;
    }


    public Integer getId() {
        return id;
    }

    public String getCover() {
        return cover;
    }

    public String getContent() {
        return content;
    }

    public Bitmap getCoverBitmap() {
        return coverBitmap;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public Person getPerson() {
        return person;
    }

    public String getTitle() {
        return title;
    }

    public String getPosition() {
        return position;
    }


    public Boolean getPrivate() {
        return isPrivate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCoverBitmap(Bitmap coverBitmap) {
        this.coverBitmap = coverBitmap;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setPrivate(Boolean aPrivate) {
        isPrivate = aPrivate;
    }
}

