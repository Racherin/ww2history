package com.example.mert.ww2database.Fragment;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mert.ww2database.Adapter.EquipmentCategoryRecyclerViewAdapter;
import com.example.mert.ww2database.Database.DatabaseHelper;
import com.example.mert.ww2database.Model.Techizat;
import com.example.mert.ww2database.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FragmentEquipment extends Fragment {

    List<Techizat> techizat;
    EquipmentCategoryRecyclerViewAdapter adapter;
     public static DatabaseHelper dbHelper;
    public static  SQLiteDatabase db;

    public ArrayList<Techizat> getAllEqs(){
        ArrayList<Techizat> techizat=new ArrayList<>();
        try {
            dbHelper=new DatabaseHelper(getContext());
            db=dbHelper.getReadableDatabase();
            Cursor c=db.rawQuery("select * from EquipmentCategory",null);
            while (c.moveToNext()){
                int id=c.getInt(c.getColumnIndex("id"));
                String eqName=c.getString(c.getColumnIndex("eq_name"));
                String eqImg=c.getString(c.getColumnIndex("eq_photo"));
                techizat.add(new Techizat(id,eqName,eqImg));
            }
            c.close();
            db.close();
        } catch (IOException e) {
            e.printStackTrace();
        }



        return techizat;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_equipments2,container,false);
        techizat=new ArrayList<>();
        RecyclerView myRv=view.findViewById(R.id.recyclerview_eq_category);
        EquipmentCategoryRecyclerViewAdapter equipmentCategoryRecyclerViewAdapter=new EquipmentCategoryRecyclerViewAdapter(getContext(),getAllEqs());
        myRv.setAdapter(equipmentCategoryRecyclerViewAdapter);
        myRv.setLayoutManager(new GridLayoutManager(getContext(),2));
      return view;
    }


    }

