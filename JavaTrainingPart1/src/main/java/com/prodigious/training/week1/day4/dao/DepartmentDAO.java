package com.prodigious.training.week1.day4.dao;

import com.prodigious.training.week1.day4.model.Department;

import java.sql.SQLException;
import java.util.Collection;

/**
 * Created by Luis Chaves on 1/27/2017.
 * to create the contract for the Department data access.
 */
public interface DepartmentDao {
    Collection<Department> getDepartmentList() throws SQLException;
    void deleteDepartment(Department department)throws SQLException;
    void addDepartment(Department department)throws SQLException;
}
