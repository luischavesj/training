package com.prodigious.training.swing.TableModelExample;

/**
 * Created by Luis Chaves on 2/2/2017
 * to test the Table Model.
 */
public class Person {
    public final static int COLUMNS = 4;

    private String firstName;
    private String lastName;
    private int age;
    private Boolean isVegetarian;

    public Person(String firstName, String lastName, int age, Boolean isVegetarian){
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.isVegetarian = isVegetarian;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Boolean getVegetarian() {
        return isVegetarian;
    }

    public void setVegetarian(Boolean vegetarian) {
        isVegetarian = vegetarian;
    }

    public Object getValueAt(int index){
        switch (index) {
            case 0:
                return this.getFirstName();
            case 1:
                return this.getLastName();
            case 2:
                return new Integer (this.getAge());
            case 3:
                return isVegetarian;
        }
        return null;
    }
}
