package com.patrickleonard.nonestdeus.atheismquotes.palletWheel;

public class ColorPalette {

    private int mTitleTextColorID;
    private int mBarBackgroundColorID;
    private int mQuoteTextColorID;
    private int mQuoteBackgroundColorID;
    private int mListItemBackgroundColorID;

    public int getTitleTextColor() {
        return mTitleTextColorID;
    }

    public void setTitleTextColor(int mTitleTextColor) {
        this.mTitleTextColorID = mTitleTextColor;
    }

    public int getBarBackgroundColorID() {
        return mBarBackgroundColorID;
    }

    public void setBarBackgroundColor(int mBarBackgroundColor) {
        this.mBarBackgroundColorID = mBarBackgroundColor;
    }

    public int getQuoteTextColor() {
        return mQuoteTextColorID;
    }

    public void setQuoteTextColor(int mQuoteTextColor) {
        this.mQuoteTextColorID = mQuoteTextColor;
    }

    public int getQuoteBackgroundColor() {
        return mQuoteBackgroundColorID;
    }

    public void setQuoteBackgroundColor(int mQuoteBackgroundColor) {
        this.mQuoteBackgroundColorID = mQuoteBackgroundColor;
    }

    public int getListItemBackgroundColor() {
        return mListItemBackgroundColorID;
    }

    public void setListItemBackgroundColor(int mListItemBackgroundColor) {
        this.mListItemBackgroundColorID = mListItemBackgroundColor;
    }

    public ColorPalette(int mTitleTextColor, int mBarBackgroundColor, int mQuoteTextColor, int mQuoteBackgroundColor, int mListItemBackgroundColor) {
        this.mTitleTextColorID = mTitleTextColor;
        this.mBarBackgroundColorID = mBarBackgroundColor;
        this.mQuoteTextColorID = mQuoteTextColor;
        this.mQuoteBackgroundColorID = mQuoteBackgroundColor;
        this.mListItemBackgroundColorID = mListItemBackgroundColor;
    }
}
