package com.example.splash_screen;


import android.support.v4.app.Fragment;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.OnStreetViewPanoramaReadyCallback;
import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.SupportStreetViewPanoramaFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;
import com.google.android.gms.maps.model.StreetViewPanoramaLocation;
import com.google.android.gms.maps.model.StreetViewPanoramaOrientation;

public class Streetview extends AppCompatActivity
{
    private double latitudeValue;
    private double longitudeValue;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.streetview);
        Bundle extras = getIntent().getExtras();
        if(extras == null) {
            latitudeValue= 0.0;
            longitudeValue= 0.0;
        } else {
            latitudeValue= Double.parseDouble(extras.getString("latValue"));
            longitudeValue= Double.parseDouble(extras.getString("longValue"));


        }
        final LatLng latLng = new LatLng(latitudeValue,longitudeValue);

        SupportStreetViewPanoramaFragment streetViewPanoramaFragment =
                (SupportStreetViewPanoramaFragment)
                        getSupportFragmentManager().findFragmentById(R.id.streetviewpanorama);
        streetViewPanoramaFragment.getStreetViewPanoramaAsync(
                new OnStreetViewPanoramaReadyCallback() {
                    @Override
                    public void onStreetViewPanoramaReady(StreetViewPanorama panorama) {
                        if (savedInstanceState == null) {
                            panorama.setPosition(latLng);
                        }
                    }
                });

    }


}
