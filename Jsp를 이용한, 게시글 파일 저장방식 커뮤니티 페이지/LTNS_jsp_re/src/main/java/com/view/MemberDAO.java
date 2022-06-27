package main.java.com.view;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import main.java.com.model.DB;
import main.java.com.model.post.FileWriteDTO;

public class MemberDAO {

	
	Connection conn = null;
	PreparedStatement psmt = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	public static final String SQL_SELECT_USER_ID = 
			"SELECT * FROM MM_TABLE mt WHERE MM_id = ?";
	
	public static final String SQL_LOGIN_CHK = 
			"SELECT * FROM MM_TABLE WHERE id = ? AND PASSWORD = ? ";

	public static final String SQL_ID_INFO_SELECT =
			"SELECT * FROM MM_TABLE mt WHERE id = ? ";
	
	public static final String SQL_NIK_INFO_SELECT =
			"SELECT * FROM MM_TABLE mt WHERE nickname = ? ";
	
	public static final String SQL_EMAIL_INFO_SELECT =
			"SELECT * FROM MM_TABLE mt WHERE email = ? ";
	
	public static final String SQL_ID_INFO_SELECT_MM_ID =
			"SELECT * FROM MM_TABLE mt WHERE MM_ID = ? ";
	
	public static final String SQL_SELECT_JOIN =
			"SELECT * FROM MM_TABLE WHERE ID=? AND NICKNAME= ? AND EMAIL=? " ;
	
	public static final String SQL_INSERT_JOIN =
			" INSERT INTO mm_table(mm_id,id,password,nickname,email,grade) "
			+ "VALUES"
			+ "(SEQ_mm_table_mm_id.NEXTVAL,?,?,?,?,'MEMBER')";
	
	public static final String UPDATE_PASSWORD = 
			"UPDATE MM_TABLE  SET PASSWORD = ? WHERE mm_id = ?";
	

	public static final String UPDATE_NICK = 
			"UPDATE MM_TABLE  SET NICKNAME = ? WHERE mm_id = ?";

	
	
	
	
