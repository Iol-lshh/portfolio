package main.java.com.view;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import main.java.com.model.DB;

public class recomendDAO {

	
	Connection conn = null;
	PreparedStatement psmt = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	
	public static final String SQL_INSERT_EMPATHIZE = 
			"INSERT INTO empathize_table(post_id,mm_id) "
			+"VALUES (?, ?)";
	
	public static final String SQL_DELETE_EMPATHIZE = 
			"DELETE EMPATHIZE_TABLE WHERE post_id = ? AND mm_id =?";
	
	public static final String SQL_SELECT_EMPATHIZE = 
			"SELECT * FROM EMPATHIZE_TABLE et WHERE post_id =? AND MM_ID =? ";

	
	
	public recomendDAO() {

		try {
			Class.forName(DB.DRIVER);
			conn = DriverManager.getConnection(DB.URL, DB.USERID, DB.USERPW);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public void close() throws SQLException {
		if(rs != null) rs.close();
		if(psmt != null) psmt.close();
		if(stmt != null) stmt.close();
		if(conn != null) conn.close();
		
	} // end close()
	

	public int empathize_select(int post_id, int mm_id) {
		int cnt = 0;
		
		try {
			psmt = conn.prepareStatement(SQL_SELECT_EMPATHIZE);
			psmt.setInt(1, post_id);
			psmt.setInt(2, mm_id);
			cnt = psmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cnt ;
	}
	
	
	
	
	
	
	public int empathize_insert(int post_id, int mm_id) {
		int cnt = 0;
		
		try {
			psmt = conn.prepareStatement(SQL_INSERT_EMPATHIZE);
			psmt.setInt(1, post_id);
			psmt.setInt(2, mm_id);
			cnt = psmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cnt ;
	}
	
	public int empathize_delete(int post_id, int mm_id) {
		int cnt = 0;
		try {
			psmt = conn.prepareStatement(SQL_DELETE_EMPATHIZE);
			psmt.setInt(1, post_id);
			psmt.setInt(2, mm_id);
			cnt = psmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cnt ;
	}
	
	
	
	
	
	
	
	
	
}
