package com.prodigious.training.week1.day4.dao;

import com.prodigious.training.week1.day4.model.Employee;
import com.prodigious.training.week1.day4.util.ConversionMapper;

import javax.naming.NamingException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

/**
 * Created by Luis Chaves on 1/16/2017
 * for Week 1 day 4 Exercise.
 */
public final class EmployeeDaoImpl extends AbstractDao implements EmployeeDao{

    private static final String QUERY_EMPLOYEE_LIST = "SELECT Emp_Id, Emp_Name, Salary FROM Employee";
    private static final String UPDATE_EMPLOYEE = "UPDATE Employee SET Emp_Name = ?, Salary = ? WHERE Emp_Id = ?";
    private static final String DELETE_EMPLOYEE = "DELETE FROM Employee WHERE Emp_Id = ?";
    private static final String INSERT_EMPLOYEE = "INSERT INTO Employee (Emp_Id, Emp_Name,Salary) Values(?,?,?)";

    private static final int COLUMN_ONE = 1;
    private static final int COLUMN_TWO = 2;
    private static final int COLUMN_THREE = 3;

    public EmployeeDaoImpl() throws NamingException {
    }

    public Collection<Employee> getEmployeeList() throws SQLException{
         Collection<Employee> employees;

        try (PreparedStatement statement = super.getStatement(EmployeeDaoImpl.QUERY_EMPLOYEE_LIST);
             ResultSet rs = statement.executeQuery()
        ) {
            employees = ConversionMapper.toEmployeeCollection(rs);
        }
        return employees;
    }

    public void updateEmployee(Employee employee)throws SQLException {
        PreparedStatement statement;
        if (employee != null) {
            statement = super.getStatement(EmployeeDaoImpl.UPDATE_EMPLOYEE);
            statement.setInt(EmployeeDaoImpl.COLUMN_THREE,employee.getEmployeeId());
            statement.setBigDecimal(EmployeeDaoImpl.COLUMN_TWO,employee.getEmployeeSalary());
            statement.setString(EmployeeDaoImpl.COLUMN_ONE,employee.getEmployeeName());
            super.executeWithoutResultSet(statement);
        }
    }

    public void deleteEmployee(Employee employee) throws SQLException{
        PreparedStatement statement;
        if (employee != null) {
            statement = super.getStatement(EmployeeDaoImpl.DELETE_EMPLOYEE);
            statement.setInt(EmployeeDaoImpl.COLUMN_ONE,employee.getEmployeeId());
            super.executeWithoutResultSet(statement);
        }
    }

    public void addEmployee(Employee employee) throws SQLException {
        PreparedStatement statement;
        if (employee != null) {
            statement = super.getStatement(EmployeeDaoImpl.INSERT_EMPLOYEE);
            statement.setInt(EmployeeDaoImpl.COLUMN_ONE,employee.getEmployeeId());
            statement.setString(EmployeeDaoImpl.COLUMN_TWO,employee.getEmployeeName());
            statement.setBigDecimal(EmployeeDaoImpl.COLUMN_THREE,employee.getEmployeeSalary());
            super.executeWithoutResultSet(statement);
        }
    }
}
