package main.java.com.model.post;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import main.java.com.model.DAO;
import main.java.com.model.DB;
import main.java.com.model.DTO;

public class WriteDAO  {
	
	Connection conn = null;
	PreparedStatement psmt = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	
	public static final String SQL_WRITE_SEARCH_BY_UID =
			"SELECT * FROM POST_TABLE pt WHERE POST_ID  = ?";
	
	public static final String SQL_WRITE_UPDATE_DATA = 
			"UPDATE POST_TABLE SET title = ?, CATEGORY = ? WHERE post_id= ?";

	public static final String SQL_MM_TABLE_SELECT = 
			"SELECT * FROM MM_TABLE mt WHERE MM_ID = ?";
	
	public static final String SQL_WRITE_INSERT =
					"INSERT INTO post_table"
					+"(post_id,title,writer,category,regdate,post_contents,viewCnt) "
					+"VALUES"
					+"(SEQ_post_table_post_id.NEXTVAL,?,?,?,SYSTIMESTAMP,?,0)";

	public static final String View_CNT_ADD =
				"UPDATE post_table SET viewCnt=viewCnt+1 WHERE post_id = ?";
			
	public static final String SELETE_POST_ID=
				"SELECT  * FROM POST_TABLE pt WHERE POST_ID = ?";
	
	
	public WriteDAO() {
		
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
	

	
	
	
	
	//글쓰기
	public int wr_insert(WriteDTO dto, int currval) throws SQLException {
		int cnt =0;
		
		String title = dto.getTitle(); //제목 
		int writer = dto.getWriter(); // 글쓴이
		String category = dto.getCategory(); // 카테고리 
		int currvaldata = currval;
		
		cnt = this.wr_insert(title, category, writer ,currvaldata);
		return cnt;
	}
	
	public int wr_insert(String title, String category, int writer ,int currvaldata) throws SQLException {
		int cnt = 0;
	
		
		try {
			psmt = conn.prepareStatement(SQL_WRITE_INSERT);
			psmt.setString(1, title);
			psmt.setInt(2, writer);
			psmt.setString(3, category);
			psmt.setInt(4, currvaldata);
			cnt = psmt.executeUpdate();
		} finally {
			close();
		}
		
		return cnt;
	}
	
	
	public WriteDTO[] createArray(ResultSet rs) throws SQLException {
		ArrayList<WriteDTO> list = new ArrayList<WriteDTO>();
		
		while(rs.next()) {

			int post_id = rs.getInt("post_id"); // 게시글 고유번호 
			String title = rs.getString("title"); //제목 
			int writer = rs.getInt("writer"); //제목 
			String category = rs.getString("category"); //제목 
			//sysdate
			Date d = rs.getDate("regdate");
			Time t = rs.getTime("regdate");
			String regDate = "";
			if( d != null) {
				int month = d.getMonth()+1;
				regDate = new SimpleDateFormat(month+"月 dd日").format(d) + " "
						+ new SimpleDateFormat("hh:mm").format(t);
			}
			int content = rs.getInt("post_contents"); //파일주소 
			int viewCnt = rs.getInt("viewCnt");
			WriteDTO dto = new WriteDTO(post_id, title, writer,category, content, viewCnt);
			dto.setRegDate(regDate);
			list.add(dto);
			
		}
		
		int size = list.size();
		if(size == 0 ) return null;
		
		WriteDTO arr[] = new WriteDTO[size];
		list.toArray(arr);
		return arr;
	}
	
	
	
	//글 조회수증가 보기 뷰
	public WriteDTO[] wr_view(int post_id) throws SQLException {
		int cnt =0;
		WriteDTO [] arr = null;
		
		try {
			
			conn.setAutoCommit(false);
			psmt = conn.prepareStatement(View_CNT_ADD);
			psmt.setInt(1, post_id);
			cnt = psmt.executeUpdate();
			psmt.close();
			
			psmt = conn.prepareStatement(SELETE_POST_ID);
			psmt.setInt(1, post_id);
			rs = psmt.executeQuery();
			arr= createArray(rs);
			conn.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			close();
		} 
		
		return arr;
	}
	
	
	
	
	
	//글 업데이트하기 1.1 ( user_id 조회해서 정보 가져오기 ) 
	public  WriteDTO[] _post_update(int post_id) throws SQLException {
		int cnt = 0;
		WriteDTO[] arr = null;
		try {
			psmt = conn.prepareStatement(SQL_WRITE_SEARCH_BY_UID);
			psmt.setInt(1, post_id);
			rs = psmt.executeQuery();
			arr = createArray(rs);
		} finally {
			close();
		}
		return arr;
	}

	
	
	//글 업데이트 하기 1.2 ( 수정 정보 저장하기 ) 
	public int post_Update(String title, String category,int post_id) throws SQLException {
			int cnt = 0;
			try {
						
				psmt = conn.prepareStatement(SQL_WRITE_UPDATE_DATA);
				psmt.setString(1, title);
				psmt.setString(2, category);
				psmt.setInt(3, post_id);
				cnt = psmt.executeUpdate();
			} finally {
				close();
			}
			
			return cnt;
		}//유저테이블 정보 가져오기 
	

}
