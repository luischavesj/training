package com.prodigious.training.week1.day4.service;

import com.prodigious.training.week1.day4.model.Employee;

import java.math.BigDecimal;
import java.util.Collection;

/**
 * Created by luichave2 on 1/27/2017.
 */
public interface EmployeeService {
    void createEmployee(Employee employee);
    void removeEmployee(Employee employee);
    void increaseEmployeesSalary(Collection<Employee> employees, BigDecimal newSalary);
    Collection<Employee> getEmployees();

}
