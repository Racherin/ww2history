package com.example.mert.ww2database.Adapter;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mert.ww2database.Activity.TechizatDetayActivity;
import com.example.mert.ww2database.Database.DatabaseHelper;
import com.example.mert.ww2database.Model.Techizat;
import com.example.mert.ww2database.Model.TechizatDetay;
import com.example.mert.ww2database.R;

import java.io.IOException;
import java.util.List;

public class EquipmentCategoryRecyclerViewAdapter extends RecyclerView.Adapter<EquipmentCategoryRecyclerViewAdapter.mViewHolder>{

    private List<Techizat> techizat;
    private Context context;
    private LayoutInflater layoutInflater;
    private DatabaseHelper dbHelper;
    private SQLiteDatabase db;

    public EquipmentCategoryRecyclerViewAdapter(Context context,List<Techizat> techizat) {
        this.techizat=techizat;
        this.context=context;
        try {
            dbHelper=new DatabaseHelper(context);
            db=dbHelper.getReadableDatabase();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @NonNull
    @Override
    public mViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_eq_item,parent,false);
        EquipmentCategoryRecyclerViewAdapter.mViewHolder holder=new EquipmentCategoryRecyclerViewAdapter.mViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull mViewHolder holder, final int position) {
        int id =context.getResources().getIdentifier(techizat.get(position).getEq_img(),"drawable",context.getPackageName());
        Glide.with(context).load(id).into(holder.ivEqCategoryImage);
        holder.tvEqCategoryName.setText(techizat.get(position).getEq_name());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, TechizatDetayActivity.class);
                intent.putExtra("kategori_id",techizat.get(position).getId());
                intent.putExtra("name",techizat.get(position).getEq_name());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return techizat.size();
    }

    @Override
    public long getItemId(int position) {
        return techizat.get(position).getId();
    }

    public static class mViewHolder extends RecyclerView.ViewHolder{
       public TextView tvEqCategoryName;
       public ImageView ivEqCategoryImage;
        public mViewHolder(View itemView) {
            super(itemView);
ivEqCategoryImage=itemView.findViewById(R.id.ivEqCategoryImage);
tvEqCategoryName=itemView.findViewById(R.id.tvEqCategoryName);
        }
    }



}
