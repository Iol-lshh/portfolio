package test;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DataUtil {

	public static Connection getConnection() {

		try {

			InitialContext initCon = new InitialContext();
			Context context = (Context) initCon.lookup("java:/comp/env");
			DataSource dataSource = (DataSource) context.lookup("jdbc/LTNS_jsp_re");
			return dataSource.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.error(e.getMessage());
		}
		return null;
	}

	public static Connection getLocalConnection() {

		Connection conn = null;
		
		try {
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "ltns";
			String password = "ltns";
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, user, password);
			return conn;
			
		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.error(e.getMessage());
		}
		return conn;
	}

	public static void resourceClose(PreparedStatement pstmt, Connection conn) {
		try {
			if (pstmt != null && !pstmt.isClosed())
				pstmt.close();
			if (conn != null && !conn.isClosed())
				conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.error(e.getMessage());
		}
	}

	public static void resourceClose(Statement stmt, Connection conn) {
		try {
			if (stmt != null && !stmt.isClosed())
				stmt.close();
			if (conn != null && !conn.isClosed())
				conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.error(e.getMessage());
		}
	}

	public static void resourceClose(ResultSet rs, PreparedStatement pstmt) {
		try {
			if (rs != null && !rs.isClosed())
				rs.close();
			if (pstmt != null && !pstmt.isClosed())
				pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.error(e.getMessage());
		}
	}

	public static void resourceClose(ResultSet rs, PreparedStatement pstmt, Connection conn) {
		try {
			if (rs != null && !rs.isClosed())
				rs.close();
			if (pstmt != null && !pstmt.isClosed())
				pstmt.close();
			if (conn != null && !conn.isClosed())
				conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.error(e.getMessage());
		}
	}

	public static void resourceClose(ResultSet rs, Statement stmt, Connection conn) {
		try {
			if (rs != null && !rs.isClosed())
				rs.close();
			if (stmt != null && !stmt.isClosed())
				stmt.close();
			if (conn != null && !conn.isClosed())
				conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.error(e.getMessage());
		}
	}

	public static void resourceClose(ResultSet rs, PreparedStatement pstmt, Statement stmt, Connection conn) {
		try {
			if (rs != null && !rs.isClosed())
				rs.close();
			if (pstmt != null && !pstmt.isClosed())
				pstmt.close();
			if (stmt != null && !stmt.isClosed())
				stmt.close();
			if (conn != null && !conn.isClosed())
				conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.error(e.getMessage());
		}
	}

}
