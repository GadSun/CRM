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

<title>My JSP 'KLXFPageJsp.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/costMessage.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/jquery/jquery-ui.min.css">
<script
	src="${pageContext.request.contextPath }/jquery/external/jquery/jquery.js"></script>
<script
	src="${pageContext.request.contextPath }/jquery/jquery-ui.min.js"></script>
<script>
	$(document).ready(function() {
		$("#txl_date_cost").datepicker({
			dateFormat : 'yy-mm-dd'
		});
		$("#txr_date_cost").datepicker({
			dateFormat : 'yy-mm-dd'
		});
	})
</script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/KLXFPage.js"></script>
</head>

<body>
	<div id="main">
		<p id="zs">总计：${list.size() }</p>
		<p id="zj">总消费额：${total.total }</p>
		<p id="qt">前台消费合计：${total.front }</p>
		<p id="cy">餐饮消费合计：${total.res }</p>
		<p id="xy">洗浴消费合计：${total.bath }</p>
		<p id="qta">其他消费合计：${total.other }</p>
		<form name="form1">
			<input id="path" type="hidden"
				value="${pageContext.request.contextPath }">
			<div id="top">
				<div id="tleft">
					<div id="pinyin">
						<input type="checkbox" id="chkpinyin" name="box" value="pinyin"
							onclick="oper()">简拼 &nbsp;&nbsp;&nbsp;&nbsp;<input id="txpinyin" class="oneline"
							name="pinyin" style="width: 100px;" disabled>
					</div>
					<div id="saler">
						<input type="checkbox" id="chksaler" name="box" value="saler"
							onclick="oper3()">销售员 <select
							id="slcsaler" name="saler" style="color:gray;width: 105px;"
							disabled>
							<option value="">全&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;部</option>
							<c:forEach var="saler" items="${listForSaler }">
								<option value="${saler }">${saler }</option>
							</c:forEach>
						</select>
					</div>
					<div id="total_cost">
						<input type="checkbox" id="chktotal_cost" name="box"
							value="total_cost" onclick="oper1()">消费合计 <input
							type="text" id="txl_total_cost" name="l_total_cost"
							style="width: 100px;" disabled>&nbsp;&nbsp;元&nbsp;&nbsp;~
						<input type="text" id="txr_total_cost" name="r_total_cost"
							style="width: 100px;" disabled>&nbsp;&nbsp;元
					</div>
					<div id="date_cost">
						<input type="checkbox" id="chkdate_cost" name="box" value="date"
							onclick="oper2()">消费日期 <input type="text"
							id="txl_date_cost" name="l_date" style="width: 100px;" disabled>&nbsp;&nbsp;至&nbsp;&nbsp;~
						<input type="text" id="txr_date_cost" name="r_date"
							style="width: 100px;" disabled>
					</div>
					<!-- <div id="lplace_cost">
					<input type="checkbox" id="chklplace_cost" name="lplace_cost" onclick="oper4()">消费场所
					<select id="slclplace_cost" name="slclplace_cost" style="color: gray;width: 90px;" disabled>
						<option value="">全&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;部</option>
						<option value="">123</option>
						<option value="">456</option>
					</select>
				</div> -->
					<div id="rplace_cost">
						<input type="checkbox" id="chkrplace_cost" name="box"
							value="place" onclick="oper5()">消费场所 <select
							id="slcrplace_cost" name="place" style="color: gray;width: 90px;"
							disabled>
							<option value="total_cost">全&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;部</option>
							<option value="front_cost">前台消费</option>
							<option value="res_cost">餐饮消费</option>
							<option value="bath_cost">洗浴消费</option>
							<option value="others_cost">其他消费</option>
						</select>
					</div>
					<!--<div id="lchoose" style="position:absolute;left: 59%;top: 66%;font-size: 13px;border:1px solid silver;padding: 2px;">
				    <form id="lform">
					    <label for="total" >汇总</label>
					    <input type="radio" name="lchoose">
					    <label for="detail">明细</label>
					    <input type="radio" name="lchoose">
					    <label for="saler">销售员</label>
					    <input type="radio" name="lchoose">
				        </form>
				</div>  -->

					<div id="rchoose"
						style="position:absolute;left: 78%;top: 66%;font-size:0.9em;border:1px solid silver;padding: 0.1%;">
						<form id="rform">
							<label for="all">全部</label> <input type="radio" name="reorder"
								value="" checked="checked"> <label for="hand">手工补单</label>
							<input type="radio" name="reorder" value="y"> <label
								for="pass">传帐</label> <input type="radio" name="reorder"
								value="n">
						</form>
					</div>
				</div>
				<div id="tright">
					<button id="button1" onclick="select()">查询</button>
					<button id="button2" onclick="getPrint()">打印预览</button>
					<button id="button3" type="button" onclick="pageClear()">清空</button>
					<button id="button4" type="button" onclick="getExcel()">EXCEL导出</button>
				</div>
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
							<th align="center" class="tabhead" width="240px">序号</th>
							<th align="center" class="tabhead" width="240px">客人姓名</th>
							<th align="center" class="tabhead" width="240px">客历编号</th>
							<th align="center" class="tabhead" width="240px">${place
								}消费金额</th>
							<th align="center" class="tabhead" width="240px">销售员</th>
							<th align="center" class="tabhead" width="240px">手工补单否</th>
						</tr>
					</thead>
					<tbody id="group_one">
						<c:forEach var="str" items="${list }">
							<tr>
								<td align="center" class="message">${str.num }</td>
								<td align="center" class="message">${str.name }</td>
								<td align="center" class="message">${str.klnumber }</td>
								<td align="center" class="message">${str.total_cost }</td>
								<td align="center" class="message">${str.saler }</td>
								<td align="center" class="message">${str.reorder }</td>
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
		</form>
	</div>
	${alter }
</body>
</html>
