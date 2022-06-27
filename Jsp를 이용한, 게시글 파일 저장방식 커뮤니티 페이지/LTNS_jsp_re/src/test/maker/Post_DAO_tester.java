package test.maker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import main.java.com.model.DAO;
import main.java.com.model.DB;
import main.java.com.model.DTO;
import main.java.com.model.mainpage.File_DTO;
import main.java.com.model.mainpage.Post_DTO;

public class Post_DAO_tester implements DAO {
	
	//새 post 삽입 제목, (int)mm_id, 카테고리, (int)file_id 필요
	public static final String INSERT_NEW_POST = "INSERT INTO post_table(post_id,title,writer,category,regdate,post_contents,viewCnt) " + 
			"VALUES (SEQ_post_table_post_id.NEXTVAL,?,?,?,SYSTIMESTAMP,?,0)";

	//DB 연결에 필요한 변수들
	Connection conn;
	PreparedStatement pstmt;
	Statement stmt;
	ResultSet rs;
	
	//객체 생성시, DB Connection 생성
	public Post_DAO_tester() {
		try {
			System.out.println("File_DAO_tester 생성 중..");
			Class.forName(DB.DRIVER);
			conn=DriverManager.getConnection(DB.URL,DB.USERID,DB.USERPW);
			System.out.println("File_DAO_tester 생성 성공!, DB 연결");
		}catch (Exception e) {
			e.printStackTrace();
		}// end try
	}// 생성자 end
	
	
	//DB 자원 반납
	@Override
	public void close() throws SQLException {
		if(rs != null) rs.close();
		if(pstmt != null) pstmt.close();
		if(stmt != null) stmt.close();
		if(conn != null) conn.close(); 
	}//end close()

	
	//집어넣기
	@Override
	public int insertBySQL_withDTO(String sql, DTO dto) throws SQLException {
		int result = 0;
		try {
			Post_DTO post_dto = (Post_DTO) dto;
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, post_dto.getTitle());
			pstmt.setInt(2, post_dto.getMm_id());
			pstmt.setString(3, post_dto.getCategory());
			pstmt.setInt(4, post_dto.getFile_id());
			pstmt.executeUpdate();
			pstmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}
	
	//한번에 여러개 집어넣기
	@Override
	public int insertBySQL_withDTO(String sql, DTO... dtos) throws SQLException {
		int result = 0;
		try {
			for(DTO dto : dtos) {
				Post_DTO post_dto = (Post_DTO) dto;
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, post_dto.getTitle());
				pstmt.setInt(2, post_dto.getMm_id());
				pstmt.setString(3, post_dto.getCategory());
				pstmt.setInt(4, post_dto.getFile_id());
				pstmt.executeUpdate();
				pstmt.close();				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}
	
	/////////////////////////////////
	@Override
	public DTO mkDTO(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public DTO[] mkDTOs(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public DTO[] selectBySQL(String sql) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public DTO[] selectBySQL(String sql, int integerParamForPstmt) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public DTO[] selectBySQL(String sql, int... integerParamForPstmt) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public DTO[] selectBySQL(String sql, String stringParamForPstmt) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public DTO[] selectBySQL(String sql, String... stringParamForPstmt) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public DTO mkDTO(ResultSet rs, int signal) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public DTO[] mkDTOs(ResultSet rs, int signal) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public DTO[] withSignal(DTO[] _arr, int signal) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public DTO[] selectBySQL_withSignal(String sql, int signal) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public DTO[] selectBySQL_withSignal(String sql, int signal, int integerParamForPstmt) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public DTO[] selectBySQL_withSignal(String sql, int signal, int... integerParamForPstmt) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public DTO[] selectBySQL_withSignal(String sql, int signal, String stringParamForPstmt) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public DTO[] selectBySQL_withSignal(String sql, int signal, String... stringParamForPstmt) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public DTO[] withSignal(DTO[] _arr, int signal, int integerParamForPstmt) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}




}
