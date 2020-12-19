package an.tableBoard;

import java.sql.Date;

public class PaymentDTO {

	//시간,테이블번호,메뉴,수량,가격
	String bills_orderedmenu;
	String bills_tablenum;
	int bills_count,bills_price;
	

	public int getBills_count() {
		return bills_count;
	}
	public void setBills_count(int bills_count) {
		this.bills_count = bills_count;
	}
	public int getBills_price() {
		return bills_price;
	}
	public void setBills_price(int bills_price) {
		this.bills_price = bills_price;
	}
	public String getBills_tablenum() {
		return bills_tablenum;
	}
	public void setBills_tablenum(String bills_tablenum) {
		this.bills_tablenum = bills_tablenum;
	}
	public String getBills_orderedmenu() {
		return bills_orderedmenu;
	}
	public void setBills_orderedmenu(String bills_orderedmenu) {
		this.bills_orderedmenu = bills_orderedmenu;
	}
	
	
	@Override
	public String toString() {
		return "an_paymentDTO [bills_orderedmenu=" + bills_orderedmenu + ", bills_tablenum=" + bills_tablenum
				+ ", bills_count=" + bills_count + ", bills_price=" + bills_price + "]";
	}
	
	

	
}
