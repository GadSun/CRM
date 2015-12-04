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

<title>My JSP 'view.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/ctview.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/jquery/jquery-ui.min.css">

<body>
	<div id="main">
		<form>
			<p id="p1">客历基本信息</p>
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
			<a href="${pageContext.request.contextPath }/servlet/KLUIServlet"
				style="font-size:'0.9em';position:absolute;left:2%;top:4%;text-decoration:underline;">返回上一层</a>
			<a
				href="${pageContext.request.contextPath }/servlet/MainPageUIServlet"
				style="font-size:'0.9em';position:absolute;left:8%;top:4%;text-decoration:underline;">返回主界面</a>
			<div id="tableview">
				<table id="table1">
					<thead id="thead1">
						<tr>
							<th align="left" class="tabhead" width="3%">序号</th>
							<th align="left" class="tabhead" width="5%">客历编号</th>
							<th align="left" class="tabhead" width="5%">客人姓名</th>
							<th align="left" class="tabhead" width="5%">基本信息</th>
							<th align="left" class="tabhead" width="5%">合约名称</th>
							<th align="left" class="tabhead" width="6%">手机/联系电话</th>
							<th align="left" class="tabhead" width="5%">贵宾卡号</th>
							<th align="left" class="tabhead" width="4%">销售员</th>
							<th align="left" class="tabhead" width="3%">性别</th>
							<th align="left" class="tabhead" width="5%">消费合计</th>
							<th align="left" class="tabhead" width="6%">前台消费额</th>
							<th align="left" class="tabhead" width="6%">餐饮消费额</th>
							<th align="left" class="tabhead" width="6%">洗浴消费额</th>
							<th align="left" class="tabhead" width="7%">其他类消费额</th>
							<th align="left" class="tabhead" width="5%">住店天数</th>
							<th align="left" class="tabhead" width="5%">进场次数</th>
							<th align="left" class="tabhead" width="3%">备注</th>
						</tr>
					</thead>
					<tbody id="group_one">
						<c:forEach var="str" items="${list }">
							<tr>
								<td class="message">${str.num }</td>
								<td class="message">${str.name }</td>
								<td class="message">${str.id }</td>
								<td class="message">无</td>
								<td class="message">${str.contractname }</td>
								<td class="message">${str.tel }</td>
								<td class="message">${str.cardnumber }</td>
								<td class="message">${str.saler }</td>
								<td class="message">${str.sex }</td>
								<td class="message">${str.total_cost }</td>
								<td class="message">${str.front_cost }</td>
								<td class="message">${str.res_cost }</td>
								<td class="message">${str.bath_cost }</td>
								<td class="message">${str.others_cost }</td>
								<td class="message">${str.days }</td>
								<td class="message">${str.total_cost_time }</td>
								<td class="message">${str.remark }</td>
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
				<td><span id="pageindex"></span></td>
			</tr>
		</table>
	</div>
</body>
<script
	src="${pageContext.request.contextPath }/jquery/external/jquery/jquery.js"></script>
<script
	src="${pageContext.request.contextPath }/jquery/jquery-ui.min.js"></script>
<script src="${pageContext.request.contextPath }/js/view.js"></script>
</html>
