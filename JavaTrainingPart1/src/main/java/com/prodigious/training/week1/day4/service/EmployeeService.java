package com.prodigious.training.week1.day4.service;

import com.prodigious.training.week1.day4.dao.EmployeeDAO;
import com.prodigious.training.week1.day4.model.Employee;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.naming.NamingException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Luis Chaves on 1/24/2017
 * to test services with DAO and Models.
 */
public class EmployeeService {
    private static final Logger logger = LogManager.getLogger(DepartmentService.class);
    private EmployeeDAO employeeDAO;

    public EmployeeService(){
        try {
            employeeDAO = new EmployeeDAO();
        } catch (NamingException e) {
            logger.error(e.getMessage(),e);
        }
    }

    public void createEmployee(Employee employee){
        try {
            employeeDAO.addEmployee(employee);
        } catch (SQLException e) {
            logger.error(e.getMessage(),e);
        }
    }

    public void removeEmployee(Employee employee){
        try {
            employeeDAO.deleteEmployee(employee);
        } catch (SQLException e) {
            logger.error(e.getMessage(),e);
        }
    }

    public Collection<Employee> getEmployees(){
        Collection<Employee> employees;
        try {
            employees = employeeDAO.getEmployeeList();
        } catch (SQLException e) {
            employees = new ArrayList<>();
            logger.error(e.getMessage(),e);
        }
        return employees;
    }

    private Employee getEmployeeWithSalaryIncremented(Employee employee, BigDecimal incrementedPercentage) {
        BigDecimal newSalary = employee.getEmployeeSalary().multiply(incrementedPercentage);
        newSalary = newSalary.add(employee.getEmployeeSalary());
        return new Employee(employee.getEmployeeId(), employee.getEmployeeName(), newSalary);
    }

    public void increaseEmployeesSalary(Collection<Employee> employees, BigDecimal increasedPercentage){
        Employee employeeWithNewSalary;
        try {
            if (employees != null && employees.size() > 0) {
                for (Employee employee : employees) {
                    employeeWithNewSalary = this.getEmployeeWithSalaryIncremented(employee, increasedPercentage);
                    employeeDAO.setUpdateEmployee(employeeWithNewSalary);
                }
            }
        }catch (SQLException e) {
            logger.error(e.getMessage(),e);
        }
    }
}
