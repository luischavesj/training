package com.prodigious.training.day4.model;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

/**
 * Created by Luis Chaves on 1/16/2017
 * for Week 1 day 4 Exercise for the Assignment 1.
 */
public final class Department {

    private final int departmentId;
    private final String departmentName;
    private final Collection<Employee> employees;

    public int getDepartmentId() {
        return departmentId;
    }

    public String getDepartmentName(){
        return departmentName;
    }

    public Collection<Employee> getEmployees(){
        return Collections.unmodifiableCollection(employees);
    }

    public void addEmployee(Employee employee){
        employees.add(employee);
    }

    public Department(int departmentId, String departmentName){
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.employees = new HashSet<>();
    }

    public final String toString(){
        StringBuilder builder = new StringBuilder();

        for(Employee employee: employees){
            builder.append(employee);
        }
        return  "DeptId " + this.getDepartmentId() +
                " DeptName " + this.getDepartmentName() +
                " Employees "  + builder;
    }

    public boolean equals(Object o){
        if(o == null){
            return false;
        }
        if(!(o instanceof Department)){
            return false;
        }
        Department other = (Department) o;
        return this.departmentId == other.getDepartmentId() && this.departmentName.equals(other.getDepartmentName());
    }

    public int hashCode (){
        return this.departmentId * this.departmentName.hashCode();
    }
}
