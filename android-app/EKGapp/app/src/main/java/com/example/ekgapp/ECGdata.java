package com.example.ekgapp;

public class ECGdata {

    double a;
    double b;

    public void ECGdata(double x, double y){
        a=x;
        b=y;
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }



    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }


}
