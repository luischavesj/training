package com.prodigious.training.week1.day4.dao;

import com.prodigious.training.week1.day4.model.Department;
import com.prodigious.training.week1.day4.model.Employee;
import com.prodigious.training.week1.day4.util.ConversionMapper;

import javax.naming.NamingException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Luis Chaves on 1/16/2017
 * for Week 1 day 4 Exercise.
 */
public /*final <REMOVED TO USE MOCKITO>*/ class DepartmentDAO extends BaseDAO {

    private static final String QUERY_DEPARTMENT_LIST = "SELECT DISTINCT Dept_Id, Dept_Name FROM Department";
    private static final String QUERY_EMPLOYEE_LIST_BY_DEPARTMENT_ID = "SELECT e.Emp_Id, e.Emp_Name, e.Salary FROM Employee e JOIN Department d ON e.Emp_Id = d.Emp_Id WHERE d.Dept_Id = ?";
    private static final String DELETE_DEPARTMENT = "DELETE FROM Department WHERE Dept_Id = ?";
    private static final String INSERT_DEPARTMENT = "INSERT INTO Department (Dept_Id, Dept_Name,Emp_Id) Values(?,?,?)";

    private static final int COLUMN_ONE = 1;
    private static final int COLUMN_TWO = 2;
    private static final int COLUMN_THREE = 3;

    public DepartmentDAO() throws NamingException {
    }

    public List<Department> getDepartmentList() throws SQLException {
        List<Department> departments = new ArrayList<>();
        PreparedStatement statementEmployee;
        ResultSet employeesResult = null;

        try (PreparedStatement statement = super.getStatement(DepartmentDAO.QUERY_DEPARTMENT_LIST);
             ResultSet departmentResult = statement.executeQuery()
        ) {
            while (departmentResult.next()) {
                statementEmployee = super.getStatement(DepartmentDAO.QUERY_EMPLOYEE_LIST_BY_DEPARTMENT_ID);
                statementEmployee.setInt(DepartmentDAO.COLUMN_ONE,departmentResult.getInt(DepartmentDAO.COLUMN_ONE));
                employeesResult = statementEmployee.executeQuery();
                departments.add(ConversionMapper.toDepartment(departmentResult,employeesResult));
            }
        }finally {
            if (employeesResult != null) {
                employeesResult.close();
            }
        }
        return departments;
    }

    public void deleteDepartment(Department department)  throws SQLException {
        PreparedStatement statement;
        if (department != null) {
            statement = super.getStatement(DepartmentDAO.DELETE_DEPARTMENT);
            statement.setInt(DepartmentDAO.COLUMN_ONE,department.getDepartmentId());
            super.executeWithoutResultSet(statement);
        }
    }

    public void addDepartment(Department department) throws SQLException {
        PreparedStatement statement;
        if (department != null) {
            for(Employee employee:department.getEmployees()) {
                statement = super.getStatement(DepartmentDAO.INSERT_DEPARTMENT);
                statement.setInt(DepartmentDAO.COLUMN_ONE, department.getDepartmentId());
                statement.setString(DepartmentDAO.COLUMN_TWO, department.getDepartmentName());
                statement.setInt(DepartmentDAO.COLUMN_THREE,employee.getEmployeeId());
                super.executeWithoutResultSet(statement);
            }
        }
    }
}
