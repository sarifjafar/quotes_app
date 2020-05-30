package com.example.quotelocalwithtablayout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import org.json.JSONArray;

public class Pager extends FragmentPagerAdapter {

    private final String DEV_QUOTES = "Developers Quotes";
    private final String LIFE_QUOTES = "Life Quotes";
    private final String DEV_QUOTES_KEY = "DEV_QUOTES_KEY";
    private final String LIFE_QUOTES_KEY = "LIFE_QUOTES_KEY";
    private JSONData mJSONData;

    public Pager(FragmentManager fm,JSONData jsonData) {
        super(fm);
        this.mJSONData = jsonData;
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0 : DevQuotesFragment devQuotesFragment = new DevQuotesFragment();
                     Bundle devQuotesBundle = new Bundle();
                     devQuotesBundle.putString(DEV_QUOTES_KEY,mJSONData.getJsonArrayDevQuotes().toString());
                     devQuotesFragment.setArguments(devQuotesBundle);
                     return devQuotesFragment;

            case 1 : LifeQuotesFragment lifeQuotesFragment = new LifeQuotesFragment();
                     Bundle lifeQuotesBundle = new Bundle();
                     lifeQuotesBundle.putString(LIFE_QUOTES_KEY,mJSONData.getJsonArrayLifeQuotes().toString());
                     lifeQuotesFragment.setArguments(lifeQuotesBundle);
                     return lifeQuotesFragment;

            default: return null;
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0 : return DEV_QUOTES;

            case 1 : return LIFE_QUOTES;

            default: return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
