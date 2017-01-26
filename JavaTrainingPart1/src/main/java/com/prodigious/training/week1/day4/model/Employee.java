package com.prodigious.training.week1.day4.model;

import com.prodigious.training.week2.day2.AuthorAnnotationCustom;

import java.math.BigDecimal;
/**
 * Created by Luis Chaves on 1/16/2017
 * for Week 1 day 4 Exercise.
 */
@AuthorAnnotationCustom.AuthorAnnotation(AuthorName = "Luis Chaves",EmailId = "luis.chaves@prodigious.com")
public final class Employee {
    private static final int DECIMAL_DIGITS = 5;

    private final int employeeId;
    private final String employeeName;
    private final BigDecimal employeeSalary;

    public Employee(int employeeId, String employeeName, BigDecimal employeeSalary){
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.employeeSalary = employeeSalary;
    }
    @AuthorAnnotationCustom.AuthorAnnotation(AuthorName = "Luis Chaves",EmailId = "luis.chaves@prodigious.com")
    public final int getEmployeeId(){
        return employeeId;
    }

    public final String getEmployeeName(){
        return employeeName;
    }

    public final BigDecimal getEmployeeSalary(){
        return employeeSalary;
    }

    @Override
    public final String toString(){
        return  "EmpId " + this.getEmployeeId() +
                " EmpName " + this.getEmployeeName() +
                " Salary " + this.getEmployeeSalary();
    }

    /**
     * use toString instead. Example just to see annotations working.
     */

    @Deprecated
    public final String printEmployee(){
        return this.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }

        Employee that = (Employee) o;

        if (employeeId != that.employeeId){
            return false;
        }
        if (!employeeName.equals(that.employeeName)){
            return false;
        }
        return employeeSalary.setScale(Employee.DECIMAL_DIGITS,BigDecimal.ROUND_CEILING).equals(
                that.employeeSalary.setScale(Employee.DECIMAL_DIGITS,BigDecimal.ROUND_CEILING));
    }

    @Override
    public int hashCode() {
        int result = employeeId;
        result = 31 * result + employeeName.hashCode();
        result = 31 * result + employeeSalary.hashCode();
        return result;
    }
}
