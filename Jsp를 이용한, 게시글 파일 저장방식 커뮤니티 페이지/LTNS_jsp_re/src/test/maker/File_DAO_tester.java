package test.maker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import main.java.com.model.DAO;
import main.java.com.model.DB;
import main.java.com.model.DTO;
import main.java.com.model.mainpage.File_DTO;
import main.java.com.model.membermanage.MM_DTO;
import main.java.com.util.DataUtil;

public class File_DAO_tester implements DAO {

	public static final String SELECT_POSTFILE_BY_FILE_ID="SELECT file_id, filekind, filename, real_filename " + 
			"FROM file_table " + 
			"WHERE file_id=?";
	
	//최근 생성된 파일의 file_id를 가져옴
	public static final String SELECT_CURRVAL_FILE_ID="SELECT SEQ_file_table_file_id.CURRVAL FROM DUAL";
	
	//새 파일 정보 집어넣기
	public static final String INSERT_NEW_FILE="INSERT INTO file_table(file_id,filekind,real_filename,filename) " + 
			"VALUES (SEQ_FILE_TABLE_FILE_ID.NEXTVAL,'POST',?,?)";

	
	//DB 연결에 필요한 변수들
	Connection conn;
	PreparedStatement pstmt;
	Statement stmt;
	ResultSet rs;
	
	//객체 생성시, DB Connection 생성
	public File_DAO_tester() {
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
	
	
	public int selectBySQL_returnValue(String sql) throws SQLException {
		int result=0;
		
		try {
			System.out.println("a");
			pstmt=conn.prepareStatement(sql);
			System.out.println("b");
			rs=pstmt.executeQuery();
			System.out.println("c");
			if(rs.next())
				result=rs.getInt("CURRVAL");	
			System.out.println("result : "+result);
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("으악!");
		}finally {
//			close();
		}
		return result;
	}

	@Override
	public int insertBySQL_withDTO(String sql, DTO dto) throws SQLException {
		int result = 0;
		try {
			File_DTO file_dto = (File_DTO) dto;
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, file_dto.getReal_filename());
			pstmt.setString(2, file_dto.getFilename());
			System.out.println("f");
			pstmt.executeUpdate();
			System.out.println("e");
			pstmt.close();
			System.out.println("d");
			result=this.selectBySQL_returnValue(SELECT_CURRVAL_FILE_ID);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		if(result==0) {
			System.out.println("CURRVAL값을 반환받지 못했습니다ㅠ");
			throw new SQLException();
		}
		return result;
	}
	//////////////////////////////////////


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
