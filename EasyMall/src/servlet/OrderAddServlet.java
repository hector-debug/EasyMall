package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.OrderDao;
import entity.Order;
import entity.OrderItem;
import entity.User;
import exception.MsgException;
import entity.Product;

/**
 * Servlet implementation class OrderAddServlet
 */
@WebServlet("/OrderAddServlet")
public class OrderAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderAddServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 解决请求乱码
				ServletContext context = this.getServletContext();
				String encode = context.getInitParameter("encode");
				request.setCharacterEncoding(encode);
				// 解决响应乱码
				response.setContentType("text/html;charset=" + encode);
				// 1.获取当前登陆用户
				User user = (User) request.getSession().getAttribute("user");
				if (user == null) {
					response.getWriter().write("<a href='"+request.getContextPath()+"/EasyMall/EasyMall/login.jsp'>请先登录！</a>");
				} else {
					// 2.获取订单数据(即获取订单中包含哪些商品和商品购买数量)，并将数据封装到JavaBean中
					Order order = new Order();
					order.setId(UUID.randomUUID().toString());
					order.setPaystate(0);// 默认是0表示未支付
					order.setReceiverinfo(request.getParameter("receiverinfo"));
					order.setUser_id(user.getId());
					/*
					 * 3.创建List集合保存一个订单中的所有订单项 一个订单中可以包含多个商品, 一个商品对应一个订单项(OrderItem),
					 * 即一个订单中 包含多个订单项, 此处可以用一个list集合存放一个订单中的所有订单项
					 */
					List<OrderItem> list = new ArrayList<OrderItem>();
					// 4.计算订单总金额 注意: 不要轻信用户提交过来的数据!!!在下面通过计算得出订单总金额
					double totalMoney = 0;
					// >>获取购物车map
					Map<Product, Integer> map = (Map<Product, Integer>) request
							.getSession().getAttribute("cartmap");
					for (Map.Entry<Product, Integer> entry : map.entrySet()) {
						double price = entry.getKey().getPrice();// 当前商品的单价
						int buyNum = entry.getValue();// 购买数量
						totalMoney += price * buyNum;// 计算订单总金额
						OrderItem item = new OrderItem();
						item.setOrder_id(order.getId());
						item.setProduct_id(entry.getKey().getId());
						item.setBuynum(buyNum);
						// 将订单中的每一个商品即每一个订单项添加到一个list集合中保存
						list.add(item);
					}
					order.setMoney(totalMoney);// 将计算好的订单金额存入javabean中
					// 5.调用addOrder方法添加订单
					try {
						OrderDao.addOrder(order, list);
					} catch (MsgException e) {
						response.getWriter().write(
								"<h1 style='color:red;text-align:center'>"
										+ e.getMessage() + "</h1>");
						return;
					}
					// 6.订单添加后清空购物车中的商品
					map.clear();
					// 7.添加成功回到订单列表页面
					response.sendRedirect(request.getContextPath()
							+  "/OrderListServlet");
	}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
