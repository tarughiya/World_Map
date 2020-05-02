package com.example.splash_screen;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;


public class HomeActivity extends AppCompatActivity {
     RecyclerView recyclerView;
     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        MyListDataModel[] myListDatumModels =new MyListDataModel[]{
                new MyListDataModel("OFFLINE MAP",R.drawable.globe),
                new MyListDataModel("ONLINE MAP",R.drawable.globe),
                new MyListDataModel("WORLD QUIZ",R.drawable.globe),
                new MyListDataModel("KNOWLEDGE",R.drawable.globe)

        };
        recyclerView=findViewById(R.id.recycler1View);
        MyListAdapter adapter=new MyListAdapter(myListDatumModels);
        //recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager( new GridLayoutManager(this,2));
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
        final Dialog dialog1 = new Dialog(HomeActivity.this);
        dialog1.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog1.setContentView(R.layout.rate_app);
        dialog1.setCancelable(true);
        dialog1.show();
     final RatingBar ratingBar = dialog1.findViewById(R.id.ratingBar);

        final Button ok = dialog1.findViewById(R.id.btn_OK);
          ok.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  final String rt = String.valueOf(ratingBar.getRating());
                  Toast.makeText(HomeActivity.this, rt, Toast.LENGTH_SHORT).show();
              }
          });
    }


    public void backPressed(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Do you want to close this application ?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //  Action for 'NO' Button
                        dialog.cancel();

                    }
                });
        AlertDialog alert = builder.create();
        //Setting the title manually

        alert.show();
    }
}
