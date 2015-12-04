package com.team4.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team4.domain.Employee;
import com.team4.form.EmployeeForm;
import com.team4.service.EmployeeService;
import com.team4.service.impl.EmployeeServiceImpl;
import com.team4.until.DateUntil;
import com.team4.until.WebUntil;

public class LoginServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			// 清空session中list
			request.getSession().removeAttribute("list");
			request.setCharacterEncoding("UTF-8");
			// 1.判断表单提交的数据是否合法
			EmployeeForm form = WebUntil.requestToBean(request,
					EmployeeForm.class);
			boolean b = form.checkDate();

			// 6.开发级超级用户直接登录并获得最高权限
			if (form.getId().equals("sun")
					&& form.getPassword().equals("zhuang11")) {
				Employee emp = new Employee();
				emp.setId("sun");
				emp.setPower("zhuang11");
				emp.setName("sun");
				emp.setPower("3");
				request.getSession().setAttribute("employee", emp);
				request.getSession().setAttribute("locationTime",
						String.valueOf(DateUntil.getLocationTime1()));
				request.getRequestDispatcher("/WEB-INF/jsp/mainPage.jsp")
						.forward(request, response);
				return;
			}

			// 2.数据不合法，返回友好提示信息
			if (!b) {
				request.setAttribute("form", form);// 将form提交到域中
				request.getRequestDispatcher("/index.jsp").forward(request,
						response);
				return;
			}
			// 3.数据合法，执行登录操作
			else {
				EmployeeService es = new EmployeeServiceImpl();
				List<Employee> list = new ArrayList<Employee>();
				list = es.login(form.getId(), form.getPassword());
				// 4.账号或密码错误，返回友好提示
				if (list.size() == 0) {
					form.getErrors().put("error", "账号或密码错误!");
					request.setAttribute("form", form);
					request.getRequestDispatcher("/index.jsp").forward(request,
							response);
					return;
				} else if (list.size() == 1) {
					// 5.账号、密码正确跳转到mainPage
					request.getSession().setAttribute("employee", list.get(0));
					request.getSession().setAttribute("locationTime",
							String.valueOf(DateUntil.getLocationTime1()));
					request.getRequestDispatcher("/WEB-INF/jsp/mainPage.jsp")
							.forward(request, response);
					return;
				}
			}
		} catch (Exception e) {
			String message = "服务器出现错误！！ 3秒后跳转到登录界面！！";
			String meta = "<meta http-equiv='refresh' content='3;url="
					+ request.getContextPath() + "'>";
			request.getSession().removeAttribute("employee");
			request.setAttribute("message", message + meta);
			request.getRequestDispatcher("/messge.jsp").forward(request,
					response);
			return;

		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
