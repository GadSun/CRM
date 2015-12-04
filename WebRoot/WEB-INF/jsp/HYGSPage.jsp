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

<title>My JSP 'HYGSPage.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/hycompany.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/jquery/jquery-ui.min.css">
<script
	src="${pageContext.request.contextPath }/jquery/external/jquery/jquery.js"></script>
<script
	src="${pageContext.request.contextPath }/jquery/jquery-ui.min.js"></script>
<script>
	$(document).ready(function() {
		$("#txlbedate").datepicker({
			dateFormat : 'yy-mm-dd'
		});
		$("#txrbedate").datepicker({
			dateFormat : 'yy-mm-dd'
		});
	})
</script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/hycompany.js"></script>
</head>

<body>
${dfpe.get(0).id }
	<div id="main">
		<p id="zs">总计：${list.size() }</p>
		<p id="zj">总消费额：${total.total }</p>
		<p id="qt">前台消费合计：${total.front }</p>
		<p id="cy">餐饮消费合计：${total.res }</p>
		<p id="xy">洗浴消费合计：${total.bath }</p>
		<p id="ktv">KTV消费合计：${total.other }</p>

		<form name="form1">
			<input id="path" type="hidden"
				value="${pageContext.request.contextPath }">
			<div id="top">
				<div id="tleft">
					<div id="codelegate" style="position: absolute;left:1%;top:15%;">
						<input type="checkbox" id="chkcodelegate" name="box"
							value="codelegate" onclick="oper()">合约代表 <input
							type="text" id="txcodelegate" name="codelegate"
							style="width: 100px;" disabled>
					</div>
					<div id="pinyin" style="position: absolute;left:1%;top:55%;">
						<input type="checkbox" id="chkpinyin" name="box" value="pinyin"
							onclick="oper1()">合约简拼 <input type="text" id="txpinyin"
							name="pinyin" style="width: 100px;" disabled>
					</div>
					<div id="saler" style="position: absolute;left:22%;top:15%;">
						<input type="checkbox" id="chksaler" name="box" value="saler"
							onclick="oper5()">销&nbsp;&nbsp;售&nbsp;&nbsp;员 <select
							id="slcsaler" name="saler" style="color:gray;width: 105px;"
							disabled>
							<option value="">全&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;部</option>
							<c:forEach var="saler" items="${listForSaler }">
								<option value="${saler }">${saler }</option>
							</c:forEach>
						</select>
					</div>
					<div id="cotype" style="position: absolute;left:22%;top:55%;">
						<input type="checkbox" id="chkcotype" name="box" value="cotype"
							onclick="oper6()">合约类型 <select id="slccotype"
							name="cotype" style="color:gray;width: 105px;" disabled>
							<option value="">全&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;部</option>
							<c:forEach var="cotype" items="${listForCotype }">
								<option value="${cotype }">${cotype }</option>
							</c:forEach>
						</select>
					</div>
					<div id="bedate" style="position: absolute;left:43%;top:15%;">
						<input type="checkbox" id="chkbedate" name="box" value="bedate"
							onclick="oper2()">签约日期 <input type="text" id="txlbedate"
							name="l_date" style="width: 90px;" disabled>&nbsp;&nbsp;&nbsp;至&nbsp;&nbsp;&nbsp;
						<input type="text" id="txrbedate" name="r_date"
							style="width: 90px;" disabled>
					</div>
					<div id="total_cost" style="position: absolute;left:43%;top:55%;">
						<input type="checkbox" id="chktotal_cost" name="box"
							value="total_cost" onclick="oper3()">消费合计 <input
							type="text" id="txltotal_cost" name="l_total_cost"
							style="width: 90px;" disabled>&nbsp;&nbsp;元&nbsp;&nbsp;~
						<input type="text" id="txrtotal_cost" name="r_total_cost"
							style="width: 90px;" disabled>
					</div>
					<div id="tel" style="position: absolute;left:78%;top:15%;">
						<input type="checkbox" id="chktel" name="box" value="tel"
							onclick="oper4()">联系电话 <input type="text" id="txtel"
							name="tel" style="width: 100px;" disabled>
					</div>
					<div id="remark" style="position: absolute;left:78%;top:55%;">
						<input type="checkbox" id="chkremark" name="box" value="remark">备注
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
				<table id="table1" border="1px" cellspacing="0px"
					style="font-size:0.8em;">
					<caption id="p1">搜索结果:</caption>
					<thead>
						<tr>
							<th align="center" class="tabhead" width="2%">序号</th>
							<th align="center" class="tabhead" width="5.5%">合约编号</th>
							<th align="center" class="tabhead" width="5.5%">合约类型</th>
							<th align="center" class="tabhead" width="5%">合约名称</th>
							<th align="center" class="tabhead" width="3.5%">合约卡片</th>
							<th align="center" class="tabhead" width="5%">联系电话</th>
							<th align="center" class="tabhead" width="5%">签约日期</th>
							<th align="center" class="tabhead" width="5%">终止日期</th>
							<th align="center" class="tabhead" width="5%">前台消费额</th>
							<th align="center" class="tabhead" width="5%">餐饮消费额</th>
							<th align="center" class="tabhead" width="5%">洗浴消费额</th>
							<th align="center" class="tabhead" width="5%">KTV消费额</th>
							<th align="center" class="tabhead" width="5%">消费合计</th>
							<th align="center" class="tabhead" width="4%">合约协议价</th>
							<th align="center" class="tabhead" width="3.5%">订房配额</th>
							<th align="center" class="tabhead" width="5%">合约代表</th>
							<th align="center" class="tabhead" width="5%">公司传真</th>
							<th align="center" class="tabhead" width="5%">销售员</th>
							<th align="center" class="tabhead" width="2%">折扣</th>
							<th align="center" class="tabhead" width="5%">应收账号</th>
							<th align="center" class="tabhead" width="5%">备注</th>
						</tr>
					</thead>
					<tbody id="group_one">
						<c:forEach var="str" items="${list }">
							<tr>
								<td align="left" class="message">${str.num }</td>
								<td align="left" class="message">${str.id }</td>
								<td align="left" class="message">${str.cotype }</td>
								<td align="left" class="message">${str.coname }</td>
								<td id="hycard" align="center" class="message"
									onclick="point('${str.id }')">*</td>
								<td align="left" class="message">${str.tel }</td>
								<td align="left" class="message">${str.bedate }</td>
								<td align="left" class="message">${str.stdate }</td>
								<td align="left" class="message">${str.front_cost }</td>
								<td align="left" class="message">${str.res_cost }</td>
								<td align="left" class="message">${str.bath_cost }</td>
								<td align="left" class="message">${str.ktv_cost }</td>
								<td align="left" class="message">${str.total_cost }</td>
								<td id="hycost" align="center" class="message"
									onclick="point1('${str.id }')">*</td>
								<td id="roomcost" align="center" class="message"
									onclick="point2('${str.id }')">*</td>
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
				<table id="jump"
					style="position: absolute;left:87%;top:94%;font-size: 12px;">
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
			<div id="mb" onclick="quit()"></div>
			<div id="tbhycard">
				<table id="cardtable" border="1px" cellspacing="0px" style="position: absolute;top: 4%;left:2.3%;font-size: 1.0em;" width="90%">
					<caption>合约卡片信息:</caption>
					<tbody></tbody>
				</table>
			</div>
			<div id="tbhycost">
				<table id="costtable" border="1px" cellspacing="0px"
					style="position: absolute;top: 4%;left:0%;font-size: 0.8em;">
					<caption>合约协议价:</caption>
					<thead>
						<tr>
							<th align="center" class="cardth" width="80px">合约编号</th>
							<th align="center" class="cardth" width="150px">二级类别</th>
							<th align="center" class="cardth" width="150px">一级类别</th>
							<th align="center" class="cardth" width="150px">折扣标准</th>
							<th align="center" class="cardth" width="150px">折扣金额</th>
							<th align="center" class="cardth" width="150px">消费名称</th>
							<th align="center" class="cardth" width="120px">场所名</th>
							<th align="center" class="cardth" width="150px">酒店名称</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>
			<div id="tbroomcost">
				<table id="costtable" border="1px" cellspacing="0px"
					style="position: absolute;top: 4%;left:0%;font-size: 0.8em;">
					<caption>订房配额:</caption>
					<thead>
						<tr>
							<th align="center" class="cardth" width="80px">合约编号</th>
							<th align="center" class="cardth" width="150px">二级类别</th>
							<th align="center" class="cardth" width="150px">一级类别</th>
							<th align="center" class="cardth" width="150px">房间类型</th>
							<th align="center" class="cardth" width="150px">房价</th>
							<th align="center" class="cardth" width="150px">折扣标准</th>
							<th align="center" class="cardth" width="120px">折扣金额</th>
							<th align="center" class="cardth" width="150px">场所名</th>
							<th align="center" class="cardth" width="150px">酒店名</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>
		</form>
	</div>
	${alter }
</body>
</html>
