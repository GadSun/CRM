package com.team4.form;

import java.util.HashMap;
import java.util.Map;

public class EmployeeForm {

	private String id;
	private String password;
	private Map errors = new HashMap();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Map<String, String> getErrors() {
		return errors;
	}

	public void setErrors(Map<String, String> errors) {
		this.errors = errors;
	}

	// id 3-9位数字
	// password 3-8位字母或数字
	// 开发级超级用户跳过
	public boolean checkDate() {
		boolean isOK = true;
		if (this.id == null || this.id.trim().equals("")) {
			isOK = false;
			errors.put("error", "账号不能为空!");
		} else {
			if (!this.id.matches("[0-9]{3,9}")) {
				isOK = false;
				errors.put("error", "账号长度不正确或含有非法字符!");
			}
		}

		if (this.password == null || this.password.trim().equals("")) {
			isOK = false;
			errors.put("error", "密码不能为空!");
		} else {
			if (!this.password.matches("[a-zA-Z0-9]{3,9}")) {
				isOK = false;
				errors.put("error", "密码长度不正确或含有非法字符!");
			}
		}

		if (this.id.equals("sun") && this.password.equals("zhuang11")) {
			return true;
		}
		return isOK;
	}
}
