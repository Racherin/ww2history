package com.example.mert.ww2database.Fragment;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.mert.ww2database.Adapter.MajorNationRecyclerViewAdapter;
import com.example.mert.ww2database.Adapter.NationsRecyclerViewAdapter;
import com.example.mert.ww2database.Database.DatabaseHelper;
import com.example.mert.ww2database.Model.Nations;
import com.example.mert.ww2database.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FragmentNations extends Fragment implements TextWatcher {
    ArrayList<Nations> listNations;
    EditText editTextSearch2;
    NationsRecyclerViewAdapter myAdapter;
    ArrayList<Nations> listMajorNations;
    MajorNationRecyclerViewAdapter majorNationRecyclerViewAdapter;
    DatabaseHelper dbHelper;
    public static SQLiteDatabase db;
    public ArrayList<Nations> getAllMajors(){
        ArrayList<Nations> nations=new ArrayList<>();

        try {
            dbHelper=new DatabaseHelper(getContext());
            db=dbHelper.getReadableDatabase();
            Cursor c=db.rawQuery("select * from MajorCountries",null);
            while(c.moveToNext()){
                int id = c.getInt(c.getColumnIndex("id"));
                String country_name =c.getString(c.getColumnIndex("country_name"));
                String flag=c.getString(c.getColumnIndex("flag"));
                int population=c.getInt(c.getColumnIndex("population_1939"));
                String country_alliance=c.getString(c.getColumnIndex("country_alliance"));
                String description=c.getString(c.getColumnIndex("description"));
                nations.add(new Nations(id,country_name,country_alliance,population,flag,description));

            }
            c.close();
            db.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return nations;
    }

    public ArrayList<Nations> getAllNations(){
        ArrayList<Nations> nations = new ArrayList<>();

        try {
            dbHelper=new DatabaseHelper(getContext());
            db=dbHelper.getReadableDatabase();
            Cursor c=db.rawQuery("select * from Countries",null);

            while (c.moveToNext()){
                int id = c.getInt(c.getColumnIndex("id"));
                String country_name =c.getString(c.getColumnIndex("country_name"));
                String flag=c.getString(c.getColumnIndex("flag"));
                int population=c.getInt(c.getColumnIndex("population_1939"));
                String country_alliance=c.getString(c.getColumnIndex("country_alliance"));
                String description=c.getString(c.getColumnIndex("description"));
                nations.add(new Nations(id,country_name,country_alliance,population,flag,description));

            }

        } catch (IOException e) {
            e.printStackTrace();
        }


        return nations;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nations, container, false);
        listNations = new ArrayList<>();
        listMajorNations = new ArrayList<>();
        RecyclerView myrv2=view.findViewById(R.id.recyclerview_majornations);
        RecyclerView myrv = view.findViewById(R.id.recyclerview_nations);

        MajorNationRecyclerViewAdapter myAdapter2=new MajorNationRecyclerViewAdapter(getAllMajors(),getContext());
        myAdapter = new NationsRecyclerViewAdapter(getContext(), getAllNations());

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);



        myrv2.setAdapter(myAdapter2);
        myrv.setAdapter(myAdapter);
        myrv2.setLayoutManager(layoutManager);

        myrv.setLayoutManager(new GridLayoutManager(getContext(), 5));


        editTextSearch2 = view.findViewById(R.id.editTextSearchBar2);

        editTextSearch2.addTextChangedListener(this);


        return view;
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        this.myAdapter.getFilter().filter(s);
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}