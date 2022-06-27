package test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import main.java.com.model.DAO;
import main.java.com.model.DTO;
import main.java.com.util.DataUtil;

public class TestDAO implements DAO {
	Connection conn = null;
	PreparedStatement psmt = null;
	Statement stmt = null;
	ResultSet rs = null;
	String contextPath = null;

	@Override
	public DTO[] selectBySQL(String sql, String... stringParamForPstmt) throws SQLException {
		DTO[] result = null;

		try {
			conn = DataUtil.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, stringParamForPstmt[0]);
			psmt.setString(2, stringParamForPstmt[0]);
			psmt.setInt(3, Integer.parseInt(stringParamForPstmt[1]));
			psmt.setInt(4, Integer.parseInt(stringParamForPstmt[2]));
			rs = psmt.executeQuery();
			result = mkDTOs(rs);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DataUtil.resourceClose(rs, psmt, conn);
		}
		return result;
	}

	@Override
	public DTO[] selectBySQL(String sql) throws SQLException {
		DTO[] result = null;

		try {
			conn = DataUtil.getConnection();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			result = mkDTOs(rs);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DataUtil.resourceClose(rs, psmt, conn);
		}
		return result;
	}

	// ResultSet => DTO
	@Override
	public DTO mkDTO(ResultSet rs) throws SQLException {
		long postId = rs.getLong("POST_ID");
		String title = rs.getString("TITLE");
		String writer = rs.getString("ID");
		String category = rs.getString("CATEGORY");

		Date date = rs.getDate("REGDATE");
		Time time = rs.getTime("REGDATE");

		String strDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
		String strTime = new SimpleDateFormat("HH:mm").format(time);

		String meridiem = "오전";
		int integerTime = Integer.parseInt(strTime.substring(0, 2));
		if (integerTime > 12) {
			meridiem = "오후";
			integerTime -= 12;
		} // end if
		String regDate = strDate + " (" + meridiem + ")" + integerTime + strTime.substring(2);

		String realFileName = rs.getString("REAL_FILENAME");
		long empathizeCnt = rs.getLong("EMPATHIZE_CNT");
		long viewCnt = rs.getLong("VIEW_CNT");
		long dataLength = rs.getLong("DATALENGTH");

		DTO dto = new TestDTO(postId, title, writer, category, regDate, realFileName, empathizeCnt, viewCnt,
				dataLength);

		return dto;
	}// end mkDTO

	// ResultSet => DTOs
	@Override
	public DTO[] mkDTOs(ResultSet rs) throws SQLException {
		DTO[] arr = null;
		ArrayList<DTO> list = new ArrayList<DTO>();

		while (rs.next()) {
			list.add(mkDTO(rs));
		}
		int size = list.size();
		if (size == 0)
			return null;
		arr = new DTO[size];
		list.toArray(arr);

		return arr;
	}// end mkDTOs()

	@Override
	public DTO[] selectBySQL(String sql, String stringParamForPstmt) throws SQLException {
		return null;
	}

	@Override
	public void close() throws SQLException {
	}

}
