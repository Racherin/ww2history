package com.example.mert.ww2database.Fragment;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.mert.ww2database.Activity.ProfileActivity;
import com.example.mert.ww2database.Adapter.KisilerListViewAdapter;
import com.example.mert.ww2database.Database.DatabaseHelper;
import com.example.mert.ww2database.Model.Profile;
import com.example.mert.ww2database.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class FragmentPeople extends Fragment implements TextWatcher, PopupMenu.OnMenuItemClickListener {
    ListView lv;
    ArrayList<Profile> mylist;
    TextView et;
    KisilerListViewAdapter kisilerListViewAdapter;
    Button popup;
    DatabaseHelper dbHelper;
    public static SQLiteDatabase db;


    public ArrayList<Profile> getProfiles(){
        ArrayList<Profile> profiles = new ArrayList<>();
        Cursor c =db.rawQuery("select * from Profiles",null);


        while (c.moveToNext()){
            int id =c.getInt(c.getColumnIndex("id"));

            String name=c.getString(c.getColumnIndex("name"));
            String country=c.getString(c.getColumnIndex("country"));
            String category=c.getString(c.getColumnIndex("category"));
            String image_url=c.getString(c.getColumnIndex("image_url"));
            String gender=c.getString(c.getColumnIndex("gender"));
            int birth=c.getInt(c.getColumnIndex("birth"));
            int death=c.getInt(c.getColumnIndex("death"));
            String description=c.getString(c.getColumnIndex("description"));

            profiles.add(new Profile(id,name,country,category,gender,birth,death,description,image_url));
        }
        c.close();


        return profiles;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_people, container, false);
        mylist = new ArrayList<>();
        lv = view.findViewById(R.id.listViewPeople);
        et = view.findViewById(R.id.etSearch);

        try {
            dbHelper =new DatabaseHelper(getContext());
            db=dbHelper.getReadableDatabase();
            kisilerListViewAdapter = new KisilerListViewAdapter(getContext(), getProfiles());
            lv.setAdapter(kisilerListViewAdapter);
        } catch (IOException e) {
            e.printStackTrace();
        }


        et.addTextChangedListener(this);

        sortArrayList();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getContext(),ProfileActivity.class));
            }
        });
        return view;


    }

    @Override
    public void onResume() {
        super.onResume();
        try {
            dbHelper =new DatabaseHelper(getContext());
            db=dbHelper.getReadableDatabase();
            kisilerListViewAdapter = new KisilerListViewAdapter(getContext(), getProfiles());
            lv.setAdapter(kisilerListViewAdapter);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void sortArrayList() {
        Collections.sort(mylist, new Comparator<Profile>() {
            @Override
            public int compare(Profile o1, Profile o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
    }
    private void sortArrayListCountry() {
        Collections.sort(mylist, new Comparator<Profile>() {
            @Override
            public int compare(Profile o1, Profile o2) {
                return o1.getCountry().compareTo(o2.getCountry());
            }
        });
    }
    private void sortArrayListType() {
        Collections.sort(mylist, new Comparator<Profile>() {
            @Override
            public int compare(Profile o1, Profile o2) {
                return o1.getCategory().compareTo(o2.getCategory());
            }
        });
    }
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }
    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        this.kisilerListViewAdapter.getFilter().filter(s);
    }
    @Override
    public void afterTextChanged(Editable s) {

    }
    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(getContext(),v);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.popup_menu);
        popup.show();

    }
    @Override
    public boolean onMenuItemClick(MenuItem item) {
         switch (item.getItemId()){
             case R.id.item1:
                 sortArrayList();
                 kisilerListViewAdapter.notifyDataSetChanged();
                 return true;
             case R.id.item2:
                 sortArrayListCountry();
                        kisilerListViewAdapter.notifyDataSetChanged();
                 return true;
             case R.id.item3:
                 sortArrayListType();
                 kisilerListViewAdapter.notifyDataSetChanged();
                 return true;
                 default:
                     return false;
             }



    }
}
