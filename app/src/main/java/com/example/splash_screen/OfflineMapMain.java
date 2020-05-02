package com.example.splash_screen;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.RatingBar;
import android.widget.Toast;

public class OfflineMapMain extends AppCompatActivity {
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.offline_map_main);
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recyclerView=findViewById(R.id.recycleView1);
        OfflineMapModel[] continentLists=new OfflineMapModel[]{
                new OfflineMapModel("Africa",R.drawable.africa),
                new OfflineMapModel("Asia",R.drawable.asia),
                new OfflineMapModel("Antarctica",R.drawable.antarctica),
                new OfflineMapModel("Australia",R.drawable.australia),
                new OfflineMapModel("Europe",R.drawable.europe),
                new OfflineMapModel("North America",R.drawable.northamerica),
                new OfflineMapModel("South America",R.drawable.southamerica),
                new OfflineMapModel("World Map",R.drawable.offlineworldmap)
        };
        OfflineMapAdapter adapter=new OfflineMapAdapter(continentLists);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);
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
