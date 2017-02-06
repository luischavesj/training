package com.prodigious.training.week1.day3;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Luis Chaves on 1/13/2017.
 * In order to test an exercise on Week 1 day 3
 */

public class FruitExerciseTest{
    //Purpose of this test, is to demonstrate a way to remove duplicates using an existing data structure in java
    //instead of traditional custom code
    @Test
    public void createNonDuplicatedFruitListTest(){

        Collection<String> expected = new ArrayList<>();
        expected.add("Pineapple");
        expected.add("Apple");
        expected.add("Orange");
        expected.add("Grape");

        Collection<String> fruitList = new ArrayList<>();
        fruitList.add("Pineapple");
        fruitList.add("Apple");
        fruitList.add("Orange");
        fruitList.add("Grape");
        fruitList.add("Apple");
        fruitList.add("Orange");

        FruitExercise fruitExercise = new FruitExercise();
        fruitList = fruitExercise.createNonDuplicatedFruitList(fruitList);

        //Just to print the method name
        System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
        System.out.println(fruitList);
        System.out.println(expected);
        System.out.println();
        assert(expected.toString().equals(fruitList.toString()));
    }

    @Test
    public void createNonDuplicatedFruitListTestWithNoDuplicates(){

        Collection<String> expected = new ArrayList<>();
        expected.add("Pineapple");
        expected.add("Apple");
        expected.add("Orange");
        expected.add("Grape");

        Collection<String> fruitList = new ArrayList<>();
        fruitList.add("Pineapple");
        fruitList.add("Apple");
        fruitList.add("Orange");
        fruitList.add("Grape");

        FruitExercise fruitExercise = new FruitExercise();
        fruitList = fruitExercise.createNonDuplicatedFruitList(fruitList);

        //Just to print the method name
        System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());

        System.out.println(fruitList);
        System.out.println(expected);
        System.out.println();

        assert(expected.toString().equals(fruitList.toString()));
    }

    @Test
    public void createNonDuplicatedFruitListTestWithEmptyList(){

        Collection<String> expected = new ArrayList<>();
        expected.add("Pineapple");
        expected.add("Apple");
        expected.add("Orange");
        expected.add("Grape");

        Collection<String> fruitList = new ArrayList<>();

        FruitExercise fruitExercise = new FruitExercise();
        fruitList = fruitExercise.createNonDuplicatedFruitList(fruitList);

        //Just to print the method name
        System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
        System.out.println(fruitList);
        System.out.println(expected);
        System.out.println();

        assert(!expected.toString().equals(fruitList.toString()));
    }

}
