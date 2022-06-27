package main.java.com.model.mainpage;

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
import main.java.com.model.membermanage.MM_DTO;

public class MM_DAO implements DAO {
	
	public static final String SELECT_MM_BY_MM_ID="SELECT	 mm_id, id, password, nickname, email, grade " + 
			"FROM mm_table " + 
			"WHERE mm_id=?";

	//DB 연결에 필요한 변수들
		Connection conn;
		PreparedStatement pstmt;
		Statement stmt;
		ResultSet rs;
		
		//객체 생성시, DB Connection 생성
		public MM_DAO() {
			try { 
				Class.forName(DB.DRIVER);
				conn=DriverManager.getConnection(DB.URL,DB.USERID,DB.USERPW);
				System.out.println("DAO 생성, DB 연결");
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
		
		
		
		// ResultSet => DTO
		@Override
		public DTO mkDTO(ResultSet rs) throws SQLException {
			int mm_id=rs.getInt("mm_id");
			String id=rs.getString("id");
			String password=rs.getString("password");
			String nickname=rs.getString("nickname");
			String email=rs.getString("email");
			String grade=rs.getString("grade");
			
			DTO dto=new MM_DTO(mm_id, id, password, nickname, email, grade);
			return dto;
		}//end mkDTO
		
		
		
		// ResultSet => DTOs
		@Override
		public DTO[] mkDTOs(ResultSet rs) throws SQLException {
			DTO[] arr=null;
			ArrayList<DTO> list=new ArrayList<DTO>();
			
			while(rs.next()) {
				list.add(mkDTO(rs));
			}
			int size=list.size();
			if(size==0)return null;
			arr=new DTO[size];
			list.toArray(arr);
			
			return arr;
		}//end mkDTOs()
		
		
		
		//sql문으로 가져오기
		@Override
		public DTO[] selectBySQL(String sql) throws SQLException {
			DTO [] arr=null;
			try {
				pstmt=conn.prepareStatement(sql);
				rs=pstmt.executeQuery();
				arr=mkDTOs(rs);
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close();
			}
			return arr;
		}//end - sql문으로 가져오기
		
		
		
		//String 매개변수를 받는 sql문으로 가져오기
		@Override
		public DTO[] selectBySQL(String sql, String stringParamForPstmt) throws SQLException {
			DTO [] arr=null;
			try {
				pstmt=conn.prepareStatement(sql);
				
				pstmt.setString(1, stringParamForPstmt);
				
				rs=pstmt.executeQuery();
				arr=mkDTOs(rs);
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close();
			}
			return arr;
		}//end - String 매개변수를 받는 sql문으로 가져오기	
		
		//String 매개변수를 받는 sql문으로 가져오기
		@Override
		public DTO[] selectBySQL(String sql, String ...stringParamForPstmt) throws SQLException {
			DTO [] arr=null;
			try {
				pstmt=conn.prepareStatement(sql);
				
				for(int i=0;i<stringParamForPstmt.length;i++)
					pstmt.setString(i+1, stringParamForPstmt[i]);
				
				rs=pstmt.executeQuery();
				arr=mkDTOs(rs);
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close();
			}
			return arr;
		}//end - String 매개변수를 받는 sql문으로 가져오기


		@Override
		public DTO[] selectBySQL(String sql, int integerParamForPstmt) throws SQLException {
			DTO [] arr=null;
			try {
				pstmt=conn.prepareStatement(sql);
				
				pstmt.setInt(1, integerParamForPstmt);
				
				rs=pstmt.executeQuery();
				arr=mkDTOs(rs);
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close();
			}
			return arr;
		}


		@Override
		public DTO[] selectBySQL(String sql, int... integerParamForPstmt) throws SQLException {
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
