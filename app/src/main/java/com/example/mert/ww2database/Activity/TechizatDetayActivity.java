package com.example.mert.ww2database.Activity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mert.ww2database.Adapter.TechizatDetayListViewAdapter;
import com.example.mert.ww2database.Database.DatabaseHelper;
import com.example.mert.ww2database.Model.TechizatDetay;
import com.example.mert.ww2database.R;

import java.io.IOException;
import java.util.ArrayList;

public class TechizatDetayActivity extends AppCompatActivity {
    ListView lv;
    public static DatabaseHelper dbHelper;
    public static SQLiteDatabase db;
    ArrayList<TechizatDetay> techizatDetay;
    TechizatDetayListViewAdapter techizatDetayListViewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_techizat_detay);

        ImageView back=findViewById(R.id.backtdetay);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        TextView tdetay=findViewById(R.id.tdetay);

        int id=getIntent().getIntExtra("kategori_id",0);
        String sayfaBasligi=getIntent().getStringExtra("name");
        tdetay.setText(sayfaBasligi);

        lv=findViewById(R.id.lvEqListView);
        techizatDetay=new ArrayList<>();
        techizatDetayListViewAdapter=new TechizatDetayListViewAdapter(this,techizatDetay);
        eqList(id);
        lv.setAdapter(techizatDetayListViewAdapter);
    }



    public void eqList (int kategoriId){
        try {
            dbHelper=new DatabaseHelper(this);
            db=dbHelper.getReadableDatabase();
            Cursor c=db.rawQuery("select * from Equipments where category_id="+kategoriId,null);
            while (c.moveToNext()){
                int id=c.getInt(c.getColumnIndex("id"));
                int category_id=c.getInt(c.getColumnIndex("category_id"));
                String eq_name =c.getString(c.getColumnIndex("eq_name"));
                String eq_photo=c.getString(c.getColumnIndex("eq_photo"));
                String eq_desc=c.getString(c.getColumnIndex("eq_description"));
                String eq_type=c.getString(c.getColumnIndex("eq_type"));
               String eq_country=c.getString(c.getColumnIndex("eq_country"));
                techizatDetay.add(new TechizatDetay(id,category_id,eq_name,eq_photo,eq_country,eq_type,eq_desc));


            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
