package Dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entity.Order;
import entity.OrderItem;
import entity.Orderinfo;
import entity.Product;

public class OrderInfoDao {
	public static List<Orderinfo> findOrderInfoByUserId(int userId) {
		List<Orderinfo> orderInfoList = new ArrayList<Orderinfo>();
		// 1.根据用户id查询该用户的所有订单信息，查询orders表
		List<Order> orderList = OrderDao.findOrderByUserId(userId);
		//2.遍历每一个订单, 通过订单id查询当前订单中包含的所有订单项信息
		for (Order order : orderList) {
			String orderId = order.getId();
			//根据用户order_id查询该订单号的所有订单项信息，查询orderitem表
			List<OrderItem> orderItems = OrderItemDao.findOrderItemByOrderId(orderId);
			//3.遍历每一个订单项, 通过订单项获取商品信息及商品的购买数量
			Map<Product, Integer> map = new HashMap<Product, Integer>();
			for (OrderItem orderItem : orderItems) {
				//3.1.获取商品id, 通过商品id查询商品信息, 返回Product对象
				Product product = ProductDao.selectById(orderItem.getProduct_id());
				//3.2.将商品信息和购买数量存入map中
				map.put(product, orderItem.getBuynum());
			}
			//4.将订单信息和所有的订单项信息存入OrderInfo中
			Orderinfo orderInfo = new Orderinfo();
			orderInfo.setOrder(order);
			orderInfo.setMap(map);
			//5.将一个完整的订单信息存入List集合中
			orderInfoList.add(orderInfo);
		}
		return orderInfoList;
	}
}
