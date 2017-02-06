package com.prodigious.training.threads;

import java.math.BigDecimal;

/**
 * Created by Luis Chaves on 2/6/2017
 * to test Thread concepts.
 */
public final class PowerCalculatorThread extends Thread {

    private BigDecimal initialBase;
    private BigDecimal finalBase;
    private BigDecimal exponent;
    private BigDecimal result;
    private int sleepTime;
    private final Object lock;

    public PowerCalculatorThread(BigDecimal initialBase, BigDecimal finalBase, BigDecimal exponent, int sleepTimeInSeconds){
        this.initialBase = initialBase;
        this.finalBase = finalBase;
        this.exponent = exponent;
        this.result = BigDecimal.ZERO;
        this.sleepTime = sleepTimeInSeconds * 1000;
        this.lock = new Object();
        this.start();
    }

    @Override
    public void run(){
        try {
            calculatePower();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void calculatePower() throws InterruptedException {

        BigDecimal intermediateResult = BigDecimal.ZERO;
        for (int i = initialBase.intValue(); i <= finalBase.intValue(); i++) {
            intermediateResult = intermediateResult.add(new BigDecimal(Math.pow(i, exponent.doubleValue())));
        }
         //this is just to test how effective is the total calculatePower
        if (sleepTime != 0) {
            this.sleep(sleepTime);
        }
        synchronized (lock) {
            result = intermediateResult;
            lock.notify();
        }
    }

    public BigDecimal getResult() throws InterruptedException {
        synchronized (lock) {
            while (result.compareTo(BigDecimal.ZERO) == 0) {
                lock.wait();
            }
        }
        return result;
    }
}
