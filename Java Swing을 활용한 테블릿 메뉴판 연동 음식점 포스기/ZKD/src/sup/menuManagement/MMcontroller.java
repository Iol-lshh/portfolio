package sup.menuManagement;

import java.awt.event.WindowListener;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import an.adminMain.AdminMainControl;
import an.adminMain.AdminMainView;
import an.tableBoard.TableBoardControl;
import hong.client.DefaultClient;
import hong.client.ReceiverObjFromClient;
import hong.server.MessageObject;
import sup.menu.Menu;
import sup.menu.MenuMainView;

public class MMcontroller  {
	public AdminMainControl adminControl;
	public AdminMainView adminView;
	MenuManagementMain menuManagementMain;
	TableBoardControl tbc;
	ArrayList<Menu> MenuList;
//	String clientName = "카운터";
	public MMcontroller(int i) {
		
	}
	public MMcontroller(AdminMainView adminView,int port) {
		this.adminView = adminView;
		menuManagementMain = new MenuManagementMain(this,adminView);
		
		
	}
	JTable insertMenu(DefaultTableModel model, JTable menuTable) {
		MenuList = new MenuManagementDBControl().list2();	
		Vector<String> columnNames = new Vector<String>();
		columnNames.add("메뉴 이름");
		columnNames.add("메뉴 가격");
		columnNames.add("카테고리");
		model = new DefaultTableModel(columnNames,0);
		for (Menu menu : MenuList) {
			Vector<String> menuData = new 	Vector<String>();
			menuData.add(menu.getMenu_name());
			menuData.add(menu.getMenu_price()+"");
			menuData.add(menu.getMenu_catag());
			model.addRow(menuData);
		}
		
		if(menuTable != null) {
			
			menuTable.setModel(model);
		}else {
		    menuTable = new JTable(model);
		}
		return menuTable;
	}
	JTable refreshMeneegementList(DefaultTableModel model,JTable menuTable) {
		DefaultTableModel nModel = (DefaultTableModel) menuTable.getModel();
		nModel.setNumRows(0);
		menuTable =insertMenu(nModel,menuTable);
		
		nModel.fireTableDataChanged();
		return menuTable;
		
	}
	public File fileOpenDlg() {

		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		chooser.setCurrentDirectory(new File("/"));

		int returnVal = chooser.showOpenDialog(null);

		if(returnVal == JFileChooser.APPROVE_OPTION)  {
			
			File f = chooser.getSelectedFile();
			return f;
		}
		
		return null;
	}

	void open_menuModifyFream(MenuManagementMain MMGui, Menu menu) {
		new Menu_modify_Frame(this, menu, MMGui);
	}
	void open_menuInsertFrame(MenuManagementMain MMGui) {
		Menu_Insert_Frame e1 = new Menu_Insert_Frame(MMGui,this);
	}
	void create_menu(String menu_name, String menu_catag, int menu_price, File image) {
		System.out.println("image ++"+image);
		new MenuManagementDBControl().create_menu(menu_name, menu_catag, menu_price, image);
	}
	void update_menu(String menu_name,Menu menu) {
		new MenuManagementDBControl().update_menu2(menu_name,menu);
	}
	void del_menu(String menu_code) {
		new MenuManagementDBControl().del_menu(menu_code);
	}
	
	
//	@Override
//	public void getMsgObjectFromClient(MessageObject msgObject) {
//		
//		switch (msgObject.getType()) {
//		case "조리완료": 
	
//			System.out.println("여기");
//			JOptionPane.showMessageDialog(null, "조리완료");
//			break;
//		case "" :
//			break;
//		default:
//			break;
//		}
//		System.out.println(msgObject.getMessageMain());
//		
//	}
}
