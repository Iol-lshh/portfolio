package sup.kitchen;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import aaaaaaaaaaaaaaa.InitData;
import hong.client.DefaultClient;
import hong.client.ReceiverObjFromClient;
import hong.server.MessageObject;
import sup.menu.OrderListDBControl;

public class KitchenMainController{
	ArrayList<Order> orderList = new ArrayList<Order>();
	ArrayList<orderPane> ords = new ArrayList<orderPane>();
	KitchenMainJFrame kitchenMainJFrame;
	
	Kitchen kitchen;
	String clientName= "주방";
	public KitchenMainController(){
		
		kitchenMainJFrame=new KitchenMainJFrame(this);
		kitchenMainJFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		kitchen = new Kitchen(clientName,7777, this);
		new_orderList();
		
	}
	class Kitchen implements ReceiverObjFromClient{
		
		DefaultClient df;
		private KitchenMainController control;
		
		public Kitchen(String clientName,  int serverPort ,KitchenMainController control) {
			df = new DefaultClient(clientName, this, InitData.ip, serverPort);
			this.control =control;
		}
		

		@Override
		public void getMsgObjectFromClient(MessageObject msgObject) {
			
			System.out.println("연섭연섭연섭"+msgObject.getType());
			
			
			control.refresh();
				
		}
		
	}
	
	
	public void new_orderList() {
		ArrayList<Order> orders = new OrderListDBControl(InitData.ip).kList();
		if(orderList.size()!=0) {
			System.out.println("와");
			
			for (Order order : orderList) {
				
				checkAdd(orders, order);
			}
		}else {
			orderList = orders;
		}
//		addOrderLable();
	}
	public void checkAdd(ArrayList<Order> orders , Order order) {
		for (Order order2 : orders) {
			if(!order.table_num.equals(order2.table_num) && order.menu_Name.equals(order2.menu_Name)
			   && order.time.equals(order2.time)) {
				System.out.println("에궁");
				orderList.add(order2);
			}
		}
	}
	public void checkAdd(ArrayList<Order> order2 , Order order, ArrayList<Order> orders) {
		for (Order ord : order2) {
			if(!order.table_num.equals(ord.table_num) && order.menu_Name.equals(ord.menu_Name)
			   && order.time.equals(ord.time)) {
				orders.add(ord);
			}
		}
	}
	public ArrayList<Order> checkOrder() {

		ArrayList<Order> orders = new ArrayList<Order>();
		if(ords.size()!=0) {
			for (orderPane orderL : ords) {
				System.out.println("왜안와>?");
				for (Order order : orderList) {
					checkAdd(orderList, order, orders);
				}
			}
		}else {
			orders = orderList;
		}
		
		return orders;
	}
	public void addOrderLable() {
		ArrayList<Order> orders = checkOrder();
		
		for (Order order : orders) {
			if(order.table_num.equals("테이블_1")) {
				System.out.println("들와");
				orderPane ol = new orderPane(order, this);
				
				ords.add(ol);
				kitchenMainJFrame.waiting_p.add(ol);
				kitchenMainJFrame.waiting_p.revalidate();
				kitchenMainJFrame.waiting_p.validate();
				kitchenMainJFrame.contentPane.revalidate();;
				kitchenMainJFrame.contentPane.repaint();
			}
			if(order.table_num.equals("테이블_2")) {
				System.out.println("들와");
				orderPane ol = new orderPane(order, this);
				
				ords.add(ol);
				kitchenMainJFrame.waiting_p.add(ol);
				kitchenMainJFrame.waiting_p.revalidate();
				kitchenMainJFrame.waiting_p.validate();
				kitchenMainJFrame.contentPane.revalidate();;
				kitchenMainJFrame.contentPane.repaint();
			}
			if(order.table_num.equals("테이블_3")) {
				System.out.println("들와");
				orderPane ol = new orderPane(order, this);
				
				ords.add(ol);
				kitchenMainJFrame.waiting_p.add(ol);
				kitchenMainJFrame.waiting_p.revalidate();
				kitchenMainJFrame.waiting_p.validate();
				kitchenMainJFrame.contentPane.revalidate();;
				kitchenMainJFrame.contentPane.repaint();
			}
			if(order.table_num.equals("테이블_4")) {
				System.out.println("들와");
				orderPane ol = new orderPane(order, this);
				
				ords.add(ol);
				kitchenMainJFrame.waiting_p.add(ol);
				kitchenMainJFrame.waiting_p.revalidate();
				kitchenMainJFrame.waiting_p.validate();
				kitchenMainJFrame.contentPane.revalidate();;
				kitchenMainJFrame.contentPane.repaint();
			}
			if(order.table_num.equals("테이블_5")) {
				System.out.println("들와");
				orderPane ol = new orderPane(order, this);
				
				ords.add(ol);
				kitchenMainJFrame.waiting_p.add(ol);
				kitchenMainJFrame.waiting_p.revalidate();
				kitchenMainJFrame.waiting_p.validate();
				kitchenMainJFrame.contentPane.revalidate();;
				kitchenMainJFrame.contentPane.repaint();
			}
			if(order.table_num.equals("테이블_6")) {
				System.out.println("들와");
				orderPane ol = new orderPane(order, this);
				
				ords.add(ol);
				kitchenMainJFrame.waiting_p.add(ol);
				kitchenMainJFrame.waiting_p.revalidate();
				kitchenMainJFrame.waiting_p.validate();
				kitchenMainJFrame.contentPane.revalidate();;
				kitchenMainJFrame.contentPane.repaint();
			}
			if(order.table_num.equals("테이블_7")) {
				System.out.println("들와");
				orderPane ol = new orderPane(order, this);
				
				ords.add(ol);
				kitchenMainJFrame.waiting_p.add(ol);
				kitchenMainJFrame.waiting_p.revalidate();
				kitchenMainJFrame.waiting_p.validate();
				kitchenMainJFrame.contentPane.revalidate();;
				kitchenMainJFrame.contentPane.repaint();
			}
			if(order.table_num.equals("테이블_8")) {
				System.out.println("들와");
				orderPane ol = new orderPane(order, this);
				
				ords.add(ol);
				kitchenMainJFrame.waiting_p.add(ol);
				kitchenMainJFrame.waiting_p.revalidate();
				kitchenMainJFrame.waiting_p.validate();
				kitchenMainJFrame.contentPane.revalidate();;
				kitchenMainJFrame.contentPane.repaint();
			}
		}
	}
//				kitchenMainJFrame.waiting_p.setVisible(false);
//				kitchenMainJFrame.waiting_p.setVisible(true);
	public void changeState(Order order, String state, orderPane op2) {
		if(order.menu_state.equals("조리대기")) {
			
			op2.menu_state.setText("조리중");
			order.setCancel(0);
			op2.setBackground(Color.red);
			order.setMenu_state("조리중");
			
			new OrderListDBControl(InitData.ip).updateState(order);
			
			System.out.println(order.table_num);
			kitchen.df.sendMessage(order.table_num, "주문", order.table_num,"카운터");
			
		}
		else if(order.menu_state.equals("조리중")) {
			op2.menu_state.setText("조리완료");
			order.setMenu_state("조리완료");
			op2.setBackground(Color.green);
			new OrderListDBControl(InitData.ip).updateState(order);
			kitchen.df.sendMessage(order.time, "주문", order.table_num);
			kitchen.df.sendMessage(order.table_num, "조리완료", "카운터");
			
			refresh();
		}
//		else if(order.menu_state.equals("조리완료")) {
//			orderList.remove(order);
//			kitchenMainJFrame.remove(kitchenMainJFrame.waiting_p);
//			kitchenMainJFrame.waiting_p = new JPanel();
//			for (Order order2 : orderList) {
//				orderPane ol = new orderPane(order2, this);
//				kitchenMainJFrame.waiting_p.add(ol);
//			}
//			kitchenMainJFrame.contentPane.add(kitchenMainJFrame.waiting_p);
//			kitchenMainJFrame.waiting_p.setVisible(false);
//			kitchenMainJFrame.waiting_p.setVisible(true);
//			
//		}
	}
	public void refresh() {
		System.out.println("들어오니 그게뭐여 그게뭐여 그게뭐여 세번써 왜안써 ");
		orderList = new OrderListDBControl(InitData.ip).kList();
		kitchenMainJFrame.remove(kitchenMainJFrame.waiting_p);
		kitchenMainJFrame.waiting_p = new JPanel();
		for (Order order2 : orderList) {
			orderPane ol = new orderPane(order2, this);
			kitchenMainJFrame.waiting_p.add(ol);
		}
		kitchenMainJFrame.contentPane.add(kitchenMainJFrame.waiting_p);
		kitchenMainJFrame.waiting_p.setVisible(false);
		kitchenMainJFrame.waiting_p.setVisible(true);
	}
		
}