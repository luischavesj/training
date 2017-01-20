package com.prodigious.training.week1.day4.model;

import java.math.BigDecimal;
/**
 * Created by Luis Chaves on 1/16/2017
 * for Week 1 day 4 Exercise.
 */
public final class Employee{
    private static final int DECIMAL_DIGITS = 5;
    private final int employeeId;
    private final String employeeName;
    private final BigDecimal employeeSalary;

    public Employee (int employeeId, String employeeName, BigDecimal employeeSalary){
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        if (employeeSalary != null) {
            //Added this validation since in database the DataType stored is a Decimal 19,5
            this.employeeSalary = employeeSalary.setScale(Employee.DECIMAL_DIGITS,BigDecimal.ROUND_CEILING);
        }
        else{
            this.employeeSalary = BigDecimal.ZERO;
        }
    }

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
    public boolean equals(Object o){
        if(o == null){
            return false;
        }
        if(!(o instanceof Employee)){
            return false;
        }
        Employee other = (Employee) o;
        return this.employeeId == other.getEmployeeId() && this.employeeName.equals(other.getEmployeeName()) &&
                this.employeeSalary.equals(other.getEmployeeSalary());
    }
    @Override
    public int hashCode (){
        return this.employeeId * this.employeeName.hashCode() * this.employeeSalary.hashCode();
    }
}
