package com.team4.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.team4.domain.Employee;
import com.team4.domain.KL;
import com.team4.form.KLForm;
import com.team4.service.KLService;
import com.team4.service.impl.KLServiceImpl;
import com.team4.until.SqlUtils;
import com.team4.until.WebUntil;

public class KLServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		try {
			//清空session中的form
			request.getSession().removeAttribute("form");
			// 清空session中list
			request.getSession().removeAttribute("list");
			//清空session中的total
			request.getSession().removeAttribute("total");
			// 1.检查表单提交数据是否合法
			KLForm form = WebUntil.requestToBean(request, KLForm.class);
			String str = null;
			String strl = null;
			String strr = null;
			String sqlAdd = null;
			String[] box = null;
			// 2.生成后续sql语句
			box = form.getBox();
			System.out.println(box);
			if (box != null) {
				sqlAdd = " where ";
				// 循环获得字段名称
				for (int i = 0; i < box.length; i++) {
					// 是total_cost时生成对应sql语句
					if (box[i].equals("total_cost")) {

						sqlAdd = sqlAdd
								+ SqlUtils.sqlForTotal_Cost(form, box[i]);
					}
					// 选中vip时对应sql语句
					else if (box[i].equals("khtype")) {
						sqlAdd = sqlAdd + "khtype = 'vip' and ";
					}
					// 选中hobbyy时对应sql语句
					else if (box[i].equals("hobby")) {
						sqlAdd = sqlAdd + "hobby is not null and ";
					}
					// 其他时生成对应sql语句
					else {
						try {
							str = BeanUtils.getProperty(form, box[i]);
						} catch (Exception e) {
							// TODO: handle exception
							throw new RuntimeException(e);
						}
						if (str != null && !str.equals("")) {
							sqlAdd = sqlAdd + box[i] + " like '%" + str + "%' and ";
						}
					}

				}
				if (sqlAdd.substring(sqlAdd.length() - 4, sqlAdd.length())
						.equals("and ")) {
					sqlAdd = sqlAdd.substring(0, sqlAdd.length() - 4);
				}
				if (!sqlAdd.equals(" where ")) {
					form.setSqlAdd(sqlAdd);
				}
			}
			boolean b = form.checkDate();
			if (!b) {
				// 3.数据不合法，返回友好提示信息
				request.setAttribute("form", form);
				request.getRequestDispatcher("/WEB-INF/jsp/KLPage.jsp")
						.forward(request, response);
				return;
			} else {
				// 4.数据合法，调用service层方法进行查找
				KLService kls = new KLServiceImpl();
				List<KL> list = new ArrayList<KL>();
				Map total = new HashMap();
				String power;
				String alter = null;
				int total_total = 0;
				int front_total = 0;
				int res_total = 0;
				int bath_total = 0;
				int other_total = 0;
				Employee emp = (Employee) request.getSession().getAttribute(
						"employee");
				power = emp.getPower();
				list = kls.selectKL(form, power);
				for(int i = 0;i<list.size();i++){
					total_total += Integer.parseInt(list.get(i).getTotal_cost());
					front_total += Integer.parseInt(list.get(i).getFront_cost());
					res_total += Integer.parseInt(list.get(i).getRes_cost());
					bath_total += Integer.parseInt(list.get(i).getBath_cost());
					other_total += Integer.parseInt(list.get(i).getOthers_cost());
				}
				total.put("total", total_total);
				total.put("front", front_total);
				total.put("bath", bath_total);
				total.put("res", res_total);
				total.put("other", other_total);
				if (list.size() == 0) {
					alter = "<script>alert('empty!')</script>";
				}
				request.setAttribute("alter", alter);
				request.getSession().setAttribute("form", form);
				request.getSession().setAttribute("list", list);
				request.getSession().setAttribute("total", total);
				request.getRequestDispatcher("/WEB-INF/jsp/KLPage.jsp")
						.forward(request, response);
				return;
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
			return;

		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
