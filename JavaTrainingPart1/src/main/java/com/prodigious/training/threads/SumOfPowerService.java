package com.prodigious.training.threads;

import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by Luis Chaves on 2/3/2017.
 * to test multi threading
 */
public class SumOfPowerService {

    private ExecutorService service;
    private List<Future> tasks;
    private BigDecimal numberToCalculate;
    private int numberOfThreads;
    private int sleepTime;
    private static final BigDecimal NUMBER_OF_CHUNKS = new BigDecimal("100");
    private static final BigDecimal EXPONENT = new BigDecimal("2");
    private static Logger logger;

    public SumOfPowerService(BigDecimal numberToCalculate, int sleepTime){
        this.numberToCalculate = numberToCalculate;
        this.sleepTime = sleepTime;
        numberOfThreads = this.numberToCalculate.divide(SumOfPowerService.NUMBER_OF_CHUNKS,BigDecimal.ROUND_UP).intValue();
        service = Executors.newFixedThreadPool(numberOfThreads);
        tasks = new ArrayList<>();
        logger = Logger.getLogger(SumOfPowerService.class);
    }

    public SumOfPowerService(BigDecimal numberToCalculate){
        this(numberToCalculate,0);
    }

    public BigDecimal calculatePower() throws ExecutionException, InterruptedException {

        Callable powerCalculator;
        BigDecimal initialNumber = BigDecimal.ONE;
        BigDecimal finalNumber;
        logger.info("Number of Threads: " + numberOfThreads);

        for (int i = 0; i < numberOfThreads; i++) {
            finalNumber = initialNumber.add(SumOfPowerService.NUMBER_OF_CHUNKS.subtract(BigDecimal.ONE));

            if(finalNumber.compareTo(numberToCalculate) > 0){
                finalNumber = numberToCalculate;
            }
            powerCalculator = new PowerCalculator(initialNumber,finalNumber,SumOfPowerService.EXPONENT,sleepTime);
            tasks.add(service.submit(powerCalculator));
            initialNumber = finalNumber.add(BigDecimal.ONE);
        }

        BigDecimal result = BigDecimal.ZERO;
        for(Future f:tasks){
            result = result.add((BigDecimal) f.get());
        }
        service.shutdown();
        return result;
    }
}
