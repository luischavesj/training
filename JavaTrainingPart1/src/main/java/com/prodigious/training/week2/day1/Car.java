package com.prodigious.training.week2.day1;

/**
 * Created by Luis Chaves on 1/19/2017.
 * To test the Anonymous Inner classes
 */
public abstract class Car {
    private String manufacturer;
    public abstract void start();

    public void setManufacturer(String maker){
        this.manufacturer = maker;
    }

    public String getManufacturer() {
        return manufacturer;
    }
}