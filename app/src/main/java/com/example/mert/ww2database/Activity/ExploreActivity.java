package com.example.mert.ww2database.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mert.ww2database.Model.Explore;
import com.example.mert.ww2database.R;

public class ExploreActivity extends AppCompatActivity {
    ImageView ivMainActivityExplore;
    TextView tvMainActivityExplore;
    TextView tvDescActivityExplore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore);
        ivMainActivityExplore=findViewById(R.id.ivMainActivityExplore);
        tvMainActivityExplore=findViewById(R.id.tvMainActivityExplore);
        tvDescActivityExplore=findViewById(R.id.tvDescActivityExplore);
        ImageView back=findViewById(R.id.backexplore);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Explore mExplore=(Explore) getIntent().getSerializableExtra("explore");

        String img=mExplore.getContentImg().toString();
        tvMainActivityExplore.setText(mExplore.getContentName());
        tvDescActivityExplore.setText(mExplore.getId());



    }
}
