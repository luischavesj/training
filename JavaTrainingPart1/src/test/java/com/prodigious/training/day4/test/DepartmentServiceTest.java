package com.prodigious.training.day4.test;

import com.prodigious.training.day4.model.Department;
import com.prodigious.training.day4.model.Employee;
import com.prodigious.training.day4.service.DepartmentService;
import com.prodigious.training.day4.service.EmployeeService;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by Luis Chaves on 1/18/2017 as part of the testing for Day 4 Week 1.
 * This class is intended to test the following things:
 * Create 3 departments and assign to them some of the 5 employees
 * List the departments with the employees (using toString)
 * Remove the departments one by one using the removeDepartment method.
 */
public class DepartmentServiceTest {
    @BeforeClass
    public static void prepareData(){
        //Use this just as a way to retrieve the current employees
        EmployeeService employeeService = new EmployeeService();
        List<Employee> employees = employeeService.getEmployeeList();
        //If database empty then recreate the employees reusing previous test
        if (employees.size() == 0){
            EmployeeServiceTest.prepareData();
            employees = employeeService.getEmployeeList();
        }

        DepartmentService departmentService = new DepartmentService();
        Department department = new Department(1,"Development");
        department.addEmployee(employees.get(0));
        department.addEmployee(employees.get(1));
        departmentService.addDepartment(department);

        department = new Department(2,"Production");
        department.addEmployee(employees.get(2));
        department.addEmployee(employees.get(3));
        departmentService.addDepartment(department);

        department = new Department(3,"Human Resources");
        department.addEmployee(employees.get(4));
        departmentService.addDepartment(department);
    }

    @Test
    public void getDepartmentListTest(){
        DepartmentService departmentService = new DepartmentService();
        List<Department> departmentList = departmentService.getDepartmentList();
        System.out.println(departmentList);

        assert(departmentList.size() == 3);
    }

    @AfterClass
    public static void clearDatabase(){
        DepartmentService departmentService = new DepartmentService();
        List<Department> departmentList = departmentService.getDepartmentList();
        for(Department department: departmentList) {
            departmentService.removeDepartment(department);
        }

        //Make sure no departments are available in DB
        assert (departmentService.getDepartmentList().size() == 0);

        EmployeeServiceTest.clearDatabase();
    }
}
