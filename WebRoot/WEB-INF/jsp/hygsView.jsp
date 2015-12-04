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

<title>My JSP 'hygsView.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/hyview.css">
</head>

<body>
	<div id="main">
		<form>
			<p id="p1">合约公司基本信息</p>
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
			<a href="${pageContext.request.contextPath }/servlet/HYGSUIServlet"
				style="font-size:'0.9em';position:absolute;left:2%;top:4%;text-decoration:underline;">返回上一层</a>
			<a
				href="${pageContext.request.contextPath }/servlet/MainPageUIServlet"
				style="font-size:'0.9em';position:absolute;left:8%;top:4%;text-decoration:underline;">返回主界面</a>
			<div id="tableview">
				<table id="table1">
					<thead>
						<tr>
							<th align="left" class="tabhead" width="3%">序号</th>
							<th align="left" class="tabhead" width="5%">合约编号</th>
							<th align="left" class="tabhead" width="5%">合约类型</th>
							<th align="left" class="tabhead" width="5%">合约名称</th>
							<th align="left" class="tabhead" width="5%">联系电话</th>
							<th align="left" class="tabhead" width="5%">签约日期</th>
							<th align="left" class="tabhead" width="5%">终止日期</th>
							<th align="left" class="tabhead" width="6%">前台消费额</th>
							<th align="left" class="tabhead" width="6%">餐饮消费额</th>
							<th align="left" class="tabhead" width="6%">洗浴消费额</th>
							<th align="left" class="tabhead" width="6%">KTV消费额</th>
							<th align="left" class="tabhead" width="5%">消费合计</th>
							<th align="left" class="tabhead" width="5%">合约代表</th>
							<th align="left" class="tabhead" width="5%">公司传真</th>
							<th align="left" class="tabhead" width="4%">销售员</th>
							<th align="left" class="tabhead" width="3%">折扣</th>
							<th align="left" class="tabhead" width="5%">应收账号</th>
							<th align="left" class="tabhead" width="3%">备注</th>
						</tr>
					</thead>
					<tbody id="group_one">
						<c:forEach var="str" items="${list }">
							<tr>
								<td align="left" class="message">${str.num }</td>
								<td align="left" class="message">${str.id }</td>
								<td align="left" class="message">${str.cotype }</td>
								<td align="left" class="message">${str.coname }</td>
								<td align="left" class="message">${str.tel }</td>
								<td align="left" class="message">${str.bedate }</td>
								<td align="left" class="message">${str.stdate }</td>
								<td align="left" class="message">${str.front_cost }</td>
								<td align="left" class="message">${str.res_cost }</td>
								<td align="left" class="message">${str.bath_cost }</td>
								<td align="left" class="message">${str.ktv_cost }</td>
								<td align="left" class="message">${str.total_cost }</td>
								<td align="left" class="message">${str.codelegate }</td>
								<td align="left" class="message">${str.fax }</td>
								<td align="left" class="message">${str.saler }</td>
								<td align="left" class="message">${str.discount }</td>
								<td align="left" class="message">${str.account }</td>
								<td align="left" class="message">${str.remark }</td>
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
<script src="${pageContext.request.contextPath }/js/hyview.js"></script>
</html>
