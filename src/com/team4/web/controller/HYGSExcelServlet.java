package com.team4.web.controller;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.team4.domain.Employee;
import com.team4.domain.KL;
import com.team4.until.DateUntil;
import com.team4.until.ExcelUtil;

public class HYGSExcelServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("1");
		request.setCharacterEncoding("UTF-8");
		try {
			List<KL> list = (List<KL>) request.getSession()
					.getAttribute("list");
			List<String> listName = new ArrayList<String>();
			List<String> thead = new ArrayList<String>();
			listName.add("id");
			listName.add("cotype");
			listName.add("coname");
			listName.add("tel");
			listName.add("bedate");
			listName.add("stdate");
			listName.add("front_cost");
			listName.add("res_cost");
			listName.add("bath_cost");
			listName.add("ktv_cost");
			listName.add("total_cost");
			listName.add("codelegate");
			listName.add("fax");
			listName.add("saler");
			listName.add("discount");
			listName.add("account");
			listName.add("remark");
			thead.add("合约编号");
			thead.add("合约类型");
			thead.add("合约名称");
			thead.add("联系电话");
			thead.add("签约日期");
			thead.add("终止日期");
			thead.add("前天消费额");
			thead.add("餐饮消费额");
			thead.add("洗浴消费额");
			thead.add("消费合计");
			thead.add("合约代表");
			thead.add("公司传真");
			thead.add("销售员");
			thead.add("折扣");
			thead.add("应收账号");
			thead.add("备注");
			Employee emp = (Employee) request.getSession().getAttribute(
					"employee");
			String name = emp.getName();
			String date = DateUntil.getLocationTime1();
			String title = "合约公司查询";
			String fileTitle = "HYGS";
			try {
				ExcelUtil.getExcel(list, listName, thead, date, name, title, fileTitle);
				File file = new File("D:/" + fileTitle + ".xls");
				Desktop.getDesktop().open(file);
			} catch (Exception e) {
				// TODO: handle exception
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
