package an.tableBoard;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import aaaaaaaaaaaaaaa.InitData;

public class PaymentDAO {

	Connection con;
	Statement stmt;
	ResultSet rs;
	
	String sql;
	String url ="jdbc:oracle:thin:@"+InitData.ip+":1521:xe";
	String id = "hr";
	String pw = "hr";
	
	public PaymentDAO() {
	
		try {
			con = DriverManager.getConnection(url,id,pw);
			
			stmt = con.createStatement();	
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}	
	}
	
	
	public ArrayList<PaymentDTO> payment(String tableNum) {
		
		ArrayList<PaymentDTO> res = new ArrayList<PaymentDTO>();
		sql = "select * from orderList where ord_tableNum='"+tableNum+"'";
		
		try {
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				PaymentDTO dto = new PaymentDTO();
				
				dto.bills_tablenum = rs.getString("ord_tablenum");
				dto.bills_orderedmenu = rs.getString("ord_orderedmenu");
				dto.bills_count = rs.getInt("ord_count");
				dto.bills_price = rs.getInt("ord_price");
			
				res.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}

		return res;
		
	}
		
	
	
	
	public void close ()  {
		if(rs!=null) try {rs.close();} catch (SQLException e) {}
		if(stmt!=null) try {stmt.close();} catch (SQLException e) {}
		if(con!=null) try {con.close();} catch (SQLException e) {}
	}

}
