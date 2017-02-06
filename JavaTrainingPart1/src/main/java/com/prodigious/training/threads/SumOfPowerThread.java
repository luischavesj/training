package com.prodigious.training.threads;

import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.util.concurrent.ExecutionException;

/**
 * Created by Luis Chaves on 2/3/2017.
 * to test multi threading
 */
public class SumOfPowerThread extends Thread{

    private BigDecimal numberToCalculate;
    private int numberOfThreads;
    private static final BigDecimal NUMBER_OF_CHUNKS = new BigDecimal("100");
    private static final BigDecimal EXPONENT = new BigDecimal("2");
    private static Logger logger;
    private BigDecimal result = BigDecimal.ZERO;
    private final Object lock;

    public SumOfPowerThread(BigDecimal numberToCalculate){
        this.numberToCalculate = numberToCalculate;
        numberOfThreads = this.numberToCalculate.divide(SumOfPowerThread.NUMBER_OF_CHUNKS,BigDecimal.ROUND_UP).intValue();
        this.lock = new Object();
        logger = Logger.getLogger(SumOfPowerThread.class);
        this.start();
    }

    @Override
    public void run() {
        try {
            calculatePower();
        } catch (ExecutionException e) {
            logger.error(e.getMessage(),e);
        } catch (InterruptedException e) {
            logger.error(e.getMessage(),e);
        }
    }

    public BigDecimal getResult() throws InterruptedException {
        synchronized (lock){
            while (result.compareTo(BigDecimal.ZERO) == 0){
                lock.wait();
            }
            return result;
        }
    }

    public void calculatePower() throws ExecutionException, InterruptedException {
        PowerCalculatorThread powerCalculator;
        BigDecimal initialNumber = BigDecimal.ONE;
        BigDecimal finalNumber;
        BigDecimal intermediateResult = BigDecimal.ZERO;
        logger.info("Number of Threads: " + numberOfThreads);

        for (int i = 0; i < numberOfThreads; i++) {
            finalNumber = initialNumber.add(SumOfPowerThread.NUMBER_OF_CHUNKS.subtract(BigDecimal.ONE));

            if(finalNumber.compareTo(numberToCalculate) > 0){
                finalNumber = numberToCalculate;
            }
            powerCalculator = new PowerCalculatorThread(initialNumber,finalNumber, SumOfPowerThread.EXPONENT);
            intermediateResult = intermediateResult.add(powerCalculator.getResult());

            initialNumber = finalNumber.add(BigDecimal.ONE);
        }
        synchronized (lock) {
            result = intermediateResult;
            lock.notify();
        }
    }
}
