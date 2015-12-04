package com.team4.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.team4.dao.HYGS_HYKPDao;
import com.team4.domain.HYGS_HYKP;
import com.team4.until.DBUntil;

public class HYGS_HYKPDaoImpl implements HYGS_HYKPDao {

	public List<HYGS_HYKP> select(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<HYGS_HYKP> list = new ArrayList<HYGS_HYKP>();
		String sql = "select * from kh_hykp where id=  ?";
		try {
			conn = DBUntil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()){
				HYGS_HYKP hygshykp = new HYGS_HYKP();
				for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
					String name = rs.getMetaData().getColumnName(i);
					String value = rs.getString(i);
					try {
						BeanUtils.setProperty(hygshykp, name, value);
					} catch (Exception e) {
						// TODO: handle exception
						throw new RuntimeException(e);
					}
				}
				list.add(hygshykp);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}finally {
			DBUntil.closeConnection(conn);
			DBUntil.closePreparedStatement(pstmt);
			DBUntil.closeResultSet(rs);
		}
		return list;
	}
}
