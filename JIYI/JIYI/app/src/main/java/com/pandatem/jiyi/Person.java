package com.pandatem.jiyi;

import android.graphics.Bitmap;

import java.io.Serializable;

public class Person implements Serializable {
    private Integer id;
    private String cover;
    private Bitmap coverBitmap;
    private String name;
    private String password;

    public Person(){
        id=0;
        cover="Testcover";
        name="TestName";
        password="TestPassword";
    }

    public Bitmap getCoverBitmap() {
        return coverBitmap;
    }

    public String getCover() {
        return cover;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void setCoverBitmap(Bitmap coverBitmap) {
        this.coverBitmap = coverBitmap;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
