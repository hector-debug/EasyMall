<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML>
<html>
	<head>
		<meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
		<%
    		String path = request.getContextPath();
    		String basePath = request.getScheme()+"://"
            +request.getServerName()+":"
            +request.getServerPort()+path+"/";
            System.out.println(basePath);

		%>

		<base href="<%=basePath%>">
		<link rel="stylesheet" href="${app}/EasyMall/login/css/login.css"/>
		<title>EasyMall欢迎您登陆</title>
	</head>
	<body>
	<%!
		String name;
		String pwd;
	%>
	<%
	try{
		Cookie[] cookies=request.getCookies();
		for(Cookie cookie:cookies){
			if(cookie.getName().equals("name")){
				name=cookie.getValue();
			}
			if(cookie.getName().equals("pwd"))
				pwd=cookie.getValue();
			
		}
		for(Cookie cookie:cookies){
			if(cookie.getName().equals("rename")){
				
				if(cookie.getValue().equals("")){
					name="";
					pwd="";
				}
				
			}
		}
		
		
	}catch(Exception e){
		
	}
		
		
	%>
		<h1>欢迎登陆EasyMall </h1>
		<form action="LoginServlet" method="POST">
			<table>
				<tr>
					<td class="tdx">用户名：</td>
					<td><input type="text" name="username" value="<%=(name==null?"":name)%>"/></td>
				</tr>
				<tr>
					<td class="tdx">密&nbsp;&nbsp; 码：</td>
					<td><input type="password" name="password" value="<%=(pwd==null?"":pwd)%>"/></td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="checkbox" name="remname" value="true"/>记住用户名和密码
						<input type="checkbox" name="autologin" value="true"/>30天内自动登陆
					</td>
				</tr>
				<tr>
					<td colspan="2" style="text-align:center">
						<input type="submit" value="登 陆"/>
					</td>
				</tr>
			</table>
		</form>		
	</body>
</html>
