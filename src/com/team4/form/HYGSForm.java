package com.team4.form;

import java.util.HashMap;
import java.util.Map;

import com.team4.until.DateUntil;

public class HYGSForm {

	private String codelegate;// 合约代表
	private String pinyin;// 合约简拼
	private String saler;// 销售员
	private String cotype;// 合约类型
	private String l_date;// 签约日期 ←
	private String r_date;// 签约日期 右
	private String l_total_cost;// 消费合计←
	private String r_total_cost;// 消费合计 右
	private String tel;// 联系电话
	private String remark;// 备注
	private String[] box;
	private String sqlAdd;

	public String[] getBox() {
		return box;
	}

	public void setBox(String[] box) {
		this.box = box;
	}

	private Map errors = new HashMap();

	public Map getErrors() {
		return errors;
	}

	public void setErrors(Map errors) {
		this.errors = errors;
	}

	public String getCodelegate() {
		return codelegate;
	}

	public void setCodelegate(String codelegate) {
		this.codelegate = codelegate;
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

	public String getCotype() {
		return cotype;
	}

	public void setCotype(String cotype) {
		this.cotype = cotype;
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

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSqlAdd() {
		return sqlAdd;
	}

	public void setSqlAdd(String sqlAdd) {
		this.sqlAdd = sqlAdd;
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
		// 3.tel只能是数字
		if (b) {
			if (tel != null) {
				if (!tel.matches("[0-9]{0,20}")) {
					b = false;
					errors.put("error", "电话含有非法字符或长度不合法!!");
				}
			}
		}
		// 4 l_date&r_date不能大于系统当前时间
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
		// 5.l_date<r_date
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
