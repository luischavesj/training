package com.prodigious.training.week1.day4.util;


import com.prodigious.training.week1.day4.model.Department;
import com.prodigious.training.week1.day4.model.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Luis Chaves on 1/17/2017 for Week 1 day 4
 * Exercise to deal with Employee and Department conversions from ResultSet.
 */
public final class ConversionMapper {

    public ConversionMapper(){
    }

    public static Employee toEmployee(ResultSet employeeResult) throws SQLException {
        return new Employee(employeeResult.getInt(1),employeeResult.getString(2),employeeResult.getBigDecimal(3));
    }

    public static Department toDepartment(ResultSet departmentResult) throws SQLException {
        return new Department(departmentResult.getInt(1),departmentResult.getString(2));
    }
}
