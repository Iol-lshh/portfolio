package main.java.com.view;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import main.java.com.model.DB;

public class TOT_Post_DAO {

	
	Connection conn = null;
	PreparedStatement psmt = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	
	public static final String SQL_SELETE_TOT_TABLE= 
				"SELECT * FROM tot_post_view WHERE post_id = ?";

	
	
	public TOT_Post_DAO() {

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
	

	public TOT_Post_DTO[] tot_table_select(int post_id) {
		int cnt = 0;
		TOT_Post_DTO[] arr = null;
		try {
			psmt = conn.prepareStatement(SQL_SELETE_TOT_TABLE);
			psmt.setInt(1, post_id);
			rs = psmt.executeQuery();
			arr = createTOT(rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return arr;
	}
	
	public TOT_Post_DTO[] createTOT(ResultSet rs) throws SQLException {
		ArrayList<TOT_Post_DTO> TOTlist = new ArrayList<TOT_Post_DTO>();
		
		while(rs.next()) {

			int post_id = rs.getInt("post_id");
			String regdate = rs.getString("regdate");
			int empathize_cnt = rs.getInt("empathize_cnt");
			int view_cnt = rs.getInt("view_cnt");
			TOT_Post_DTO dto = new TOT_Post_DTO(post_id, regdate, empathize_cnt, view_cnt);
			TOTlist.add(dto);
			
		}
		
		int size = TOTlist.size();
		if(size == 0 ) return null;
		
		TOT_Post_DTO[] arr = new TOT_Post_DTO[size];
		TOTlist.toArray(arr);
		return arr;
	}
	
	
	
	
	
	
	
	
	
	
	
}
