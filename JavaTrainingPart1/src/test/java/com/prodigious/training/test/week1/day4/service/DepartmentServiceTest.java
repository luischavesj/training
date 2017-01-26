package com.prodigious.training.test.week1.day4.service;

import com.prodigious.training.week1.day4.dao.DepartmentDAO;
import com.prodigious.training.week1.day4.model.Department;
import com.prodigious.training.week1.day4.service.DepartmentService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by luichave2 on 1/25/2017
 * to test the Department Service using Mockito.
 */
public class DepartmentServiceTest {

    private DepartmentService departmentService;
    private DepartmentDAO departmentDAO;

    @Before
    public void createDepartmentService() throws NamingException, SQLException {
        departmentDAO = Mockito.mock(DepartmentDAO.class);
        departmentService = new DepartmentService(departmentDAO);
    }

    @After
    public void removeDepartmentService() throws SQLException, NamingException {
        departmentService = null;
        departmentDAO = null;
    }

    @Test
    public void createDepartmentTest() throws SQLException {
        Department department = new Department(1,"Development",new ArrayList<>());
        departmentService.createDepartment(department);
        verify(departmentDAO,times(1)).addDepartment(department);
    }

    @Test
    public void removeDepartmentTest() throws SQLException {
        Department department = new Department(1,"Development",new ArrayList<>());
        departmentService.removeDepartment(department);
        verify(departmentDAO,times(1)).deleteDepartment(department);
    }

    @Test
    public void getDepartmentsTest() throws SQLException {
        departmentService.getDepartments();
        verify(departmentDAO,times(1)).getDepartmentList();
    }
}
