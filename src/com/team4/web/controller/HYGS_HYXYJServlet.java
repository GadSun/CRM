package com.team4.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.team4.domain.HYGS_HYXYJ;
import com.team4.service.HYGS_HYXYJService;
import com.team4.service.impl.HYGS_HYXYJServiceImpl;

public class HYGS_HYXYJServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// 可以认为传输过来的数据一定是合法的，所以直接调用查询方法
			request.getSession().removeAttribute("dfpe");
			JSONObject json = new JSONObject();
			response.setContentType("application/json");
			HYGS_HYXYJService hygshyxyjs = new HYGS_HYXYJServiceImpl();
			List<HYGS_HYXYJ> list = new ArrayList<HYGS_HYXYJ>();
			String id = request.getParameter("idFromPage");
			list = hygshyxyjs.select(id);
			try {
				for (int i = 0; i < list.size(); i++) {
					json.put("Id" + String.valueOf(i), list.get(i).getId());
					json.put("Setype" + String.valueOf(i), list.get(i)
							.getSetype());
					json.put("Fitype" + String.valueOf(i), list.get(i)
							.getFitype());
					json.put("Roprice" + String.valueOf(i), list.get(i)
							.getRoprice());
					json.put("Stdiscount" + String.valueOf(i), list.get(i)
							.getStdiscount());
					json.put("Prdiscount" + String.valueOf(i), list.get(i)
							.getPrdiscount());
					json.put("Name" + String.valueOf(i), list.get(i).getName());
					json.put("Place" + String.valueOf(i), list.get(i)
							.getPlace());
					json.put("Resname" + String.valueOf(i), list.get(i)
							.getResname());
				}
				json.put("size", list.size());
			} catch (Exception e) {
				// TODO: handle exception
				throw new RuntimeException(e);
			}
			response.getWriter().write(json.toString());
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
