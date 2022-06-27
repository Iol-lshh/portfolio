package ji.tablet;

import hong.table.TableMainController;

public class TableSetController2{
	TableSetUpFrame2 tableSetUpFrame2;
//	ChangeTableSetUpFrame changeTableSetUpFrame;
	TabletDAO tabletDAO;
	TabletDTO myTabletDTO;
	TableMainController tableMaincontroller;
	String clientName;
	public TableSetController2(TableMainController tableMaincontroller, String clientName) {
		this.clientName = clientName;
		this.tableMaincontroller = tableMaincontroller;
		myTabletDTO = new TabletDTO();
		init();
	}
	public TableSetController2() {
	
		init();
		reset();
	}
	void init() {
		tableSetUpFrame2=new TableSetUpFrame2(this);	
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
		tableSetUpFrame2.pass.setEnabled(true);
		tableSetUpFrame2.table_SetUp_Bnt.setEnabled(true);
	}
	void reset() {// 토글버튼 초기화
		tableSetUpFrame2.bg1.clearSelection();
//		changeTableSetUpFrame.bg1.clearSelection();
	}
	
}
