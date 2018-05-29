package com.example.mert.ww2database.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mert.ww2database.Fragment.FragmentPeople;
import com.example.mert.ww2database.Model.Profile;
import com.example.mert.ww2database.R;

import java.util.ArrayList;


public class FavsProfileListViewAdapter extends BaseAdapter {

    public ArrayList<Profile> profile;
    Context context;
    LayoutInflater layoutInflater;

    public FavsProfileListViewAdapter(ArrayList<Profile> profile, Context context) {
        this.profile = profile;
        this.context = context;
        layoutInflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return profile.size();
    }

    @Override
    public Object getItem(int position) {
        return profile.get(position);
    }

    @Override
    public long getItemId(int position) {
        return profile.get(position).getId();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view= layoutInflater.inflate(R.layout.listview_favs,parent,false);
        TextView tvName= view.findViewById(R.id.listview_favs_name);
        tvName.setText(profile.get(position).getName());

        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("");
                builder.setMessage("Would you like to delete from your favorites?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FragmentPeople.db.execSQL("DELETE FROM Favourites WHERE profile_id="+profile.get(position).getId()+"");
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
}
