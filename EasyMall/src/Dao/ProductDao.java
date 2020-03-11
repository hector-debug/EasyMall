package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



import entity.Product;

public class ProductDao {
	
	
	public static List<Product> selectprod(String name,String category,double minprice,double maxprice) {
		List<Product> list=new ArrayList<Product>();
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		con=Connect.getConnection();
//		String sql= "select * from products where "
//				+ "name like '% ? %' and category like '% ? %' and price >= ? and price <= ?";
		
		String sql = "select * from products where name like '%" + name
				+ "%' and category like '%" + category
				+ "%' and price >= " + minprice + " and price <= "
				+ maxprice + "";
		try {
			
			pstmt=con.prepareStatement(sql);
//			pstmt.setString(1, name);
//			pstmt.setString(2, category);
//			System.out.println("here1");
//			pstmt.setDouble(3, minprice);
//			System.out.println("here2");
//			pstmt.setDouble(4, maxprice);
//			System.out.println("here");
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				Product p = new Product();
				p.setId(rs.getString("id"));
				p.setName(rs.getString("name"));
				p.setCategory(rs.getString("category"));
				p.setDescription(rs.getString("description"));
				p.setPrice(rs.getDouble("price"));
				p.setPnum(rs.getInt("pnum"));
				p.setImgurl(rs.getString("imgurl"));
				// System.out.println(p);
				list.add(p);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
			return list;
		}
	}
	
	public static Product selectById(String id) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		Product product=new Product();
		
		con=Connect.getConnection();
		String sql = "select * from products where id = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,id);
			rs = pstmt.executeQuery();
			if(rs.next()){
				product.setId(rs.getString("id"));
				product.setName(rs.getString("name"));
				product.setCategory(rs.getString("category"));
				product.setDescription(rs.getString("description"));
				product.setPnum(rs.getInt("pnum"));
				product.setPrice(rs.getDouble("price"));
				product.setImgurl(rs.getString("imgurl"));				
			}
			return product;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return product;
		}
	}
	
	public static void updatePnum(String pid, int num) {
		Connection conn=null;
		PreparedStatement ps=null;
		
		conn=Connect.getConnection();
		try {
			conn = Connect.getConnection();
			String sql = "update products set pnum = ? where id = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, num);
			ps.setString(2, pid);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Connect.close(conn, ps);
		}
	}
}
