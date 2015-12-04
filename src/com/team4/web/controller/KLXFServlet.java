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
import com.team4.domain.KLXF;
import com.team4.form.KLXFForm;
import com.team4.service.KLXFService;
import com.team4.service.impl.KLXFServiceImpl;
import com.team4.until.SqlUtils;
import com.team4.until.WebUntil;

public class KLXFServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			//清空session中的form
			request.getSession().removeAttribute("form");
			// 清空session中list
			request.getSession().removeAttribute("list");
			// 清空session中的total
			request.getSession().removeAttribute("total");
			request.setCharacterEncoding("UTF-8");
			// 1.检查表单数据是否合法
			KLXFForm form = WebUntil.requestToBean(request, KLXFForm.class);
			String str = null;
			String strl = null;
			String strr = null;
			String sqlAdd = null;
			String[] box = null;
			String cost_place = null;
			// 2.不合法，返回友好提示信息
			boolean b = form.checkDate();
			if (!b) {
				request.setAttribute("form", form);
				request.getRequestDispatcher("/WEB-INF/jsp/KLXFPage.jsp")
						.forward(request, response);
				return;
			}
			// 3.合法，生成对应sqlAdd语句
			else {
				box = form.getBox();
				if (box != null) {
					sqlAdd = " where ";
					for (int i = 0; i < box.length; i++) {
						// 选中消费合计时对应sql语句
						if (box[i].equals("total_cost")) {

							sqlAdd = sqlAdd
									+ SqlUtils.sqlForTotal_Cost(form, box[i]);
						}
						// 选中消费日期时对应sql语句
						else if (box[i].equals("date")) {

							sqlAdd = sqlAdd + SqlUtils.sqlForDate(form, box[i]);
						}
						// 选中place时对应的sql语句
						else if (box[i].equals("place")) {

						} else {
							try {
								str = BeanUtils.getProperty(form, box[i]);
							} catch (Exception e) {
								// TODO: handle exception
								throw new RuntimeException(e);
							}
							if (str != null && !str.equals("")) {
								sqlAdd = sqlAdd + box[i] + " like '%" + str
										+ "%' and ";
							}
						}
					}
					if (form.getReorder().equals("y")) {
						sqlAdd = sqlAdd + "reorder = 'y' ";
					} else if (form.getReorder().equals("n")) {
						sqlAdd = sqlAdd + "reorder = 'n' ";
					}
					if (sqlAdd.substring(sqlAdd.length() - 4, sqlAdd.length())
							.equals("and ")) {
						sqlAdd = sqlAdd.substring(0, sqlAdd.length() - 4);
					}
				}
				if (form.getReorder().equals("y")) {
					sqlAdd = " where reorder = 'y' ";
				} else if (form.getReorder().equals("n")) {
					sqlAdd = " where reorder = 'n' ";
				}

				if (sqlAdd == null || !sqlAdd.equals(" where ")) {
					form.setSqlAdd(sqlAdd);
				}
				// 4.调用service层方法进行查找,并显示数据
				Employee emp = (Employee) request.getSession().getAttribute(
						"employee");
				List<KLXF> list = new ArrayList<KLXF>();
				String power = emp.getPower();
				KLXFService klxfs = new KLXFServiceImpl();
				String alter = null;
				list = klxfs.selectKLXF(form, power);

				Map total = new HashMap();
				int total_total = 0;
				int front_total = 0;
				int res_total = 0;
				int bath_total = 0;
				int other_total = 0;
				for (int i = 0; i < list.size(); i++) {
					total_total += Integer
							.parseInt(list.get(i).getTotal_cost());
					front_total += Integer
							.parseInt(list.get(i).getFront_cost());
					res_total += Integer.parseInt(list.get(i).getRes_cost());
					bath_total += Integer.parseInt(list.get(i).getBath_cost());
					other_total += Integer.parseInt(list.get(i)
							.getOthers_cost());
				}
				total.put("total", total_total);
				total.put("front", front_total);
				total.put("bath", bath_total);
				total.put("res", res_total);
				total.put("other", other_total);
				if (box != null) {
					for (int i = 0; i < box.length; i++) {
						System.out.println(box[i]);
						if (box[i].equals("place")) {
							String place = form.getPlace();
							for (int j = 0; j < list.size(); j++) {
								try {
									list.get(j).setTotal_cost(
											BeanUtils.getProperty(list.get(j),
													place));
								} catch (Exception e) {
									// TODO: handle exception
									throw new RuntimeException(e);
								}
							}
							if (place.equals("total_cost")) {
								cost_place = "全部";
							} else if (place.equals("front_cost")) {
								cost_place = "前台";
							} else if (place.equals("res_cost")) {
								cost_place = "餐饮";
							} else if (place.equals("others_cost")) {
								cost_place = "其他";
							} else if (place.equals("bath_cost")) {
								cost_place = "洗浴";
							}
						}
					}
				}
				if (list.size() == 0) {
					alter = "<script>alert('empty!')</script>";
				}
				request.setAttribute("alter", alter);
				request.getSession().setAttribute("form", form);
				request.getSession().setAttribute("list", list);
				request.getSession().setAttribute("total", total);
				request.setAttribute("place", cost_place);
				request.getRequestDispatcher("/WEB-INF/jsp/KLXFPage.jsp")
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
