package com.prodigious.training.week1.day4.service;

import com.prodigious.training.week1.day4.dao.DepartmentDao;
import com.prodigious.training.week1.day4.model.Department;
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
public class DepartmentServiceImplTest {

    private DepartmentService departmentService;
    private DepartmentDao departmentDao;

    @Before
    public void createDepartmentService() throws NamingException, SQLException {
        departmentDao = Mockito.mock(DepartmentDao.class);
        departmentService = new DepartmentServiceImpl(departmentDao);
    }

    @After
    public void removeDepartmentService(){
        departmentService = null;
        departmentDao = null;
    }

    @Test
    public void createDepartmentTest() throws SQLException {
        Department department = new Department(1,"Development",new ArrayList<>());
        departmentService.createDepartment(department);
        verify(departmentDao,times(1)).addDepartment(department);
    }

    @Test
    public void removeDepartmentTest() throws SQLException {
        Department department = new Department(1,"Development",new ArrayList<>());
        departmentService.removeDepartment(department);
        verify(departmentDao,times(1)).deleteDepartment(department);
    }

    @Test
    public void getDepartmentsTest() throws SQLException {
        departmentService.getDepartments();
        verify(departmentDao,times(1)).getDepartmentList();
    }
}
