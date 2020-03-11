<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML>
<html>
	<head>
		<meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
		<link href="${app }/EasyMall/cart/css/cart.css" rel="stylesheet" type="text/css">
		<!-- 引入jQuery函数库 -->
		<script type="text/javascript" src="${app}/js/jquery-1.4.2.js"></script>		
	</head>
<script type="text/javascript">
/* 文档就绪函数 */
$(function(){
/* 为"减号"绑定点击事件, 点击"减号"将购买数量输入框中的值减1
 * 同时利用ajax将session中cartmap中的对应商品的数量减1
 * 重新计算总金额(包括当前商品的总价和所有商品总价的和)
*/
		$(".delNum").click(function() {
			//1.点击"减号"将购买数量输入框中的值减1
			//>>获取输入框
			var $buyNumInp = $(this).siblings("input")//input输入框
			
			var buyNum = $buyNumInp.val();
			var newBuyNum = buyNum;
			if (buyNum > 1) {//购买数量必须要大于等于1
				newBuyNum = buyNum - 1;
				$buyNumInp.val(newBuyNum);
				
				
			}
			
			//2.利用ajax将session中cartmap中的对应商品的购买数量减1
			//>>获取商品的id和购买数量(newBuyNum)
			var pid = $buyNumInp.attr("id");
			
			//>>发送ajax请求
			$.post("UpdateBuyNum", {
				"pid" : pid,
				"buyNum" : newBuyNum
			});
			//3.重新计算总金额(包括当前商品的总价和所有商品总价的和)
			totalMoney(this, newBuyNum);
		});
/* 重新计算总金额 */
		function totalMoney(thisobj, newBuyNum) {
			//1.获取当前商品的单价
			var price = $(thisobj).parents("ul").find(".li_price").text();
			//2.计算当前商品的总价
			var prod_sum = price * newBuyNum;
			$(thisobj).parents("ul").find(".sum_price").text(prod_sum);
			//4.计算购物车所有商品的总价
			var totalMoney = 0;
			$(".sum_price").each(function() {
				totalMoney += parseFloat($(this).text());
			});
			//设置所有商品的总价
			$("#span_2").text(totalMoney);
		}
/* 为"加号"绑定点击事件, 点击"加号"将购买数量输入框中的值加1
 * 同时利用ajax将session中cartmap中的对应商品的数量加1
 * 重新计算总金额(包括当前商品的总价和所有商品总价的和)
 */
		$(".addNum").click(function() {
			//1.点击"加号"将购买数量输入框中的值加1
			//>>获取输入框
			var $buyNumInp = $(this).siblings("input")//input输入框
			var buyNum = $buyNumInp.val();
			var newBuyNum = parseInt(buyNum) + 1;
			$buyNumInp.val(newBuyNum);

			//2.利用ajax将session中cartmap中的对应商品的购买数量减1
			//>>获取商品的id和购买数量(newBuyNum)
			var pid = $buyNumInp.attr("id");
			//>>发送ajax请求
			$.post("UpdateBuyNum", {
				"pid" : pid,
				"buyNum" : newBuyNum
			});

			//3.重新计算总金额(包括当前商品的总价和所有商品总价的和)
			totalMoney(this, newBuyNum);
		});
		
//	3)设置全选和全不选
		/* 设置全选和全不选 */
		$(".allC").click(function() {
			$(".prodC").attr("checked", $(this).attr("checked"));
			$(".allC").attr("checked", $(this).attr("checked"));
		});	
		
/* 删除当前商品 
* 给每一个商品后面的删除按钮添加点击事件, 
* 点击"删除"删除当前商品(通过也要删除购物车中的商品)
*/
		$(".delProd").click(function(){
			//1.获取商品的id
			var pid = $(this).attr("id");
					
			//2.利用ajax请求删除购物车中指定id的商品(-1后台会删除该商品)
			$.post("CartAddServlet",{"pid":pid, "buyNum":-1});			
			//3.删除当前页面中的商品
			$(this).parents("ul").remove();
			//4.重新计算购物车中商品的总价
			var totalMoney = 0;
			$(".sum_price").each(function(){
				totalMoney += parseFloat($(this).text());
			});				
			//设置所有商品的总价
			$("#span_2").text(totalMoney);
		});
			
});	
</script>
	<body>
	
	<%@ include file = "../head/_head.jsp" %>
		<div id="wrap">
			<!-- 标题信息 -->
			<ul id="title">
				<li>
					<input class="allC" name="allC" type="checkbox"/>
					<span id="title_checkall_text">全选</span>
				</li>
				<li class="li_prod">商品</li>
				<li>单价（元）</li>
				<li>数量</li>
				<li>小计（元）</li>
				<li>操作</li>
			</ul>

			<!-- 购物信息 -->
			<c:set var="totalManey" value="0"/>
			<c:forEach items="${ cartmap }" var="entry">
			
			<ul class="prods">
				<li>
					<input type="checkbox" class="prodC"/> 
				</li>
				<li class="li_prod">
					<img src="prodImgServlet?imgurl=${entry.key.imgurl }" width="90" height="90" class="prodimg" />
					<span class="prodname">${entry.key.name }</span>
				</li>
				<li class="li_price">${entry.key.price }</li>
				<li>
					<a href="javascript:void(0)" class="delNum"  id="delNum">-</a>
					<input id="${ entry.key.id }" class="buyNumInp" type="text" readonly="readonly" value="${entry.value }" />
					<a href="javascript:void(0)" class="addNum" id="addNum">+</a>
				</li>
				<li class="sum_price">${entry.key.price*entry.value }</li>
				<li><a id="${entry.key.id}" class="delProd" href="javascript:void(0)">删除</a></li>
			</ul>
			<c:set var="totalManey" value="${ totalManey + entry.key.price * entry.value }" />
			
			</c:forEach>
			<!-- 总计条 -->
			<div id="total">
				<div id="total_1">
					<input type="checkbox" class="allC" name="allC"/> 
					<span>全选</span>
					<a id="del_a" href="javascript:void(0)">删除选中的商品</a>
					<div id="div_sum">
						<span id="span_1">总价：</span>
						<span>￥</span>
						<span id="span_2" class="total_sum_price">${totalManey}</span>&nbsp;&nbsp;&nbsp;
					</div>
				</div>
				<div id="total_2">	
					<a id="goto_order" href="payServlet">去结算</a>
				</div>
			</div>
		</div>
	<%@ include file = "../foot/_foot.jsp" %>
	</body>
</html>