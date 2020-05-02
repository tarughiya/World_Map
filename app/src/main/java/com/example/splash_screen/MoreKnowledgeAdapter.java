package com.example.splash_screen;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public class MoreKnowledgeAdapter extends RecyclerView.Adapter<MoreKnowledgeAdapter.FlagHolder> {
    private final Context context;
    private List<MoreKnowledgeModel>countryData;


    public MoreKnowledgeAdapter(List<MoreKnowledgeModel> countryData, Context context) {
        this.countryData = countryData;
        this.context = context;
    }

    @NonNull
    @Override
    public FlagHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
       View v = LayoutInflater.from(context).inflate(R.layout.flag_layout,viewGroup,false);
         return new FlagHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull FlagHolder flagHolder, int i) {
        int size = getItemCount();
        final MoreKnowledgeModel moreKnowledgeModelObj =countryData.get(i);
       flagHolder.flag.setImageResource(moreKnowledgeModelObj.cId);
     flagHolder.country.setText(moreKnowledgeModelObj.cName);
     flagHolder.flag.setTag(moreKnowledgeModelObj);
     flagHolder.flag.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             MoreKnowledgeModel moreKnowledgeModelTemp = (MoreKnowledgeModel) v.getTag();
             Toast.makeText(context, moreKnowledgeModelTemp.cName,Toast.LENGTH_LONG).show();
             Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(moreKnowledgeModelObj.uri));
             context.startActivity(myIntent);
         }
     });
    }

    @Override
    public int getItemCount() {
        return countryData.size();
    }

    public class FlagHolder extends RecyclerView.ViewHolder {
       ImageView flag;
       TextView country;
        public FlagHolder(@NonNull View itemView) {
            super(itemView);
            flag = itemView.findViewById(R.id.flag);
            country= itemView.findViewById(R.id.country);

        }
    }
}
