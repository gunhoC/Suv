package com.example.choi.suv;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Mycom extends AppCompatActivity implements ServerResponse {

    Spinner fiSpinner;
    ListView fList;
    ListView sList;
    public Context context_list4;  //spinner
    public Context context_list5;
    public Context context_list6;
    Mycom thi;
    ImageView ima;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_car_com);
        Button btn = findViewById(R.id.btn);
        context_list6 = getBaseContext();
        fiSpinner = findViewById(R.id.fiSpinner1);
        fList = findViewById(R.id.f_ListView);
        sList = findViewById(R.id.s_ListView);
        context_list4 = getBaseContext();
        context_list5 = getBaseContext();
        thi = this;
        ima = findViewById(R.id.se);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Mycom.this,Infor.class);
                startActivity(intent);
            }
        });

        new Server().onDb("http://www.udmcps.com:14101/caridlist", null, thi);


        fiSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                HashMap<String, String> parameter = new HashMap<>();
                parameter.put("car", fiSpinner.getSelectedItem().toString());
                new Server().onDb("http://www.udmcps.com:14101/carinfor", parameter, thi);
                new Server().onDb("http://www.udmcps.com:14101/mycarinfor", null, thi);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    private void comCarList(String s) {

        try {
            JSONArray jArray = new JSONArray(s);
            String[] car_list = new String[10];

            for (int i = 0; i < jArray.length(); i++)
                car_list[i] = String.valueOf(jArray.getJSONObject(i).get("carid"));

            ArrayList<String> list = new ArrayList<>();
            ArrayAdapter<String> adapter = new ArrayAdapter<>(context_list4, android.R.layout.simple_spinner_item, list);
            for (int i = 0; i < jArray.length(); i++)
                list.add(car_list[i]);
            fiSpinner.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void setListCarInfor2(String s) {

        try {
            JSONArray jArray = new JSONArray(s);
            ArrayList<String> Data = new ArrayList<>();
            ArrayAdapter<String> adapter = new ArrayAdapter<>(context_list5, android.R.layout.simple_spinner_item, Data);


            Data.add("가격: " + String.valueOf(jArray.getJSONObject(0).get("price")));
            Data.add("출시일: " + String.valueOf(jArray.getJSONObject(0).get("release")));
            Data.add("크기: " + String.valueOf(jArray.getJSONObject(0).get("size")));
            Data.add("타입: " + String.valueOf(jArray.getJSONObject(0).get("type")));
            Data.add("연료: " + String.valueOf(jArray.getJSONObject(0).get("oil")));
            Data.add("연비: " + String.valueOf(jArray.getJSONObject(0).get("mileage")));
            Data.add("마력: " + String.valueOf(jArray.getJSONObject(0).get("hp")));


            fList.setAdapter(adapter);


            String id = String.valueOf(jArray.getJSONObject(0).get("carid"));

            if (id.equals("kona"))
                ima.setImageResource(R.drawable.kona);
            if (id.equals("qm3"))
                ima.setImageResource(R.drawable.qm3);
            if (id.equals("stonic"))
                ima.setImageResource(R.drawable.stonic);
            if (id.equals("tivoli"))
                ima.setImageResource(R.drawable.tivoli);
            if (id.equals("trax"))
                ima.setImageResource(R.drawable.trax);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void processFinish(String output) throws JSONException {

        JSONObject jObject = new JSONObject(output);
        String code = jObject.getString("code");

        switch (code) {

            case "caridlist":
                comCarList(jObject.getString("data"));
                break;
            case "carinfor":
                setListCarInfor2(jObject.getString("data"));
                break;
            case "mycarinfor":
                try {
                    JSONArray jArray = new JSONArray(jObject.getString("data"));
                    ArrayList<String> Data = new ArrayList<>();
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(context_list6, android.R.layout.simple_spinner_item, Data);


                    Data.add("가격: " + String.valueOf(jArray.getJSONObject(0).get("price")));
                    Data.add("출시일: " + String.valueOf(jArray.getJSONObject(0).get("release")));
                    Data.add("크기: " + String.valueOf(jArray.getJSONObject(0).get("size")));
                    Data.add("타입: " + String.valueOf(jArray.getJSONObject(0).get("type")));
                    Data.add("연료: " + String.valueOf(jArray.getJSONObject(0).get("oil")));
                    Data.add("연비: " + String.valueOf(jArray.getJSONObject(0).get("mileage")));
                    Data.add("마력: " + String.valueOf(jArray.getJSONObject(0).get("hp")));


                    sList.setAdapter(adapter);


                } catch (JSONException e) {
                    e.printStackTrace();
                }


                break;

        }
    }
}