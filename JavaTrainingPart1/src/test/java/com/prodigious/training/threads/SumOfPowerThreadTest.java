package com.prodigious.training.threads;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.concurrent.ExecutionException;

/**
 * Created by Luis Chaves on 2/3/2017
 * to test the Thread Pool service.
 */
public class SumOfPowerThreadTest {
    @Test
    public void calculatePowerTest() throws ExecutionException, InterruptedException {
        BigDecimal numberToCalculate = new BigDecimal("1000000");
        BigDecimal result;
        //To track duration
        long initialTime = System.currentTimeMillis();
        SumOfPowerThread thread = new SumOfPowerThread(numberToCalculate);
        result = thread.getResult();
        //checks against formula (n(n + 1)(2n + 1))/6
        assert(result.equals(PowerCalculatorUtil.calculatePowerDirect(numberToCalculate)));
        long finalTime = System.currentTimeMillis();

        System.out.println("Elapsed Time: " + (finalTime - initialTime)/1000 + " Second(s). Number = " + result);
    }
}
