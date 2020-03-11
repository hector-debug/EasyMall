package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entity.Order;
import entity.OrderItem;
import entity.Product;
import exception.MsgException;

public class OrderDao {
	public static  void addOrder(Order order, List<OrderItem> list) throws MsgException {
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs = null;
		conn=Connect.getConnection();
		try {
			
			String sql = "insert into orders(id,money,receiverinfo,paystate,user_id) values(?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, order.getId());
			ps.setDouble(2, order.getMoney());
			ps.setString(3, order.getReceiverinfo());
			ps.setInt(4, order.getPaystate());
			ps.setInt(5, order.getUser_id());
			ps.executeUpdate();
			Connect.close(conn, ps,rs);
			for (OrderItem orderItem : list) {
				// ��鹺������(orderItem.buyNum)�Ƿ�С�ڵ��ڿ������(Product.pnum)
				// ��ȡ��������
				int buyNum = orderItem.getBuynum();
				// ��ȡ�������
				// >>��ȡ��Ʒid
				String pid = orderItem.getProduct_id();
				// >>��ѯ��Ʒ��Ϣ
				Product prod = ProductDao.selectById(pid);
				int pnum = prod.getPnum();
				if (buyNum > pnum) {
					// ��������������ڿ������
					throw new MsgException("�����������, �������ʧ��!");
				}
				OrderItemDao.addOrderItem(orderItem);
				ProductDao.updatePnum(pid, prod.getPnum() - buyNum);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static List<Order> findOrderByUserId(int userId) {
		List<Order> orderList = new ArrayList<Order>();

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn=Connect.getConnection();
			String sql = "select * from orders where user_id=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, userId);
			rs = ps.executeQuery();
			while (rs.next()) {
				Order order = new Order();
				order.setId(rs.getString("id"));
				order.setMoney(rs.getDouble("money"));
				order.setReceiverinfo(rs.getString("receiverinfo"));
				order.setPaystate(rs.getInt("paystate"));
				order.setUser_id(rs.getInt("user_id"));
				order.setOrdertime(rs.getTimestamp("ordertime"));
				orderList.add(order);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Connect.close(conn, ps, rs);
		}
		return orderList;
	}
}
