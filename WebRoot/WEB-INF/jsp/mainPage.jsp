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

<title>My JSP 'mainPage.jsp' starting page</title>


<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/reset.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/address.css">

</head>

<body>
	<div id="main">
		<div id="top">
			<div id="logo">
				<p id="ruddy">4th Team</p>
			</div>
			<ul id="shang">
				<li id="li1"><a href="">客历管理</a>
				</li>
				<li id="li2"><a href="">会员查询</a>
				</li>
				<li id="li3"><a href="">合约公司管理</a>
				</li>
				<li id="li4"><a href="">应收单位查询</a>
				</li>
				<li id="li5"><a href="">客历关怀</a>
				</li>
				<li id="li6"><a href="javascript:void(0);">万能查询</a>
					<ul id="xiala">
						<form>
							<li class="xia1"><a
								href="${pageContext.request.contextPath }/servlet/KLUIServlet"
								id="a1">客历基本信息查询</a>
							</li>
							<li class="xia1"><a
								href="${pageContext.request.contextPath }/servlet/KLXFUIServlet"
								id="a1">客历消费查询</a>
							</li>
							<li class="xia1"><a
								href="${pageContext.request.contextPath }/servlet/HYGSUIServlet"
								" id="a1">合约公司基本信息查询</a>
							</li>
							<li class="xia1"><a href="" id="a1">合约公司消费查询</a>
							</li>
							<li class="xia1"><a href="" id="a1">合约公司所属客历关系</a>
							</li>
							<li class="xia1"><a href="" id="a1">客历分析</a>
							</li>
							<li class="xia1"><a href="" id="a1">客历消费分析</a>
							</li>
							<li class="xia1"><a href="" id="a1">合约消费分析</a>
							</li>
						</form>
					</ul></li>
				<li id="li7"><a href="">系统设置</a>
				</li>
				<li id="li8"><a href="">自定义报表</a>
				</li>
			</ul>
		</div>
		<div id="mtop">WE ARE</div>
		<div id="mbottom">Customer Relationship Management System</div>
		<div id="bottom">
			<ul id="xia">
				<li id="li9"><a href="">关于我们</a>
				</li>
				<li id="li10"><a href="${pageContext.request.contextPath }">退出系统</a>
				</li>
			</ul>
		</div>
	</div>
</body>
</html>
