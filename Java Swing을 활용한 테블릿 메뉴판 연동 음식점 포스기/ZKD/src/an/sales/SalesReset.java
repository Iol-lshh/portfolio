package an.sales;

import javax.swing.table.DefaultTableModel;

public class SalesReset {
	
	
	SalesView sv;
	
	
	public SalesReset(SalesView sv) {
		this.sv = sv;
		reset();
	}
	
	
	public void reset () {
		DefaultTableModel dtm = (DefaultTableModel)sv.table_1.getModel();
		dtm.setNumRows(0);
	}

}
