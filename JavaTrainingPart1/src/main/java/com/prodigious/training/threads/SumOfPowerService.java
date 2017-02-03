package com.prodigious.training.threads;

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
    private static final BigDecimal NUMBER_OF_CHUNKS = new BigDecimal("100");
    private static final BigDecimal EXPONENT = new BigDecimal("2");

    public SumOfPowerService(BigDecimal numberToCalculate){
        this.numberToCalculate = numberToCalculate;
        numberOfThreads = this.numberToCalculate.divide(SumOfPowerService.NUMBER_OF_CHUNKS,BigDecimal.ROUND_UP).intValue();
        service = Executors.newFixedThreadPool(numberOfThreads);
        tasks = new ArrayList<>();
    }

    private BigDecimal min(BigDecimal a, BigDecimal b){
        if(a.compareTo(b) < 0){
            return a;
        }
        else{
            return b;
        }
    }

    public BigDecimal calculatePowerDirect(BigDecimal n){
        return (n.multiply(n.add(BigDecimal.ONE).multiply(n.multiply(new BigDecimal("2")).add(BigDecimal.ONE))).divide(new BigDecimal("6")));
    }

    public BigDecimal calculatePower() throws ExecutionException, InterruptedException {

        Callable powerCalculator;
        BigDecimal initialNumber = BigDecimal.ONE;
        BigDecimal finalNumber;

        for (int i = 0; i < numberOfThreads; i++) {
            finalNumber = min(initialNumber.add(SumOfPowerService.NUMBER_OF_CHUNKS),numberToCalculate);
            powerCalculator = new PowerCalculator(initialNumber,finalNumber,SumOfPowerService.EXPONENT);
            tasks.add(service.submit(powerCalculator));
            initialNumber = finalNumber;
        }

        BigDecimal result = BigDecimal.ZERO;
        for(Future f:tasks){
            result = result.add((BigDecimal) f.get());
        }
        service.shutdown();
        return result;
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        BigDecimal numberToCalculate = new BigDecimal("1000000");
        BigDecimal result;
        SumOfPowerService service = new SumOfPowerService(numberToCalculate);

        //To track duration
        long initialTime = System.currentTimeMillis();

        result = service.calculatePower();

        //checks against formula (n(n + 1)(2n + 1))/6
        assert(result.equals(service.calculatePowerDirect(numberToCalculate)));

        long finalTime = System.currentTimeMillis();

        System.out.println("Elapsed Time: " + (finalTime - initialTime)/1000 + " Second(s). Number = " + result);
    }
}
