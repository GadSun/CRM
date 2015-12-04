package com.team4.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.team4.dao.KLDao;
import com.team4.domain.Employee;
import com.team4.domain.KL;
import com.team4.form.KLForm;
import com.team4.until.DBUntil;

public class KLDaoImpl implements KLDao {
	public List<KL> select(KLForm klf) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<KL> list = new ArrayList<KL>();
		String sqlAdd = klf.getSqlAdd();
		String sql = "select * from kh_kl";
		int j = 1;
		if (sqlAdd != null) {
			sql = sql + sqlAdd;
		}
		System.out.println(sql);
		try {
			conn = DBUntil.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				KL kl = new KL();
				for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
					String name = rs.getMetaData().getColumnName(i);
					String value = rs.getString(i);
					try {
						BeanUtils.setProperty(kl, name, value);
					} catch (Exception e) {
						// TODO: handle exception
						throw new RuntimeException(e);
					}
				}
				kl.setNum(String.valueOf(j++));
				list.add(kl);
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
