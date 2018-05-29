package com.example.mert.ww2database.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mert.ww2database.Activity.MainActivity;
import com.example.mert.ww2database.Activity.ProfileActivity;
import com.example.mert.ww2database.Fragment.FragmentFavs;
import com.example.mert.ww2database.Fragment.FragmentPeople;
import com.example.mert.ww2database.Model.Profile;
import com.example.mert.ww2database.R;

import java.util.ArrayList;

public class KisilerListViewAdapter extends BaseAdapter implements Filterable{

    Context mContext;
    ArrayList<Profile> originalArray,tempArray;

    ImageView iconPortrait;
    TextView tvName,tvType,tvCountry;
    CustomFilter cs;

    LayoutInflater layoutInflater;


    public KisilerListViewAdapter(Context mContext, ArrayList<Profile> originalArray){
        this.mContext=mContext;
        this.originalArray=originalArray;
        this.tempArray=originalArray;
        layoutInflater=(LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return originalArray.size();
    }

    @Override
    public Profile getItem(int position) {
        return originalArray.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view=layoutInflater.inflate(R.layout.listview_people,null);
        ImageView ivProfilePhoto;
        TextView tvName, tvCountry, tvCategory;

        ivProfilePhoto=view.findViewById(R.id.ivProfilePhotoListView);
        tvName=view.findViewById(R.id.tvPeopleNameListView);
        tvCountry=view.findViewById(R.id.tvPeopleCountryListView);
        tvCategory=view.findViewById(R.id.tvPeopleJobListView);


        Glide.with(mContext).load(originalArray.get(position).getImage_url()).into(ivProfilePhoto);
        tvName.setText(originalArray.get(position).getName());
         tvCountry.setText(originalArray.get(position).getCountry());
         tvCategory.setText(originalArray.get(position).getCategory());


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext, ProfileActivity.class);
               intent.putExtra("profile",originalArray.get(position));
                mContext.startActivity(intent);
            }
        });
        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Add to favourites.");
                builder.setMessage("Would you like to add your favorites?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FragmentPeople.db.execSQL("insert into Favourites values(NULL,"+originalArray.get(position).getId()+")");
                    }
                });
                builder.setNegativeButton("No",null);
                builder.create();
                builder.show();



                return false;
            }
        });



        return view;
    }

    @Override
    public Filter getFilter() {
        if (cs==null){
            cs=new CustomFilter();
        }


        return cs;
    }
    class CustomFilter extends Filter{


        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results =new FilterResults();
            if (constraint!=null && constraint.length()>0){
                constraint=constraint.toString().toUpperCase();
                ArrayList<Profile> filters= new ArrayList<>();


                for (int i =0;i<tempArray.size();i++) {
                    if (tempArray.get(i).getName().toUpperCase().contains(constraint))
                    {
                        Profile kisiler =new Profile(tempArray.get(i).getId(),tempArray.get(i).getName(),tempArray.get(i).getCountry(),tempArray.get(i).getCategory(),tempArray.get(i).getImage_url());
                        filters.add(kisiler);
                    }
                }
                results.count=filters.size();
                results.values=filters;



            }else{
                results.count=tempArray.size();
                results.values=tempArray;
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            originalArray=(ArrayList<Profile>)results.values;
            notifyDataSetChanged();

        }
    }
}
