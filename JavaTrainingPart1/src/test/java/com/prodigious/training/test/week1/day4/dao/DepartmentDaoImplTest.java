package com.prodigious.training.test.week1.day4.dao;

import com.prodigious.training.week1.day4.dao.DepartmentDao;
import com.prodigious.training.week1.day4.dao.DepartmentDaoImpl;
import com.prodigious.training.week1.day4.dao.EmployeeDao;
import com.prodigious.training.week1.day4.dao.EmployeeDaoImpl;
import com.prodigious.training.week1.day4.model.Department;
import com.prodigious.training.week1.day4.model.Employee;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

/**
 * Created by Luis Chaves on 1/18/2017 as part of the testing for Day 4 Week 1.
 * This class is intended to test the following things:
 * Create 3 departments and assign to them some of the 5 employees
 * List the departments with the employees (using toString)
 * Remove the departments one by one using the deleteDepartment method.
 */
public class DepartmentDaoImplTest {
    @BeforeClass
    public static void prepareData() throws NamingException, SQLException {
        //Use this just as a way to retrieve the current employees
        EmployeeDao employeeDAO = new EmployeeDaoImpl();
        Collection<Employee> employees = employeeDAO.getEmployeeList();
        //If database empty then recreate the employees reusing previous test
        if (employees.size() == 0){
            EmployeeDaoImplTest.prepareData();
            employees = employeeDAO.getEmployeeList();
        }

        DepartmentDao departmentDAO = new DepartmentDaoImpl();
        Department department = new Department(1,"Development", ((List<Employee>)employees).subList(0,1));
        departmentDAO.addDepartment(department);

        department = new Department(2,"Production", ((List<Employee>)employees).subList(2,3));
        departmentDAO.addDepartment(department);

        department = new Department(3,"Human Resources", ((List<Employee>)employees).subList(4,5));
        departmentDAO.addDepartment(department);
    }

    @Test
    public void getDepartmentListTest() throws NamingException,SQLException {
        DepartmentDao departmentDAO = new DepartmentDaoImpl();
        Collection<Department> departmentList = departmentDAO.getDepartmentList();
        System.out.println(departmentList);

        assert(departmentList.size() == 3);
    }

    @AfterClass
    public static void clearDatabase() throws NamingException, SQLException {
        DepartmentDao departmentDAO = new DepartmentDaoImpl();
        Collection<Department> departmentList = departmentDAO.getDepartmentList();
        for(Department department: departmentList) {
            departmentDAO.deleteDepartment(department);
        }

        //Make sure no departments are available in DB
        assert (departmentDAO.getDepartmentList().size() == 0);

        EmployeeDaoImplTest.clearDatabase();
    }
}
