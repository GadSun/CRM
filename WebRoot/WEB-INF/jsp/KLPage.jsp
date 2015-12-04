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

<title>My JSP 'KL.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/BasicMessage.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/jquery/jquery-ui.min.css">
<script
	src="${pageContext.request.contextPath }/jquery/external/jquery/jquery.js"></script>
<script
	src="${pageContext.request.contextPath }/jquery/jquery-ui.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/klPage.js">
	
</script>
</head>
<body>
	<div id="main" lang="en">
		<p id="zs">总计：${list.size() }</p>
		<p id="zj">总消费额：${total.total }</p>
		<p id="qt">前台消费合计：${total.front }</p>
		<p id="cy">餐饮消费合计：${total.res }</p>
		<p id="xy">洗浴消费合计：${total.bath }</p>
		<p id="qta">其他消费合计：${total.other }</p>
		<form name="myForm" method="post">
			<div id="top">
				<div id="tleft">
					<div id="pinyin">
						<input id="chkpinyin1" type="checkbox" name="box" value="pinyin"
							onclick="oper()">简&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;拼</input> <input
							id="pinyin1" class="oneline" type="text" name="pinyin" disabled></input>
					</div>
					<div id="tel">
						<input id="chktel1" type="checkbox" name="box" value="tel"
							onclick="oper1()">手&nbsp;&nbsp;&nbsp;&nbsp;机</input> <input
							id="tel1" class="oneline" type="text" name="tel" disabled></input>
					</div>
					<div id="saler">
						<input id="chksaler1" type="checkbox" name="box" value="saler"
							onclick="oper2()">销&nbsp;&nbsp;售&nbsp;&nbsp;员</input> <select
							name="saler" id="slcsaler" style="color:gray;" disabled>
							<option value="">全&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;部</option>
							<c:forEach var="saler" items="${listForSaler }">
								<option value="${saler }">${saler }</option>
							</c:forEach>
						</select>
					</div>
					<div id="contractname">
						<input id="chkcontractname1" type="checkbox" name="box"
							value="contractname" onclick="oper3()">合约名称</input> <select
							name="contractname" id="slccontractname" style="color:gray;"
							disabled>
							<option value="">全&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;部</option>
							<c:forEach var="contractname" items="${listForContractname }">
								<option value="${contractname }">${contractname }</option>
							</c:forEach>

						</select>
					</div>
					<div id="total_cost">
						<input id="chktotal_cost1" type="checkbox" name="box"
							value="total_cost" onclick="oper4()">消费合计</input> <input
							id="l_total_cost1" class="threeline" type="text"
							name="l_total_cost" disabled>&nbsp;&nbsp;元&nbsp;&nbsp;~</input> <input
							id="r_total_cost1" class="threeline" type="text"
							name="r_total_cost" disabled>&nbsp;&nbsp;元</input>
					</div>
					<div id="remark">
						<input id="chkremark1" type="checkbox" name="box" value="remark"
							onclick="oper5()">备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注</input>
						<input id="threeline_b" type="text" name="remark" disabled></input>
					</div>
					<div id="khtype">
						<input type="checkbox" name="box" value="khtype">会员</input>
					</div>
					<div id="hobby">
						<input type="checkbox" name="box" value="hobby">喜好/禁忌</input>
					</div>
				</div>
				<div id="tright">
					<button id="button1" onclick="select()">查询</button>
					<button id="button2" onclick="getPrint()">打印预览</button>
					<button id="button3" type="button" onclick="pageClear()">清空</button>
					<button id="button4" type="button" onclick="getExcel()">EXCEL导出</button>
				</div>
				<input id="path" type="hidden"
					value="${pageContext.request.contextPath }">
		</form>
	</div>
	<div id="bottom">
		<a
			href="${pageContext.request.contextPath }/servlet/MainPageUIServlet"
			id="back"
			style="font-size:12px;position:absolute;left:2%;top:94%;text-decoration:underline;">返回导航</a>
		<table id="table1" border="1px" cellspacing="0px">
			<caption id="p1">搜索结果:</caption>
			<thead>
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
					<th align="left" class="tabhead" width="5%">前台消费额</th>
					<th align="left" class="tabhead" width="5%">餐饮消费额</th>
					<th align="left" class="tabhead" width="5%">洗浴消费额</th>
					<th align="left" class="tabhead" width="6%">其他类消费额</th>
					<th align="left" class="tabhead" width="5%">住店天数</th>
					<th align="left" class="tabhead" width="5%">进场次数</th>
					<th align="left" class="tabhead" width="3%">备注</th>
				</tr>
			</thead>
			<tbody id="group_one">
				<c:forEach var="str" items="${list }">
					<tr>
						<td align="left" class="message">${str.num }</td>
						<td align="left" class="message">${str.klnumber }</td>
						<td align="left" class="message">${str.name }</td>
						<td align="left" class="message">无</td>
						<td align="left" class="message">${str.contractname }</td>
						<td align="left" class="message">${str.tel }</td>
						<td align="left" class="message">${str.cardnumber }</td>
						<td align="left" class="message">${str.saler }</td>
						<td align="left" class="message">${str.sex }</td>
						<td align="left" class="message">${str.total_cost }</td>
						<td align="left" class="message">${str.front_cost }</td>
						<td align="left" class="message">${str.res_cost }</td>
						<td align="left" class="message">${str.bath_cost }</td>
						<td align="left" class="message">${str.others_cost }</td>
						<td align="left" class="message">${str.days }</td>
						<td align="left" class="message">${str.total_cost_time }</td>
						<td align="left" class="message">${str.remark }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
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
	<div id="left">
		<table id="table2" style="position: absolute;left:0%;top: 5%;">
					<caption id="p2" style="font-size: 14px;font-weight: bolder;">当前登录信息</caption>
					<tr>
						<th align="left" style="font-size: 0.8em;font-family:'heiti'">用&nbsp;&nbsp;&nbsp;&nbsp;户:</th>
						<td align="left" style="font-size: 0.8em;">${employee.name }</td>
					</tr>
					<tr>
						<th align="left" style="font-size: 0.8em;font-family:'heiti'">权&nbsp;&nbsp;&nbsp;&nbsp;限:</th>
						<td align="left" style="font-size: 0.8em;">${employee.power }</td>
					</tr>
					<tr>
						<th align="left" style="font-size: 0.8em;font-family:'heiti'">登录时间:</th>
						<td align="left" style="font-size: 0.8em;">${locationTime }</td>
					</tr>
					<tr>
						<th align="left" style="font-size: 0.8em;font-family:'heiti'">错误信息:</th>
						<td align="left" style="font-size:0.8em;">${form.errors.error
							}</td>
					</tr>
				</table>
	</div>
	</div>
	${alter }
</body>
</html>
