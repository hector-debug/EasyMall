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
		// 1.�����û�id��ѯ���û������ж�����Ϣ����ѯorders��
		List<Order> orderList = OrderDao.findOrderByUserId(userId);
		//2.����ÿһ������, ͨ������id��ѯ��ǰ�����а��������ж�������Ϣ
		for (Order order : orderList) {
			String orderId = order.getId();
			//�����û�order_id��ѯ�ö����ŵ����ж�������Ϣ����ѯorderitem��
			List<OrderItem> orderItems = OrderItemDao.findOrderItemByOrderId(orderId);
			//3.����ÿһ��������, ͨ���������ȡ��Ʒ��Ϣ����Ʒ�Ĺ�������
			Map<Product, Integer> map = new HashMap<Product, Integer>();
			for (OrderItem orderItem : orderItems) {
				//3.1.��ȡ��Ʒid, ͨ����Ʒid��ѯ��Ʒ��Ϣ, ����Product����
				Product product = ProductDao.selectById(orderItem.getProduct_id());
				//3.2.����Ʒ��Ϣ�͹�����������map��
				map.put(product, orderItem.getBuynum());
			}
			//4.��������Ϣ�����еĶ�������Ϣ����OrderInfo��
			Orderinfo orderInfo = new Orderinfo();
			orderInfo.setOrder(order);
			orderInfo.setMap(map);
			//5.��һ�������Ķ�����Ϣ����List������
			orderInfoList.add(orderInfo);
		}
		return orderInfoList;
	}
}
