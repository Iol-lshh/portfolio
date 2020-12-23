package main.java.com.model.mainpage;

import java.sql.*;
import java.util.ArrayList;
import main.java.com.model.DAO;
import main.java.com.model.DB;
import main.java.com.model.DTO;
import main.java.com.model.Post_Contents;
import main.java.com.util.DataUtil;

public class Mainpage_DAO implements DAO {
	/*--공감 순으로 뷰를 정렬하여,포스트 3개  가져오기--*/
	public final static String SELECT_3_POSTS_BY_EMPATHIZE_CNT="SELECT * FROM " + 
			"(SELECT P.post_id AS post_id, M.mm_id AS mm_id, M.ID AS id, M.nickname AS nickname, P.title AS title, P.regdate AS regdate, P.category AS category, F.real_filename AS real_filename, V.empathize_cnt AS empathize_cnt, P.viewcnt AS viewcnt " + 
			"FROM tot_post_view V " + 
			"LEFT OUTER JOIN post_table P " + 
			"ON V.post_id=P.post_id " + 
			"LEFT OUTER JOIN mm_table M " + 
			"ON P.writer=M.mm_id " + 
			"LEFT OUTER JOIN file_table F " + 
			"ON P.post_contents=F.file_id " + 
			"ORDER by empathize_cnt DESC NULLS LAST, post_id DESC  " + 
			") WHERE ROWNUM <=3 ";
	
	/*--조회수 순으로 뷰를 정렬하여,포스트 6개  가져오기--*/
	public final static String SELECT_6_POSTS_BY_VIEWCNT="SELECT  * FROM " + 
			"(SELECT P.post_id AS post_id, M.mm_id AS mm_id, M.ID AS id, M.nickname AS nickname, P.title AS title, P.regdate AS regdate, P.category AS category, F.real_filename AS real_filename, V.empathize_cnt AS empathize_cnt, P.viewcnt AS viewcnt " + 
			"FROM tot_post_view V " + 
			"LEFT OUTER JOIN post_table P " + 
			"ON V.post_id=P.post_id " + 
			"LEFT OUTER JOIN mm_table M " + 
			"ON P.writer=M.mm_id " + 
			"LEFT OUTER JOIN file_table F " + 
			"ON P.post_contents=F.file_id " + 
			"ORDER by P.viewcnt DESC, post_id DESC  " + 
			") WHERE ROWNUM <= 6";
	
	/*--최신 순으로 포스트 5개 가져오기(여기서 갯수제한 안함!)--*/
	public final static String SELECT_5_POSTS_BY_NEAREST="SELECT * FROM " + 
			"(SELECT P.post_id AS post_id, M.mm_id AS mm_id, M.ID AS id, M.nickname AS nickname, P.title AS title, P.regdate AS regdate, P.category AS category, F.real_filename AS real_filename, V.empathize_cnt AS empathize_cnt, P.viewcnt AS viewcnt " + 
			"FROM tot_post_view V " + 
			"LEFT OUTER JOIN post_table P " + 
			"ON V.post_id=P.post_id " + 
			"LEFT OUTER JOIN mm_table M " + 
			"ON P.writer=M.mm_id " + 
			"LEFT OUTER JOIN file_table F " + 
			"ON P.post_contents=F.file_id " + 
			"ORDER BY regdate DESC, post_id DESC  " + 
			") WHERE ROWNUM <= 6";
	
	/*--최신 순으로, n번째 이후, 다음 포스트 3개 가져오기 매개변수로 regdate 받을것!--*/
	public final static String SELECT_NEXT_3_POSTS_BY_NEAREST="SELECT * FROM " + 
			"(SELECT P.post_id AS post_id, M.mm_id AS mm_id, M.ID AS id, M.nickname AS nickname, P.title AS title, P.regdate AS regdate, P.category AS category, F.real_filename AS real_filename, V.empathize_cnt AS empathize_cnt, P.viewcnt AS viewcnt " + 
			"FROM tot_post_view V  " + 
			"LEFT OUTER JOIN post_table P " + 
			"ON V.post_id=P.post_id " + 
			"LEFT OUTER JOIN mm_table M " + 
			"ON P.writer=M.mm_id " + 
			"LEFT OUTER JOIN file_table F " + 
			"ON P.post_contents=F.file_id " + 
			"WHERE P.post_id<? " + 
			"ORDER BY regdate DESC, post_id DESC  " + 
			") WHERE ROWNUM<=3";
	
	
	//DB 연결에 필요한 변수들
	Connection conn;
	PreparedStatement pstmt;
	Statement stmt;
	ResultSet rs;
	
	//객체 생성시, DB Connection 생성
	public Mainpage_DAO() {
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
		int post_id=rs.getInt("post_id");
		String title=rs.getString("title");
		String nickname=rs.getString("nickname");
		String id=rs.getString("id");
		String category=rs.getString("category");
		String regdate=rs.getString("regdate");
//		regdate=regdate.substring(0,16);
		int viewCnt=rs.getInt("viewCnt");//받지 않음
		int empathCnt=rs.getInt("empathize_cnt");//받지 않음
		int mm_id=rs.getInt("mm_id");// mm_id
		Post_Contents post_contents=new Post_Contents(rs.getString("real_filename"));
		
		DTO dto=new Post_DTO(post_id, title, mm_id, category, regdate,
				post_contents, nickname, id, viewCnt, empathCnt);
		System.out.println("메인페이지 DAO 생성");

		return dto;
	}//end mkDTO
	
	
	
	// ResultSet => DTOs
	@Override
	public DTO[] mkDTOs(ResultSet rs) throws SQLException {
		DTO[] arr=null;
		ArrayList<DTO> list=new ArrayList<DTO>();
		System.out.println(rs);
		try {
			while(rs.next()) {
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
		}catch(Exception e) {
			
		} finally {
			close();
		}
		return arr;
	}//end - sql문으로 가져오기
	
	//String 매개변수를 받는 sql문으로 가져오기
	public DTO[] selectBySQL(String sql, String stringParamForPstmt, int intParamForPstmt) throws SQLException {
		DTO [] arr=null;
		try {
			pstmt=conn.prepareStatement(sql);
			System.out.println(stringParamForPstmt);
			pstmt.setString(1, stringParamForPstmt);
			pstmt.setInt(2, intParamForPstmt);
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
	public DTO[] selectBySQL(String sql, String stringParamForPstmt) throws SQLException {
		DTO [] arr=null;
		try {
			pstmt=conn.prepareStatement(sql);
			System.out.println(stringParamForPstmt);
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
		return null;
	}


	@Override
	public DTO[] mkDTOs(ResultSet rs, int signal) throws SQLException {
		return null;
	}


	@Override
	public DTO[] selectBySQL_withSignal(String sql, int signal) throws SQLException {
		return null;
	}


	@Override
	public DTO[] selectBySQL_withSignal(String sql, int signal, int integerParamForPstmt) throws SQLException {
		return null;
	}


	@Override
	public DTO[] selectBySQL_withSignal(String sql, int signal, int... integerParamForPstmt) throws SQLException {
		return null;
	}


	@Override
	public DTO[] selectBySQL_withSignal(String sql, int signal, String stringParamForPstmt) throws SQLException {
		return null;
	}


	@Override
	public DTO[] selectBySQL_withSignal(String sql, int signal, String... stringParamForPstmt) throws SQLException {
		return null;
	}


	@Override
	public DTO[] withSignal(DTO[] _arr, int signal) throws SQLException {
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
