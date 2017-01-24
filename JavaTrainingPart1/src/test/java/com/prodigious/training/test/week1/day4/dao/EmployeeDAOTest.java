package com.prodigious.training.test.week1.day4.dao;

import com.prodigious.training.week1.day4.dao.EmployeeDAO;
import com.prodigious.training.week1.day4.model.Employee;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.naming.NamingException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Luis Chaves on 1/16/2017
 * for Week 1 day 4 Exercise.
 */

public class EmployeeDAOTest {

    @BeforeClass
    public static void prepareData() throws NamingException, SQLException {
        EmployeeDAO employeeDAO = new EmployeeDAO();
        employeeDAO.addEmployee(new Employee(1,"Luis",new BigDecimal("1000")));
        employeeDAO.addEmployee(new Employee(2,"Bob",new BigDecimal("1500")));
        employeeDAO.addEmployee(new Employee(3,"Robert",new BigDecimal("700")));
        employeeDAO.addEmployee(new Employee(4,"Maria",new BigDecimal("2000")));
        employeeDAO.addEmployee(new Employee(5,"Angela",new BigDecimal("800")));

        assert(employeeDAO.getEmployeeList().size() == 5);
    }
    @Test
    public void getEmployeeListTest() throws NamingException, SQLException {
        EmployeeDAO employeeDAO = new EmployeeDAO();
        Collection<Employee> employees = employeeDAO.getEmployeeList();
        //Just to print the method name
        System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
        System.out.println(employees);
        System.out.println("Total of employees " + employees.size());
        //Based on BeforeClass, there should be 5 items in total
        assert(employees.size() == 5);

        //Test line to see working the @Deprecated annotation.
        System.out.println(((List<Employee>)employees).get(0).printEmployee());
    }

    @Test
    public void increaseEmployeesSalaryTest() throws NamingException, SQLException {

        EmployeeDAO employeeDAO = new EmployeeDAO();
        // This call is just to prepare the data for the assert method
        Collection<Employee> employees = employeeDAO.getEmployeeList();
        Employee updatedEmployee = new Employee(5,"Angela",new BigDecimal("200"));
        employeeDAO.setUpdateEmployee(updatedEmployee);
        //Just to print the method name
        System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
        System.out.println(employeeDAO.getEmployeeList());

        //Using equals and hashCode of Employee class which was implemented to include Name and Salary also.
        assert(employees.size() == employeeDAO.getEmployeeList().size());
        assert (!employees.containsAll(employeeDAO.getEmployeeList()));
    }

    @AfterClass
    public static void clearDatabase() throws NamingException, SQLException {
        EmployeeDAO employeeDAO = new EmployeeDAO();
        Collection<Employee> employees = employeeDAO.getEmployeeList();
        for(Employee employee:employees){
            employeeDAO.deleteEmployee(employee);
        }

        //Make sure all records were deleted
        assert (employeeDAO.getEmployeeList().size() == 0);
    }
}
