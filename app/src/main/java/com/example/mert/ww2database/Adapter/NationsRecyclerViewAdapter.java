package com.example.mert.ww2database.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mert.ww2database.Activity.NationsActivity;
import com.example.mert.ww2database.Activity.ProfileActivity;
import com.example.mert.ww2database.Fragment.FragmentNations;
import com.example.mert.ww2database.Fragment.FragmentPeople;
import com.example.mert.ww2database.Model.Nations;
import com.example.mert.ww2database.Model.Profile;
import com.example.mert.ww2database.R;

import java.util.ArrayList;
import java.util.List;

public class NationsRecyclerViewAdapter extends RecyclerView.Adapter<NationsRecyclerViewAdapter.myViewHolder> implements Filterable {

    private Context mContext;
    private ArrayList<Nations> originalArray, tempArray;
    CustomFilter cs;

    public NationsRecyclerViewAdapter(Context mContext, ArrayList<Nations> originalArray) {
        this.mContext = mContext;
        this.originalArray = originalArray;
        this.tempArray = originalArray;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.cardview_item_nations, parent, false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, final int position) {
        holder.textViewCountryName.setText(originalArray.get(position).getCountry_name());

        int id = mContext.getResources().getIdentifier(originalArray.get(position).getFlag(), "drawable", mContext.getPackageName());
        // holder.imageViewCountryFlag.setImageResource(id);
        Glide.with(mContext).load(id).into(holder.imageViewCountryFlag);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, NationsActivity.class);
                intent.putExtra("nation", originalArray.get(position));
                mContext.startActivity(intent);
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("");
                builder.setMessage("Would you like to add your favorites?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FragmentNations.db.execSQL("insert into Favourites values(NULL,"+originalArray.get(position).getId()+")");
                    }
                });
                builder.setNegativeButton("No", null);
                builder.create();
                builder.show();
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return originalArray.size();
    }


        @Override
        public Filter getFilter() {
            if (cs==null){
                cs=new CustomFilter();
            }


            return cs;
        }



    public static class myViewHolder extends RecyclerView.ViewHolder {

        TextView textViewCountryName;
        ImageView imageViewCountryFlag;
        CardView myCardView;

        public myViewHolder(View itemView) {
            super(itemView);
            textViewCountryName = itemView.findViewById(R.id.text_view_country_name);
            imageViewCountryFlag = itemView.findViewById(R.id.image_view_country_flag);
            myCardView = (CardView) itemView.findViewById(R.id.cardview_item_nations);

        }
    }

    class CustomFilter extends Filter {


        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            if (constraint != null && constraint.length() > 0) {
                constraint = constraint.toString().toUpperCase();
                ArrayList<Nations> filters = new ArrayList<>();


                for (int i = 0; i < tempArray.size(); i++) {
                    if (tempArray.get(i).getCountry_name().toUpperCase().contains(constraint)) {
                        Nations kisiler = new Nations(tempArray.get(i).getId(), tempArray.get(i).getCountry_name(), tempArray.get(i).getCountry_alliance(), tempArray.get(i).getPopulation_1939(), tempArray.get(i).getFlag(), tempArray.get(i).getDescription());
                        filters.add(kisiler);
                    }
                }
                results.count = filters.size();
                results.values = filters;


            } else {
                results.count = tempArray.size();
                results.values = tempArray;
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            originalArray=(ArrayList<Nations>)results.values;
            notifyDataSetChanged();
        }

    }
}
