package an.settlement;

import javax.swing.table.DefaultTableModel;

import an.tableBoard.PaymentView;

public class SettlementReset {
	PaymentView pmv;
	SettlementBillsControl billscontrol;

	public SettlementReset(PaymentView pmv) {
		this.pmv = pmv;
		init();
	}
	
	
	public void init () {
		DefaultTableModel dtm = (DefaultTableModel)pmv.table.getModel();
		dtm.setNumRows(0);
		
		
	}
	
}
