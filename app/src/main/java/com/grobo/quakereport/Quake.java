package com.grobo.quakereport;

public class Quake {

    private String mMagnitude;
    private String mLocation;
    private String mQuakeDate;


    public Quake(String magnitude, String location, String quakeDate) {
        mMagnitude = magnitude;
        mLocation = location;
        mQuakeDate = quakeDate;
    }

    public String getMagnitude() {return mMagnitude;}

    public String getLocation() {return mLocation;}

    public String getQuakeDate() {return mQuakeDate;}

}
