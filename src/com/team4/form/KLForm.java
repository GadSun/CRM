package com.team4.form;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.util.SystemOutLogger;

public class KLForm {
	private String[] box;
	private String pinyin;
	private String saler;
	private String l_total_cost;
	private String r_total_cost;
	private String tel;
	private String contractname;
	private String remark;
	private String sqlAdd;
	private Map errors = new HashMap();

	public Map getErrors() {
		return errors;
	}

	public void setErrors(Map errors) {
		this.errors = errors;
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

	public String getContractname() {
		return contractname;
	}

	public void setContractname(String contractname) {
		this.contractname = contractname;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String[] getBox() {
		return box;
	}

	public void setBox(String[] box) {
		this.box = box;
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
		
		return b;
	}
}
