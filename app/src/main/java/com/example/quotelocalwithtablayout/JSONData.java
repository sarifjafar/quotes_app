package com.example.quotelocalwithtablayout;

import android.util.Log;

import org.json.JSONArray;

public class JSONData {
    public static final String TAG = JSONData.class.getSimpleName();
    private JSONArray jsonArrayLifeQuotes;
    private JSONArray jsonArrayDevQuotes;

    public JSONArray getJsonArrayDevQuotes() {
        return jsonArrayDevQuotes;
    }

    public void setJsonArrayDevQuotes(JSONArray jsonArrayDevQuotes) {
        this.jsonArrayDevQuotes = jsonArrayDevQuotes;
        Log.v(TAG, "setJsonArrayDevQuotes: "+this.jsonArrayDevQuotes);
    }

    public JSONArray getJsonArrayLifeQuotes() {
        return jsonArrayLifeQuotes;
    }

    public void setJsonArrayLifeQuotes(JSONArray jsonArrayLifeQuotes) {
        this.jsonArrayLifeQuotes = jsonArrayLifeQuotes;
        Log.v(TAG, "setJsonArrayDevQuotes: "+this.jsonArrayLifeQuotes);
    }
}
