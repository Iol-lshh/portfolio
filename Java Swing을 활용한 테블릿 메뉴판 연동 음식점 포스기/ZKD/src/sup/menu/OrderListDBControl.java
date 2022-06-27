package sup.menu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

import aaaaaaaaaaaaaaa.InitData;
import an.tableBoard.PaymentDTO;
import sup.kitchen.Order;
import sup.bills.Bills;
import sup.menuManagement.MenuManagementDBControl;

public class OrderListDBControl {
	Connection con;
	Statement stmt;
	ResultSet rs;
	String sql;

//	MenuMainController viewMenuMainController;
	public OrderListDBControl(String ip) {
//		this.viewMenuMainController = viewMenuMainController;
		try {
			con = DriverManager.getConnection("jdbc:oracle:thin:@"+InitData.ip+":1521:xe", "hr", "hr");
			stmt = con.createStatement();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	public void billsInsert(PaymentDTO orderMenu, String tableNum) {
		System.out.println("주문인데 들어가니???>>>>> "+orderMenu.getBills_orderedmenu());
		
		sql = "insert into billslist (bills_tablenum, bills_orderedmenu, bills_PRICE, bills_COUNT) "
				+ "values('"+tableNum+"','"+orderMenu.getBills_orderedmenu()+"',"+orderMenu.getBills_price()+","+orderMenu.getBills_count()+")";
		try {
			stmt.executeUpdate(sql);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
	}
	public void insert(OrderMenu orderMenu, String tableNum) {
		System.out.println(orderMenu.toString());
		System.out.println(tableNum);
		if(orderMenu.getType().equals("술")) {
			orderMenu.setState("픽업");
		}
		sql = "insert into OrderList (ord_tablenum, ord_orderedmenu, ORD_PRICE, ORD_COUNT,ORD_cancel, ORD_state, ORD_type,ord_pickup) "
				+ "values('"+tableNum+"','"+orderMenu.menuName+"',"+orderMenu.price+","+orderMenu.cnt+","+orderMenu.cancel+",'"+orderMenu.state+"','"+orderMenu.type+
				"', "+orderMenu.pickup+")";
		System.out.println("orderMenu.pickup"+orderMenu.pickup);
		try {
			stmt.executeUpdate(sql);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
	}
	public void del_menu(OrderMenu om) {
		
		sql = "delete from OrderList where ord_orderedmenu = '"+om.menuName+"' and ord_time = '"+om.timestamp+"'";
		
		try {
			stmt.executeUpdate(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close();
		}
	}
	public void del_table(String tableNum) {
		
		sql = "delete from OrderList where ord_tableNum = '"+tableNum+"'";
		
		try {
			stmt.executeUpdate(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close();
		}
	}
	public void updateState(Order order) {
		sql = "update orderlist set ord_cancel = "+order.getCancel()+", ord_state = '"+order.getMenu_state()+"' where ord_tablenum = '"
				+order.getTable_num()+"' and ord_orderedmenu = '"+order.getMenu_Name()+"'"
				+"and ord_time = '"+order.getTime()+"'";
		
		try {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
	}
	public void updatePickup(OrderMenu orderMenu, String tableName) {
		sql = "update orderlist set ord_pickup = "+orderMenu.getPickup()+", ord_cancel = "+orderMenu.getCancel()+" where ord_tablenum = '"
				+tableName+"' and ord_orderedmenu = '"+orderMenu.menuName+"'"
				+"and ord_time = '"+orderMenu.getTimestamp()+"'";
		
		try {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
	}
	public ArrayList<OrderMenu> list(String tableNum){
		ArrayList<OrderMenu> res = new ArrayList<OrderMenu>();
		sql = "select * from OrderList where ord_tablenum='"+tableNum+"'";
		Bills bills = new Bills();
		try {
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				OrderMenu om = new OrderMenu(Integer.parseInt(rs.getString("ord_count")));
				om.menuName = rs.getString("ord_orderedmenu");
				om.price = Integer.parseInt(rs.getString("ord_price"));
				om.timestamp = rs.getString("ord_time");
				om.state = rs.getString("ord_state");
				om.cancel = rs.getInt("ord_cancel");
				om.pickup = rs.getInt("ord_pickup");
				om.sum();
				res.add(om);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}

		return res;
	}
	public ArrayList<OrderMenu> pickuplist(String tableNum){
		ArrayList<OrderMenu> res = new ArrayList<OrderMenu>();
		sql = "select * from OrderList where ord_tablenum='"+tableNum+"' and ord_pickup = 1";
		Bills bills = new Bills();
		try {
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				OrderMenu om = new OrderMenu(Integer.parseInt(rs.getString("ord_count")));
				om.menuName = rs.getString("ord_orderedmenu");
				om.price = Integer.parseInt(rs.getString("ord_price"));
				om.timestamp = rs.getString("ord_time");
				om.state = rs.getString("ord_state");
				om.cancel = rs.getInt("ord_cancel");
				om.pickup = rs.getInt("ord_pickup");
				om.type  = rs.getString("ord_type");
				om.sum();
				res.add(om);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}

		return res;
	}
	public OrderMenu detail(Object ordermenu, String tableNum) {
		OrderMenu om = (OrderMenu)ordermenu;
		sql = "select * from OrderList where ord_tablenum ='"+tableNum+"' and ord_orderedmenu = '"+om.getMenuName()+"'and ord_time = '"+om.getTimestamp()+"'";
		try {
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				om.setState(rs.getString("ord_state"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return om;
	}
	public  ArrayList<Order> kList() {
		ArrayList<Order> om = new ArrayList<Order>();
		sql = "select * from OrderList where ord_type = '안주' and (ord_state = '조리대기' or ord_state = '조리중') order by ord_time";
		try {
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				Order order = new Order();
				order.setTable_num(rs.getString("ord_tablenum"));
				order.setMenu_Name(rs.getString("ord_orderedmenu"));
				order.setMenu_state(rs.getString("ord_state"));
				order.setMenu_cnt(rs.getInt("ord_count"));
				order.setTime(rs.getString("ord_time"));
				order.setCancel(rs.getInt("ord_cancel"));
				om.add(order);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return om;
	}
	public void close() {
		if(rs!=null)try {rs.close();} catch (SQLException e) {e.printStackTrace();}
		if(stmt!=null)try {stmt.close();} catch (SQLException e) {e.printStackTrace();}
		if(con!=null)try {con.close();} catch (SQLException e) {e.printStackTrace();}

	}
}
