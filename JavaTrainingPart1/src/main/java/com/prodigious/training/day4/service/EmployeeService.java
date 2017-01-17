package com.prodigious.training.day4.service;

import com.prodigious.training.day4.model.Employee;
import com.prodigious.training.day4.util.EmployeeMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Luis Chaves on 1/16/2017 for Week 1 day 4 Exercise.
 */
public final class EmployeeService extends DatabaseService {

    private static final String SQL_EMPLOYEE_LIST = "SELECT Emp_Id, Emp_Name, Salary FROM Employee";
    public EmployeeService(){
        super();
    }

    public List<Employee> getEmployeeList() {
        List<Employee> employees = new ArrayList<>();

        try (Connection connection = super.getConnection();
             PreparedStatement statement = connection.prepareStatement(EmployeeService.SQL_EMPLOYEE_LIST);
             ResultSet rs = statement.executeQuery()
            ){
            while (rs.next()){
                employees.add(EmployeeMapper.toEmployee(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }
}
