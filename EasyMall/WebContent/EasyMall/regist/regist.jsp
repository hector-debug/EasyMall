<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML>
<html>
	<head>
		<title>欢迎注册EasyMall</title>
		<meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
		<%
    		String path = request.getContextPath();
    		String basePath = request.getScheme()+"://"
            +request.getServerName()+":"
            +request.getServerPort()+path+"/";
	%>

<base href="<%=basePath%>">
		<link rel="stylesheet" href="${app}EasyMall/regist/css/regist.css"/>
	</head>
	<body>
		<form action="RegisterServlet" method="POST">
			<h1>欢迎注册EasyMall</h1>
			<table>
				<tr>
					<td class="tds">用户名：</td>
					<td>
						<input type="text" name="username" value="zhangsan"/>
					</td>
				</tr>
				<tr>
					<td class="tds">密码：</td>
					<td>
						<input type="password" name="password"/>
					</td>
				</tr>
				<tr>
					<td class="tds">确认密码：</td>
					<td>
						<input type="password" name="password2"/>
					</td>
				</tr>
				<tr>
					<td class="tds">昵称：</td>
					<td>
						<input type="text" name="nickname" value="zhangsan"/>
					</td>
				</tr>
				<tr>
					<td class="tds">邮箱：</td>
					<td>
						<input type="text" name="email" value="a@163.com"/>
					</td>
				</tr>
				<tr>
					<td class="tds">验证码：</td>
					<td>
						<input type="text" name="valistr" value="<%=request.getParameter("valistr") == null?"":request.getParameter("valistr")%>"/>

						<img onclick="changeImage(this)" src="<%=request.getContextPath()%>/servlet/ValiImageServlet" />
					</td>
					<script>
						function changeImage(thisobj){
						thisobj.src = "<%= request.getContextPath() %>/servlet/ValiImageServlet?time="+new Date().getTime();
						}
					</script>
				</tr>
				<tr>
					<td class="sub_td" colspan="2" class="tds">
						<input type="submit" value="注册用户"/>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
