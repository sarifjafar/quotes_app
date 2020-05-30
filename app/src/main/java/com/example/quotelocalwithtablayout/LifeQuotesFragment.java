package com.example.quotelocalwithtablayout;

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

public class LifeQuotesFragment extends Fragment {

    public static final String TAG = DevQuotesFragment.class.getSimpleName();
    private ImageView bgImageView;
    private TextView mLifeQuoteVale;
    private TextView mLifeAuthorValue;
    private Button mLifeNextQuoteButton;
    private Button mLifePrevQuoteButton;
    private final String LIFE_QUOTES_KEY = "LIFE_QUOTES_KEY";
    private static final String LIFE_BGIMAGE_CODE = "LIFE_BGImage";
    private JSONArray lifeQuotesArray;
    private int length;
    private int lastIndexPopped;
    private Stack<Integer> backStack = new Stack<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.lifequotes_fragment,container,false);

        bgImageView = view.findViewById(R.id.bgImageValue);
        mLifeQuoteVale = view.findViewById(R.id.quoteValue);
        mLifeAuthorValue = view.findViewById(R.id.authorValue);
        mLifeNextQuoteButton = view.findViewById(R.id.nextQuote);
        mLifePrevQuoteButton = view.findViewById(R.id.prevQuote);

        final String jsonData = getArguments().getString(LIFE_QUOTES_KEY);
        final QuoteUtil quoteUtil = new QuoteUtil();

        bgImageView.setImageResource(quoteUtil.getRandomBackGround(LIFE_BGIMAGE_CODE));

        try {
            lifeQuotesArray = new JSONArray(jsonData);
            length = lifeQuotesArray.length();
            int index = quoteUtil.getRandomIndex(length);
            Quote quote = quoteUtil.getQuote(index,lifeQuotesArray);
            mLifeQuoteVale.setText(quoteUtil.getSpannableStyledQuote(quote.getQuote()));
            mLifeAuthorValue.setText(quoteUtil.getSpannableStyledAuthor(quote.getAuthor()));
            backStack.push(index);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d(TAG, "onCreateView: "+TAG+" "+jsonData);

//        try {
//            Quote quote = getQuote(jsonData);
//            mLifeQuoteVale.setText(quote.getQuote());
//            mLifeAuthorValue.setText(quote.getAuthor());
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }


        mLifeNextQuoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (backStack.size() == 0){
                    backStack.push(lastIndexPopped);
                }
                mLifePrevQuoteButton.setEnabled(true);
                try {
                    int index = quoteUtil.getRandomIndex(length);
                    Quote quote = quoteUtil.getQuote(index,lifeQuotesArray);
                    mLifeQuoteVale.setText(quoteUtil.getSpannableStyledQuote(quote.getQuote()));
                    mLifeAuthorValue.setText(quoteUtil.getSpannableStyledAuthor(quote.getAuthor()));
                    backStack.push(index);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        mLifePrevQuoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lastIndexPopped = backStack.pop();
                try {
                    if (backStack.size() > 0) {
                        int index = backStack.peek();
                        Quote quote = quoteUtil.getQuote(index, lifeQuotesArray);
                        //backStack.push(index);
                        mLifeQuoteVale.setText(quoteUtil.getSpannableStyledQuote(quote.getQuote()));
                        mLifeAuthorValue.setText(quoteUtil.getSpannableStyledAuthor(quote.getAuthor()));
                    } else {
                        Toast.makeText(getActivity().getApplicationContext(), "Reached Last Quote.", Toast.LENGTH_SHORT).show();
                        mLifePrevQuoteButton.setEnabled(false);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });


        return view;
    }

}
