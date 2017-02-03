package com.prodigious.training.threads;

import java.math.BigDecimal;
import java.util.concurrent.Callable;

/**
 * Created by Luis Chaves on 2/3/2017.
 * to test the Sum Of Powers using Multi Threading
 */
public class PowerCalculator implements Callable{

    private BigDecimal initialBase;
    private BigDecimal finalBase;
    private BigDecimal exponent;
    private BigDecimal result;
    private int sleepTime;

    public PowerCalculator(BigDecimal initialBase, BigDecimal finalBase, BigDecimal exponent, int sleepTimeInSeconds){
        this.initialBase = initialBase;
        this.finalBase = finalBase;
        this.exponent = exponent;
        this.sleepTime = sleepTimeInSeconds * 1000;
    }

    public PowerCalculator(BigDecimal initialBase, BigDecimal finalBase, BigDecimal exponent){
        this(initialBase, finalBase,exponent,0);
    }

    public void calculatePower() throws InterruptedException {
        result = BigDecimal.ZERO;
        for (int i = initialBase.intValue(); i <= finalBase.intValue(); i++) {
            result = result.add(new BigDecimal(Math.pow(i, exponent.doubleValue())));
        }
        //this is just to test how effective is the total calculatePower
        new Thread().sleep(sleepTime);
    }

    public BigDecimal getResult(){
        return result;
    }

    @Override
    public Object call() throws Exception {
        calculatePower();
        return getResult();
    }
}
