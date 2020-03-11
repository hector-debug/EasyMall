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
		// �����������
				ServletContext context = this.getServletContext();
				String encode = context.getInitParameter("encode");
				request.setCharacterEncoding(encode);
				// �����Ӧ����
				response.setContentType("text/html;charset=" + encode);
				// 1.��ȡ��ǰ��½�û�
				User user = (User) request.getSession().getAttribute("user");
				if (user == null) {
					response.getWriter().write("<a href='"+request.getContextPath()+"/EasyMall/EasyMall/login.jsp'>���ȵ�¼��</a>");
				} else {
					// 2.��ȡ��������(����ȡ�����а�����Щ��Ʒ����Ʒ��������)���������ݷ�װ��JavaBean��
					Order order = new Order();
					order.setId(UUID.randomUUID().toString());
					order.setPaystate(0);// Ĭ����0��ʾδ֧��
					order.setReceiverinfo(request.getParameter("receiverinfo"));
					order.setUser_id(user.getId());
					/*
					 * 3.����List���ϱ���һ�������е����ж����� һ�������п��԰��������Ʒ, һ����Ʒ��Ӧһ��������(OrderItem),
					 * ��һ�������� �������������, �˴�������һ��list���ϴ��һ�������е����ж�����
					 */
					List<OrderItem> list = new ArrayList<OrderItem>();
					// 4.���㶩���ܽ�� ע��: ��Ҫ�����û��ύ����������!!!������ͨ������ó������ܽ��
					double totalMoney = 0;
					// >>��ȡ���ﳵmap
					Map<Product, Integer> map = (Map<Product, Integer>) request
							.getSession().getAttribute("cartmap");
					for (Map.Entry<Product, Integer> entry : map.entrySet()) {
						double price = entry.getKey().getPrice();// ��ǰ��Ʒ�ĵ���
						int buyNum = entry.getValue();// ��������
						totalMoney += price * buyNum;// ���㶩���ܽ��
						OrderItem item = new OrderItem();
						item.setOrder_id(order.getId());
						item.setProduct_id(entry.getKey().getId());
						item.setBuynum(buyNum);
						// �������е�ÿһ����Ʒ��ÿһ����������ӵ�һ��list�����б���
						list.add(item);
					}
					order.setMoney(totalMoney);// ������õĶ���������javabean��
					// 5.����addOrder������Ӷ���
					try {
						OrderDao.addOrder(order, list);
					} catch (MsgException e) {
						response.getWriter().write(
								"<h1 style='color:red;text-align:center'>"
										+ e.getMessage() + "</h1>");
						return;
					}
					// 6.������Ӻ���չ��ﳵ�е���Ʒ
					map.clear();
					// 7.��ӳɹ��ص������б�ҳ��
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
