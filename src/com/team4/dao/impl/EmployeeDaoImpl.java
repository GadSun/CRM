package com.team4.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.team4.dao.EmployeeDao;
import com.team4.domain.Employee;
import com.team4.until.DBUntil;

public class EmployeeDaoImpl implements EmployeeDao {

	public List<Employee> find(String id, String password) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Employee> list = new ArrayList<Employee>();
		String sql = "select * from employee where id=  ? and password = ?";
		try {
			conn = DBUntil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Employee emp = new Employee();
				for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
					String name = rs.getMetaData().getColumnName(i);
					String value = rs.getString(i);
					try {
						BeanUtils.setProperty(emp, name, value);
					} catch (Exception e) {
						// TODO: handle exception
						throw new RuntimeException(e);
					}
				}
				list.add(emp);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			DBUntil.closeConnection(conn);
			DBUntil.closePreparedStatement(pstmt);
			DBUntil.closeResultSet(rs);
		}
		return list;
	}
}
