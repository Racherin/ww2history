package com.example.mert.ww2database.Fragment;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.mert.ww2database.Adapter.FavsEqListViewAdapter;
import com.example.mert.ww2database.Adapter.FavsNationListViewAdapter;
import com.example.mert.ww2database.Adapter.FavsProfileListViewAdapter;
import com.example.mert.ww2database.Database.DatabaseHelper;
import com.example.mert.ww2database.Model.Nations;
import com.example.mert.ww2database.Model.Profile;
import com.example.mert.ww2database.Model.TechizatDetay;
import com.example.mert.ww2database.R;

import java.io.IOException;
import java.util.ArrayList;

public class FragmentFavs extends Fragment {
    DatabaseHelper databaseHelper;
    SQLiteDatabase db;
    ListView listview ,listview2, listview3;
    FavsProfileListViewAdapter adapterProfile;
    FavsNationListViewAdapter adapterCountry;
    FavsEqListViewAdapter adapterEq;




    public ArrayList<Nations> getNat(){
        ArrayList<Nations> nations =new ArrayList<>();

        Cursor cursor=db.rawQuery("select * from Favourites",null);

        while (cursor.moveToNext()){

            int id=cursor.getInt(cursor.getColumnIndex("id"));
            Cursor c=databaseHelper.getRows("select * from Countries where id="+id);

            while(c.moveToNext()){

                int id2 = c.getInt(c.getColumnIndex("id"));
                String country_name =c.getString(c.getColumnIndex("country_name"));
                String flag=c.getString(c.getColumnIndex("flag"));
                int population=c.getInt(c.getColumnIndex("population_1939"));
                String country_alliance=c.getString(c.getColumnIndex("country_alliance"));
                String description=c.getString(c.getColumnIndex("description"));
                nations.add(new Nations(id2,country_name,country_alliance,population,flag,description));
            }
        }
        cursor.close();
        db.close();
        return nations;
    }
    public ArrayList<TechizatDetay> getEq(){
        ArrayList<TechizatDetay> techizatDetays=new ArrayList<>();
        Cursor cursor=db.rawQuery("select * from Favourites",null);
        while(cursor.moveToNext()){
            int id=cursor.getInt(cursor.getColumnIndex("id"));
            Cursor c=databaseHelper.getRows("select * from Equipments where id="+id);
            while (c.moveToNext()){
                int id2=c.getInt(c.getColumnIndex("id"));
                int category_id=c.getInt(c.getColumnIndex("category_id"));
                String eq_name =c.getString(c.getColumnIndex("eq_name"));
                String eq_photo=c.getString(c.getColumnIndex("eq_photo"));
                String eq_desc=c.getString(c.getColumnIndex("eq_description"));
                String eq_type=c.getString(c.getColumnIndex("eq_type"));
                String eq_country=c.getString(c.getColumnIndex("eq_country"));
                techizatDetays.add(new TechizatDetay(id2,category_id,eq_name,eq_photo,eq_country,eq_type,eq_desc));
            }
        }
        cursor.close();

        return techizatDetays;
    }
    public ArrayList<Profile> getProfiles(){
        ArrayList<Profile> profile= new ArrayList<>();

        Cursor cursor =db.rawQuery("select * from Favourites",null);

        while (cursor.moveToNext()){
            int id =cursor.getInt(cursor.getColumnIndex("id"));
            Cursor c=databaseHelper.getRows("select * from Profiles where id="+id);
            while (c.moveToNext()){
                int id2 =c.getInt(c.getColumnIndex("id"));

                String name=c.getString(c.getColumnIndex("name"));
                String country=c.getString(c.getColumnIndex("country"));
                String category=c.getString(c.getColumnIndex("category"));
                String image_url=c.getString(c.getColumnIndex("image_url"));
                String gender=c.getString(c.getColumnIndex("gender"));
                int birth=c.getInt(c.getColumnIndex("birth"));
                int death=c.getInt(c.getColumnIndex("death"));
                String description=c.getString(c.getColumnIndex("description"));

                profile.add(new Profile(id2,name,country,category,gender,birth,death,description,image_url));
            }
        }
        cursor.close();

        return profile;

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_favs,container,false);

        try {
            databaseHelper=new DatabaseHelper(getContext());
        } catch (IOException e) {
            e.printStackTrace();
        }
        db=databaseHelper.getReadableDatabase();
        listview=view.findViewById(R.id.listview_favs_profile);
        listview2=view.findViewById(R.id.listview_favs_countries);
        listview3=view.findViewById(R.id.listview_favs_equipments);
        adapterEq= new FavsEqListViewAdapter(getEq(),getContext());
        adapterCountry=new FavsNationListViewAdapter(getNat(),getContext());
        adapterProfile=new FavsProfileListViewAdapter(getProfiles(),getContext());
        listview3.setAdapter(adapterEq);
        listview.setAdapter(adapterProfile);
        listview2.setAdapter(adapterCountry);







        return view;
    }
}
