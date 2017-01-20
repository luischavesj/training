package com.prodigious.training.week1.day4.service;

import com.prodigious.training.week1.day4.model.Employee;
import com.prodigious.training.week1.day4.util.ConversionMapper;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Luis Chaves on 1/16/2017
 * for Week 1 day 4 Exercise.
 */
public final class EmployeeService extends DatabaseService {

    private static final String QUERY_EMPLOYEE_LIST = "SELECT Emp_Id, Emp_Name, Salary FROM Employee";
    private static final String UPDATE_EMPLOYEE_SALARY = "UPDATE Employee SET Salary = ? WHERE Emp_Id = ?";
    private static final String DELETE_EMPLOYEE = "DELETE FROM Employee WHERE Emp_Id = ?";
    private static final String INSERT_EMPLOYEE = "INSERT INTO Employee (Emp_Id, Emp_Name,Salary) Values(?,?,?)";

    public EmployeeService() {
        super();
    }

    public List<Employee> getEmployeeList() {
        List<Employee> employees = new ArrayList<>();

        try (PreparedStatement statement = super.getStatement(EmployeeService.QUERY_EMPLOYEE_LIST);
             ResultSet rs = statement.executeQuery()
        ) {
            while (rs.next()) {
                employees.add(ConversionMapper.toEmployee(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    private Employee getEmployeeWithSalaryIncremented(Employee employee, double incrementedPercentage) {
        BigDecimal newSalary = employee.getEmployeeSalary().multiply(BigDecimal.valueOf(incrementedPercentage));
        newSalary = newSalary.add(employee.getEmployeeSalary());
        return new Employee(employee.getEmployeeId(), employee.getEmployeeName(), newSalary);
    }

    public void increaseEmployeesSalary(double increasedPercentage) {
        List<Employee> employees = this.getEmployeeList();
        Employee employeeWithNewSalary;
        PreparedStatement statement;
        if (employees != null && employees.size() > 0) {
            try {
                for (Employee employee : employees) {
                    employeeWithNewSalary = this.getEmployeeWithSalaryIncremented(employee, increasedPercentage);
                    statement = super.getStatement(EmployeeService.UPDATE_EMPLOYEE_SALARY);
                    statement.setInt(2,employeeWithNewSalary.getEmployeeId());
                    statement.setBigDecimal(1,employeeWithNewSalary.getEmployeeSalary());
                    super.executeWithoutResultSet(statement);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void removeEmployee(Employee employee) {
        PreparedStatement statement;
        if (employee != null) {
            try {
                statement = super.getStatement(EmployeeService.DELETE_EMPLOYEE);
                statement.setInt(1,employee.getEmployeeId());
                super.executeWithoutResultSet(statement);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void addEmployee(Employee employee) {
        PreparedStatement statement;
        if (employee != null) {
            try {
                statement = super.getStatement(EmployeeService.INSERT_EMPLOYEE);
                statement.setInt(1,employee.getEmployeeId());
                statement.setString(2,employee.getEmployeeName());
                statement.setBigDecimal(3,employee.getEmployeeSalary());
                super.executeWithoutResultSet(statement);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
