package com.example.mert.ww2database.Adapter;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mert.ww2database.Activity.MajorNationsActivity;
import com.example.mert.ww2database.Activity.NationsActivity;
import com.example.mert.ww2database.Database.DatabaseHelper;
import com.example.mert.ww2database.Model.Nations;
import com.example.mert.ww2database.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MajorNationRecyclerViewAdapter extends RecyclerView.Adapter<MajorNationRecyclerViewAdapter.ViewHolder> {
    private List<Nations> listMajorNations;
    private Context mContext;
    private LayoutInflater layoutInflater;
    public MajorNationRecyclerViewAdapter(List<Nations> listMajorNations, Context mContext) {
        this.listMajorNations = listMajorNations;
        this.mContext = mContext;

    }

    @NonNull
    @Override
    public MajorNationRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        view=layoutInflater.inflate(R.layout.cardview_item_nations,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MajorNationRecyclerViewAdapter.ViewHolder holder, final int position) {
        int id=mContext.getResources().getIdentifier(listMajorNations.get(position).getFlag(),"drawable",mContext.getPackageName());
        Glide.with(mContext).load(id).into(holder.imageViewCountryFlag);
        holder.textViewCountryName.setText(listMajorNations.get(position).getCountry_name());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MajorNationsActivity.class);
                intent.putExtra("nation", listMajorNations.get(position));
                mContext.startActivity(intent);            }
        });
    }

    @Override
    public int getItemCount() {
        return listMajorNations.size();
    }

    @Override
    public long getItemId(int position) {
        return listMajorNations.get(position).getId();
    }

    //
    public class ViewHolder extends RecyclerView.ViewHolder {
        Context context=null;
        TextView textViewCountryName;
        ImageView imageViewCountryFlag;
        CardView myCardView;

        public ViewHolder(View itemView) {
            super(itemView);
            context=itemView.getContext();
            textViewCountryName=itemView.findViewById(R.id.text_view_country_name);
            imageViewCountryFlag=itemView.findViewById(R.id.image_view_country_flag);
            myCardView=itemView.findViewById(R.id.cardview_item_nations);

        }

    }
}
