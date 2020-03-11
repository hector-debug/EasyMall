package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.OrderInfoDao;
import entity.Orderinfo;
import entity.User;

/**
 * Servlet implementation class OrderListServlet
 */
@WebServlet("/OrderListServlet")
public class OrderListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderListServlet() {
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
					response.getWriter().write(
							"<a href='" + request.getContextPath()
									+ "/login.jsp'>请先登录！</a>");
				} else {
					// 2.根据用户id查询该用户的所有订单信息
					List<Orderinfo> orderInfoList = OrderInfoDao.findOrderInfoByUserId(user.getId());
					// 3.将该用户的所有订单信息的list集合存入request域中, 转发到order_list.jsp中显示
					request.setAttribute("list", orderInfoList);
					request.getRequestDispatcher("EasyMall/orderlist/order_list.jsp").forward(request,
							response);
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
