package com.team4.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.team4.dao.HYGSDao;
import com.team4.domain.HYGS;
import com.team4.form.HYGSForm;
import com.team4.until.DBUntil;
import com.team4.until.DateUntil;

public class HYGSDaoImpl implements HYGSDao {

	public List<HYGS> select(HYGSForm hygsf){
		int j =1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<HYGS> list = new ArrayList<HYGS>();
		String sqlAdd = hygsf.getSqlAdd();
		String sql = "select * from kh_hygs";
		if(sqlAdd!=null){
			sql = sql + sqlAdd;
		}
		System.out.println(sql);
		try {
			conn = DBUntil.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				HYGS hygs = new HYGS();
				for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
					String name = rs.getMetaData().getColumnName(i);
					String value = rs.getString(i);
					if(name.equals("bedate") || name.equals("stdate")){
						value = DateUntil.longToDate(Long.parseLong(value));
					}
					try {
						BeanUtils.setProperty(hygs, name, value);
					} catch (Exception e) {
						// TODO: handle exception
						throw new RuntimeException(e);
					}
				}
				hygs.setNum(String.valueOf(j++));
				list.add(hygs);
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}finally {
			DBUntil.closeConnection(conn);
			DBUntil.closePreparedStatement(pstmt);
			DBUntil.closeResultSet(rs);
		}
		return list;
		
	}
}
