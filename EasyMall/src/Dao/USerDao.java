package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.User;

public class USerDao {
	public static boolean addUSer(User user) {
		Connection con=null;
		PreparedStatement pstmt=null;
		
		con=Connect.getConnection();
		String sql="insert into user value(?,?,?,?,?)";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, 0);
			pstmt.setString(2, user.getUsername());
			pstmt.setString(3, user.getPassword());
			
			pstmt.setString(4, user.getNickname());
			pstmt.setString(5, user.getEmail());
			int count =pstmt.executeUpdate();
			Connect.close(con, pstmt);
			if (count>0)
				return true;
			else 
				return false;
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}	
		
	}
	public static User selectUserbyName(String name) {
		User user=null;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rst=null;
		
		con=Connect.getConnection();
		String sql="select * from user where username="+"?";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, name);
			//int count =0;
			rst=pstmt.executeQuery();
			if(rst.next()) {
				int id=rst.getInt("id");
				String username =rst.getString("username");
				String password = rst.getString("password");
				String nickname = rst.getString("nickname");
				String email = rst.getString("email");
				user =new User(id, username, password, nickname, email);
			}
			Connect.close(con, pstmt);
			return user;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return user;
		}
		
	}
	public static  User selectbynameandpwd(String name,String pwd) {
		User user=null;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rst=null;
		
		con=Connect.getConnection();
		String sql="select * from user where username="+"?"+"and password="+"?";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, pwd);
			//int count =0;
			rst=pstmt.executeQuery();
			if(rst.next()) {
				int id=rst.getInt("id");
				String username =rst.getString("username");
				String password = rst.getString("password");
				String nickname = rst.getString("nickname");
				String email = rst.getString("email");
				user =new User(id, username, password, nickname, email);
			}
			Connect.close(con, pstmt);
			return user;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return user;
		}
	}
	
}
