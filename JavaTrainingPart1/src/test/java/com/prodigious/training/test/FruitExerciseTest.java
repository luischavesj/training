package com.prodigious.training.test;

import com.prodigious.training.model.FruitExercise;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Luis Chaves on 1/13/2017.
 * In order to test an exercise on Week 1 day 3
 */

public class FruitExerciseTest{
    //Purpose of this test, is to demonstrate a way to remove duplicates using an existing data structure in java
    //instead of traditional custom code
    @Test
    public void createNonDuplicatedFruitListTest(){

        List<String> expected = new ArrayList<String>();
        expected.add("Pineapple");
        expected.add("Apple");
        expected.add("Orange");
        expected.add("Grape");

        List<String> fruitList = new ArrayList<String>();
        fruitList.add("Pineapple");
        fruitList.add("Apple");
        fruitList.add("Orange");
        fruitList.add("Grape");
        fruitList.add("Apple");
        fruitList.add("Orange");

        FruitExercise fruitExercise = new FruitExercise();
        fruitList = fruitExercise.createNonDuplicatedFruitList(fruitList);

        System.out.println(fruitList);
        System.out.println(expected);

        assert(expected.equals(fruitList));
    }

}
