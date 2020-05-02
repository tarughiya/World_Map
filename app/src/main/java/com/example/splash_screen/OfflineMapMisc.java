package com.example.splash_screen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class OfflineMapMisc extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view2);
        TextView textView=findViewById(R.id.continentName);
        ImageView imageView=findViewById(R.id.continentImage);
        TextView textView1=findViewById(R.id.continentPopulation);
        TextView textView2=findViewById(R.id.continentArea);
        TextView textView3=findViewById(R.id.continentCountries);
        int image=getIntent().getIntExtra("Image",0);
        String continent=getIntent().getStringExtra("continent");
        double Population=getIntent().getDoubleExtra("Population",0);
        double Area=getIntent().getDoubleExtra("Area",0);
        int Countries=getIntent().getIntExtra("Countries",0);
        textView.setText(continent);
        textView1.setText("Population:- "+Population+" "+"crores");
        textView2.setText("Area:- "+Area+" "+"million km²");
        textView3.setText("Total Country:- "+Countries+"");
        imageView.setImageResource(image);




    }
}
