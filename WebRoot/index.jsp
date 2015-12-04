<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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

<title>My JSP 'logINPage.jsp' starting page</title>

<title>Document</title>
<link rel="stylesheet" href="./css/reset.css">
<link rel="stylesheet" href="./css/index.css">
</head>

<body>
	<div id="container">
		<div id="main">
			<p id="p1">Login Form</p>
			<p id="p2">
				Fill out the form blow to login to <br>the system
			</p>
			<p id="errors">
				<br> ${form.errors.error}
			</p>
			<a id="link">Register</a>
			<form
				action="${pageContext.request.contextPath }/servlet/LoginServlet"
				method="post">
				<input id="Username" type="text" name="id" value="Username"
					style="color:gray;"
					onfocus="if(this.value=='Username'){this.value='';password.value='';}this.style.color='black';"
					onblur="if(this.value==''){this.value='Username';password.value='password'}this.style.color='gray';">
				<input id="password" type="password" name="password"
					value="password" style="color:gray;"
					onfocus="if(this.value=='password'){this.value='';}this.style.color='black';"
					onblur="if(this.value==''){this.value='password';}this.style.color='gray';">
				<input id="login" type="submit" name="sign in" value="sign in"><br>
			</form>
		</div>
		<div id="ltop"></div>
		<div id="lbottom"></div>
	</div>
</body>
</html>
