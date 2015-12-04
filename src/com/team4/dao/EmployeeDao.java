package com.team4.dao;

import java.util.List;

import com.team4.domain.Employee;

public interface EmployeeDao {

	List<Employee> find(String id, String password);

}