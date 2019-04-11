package com.advanced.ocr.calculation;

public class scanArrayList {

    private double Area;
    private double Length;
    private double Width;
    private int SerialNumber;

    public scanArrayList() {
        this.Area = Area;
        this.Length = Length;
        this.Width = Width;
        this.SerialNumber = SerialNumber;
    }

    public int getSerialNumber() {
        return SerialNumber;
    }

    public void setSerialNumber(int iSerialNumber) {
        this.SerialNumber = iSerialNumber;
    }

    public double getWidth() {
        return Width;
    }

    public void setWidth(double dWidth) {
        this.Width = dWidth;
    }

    public double getLength() {
        return Length;
    }

    public void setLength(double dLength) {
        this.Length = dLength;
    }

    public double getArea() {
        return Area;
    }

    public void setArea(double dArea) { this.Area = dArea; }
}
