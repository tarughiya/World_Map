package com.example.splash_screen;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;



public class TabActivity extends AppCompatActivity {
    Button mapview,streetview;
    String latitudeValue ;
    String longitudeValue ;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
        Bundle extras = getIntent().getExtras();
        if(extras == null) {
            latitudeValue= "";
            longitudeValue= "";
        } else {
            latitudeValue= extras.getString("latValue");
            longitudeValue= extras.getString("longValue");

        }


        mapview = findViewById(R.id.mapview);
        streetview = findViewById(R.id.streetview);
        loadFragment(CommonGoogleMapFragment.newInstance(latitudeValue,longitudeValue));
        mapview.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        streetview.setBackgroundColor(getResources().getColor(R.color.black));

        mapview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(CommonGoogleMapFragment.newInstance(latitudeValue,longitudeValue));
                mapview.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                streetview.setBackgroundColor(getResources().getColor(R.color.black));
            }
        });
        streetview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                streetview.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                mapview.setBackgroundColor(getResources().getColor(R.color.black));

                Intent a=new Intent(TabActivity.this,Streetview.class);
                a.putExtra("latValue",latitudeValue);
                a.putExtra("longValue",longitudeValue);
                startActivity(a);
            }
        });

    }
    private void loadFragment(Fragment fragment) {
// create a FragmentManager
        FragmentManager fm = getSupportFragmentManager();
// create a FragmentTransaction to begin the transaction and replace the Fragment
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
// replace the FrameLayout with new Fragment
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit(); // save the changes
    }
}
