package com.example.splash_screen;


import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.RatingBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;



public class MoreKnowledgeMain extends AppCompatActivity {
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flag_recycler_view);

        recyclerView = findViewById(R.id.recyclerView);
        List<MoreKnowledgeModel>countryData= new ArrayList<>();

       String uri[]=getResources().getStringArray(R.array.link_array);
        String cName[]= getResources().getStringArray(R.array.countries_array);
        int cId[] ={R.drawable.flag1, R.drawable.flag2,  R.drawable.flag3, R.drawable.flag4,
                R.drawable.flag5,R.drawable.flag6,R.drawable.flag7,R.drawable.flag8,R.drawable.flag9,
                R.drawable.flag10,R.drawable.flag11,R.drawable.flag12,R.drawable.flag13,
                R.drawable.flag14,R.drawable.flag15,R.drawable.flag16, R.drawable.flag17,
                R.drawable.flag18,R.drawable.flag19,R.drawable.flag20,R.drawable.flag21,
                R.drawable.flag22,R.drawable.flag23,R.drawable.flag24,R.drawable.flag25,
                R.drawable.flag26,R.drawable.flag27,R.drawable.flag28,R.drawable.flag29,
                R.drawable.flag30,R.drawable.flag31};

        for(int i=0;i<31;i++)
            countryData.add(new MoreKnowledgeModel(cName[i], cId[i],uri[i]));
      recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(new MoreKnowledgeAdapter(countryData,this));

    }
    public void popMenu(View view) {

        PopupMenu popupMenu =new PopupMenu(this,view);
        MenuInflater inflater=popupMenu.getMenuInflater();
        inflater.inflate(R.menu.menu,popupMenu.getMenu());


        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.review:
                        handleReview();
                        return  true;

                    case R.id.share:
                        Intent shareIntent = new Intent(Intent.ACTION_SEND);
                        shareIntent.setType("text/plain");
                        String shareSubText = "WhatsApp - The Great Chat App";
                        String shareBodyText = "https://play.google.com/store/apps/details?id=com.whatsapp&hl=en";
                        shareIntent.putExtra(Intent.EXTRA_SUBJECT, shareSubText);
                        shareIntent.putExtra(Intent.EXTRA_TEXT, shareBodyText);
                        startActivity(Intent.createChooser(shareIntent, "Share With"));
                        return true;

                }
                return true;
            }
        });
        popupMenu.show();
    }

    private void handleReview() {
        final Dialog dialog1 = new Dialog(this);
        dialog1.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog1.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog1.setContentView(R.layout.rate_app);
        dialog1.setCancelable(true);
        dialog1.show();
        RatingBar ratingBar = dialog1.findViewById(R.id.ratingBar);
        final String rt = String.valueOf(ratingBar.getRating());
        Button ok = dialog1.findViewById(R.id.btn_OK);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), rt, Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void backPressed(View view) {
        finish();
    }

    }



