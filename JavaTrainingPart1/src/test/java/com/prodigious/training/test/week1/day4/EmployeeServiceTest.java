package com.prodigious.training.test.week1.day4;

import com.prodigious.training.week1.day4.model.Employee;
import com.prodigious.training.week1.day4.service.EmployeeService;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Luis Chaves on 1/16/2017
 * for Week 1 day 4 Exercise.
 */

public class EmployeeServiceTest {

    @BeforeClass
    public static void prepareData(){
        EmployeeService service = new EmployeeService();
        service.addEmployee(new Employee(1,"Luis",BigDecimal.valueOf(1000)));
        service.addEmployee(new Employee(2,"Bob",BigDecimal.valueOf(1500)));
        service.addEmployee(new Employee(3,"Robert",BigDecimal.valueOf(700)));
        service.addEmployee(new Employee(4,"Maria",BigDecimal.valueOf(2000)));
        service.addEmployee(new Employee(5,"Angela",BigDecimal.valueOf(800)));

        assert(service.getEmployeeList().size() == 5);
    }
    @Test
    public void getEmployeeListTest(){
        EmployeeService service = new EmployeeService();
        List<Employee> employees = service.getEmployeeList();
        //Just to print the method name
        System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
        System.out.println(employees);
        System.out.println("Total of employees " + employees.size());
        //Based on BeforeClass, there should be 5 items in total
        assert(employees.size() == 5);

        //Test line to see working the @Deprecated annotation.
        System.out.println(employees.get(0).printEmployee());

    }

    @Test
    public void increaseEmployeesSalaryTest(){

        EmployeeService service = new EmployeeService();
        // This call is just to prepare the data for the assert method
        List<Employee> employees = service.getEmployeeList();
        List<Employee> expected = new ArrayList<>(employees.size());
        BigDecimal newSalary;
        service.increaseEmployeesSalary(0.2);
        //Just to print the method name
        System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
        System.out.println(service.getEmployeeList());

        //To build the expected list using other mechanism to truly validate the method.
        for(Employee employee: employees){
            newSalary = employee.getEmployeeSalary().add(employee.getEmployeeSalary().multiply(BigDecimal.valueOf(0.2)));
            expected.add(new Employee(employee.getEmployeeId(),employee.getEmployeeName(),newSalary));
        }
        //Using equals and hashCode of Employee class which was implemented to include Name and Salary also.
        assert (expected.containsAll(service.getEmployeeList()));
    }

    @AfterClass
    public static void clearDatabase(){
        EmployeeService service = new EmployeeService();
        List<Employee> employees = service.getEmployeeList();
        for(Employee employee:employees){
            service.removeEmployee(employee);
        }

        //Make sure all records were deleted
        assert (service.getEmployeeList().size() == 0);
    }
}
