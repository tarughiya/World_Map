package com.example.splash_screen;

public class CountryModel {
    private String description;
    private int imgId;
    private String capital;
    private String latValue;
    private String longValue;

    public CountryModel(String description, String capital,int imgId,String latValue,String longValue) {
        this.description = description;
        this.imgId = imgId;
        this.capital = capital;
        this.latValue=latValue;
        this.longValue=longValue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getLatValue() {
        return latValue;
    }

    public void setLatValue(String latValue) {
        this.latValue = latValue;
    }

    public String getLongValue() {
        return longValue;
    }

    public void setLongValue(String longValue) {
        this.longValue = longValue;
    }
}