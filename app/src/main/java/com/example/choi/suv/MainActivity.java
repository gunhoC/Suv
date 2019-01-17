package com.example.choi.suv;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button car_list;
    private Button comp;
    private Button infor;
    private Button reco;
    static final String IPADDR = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        car_list = findViewById(R.id.car_list);
         reco = findViewById(R.id.reco);
        comp = findViewById(R.id.comp);
        infor = findViewById(R.id.infor);
        car_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {         //리스트 클릭시
                Intent intent = new Intent(MainActivity.this, ListActivity.class);
                startActivity(intent);
            }
        });

        comp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Compare.class);
                startActivity(intent);
            }
        });

        infor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Infor.class);
                startActivity(intent);


            }
        });

        reco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Mycom.class);
                startActivity(intent);
            }
        });




    }


}
