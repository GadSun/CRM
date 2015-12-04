package com.team4.service;

import java.util.List;

import com.team4.domain.Employee;

public interface EmployeeService {

	List<Employee> login(String id, String password);

}