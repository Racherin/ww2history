package com.example.mert.ww2database.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mert.ww2database.Activity.TechizatDetayActivity;
import com.example.mert.ww2database.Activity.TechizatProfileActivity;
import com.example.mert.ww2database.Fragment.FragmentEquipment;
import com.example.mert.ww2database.Fragment.FragmentPeople;
import com.example.mert.ww2database.Model.TechizatDetay;
import com.example.mert.ww2database.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class TechizatDetayListViewAdapter extends BaseAdapter {
    ArrayList<TechizatDetay> techizatDetay;
    Context context;
    LayoutInflater layoutInflater;

    public TechizatDetayListViewAdapter(Context context, ArrayList<TechizatDetay> techizatDetay){
        this.context=context;
        this.techizatDetay=techizatDetay;
        this.layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }





    @Override
    public int getCount() {
        return techizatDetay.size();
    }

    @Override
    public Object getItem(int position) {
        return techizatDetay.get(position);
    }

    @Override
    public long getItemId(int position) {
        return techizatDetay.get(position).getId();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view=layoutInflater.inflate(R.layout.listview_equipments,parent,false);
        ImageView ivResim=view.findViewById(R.id.image_view_equipmentlist);
        TextView tvCountry=view.findViewById(R.id.text_view_equipmentcountry);
        TextView tvRole=view.findViewById(R.id.text_view_equipmentrole);
        TextView tvBaslik=view.findViewById(R.id.text_view_equipmentlist);

        Glide.with(context).load(techizatDetay.get(position).getImage()).into(ivResim);
        tvBaslik.setText(techizatDetay.get(position).getName());
        tvCountry.setText(techizatDetay.get(position).getCountry());
        tvRole.setText(techizatDetay.get(position).getPrimaryRole());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, TechizatProfileActivity.class);
                intent.putExtra("techizat",techizatDetay.get(position));
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
                        TechizatDetayActivity.db.execSQL("insert into Favourites values(NULL,"+techizatDetay.get(position).getId()+")");
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
