package sup.menu;

public class OrderMenu {
	String menuName;
	int price;
	int cnt;
	int cancel;
	int sum;
	String timestamp;
	String state;
	String type;
	int pickup;
	
	public int getPickup() {
		return pickup;
	}
	public void setPickup(int pickup) {
		this.pickup = pickup;
	}
	@Override
	public String toString() {
		return "OrderMenu [menuName=" + menuName + ", price=" + price + ", cnt=" + cnt + ", cancel=" + cancel + ", sum="
				+ sum + ", timestamp=" + timestamp + ", state=" + state + ", type=" + type + "]";
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public int getCancel() {
		return cancel;
	}
	public void setCancel(int cancel) {
		this.cancel = cancel;
	}
	public OrderMenu() {
		this.cancel = 1;
		state ="조리대기";
	}
	public OrderMenu(int cnt) {
		super();
		this.cnt = cnt;
		this.cancel = 1;
		
		state ="조리대기";
		
		pickup = 1;
	}
	public void setCnt(Object cnt) {
		String sCnt = cnt.toString();
		this.cnt = Integer.parseInt(sCnt);
		sum();
	}
	void sum() {
		sum = cnt*price;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
}
