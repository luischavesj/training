package com.prodigious.training.threads;

import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Luis Chaves on 2/3/2017.
 * to test multi threading
 */
public final class SumOfPowerThread extends Thread{

    private BigDecimal numberToCalculate;
    private int numberOfThreads;
    private static final BigDecimal NUMBER_OF_CHUNKS = new BigDecimal("100");
    private static final BigDecimal EXPONENT = new BigDecimal("2");
    private static Logger logger;
    private BigDecimal result = BigDecimal.ZERO;
    private final Object lock;
    private final int sleepTime;
    private List<PowerCalculatorThread> tasks;

    public SumOfPowerThread(BigDecimal numberToCalculate, int sleepTime){
        this.numberToCalculate = numberToCalculate;
        numberOfThreads = this.numberToCalculate.divide(SumOfPowerThread.NUMBER_OF_CHUNKS,BigDecimal.ROUND_UP).intValue();
        this.lock = new Object();
        this.sleepTime = sleepTime;
        this.tasks = new ArrayList<>(numberOfThreads);
        logger = Logger.getLogger(SumOfPowerThread.class);
        this.start();
    }

    public SumOfPowerThread(BigDecimal numberToCalculate){
        this(numberToCalculate,0);
    }

    @Override
    public void run() {
        calculatePower();
        processResults();
    }

    public BigDecimal getResult() throws InterruptedException {
        synchronized (lock){
            while (result.compareTo(BigDecimal.ZERO) == 0){
                lock.wait();
            }
            return result;
        }
    }

    private void calculatePower(){
        PowerCalculatorThread powerCalculator;
        BigDecimal initialNumber = BigDecimal.ONE;
        BigDecimal finalNumber;
        logger.info("Number of Threads: " + numberOfThreads);

        for (int i = 0; i < numberOfThreads; i++) {
            finalNumber = initialNumber.add(SumOfPowerThread.NUMBER_OF_CHUNKS.subtract(BigDecimal.ONE));
            if (finalNumber.compareTo(numberToCalculate) > 0) {
                finalNumber = numberToCalculate;
            }
            powerCalculator = new PowerCalculatorThread(initialNumber, finalNumber, SumOfPowerThread.EXPONENT,sleepTime);
            initialNumber = finalNumber.add(BigDecimal.ONE);
            tasks.add(powerCalculator);
        }

    }

    private void processResults(){
        BigDecimal intermediateResult = BigDecimal.ZERO;
        try {
            for(PowerCalculatorThread task:tasks){
                intermediateResult = intermediateResult.add(task.getResult());
            }
        } catch (InterruptedException e) {
            logger.error(e.getMessage(), e);
        }
        synchronized (lock) {
            result = intermediateResult;
            lock.notify();
        }
    }
}
