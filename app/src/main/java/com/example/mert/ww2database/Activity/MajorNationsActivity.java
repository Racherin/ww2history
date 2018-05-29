package com.example.mert.ww2database.Activity;

import android.database.sqlite.SQLiteDatabase;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mert.ww2database.Database.DatabaseHelper;
import com.example.mert.ww2database.Model.Nations;
import com.example.mert.ww2database.R;

import java.io.IOException;

public class MajorNationsActivity extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    TextView tvCountry,tvDesc,tvAlliance;
    ImageView ivFlag;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_major_nations);

        tvCountry=findViewById(R.id.textview_majorcountryid);
        tvDesc=findViewById(R.id.textview_majorcountrydescription);
        tvAlliance=findViewById(R.id.textview_majorcountryalliance);
        ivFlag=findViewById(R.id.majornationflag);
        ImageView back=findViewById(R.id.backmnations);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Nations nations=(Nations) getIntent().getSerializableExtra("nation");
        int resim = getResources().getIdentifier(nations.getFlag(),"drawable",getPackageName());
        try {
            databaseHelper = new DatabaseHelper(getApplicationContext());
        } catch (IOException e) {
            e.printStackTrace();
        }
        db = databaseHelper.getReadableDatabase();
        final int id = nations.getId();

        tvCountry.setText(nations.getCountry_name());
        tvDesc.setText(nations.getDescription());
        tvAlliance.setText(nations.getCountry_alliance());
        Glide.with(getApplicationContext()).load(resim).into(ivFlag);


    }
}
