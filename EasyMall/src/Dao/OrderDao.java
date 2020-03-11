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
				// 检查购买数量(orderItem.buyNum)是否小于等于库存数量(Product.pnum)
				// 获取购买数量
				int buyNum = orderItem.getBuynum();
				// 获取库存数量
				// >>获取商品id
				String pid = orderItem.getProduct_id();
				// >>查询商品信息
				Product prod = ProductDao.selectById(pid);
				int pnum = prod.getPnum();
				if (buyNum > pnum) {
					// 如果购买数量大于库存数量
					throw new MsgException("库存数量不足, 订单添加失败!");
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
