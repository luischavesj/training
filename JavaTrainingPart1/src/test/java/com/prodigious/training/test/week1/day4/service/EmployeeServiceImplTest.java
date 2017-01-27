package com.prodigious.training.test.week1.day4.service;

import com.prodigious.training.week1.day4.dao.EmployeeDao;
import com.prodigious.training.week1.day4.dao.EmployeeDaoImpl;
import com.prodigious.training.week1.day4.model.Employee;
import com.prodigious.training.week1.day4.service.EmployeeService;
import com.prodigious.training.week1.day4.service.EmployeeServiceImpl;
import org.junit.*;
import org.mockito.Mockito;

import javax.naming.NamingException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by Luis Chaves on 1/24/2017
 * to test the Employee Service using Mockito.
 */
public class EmployeeServiceImplTest {

    private EmployeeService employeeService;
    private EmployeeDao employeeDAO;
    @Before
    public void createEmployeeService() throws NamingException, SQLException {
        employeeDAO = Mockito.mock(EmployeeDao.class);
        employeeService = new EmployeeServiceImpl(employeeDAO);
    }

    @After
    public void removeEmployeeService() throws SQLException, NamingException {
        employeeService = null;
        employeeDAO = null;
    }

    @Test
    public void createEmployeeTest() throws SQLException {
        Employee employee = new Employee(1,"Luis",new BigDecimal("1000"));
        employeeService.createEmployee(employee);
        verify(employeeDAO,times(1)).addEmployee(employee);
    }

    @Test
    public void removeEmployeeTest() throws SQLException {
        Employee employee = new Employee(1,"Luis",new BigDecimal("1000"));
        employeeService.removeEmployee(employee);
        verify(employeeDAO,times(1)).deleteEmployee(employee);
    }

    @Test
    public void getEmployeesTest() throws SQLException {
        Collection<Employee> employees = employeeService.getEmployees();
        verify(employeeDAO,times(1)).getEmployeeList();
    }

    @Test
    public void increaseEmployeesSalaryTest() throws SQLException {
        Collection<Employee> employees = new ArrayList<>();
        Employee employee = new Employee(1,"Luis",new BigDecimal("1000"));
        employees.add(employee);
        employees.add(employee);
        Employee employeeWithNewSalary = new Employee(1,"Luis", new BigDecimal("1200"));
        employeeService.increaseEmployeesSalary(employees, new BigDecimal("0.2"));
        verify(employeeDAO,times(2)).updateEmployee(employeeWithNewSalary);
    }
}
