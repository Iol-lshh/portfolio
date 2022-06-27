package main.java.com.model.mypage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import main.java.com.model.DAO;
import main.java.com.model.DTO;
import main.java.com.model.Post_Contents;
import main.java.com.model.mainpage.Post_DTO;
import main.java.com.util.DataUtil;

public class Count_DAO implements DAO {
	//DB 연결에 필요한 변수들
		Connection conn;
		PreparedStatement pstmt;
		Statement stmt;
		ResultSet rs;
		
		//객체 생성시, DB Connection 생성
		public Count_DAO() {
			try {
				System.out.println("DAO 생성");
				conn=DataUtil.getConnection();
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
			System.out.println("카운트 DAO 생성시작");
			int count=rs.getInt("count");			
			System.out.println("0");
			
			DTO dto=new Count_DTO(count);
			return dto;
		}//end mkDTO
		
		
		
		// ResultSet => DTOs
		@Override
		public DTO[] mkDTOs(ResultSet rs) throws SQLException {
			DTO[] arr=null;
			ArrayList<DTO> list=new ArrayList<DTO>();
			System.out.println("엥?");
			System.out.println(rs);
			try {
				while(rs.next()) {
					System.out.println("엥2?");
					list.add(mkDTO(rs));
				}
				int size=list.size();
				if(size==0)return null;
				arr=new DTO[size];
				list.toArray(arr);
			}catch(SQLException e){
				if(arr.length==0) {
					System.out.println("맞는 자료가 하나도 없다!");
					throw new SQLException(); //하나도 안담겼으면 에러 반환해주자
				}
				return arr; //갯수 부족일땐 잘 출력해주자
			}
			return arr;
		}//end mkDTOs()
		
		@Override
		public DTO[] selectBySQL(String sql, int integerParamForPstmt) throws SQLException {
			DTO [] arr=null;
			try {
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, integerParamForPstmt);
				rs=pstmt.executeQuery();
				System.out.println("시벌");
				arr=mkDTOs(rs);
				System.out.println("수벌");

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close();
			}
			return arr;
		}


		@Override
		public DTO[] selectBySQL(String sql) throws SQLException {
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
		public DTO[] withSignal(DTO[] _arr, int signal, int integerParamForPstmt) throws SQLException {
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

}
