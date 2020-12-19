package an.sales;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class SalesDTO {
	String BILLS_ORDEREDMENU,BILLS_TABLENUM;
	Integer BILLS_INDEX, BILLS_COUNT, BILLS_PRICE;

	Timestamp BILLS_TIME;
	String before;
	String after;

	Integer sumprice;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	//YYYYMMDD



	public String getBefore() {
		return before;
	}
	public Integer getSumprice() {
		return sumprice;
	}
	public void setSumprice(Integer sumprice) {
		this.sumprice = sumprice;
	}
	public void setBefore(String before) {
		this.before = before;
	}

	public String getAfter() {
		return after;
	}

	public void setAfter(String after) {
		this.after = after;
	}

	public String getBILLS_ORDEREDMENU() {
		return BILLS_ORDEREDMENU;
	}

	public void setBILLS_ORDEREDMENU(String bILLS_ORDEREDMENU) {
		BILLS_ORDEREDMENU = bILLS_ORDEREDMENU;
	}

	public String getBILLS_TABLENUM() {
		return BILLS_TABLENUM;
	}

	public void setBILLS_TABLENUM(String bILLS_TABLENUM) {
		BILLS_TABLENUM = bILLS_TABLENUM;
	}

	public Integer getBILLS_INDEX() {
		return BILLS_INDEX;
	}

	public void setBILLS_INDEX(Integer bILLS_INDEX) {
		BILLS_INDEX = bILLS_INDEX;
	}

	public Integer getBILLS_COUNT() {
		return BILLS_COUNT;
	}

	public void setBILLS_COUNT(Integer bILLS_COUNT) {
		BILLS_COUNT = bILLS_COUNT;
	}

	public Integer getBILLS_PRICE() {
		return BILLS_PRICE;
	}

	public void setBILLS_PRICE(Integer bILLS_PRICE) {
		BILLS_PRICE = bILLS_PRICE;
	}

	public Timestamp getBILLS_TIME() {
		return BILLS_TIME;
	}

	public void setBILLS_TIME(Timestamp bILLS_TIME) {
		BILLS_TIME = bILLS_TIME;
	}

	//?
	void sumprice() {
		sumprice = BILLS_COUNT*BILLS_PRICE;
	}
	
	public void sumpr(Object cnt) {
		String sCnt = cnt.toString();
		this.BILLS_COUNT = Integer.parseInt(sCnt);
		sumprice();
	}




}