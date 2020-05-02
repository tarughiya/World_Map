package com.example.splash_screen;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class NewTestAdapter extends RecyclerView.Adapter<NewTestAdapter.MyViewHolder> {


    View view;
    Context context;
    private CountryModel[] listData;

    public NewTestAdapter( Context context, CountryModel[] listData) {
        this.context = context;
        this.listData = listData;
    }

    View.OnClickListener onClickListener;




    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        view = LayoutInflater.from(context).inflate(R.layout.item_new_test_adapter,viewGroup,false);
        return new NewTestAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        final CountryModel mylistdata =listData[i];

        myViewHolder.textITemNewTestAdpater.setText(listData[i].getDescription());
        myViewHolder.imageView.setImageResource(listData[i].getImgId());
        myViewHolder.capital.setText(listData[i].getCapital());
       final String  latitude= listData[i].getLatValue();
        final String longitude=listData[i].getLongValue();

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a Uri from an intent string. Use the result to create an Intent.

                Intent a=new Intent(context,TabActivity.class);
                a.putExtra("latValue",latitude);
                a.putExtra("longValue",longitude);
                context.startActivity(a);
//                // Create an Intent from gmmIntentUri. Set the action to ACTION_VIEW
//                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
//                // Make the Intent explicit by setting the Google Maps package
//                mapIntent.setPackage("com.google.android.apps.maps");
//
//                // Attempt to start an activity that can handle the Intent
//                if (mapIntent.resolveActivity(getPackageManager()) != null) {
//                    startActivity(mapIntent);
//                }
                }
        });

    }

    @Override
    public int getItemCount() {
        return listData.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textITemNewTestAdpater;
        ImageView imageView;
        TextView capital;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textITemNewTestAdpater = itemView.findViewById(R.id.textITemNewTestAdpater);
            imageView=itemView.findViewById(R.id.imageView);
            capital=itemView.findViewById(R.id.capital);

        }
    }
    public View.OnClickListener getOnClickListener() {
        return onClickListener;
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
}

