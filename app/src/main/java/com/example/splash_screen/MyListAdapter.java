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

public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder>{
    private MyListDataModel[] listData;

    public MyListAdapter(MyListDataModel[] listData) {
        this.listData = listData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View listItem=layoutInflater.inflate(R.layout.list_data,parent,false);
        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder,final int position) {
        viewHolder.txtTitle.setText(listData[position].getTitle());
        viewHolder.imgView.setImageResource(listData[position].getImgId());
        viewHolder.imgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (position){
                    case 0:
                            v.getContext().startActivity(new Intent(v.getContext(),OfflineMapMain.class));
                            break;
                    case 1:
                           v.getContext().startActivity(new Intent(v.getContext(),OnlineMap.class));
                        break;
                    case 2:
                        Toast.makeText(v.getContext(),"Coming Soon",Toast.LENGTH_SHORT).show();
                        Toast.makeText(v.getContext(),"Coming Soon",Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                           v.getContext().startActivity(new Intent(v.getContext(),MoreKnowledgeMain.class));
                        break;


                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return listData.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
      private ImageView imgView;
      private TextView txtTitle;
      private LinearLayout linearLayout;
      private ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.imgView=itemView.findViewById(R.id.imgView);
            this.txtTitle=itemView.findViewById(R.id.txtTitle);
            linearLayout=itemView.findViewById(R.id.linearLayout);
        }
    }
}
