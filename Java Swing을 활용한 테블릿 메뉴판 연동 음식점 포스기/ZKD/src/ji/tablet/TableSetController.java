package ji.tablet;

import javax.swing.JFrame;

import hong.table.TableMainController;

public class TableSetController{
	public TableSetUpFrame tableSetUpFrame;
//	ChangeTableSetUpFrame changeTableSetUpFrame;
	TabletDAO tabletDAO;
	TabletDTO myTabletDTO;
	TableMainController tableMaincontroller;
	String clientName;
	public TableSetController(TableMainController tableMaincontroller, String clientName) {
		this.clientName = clientName;
		this.tableMaincontroller = tableMaincontroller;
		myTabletDTO = new TabletDTO();
		init();
	}
	public TableSetController() {
	
		init();
		reset();
	}
	void init() {
	
		tableSetUpFrame=new TableSetUpFrame(this);
		tableSetUpFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
//		changeTableSetUpFrame = new ChangeTableSetUpFrame(this);
		tabletDAO=new TabletDAO();
	}

	//닉네임&컨셉 설정하기
	void setNickNameNConcept(String tT_nickname, String tT_concept,String tT_name) {
		myTabletDTO.setTT_nickname(tT_nickname);
		myTabletDTO.setTT_concept(tT_concept);
		myTabletDTO.setTT_name(tT_name);
		System.out.println(tabletDAO.modify(myTabletDTO));
	}
	
	//닉네임&컨셉 바꾸기
	void changeTableSetUp(String tT_nickname, String tT_concept, String tT_name) {
		myTabletDTO.setTT_nickname(tT_nickname);
		myTabletDTO.setTT_concept(tT_concept);
		myTabletDTO.setTT_name(tT_name);
		System.out.println(tabletDAO.modify(myTabletDTO));
	}
	public void refreshTable(String nickName, String t_concept) {
		tableMaincontroller.tableFrame.refreshMyTableSetting(nickName,t_concept);
	}
	public void unLock() {
		System.out.println("Dfdfdff");
		tableSetUpFrame.pass.setEnabled(true);
		tableSetUpFrame.table_SetUp_Bnt.setEnabled(true);
	}
	void reset() {// 토글버튼 초기화
		tableSetUpFrame.bg1.clearSelection();
//		changeTableSetUpFrame.bg1.clearSelection();
	}
}
