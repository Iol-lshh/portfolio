package main.java.com.model.membermanage;

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
import main.java.com.util.DataUtil;

public class MM_DAO implements DAO {
	
	public static final String SELECT_MM_BY_MM_ID="SELECT	 mm_id, id, password, nickname, email, grade " + 
			"FROM mm_table " + 
			"WHERE mm_id=?";
	public static final String INSERT_MM_BY_DTO="INSERT INTO mm_table(mm_id,id,password,nickname,email,grade) " + 
			"VALUES (SEQ_mm_table_mm_id.NEXTVAL,?,?,?,?,?)";
	public static final String DELETE_MM_BY_DTO="DELETE FROM mm_table " + 
			"WHERE mm_id=?";
	
	
	//DB 연결에 필요한 변수들
		Connection conn;
		PreparedStatement pstmt;
		Statement stmt;
		ResultSet rs;
		
		//객체 생성시, DB Connection 생성
		public MM_DAO() {
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
		
		
		
		//String 매개변수를 받는 sql문으로 가져오기 ☆★아이디나 닉네임 이메일 중복 검증에 필요
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
		
		
		
		
		// insert : DTO => DB ★☆회원가입때 디비에 집어넣을때 필요
		// mm_id는 ai로 올라가므로, 집어넣지 않는다!
		@Override
		public int insertBySQL_withDTO(String sql, DTO dto) throws SQLException{
			int cnt=0;
			MM_DTO mm_dto=(MM_DTO)dto;
			try {
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, mm_dto.getId());
				pstmt.setString(2, mm_dto.getPassword());
				pstmt.setString(3, mm_dto.getNickname());
				pstmt.setString(4, mm_dto.getEmail());
				pstmt.setString(5, mm_dto.getGrade());
				cnt=pstmt.executeUpdate();
			}catch(SQLException e){
				e.printStackTrace();
			}finally {
				close();
			}
			return cnt;
		}//end insert dto

		
		// insert : DTOs => DB 
		@Override
		public int insertBySQL_withDTO(String sql, DTO ...dtos) throws SQLException{
			int cnt=0;
			try {
				for(DTO dto:dtos) {
					MM_DTO mm_dto=(MM_DTO)dto;
					pstmt=conn.prepareStatement(sql);
					pstmt.setString(1, mm_dto.getId());
					pstmt.setString(2, mm_dto.getPassword());
					pstmt.setString(3, mm_dto.getNickname());
					pstmt.setString(4, mm_dto.getEmail());
					pstmt.setString(5, mm_dto.getGrade());					
				}
				cnt=pstmt.executeUpdate();
			}catch(SQLException e){
				e.printStackTrace();
			}finally {
				close();
			}
			return cnt;
		}//end insert dtos
		
		public int deleteBySQL_withDTO(String sql,DTO ...dtos) throws SQLException {
			int cnt=0;
			try {
			for(DTO dto:dtos) {
				MM_DTO mm_dto=(MM_DTO)dto;
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, mm_dto.getMm_id());
			}
				cnt=pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close();
			}
			return cnt;
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
		public DTO[] withSignal(DTO[] _arr, int signal, int integerParamForPstmt) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

}
