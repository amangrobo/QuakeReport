package com.grobo.quakereport;

public class Quake {

    private double mMagnitude;
    private String mLocation;
    private long mQuakeTimeMilisecs;


    public Quake(double magnitude, String location, long quakeTimeMilisecs) {
        mMagnitude = magnitude;
        mLocation = location;
        mQuakeTimeMilisecs = quakeTimeMilisecs;
    }

    public double getMagnitude() {return mMagnitude;}

    public String getLocation() {return mLocation;}

    public long getQuakeTimeMilisecs() {return mQuakeTimeMilisecs;}

}
