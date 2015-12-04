<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'klxfView.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/ctview.css">
</head>
<body>
	<div id="main">
		<form>
			<p id="p1">客历消费</p>
			<p id="p2">打印日期:${locationTime }</p>
			<p id="p3">总计：${list.size() }</p>
			<p id="p4">消费总计：${total.total }</p>
			<p id="p5">前台消费总计：${total.front }</p>
			<p id="p6">餐饮消费总计：${total.res }</p>
			<p id="p7">洗浴消费总计：${total.bath }</p>
			<p id="p8">其他消费总计：${total.other }</p>
			<p id="pp"></p>
			<p id="ppp">制表人：${employee.name }</p>
			<p id="pppp">第四小组</p>
			<p id="ppppp">查询条件&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${condition }</p>
			<a href="${pageContext.request.contextPath }/servlet/KLXFUIServlet"
				style="font-size:'0.9em';position:absolute;left:2%;top:4%;text-decoration:underline;">返回上一层</a>
			<a
				href="${pageContext.request.contextPath }/servlet/MainPageUIServlet"
				style="font-size:'0.9em';position:absolute;left:8%;top:4%;text-decoration:underline;">返回主界面</a>
			<div id="tableview">
				<table id="table1">
					<thead>
						<tr>
							<th align="center" class="tabhead" width="10%">序号</th>
							<th align="center" class="tabhead" width="10%">客人姓名</th>
							<th align="center" class="tabhead" width="10%">客历编号</th>
							<th align="center" class="tabhead" width="10%">消费金额</th>
							<th align="center" class="tabhead" width="10%">前台消费金额</th>
							<th align="center" class="tabhead" width="10%">餐饮消费金额</th>
							<th align="center" class="tabhead" width="10%">洗浴消费金额</th>
							<th align="center" class="tabhead" width="10%">其他消费金额</th>
							<th align="center" class="tabhead" width="10%">销售员</th>
							<th align="center" class="tabhead" width="10%">手工补单否</th>
						</tr>
					</thead>
					<tbody id="group_one">
						<c:forEach var="str" items="${list }">
							<tr>
								<td align="center" class="message">${str.num }</td>
								<td align="center" class="message">${str.name }</td>
								<td align="center" class="message">${str.id }</td>
								<td align="center" class="message">${str.total_cost }</td>
								<td align="center" class="message">${str.front_cost }</td>
								<td align="center" class="message">${str.res_cost }</td>
								<td align="center" class="message">${str.bath_cost }</td>
								<td align="center" class="message">${str.others_cost }</td>
								<td align="center" class="message">${str.saler }</td>
								<td align="center" class="message">${str.reorder }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</form>
		<table id="jump">
			<tr>
				<td><a href="javascript:void(0);" onclick="page.firstPage();">首页</a>
				</td>
				<td><a href="javascript:void(0);" onclick="page.prePage();">上一页</a>
				</td>
				<td><a href="javascript:void(0);" onclick="page.nextPage();">下一页</a>
				</td>
				<td><a href="javascript:void(0);" onclick="page.lastPage();">尾页</a>
				</td>
				<td><span id="pageindex"></span>
				</td>
			</tr>
		</table>
	</div>
</body>
<script
	src="${pageContext.request.contextPath }/jquery/external/jquery/jquery.js"></script>
<script
	src="${pageContext.request.contextPath }/jquery/jquery-ui.min.js"></script>
<script src="${pageContext.request.contextPath }/js/ctview.js"></script>
</html>
