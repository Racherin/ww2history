package com.example.mert.ww2database.Activity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mert.ww2database.R;

import org.w3c.dom.Text;

public class SplashActivity extends AppCompatActivity {
    private TextView tvSplash;
    private ImageView ivSplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);
        tvSplash=findViewById(R.id.tvsplash);
        ivSplash=findViewById(R.id.ivsplash);
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.transition_splash);
        tvSplash.startAnimation(animation);
        ivSplash.startAnimation(animation);
        if(networkConnection()){
            NewClass newClass=new NewClass();
            newClass.start();
        }else{
            Toast.makeText(getApplicationContext(), "Bağlantı problemi oluştu", Toast.LENGTH_LONG).show();
            finish();
        }

    }
    private class NewClass extends Thread{
        @Override
        public void run() {
            try{
                Thread.sleep(2500);
            }catch (Exception e){

            }

            Intent intent= new Intent(SplashActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
    public boolean networkConnection() {
        ConnectivityManager conMgr = (ConnectivityManager) getSystemService (Context.CONNECTIVITY_SERVICE);
        if (conMgr.getActiveNetworkInfo() != null && conMgr.getActiveNetworkInfo().isAvailable() && conMgr.getActiveNetworkInfo().isConnected()) {
            return true;
        }else{
            return false;
        }
    }
}
