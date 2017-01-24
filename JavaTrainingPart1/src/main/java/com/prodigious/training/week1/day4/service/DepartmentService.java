package com.prodigious.training.week1.day4.service;

import com.prodigious.training.week1.day4.dao.DepartmentDAO;
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
public class DepartmentService {

    private static final Logger logger = LogManager.getLogger(DepartmentService.class);
    private DepartmentDAO departmentDAO;

    public DepartmentService(){
        try {
            departmentDAO = new DepartmentDAO();
        } catch (NamingException e) {
            logger.error(e.getMessage(),e);
        }
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