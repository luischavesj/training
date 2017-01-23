package com.prodigious.training.test.week2.day1;

import com.prodigious.training.week2.day1.SolarSystem;
import org.junit.Test;

import java.util.EnumSet;

/**
 * Created by Luis Chaves on 1/23/2017
 * to test creation of enum using constructor.
 */
public class SolarSystemTest {
    @Test
    public void solarSystemConstructorTest(){
        for(SolarSystem planet: SolarSystem.values()){
            System.out.println(planet);
        }

        //For testing only 4 planets were added
        assert (SolarSystem.values().length == 4);
    }

    @Test
    public void solarSystemStringComparisonEqual(){
        assert (SolarSystem.MARS.equals(SolarSystem.valueOf(SolarSystem.class,"MARS")));
    }

    @Test
    public void solarSystemStringComparisonDifferent(){
        assert (!SolarSystem.MARS.equals(SolarSystem.valueOf(SolarSystem.class,"EARTH")));
    }

    @Test(expected = java.lang.IllegalArgumentException.class)
    public void SolarSystemStringNotFound(){
        assert (SolarSystem.MARS.equals(SolarSystem.valueOf(SolarSystem.class,"Jupiter")));
    }

    @Test
    public void solarSystemEnumSetTest(){
        EnumSet<SolarSystem> solarSystemEnumSet = EnumSet.range(SolarSystem.MERCURY,SolarSystem.EARTH);

        for (SolarSystem planet: solarSystemEnumSet){
            System.out.println(planet);
        }
        //Only 3 planets
        assert (solarSystemEnumSet.size() == 3);
    }

    @Test
    public void solarSystemEnumSetWithoutMarsTest(){
        EnumSet<SolarSystem> solarSystemEnumSet = EnumSet.range(SolarSystem.MERCURY,SolarSystem.EARTH);
        assert(!solarSystemEnumSet.contains(SolarSystem.MARS));
    }
}
