package com.prodigious.training.week2.day1;

/**
 * Created by Luis Chaves on 1/23/2017
 * to see how to use private constructor.
 */
public enum SolarSystem {

    MERCURY(1000, 5),
    VENUS(2000, 6),
    EARTH(3000,7),
    MARS(3500,8);

    private int mass;
    private int volume;

    public int getMass(){
        return this.mass;
    }

    public int getVolume(){
        return this.volume;
    }

    public double getDensity() {
        if (volume > 0) {
            return this.getMass() / this.getVolume();
        }
        else{
            return 0;
        }
    }
    private SolarSystem(int mass, int volume) {
        this.mass = mass;
        this.volume = volume;
    }

    @Override
    public String toString(){
        return "Name = " + this.name() + " Density = " + getDensity();
    }
}
