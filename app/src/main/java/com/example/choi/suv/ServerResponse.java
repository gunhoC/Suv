package com.example.choi.suv;

import org.json.JSONException;
import org.json.JSONObject;

public interface ServerResponse {
    void processFinish(String output) throws JSONException;
}
