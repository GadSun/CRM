package com.team4.web.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team4.form.HYGSForm;
import com.team4.until.ConditionUtil;

public class HYGSPrintServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		try {
			HYGSForm form = (HYGSForm) request.getSession().getAttribute("form");
			String[] box = null;
			if(form != null){
				box = form.getBox();
			}
			String conditon = null;
			conditon = ConditionUtil.getCondition(box, form);
			request.setAttribute("condition", conditon);
			request.getRequestDispatcher("/WEB-INF/jsp/hygsView.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO: handle exception
			String message = "服务器出现错误！！ 3秒后跳转到登录界面！！";
			String meta = "<meta http-equiv='refresh' content='3;url="
					+ request.getContextPath() + "'>";
			request.getSession().removeAttribute("employee");
			request.setAttribute("message", message + meta);
			request.getRequestDispatcher("/messge.jsp").forward(request,
					response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
