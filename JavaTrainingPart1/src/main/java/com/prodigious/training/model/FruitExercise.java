package com.prodigious.training.model;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Luis Chaves on 1/13/2017.
 * In order to develop an exercise on Week 1 day 3
 */
public final class FruitExercise {

    public List<String> createNonDuplicatedFruitList(List<String> fruits){
        //Using Set will remove duplicates
        Set<String> nonDuplicatedSet =  new LinkedHashSet<String>(fruits);
        return new ArrayList<String>(nonDuplicatedSet);
    }
}
