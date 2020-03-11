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
		// �����������
				ServletContext context = this.getServletContext();
				String encode = context.getInitParameter("encode");
				request.setCharacterEncoding(encode);
				// �����Ӧ����
				response.setContentType("text/html;charset=" + encode);
				// 1.��ȡ��ǰ��½�û�
				User user = (User) request.getSession().getAttribute("user");
				if (user == null) {
					response.getWriter().write(
							"<a href='" + request.getContextPath()
									+ "/login.jsp'>���ȵ�¼��</a>");
				} else {
					// 2.�����û�id��ѯ���û������ж�����Ϣ
					List<Orderinfo> orderInfoList = OrderInfoDao.findOrderInfoByUserId(user.getId());
					// 3.�����û������ж�����Ϣ��list���ϴ���request����, ת����order_list.jsp����ʾ
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
