package com.prodigious.training.week1.day3;

import java.util.*;

/**
 * Created by Luis Chaves on 1/13/2017.
 * In order to develop an exercise on Week 1 day 3
 */
public final class FruitExercise {

    public Collection<String> createNonDuplicatedFruitList(Collection<String> fruits){
        //Using Set will remove duplicates
        Set<String> nonDuplicatedSet =  new LinkedHashSet<>();

        if (fruits != null && fruits.size() > 0){
            nonDuplicatedSet.addAll(fruits);
        }
        return nonDuplicatedSet;
    }
}
