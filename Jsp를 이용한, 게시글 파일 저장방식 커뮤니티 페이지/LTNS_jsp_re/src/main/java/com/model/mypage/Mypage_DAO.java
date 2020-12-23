package main.java.com.model.mypage;

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
import main.java.com.model.Post_Contents;
import main.java.com.model.board.CommentDTO;
import main.java.com.model.mainpage.Post_DTO;
import main.java.com.util.DataUtil;

public class Mypage_DAO implements DAO {

	//pagenation을 위해선 cnt를 가져와야 한다..
	//1. 내가 작성한 글 : 5*n, pagination : /5, 제목, 작성일, 조회수, post_table에서  mm_id가 나와 일치해야 한다
	final static public String SELECT_6_MY_POSTS="SELECT * FROM " + 
			"(SELECT P.post_id AS post_id, M.mm_id AS mm_id, M.ID AS id, M.nickname AS nickname, P.title AS title, P.regdate AS regdate, P.category AS category, F.real_filename AS real_filename, V.empathize_cnt AS empathize_cnt, P.viewcnt AS viewcnt " + 
			"FROM tot_post_view V  " + 
			"LEFT OUTER JOIN post_table P " + 
			"ON V.post_id=P.post_id " + 
			"LEFT OUTER JOIN mm_table M " + 
			"ON P.writer=M.mm_id " + 
			"LEFT OUTER JOIN file_table F " + 
			"ON P.post_contents=F.file_id " + 
			"WHERE M.mm_id=? " + 
			"ORDER BY P.regdate DESC, P.post_id DESC  " + 
			") WHERE ROWNUM <= 6";
	
	//1. 내가 작성한 글 갯수-pagination cnt
	final static public String SELECT_MY_POSTS_CNT="SELECT COUNT(*) AS count  " + 
			"FROM post_table " + 
			"WHERE writer=? " + 
			"GROUP BY writer";
	
	//1. n번째 내가 작성한 글 : 5*n, pagination : /5, 제목, 작성일, 조회수, post_table에서  mm_id가 나와 일치해야 한다
	final static public String SELECT_6_MY_POSTS_NTH="SELECT * FROM " + 
			"(SELECT P.post_id AS post_id, M.mm_id AS mm_id, M.ID AS id, M.nickname AS nickname, P.title AS title, P.regdate AS regdate, P.category AS category, F.real_filename AS real_filename, V.empathize_cnt AS empathize_cnt, P.viewcnt AS viewcnt " + 
			"FROM tot_post_view V  " + 
			"LEFT OUTER JOIN post_table P " + 
			"ON V.post_id=P.post_id " + 
			"LEFT OUTER JOIN mm_table M " + 
			"ON P.writer=M.mm_id " + 
			"LEFT OUTER JOIN file_table F " + 
			"ON P.post_contents=F.file_id " + 
			"WHERE M.mm_id=? " + 
			"ORDER BY P.regdate DESC, P.post_id DESC  " +  
			")WHERE ROWNUM >= ? AND ROWNUM <= ?+5";
	
	//2. 내가 작성한 댓글 : 5*n, pagination : /5, 제목, 작성일, post_table에서  mm_id가 나와 일치해야 한다
	final static public String SELECT_6_MY_COMMENTS="SELECT * FROM ( " + 
			"SELECT  comment_id, post_id, regdate, comment_contents " + 
			"FROM comment_table " + 
			"WHERE writer=? " + 
			"ORDER BY regdate DESC, comment_id DESC " + 
			")WHERE ROWNUM <=6";
	
	//2. 내가 작성한 댓글 갯수 -pagination cnt
	final static public String SELECT_MY_COMMENTS_CNT="SELECT COUNT(*) AS count " + 
			"FROM comment_table " + 
			"WHERE writer=? " + 
			"GROUP BY writer";
	
