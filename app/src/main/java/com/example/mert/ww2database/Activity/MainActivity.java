package com.example.mert.ww2database.Activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


import com.example.mert.ww2database.Fragment.FragmentEquipment;
import com.example.mert.ww2database.Fragment.FragmentFavs;
import com.example.mert.ww2database.Fragment.FragmentNations;
import com.example.mert.ww2database.Fragment.FragmentPeople;
import com.example.mert.ww2database.Fragment.FragmentExplore;
import com.example.mert.ww2database.R;
import com.polyak.iconswitch.IconSwitch;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    ImageView ivNations,ivPeople,ivEquipments,ivSearch,ivFavs, iconSettings,iconDonate;
    int tabIndex;
    Fragment fragment=null;
    public void changeFragment(int tabFragmentIndex){
        switch (tabFragmentIndex){
            case 0:
              fragment=new FragmentNations();
                break;
            case 1:
                fragment=new FragmentPeople();
                break;
            case 2:
                fragment=new FragmentEquipment();
                break;
            case 3:
                fragment=new FragmentExplore();
                break;
            case 4:
                fragment=new FragmentFavs();
                break;
        }
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container,fragment);
        fragmentTransaction.commit();
    }
    public void setDefaultImage(){
        ivNations.setImageResource(R.drawable.flag_black);
        ivPeople.setImageResource(R.drawable.soldier_black);
        ivEquipments.setImageResource(R.drawable.tank_black);
        ivSearch.setImageResource(R.drawable.search_black);
        ivFavs.setImageResource(R.drawable.fav_black);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ivNations=findViewById(R.id.ivNations);
        ivEquipments=findViewById(R.id.ivEquipment);
        ivFavs=findViewById(R.id.ivFavs);
        ivPeople=findViewById(R.id.ivPeople);
        ivSearch=findViewById(R.id.ivSearch);
        iconSettings=findViewById(R.id.iconsettings);
        iconDonate=findViewById(R.id.icondonate);
        ivSearch.setOnClickListener(this);
        ivPeople.setOnClickListener(this);
        ivFavs.setOnClickListener(this);
        ivEquipments.setOnClickListener(this);
        ivNations.setOnClickListener(this);

        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container,new FragmentNations());
        fragmentTransaction.commit();
        iconSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,SettingsActivity.class));
            }
        });
        iconDonate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent donateIntent = new Intent();
                donateIntent.setAction(Intent.ACTION_VIEW);
                donateIntent.addCategory(Intent.CATEGORY_BROWSABLE);
                donateIntent.setData(Uri.parse("https://salt.bountysource.com/teams/wwii-history"));
                startActivity(donateIntent);
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ivNations:
                tabIndex=0;
                setDefaultImage();
                ivNations.setImageResource(R.drawable.flag_red);
                break;
            case R.id.ivPeople:
                tabIndex=1;
                setDefaultImage();
                ivPeople.setImageResource(R.drawable.soldier_red);
                break;
            case R.id.ivEquipment:
                tabIndex=2;
                setDefaultImage();
                ivEquipments.setImageResource(R.drawable.tank_red);
                break;
            case R.id.ivSearch:
                tabIndex=3;
                setDefaultImage();
                ivSearch.setImageResource(R.drawable.search_red);
                break;
            case R.id.ivFavs:
                tabIndex=4;
                setDefaultImage();
                ivFavs.setImageResource(R.drawable.fav_red);
                break;
        }
        changeFragment(tabIndex);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_settings:
                Toast.makeText(getApplicationContext(),"Settings",Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_invite:
                Toast.makeText(getApplicationContext(),"Invite",Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_gethelp:
                Toast.makeText(getApplicationContext(),"Get Help",Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_feedback:
                Toast.makeText(getApplicationContext(),"Feedback",Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_donate:
                Toast.makeText(getApplicationContext(),"Feedback",Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
