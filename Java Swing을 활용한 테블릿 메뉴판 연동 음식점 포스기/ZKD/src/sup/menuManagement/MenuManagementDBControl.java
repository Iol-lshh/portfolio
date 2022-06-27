package sup.menuManagement;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import aaaaaaaaaaaaaaa.InitData;
import oracle.sql.BLOB;
import sup.menu.Menu;
import sup.menu.MenuMainView;

public class MenuManagementDBControl {
	Connection con;
	Statement stmt;
	ResultSet rs;
	String sql;
	
	public MenuManagementDBControl() {
		
		try {
			con = DriverManager.getConnection("jdbc:oracle:thin:@"+InitData.ip+":1521:xe", "hr", "hr");
			stmt = con.createStatement();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
//	public String createMenu_code(String menu_catag) {
//		String code="";
//		int code1 = 1;
//		if(menu_catag.equals("안주")) {
//			code="A";
//			for (int i = 1; i <= new MenuManagementDBControl().Alist().size()+1; i++) {				
//				code1=i;
//			}
//		}
//		if(menu_catag.equals("술")) {
//			code="B";
//			for (int i = 1; i <= new MenuManagementDBControl().Blist().size()+1; i++) {				
//				code1=i;
//			}
//		}
//		DecimalFormat df = new DecimalFormat("000");
//		
//		code += df.format(code1);
//		System.out.println(code);
//		return code;
//	}
	public void create_menu(String menu_name, String menu_catag, int menu_price, File image) {
		sql = "insert into menu (menu_name, menu_catag, menu_price, menu_img) "
				+ "values(?,?,?,?)";
		
		try {
			System.out.println(image.getName());
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, menu_name);
			pstmt.setString(2, menu_catag);
			pstmt.setInt(3, menu_price);
			pstmt.setString(4, "img2/"+image.getName());
			pstmt.executeUpdate();
			
			pstmt.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
	}
	public void del_menu(String menu_name) {
		
		sql = "delete from menu where menu_name = '"+menu_name+"'";
		
		try {
			stmt.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			close();
		}
	}
	public void update_menu(Menu menu) {
		InputStream fis = null;
		sql = "update menu set menu_name=?, menu_price= ? , menu_catag=?, menu_img=? where menu_name='"+menu.getMenu_name()+"'";

		try {
			
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, menu.getMenu_name());
			pstmt.setInt(2, menu.getMenu_price());
			pstmt.setString(3, menu.getMenu_catag());
			pstmt.executeUpdate();
			
			fis.close();
			pstmt.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close();
		}
	}
	public ArrayList<Menu> list2() {
		ArrayList<Menu> res = new ArrayList<Menu>();
		sql = "select * from menu";

		try {
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				Menu menuDto = new Menu(rs.getString("menu_name"),
										rs.getString("menu_catag"),rs.getInt("menu_price"),
										rs.getString("menu_img"));
			
				res.add(menuDto);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close();
		}
		return res;
	}

	
	public void update_menu2(String menu_name,Menu menu) {
		InputStream fis = null;
		sql = "update menu set menu_name=?, menu_price= ? , menu_catag=?, menu_img = ? where menu_name='"+menu_name+"'";
		try {
			
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, menu.getMenu_name());
			pstmt.setInt(2, menu.getMenu_price());
			pstmt.setString(3, menu.getMenu_catag());
			pstmt.setString(4,"img2/"+menu.getImageF().substring(menu.getImageF().lastIndexOf("\\")+1));
			pstmt.executeUpdate();
			pstmt.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close();
		}
	}
	
	public ArrayList<Menu> Alist(){
		ArrayList<Menu> res = new ArrayList<Menu>();
		sql = "select * from menu where menu_catag = '안주'";

		try {
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				Menu menuDto = new Menu(rs.getString("menu_name"),
										rs.getString("menu_catag"),rs.getInt("menu_price"),
										rs.getString("menu_img"));
				res.add(menuDto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}

		return res;
	}
	public ArrayList<Menu> Blist(){
		ArrayList<Menu> res = new ArrayList<Menu>();
		sql = "select * from menu where menu_catag = '술'";

		try {
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				Menu menuDto = new Menu(rs.getString("menu_name"),
										rs.getString("menu_catag"),rs.getInt("menu_price"),
										rs.getString("menu_img"));
				res.add(menuDto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}

		return res;
	}
	public void close() {
		if(rs!=null)try {rs.close();} catch (SQLException e) {e.printStackTrace();}
		if(stmt!=null)try {stmt.close();} catch (SQLException e) {e.printStackTrace();}
		if(con!=null)try {con.close();} catch (SQLException e) {e.printStackTrace();}

	}
}
