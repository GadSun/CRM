package com.team4.web.ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team4.service.KLService;
import com.team4.service.impl.KLServiceImpl;

public class KLUIServlet extends HttpServlet {

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
			KLService kls = new KLServiceImpl();
			List<String> listForSaler = new ArrayList<String>();
			List<String> listForContractname = new ArrayList<String>();

			listForSaler = kls.selectSaler();
			listForContractname = kls.selectContractName();
			request.getSession().setAttribute("listForSaler", listForSaler);
			request.getSession().setAttribute("listForContractname",
					listForContractname);
			request.getRequestDispatcher("/WEB-INF/jsp/KLPage.jsp").forward(
					request, response);
			return;
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
