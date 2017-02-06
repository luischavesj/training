package com.prodigious.training.threads;

import org.junit.Test;

import java.math.BigDecimal;

/**
 * Created by Luis Chaves on 2/3/2017
 * to test the Thread Pool service.
 */
public class SumOfPowerServiceTest {
    @Test
    public void calculatePowerTest() {
        BigDecimal numberToCalculate = new BigDecimal("1000000");
        BigDecimal result;
        SumOfPowerService service = new SumOfPowerService(numberToCalculate,5);
        //To track duration
        long initialTime = System.currentTimeMillis();
        result = service.calculatePower();
        //checks against formula (n(n + 1)(2n + 1))/6
        assert(result.equals(PowerCalculatorUtil.calculatePowerDirectly(numberToCalculate)));
        long finalTime = System.currentTimeMillis();
        System.out.println("Elapsed Time: " + (finalTime - initialTime)/1000 + " Second(s). Number = " + result);
    }
}
