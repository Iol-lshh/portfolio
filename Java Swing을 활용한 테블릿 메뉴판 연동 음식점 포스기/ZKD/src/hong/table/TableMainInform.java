package hong.table;

import java.io.Serializable;
import java.util.HashMap;

public class TableMainInform {
	String tableName="테이블_1";
	TableInform myTableInform;
	String tableStatus; //채팅중, 게임중 등..
	//다른 테이블의 정보 <테이블이름,테이블>
	HashMap<String, TableInform> otherTableMap=new HashMap<String, TableInform>();
	
	
	
	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public TableInform getMyTableInform() {
		return myTableInform;
	}

	public void setMyTableInform(TableInform myTableInform) {
		this.myTableInform = myTableInform;
	}

	public String getTableStatus() {
		return tableStatus;
	}

	public void setTableStatus(String tableStatus) {
		this.tableStatus = tableStatus;
	}

	public TableMainInform(String tableName, String tableNickName, String tableConcept, int manCnt, int womanCnt) {
		myTableInform=new TableInform(tableName, tableNickName, tableConcept, manCnt, womanCnt);
	}
	
	//테이블 추가(테이블 현황판용)
	void addOtherTable(String tableName,TableInform tableInform) {
		otherTableMap.put(tableName, tableInform);
	}
	//삭제
	void deleteOtherTable(String tableName) {
		otherTableMap.remove(tableName);
	}
	//수정
	void modifyOtherTable(String tableName,TableInform tableInform) {
		otherTableMap.remove(tableName);
		otherTableMap.put(tableName, tableInform);
	}
}
