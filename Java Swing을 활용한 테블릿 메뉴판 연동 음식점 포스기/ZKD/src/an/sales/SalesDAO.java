package an.sales;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import sup.bills.Bills;

public class SalesDAO {

	ArrayList<SalesDTO> saleslist;
	SalesControl sc;
	Connection con;
	Statement stmt;
	ResultSet rs;

	String sql1;
	String sql2;
	String sql3;

	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String id = "hr";
	String pw = "hr";

	public SalesDAO() {

		try {
			con = DriverManager.getConnection(url, id, pw);      
			stmt = con.createStatement();


		} catch (SQLException e) {

			e.printStackTrace();
		}
	}
	public ArrayList<SalesDTO> list (SalesDTO dto) {

		ArrayList<SalesDTO> res = new ArrayList<SalesDTO>();

		sql1=	"select bills_time, bills_tablenum,bills_orderedmenu, bills_count," +
				"bills_count * bills_price as sumprice " + 
				
				" from billslist WHERE bills_time BETWEEN" +"'"+dto.getBefore()+"' "+"AND"+" '"+dto.getAfter()+"'" +
	            " order by bills_time ASC";
		
		System.out.println("sql1:"+sql1);
		try {
			rs = stmt.executeQuery(sql1);

			while(rs.next()) {

				SalesDTO sdto = new SalesDTO();

				sdto.setBILLS_TIME(rs.getTimestamp("BILLS_TIME"));
				sdto.setBILLS_TABLENUM(rs.getString("BILLS_TABLENUM"));
				sdto.setBILLS_ORDEREDMENU(rs.getString("BILLS_ORDEREDMENU"));
				sdto.setBILLS_COUNT(rs.getInt("BILLS_COUNT"));
				sdto.setBILLS_PRICE(rs.getInt("sumprice"));
				
				
				res.add(sdto);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}finally {
			close();
		}   
		return res;      
	}

	public ArrayList<SalesDTO> anjulist (SalesDTO dto) {

		ArrayList<SalesDTO> res = new ArrayList<SalesDTO>();

		sql2 =	" select bills_time, bills_tablenum, bills_orderedmenu, bills_count," +
				" bills_count * bills_price as sumprice " + 
				" from billslist  " +
				" WHERE bills_time BETWEEN " +"'"+dto.getBefore()+"' "+"AND"+" '"+dto.getAfter()+"'" +
	                         "and bills_orderedmenu in('Â«»Í','³ª°¡»çÅ°Â«»Í','°¨ÀÚÆ¢±è','°ñ¹ðÀÌ¹«Ä§','±èÄ¡Âî°³','ºÎ´ëÂî°³'," +
	                         "'µÎºÎ±èÄ¡','±èÀå±èÄ¡','¿ÀÀÏÆÄ½ºÅ¸','Á¦À°ººÀ½','±î¸£º¸³ª¶ó')" +
	                         "order by bills_time asc";
		System.out.println("sql2:"+sql2);

		try {
			rs = stmt.executeQuery(sql2);

			while(rs.next()) {

				SalesDTO sdto = new SalesDTO();

				sdto.setBILLS_TABLENUM(rs.getString("BILLS_TABLENUM"));
				sdto.setBILLS_ORDEREDMENU(rs.getString("BILLS_ORDEREDMENU"));
				sdto.setBILLS_COUNT(rs.getInt("BILLS_COUNT"));
				sdto.setBILLS_PRICE(rs.getInt("sumprice"));
				//sdto.sumprice();
				//sdto.setSumprice(rs.getInt("sumprice"));
				sdto.setBILLS_TIME(rs.getTimestamp("BILLS_TIME"));
				res.add(sdto);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}finally {
			close();
		}   
		return res;      
	}


	public ArrayList<SalesDTO> sullist (SalesDTO dto) {

		ArrayList<SalesDTO> res = new ArrayList<SalesDTO>();

		sql3 =	" select bills_time, bills_tablenum, bills_orderedmenu, bills_count," +
				" bills_count * bills_price as sumprice " + 
				" from billslist " +
				" WHERE bills_time BETWEEN" +"'"+dto.getBefore()+"' "+"AND"+" '"+dto.getAfter()+"'" +
	                         " and bills_orderedmenu in('Ã³À½Ã³·³','Å¬¶ó¿ìµå','ÀÙ»õÁÖ','Ä«½º','ÇÏÀÌÆ®','¸·°É¸®'," +
	                         "'ÁÁÀºµ¥ÀÌ','¸ÅÈ­¼ö','C1','º¹ºÐÀÚ','»ê»çÃá')" +
	                         " order by bills_time asc";
		
		System.out.println("sql3:"+sql3);
		try {
			rs = stmt.executeQuery(sql3);

			while(rs.next()) {

				SalesDTO sdto = new SalesDTO();

				sdto.setBILLS_TABLENUM(rs.getString("BILLS_TABLENUM"));
				sdto.setBILLS_ORDEREDMENU(rs.getString("BILLS_ORDEREDMENU"));
				sdto.setBILLS_COUNT(rs.getInt("BILLS_COUNT"));
				sdto.setBILLS_PRICE(rs.getInt("sumprice"));;	 //
				//sdto.setSumprice(rs.getInt("sumprice"));
				sdto.setBILLS_TIME(rs.getTimestamp("BILLS_TIME"));
				
				res.add(sdto);

			}

		} catch (SQLException e) {

			e.printStackTrace();
		}finally {
			close();
		}   
		return res;      
	}

	public void close() {   
		if(rs!=null) try {rs.close();} catch (SQLException e) {}
		if(stmt!=null) try {stmt.close();} catch (SQLException e) {}
		if(con!=null) try {con.close();} catch (SQLException e) {}

	}

}