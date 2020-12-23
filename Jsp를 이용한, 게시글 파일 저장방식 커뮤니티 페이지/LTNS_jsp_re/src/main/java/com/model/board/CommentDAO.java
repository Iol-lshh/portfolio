package main.java.com.model.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import main.java.com.util.DataUtil;
import main.java.com.util.LogUtil;

public class CommentDAO {

	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;

	final static String INSERT_COMMENT = "INSERT INTO COMMENT_TABLE (COMMENT_ID, COMMENT_CONTENTS, WRITER, POST_ID, REGDATE) VALUES (SEQ_comment_table_comment_id.NEXTVAL, ?, ?, ?, sysdate)";
	final static String SELECT_COMMENT_BY_CCOMMENTID = "SELECT * FROM COMMENT_TABLE c"
			+ " JOIN MM_TABLE m ON c.WRITER = m.MM_ID"
			+" WHERE COMMENT_ID = ?";
	final static String SELECT_COMMENT_BY_POSTID_ALL = "SELECT * FROM COMMENT_TABLE c"
			+ " JOIN MM_TABLE m ON c.WRITER = m.MM_ID"
			+" WHERE POST_ID = ?";
	final static String SELECT_COMMENT_BY_POSTID_PAGE = "SELECT * FROM" + "(SELECT ROWNUM AS NO, t.* FROM"
			+ " (SELECT * FROM COMMENT_TABLE c" + " JOIN MM_TABLE m ON c.WRITER = m.MM_ID " + " WHERE POST_ID = ?"
			+ " ORDER BY REGDATE DESC, NICKNAME DESC, COMMENT_ID DESC, WRITER DESC) t)" + " WHERE NO >= ? AND NO <= ?";
	final static String UPDATE_COMMENT_BY_COMMENTID = "UPDATE COMMENT_TABLE SET COMMENT_CONTENTS = ? WHERE COMMENT_ID = ?";
	final static String DELETE_COMMENT_BY_COMMENTID = "DELETE FROM COMMENT_TABLE WHERE COMMENT_ID = ?";

	public CommentDAO() {
		this.conn = DataUtil.getConnection();
	}

	// create
	public int createComment(String content, int writer, int postId) {

		String sql = INSERT_COMMENT;
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, content);
			psmt.setInt(2, writer);
			psmt.setInt(3, postId);
			return psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			LogUtil.error(e.getMessage());
		} finally {
			DataUtil.resourceClose(psmt, conn);
		} // end try
		return -1;
	} // end createComment()

	// read
	public ArrayList<CommentDTO> getCommendById(long commentId) {
		ArrayList<CommentDTO> list = null;
		String sql = SELECT_COMMENT_BY_CCOMMENTID;
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setLong(1, commentId);
			rs = psmt.executeQuery();
			list = createArray(rs);
		} catch (SQLException e) {
			e.printStackTrace();
			LogUtil.error(e.getMessage());
		} finally {
			DataUtil.resourceClose(psmt, conn);
		} // end try
		
		return list;
	} // end getAllCommentByPostId()
	
	// read
	public ArrayList<CommentDTO> getAllCommentByPostId(int postId) {
		ArrayList<CommentDTO> list = null;
		String sql = SELECT_COMMENT_BY_POSTID_ALL;

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, postId);
			rs = psmt.executeQuery();
			list = createArray(rs);
		} catch (SQLException e) {
			e.printStackTrace();
			LogUtil.error(e.getMessage());
		} finally {
			DataUtil.resourceClose(psmt, conn);
		} // end try

		return list;
	} // end getAllCommentByPostId()

	

	// read
	public ArrayList<CommentDTO> getPageCommentListByPostId(int postId, int page) {
		ArrayList<CommentDTO> list = null;
		String sql = SELECT_COMMENT_BY_POSTID_PAGE;
		int startNum = (page - 1) * 5 + 1;
		int endNum = page * 5;
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, postId);
			psmt.setInt(2, startNum);
			psmt.setInt(3, endNum);
			rs = psmt.executeQuery();
			list = createArray(rs);
		} catch (SQLException e) {
			e.printStackTrace();
			LogUtil.error(e.getMessage());
		} finally {
			DataUtil.resourceClose(psmt, conn);
		} // end try

		return list;
	} // end getPageCommentListByPostId()

	// update
	public int updateCommentByCommentId(String content, int commentId) {
		String sql = UPDATE_COMMENT_BY_COMMENTID;

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, content);
			psmt.setInt(2, commentId);
			return psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			LogUtil.error(e.getMessage());
		} finally {
			DataUtil.resourceClose(psmt, conn);
		} // end try

		return -1;
	} // end updateCommentByCommentId()

	// delete
	public int deleteCommentByCommentId(int commentId) {
		String sql = DELETE_COMMENT_BY_COMMENTID;

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, commentId);
			return psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			LogUtil.error(e.getMessage());
		} finally {
			DataUtil.resourceClose(psmt, conn);
		} // end try

		return -1;
	}

	public ArrayList<CommentDTO> createArray(ResultSet rs) {
		ArrayList<CommentDTO> list = new ArrayList<CommentDTO>();

		try {
			while (rs.next()) {
				CommentDTO dto = new CommentDTO();
				dto.setCommentId(rs.getLong("COMMENT_ID"));
				dto.setCommentContents(rs.getString("COMMENT_CONTENTS").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll(" ", "&nbsp;").replaceAll("\n", "<br />"));
				dto.setWriterId(rs.getLong("WRITER"));
				dto.setWriter(rs.getString("ID"));
				dto.setNickName(rs.getString("NICKNAME"));
				dto.setPostId(rs.getLong("POST_ID"));
				dto.setEmail(rs.getString("EMAIL"));
				dto.setGrade(rs.getString("GRADE"));

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
				dto.setRegdate(regDate);

				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			LogUtil.error(e.getMessage());
		} // end try
		return list;
	} // end createArray()

}
