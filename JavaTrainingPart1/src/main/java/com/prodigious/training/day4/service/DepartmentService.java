package com.prodigious.training.day4.service;

import com.prodigious.training.day4.model.Department;
import com.prodigious.training.day4.model.Employee;
import com.prodigious.training.day4.util.ConversionMapper;

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
public final class DepartmentService extends DatabaseService {

    private static final String QUERY_DEPARTMENT_LIST = "SELECT DISTINCT Dept_Id, Dept_Name FROM Department";
    private static final String QUERY_EMPLOYEE_LIST_BY_DEPARTMENT_ID = "SELECT e.Emp_Id, e.Emp_Name, e.Salary FROM Employee e JOIN Department d ON e.Emp_Id = d.Emp_Id WHERE d.Dept_Id = ?";
    private static final String DELETE_DEPARTMENT = "DELETE FROM Department WHERE Dept_Id = ?";
    private static final String INSERT_DEPARTMENT = "INSERT INTO Department (Dept_Id, Dept_Name,Emp_Id) Values(?,?,?)";

    public DepartmentService() {
        super();
    }

    public List<Department> getDepartmentList() {
        List<Department> departments = new ArrayList<>();
        PreparedStatement statementEmployee = null;
        ResultSet employeesResult = null;

        try (PreparedStatement statement = super.getStatement(DepartmentService.QUERY_DEPARTMENT_LIST);
             ResultSet departmentResult = statement.executeQuery()
        ) {
            while (departmentResult.next()) {
                departments.add(ConversionMapper.toDepartment(departmentResult));
            }

            //Now set the list of employees by department
            for(Department department:departments){
                try {
                    statementEmployee = super.getStatement(DepartmentService.QUERY_EMPLOYEE_LIST_BY_DEPARTMENT_ID);
                    statementEmployee.setInt(1,department.getDepartmentId());
                    employeesResult = statementEmployee.executeQuery();
                    while (employeesResult.next()){
                        department.addEmployee(ConversionMapper.toEmployee(employeesResult));
                    }
                }finally {
                    if(statementEmployee != null){
                        statementEmployee.close();
                    }
                    if (employeesResult != null){
                        employeesResult.close();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return departments;
    }

    public void removeDepartment(Department department) {
        PreparedStatement statement;
        if (department != null) {
            try {
                statement = super.getStatement(DepartmentService.DELETE_DEPARTMENT);
                statement.setInt(1,department.getDepartmentId());
                super.executeWithoutResultSet(statement);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void addDepartment(Department department) {
        PreparedStatement statement;
        if (department != null) {
            try {
                for(Employee employee:department.getEmployees()) {
                    statement = super.getStatement(DepartmentService.INSERT_DEPARTMENT);
                    statement.setInt(1, department.getDepartmentId());
                    statement.setString(2, department.getDepartmentName());
                    statement.setInt(3,employee.getEmployeeId());
                    super.executeWithoutResultSet(statement);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
