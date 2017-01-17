package com.prodigious.training.day4.model;

import java.math.BigDecimal;
/**
 * Created by Luis Chaves on 1/16/2017 for Week 1 day 4 Exercise.
 */
public final class Employee {
    private final int employeeId;
    private final String employeeName;
    private final BigDecimal employeeSalary;

    public Employee (int employeeId, String employeeName, BigDecimal employeeSalary){
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.employeeSalary = employeeSalary;
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

    public final String toString(){
        return  "EmpId " + this.getEmployeeId() +
                " EmpName " + this.getEmployeeName() +
                " Salary " + this.getEmployeeSalary();
    }
}
