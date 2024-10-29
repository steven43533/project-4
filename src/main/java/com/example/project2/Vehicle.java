package com.example.project2;

import java.io.Serializable;

public class Vehicle implements Serializable {
    private String makeModel;
    private int year;
    private int retailPrice;
    private int vehicleId;

    public Vehicle() {
        this.makeModel = "";
        this.year = 0;
        this.retailPrice = 0;
        this.vehicleId = 0;
    }
    public Vehicle(String makeModel, int year, int retailPrice, int vehicleId) {
        this.makeModel = makeModel;
        this.year = year;
        this.retailPrice = retailPrice;
        this.vehicleId = vehicleId;
    }

    public String getMakeModel() {
        return makeModel;
    }
    public void setMakeModel(String makeModel) {
        this.makeModel = makeModel;
    }
    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public int getRetailPrice() {
        return retailPrice;
    }
    public void setRetailPrice(int retailPrice) {
        this.retailPrice = retailPrice;
    }
    public int getVehicleId() {
        return vehicleId;
    }
    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

}
