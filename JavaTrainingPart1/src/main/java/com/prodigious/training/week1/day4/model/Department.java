package com.prodigious.training.week1.day4.model;

import java.util.Collection;
import java.util.Collections;

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

    public Department(int departmentId, String departmentName, Collection<Employee> employees){
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.employees = Collections.unmodifiableCollection(employees);
    }

    @Override
    public final String toString(){
        StringBuilder builder = new StringBuilder();

        for(Employee employee: employees){
            builder.append(employee);
        }
        return  "DeptId " + this.getDepartmentId() +
                " DeptName " + this.getDepartmentName() +
                " Employees "  + builder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }

        Department that = (Department) o;

        return (departmentId != that.departmentId)&& departmentName.equals(that.departmentName);
    }

    @Override
    public int hashCode() {
        return 31 * departmentId + departmentName.hashCode();
    }
}
