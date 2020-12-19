package sup.kitchen;

public class Order{
	String table_num, menu_Name, menu_state;
	String time;
	int menu_cnt;
	int cancel;
	

	public int getCancel() {
		return cancel;
	}

	public void setCancel(int cancel) {
		this.cancel = cancel;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getTable_num() {
		return table_num;
	}

	public void setTable_num(String table_num) {
		this.table_num = table_num;
	}

	public String getMenu_Name() {
		return menu_Name;
	}

	public void setMenu_Name(String menu_Name) {
		this.menu_Name = menu_Name;
	}

	public String getMenu_state() {
		return menu_state;
	}

	public void setMenu_state(String menu_state) {
		this.menu_state = menu_state;
	}

	public int getMenu_cnt() {
		return menu_cnt;
	}

	public void setMenu_cnt(int menu_cnt) {
		this.menu_cnt = menu_cnt;
	}
	
}
