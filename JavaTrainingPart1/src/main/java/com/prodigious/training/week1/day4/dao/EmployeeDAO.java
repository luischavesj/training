package com.prodigious.training.week1.day4.dao;

import com.prodigious.training.week1.day4.model.Employee;

import java.sql.SQLException;
import java.util.Collection;

/**
 * Created by Luis Chaves on 1/27/2017
 * to create the contract for the Employee data access.
 */
public interface EmployeeDao {

    Collection<Employee> getEmployeeList() throws SQLException;
    void updateEmployee(Employee employee)throws SQLException;
    void deleteEmployee(Employee employee)throws SQLException;
    void addEmployee(Employee employee)throws SQLException;
}
