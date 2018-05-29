package com.example.mert.ww2database.Activity;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.media.Image;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mert.ww2database.Database.DatabaseHelper;
import com.example.mert.ww2database.Model.Profile;
import com.example.mert.ww2database.R;

import java.io.IOException;
import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {

    public TextView profileName,profileCountry,profileCategory,profileBorn,profileDead,profileDesc,profileGender;
    public ImageView profileImg;
    public DatabaseHelper dbHelper;
    public SQLiteDatabase db;
    public ArrayList<Profile> profiles;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);



        profileName=findViewById(R.id.profileName);
        profileCountry=findViewById(R.id.profileCountry);
        profileCategory=findViewById(R.id.profileCategory);
        profileBorn=findViewById(R.id.profileBorn);
        profileDead=findViewById(R.id.profileDied);
        profileDesc=findViewById(R.id.profileDescription);
        profileImg=findViewById(R.id.profileImage);
        profileGender=findViewById(R.id.profileGender);
        ImageView back=findViewById(R.id.backprofile);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Intent intent=getIntent();
        try {
            dbHelper = new DatabaseHelper(getApplicationContext());
        } catch (IOException e) {
            e.printStackTrace();
        }
        db = dbHelper.getReadableDatabase();


      //  String Title = intent.getExtras().getString("Title");
        Profile profile =(Profile) getIntent().getSerializableExtra("profile") ;

        profileName.setText(profile.getName());
        profileCountry.setText(profile.getCountry());
        profileCategory.setText(profile.getCategory());
        profileBorn.setText(profile.getBirth() + "");
        profileDead.setText(profile.getDeath() + "");
        profileDesc.setText(profile.getDesription());
        profileGender.setText(profile.getGender());
        Glide.with(getApplicationContext()).load(profile.getImage_url()).into(profileImg);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_fav:

                break;

        }
        return super.onOptionsItemSelected(item);
    }
}
