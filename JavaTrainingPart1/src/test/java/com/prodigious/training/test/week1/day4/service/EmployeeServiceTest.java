package com.prodigious.training.test.week1.day4.service;

import com.prodigious.training.test.week1.day4.dao.EmployeeDAOTest;
import com.prodigious.training.week1.day4.dao.EmployeeDAO;
import com.prodigious.training.week1.day4.model.Employee;
import com.prodigious.training.week1.day4.service.EmployeeService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.naming.NamingException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Luis Chaves on 1/24/2017
 * to test the Employee Service.
 */
public class EmployeeServiceTest {

    @Before
    public void createEmployees() throws NamingException, SQLException {
        EmployeeDAOTest.prepareData();
    }

    @After
    public void removeEmployees() throws SQLException, NamingException {
        EmployeeDAOTest.clearDatabase();
    }

    @Test
    public void increaseEmployeesSalaryTest(){

        EmployeeService employeeService = new EmployeeService();
        // This call is just to prepare the data for the assert method
        Collection<Employee> employees = employeeService.getEmployees();
        Collection<Employee> expected = new ArrayList<>();
        BigDecimal newSalary;
        employeeService.increaseEmployeesSalary(employees,new BigDecimal("0.2"));
        //Just to print the method name
        System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
        System.out.println(employeeService.getEmployees());

        //To build the expected list using other mechanism to truly validate the method.
        for(Employee employee: employees){
            newSalary = employee.getEmployeeSalary().add(employee.getEmployeeSalary().multiply(new BigDecimal("0.2")));
            expected.add(new Employee(employee.getEmployeeId(),employee.getEmployeeName(),newSalary));
        }

        //Using equals and hashCode of Employee class which was implemented to include Name and Salary also.
        assert (expected.containsAll(employeeService.getEmployees()));
    }
}
