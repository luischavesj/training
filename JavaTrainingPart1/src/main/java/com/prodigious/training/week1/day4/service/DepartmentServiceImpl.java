package com.prodigious.training.week1.day4.service;

import com.prodigious.training.week1.day4.dao.DepartmentDao;
import com.prodigious.training.week1.day4.dao.DepartmentDaoImpl;
import com.prodigious.training.week1.day4.model.Department;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Luis Chaves on 1/24/2017
 * to test services with DAO and Models.
 */
public final class DepartmentServiceImpl implements DepartmentService {

    private static final Logger logger = LogManager.getLogger(DepartmentServiceImpl.class);
    private DepartmentDao departmentDAO;

    public DepartmentServiceImpl(){
        try {
            departmentDAO = new DepartmentDaoImpl();
        } catch (NamingException e) {
            logger.error(e.getMessage(),e);
        }
    }

    public DepartmentServiceImpl(DepartmentDao departmentDAO){
        this.departmentDAO = departmentDAO;
    }

    public void createDepartment(Department department){
        try {
            departmentDAO.addDepartment(department);
        } catch (SQLException e) {
            logger.error(e.getMessage(),e);
        }
    }

    public void removeDepartment(Department department){
        try {
            departmentDAO.deleteDepartment(department);
        } catch (SQLException e) {
            logger.error(e.getMessage(),e);
        }
    }

    public Collection<Department> getDepartments(){
        Collection<Department> departments;
        try {
            departments = departmentDAO.getDepartmentList();
        } catch (SQLException e) {
            departments = new ArrayList<>();
            logger.error(e.getMessage(),e);
        }

        return departments;
    }
}