package com.example.quotelocalwithtablayout;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

public class QuoteProccesor {

    public static final String TAG = QuoteProccesor.class.getSimpleName();
    private final Context mContext;
    private onQuotesParsedListener listener;


    public QuoteProccesor(Context mContext) {
        this.mContext = mContext;
    }

    public QuoteProccesor(Context mContext, onQuotesParsedListener onQuotesParsedListener) {
        this.mContext = mContext;
        this.listener = onQuotesParsedListener;
    }

    public JSONData initQuotesParsingFromFile() {
        String DEV_QUOTES = "devQuotes.json";
        String LIFE_QUOTES = "lifeQuotes.json";

        JSONData jsonData = new JSONData();
        try {
            JSONArray jsonArray = getJSONArrayFromFile(DEV_QUOTES);
            jsonData.setJsonArrayDevQuotes(jsonArray);

            //listener.onDevQuotesParsed(jsonArray);
            Log.v(TAG, "onCreate: DEV_QUOTES Parsed "+jsonArray.length());
            Log.v(TAG, "initQuotesParsingFromFile: "+jsonArray);
            JSONArray jsonArray1 = getJSONArrayFromFile(LIFE_QUOTES);
            jsonData.setJsonArrayLifeQuotes(jsonArray1);
            //listener.onLifeQuotesParsed(jsonArray1);
            Log.v(TAG, "onCreate: LIFE_QUOTES Parsed");
            Log.v(TAG, "initQuotesParsingFromFile: "+jsonArray1);
        }catch (IOException e){
            Log.e(TAG, "onCreate: IOException", e);
        }catch (JSONException e){
            Log.e(TAG, "onCreate: JSONException", e);
        }
        return jsonData;
    }

    private JSONArray getJSONArrayFromFile(String fileName) throws IOException, JSONException {
        String jsonString = null;
        InputStream inputStream = mContext.getAssets().open(fileName);
        int size = inputStream.available();
        byte buffer[] = new byte[size];
        inputStream.read(buffer);
        inputStream.close();
        jsonString = new String(buffer,"UTF-8");
        JSONArray jsonArray = new JSONArray(jsonString);
        return jsonArray;
    }



}
