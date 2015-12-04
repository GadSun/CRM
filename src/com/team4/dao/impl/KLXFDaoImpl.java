package com.team4.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.management.RuntimeErrorException;

import org.apache.commons.beanutils.BeanUtils;

import com.team4.dao.KLXFDao;
import com.team4.domain.KLXF;
import com.team4.form.KLXFForm;
import com.team4.until.DBUntil;

public class KLXFDaoImpl implements KLXFDao {

	public List<KLXF> select(KLXFForm klxff) {

		int j =1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<KLXF> list = new ArrayList<KLXF>();
		String sqlAdd = null;
		sqlAdd = klxff.getSqlAdd();
		String sql = "select * from kh_kl";
		if(sqlAdd!=null){
			sql = sql + sqlAdd;
		}
		System.out.println(sql);
		try {
			conn = DBUntil.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				KLXF klxf = new KLXF();
				for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
					String name = rs.getMetaData().getColumnName(i);
					String value = rs.getString(i);
					try {
						BeanUtils.setProperty(klxf, name, value);
					} catch (Exception e) {
						// TODO: handle exception
						throw new RuntimeException(e);
					}
				}
				klxf.setNum(String.valueOf(j++));
				list.add(klxf);
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		} finally {
			DBUntil.closeConnection(conn);
			DBUntil.closePreparedStatement(pstmt);
			DBUntil.closeResultSet(rs);
		}
		return list;
	}
}
