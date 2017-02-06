package com.prodigious.training.threads;

import java.math.BigDecimal;

/**
 * Created by Luis Chaves
 * to test the real formula to sum powers on 2/6/2017.
 */
public final class PowerCalculatorUtil {

    public static BigDecimal calculatePowerDirect(BigDecimal n){
        return (n.multiply(n.add(BigDecimal.ONE).multiply(n.multiply(new BigDecimal("2")).add(BigDecimal.ONE))).divide(new BigDecimal("6")));
    }
}
