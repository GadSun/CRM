package com.team4.until;

import org.apache.commons.beanutils.BeanUtils;

public class SqlUtils {

	// 生成选中消费合计时的sql语句
	public static <T> String sqlForTotal_Cost(T form,String check) {
		String sqlAdd = null;
		String strl = null;
		String strr = null;
		try {
			strl = BeanUtils.getProperty(form, "l_total_cost");
			strr = BeanUtils.getProperty(form, "r_total_cost");
			if (strl == null || strl.equals("")) {
				strl = "0";
			}
			if (strr == null || strr.equals("")) {
				strr = "9999999999";
			}
			sqlAdd = " cast("+ check +" as unsigned) between " + strl + " and "
					+ strr + " and ";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
		return sqlAdd;
	}

	// 生成选中消费日时的sql语句
	public static <T> String sqlForDate(T form,String check) {
		String sqlAdd = null;
		String strl = null;
		String strr = null;
		try {
			strl = BeanUtils.getProperty(form, "l_date");
			strr = BeanUtils.getProperty(form, "r_date");
			if (strl == null || strl.equals("")) {
				strl = "1971-01-01";
			}
			if (strr == null || strr.equals("")) {
				strr = DateUntil.getLocationTime1();
			}

			sqlAdd = " cast(" + check + " as unsigned) between "
					+ String.valueOf(DateUntil.dateToLong(strl)) + " and "
					+ String.valueOf(DateUntil.dateToLong(strr)) + " and ";
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
		return sqlAdd;
	}
}
