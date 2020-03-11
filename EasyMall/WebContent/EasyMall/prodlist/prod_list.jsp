<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML>
<html>
<head>
	<meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
	<link href="${app}/EasyMall/prodlist/css/prodList.css" rel="stylesheet" type="text/css">
		<%
    		String path = request.getContextPath();
    		String basePath = request.getScheme()+"://"
            +request.getServerName()+":"
            +request.getServerPort()+path+"/";
          

		%>
	
</head>
<body>

	<%@ include file = "../head/_head.jsp" %>
	<div id="content">
		<div id="search_div">
			<form method="post" action="prodlist">
				<span class="input_span">商品名：<input type="text" name="name"/></span>
				<span class="input_span">商品种类：<input type="text" name="category"/></span>
				<span class="input_span">商品价格区间：<input type="text" name="minprice"/> - <input type="text" name="maxprice"/></span>
				<input type="submit" value="查 询">
			</form>
		</div>
		
		
		<div id="prod_content">
		<c:forEach items="${list}" var="prod">
		
			<div class="prod_div">
				<a href="prodinfoServlet?pid=${prod.id}">
				<img src="prodImgServlet?imgurl=${prod.imgurl}"></img>
				</a>
				<div id="prod_name_div">
					${prod.name}
				</div>
				<div id="prod_price_div">
					￥${prod.price}
				</div>
				<div>
					<div id="gotocart_div">
						<a href="CartAddServlet?pid=${prod.id}&buyNum=1">加入购物车</a>
					</div>					
					<div id="say_div">
						133人评价
					</div>					
				</div>
			</div>
			</c:forEach>
			
			<div style="clear: both"></div>
		</div>
		
		
		
		<%@ include file = "../foot/_foot.jsp" %>
	</div>
	
</body>
</html>
