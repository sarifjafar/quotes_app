package com.example.quotelocalwithtablayout;

import org.json.JSONArray;

public interface onQuotesParsedListener {

    void onDevQuotesParsed(JSONArray devQuotesJSONArray);

    void onLifeQuotesParsed(JSONArray lifeQuotesJSONArray);
}
