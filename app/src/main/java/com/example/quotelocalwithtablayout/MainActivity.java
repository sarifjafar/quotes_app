package com.example.quotelocalwithtablayout;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.pdf.PdfDocument;
import android.os.AsyncTask;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity{

    private static final String TAG = MainActivity.class.getSimpleName();
    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

//        Pager adapter = new Pager(getSupportFragmentManager());
//
//        viewPager.setAdapter(adapter);
//
//        tabLayout.setupWithViewPager(viewPager);
//
//        QuoteProccesor quoteProccesor = new QuoteProccesor(getApplicationContext(),MainActivity.this);
//        quoteProccesor.initQuotesParsingFromFile();

        AsyncTaskRunner runner = new AsyncTaskRunner();
        runner.execute();


    }

    // Async Task Start

    private class AsyncTaskRunner extends AsyncTask<Void,Void,JSONData>{

        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(MainActivity.this, null,
                    "Preparing Freshly Baked Quote For You!!");
        }

        @Override
        protected JSONData doInBackground(Void... voids) {
            QuoteProccesor quoteProccesor = new QuoteProccesor(MainActivity.this);
            JSONData jsonData = quoteProccesor.initQuotesParsingFromFile();
            return jsonData;
        }

        @Override
        protected void onPostExecute(JSONData jsonData) {

//            tabLayout = findViewById(R.id.tabLayout);
//            viewPager = findViewById(R.id.viewPager);

            Pager adapter = new Pager(getSupportFragmentManager(),jsonData);

            viewPager.setAdapter(adapter);

            tabLayout.setupWithViewPager(viewPager);
            progressDialog.dismiss();
        }
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    // Async Task END
}
