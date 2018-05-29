package com.example.mert.ww2database.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mert.ww2database.Model.TechizatDetay;
import com.example.mert.ww2database.R;

import java.util.ArrayList;

public class FavsEqListViewAdapter extends BaseAdapter {
    ArrayList<TechizatDetay> techizatDetays;
    Context context;
    LayoutInflater layoutInflater;

    public FavsEqListViewAdapter(ArrayList<TechizatDetay> techizatDetays, Context context) {
        this.techizatDetays = techizatDetays;
        this.context = context;
        layoutInflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return techizatDetays.size();
    }

    @Override
    public Object getItem(int position) {
        return techizatDetays.get(position);
    }

    @Override
    public long getItemId(int position) {
        return techizatDetays.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view=layoutInflater.inflate(R.layout.listview_favs,parent,false);
        TextView tvName=view.findViewById(R.id.listview_favs_name);
        tvName.setText(techizatDetays.get(position).getName());

        return view;
    }
}