	//2. n번째 내가 작성한 댓글 : 5*n, pagination : /5, 제목, 작성일, post_table에서  mm_id가 나와 일치해야 한다
	final static public String SELECT_6_MY_COMMENTS_NTH="SELECT * FROM (" + 
			"SELECT  comment_id, post_id, regdate, comment_contents " + 
			"FROM comment_table " + 
			"WHERE writer=?  " + 
			"ORDER BY regdate DESC, comment_id DESC " + 
			")WHERE ROWNUM >= ? AND ROWNUM <= ?+5";
	
	//3. 공감한 게시글 :  5*n, pagination : /5, 제목, 작성자, 작성일, empathize_table에서  mm_id가 나와 일치해야 한다
	final static public String SELECT_6_MY_EMPATHIZE="SELECT * FROM( " + 
			"SELECT P.post_id AS post_id, M.mm_id AS mm_id, M.ID AS id, M.nickname AS nickname, P.title AS title, P.regdate AS regdate, P.category AS category, F.real_filename AS real_filename, P.viewcnt AS viewcnt " + 
			"FROM empathize_table E " + 
			"LEFT OUTER JOIN mm_table M " + 
			"ON E.mm_id=M.mm_id " + 
			"LEFT OUTER JOIN post_table P " + 
			"ON E.post_id=P.post_id " + 
			"LEFT OUTER JOIN file_table F " + 
			"ON P.post_contents=F.file_id " + 
			"WHERE E.mm_id=? " + 
			"ORDER BY regdate DESC, post_id DESC  " + 
			")WHERE ROWNUM <= 6";
	
	//3. 공감한 게시글 갯수-pagination cnt
	final static public String SELECT_MY_EMPATHIZE_CNT="SELECT COUNT(*) AS count " + 
			"FROM empathize_table E " + 
			"WHERE mm_id=? " + 
			"GROUP BY mm_id";
	
	//3. n번째 공감한 게시글 :  5*n, pagination : /5, 제목, 작성자, 작성일, empathize_table에서  mm_id가 나와 일치해야 한다
	final static public String SELECT_6_MY_EMPATHIZE_NTH="SELECT * FROM( " + 
			"SELECT P.post_id AS post_id, M.mm_id AS mm_id, M.ID AS id, M.nickname AS nickname, P.title AS title, P.regdate AS regdate, P.category AS category, F.real_filename AS real_filename, P.viewcnt AS viewcnt " + 
			"FROM empathize_table E " + 
			"LEFT OUTER JOIN mm_table M " + 
			"ON E.mm_id=M.mm_id " + 
			"LEFT OUTER JOIN post_table P " + 
			"ON E.post_id=P.post_id " + 
			"LEFT OUTER JOIN file_table F " + 
			"ON P.post_contents=F.file_id " + 
			"WHERE E.mm_id=? " + 
			"ORDER BY regdate DESC, post_id DESC  " + 
			")WHERE ROWNUM >= ? AND ROWNUM <= ?+5";
				
	//DB 연결에 필요한 변수들
		Connection conn;
		PreparedStatement pstmt;
		Statement stmt;
		ResultSet rs;
		
