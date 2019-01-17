package com.example.choi.suv;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Compare extends AppCompatActivity implements ServerResponse {

     Spinner comSpinner1;
     Spinner comSpinner2;
     ListView fListView;
     ListView sListView;
     public Context context_list4;
     public Context context_list5;
     public Context context_list6;
     public Context context_list7;
     public ImageView imageview1;
     public ImageView imageview2;
     private Compare thisss;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.compare);
       thisss=this;
        comSpinner1 = findViewById(R.id.comSpinner1);
        comSpinner2 = findViewById(R.id.comSpinner2);
        context_list4 = getBaseContext();
        fListView = findViewById(R.id.fListView);
        sListView = findViewById(R.id.s_ListView);
        context_list5 = getBaseContext();

        context_list6 = getBaseContext();
        context_list7 = getBaseContext();
        imageview1 = findViewById(R.id.imageview1);
        imageview2 = findViewById(R.id.imageview2);
       // new Server().execute("http://www.udmcps.com:14101/caridlist");
        new Server().onDb("http://www.udmcps.com:14101/caridlist",null,thisss);
    /*    HashMap<String, String> parameter = new HashMap<>();

        parameter.put("br",spinner.getSelectedItem().toString());

        new Server().onDb("http://www.udmcps.com:14101/carid",parameter,thiss);*/
        comSpinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                HashMap<String, String> parameter = new HashMap<>();
                parameter.put("caridd",comSpinner1.getSelectedItem().toString());
                new Server().onDb("http://www.udmcps.com:14101/caridlist1",parameter,thisss);
            //    new Server().execute("http://www.udmcps.com:14101/caridlist1?caridd=" + comSpinner1.getSelectedItem().toString());
                HashMap<String, String> parameter1 = new HashMap<>();
                parameter1.put("car",comSpinner1.getSelectedItem().toString());
                new Server().onDb("http://www.udmcps.com:14101/carinfor2",parameter1,thisss);
             //   new Server().execute("http://www.udmcps.com:14101/carinfor2?car=" + comSpinner1.getSelectedItem().toString());

                comSpinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        HashMap<String, String> parameter2 = new HashMap<>();
                        parameter2.put("car",comSpinner2.getSelectedItem().toString());
                        new Server().onDb("http://www.udmcps.com:14101/carinfor3",parameter2,thisss);
                     //   new Server().execute("http://www.udmcps.com:14101/carinfor3?car=" + comSpinner2.getSelectedItem().toString());
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });


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
            comSpinner1.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private  void comCarList1(String s) {


        try {
            JSONArray jArray = new JSONArray(s);
            String[] car_list = new String[10];

            for (int i = 0; i < jArray.length(); i++)
                car_list[i] = String.valueOf(jArray.getJSONObject(i).get("carid"));


            ArrayList<String> list = new ArrayList<>();
            ArrayAdapter<String> adapter = new ArrayAdapter<>(context_list5, android.R.layout.simple_spinner_item, list);
            for (int i = 0; i < jArray.length(); i++)
                list.add(car_list[i]);
            comSpinner2.setAdapter(adapter);


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void setListCarInfor2(String s) {

        try {
            JSONArray jArray = new JSONArray(s);
            ArrayList<String> Data = new ArrayList<>();
            ArrayAdapter<String> adapter = new ArrayAdapter<>(context_list6, android.R.layout.simple_spinner_item, Data);


            Data.add("가격: " + String.valueOf(jArray.getJSONObject(0).get("price")));
            Data.add("출시일: " + String.valueOf(jArray.getJSONObject(0).get("release")));
            Data.add("크기: " + String.valueOf(jArray.getJSONObject(0).get("size")));
            Data.add("타입: " + String.valueOf(jArray.getJSONObject(0).get("type")));
            Data.add("연료: " + String.valueOf(jArray.getJSONObject(0).get("oil")));
            Data.add("연비: " + String.valueOf(jArray.getJSONObject(0).get("mileage")));
            Data.add("마력: " + String.valueOf(jArray.getJSONObject(0).get("hp")));


            fListView.setAdapter(adapter);


            String id = String.valueOf(jArray.getJSONObject(0).get("carid"));

            if (id.equals("kona"))
                imageview1.setImageResource(R.drawable.kona);
            if (id.equals("qm3"))
                imageview1.setImageResource(R.drawable.qm3);
            if (id.equals("stonic"))
                imageview1.setImageResource(R.drawable.stonic);
            if (id.equals("tivoli"))
                imageview1.setImageResource(R.drawable.tivoli);
            if (id.equals("trax"))
                imageview1.setImageResource(R.drawable.trax);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public  void setListCarInfor3(String s) {

        try {
            JSONArray jArray = new JSONArray(s);
            ArrayList<String> Data = new ArrayList<>();
            ArrayAdapter<String> adapter = new ArrayAdapter<>(context_list7, android.R.layout.simple_spinner_item, Data);


            Data.add("가격: " + String.valueOf(jArray.getJSONObject(0).get("price")));
            Data.add("출시일: " + String.valueOf(jArray.getJSONObject(0).get("release")));
            Data.add("크기: " + String.valueOf(jArray.getJSONObject(0).get("size")));
            Data.add("타입: " + String.valueOf(jArray.getJSONObject(0).get("type")));
            Data.add("연료: " + String.valueOf(jArray.getJSONObject(0).get("oil")));
            Data.add("연비: " + String.valueOf(jArray.getJSONObject(0).get("mileage")));
            Data.add("마력: " + String.valueOf(jArray.getJSONObject(0).get("hp")));


            sListView.setAdapter(adapter);


            String id = String.valueOf(jArray.getJSONObject(0).get("carid"));

            if (id.equals("kona"))
                imageview2.setImageResource(R.drawable.kona);
            if (id.equals("qm3"))
                imageview2.setImageResource(R.drawable.qm3);
            if (id.equals("stonic"))
                imageview2.setImageResource(R.drawable.stonic);
            if (id.equals("tivoli"))
                imageview2.setImageResource(R.drawable.tivoli);
            if (id.equals("trax"))
                imageview2.setImageResource(R.drawable.trax);


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void processFinish(String output) throws JSONException {

        JSONObject jObject = new JSONObject(output);
        String code = jObject.getString("code");

        switch (code){

            case "caridlist":
                comCarList(jObject.getString("data"));
                break;
            case "caridlist1":
                comCarList1(jObject.getString("data"));
                break;
            case "carinfor2":
                setListCarInfor2(jObject.getString("data"));
                break;
            case "carinfor3":
                setListCarInfor3(jObject.getString("data"));
                break;

        }

    }
}





