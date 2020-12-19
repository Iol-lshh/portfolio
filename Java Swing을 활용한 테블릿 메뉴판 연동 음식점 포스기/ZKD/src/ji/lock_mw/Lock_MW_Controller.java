package ji.lock_mw;

import an.tableBoard.TableBoardControl;

public class Lock_MW_Controller {

	Lock_MW_Frame lockMWFrame;
	Lock_MW_DTO lockMWDTO;
	TableBoardControl boardControl;
	String tableNum;
	public Lock_MW_Controller(TableBoardControl boardControl, String tableNum) {
		this.boardControl = boardControl;
		this.tableNum = tableNum;
		lockMWDTO = new Lock_MW_DTO();
		init();
	}

	public Lock_MW_Controller(Lock_MW_DTO lockMWDTO) {
		this.lockMWDTO = lockMWDTO;
		init();
	}

	void init() {
		lockMWFrame = new Lock_MW_Frame(this,tableNum);
		
	}
	public void register (String tt,String tt2) {
		
		
//		if(tableNum == "table_1") {		
//			boardView.lblNewLabel.setText(tt);
//			boardView.lblNewLabel_1.setText(tt2);
//		}
		
		
		
		switch (tableNum) {
		case "테이블_1":
			
			boardControl.boardView.manCnt1.setText(tt);
			boardControl.boardView.womanCnt1.setText(tt2);
			break;
		
		case "테이블_2":
//			System.out.println(boardView.lblNewLabel_4);
			boardControl.boardView.manCnt2.setText(tt);
			boardControl.boardView.womanCnt2.setText(tt2);

			break;
			
		case "테이블_3":
			
			boardControl.boardView.manCnt3.setText(tt);
			boardControl.boardView.womanCnt3.setText(tt2);
			
			
			break;

		case "테이블_4":
			boardControl.boardView.manCnt4.setText(tt);
			boardControl.boardView.womanCnt4.setText(tt2);
			
			break;
		
		case "테이블_5":
			boardControl.boardView.manCnt5.setText(tt);
			boardControl.boardView.womanCnt5.setText(tt2);
			
			break;
			
		case "테이블_6":
			boardControl.boardView.manCnt6.setText(tt);
			boardControl.boardView.womanCnt6.setText(tt2);
		
			break;
			
		case "테이블_7":
			boardControl.boardView.manCnt7.setText(tt);
			boardControl.boardView.womanCnt7.setText(tt2);
			break;
			
		case "테이블_8":
			boardControl.boardView.manCnt8.setText(tt);
			boardControl.boardView.womanCnt8.setText(tt2);
			break;
			}

		}
	void setMW(String tableNum,Integer tT_man, Integer tT_woman) {
		Lock_MW_DTO lockMWDTO = new Lock_MW_DTO();
		lockMWDTO.setTT_name(tableNum);
		lockMWDTO.setTT_man(tT_man);
		lockMWDTO.setTT_woman(tT_woman);
		System.out.println(new Lock_MW_DAO().modify(lockMWDTO));
		boardControl.client.sendMessage(tableNum, "수정", "테이블_1","테이블_2","테이블_3","테이블_4","테이블_5","테이블_6","테이블_7","테이블_8");
		boardControl.client.sendMessage("락 해제 해줭", "락성비", tableNum);
	}

}
