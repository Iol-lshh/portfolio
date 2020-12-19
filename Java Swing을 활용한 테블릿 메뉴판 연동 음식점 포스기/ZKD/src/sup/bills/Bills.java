package sup.bills;

import java.util.ArrayList;

import sup.menu.OrderMenu;

public class Bills {
	int tableNum;
	ArrayList<OrderMenu> orderMenus;
	int sumPrice;
	boolean orderChk;
	public Bills() {
		super();
//		this.tableNum = tableNum;
		this.orderMenus = new ArrayList<OrderMenu>();
		this.orderChk = true;
		
	}
	public void sum() {
		int tot = 0;
		for (OrderMenu orderMenu : orderMenus) {
			tot += orderMenu.getPrice()*orderMenu.getCnt();
			
		}
		sumPrice = tot;
	}
	public ArrayList<OrderMenu> getOrderMenus() {
		return orderMenus;
	}
	public void setOrderMenus(ArrayList<OrderMenu> orderMenus) {
		this.orderMenus = orderMenus;
	}
}
