package com.prodigious.training.week1.day4.util;


import com.prodigious.training.week1.day4.model.Department;
import com.prodigious.training.week1.day4.model.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Luis Chaves on 1/17/2017 for Week 1 day 4
 * Exercise to deal with Employee and Department conversions from ResultSet.
 */
public final class ConversionMapper {

    private static final int COLUMN_ONE = 1;
    private static final int COLUMN_TWO = 2;
    private static final int COLUMN_THREE = 3;

    public ConversionMapper(){
    }

    public static Collection<Employee> toEmployeeCollection(ResultSet employeeResult) throws SQLException {
        Collection<Employee> employees = new ArrayList<>();
        while (employeeResult.next()){
            employees.add(new Employee(employeeResult.getInt(ConversionMapper.COLUMN_ONE),
                    employeeResult.getString(ConversionMapper.COLUMN_TWO),
                    employeeResult.getBigDecimal(ConversionMapper.COLUMN_THREE)));
        }
        return employees;
    }

    public static Department toDepartment(ResultSet departmentResult, ResultSet employeeResult) throws SQLException {
        return new Department(departmentResult.getInt(ConversionMapper.COLUMN_ONE),
                departmentResult.getString(ConversionMapper.COLUMN_TWO),
                toEmployeeCollection(employeeResult));
    }
}
