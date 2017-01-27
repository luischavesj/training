package com.prodigious.training.week1.day4.service;

import com.prodigious.training.week1.day4.model.Department;

import java.util.Collection;

/**
 * Created by luichave2 on 1/27/2017.
 */
public interface DepartmentService {

    void createDepartment(Department department);
    void removeDepartment(Department department);
    Collection<Department> getDepartments();
}
