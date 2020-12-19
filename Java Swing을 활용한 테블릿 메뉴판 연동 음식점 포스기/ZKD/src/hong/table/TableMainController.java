package hong.table;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Menu;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import aaaaaaaaaaaaaaa.InitData;
import hong.client.DefaultClient;
import hong.client.ReceiverObjFromClient;
import hong.server.MessageObject;
import hong.table.TableFrame.TablePane;
import hwi.game.GameMainController;
import ji.tablet.TableSetController;
import ji.tablet.TableSetController2;
import ji.tablet.TabletDAO;
import ji.tablet.TabletDTO;
import sup.bills.BillsControl;
import sup.menu.MenuMainController;
import sup.menuManagement.MenuManagementDBControl;

public class TableMainController {
	public TableFrame tableFrame; // 뷰
	MenuMainController menuPan;
	TableMainInform tableMainInform;
	TableSetController tableSetController;
	TableSetController2 tableSetController2;
	public MessageReceiver messageReceiver;
	public String clientName; // 테이블 이름 입력
	public DefaultClient defaultClient;
	public BillsControl billsControl;


	boolean isLock;
	GameMainController gm_1;//신청 할때

	public TableFrame getTableFrame() {
		return tableFrame;
	}

	public void setTableFrame(TableFrame tableFrame) {
		this.tableFrame = tableFrame;
	}

	public TableMainInform getTableMainInform() {
		return tableMainInform;
	}


	public void setTableMainInform(TableMainInform tableMainInform) {
		this.tableMainInform = tableMainInform;
	}

	public MessageReceiver getMessageReceiver() {
		return messageReceiver;
	}

	public void setMessageReceiver(MessageReceiver messageReceiver) {
		this.messageReceiver = messageReceiver;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public DefaultClient getDefaultClient() {
		return defaultClient;
	}


	public TableMainController(String clientName) {
		//여기인가 frame에서 해야하는가
		new TabletDAO().modify(new TabletDTO(), clientName);
		this.clientName = clientName;
		messageReceiver = new MessageReceiver(); // 프로그램이 켜지면 서버와 자동 연결
		tableFrame = new TableFrame(this);
		tableFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		tableSetController = new TableSetController(this, clientName); // 테이블 락
		billsControl = new BillsControl(clientName, InitData.ip, this);
	}

	// 테이블 세팅
	void tableSetting() {
		tableSetController2 = new TableSetController2(this, clientName);
	}

	// 직원 호출
	void callWaiter() {
		defaultClient.sendMessage(clientName, "직원호출", "카운터");
	}

	// 메뉴판
	void orderMenu() {
		// new MenuMainController(tableMainInform.getTableName(), defaultClient);
		menuPan = new MenuMainController(clientName, defaultClient, this);
	}

	// 계산서
	void openBill() {
		if (billsControl == null) {
			billsControl.billsMain.setVisible(true);
		} else {
			billsControl.billsMain.setVisible(true);
		}
	}
	void letsGame(String you) {
		/// 상대방에게 신청
		defaultClient.sendMessage(tableFrame.nickNameLab.getText(), "게임신청", you);

		btnEnable();

		///
		gm_1 = new GameMainController(defaultClient, clientName,you);

	}
	void btnEnable() {
		for (TablePane tp : tableFrame.tablePanes) {
			System.out.println("버튼 비활성화>>>>>>>>>>");
			tp.urGameBnt.setEnabled(false);
		}
	}

	public void openBtn() {
		tableFrame.setUpBtn.setEnabled(true);
		tableFrame.billBtn.setEnabled(true);
		tableFrame.menuPanBtn.setEnabled(true);
		tableFrame.staffCallBtn.setEnabled(true);
		System.out.println("tableFrame.tablePanes : " + tableFrame.tablePanes);

	}

	public void openTable() {
		System.out.println("tableSetController:" + tableSetController);
		tableSetController.tableSetUpFrame.pass.setEnabled(true);
		tableSetController.tableSetUpFrame.table_SetUp_Bnt.setEnabled(true);
	}

	public void refreshAllTable() {
		System.out.println(clientName);
		ArrayList<TabletDTO> tabletInfo = new TabletDAO().list();
		//////////////////////////////

		tableFrame.tablePanes.removeAll(tableFrame.tablePanes);
		tableFrame.tablePanes = new ArrayList<TablePane>();
		del();
		for (TabletDTO tabletDTO : tabletInfo) {
			if (!tabletDTO.getTT_name().equals(clientName)) {
				tableFrame.mkTablePane(tabletDTO.getTT_name(), tabletDTO.getTT_nickname(), tabletDTO.getTT_concept(),
						tabletDTO.getTT_man(), tabletDTO.getTT_woman(),tabletDTO.getTT_do());
			}else {
				tableFrame.manCntLab.setText(tabletDTO.getTT_man()+"");
				tableFrame.womanCntLab.setText(tabletDTO.getTT_woman()+"");
			}
		}

		tableFrame.panel_3.setVisible(false);
		tableFrame.panel_3.setVisible(true);

	}

	void del() {
		tableFrame.scrollPane.remove(tableFrame.tableListPanel);
		tableFrame.panel_3.remove(tableFrame.scrollPane);
		tableFrame.scrollPane = new JScrollPane();
		tableFrame.scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		tableFrame.scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		tableFrame.panel_3.add(tableFrame.scrollPane, BorderLayout.CENTER);
		tableFrame.tableListPanel = new JPanel();
		tableFrame.tableListPanel.setLayout(new GridLayout(8, 1));
		tableFrame.scrollPane.setViewportView(tableFrame.tableListPanel);
	}

	void tableReSet() {

		tableFrame.dispose();
		tableFrame = new TableFrame(this);
		billsControl.receiveMessage();
		tableSetController = new TableSetController(this, clientName);
	}

	class MessageReceiver implements ReceiverObjFromClient {
		// DefaultClient defaultClient;

		public MessageReceiver() {
			defaultClient = new DefaultClient(clientName, this, InitData.ip, 7777);
		}

		void sendMessage(Object msgMain, String type, String... receivers) {
			defaultClient.sendMessage(msgMain, type, receivers);
		}

		@Override
		public void getMsgObjectFromClient(MessageObject msgObject) {
			// 받은 메세지 type 별로 나누어 함수 실행 할것.
			if(msgObject.getType().equals("락성비")) {
				isLock=true;
				openTable();
			}
			else if(isLock){
				switch (msgObject.getType()) {
				case "메뉴갱신":
					System.out.println("메뉴 변경이다ㅣ>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
					menuPan.menuChange();
					break;
				case "게임신청"://게임 신청 받을 때,
					/// 수락 / 거절 창이 있어야 함
					// 수락 거절시 상대방에게도 회신해야 함
					// ~ 수락했다 치고 일단 바로 게임창 열림
					new GameMainController(defaultClient, clientName, msgObject);
					break;
				case "게임수락":
					gm_1.doAfterReply(true,msgObject);
					break;
				case "게임거절" ://게임 신청했는데 거절 받았을 때,
					gm_1.doAfterReply(false,msgObject);
					gm_1=null;
					break;
				case "강제종료":
				case "테이블상태변경":
					refreshAllTable();
					break;
				case "결제완료":
					tableReSet();
					isLock=false;
					break;
				case "주문":
					billsControl.receiveMessage();
					break;

				case "수정":
					if(!msgObject.equals(clientName)) {
						refreshAllTable();					
					}
					break;
				case "게임버튼비활성화":
					btnEnable();
					break;
				}
			}

		}
	}
}
