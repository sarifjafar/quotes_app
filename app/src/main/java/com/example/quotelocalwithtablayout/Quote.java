package com.example.quotelocalwithtablayout;

public class Quote {

    private String Quote;
    private String Author;

    private int lifeBackGroundImage[] = {R.drawable.first,R.drawable.second,R.drawable.third};
    private int devBackGroundImage[] = {R.drawable.dev_first,R.drawable.dev_second,R.drawable.dev_third,
            R.drawable.dev_fourth,R.drawable.dev_fifth,R.drawable.dev_sixth};

    public int getLifeBackGroundImageLength() {
        return lifeBackGroundImage.length;
    }

    public int getLifeBackGroundImage(int index){
        return lifeBackGroundImage[index];
    }

    public int getDevBackGroundImageLength() {
        return devBackGroundImage.length;
    }

    public int getDevBackGroundImage(int index){
        return devBackGroundImage[index];
    }


    public String getQuote() {
        return Quote;
    }

    public void setQuote(String quote) {
        Quote = quote;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }
}
