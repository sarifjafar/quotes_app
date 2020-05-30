package com.example.quotelocalwithtablayout;

import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;
import java.util.Stack;

public class DevQuotesFragment extends Fragment {

    public static final String TAG = DevQuotesFragment.class.getSimpleName();
    private ImageView bgImageView;
    private TextView mDevQuoteVale;
    private TextView mDevAuthorValue;
    private Button mDevNextQuoteButton;
    private Button mDevPrevQuoteButton;
    private onQuotesParsedListener listener;
    private int length;
    private JSONArray devQuotesArray;
    private Stack<Integer> backStack = new Stack<Integer>();
    private int lastIndexPoped;

    private final String DEV_QUOTES_KEY = "DEV_QUOTES_KEY";
    private final String LIFE_QUOTES_KEY = "LIFE_QUOTES_KEY";
    public static final String DEV_BGIMAGE_CODE = "DEV_BGImage";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.devquotes_fragment,container,false);

//        MainActivity mainActivity = new MainActivity();
//        mainActivity.setListener(listener);
        bgImageView = view.findViewById(R.id.bgImageValue);
        mDevQuoteVale = view.findViewById(R.id.quoteValue);
        mDevAuthorValue = view.findViewById(R.id.authorValue);
        mDevNextQuoteButton = view.findViewById(R.id.nextQuote);
        mDevPrevQuoteButton = view.findViewById(R.id.prevQuote);


        final String jsonData = getArguments().getString(DEV_QUOTES_KEY);

        final QuoteUtil quoteUtil = new QuoteUtil();

        bgImageView.setImageResource(quoteUtil.getRandomBackGround(DEV_BGIMAGE_CODE));

        Log.d(TAG, "onCreateView: "+TAG+" "+jsonData);

        try {
            devQuotesArray = new JSONArray(jsonData);
            length = devQuotesArray.length();
            int index = quoteUtil.getRandomIndex(length);
            Quote quote = quoteUtil.getQuote(index,devQuotesArray);
            mDevQuoteVale.setText(quoteUtil.getSpannableStyledQuote(quote.getQuote()));
            mDevAuthorValue.setText(quoteUtil.getSpannableStyledAuthor(quote.getAuthor()));
            backStack.push(index);
        } catch (JSONException e) {
            e.printStackTrace();
        }

//        mDevQuoteVale.setText("DEV QUOTE");
//
//        new onQuotesParsedListener() {
//            @Override
//            public void onDevQuotesParsed(JSONArray devQuotesJSONArray) {
//                Log.d(TAG, "onDevQuotesParsed: "+devQuotesJSONArray);
//            }
//
//            @Override
//            public void onLifeQuotesParsed(JSONArray lifeQuotesJSONArray) {
//                Log.d(TAG, "onLifeQuotesParsed: "+lifeQuotesJSONArray);
//            }
//        };

        mDevNextQuoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (backStack.size() == 0){
                        backStack.push(lastIndexPoped);
                    }
                    mDevPrevQuoteButton.setEnabled(true);
                    int index = quoteUtil.getRandomIndex(length);
                    Quote quote = quoteUtil.getQuote(index,devQuotesArray);
                    mDevQuoteVale.setText(quoteUtil.getSpannableStyledQuote(quote.getQuote()));
                    mDevAuthorValue.setText(quoteUtil.getSpannableStyledAuthor(quote.getAuthor()));
                    backStack.push(index);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        mDevPrevQuoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    lastIndexPoped = backStack.pop();
                    if (backStack.size() > 0) {
                        int index = backStack.peek();
                        Quote quote = quoteUtil.getQuote(index, devQuotesArray);
                        mDevQuoteVale.setText(quoteUtil.getSpannableStyledQuote(quote.getQuote()));
                        mDevAuthorValue.setText(quoteUtil.getSpannableStyledAuthor(quote.getAuthor()));
                    }else {
                        Toast.makeText(getActivity().getApplicationContext(), "Reached Last Quote.", Toast.LENGTH_SHORT).show();
                        mDevPrevQuoteButton.setEnabled(false);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });


        return view;
    }

//    private Quote displayQuote() {
//        QuoteProccesor quoteProccesor = new QuoteProccesor();
//        JSONData jsonData = new JSONData();
//        Quote quote = new Quote();
//        try {
//            quote = quoteProccesor.getQuote(getRandomIndex(jsonData.getJsonArrayDevQuotes().length())
//                    ,jsonData.getJsonArrayDevQuotes());
//        } catch (JSONException e) {
//            Log.e(TAG, "onCreateView: "+ TAG, e);
//        }
//
//        return quote;
//    }
//
    private Quote getQuote(String jsonData) throws JSONException {
        Quote quote = new Quote();
        JSONArray jsonArray = new JSONArray(jsonData);
        int index = getRandomIndex(jsonArray.length());
        JSONObject jsonObject = jsonArray.getJSONObject(index);
        quote.setQuote(jsonObject.getString("text"));
        quote.setAuthor(jsonObject.getString("author"));

        //backStack.push(index);

        return quote;
    }

    private int getRandomIndex(int seed) {
        Random random = new Random();
        return random.nextInt(seed);
    }
}
