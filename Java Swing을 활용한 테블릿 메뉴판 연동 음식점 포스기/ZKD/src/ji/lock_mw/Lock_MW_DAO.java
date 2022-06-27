package ji.lock_mw;

import java.sql.*;

import java.util.*;

import aaaaaaaaaaaaaaa.InitData;


public class Lock_MW_DAO {// 실질적으로 DB에 접근하는 객체

	Connection con; // DB와 연결해주는 역할
	Statement stmt; // 데이터 바인딩, SQL을 해석해주는 객체
	ResultSet rs; // DB에서 select한 결과를 담는다
	String sql; // 쿼리문으로 쓸 String

	// DB연결시 필요한 url과 ID, PW
	String url = "jdbc:oracle:thin:@"+InitData.ip+":1521:xe";
	String id = "hr";
	String pw = "hr";

	public Lock_MW_DAO() {// DB연결
		try {con = DriverManager.getConnection(url, id, pw);
			stmt = con.createStatement(); // 쿼리 실행 준비
		} catch (SQLException e) {
			e.printStackTrace();
		} // try-catch -> DB접속준비
	}

	public ArrayList<Lock_MW_DTO> list() {// 데이터를 담아주기

		ArrayList<Lock_MW_DTO> res = new ArrayList<Lock_MW_DTO>();// *데이터를 담아주는 리스트
		
		sql = "select * from TABLETABLET";// 쿼리 실행문.DB테이블 중 TABLETABLET을 사용
		
		try {	rs = stmt.executeQuery(sql);// 쿼리 실행결과를 가져온다. select 쿼리 일때만 사용
			
			while (rs.next()) {
				Lock_MW_DTO dto = new Lock_MW_DTO(); // 데이터를 Object로 변환해서 가져와줄 DTO생성

				dto.TT_name = rs.getString("TT_name");
				dto.TT_man = rs.getInt("TT_man");
				dto.TT_woman = rs.getInt("TT_woman");

				res.add(dto);// 위에서 만들어 준 *데이터를 담아주는 arraylist에 데이터를 넣어준다.
			} // while문 - 데이터가져오기 끝
		} catch (SQLException e) {e.printStackTrace();} finally {close();} // = try-catch 쿼리문 실행 끝 =
		return res;
	}

	public void close() {
		if (rs != null) try { rs.close();} catch (SQLException e) {} // resultset
		if (stmt != null) try { stmt.close();} catch (SQLException e) {} // statement
		if (con != null) try { con.close();} catch (SQLException e) {} // connection
		}// 제일 마지막에 연 것 부터 확인하면서 닫아주면서 나간다
	
	public int insert(Lock_MW_DTO dto) {

		int res = 0; // 쿼리문 결과 값이 숫자로 나오므로 int로 결과를 받아준다

		// java에서 받은 데이터 삽입해주는 쿼리문
		sql = "insert into TABLETABLET " + "(TT_name, TT_man, TT_woman) " +
		"values " + "('" + dto.TT_name + "'," + dto.TT_man + ","+ dto.TT_woman + ")";

		System.out.println(sql); // 쿼리문 확인을 위해 사용

		try { res = stmt.executeUpdate(sql); // 내용 업데이트
		} catch (Exception e) {e.printStackTrace();} finally { // 위에서와 마찬가지로 DB사용 후 DB를 닫아준다
			close(); // DB를 닫기 전 확인을 위한 메소드
		}
		return res;
		// 추가된 데이터의 업데이트가 성공적으로 되면 결과값을 1로 반환해준다
	} // == public int insert 끝 =======
		
	// --------------------------------------------------------
	// == public int modify : java에서 수정한 데이터 DB에 삽입 =======
	public int modify(Lock_MW_DTO dto) {

		int res = 0; // 쿼리문 결과 값이 숫자로 나오므로 int로 결과를 받아준다

		// java에서 수정한 데이터 삽입해주는 쿼리문
		sql = "update TABLETABLET set TT_woman = " + dto.TT_woman +", TT_man = "+ dto.TT_man + " where TT_name = '" + dto.TT_name +"'";
		// ▲ MemberMain에서수정해준 값들을 받아와서 DB넣어줄 쿼리문을 만들어줌

		System.out.println(sql); // 쿼리문 확인을 위해 사용

		try {

			res = stmt.executeUpdate(sql); // 수정한 내용 업데이트

		} catch (Exception e) {
			e.printStackTrace();
		} finally { // 위에서와 마찬가지로 DB사용 후 DB를 닫아준다
			close(); // DB를 닫기 전 확인을 위한 메소드
		}

		return res;
		// 수정된 데이터의 업데이트가 성공적으로 되면 결과값을 0로 반환해준다
	} // == public int modify 끝 =======
		// --------------------------------------------------------

	// --------------------------------------------------------
	// == public int delete : java에서 삭제한 데이터 DB에서 삭제 =======
	public int delete(String TT_name) { // 삭제할 아이디값만 받아와서 확인 후 삭제한다

		int res = 0; // 쿼리문 결과 값이 숫자로 나오므로 int로 결과를 받아준다

		// java에서 삭제한 데이터 삭제해주는 쿼리문
		sql = "delete from TABLETABLET where TT_name = '" + TT_name + "'";
		// ▲ MemberMain에서 삭제하기 위해 받아온 아이디 값을 DB에서 찾아서 삭제한다

		System.out.println(sql); // 쿼리문 확인을 위해 사용

		try {

			res = stmt.executeUpdate(sql); // 삭제한 내용을 업데이트

		} catch (Exception e) {
			e.printStackTrace();
		} finally { // 위에서와 마찬가지로 DB사용 후 DB를 닫아준다
			close(); // DB를 닫기 전 확인을 위한 메소드
		}

		return res;
		// 삭제된 데이터의 업데이트가 성공적으로 되면 결과값을 0로 반환해준다
	} // == public int delete 끝 =======

}
