package com.grobo.quakereport;

public class Quake {

    private double mMagnitude;
    private String mLocation;
    private long mQuakeTimeMilisecs;
    private String mUrl;


    public Quake(double magnitude, String location, long quakeTimeMilisecs, String url) {
        mMagnitude = magnitude;
        mLocation = location;
        mQuakeTimeMilisecs = quakeTimeMilisecs;
        mUrl = url;
    }

    public double getMagnitude() {return mMagnitude;}

    public String getLocation() {return mLocation;}

    public long getQuakeTimeMilisecs() {return mQuakeTimeMilisecs;}

    public String getUrl() {return mUrl;}

}
