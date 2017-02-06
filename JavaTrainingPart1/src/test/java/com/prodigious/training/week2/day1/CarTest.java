package com.prodigious.training.week2.day1;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Created by Luis Chaves on 1/19/2017
 * to test the abstract inner class implementation.
 */
public class CarTest {

    private final ByteArrayOutputStream out = new ByteArrayOutputStream();

    @Before
    public void setOutput() {
        System.setOut(new PrintStream(out));
    }

    @After
    public void cleanOutput(){
        //System.setOut(null);
    }
    @Test
    public void startTest(){
        Car hybrid = new Car() {
            private int batteryPower = 3000;

            @Override
            public void start() {
                this.batteryPower -= 100;
                System.out.println(this.batteryPower);
            }
        };
        hybrid.start();
        System.out.println(out.toString());
        assert (out.toString().contains("2900"));
    }
}
