package com.ujjwal.newsapp;

public class NewsDetail {
    private String mTitle;
    private String mTime;
    private String mSource;
    private String mDescription;
    private String mImageUrl;

    public NewsDetail(String mTitle, String mTime, String mSource, String mDescription, String mImageUrl) {
        this.mTitle = mTitle;
        this.mTime = mTime;
        this.mSource = mSource;
        this.mDescription = mDescription;
        this.mImageUrl = mImageUrl;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getTime() {
        return mTime;
    }

    public void setTime(String mTime) {
        this.mTime = mTime;
    }

    public String getSource() {
        return mSource;
    }

    public void setSource(String mSource) {
        this.mSource = mSource;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }
}
