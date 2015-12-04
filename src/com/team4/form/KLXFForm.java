package com.team4.form;

import java.util.HashMap;
import java.util.Map;

import com.team4.until.DateUntil;

public class KLXFForm {

	private String pinyin;
	private String saler;
	private String l_date;
	private String r_date;
	private String l_total_cost;
	private String r_total_cost;
	private String place;
	private String[] box;
	private String reorder;
	private Map errors = new HashMap();
	private String sqlAdd;

	public Map getErrors() {
		return errors;
	}

	public void setErrors(Map errors) {
		this.errors = errors;
	}

	public String getSqlAdd() {
		return sqlAdd;
	}

	public void setSqlAdd(String sqlAdd) {
		this.sqlAdd = sqlAdd;
	}

	public String getReorder() {
		return reorder;
	}

	public void setReorder(String reorder) {
		this.reorder = reorder;
	}

	public String[] getBox() {
		return box;
	}

	public void setBox(String[] box) {
		this.box = box;
	}

	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	public String getSaler() {
		return saler;
	}

	public void setSaler(String saler) {
		this.saler = saler;
	}

	public String getL_date() {
		return l_date;
	}

	public void setL_date(String l_date) {
		this.l_date = l_date;
	}

	public String getR_date() {
		return r_date;
	}

	public void setR_date(String r_date) {
		this.r_date = r_date;
	}

	public String getL_total_cost() {
		return l_total_cost;
	}

	public void setL_total_cost(String l_total_cost) {
		this.l_total_cost = l_total_cost;
	}

	public String getR_total_cost() {
		return r_total_cost;
	}

	public void setR_total_cost(String r_total_cost) {
		this.r_total_cost = r_total_cost;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public boolean checkDate() {
		boolean b = true;
		// 1.l_total_cost 和 r_total_cost 只能是数字
		if (b) {
			if (l_total_cost != null && !l_total_cost.equals("")) {
				if (!l_total_cost.matches("[0-9]{0,10}")) {
					b = false;
					errors.put("error", "消费合计必须是数字!!");
				}
			}
		}
		if (b) {
			if (r_total_cost != null && !r_total_cost.equals("")) {
				System.out.println(!r_total_cost.matches("[0-9]{0,10}"));
				if (!r_total_cost.matches("[0-9]{0,10}")) {
					b = false;
					errors.put("error", "消费合计必须是数字!!");
				}
			}
		}
		if (b) {
			if (l_total_cost != null && r_total_cost != null
					&& !l_total_cost.equals("") && !r_total_cost.equals("")) {
				// 2.l_total_cost < r_total_cost

				if (Integer.parseInt(l_total_cost) > Integer
						.parseInt(r_total_cost)) {
					b = false;
					errors.put("error", "消费合计两侧值不合法!!");
				}
			}
		}
		// 3. l_date&r_date不能大于系统当前时间
		if (b) {
			if (l_date != null && !l_date.equals("")) {
				System.out.println(l_date);
				try {
					if (DateUntil.dateToLong(l_date) > DateUntil
							.getLocationTime() / 1000) {
						b = false;
						errors.put("error", "输入的日期不能大于当前系统时间!!");
					}
				} catch (Exception e) {
					// TODO: handle exception
					throw new RuntimeException(e);
				}
			}
		}
		if (b) {
			if (r_date != null && !r_date.equals("")) {
				try {
					if (DateUntil.dateToLong(r_date) > DateUntil
							.getLocationTime() / 1000) {
						b = false;
						errors.put("error", "输入的日期不能大于当前系统时间!!");
					}
				} catch (Exception e) {
					// TODO: handle exception
					throw new RuntimeException(e);
				}
			}
		}
		// 4.l_date<r_date
		if (b) {
			if (l_date != null && !l_date.equals("") && r_date != null
					&& !r_date.equals("")) {
				try {
					if (DateUntil.dateToLong(l_date) > DateUntil
							.dateToLong(r_date)) {
						b = false;
						errors.put("error", "两侧日期不合法");
					}
				} catch (Exception e) {
					// TODO: handle exception
					throw new RuntimeException(e);
				}
			}
		}
		return b;
	}
}
