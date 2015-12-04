package com.team4.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.team4.dao.EmployeeDao;
import com.team4.dao.impl.EmployeeDaoImpl;
import com.team4.domain.Employee;
import com.team4.service.EmployeeService;
import com.team4.until.PasswordUntil;

public class EmployeeServiceImpl implements EmployeeService {

	public List<Employee> login(String id, String password) {
		EmployeeDao dao = new EmployeeDaoImpl();
		// password = ServiceUntil.md5(password);md5加密
		List<Employee> list = new ArrayList<Employee>();
		try {
			password = PasswordUntil.md5(password);
			list = dao.find(id, password);
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
		return list;
	}
}