		//객체 생성시, DB Connection 생성
		public Mypage_DAO() {
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
		
		public DTO mkDTO(ResultSet rs, int signal) throws SQLException {
			System.out.println("마이페이지 DAO 생성시작"+signal);
			
			DTO dto=null;
			int empathCnt=0;//signal 2번, 3번 동시 처리 위해 switch 앞에 생성
			int post_id;
			String regdate;
			switch(signal) {
			case 2: //내가 작성한 댓글
				int comment_id=rs.getInt("comment_id");
				post_id=rs.getInt("post_id");
				regdate=rs.getString("regdate");
				String comment_contents=rs.getString("comment_contents");
				
				dto=new CommentDTO(comment_id, comment_contents, post_id, regdate);
				break;
			
			case 1: //내가 작성한 글
				empathCnt=rs.getInt("empathize_cnt");
			case 3: //내가 공감한 글
				post_id=rs.getInt("post_id");
				String title=rs.getString("title");
				String nickname=rs.getString("nickname");
				String id=rs.getString("id");
				String category=rs.getString("category");
				regdate=rs.getString("regdate");
				regdate=regdate.substring(0,16);
				int viewCnt=rs.getInt("viewCnt");
				int mm_id=rs.getInt("mm_id");// mm_id
				Post_Contents post_contents=new Post_Contents(rs.getString("real_filename"));
				
				dto=new Post_DTO(post_id, title, mm_id, category, regdate,
						post_contents, nickname, id, viewCnt, empathCnt);
				break;
				
			default :
				System.out.println("잘못된 DTO 생성 요청!");
				throw new SQLException();
			}
			return dto;
		}//end mkDTO
		
		
		public DTO[] mkDTOs(ResultSet rs, int signal) throws SQLException {
			DTO[] arr=null;
			ArrayList<DTO> list=new ArrayList<DTO>();
			
			try {
				while(rs.next()) {
					list.add(mkDTO(rs,signal));
				}
				int size=list.size();
				if(size==0)return null;
				arr=new DTO[size];
				list.toArray(arr);
			}catch(SQLException e){
				if(arr.length==0)
					throw new SQLException(); //하나도 안담겼으면 에러 반환해주자
				return arr; //갯수 부족일땐 잘 출력해주자
			}
			return arr;
		}//end mkDTOs()
		
		
		
		
		public DTO[] selectBySQL_withSignal(String sql, int signal) throws SQLException {
			DTO [] arr=null;
			try {
				/*필요한 DTO 배열 가져오기*/
				pstmt=conn.prepareStatement(sql);
				rs=pstmt.executeQuery();
				arr=mkDTOs(rs);
				pstmt.close();
				
				System.out.println("arr:"+arr);
				//count DTO 추가
				arr=withSignal(arr, signal,1);
				
				System.out.println("DTO 배열 생성 성공!");
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close();
			}
			return arr;
		}//end - sql문으로 가져오기
		
		
		



		public DTO[] selectBySQL_withSignal(String sql, int signal, int integerParamForPstmt) throws SQLException {
			DTO [] arr=null;
			try {
				/*필요한 DTO 배열 가져오기*/
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, integerParamForPstmt);
				rs=pstmt.executeQuery();
				arr=mkDTOs(rs,signal);
				pstmt.close();
				
				System.out.println("DTO 배열 생성 성공!");
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close();
			}
			return arr;
		}


		public DTO[] selectBySQL_withSignal(String sql, int signal, int... integerParamForPstmt) throws SQLException {
			DTO [] arr=null;
			try {
				/*필요한 DTO 배열 가져오기*/
				pstmt=conn.prepareStatement(sql);
				for(int i=0; i< integerParamForPstmt.length;i++) {
					pstmt.setInt(i+1, integerParamForPstmt[i]);					
				}
				rs=pstmt.executeQuery();
				arr=mkDTOs(rs,signal);
				pstmt.close();
				
				System.out.println("DTO 배열 생성 성공!");
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close();
			}
			return arr;
		}

		@Override
		public DTO[] withSignal(DTO[] _arr, int signal, int mm_id) throws SQLException {
			/*중간 과정 : 배열 길이 늘리는 과정*/
			DTO[] arr=new DTO[_arr.length+1];
			for(int i=0;i<_arr.length;i++) {
				arr[i+1]=_arr[i];
			}
			System.out.println("signal은 "+signal+"입니다");
			/*count 가져와 배열[0]에 넣기 : 각각의 개수를 구해준다! (pagination을 위함!)*/
			switch(signal) {
			case 1: //my post cnt
				pstmt=conn.prepareStatement(SELECT_MY_POSTS_CNT,mm_id);
				break;
			case 2:
				pstmt=conn.prepareStatement(SELECT_MY_COMMENTS_CNT,mm_id);
				break;
			case 3:
				pstmt=conn.prepareStatement(SELECT_MY_EMPATHIZE_CNT,mm_id);
				break;
			}
			rs=pstmt.executeQuery();
			arr[0]=mkDTO(rs,0);
			
			System.out.println("count DTO 추가 성공!"+((Count_DTO)arr[0]).getCount());
			return arr;
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
		public DTO[] withSignal(DTO[] _arr, int signal) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}
}
