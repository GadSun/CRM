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

import com.team4.domain.HYGS_HYKP;
import com.team4.service.HYGS_HYKPService;
import com.team4.service.impl.HYGS_HYKPServiceImpl;
import com.team4.until.DateUntil;

public class HYGS_HYKPServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// 可以认为传输过来的数据一定是合法的，所以直接调用查询方法
			JSONObject json = new JSONObject();
			response.setContentType("application/json");
			HYGS_HYKPService hygshykps = new HYGS_HYKPServiceImpl();
			List<HYGS_HYKP> list = new ArrayList<HYGS_HYKP>();
			String id = request.getParameter("idFromPage");
			list = hygshykps.select(id);
			try {
				for (int i = 0; i < list.size(); i++) {
					json.put("Id", list.get(i).getId());
					json.put("Cotype", list.get(i)
							.getCotype());
					json.put("Coname", list.get(i)
							.getConame());
					json.put("Codelegate", list.get(i)
							.getCodelegate());
					json.put("Tel", list.get(i)
							.getTel());
					json.put("Saler", list.get(i)
							.getSaler());
					json.put("Bedate", DateUntil.longToDate(Long.parseLong(list.get(i)
							.getBedate())));
					json.put("Stdate", DateUntil.longToDate(Long.parseLong(list.get(i)
							.getStdate())));
					json.put("Stdiscount", list.get(i)
							.getStdiscount());
					json.put("Certificatetype", list.get(i)
							.getCertificatetype());
					json.put("Certificatenumber", list.get(i)
							.getCertificatenumber());
					json.put("Sex", list.get(i)
							.getSex());
					json.put("Address", list.get(i)
							.getAddress());
					json.put("Resname", list.get(i)
							.getResname());
				}
			} catch (Exception e) {
				// TODO: handle exception
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
