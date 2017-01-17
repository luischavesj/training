package com.prodigious.training.day4.util;


import com.prodigious.training.day4.model.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Luis Chaves on 1/17/2017 for Week 1 day 4 Exercise.
 */
public final class EmployeeMapper {

    public EmployeeMapper(){
    }

    public static Employee toEmployee(ResultSet employeeResult) throws SQLException {
        return new Employee(employeeResult.getInt(1),employeeResult.getString(2),employeeResult.getBigDecimal(3));
    }
}
