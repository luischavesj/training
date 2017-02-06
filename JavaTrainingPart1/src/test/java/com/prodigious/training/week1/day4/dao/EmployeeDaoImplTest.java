package com.prodigious.training.week1.day4.dao;

import com.prodigious.training.week1.day4.model.Employee;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.naming.NamingException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

/**
 * Created by Luis Chaves on 1/16/2017
 * for Week 1 day 4 Exercise.
 */

public class EmployeeDaoImplTest {

    @BeforeClass
    public static void prepareData() throws NamingException, SQLException {
        EmployeeDao employeeDao = new EmployeeDaoImpl();
        employeeDao.addEmployee(new Employee(1,"Luis",new BigDecimal("1000")));
        employeeDao.addEmployee(new Employee(2,"Bob",new BigDecimal("1500")));
        employeeDao.addEmployee(new Employee(3,"Robert",new BigDecimal("700")));
        employeeDao.addEmployee(new Employee(4,"Maria",new BigDecimal("2000")));
        employeeDao.addEmployee(new Employee(5,"Angela",new BigDecimal("800")));

        assert(employeeDao.getEmployeeList().size() == 5);
    }
    @Test
    public void getEmployeeListTest() throws NamingException, SQLException {
        EmployeeDao employeeDao = new EmployeeDaoImpl();
        Collection<Employee> employees = employeeDao.getEmployeeList();
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

        EmployeeDao employeeDao = new EmployeeDaoImpl();
        // This call is just to prepare the data for the assert method
        Collection<Employee> employees = employeeDao.getEmployeeList();
        Employee updatedEmployee = new Employee(5,"Angela",new BigDecimal("200"));
        employeeDao.updateEmployee(updatedEmployee);
        //Just to print the method name
        System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
        System.out.println(employeeDao.getEmployeeList());

        //Using equals and hashCode of Employee class which was implemented to include Name and Salary also.
        assert(employees.size() == employeeDao.getEmployeeList().size());
        assert (!employees.containsAll(employeeDao.getEmployeeList()));
    }

    @AfterClass
    public static void clearDatabase() throws NamingException, SQLException {
        EmployeeDao employeeDao = new EmployeeDaoImpl();
        Collection<Employee> employees = employeeDao.getEmployeeList();
        for(Employee employee:employees){
            employeeDao.deleteEmployee(employee);
        }

        //Make sure all records were deleted
        assert (employeeDao.getEmployeeList().size() == 0);
    }
}
