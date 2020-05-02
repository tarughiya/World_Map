package com.example.splash_screen;


import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.PopupMenu;
import android.widget.RatingBar;
import android.widget.Toast;

public class OnlineMap extends AppCompatActivity implements View.OnClickListener {
    Button country,peak,river,wonder;
    FrameLayout child_fragment_container;
    View view;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        country=findViewById(R.id.country);
        river=findViewById(R.id.river);
        peak=findViewById(R.id.peak);
        wonder=findViewById(R.id.wonder);

        country.setOnClickListener(this);
        river.setOnClickListener(this);
        peak.setOnClickListener(this);
        wonder.setOnClickListener(this);
        CountryActivityFragment countryActivityFragment = new CountryActivityFragment();
        countryActivityFragment.setFromWhere("Country");
        loadFragment(countryActivityFragment);
        country.setBackgroundColor(getResources().getColor(R.color.blue));
        peak.setBackgroundColor(getResources().getColor(R.color.gray));
        river.setBackgroundColor(getResources().getColor(R.color.gray));
        wonder.setBackgroundColor(getResources().getColor(R.color.gray));

    }

    private void loadFragment(Fragment fragment) {
// create a FragmentManager
        FragmentManager fm = getFragmentManager();
// create a FragmentTransaction to begin the transaction and replace the Fragment
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
// replace the FrameLayout with new Fragment
        fragmentTransaction.add(R.id.child_fragment_container, fragment);
        fragmentTransaction.commit(); // save the changes
    }

    @Override
    public void onClick(View v) {
        CountryActivityFragment countryActivityFragment = new CountryActivityFragment();

        switch (v.getId()) {
            case R.id.country:

                countryActivityFragment.setFromWhere("Country");
                loadFragment(countryActivityFragment);
                country.setBackgroundColor(getResources().getColor(R.color.blue));
                peak.setBackgroundColor(getResources().getColor(R.color.gray));
                river.setBackgroundColor(getResources().getColor(R.color.gray));
                wonder.setBackgroundColor(getResources().getColor(R.color.gray));
                break;
            case R.id.peak:

                countryActivityFragment.setFromWhere("peak");
                loadFragment(countryActivityFragment);
                peak.setBackgroundColor(getResources().getColor(R.color.blue));
                country.setBackgroundColor(getResources().getColor(R.color.gray));
                river.setBackgroundColor(getResources().getColor(R.color.gray));
                wonder.setBackgroundColor(getResources().getColor(R.color.gray));
                break;
            case R.id.river:

                countryActivityFragment.setFromWhere("river");
                loadFragment(countryActivityFragment);
                river.setBackgroundColor(getResources().getColor(R.color.blue));
                peak.setBackgroundColor(getResources().getColor(R.color.gray));
                country.setBackgroundColor(getResources().getColor(R.color.gray));
                wonder.setBackgroundColor(getResources().getColor(R.color.gray));
                break;
            case R.id.wonder:
                countryActivityFragment.setFromWhere("wonder");
                loadFragment(countryActivityFragment);
                wonder.setBackgroundColor(getResources().getColor(R.color.blue));
                peak.setBackgroundColor(getResources().getColor(R.color.gray));
                river.setBackgroundColor(getResources().getColor(R.color.gray));
                country.setBackgroundColor(getResources().getColor(R.color.gray));
                break;

                default:
                    countryActivityFragment.setFromWhere("Country");
                    loadFragment(countryActivityFragment);
                    country.setBackgroundColor(getResources().getColor(R.color.blue));
                    peak.setBackgroundColor(getResources().getColor(R.color.gray));
                    river.setBackgroundColor(getResources().getColor(R.color.gray));
                    wonder.setBackgroundColor(getResources().getColor(R.color.gray));

        }

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
