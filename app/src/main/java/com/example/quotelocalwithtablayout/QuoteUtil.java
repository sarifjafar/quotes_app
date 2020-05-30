package com.example.quotelocalwithtablayout;

import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.text.style.TypefaceSpan;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

import static android.graphics.Typeface.BOLD;

public class QuoteUtil {

    public Quote getQuote(int index, JSONArray jsonArray) throws JSONException {
        Quote quote = new Quote();
        JSONObject jsonObject = jsonArray.getJSONObject(index);
        quote.setQuote(jsonObject.getString("text"));
        quote.setAuthor(jsonObject.getString("author"));
        return quote;
    }

    public int getRandomIndex(int seed) {
        Random random = new Random();
        return random.nextInt(seed);
    }

    public SpannableString getSpannableStyledQuote(String quote){
        SpannableString string = new SpannableString(quote);

        String[] eachWord = quote.split(" ");
        int numberWordToBubble = getRandomIndex(eachWord.length/2);

        for (int i = 0; i < numberWordToBubble; i++) {
            int wordToBubble = getRandomIndex(eachWord.length);
            int startIndex = 0;
            int endIndex = 0;
            for (int j = 0; j < wordToBubble; j++) {
                startIndex += eachWord[j].length()+1;
            }
            endIndex += (startIndex+eachWord[wordToBubble].length());

            if (endIndex > quote.length()){
                endIndex = quote.length();
            }

            string.setSpan(new RelativeSizeSpan(1.7f), startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            string.setSpan(new ForegroundColorSpan(Color.WHITE),
                    startIndex,
                    endIndex,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

            string.setSpan(
                    new StyleSpan(BOLD),
                    startIndex,
                    endIndex,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        }

        return string;

    }

    public SpannableStringBuilder getSpannableStyledAuthor(String author){
        author = author.length() == 0 ? "Unknown" : author;
        SpannableStringBuilder authorSpan = new SpannableStringBuilder(author);
        authorSpan.setSpan(new ForegroundColorSpan(Color.WHITE),
                0,
                author.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        authorSpan.insert(0,"By - ");
        return authorSpan;
    }

    public int getRandomBackGround(String choice){
        Quote quote = new Quote();
        int resID = 0;
        switch (choice){
            case "DEV_BGImage" : int i = getRandomIndex(quote.getDevBackGroundImageLength());
                                 resID =  quote.getDevBackGroundImage(i);
                                 break;
            case "LIFE_BGImage" : int j = getRandomIndex(quote.getLifeBackGroundImageLength());
                                  resID = quote.getLifeBackGroundImage(j);
        }
        return resID;
    }

}
