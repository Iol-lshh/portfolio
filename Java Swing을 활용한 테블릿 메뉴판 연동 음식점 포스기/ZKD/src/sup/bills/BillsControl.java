package sup.bills;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import aaaaaaaaaaaaaaa.InitData;
import hong.table.TableMainController;
import sup.menu.OrderListDBControl;
import sup.menu.OrderMenu;

public class BillsControl {
	
	
	public BillsView billsMain;
	public Bills bills;
	ArrayList<OrderedMenusLable> orderedMenus;
	String ip;
	String tableNum;
	TableMainController tbControl;
	
	
	public BillsControl(String tableNum, String ip, TableMainController tbControl) {
		this.tableNum = tableNum;
		this.ip=ip;
		this.tbControl = tbControl;
		bills = new Bills();
		bills.orderMenus =  new OrderListDBControl(ip).list(tableNum);
		billsMain = new BillsView(this);

		addOrderList();
		bills.sum();
		setTot();
	}
	void cancelOrder(OrderMenu orderMenu) {
		int i = 0;

		System.out.println(orderMenu+"메뉴");
		new OrderListDBControl(InitData.ip).del_menu(orderMenu);

		bills.orderMenus = new OrderListDBControl(ip).list(tableNum);
		changeOrderMenu();
		billsMain.panel_orders.setVisible(false);
		billsMain.panel_orders.setVisible(true);
		tbControl.defaultClient.sendMessage(tableNum, "주문", "주방","카운터");

	}
	void setTot() {
		billsMain.totLable.setText(bills.sumPrice+"");
	}
	public void addOrderList() {
		orderedMenus = new ArrayList<OrderedMenusLable>();
		bills.orderMenus =  new OrderListDBControl(ip).list(tableNum);
		for (OrderMenu orderMenu : bills.orderMenus) {
			System.out.println(orderMenu);
			OrderedMenusLable orderedMenusLable = new OrderedMenusLable(billsMain,orderMenu);
			orderedMenus.add(orderedMenusLable);
		}
	}

	public void receiveMessage() {
		//		bills.orderMenus = new OrderListDBControl(ip).list(tableNum);
		bills.orderMenus = new OrderListDBControl(ip).list(tableNum);
		changeOrderMenu();

		
	}

	public void changeOrderMenu() {

		//		orderedMenus = new ArrayList<OrderedMenusLable>();

		billsMain.scrollPane.remove(billsMain.panel_2);
		billsMain.panel_orders.remove(billsMain.scrollPane);
		billsMain.panel_2 = new JPanel(new GridLayout(30, 1, 0, 0));
		for (OrderMenu orderMenu : bills.orderMenus) {

			OrderedMenusLable omL = new OrderedMenusLable(billsMain, orderMenu);

		}
		bills.sum();
		setTot();
		billsMain.scrollPane = new JScrollPane(billsMain.panel_2);
		billsMain.panel_orders.add(billsMain.scrollPane);
		billsMain.getContentPane().setVisible(false);
		billsMain.getContentPane().setVisible(true);

	}
	void closeBills() {
		billsMain.setVisible(false);
	}
	
	boolean listCheck(OrderMenu order) {

		for (OrderMenu you : bills.orderMenus) {
			if(order.getMenuName().equals(you.getMenuName())&&order.getTimestamp().equals(you.getTimestamp())) {

				return true;
			}

		}
		return false;
	}
	
	public void changeOrderMenuLable() {
		int i = 0;
		ArrayList<OrderMenu> orders=new OrderListDBControl(ip).list(tableNum);
		for (OrderMenu oms : orders) {
			if(listCheck(oms)) {
				OrderedMenusLable oml = orderedMenus.get(i);
				oml.menuState.setText(oms.getState());
				orderedMenus.set(i, oml);

			}else {
				OrderedMenusLable om2 = new OrderedMenusLable(billsMain, oms);
				orderedMenus.add(om2);
			}
			i++;
		}
	}

}
