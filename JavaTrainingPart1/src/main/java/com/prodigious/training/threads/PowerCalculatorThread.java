package com.prodigious.training.threads;

import java.math.BigDecimal;

/**
 * Created by Luis Chaves on 2/6/2017
 * to test Thread concepts.
 */
public class PowerCalculatorThread extends Thread {

    private BigDecimal initialBase;
    private BigDecimal finalBase;
    private BigDecimal exponent;
    private BigDecimal result;
    private final Object lock;

    public PowerCalculatorThread(BigDecimal initialBase, BigDecimal finalBase, BigDecimal exponent){
        this.initialBase = initialBase;
        this.finalBase = finalBase;
        this.exponent = exponent;
        this.result = BigDecimal.ZERO;
        this.lock = new Object();
        this.start();
    }

    @Override
    public void run() {
        calculatePower();
    }

    public void calculatePower() {

        BigDecimal intermediateResult = BigDecimal.ZERO;
        for (int i = initialBase.intValue(); i <= finalBase.intValue(); i++) {
            intermediateResult = intermediateResult.add(new BigDecimal(Math.pow(i, exponent.doubleValue())));
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
