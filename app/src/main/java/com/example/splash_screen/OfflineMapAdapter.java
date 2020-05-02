 package com.example.splash_screen;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class OfflineMapAdapter extends RecyclerView.Adapter<OfflineMapAdapter.ViewHolder> {
    private OfflineMapModel[] offlineMapModels;

    public OfflineMapAdapter(OfflineMapModel[] offlineMapModels) {
        this.offlineMapModels = offlineMapModels;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        LayoutInflater layoutInflater=  LayoutInflater.from(parent.getContext());
        View listItem=layoutInflater.inflate(R.layout.offline_map_list_continent,parent,false);
        ViewHolder viewHolder=new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        viewHolder.txtTitle.setText(offlineMapModels[position].getName());
        viewHolder.imageView.setImageResource(offlineMapModels[position].getImageView());
        viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(v.getContext(), offlineMapModels[position].getName(),Toast.LENGTH_LONG).show();
                switch (position){
                    case 0:
                        Intent intent0= new Intent(v.getContext(), OfflineMapMisc.class);
                        intent0.putExtra("continent","Africa");
                        intent0.putExtra("Population",121.61);
                        intent0.putExtra("Area",30.37);
                        intent0.putExtra("Countries",54);
                        intent0.putExtra("Image",R.drawable.africamap);
                        v.getContext().startActivity(intent0);
                        break;
                    case 1:
                        Intent intent1= new Intent(v.getContext(), OfflineMapMisc.class);
                        intent1.putExtra("continent","Asia");
                        intent1.putExtra("Population",446.27);
                        intent1.putExtra("Area",44.58);
                        intent1.putExtra("Countries",48);
                        intent1.putExtra("Image",R.drawable.asiamap);
                        v.getContext().startActivity(intent1);
                        break;
                    case 2:
                        Intent intent2= new Intent(v.getContext(), OfflineMapMisc.class);
                        intent2.putExtra("continent","Antarctica");
                        intent2.putExtra("Population",1106);
                        intent2.putExtra("Area",0.000142 );
                        intent2.putExtra("Countries",0);
                        intent2.putExtra("Image",R.drawable.antarcticamap);
                        v.getContext().startActivity(intent2);
                        break;
                    case 3:
                        Intent intent3= new Intent(v.getContext(), OfflineMapMisc.class);
                        intent3.putExtra("continent","Australia");
                        intent3.putExtra("Population",2.46);
                        intent3.putExtra("Area",0.0007692024);
                        intent3.putExtra("Countries",14);
                        intent3.putExtra("Image",R.drawable.australiamap);
                        v.getContext().startActivity(intent3);
                        break;
                    case 4:
                        Intent intent4= new Intent(v.getContext(), OfflineMapMisc.class);
                        intent4.putExtra("continent","Europe");
                        intent4.putExtra("Population",74.14);
                        intent4.putExtra("Area",10.18);
                        intent4.putExtra("Countries",51);
                        intent4.putExtra("Image",R.drawable.europemap);
                        v.getContext().startActivity(intent4);
                        break;
                    case 5:
                        Intent intent5= new Intent(v.getContext(), OfflineMapMisc.class);
                        intent5.putExtra("continent","North America");
                        intent5.putExtra("Population",57.9);
                        intent5.putExtra("Area",24.71);
                        intent5.putExtra("Countries",23);
                        intent5.putExtra("Image",R.drawable.northamericamap);
                        v.getContext().startActivity(intent5);
                        break;
                    case 6:
                        Intent intent6= new Intent(v.getContext(), OfflineMapMisc.class);
                        intent6.putExtra("continent","South America");
                        intent6.putExtra("Population",42.25);
                        intent6.putExtra("Area",17.84);
                        intent6.putExtra("Countries",12);
                        intent6.putExtra("Image",R.drawable.southamericamap);
                        v.getContext().startActivity(intent6);
                        break;
                    case 7:
                        Intent intent7= new Intent(v.getContext(), OfflineMapMisc.class);
                        intent7.putExtra("continent","World Map");
                        intent7.putExtra("Population", 753.04);
                        intent7.putExtra("Area", 510.072);
                        intent7.putExtra("Countries",195);
                        intent7.putExtra("Image",R.drawable.worldmap);
                        v.getContext().startActivity(intent7);
                        break;


                }

            }
        });
    }


    @Override
    public int getItemCount() {
        return offlineMapModels.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitle;
        ImageView imageView;
        LinearLayout linearLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.txtTitle=itemView.findViewById(R.id.txtTitle);
            this.imageView=itemView.findViewById(R.id.imageView);
            linearLayout=itemView.findViewById(R.id.linearLayout);
        }
    }
}
