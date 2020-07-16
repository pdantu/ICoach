package com.example.icoach.Models;

public class Data
{
    private double avgSpeed;
    private int heading;
    private int left;
    private int right;
    private double seconds;

    public Data(double seconds, double avgSpeed, int heading, int left, int right) {
        this.seconds = seconds;
        this.avgSpeed = avgSpeed;
        this.heading = heading;
        this.left = left;
        this.right = right;
    }

    public double getAvgSpeed() {
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

    public double getSeconds() {
        return seconds;
    }

    public void setAvgSpeed(double avgSpeed) {
        this.avgSpeed = avgSpeed;
    }

    public void setHeading(int heading) {
        this.heading = heading;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public void setRight(int right) {
        this.right = right;
    }

    public void setSeconds(double seconds) {
        this.seconds = seconds;
    }
}
