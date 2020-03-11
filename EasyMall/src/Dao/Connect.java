package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class Connect {
	private static Connection con;
	private static String dbDriver="com.mysql.jdbc.Driver";
	private static String dbUser="root";
	private static  String dbPsw="123456";
	private static String url="jdbc:mysql://localhost:3306/javaweb";
	public static Connection getConnection() {
		try {
			Class.forName(dbDriver);
			
			con=DriverManager.getConnection(url,dbUser,"");
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
	
	public static void close(Connection con,PreparedStatement pstmt) {
		
		try {
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void close(Connection conn,PreparedStatement pstmt,ResultSet rs){
		if(rs!=null){
			try{
				rs.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
			finally{
				rs=null;
			}
		}
		if(pstmt!=null){
			try{
				pstmt.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
			finally{
				pstmt=null;
			}
		}
		if(conn!=null){
			try{
				conn.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
			finally{
				conn=null;
			}
		}
	}
}
