package com.zgss.grib.gribservice.entity;

public class Aggregate {
    private double sum;
    private double avg;
    private double min;
    private double max;

    public double getAvg() {
        return avg;
    }

    public void setAvg(double avg) {
        this.avg = avg;
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    @Override
    public String toString() {
        return "Aggregate{" +
                "sum=" + sum +
                ", avg=" + avg +
                ", min=" + min +
                ", max=" + max +
                '}';
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }


}
