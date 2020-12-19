package an.tableBoard;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;

import aaaaaaaaaaaaaaa.InitData;
import an.adminMain.AdminMainView;
import hong.client.DefaultClient;
import hong.client.ReceiverObjFromClient;
import hong.server.MessageObject;

public class TableBoardControl {
	public DefaultClient client;
	public TableBoardView boardView;
	ArrayList<JButton> orderBtn;
	public ArrayList<OrderListFrame> orderListFrame;
	public Receiver receiver;
	
	public TableBoardControl() {
		orderListFrame = new ArrayList<OrderListFrame>();
		orderBtn = new ArrayList<JButton>();
		boardView = new TableBoardView(this);
		receiver = new Receiver(this);
		this.client = new DefaultClient("카운터", receiver, InitData.ip, 7777);
	}
	
	
	class Receiver implements ReceiverObjFromClient{
		
		TableBoardControl boardControl;
		public Receiver(TableBoardControl boardControl) {
			super();
			
			this.boardControl = boardControl;
		}
		
		@Override
		public void getMsgObjectFromClient(MessageObject msgObject) {
			System.out.println(msgObject.getType());
			switch (msgObject.getType()) {
			case "조리완료": 
				System.out.println("어나ㅣ러아너리ㅏㅇ러니ㅏ");
				for (OrderListFrame olf : orderListFrame) {
					if(olf.tableName.equals(msgObject.getMessageMain())) {
						olf.refreshOrderList();
						boardControl.completeOrder(msgObject);
						break;
					}
				}
				
				break;
			case "직원호출" :
				boardControl.callStaff(msgObject);
				break;
			case "주문":
				System.out.println(">>>>>>>>>>>>>>>>>>"+msgObject.getMessageMain());
				for (OrderListFrame olf : orderListFrame) {
					if(olf.tableName.equals(msgObject.getMessageMain())) {
						olf.refreshOrderList();
						break;
					}
				}
				break;
			}
//			System.out.println(msgObject.getMessageMain());
			
		} //디비로 받아서 자바로 데이터를 뽑아 온 다음에,

//		@Override
//		public void getReceiveFileFromClient(Object o) {
//		}


	}

	public void reset(String tableNum) {
		client.sendMessage("결제 완료", "결제완료", tableNum);
		client.sendMessage("", "테이블상태변경", "테이블_1","테이블_2","테이블_3","테이블_4","테이블_5","테이블6","테이블_7","테이블_8");
	}
	public void callStaff (MessageObject msgObject) {
		if(msgObject.getMessageMain().equals("테이블_1")) {
			boardView.call1.setBackground(Color.orange);
		}
		if(msgObject.getMessageMain().equals("테이블_2")) {
			boardView.call2.setBackground(Color.orange);
		}
		if(msgObject.getMessageMain().equals("테이블_3")) {  
			boardView.call3.setBackground(Color.orange);
		}
		if(msgObject.getMessageMain().equals("테이블_4")) {
			boardView.call4.setBackground(Color.orange);
		}
		if(msgObject.getMessageMain().equals("테이블_5")) {
			boardView.call5.setBackground(Color.orange);
		}
		if(msgObject.getMessageMain().equals("테이블_6")) {
			boardView.call6.setBackground(Color.orange);
		}
		if(msgObject.getMessageMain().equals("테이블_7")) {
			boardView.call7.setBackground(Color.orange);
		}
		if(msgObject.getMessageMain().equals("테이블_8")) {
			boardView.call8.setBackground(Color.orange);
		}
	}
	public void completeOrder(MessageObject msgObject) {
		if(msgObject.getMessageMain().equals("테이블_1")) {
			orderBtn.get(0).setBackground(Color.red);
		}
		if(msgObject.getMessageMain().equals("테이블_2")) {
			orderBtn.get(1).setBackground(Color.red);
		}
		if(msgObject.getMessageMain().equals("테이블_3")) {
			orderBtn.get(2).setBackground(Color.red);
		}
		if(msgObject.getMessageMain().equals("테이블_4")) {
			orderBtn.get(3).setBackground(Color.red);
		}
		if(msgObject.getMessageMain().equals("테이블_5")) {
			orderBtn.get(4).setBackground(Color.red);
		}
		if(msgObject.getMessageMain().equals("테이블_6")) {
			orderBtn.get(5).setBackground(Color.red);
		}
		if(msgObject.getMessageMain().equals("테이블_7")) {
			orderBtn.get(6).setBackground(Color.red);
		}
		if(msgObject.getMessageMain().equals("테이블_8")) {
			orderBtn.get(7).setBackground(Color.red);;
		}
		boardView.order_p.setVisible(false);
		boardView.order_p.setVisible(true);
	}
}
