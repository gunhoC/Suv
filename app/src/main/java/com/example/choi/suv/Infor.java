package com.example.choi.suv;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class Infor extends AppCompatActivity implements ServerResponse {

    private EditText edit_brand;
    private EditText edit_carid;
    private EditText edit_price;
    private EditText edit_release;
    private EditText edit_size;
    private EditText edit_type;
    private EditText edit_oil;
    private EditText edit_mileage;
    private EditText edit_hp;
    private Infor del;
    private String brand = null;
    private String carid = null;
    private String price = null;
    private String release = null;
    private String size = null;
    private String type = null;
    private String oil = null;
    private String mileage = null;
    private String hp = null;
    private Button re;
    private Button ok;
 private Button bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.information);

        edit_brand = findViewById(R.id.edit_brand);
        edit_carid = findViewById(R.id.edit_carid);
        edit_price = findViewById(R.id.edit_price);
        edit_release = findViewById(R.id.edit_release);
        edit_size = findViewById(R.id.edit_size);
        edit_type = findViewById(R.id.edit_type);
        edit_oil = findViewById(R.id.edit_oil);
        edit_mileage = findViewById(R.id.edit_mileage);
        edit_hp = findViewById(R.id.edit_hp);
        del = this;
        re = findViewById(R.id.re);
        ok = findViewById(R.id.ok);
        bt=findViewById(R.id.my_btn);
        edit_brand.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                brand = s.toString();
            }
        });
        edit_carid.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                carid = s.toString();
            }
        });

        edit_price.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                price = s.toString();
            }
        });

        edit_release.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                release = s.toString();
            }
        });

        edit_size.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                size = s.toString();
            }
        });

        edit_type.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                type = s.toString();
            }
        });

        edit_oil.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                oil = s.toString();
            }
        });

        edit_mileage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mileage = s.toString();
            }
        });
        edit_hp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                hp = s.toString();


            }
        });


        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                parameter = new HashMap<>();
                parameter.put("brand", brand);
                parameter.put("carid", carid);
                parameter.put("price", price);
                parameter.put("release", release);
                parameter.put("size", size);
                parameter.put("type", type);
                parameter.put("oil", oil);
                parameter.put("mileage", mileage);
                parameter.put("hp", hp);

                if (brand != null && carid != null && price != null && release != null && size != null && type != null && oil != null && mileage != null && hp != null) {

                    new Server().onDb("http://www.udmcps.com:14101/user_in", null, del);
                    if (a == null) {
                        new Server().onDb("http://www.udmcps.com:14101/user", parameter, del);
                        Toast.makeText(getApplicationContext(), "등록이 완료 되었습니다", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(getApplicationContext(), "이미 정보가 입력 되어있습니다.", Toast.LENGTH_SHORT).show();

                } else
                    Toast.makeText(getApplicationContext(), "입력하지 않은 정보가 있습니다", Toast.LENGTH_SHORT).show();

            }
        });


        re.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Server().onDb("http://www.udmcps.com:14101/user_d", null, del);
                Toast.makeText(getApplicationContext(), "삭제가 완료 되었습니다", Toast.LENGTH_SHORT).show();
            }
        });

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Infor.this,Mycom.class);
                startActivity(intent);
            }
        });


    }

    String a = null;
    HashMap<String, String> parameter;

    @Override
    public void processFinish(String output) throws JSONException {
        JSONObject jsonObject = new JSONObject(output);
        String code = jsonObject.getString("code");

        switch (code) {
            case "user_in":
                JSONArray jsonArray = new JSONArray(jsonObject.getString("data"));
                a = String.valueOf(jsonArray.getJSONObject(0).get("brand"));





                break;

        }


    }
}

