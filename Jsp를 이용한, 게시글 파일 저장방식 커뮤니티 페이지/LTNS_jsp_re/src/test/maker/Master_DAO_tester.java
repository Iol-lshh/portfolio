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
import main.java.com.util.DataUtil;

public class Master_DAO_tester implements DAO {

	//DB 연결에 필요한 변수들
	Connection conn;
	PreparedStatement pstmt;
	Statement stmt;
	ResultSet rs;

	//객체 생성시, DB Connection 생성
	public Master_DAO_tester() {
		try {
			System.out.println("Master_DAO_tester 생성 중..");
			Class.forName(DB.DRIVER);
			conn=DriverManager.getConnection(DB.URL,DB.USERID,DB.USERPW);
			System.out.println("Master_DAO_tester 생성 성공!, DB 연결");
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

	
	//db 테이블, 시퀀스, 뷰  전부 날리기
	public void deleteAll() throws SQLException {
		justExcuteBySQL(SQLMASTER_tester.DROP_COMMENT_AI_SQ);
		justExcuteBySQL(SQLMASTER_tester.DROP_FILE_AI_SQ);
		justExcuteBySQL(SQLMASTER_tester.DROP_MM_AI_SQ);
		justExcuteBySQL(SQLMASTER_tester.DROP_POST_AI_SQ);
		
		justExcuteBySQL(SQLMASTER_tester.DROP_POST_VIEW);
		justExcuteBySQL(SQLMASTER_tester.DROP_COMMENT_TABLE);
		justExcuteBySQL(SQLMASTER_tester.DROP_EMPATHIZE_TABLE);
		justExcuteBySQL(SQLMASTER_tester.DROP_FILE_TABLE);
		justExcuteBySQL(SQLMASTER_tester.DROP_MM_TABLE);
		justExcuteBySQL(SQLMASTER_tester.DROP_POST_TABLE);
	}//end deleteAll

	//db 테이블, 시퀀스, 제약조건 전부 생성하기
	public void createAll() throws SQLException {
		justExcuteBySQL(SQLMASTER_tester.CREATE_COMMENT_TABLE);
		justExcuteBySQL(SQLMASTER_tester.CREATE_EMPATHIZE_TABLE);
		justExcuteBySQL(SQLMASTER_tester.CREATE_FILE_TABLE);
		justExcuteBySQL(SQLMASTER_tester.CREATE_MM_TABLE);
		justExcuteBySQL(SQLMASTER_tester.CREATE_POST_TABLE);

		justExcuteBySQL(SQLMASTER_tester.CREATE_COMMENT_AI_SQ);
		justExcuteBySQL(SQLMASTER_tester.CREATE_FILE_AI_SQ);
		justExcuteBySQL(SQLMASTER_tester.CREATE_MM_AI_SQ);
		justExcuteBySQL(SQLMASTER_tester.CREATE_POST_AI_SQ);
		
		justExcuteBySQL(SQLMASTER_tester.ALTER_FOREIGN_KEY_C_POST_ID_REF_P_POST_ID);
		justExcuteBySQL(SQLMASTER_tester.ALTER_FOREIGN_KEY_C_WRITER_REF_MM_ID);
		justExcuteBySQL(SQLMASTER_tester.ALTER_FOREIGN_KEY_E_POST_ID_REF_P_POST_ID);
		justExcuteBySQL(SQLMASTER_tester.ALTER_FOREIGN_KEY_MM_ID_REF_MM_ID);
		justExcuteBySQL(SQLMASTER_tester.ALTER_FOREIGN_KEY_P_WRITER_REF_MM_ID);
		justExcuteBySQL(SQLMASTER_tester.ALTER_FOREIGN_KEY_POST_CONTENTS_REF_FILE_ID);
		
		justExcuteBySQL(SQLMASTER_tester.CREATE_TOT_POST_VIEW);
	}//end create all

	//db 테이블, 시퀀스, 제약조건 전부 초기화
	public void refreshAll() throws SQLException {
		deleteAll();
		createAll();
	}
	
	//그냥 sql문 실행하는 메서드
	public void justExcuteBySQL(String sql) throws SQLException {
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.executeQuery();
		} catch (SQLException e) {
			System.out.println("에러! : "+sql);
			e.printStackTrace();
		}finally {
//			close();
		}
	}
	
	
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
	public int insertBySQL_withDTO(String sql, DTO dto) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertBySQL_withDTO(String sql, DTO... dtos) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public DTO[] withSignal(DTO[] _arr, int signal, int integerParamForPstmt) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
