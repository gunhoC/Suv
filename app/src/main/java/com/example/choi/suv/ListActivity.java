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
import android.widget.SpinnerAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class ListActivity extends AppCompatActivity implements ServerResponse {

    static public Spinner spinner;
    static public Context context_list;
    static public Spinner spinner2;
    static public Context context_list2;
    static public ListView listView;
    static public Context context_list3;
    static public ImageView imageView;
    private ListActivity thiss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);

        thiss = this;
        imageView = findViewById(R.id.imageview);

        spinner = findViewById(R.id.spinner);
        context_list = getBaseContext();

        new Server().onDb("http://www.udmcps.com:14101/brand", null, thiss);
        spinner2 = findViewById(R.id.spinner2);
        context_list2 = getBaseContext();

        listView = findViewById(R.id.listview);
        context_list3 = getBaseContext();
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                HashMap<String, String> parameter = new HashMap<>();

                parameter.put("br", spinner.getSelectedItem().toString());

                new Server().onDb("http://www.udmcps.com:14101/carid", parameter, thiss);

                spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        HashMap<String, String> parameter1 = new HashMap<>();
                        parameter1.put("car", spinner2.getSelectedItem().toString());
                        new Server().onDb("http://www.udmcps.com:14101/carinfor", parameter1, thiss);

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

    private void setSpinnerCarId(String s) {

        try {
            JSONArray jArray = new JSONArray(s);
            String[] str_carid = new String[10];

            for (int i = 0; i < jArray.length(); i++)
                str_carid[i] = String.valueOf(jArray.getJSONObject(i).get("carid"));

            ArrayList<String> list = new ArrayList<>();
            ArrayAdapter<String> adapter = new ArrayAdapter<>(ListActivity.context_list, android.R.layout.simple_spinner_item, list);
            for (int i = 0; i < jArray.length(); i++)
                list.add(str_carid[i]);
            spinner2.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void setListCarInfor(String s) {

        try {
            JSONArray jArray = new JSONArray(s);
            ArrayList<String> Data = new ArrayList<>();
            ArrayAdapter<String> adapter = new ArrayAdapter<>(ListActivity.context_list3, android.R.layout.simple_spinner_item, Data);

            Data.add("가격: " + String.valueOf(jArray.getJSONObject(0).get("price")));
            Data.add("출시일: " + String.valueOf(jArray.getJSONObject(0).get("release")));
            Data.add("크기: " + String.valueOf(jArray.getJSONObject(0).get("size")));
            Data.add("타입: " + String.valueOf(jArray.getJSONObject(0).get("type")));
            Data.add("연료: " + String.valueOf(jArray.getJSONObject(0).get("oil")));
            Data.add("연비: " + String.valueOf(jArray.getJSONObject(0).get("mileage")));
            Data.add("마력: " + String.valueOf(jArray.getJSONObject(0).get("hp")));

            listView.setAdapter(adapter);
            String id = String.valueOf(jArray.getJSONObject(0).get("carid"));

            if (id.equals("kona"))
                imageView.setImageResource(R.drawable.kona);
            if (id.equals("qm3"))
                imageView.setImageResource(R.drawable.qm3);
            if (id.equals("stonic"))
                imageView.setImageResource(R.drawable.stonic);
            if (id.equals("tivoli"))
                imageView.setImageResource(R.drawable.tivoli);
            if (id.equals("trax"))
                imageView.setImageResource(R.drawable.trax);


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void setSpinnerBrand(String s) {

        try {
            JSONArray jArray = new JSONArray(s);
            String[] str_brand = new String[10];

            for (int i = 0; i < jArray.length(); i++)
                str_brand[i] = String.valueOf(jArray.getJSONObject(i).get("brand"));

            ArrayList<String> list = new ArrayList<>();
            ArrayAdapter<String> adapter = new ArrayAdapter<>(ListActivity.context_list, android.R.layout.simple_spinner_item, list);
            for (int i = 0; i < jArray.length(); i++)
                list.add(str_brand[i]);
            spinner.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void processFinish(String output) throws JSONException {

        JSONObject jObject = new JSONObject(output);
        String code = jObject.getString("code");

        switch (code) {
            case "carid":
                setSpinnerCarId(jObject.getString("data"));
                break;
            case "carinfor":
                setListCarInfor(jObject.getString("data"));
                break;
            case "brand":
                setSpinnerBrand(jObject.getString("data"));
                break;
        }
    }
}
