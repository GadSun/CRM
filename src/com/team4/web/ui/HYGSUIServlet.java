package com.team4.web.ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team4.service.HYGSService;
import com.team4.service.impl.HYGSServiceImpl;

public class HYGSUIServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			//清空session中的form
			request.getSession().removeAttribute("form");
			// 清空session中list
			request.getSession().removeAttribute("list");
			//清空session中的total
			request.getSession().removeAttribute("total");
			request.setCharacterEncoding("UTF-8");
			HYGSService hygss = new HYGSServiceImpl();
			List<String> listForSaler = new ArrayList<String>();
			List<String> listForCotype = new ArrayList<String>();

			listForSaler = hygss.selectSaler();
			listForCotype = hygss.selectCotype();

			request.getSession().setAttribute("listForSaler", listForSaler);
			request.getSession().setAttribute("listForCotype", listForCotype);
			request.getRequestDispatcher("/WEB-INF/jsp/HYGSPage.jsp").forward(
					request, response);
			return;
		} catch (Exception e) {
			// TODO: handle exception
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
