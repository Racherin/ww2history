package com.example.mert.ww2database.Activity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.mert.ww2database.R;
import com.google.firebase.iid.FirebaseInstanceId;
import com.polyak.iconswitch.IconSwitch;

import java.io.IOException;

public class SettingsActivity extends AppCompatActivity {
    FirebaseInstanceId firebaseInstanceId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ImageView back=findViewById(R.id.backsettings);
        LinearLayout rate=findViewById(R.id.rate);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        IconSwitch disableNotifications=findViewById(R.id.switchnotifications);
        disableNotifications.setCheckedChangeListener(new IconSwitch.CheckedChangeListener() {
            @Override
            public void onCheckChanged(IconSwitch.Checked current) {
                switch (current){
                    case LEFT:
                        Toast.makeText(getApplicationContext(),"Notifications are enabled.",Toast.LENGTH_SHORT).show();
                        FirebaseInstanceId.getInstance().getToken();
                        break;
                    case RIGHT:
                        Toast.makeText(getApplicationContext(),"Notifications are disabled.",Toast.LENGTH_SHORT).show();
                        try {
                            FirebaseInstanceId.getInstance().deleteInstanceId();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                }
            }
        });


    }
    public void rateMe(View v) {
        try {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("market://details?id=" + getPackageName())));
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id=" + getPackageName())));
        }
    }
}
