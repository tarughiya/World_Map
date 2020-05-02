package com.example.splash_screen;

public class OfflineMap {
    private String title;
    private int imgView;

    public OfflineMap(String title, int imgView) {
        this.title = title;
        this.imgView = imgView;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImgView() {
        return imgView;
    }

    public void setImgView(int imgView) {
        this.imgView = imgView;
    }
}
