package an.tableBoard;

import sup.menu.OrderListDBControl;

public class PaymentControl {
	public int tot;
	public PaymentView payView;
	public TableBoardView tableView;
	public String tableNum;
	public PaymentControl(TableBoardView tableView, String tableNum) {
			this.tableView = tableView;
			this.tableNum = tableNum;
			payView = new PaymentView(this);

			totPrice();
	}

	public void totPrice() {
	       tot= 0;
	      for (PaymentDTO pay : payView.payment) {
	    	  
	    	  
	         tot += pay.bills_price*pay.bills_count;
	         System.out.println(tot);
	
	      }
	      
	      payView.textField_11.setText(tot+" "); // 텍스트 필드에 들 간 값.
	      
	   }
}
