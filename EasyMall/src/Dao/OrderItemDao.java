package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entity.OrderItem;

public class OrderItemDao {
	public static void addOrderItem(OrderItem orderItem) {
		Connection conn=null;
		PreparedStatement ps=null;
		
		conn=Connect.getConnection();
		try {
			conn = Connect.getConnection();
			String sql = "insert into orderitem(order_id,product_id,buynum) values(?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, orderItem.getOrder_id());
			ps.setString(2, orderItem.getProduct_id());
			ps.setInt(3, orderItem.getBuynum());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Connect.close(conn, ps);
		}
	}
	public static List<OrderItem> findOrderItemByOrderId(String orderId) {
		List<OrderItem> orderItems = new ArrayList<OrderItem>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = Connect.getConnection();
			String sql = "select * from orderItem where order_id=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, orderId);
			rs = ps.executeQuery();
			while (rs.next()) {
				OrderItem orderItem = new OrderItem();
				orderItem.setOrder_id(orderId);
				orderItem.setProduct_id(rs.getString("product_id"));
				orderItem.setBuynum(rs.getInt("buynum"));
				orderItems.add(orderItem);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Connect.close(conn, ps, rs);
		}
		return orderItems;
	}
}
