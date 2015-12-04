package com.team4.until;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.team4.domain.Employee;
import com.team4.domain.KL;
import com.team4.form.KLForm;
import com.team4.service.KLService;
import com.team4.service.impl.KLServiceImpl;

public class WebUntil {

	public static <T> T requestToBean(HttpServletRequest request,
			Class<T> beanClass) {

		try {
			// 实例化bean
			T bean = beanClass.newInstance();

			// 将request表单封装到bean
			Enumeration e = request.getParameterNames();
			int i = 0;
			while (e.hasMoreElements()) {
				String name = (String) e.nextElement();
				if (name.equals("box")) {
					String[] values = request.getParameterValues(name);
					BeanUtils.setProperty(bean, name, values);
				} else {
					String value = request.getParameter(name);
					if (value == null) {
						continue;
					}
					BeanUtils.setProperty(bean, name, value);
				}
			}
			return bean;
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}
}
