package com.example.icoach;

public class Data
{
    private int avgSpeed;
    private int heading;
    private int left;
    private int right;

    public Data(int avgSpeed, int heading, int left, int right) {
        this.avgSpeed = avgSpeed;
        this.heading = heading;
        this.left = left;
        this.right = right;
    }

    public int getAvgSpeed() {
        return avgSpeed;
    }

    public int getHeading() {
        return heading;
    }

    public int getLeft() {
        return left;
    }

    public int getRight() {
        return right;
    }
}