	public MemberDAO() {
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
	
	
	//멤버정보 꺼내오기  회원가입완료후 정보출력
	
	public MemberDTO[] member_Select(int mm_id) throws SQLException {
		int cnt = 0;
		MemberDTO[] arr = null;
		
		try {
			psmt = conn.prepareStatement(SQL_SELECT_USER_ID);
			psmt.setInt(1, mm_id);
			rs = psmt.executeQuery();
			arr = createMember(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		
		return arr;
		
	}
	
	//이메일 정보 출력
		public MemberDTO[] member_FIND_Select(String email) throws SQLException {
			int cnt = 0;
			MemberDTO[] arr = null;
			
			try {
				psmt = conn.prepareStatement(SQL_EMAIL_INFO_SELECT);
				psmt.setString(1, email);
				rs = psmt.executeQuery();
				arr = createMember(rs);
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close();
			}

			
			return arr;
			
		}
	
	//id 정보 출력 
	public MemberDTO[] member_INFO_Select(String id) throws SQLException {
		int cnt = 0;
		MemberDTO[] arr = null;
		
		try {
			psmt = conn.prepareStatement(SQL_ID_INFO_SELECT);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			arr = createMember(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		
		return arr;
		
	}
	
	
	
	

	public MemberDTO[] member_Nick_name_Select(String nickname) throws SQLException {
		int cnt = 0;
		MemberDTO[] arr = null;
		
		try {
			psmt = conn.prepareStatement(SQL_NIK_INFO_SELECT);
			psmt.setString(1, nickname);
			rs = psmt.executeQuery();
			arr = createMember(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		
		return arr;
		
	}
	
	
	
	
	
	
		
	//닉네임 확인하기
		public int nik_select(String nik) throws SQLException {
			int cnt = 0;
			
			try {
				psmt = conn.prepareStatement(SQL_NIK_INFO_SELECT);
				psmt.setString(1, nik);
				cnt = psmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close();
			}

			
			return cnt;
			
		}
	
	
	
	
	
	
	//닉네임 중복검사
	public int nik_overlap(String nik) throws SQLException {
		int cnt = 0;
		
		try {
			psmt = conn.prepareStatement(SQL_NIK_INFO_SELECT);
			psmt.setString(1, nik);
			cnt = psmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		
		return cnt;
		
	}
	
	
	//email 중복검사
	public int email_overlap(String email) throws SQLException {
		int cnt = 0;
		
		try {
			psmt = conn.prepareStatement(SQL_EMAIL_INFO_SELECT);
			psmt.setString(1, email);
			cnt = psmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		
		return cnt;
		
	}
	
	//id 정보 출력 
		public MemberDTO[] member_MM_ID_Select(int mm_id) throws SQLException {
			int cnt = 0;
			MemberDTO[] arr = null;
			
			try {
				psmt = conn.prepareStatement(SQL_ID_INFO_SELECT_MM_ID);
				psmt.setInt(1, mm_id);
				rs = psmt.executeQuery();
				arr = createMember(rs);
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close();
			}

			
			return arr;
			
		}
		
		public MemberDTO[] member_MM_NICK_Select(String nickname) throws SQLException {
			MemberDTO[] arr = null;
			
			try {
				psmt = conn.prepareStatement(SQL_NIK_INFO_SELECT);
				psmt.setString(1, nickname);
				rs = psmt.executeQuery();
				arr = createMember(rs);
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close();
			}
			
			
			return arr;
			
		}
	
	
	
		
		
	
	
	//멤버 id검색
	public int login_member(String id, String pw) throws SQLException {
		int cnt = 0;
		
		try {
			psmt = conn.prepareStatement(SQL_LOGIN_CHK);
			psmt.setString(1, id);
			psmt.setString(2, pw);
			cnt = psmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		
		
		return cnt;
	}
	
	//회원가입 조회
	public int select_join(String id, String nik, String email) throws SQLException {
		
		int cnt = 0;
		try {
			psmt = conn.prepareStatement(SQL_SELECT_JOIN);
			psmt.setString(1, id);
			psmt.setString(2, nik);
			psmt.setString(3, email);
			
			
			cnt = psmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		
		
		return cnt;		
		
		
		
	}

	//id chk
	//id 중복검사
		public int id_overlap(String id) throws SQLException {
			int cnt = 0;
			
			try {
				psmt = conn.prepareStatement(SQL_ID_INFO_SELECT);
				psmt.setString(1, id);
				cnt = psmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close();
			}

			
			return cnt;
			
		}
	
	
	
	//비밀번호 변경 
	public int update_pw(String pw, int mm_id) throws SQLException {
		int cnt =0;
		
		try {
			psmt = conn.prepareStatement(UPDATE_PASSWORD);
			psmt.setString(1, pw);
			psmt.setInt(2, mm_id);
			cnt = psmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		
		
		return cnt;
	}
	
	
	

	//닉네임 변경
	public int update_nick(String nick, int mm_id) throws SQLException {
		int cnt =0;
		
		try {
			psmt = conn.prepareStatement(UPDATE_NICK);
			psmt.setString(1, nick);
			psmt.setInt(2, mm_id);
			cnt = psmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		
		
		return cnt;
	}
	
	
	
	
	
	
	
	//회원가입 Insert
	public int insert_join(String id, String pw, String nik, String email) throws SQLException {
		System.out.println("확인3");
		int cnt = 0;
		try {
			psmt = conn.prepareStatement(SQL_INSERT_JOIN);
			psmt.setString(1, id);
			psmt.setString(2, pw);
			psmt.setString(3, nik);
			psmt.setString(4, email);
			
			System.out.println(id);
			System.out.println(pw);
			System.out.println(nik);
			System.out.println(email);
			
			cnt = psmt.executeUpdate();
			System.out.println(cnt);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		
		
		return cnt;		
		
		
		
		
	}
	
	
	
	
	
	public MemberDTO[] createMember(ResultSet rs) throws SQLException {
		ArrayList<MemberDTO> memberlist = new ArrayList<MemberDTO>();
		
		while(rs.next()) {

			int mm_id = rs.getInt("mm_id");
			String id = rs.getString("id");
			String pw = rs.getString("password");
			String nickname = rs.getString("nickname");
			String email = rs.getString("email");
			String grade = rs.getString("grade");
			MemberDTO memberdto = new MemberDTO(mm_id, id, pw, nickname, email, grade);
			memberlist.add(memberdto);
			
		}
		
		int size = memberlist.size();
		if(size == 0 ) return null;
		
		MemberDTO arr[] = new MemberDTO[size];
		memberlist.toArray(arr);
		return arr;
	}
	

	
	
	
	
	
}
