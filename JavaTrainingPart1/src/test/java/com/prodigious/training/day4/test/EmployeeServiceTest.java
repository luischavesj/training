package com.prodigious.training.day4.test;

import com.prodigious.training.day4.model.Employee;
import com.prodigious.training.day4.service.EmployeeService;
import org.junit.Test;

import java.util.List;

/**
 * Created by Luis Chaves on 1/16/2017 for Week 1 day 4 Exercise.
 */
public class EmployeeServiceTest {
    @Test
    public void getEmployeeListTest(){
        EmployeeService service = new EmployeeService();
        List<Employee> employees = service.getEmployeeList();
        System.out.println(employees);
        System.out.println("Total of employees " + employees.size());

    }
}
