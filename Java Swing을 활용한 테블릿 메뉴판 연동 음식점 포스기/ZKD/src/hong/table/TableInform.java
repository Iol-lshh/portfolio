package hong.table;

import java.util.HashMap;


//내 테이블의 총괄 정보들을 담아두는 객체
public class TableInform {
	//테이블의 정보
	Boolean lock=true; //이거 실제 작동때는 default를 faulse로 해야한다!
	String tableName="테이블_1"; //얘는 오로지 TableMainInform에서 바꿀 수 있도록
	String tableNickName;
	String tableConcept;
	int manCnt;
	int womanCnt;
	//
	public TableInform(String tableName, String tableNickName, String tableConcept, int manCnt, int womanCnt) {
		this.tableName=tableName;
		this.tableNickName=tableNickName;
		this.tableConcept=tableConcept;
		this.manCnt=manCnt;
		this.womanCnt=womanCnt;
	}
	
	public Boolean getLock() {
		return lock;
	}

	public void setLock(Boolean lock) {
		this.lock = lock;
	}

	public String getTableName() {
		return tableName;
	}
	public String getTableNickName() {
		return tableNickName;
	}
	public void setTableNickName(String tableNickName) {
		this.tableNickName = tableNickName;
	}
	public String getTableConcept() {
		return tableConcept;
	}
	public void setTableConcept(String tableConcept) {
		this.tableConcept = tableConcept;
	}
	public int getManCnt() {
		return manCnt;
	}
	public void setManCnt(int manCnt) {
		this.manCnt = manCnt;
	}
	public int getWomanCnt() {
		return womanCnt;
	}
	public void setWomanCnt(int womanCnt) {
		this.womanCnt = womanCnt;
	}
	public void setAll(String tableNickName,String tableConcept,int manCnt,int womanCnt) {
		this.tableNickName=tableNickName;
		this.tableConcept=tableConcept;
		this.manCnt=manCnt;
		this.womanCnt=womanCnt;
	}
	
}
