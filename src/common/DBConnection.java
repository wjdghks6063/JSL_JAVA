package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnection {
	public static Connection getConnection(){
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("오라클 드라이버 없음~~~");
			e.printStackTrace();
		}
//		String DB_URL ="jdbc:oracle:thin:@localhost:1521:xe";
//		String DB_USER ="fstack2";
//		String DB_PASSWORD ="1234";
		
		String DB_URL ="jdbc:oracle:thin:@jsl505-000:1521:xe";
		String DB_USER ="fstack";
		String DB_PASSWORD ="1234";		
		
		Connection con = null;
		try {
			con =DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			if(con == null) {
				System.out.println(" 접속 오류~~~ 1");
			} else {
//				System.out.println(" 접속 성공~~~");
			}
		} catch (SQLException e) {
			System.out.println("접속 오류~~~ 2");
			e.printStackTrace();
		}	
		return con;
	}
	public static void closeDB(Connection con,
						PreparedStatement ps, 
						ResultSet rs){
		try {
			if(rs != null) rs.close();
			if(ps != null) ps.close();
			if(con != null) con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}











