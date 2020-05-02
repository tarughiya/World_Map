package com.example.splash_screen;

public class OfflineMapModel {
    private String Name;
    private int ImageView;

    public OfflineMapModel(String name, int imageView) {
        Name = name;
        ImageView = imageView;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getImageView() {
        return ImageView;
    }

    public void setImageView(int imageView) {
        ImageView = imageView;
    }
}
