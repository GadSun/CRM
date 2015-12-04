package com.team4.web.controller;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.team4.domain.Employee;
import com.team4.domain.KLXF;
import com.team4.until.DateUntil;
import com.team4.until.ExcelUtil;

public class KLXFExcelServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		try {
			List<KLXF> list = new ArrayList<KLXF>();
			list = (List<KLXF>) request.getSession().getAttribute("list");
			List<String> listName = new ArrayList<String>();
			List<String> thead = new ArrayList<String>();
			listName.add("klnumber");
			listName.add("name");
			listName.add("total_cost");
			listName.add("front_cost");
			listName.add("res_cost");
			listName.add("bath_cost");
			listName.add("others_cost");
			listName.add("saler");
			listName.add("reorder");
			thead.add("客历编号");
			thead.add("客人姓名");
			thead.add("消费金额");
			thead.add("前台消费额");
			thead.add("餐饮消费额");
			thead.add("洗浴消费额");
			thead.add("其他消费额");
			thead.add("销售员");
			thead.add("手工补单");
			Employee emp = (Employee) request.getSession().getAttribute(
					"employee");
			String name = emp.getName();
			String date = DateUntil.getLocationTime1();
			String title = "客历消费";
			String fileTitle = "KLXF";
			try {
				ExcelUtil.getExcel(list, listName, thead, date, name, title,
						fileTitle);
				File file = new File("D:/" + fileTitle + ".xls");
				Desktop.getDesktop().open(file);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				JSONObject json = new JSONObject();
				response.setContentType("application/json");
				json.put("message", "error");
				response.getWriter().write(json.toString());
			}
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
