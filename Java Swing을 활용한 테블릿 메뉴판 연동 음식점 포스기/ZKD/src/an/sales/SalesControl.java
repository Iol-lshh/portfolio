package an.sales;

import java.awt.BorderLayout;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import an.adminMain.AdminMainView;
import an.tableBoard.PaymentDTO;
import sup.menu.OrderListDBControl;

public class SalesControl {
   
   
   ArrayList<SalesDTO> saleslist;
   String before;
   String after;
   SalesView salesview;

   int tot =0;
   public SalesControl(AdminMainView adminView) {
      
      salesview = new SalesView(this,adminView);
      salesview.setContentPane(salesview.contentPane);
      salesview.setVisible(true);
   }
   
   
	public void totPrice() {
		int tot =0;
		System.out.println("salesview.saleslist : "+salesview.saleslist);
	      for (SalesDTO sales : salesview.saleslist) {	        	  
	         tot += sales.BILLS_PRICE;
	         System.out.println(tot);
	      }	      
	      salesview.textField.setText(tot+" ");
	      
	   }
   
   
   

   public void dateSettle(Object object, Object object2) {
      //일별 데이터는  날짜, 가격(합산), 건당.
      SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
      
      sdf.format(object);
      sdf.format(object2);
      System.out.println("dkdkdkdkdkdkdkdkdkdkdkdkdkdkdkdkdkd");
      System.out.println("1"+salesview);
      System.out.println("2"+salesview.spinner);
      System.out.println("3"+salesview.spinner.getModel());
      System.out.println("4"+salesview.spinner.getModel().getValue());
      before = sdf.format(salesview.spinner.getModel().getValue());
      after = sdf.format(salesview.spinner_1.getModel().getValue());
      
      if(salesview.scrollPane!=null) {
          salesview.contentPane.remove(salesview.scrollPane);         
       }
   
      SalesDTO dto = new SalesDTO();
      dto.setBefore(before);
      dto.setAfter(after);
      
      
      salesview.table_1 = new JTable();   
      salesview.scrollPane = new JScrollPane(salesview.table_1);

      Vector<String> column  = new Vector<String>();
      
      column.add("결제시간");
      column.add("테이블NO");
      column.add("결제메뉴");
      column.add("수량");
      column.add("가격");
      
      
      DefaultTableModel df = new DefaultTableModel(column,0);
      System.out.println(column);
      
      
      salesview.saleslist = new SalesDAO().list(dto);
      for (SalesDTO tt: new SalesDAO().list(dto)) {
         
         Vector<Object> vct = new Vector<Object>();
         

         vct.add(tt.getBILLS_TIME());
         vct.add(tt.getBILLS_TABLENUM());
         vct.add(tt.getBILLS_ORDEREDMENU());
         vct.add(tt.getBILLS_COUNT());
         vct.add(tt.getBILLS_PRICE());
         df.addRow(vct);
 
      }
      salesview.table_1.setModel(df);
      salesview.contentPane.add(salesview.scrollPane, BorderLayout.CENTER);

         System.out.println(tot);
      

   

   }
   
   public void anjuSettle(Object object, Object object2) {
	      //일별 데이터는  날짜, 가격(합산), 건당.
	      SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
	      
	      
	      sdf.format(object);
	      sdf.format(object2);
	      if(salesview.scrollPane!=null) {
	    	  salesview.contentPane.remove(salesview.scrollPane);         
	      }
	      
	      before = sdf.format(salesview.spinner.getModel().getValue());
	      after = sdf.format(salesview.spinner_1.getModel().getValue());
	      
	   
	      SalesDTO dto = new SalesDTO();
	      dto.setBefore(before);
	      dto.setAfter(after);
	      
	      
	      salesview.table_1 = new JTable();   
	      salesview.scrollPane = new JScrollPane(salesview.table_1);

	      Vector<String> column = new Vector<String>();
	      
	      column.add("결제시간");
	      column.add("테이블NO");
	      column.add("결제메뉴");
	      column.add("수량");
	      column.add("가격");
	      
	      DefaultTableModel df2 = new DefaultTableModel(column,0);
	      System.out.println(column);
	      
	      
	      salesview.saleslist = new SalesDAO().anjulist(dto);
	      for (SalesDTO gg: new SalesDAO().anjulist(dto)) {
	         
	         Vector<Object> vctanju = new Vector<Object>();
	         

	         vctanju.add(gg.getBILLS_TIME());
	         vctanju.add(gg.getBILLS_TABLENUM());
	         vctanju.add(gg.getBILLS_ORDEREDMENU());
	         vctanju.add(gg.getBILLS_COUNT());
	         vctanju.add(gg.getBILLS_PRICE());
	         df2.addRow(vctanju);
	 
	      }
	      salesview.table_1.setModel(df2);
	      salesview.contentPane.add(salesview.scrollPane, BorderLayout.CENTER);

	         System.out.println(tot);
	      

	   
	   }


   public void sulSettle(Object object, Object object2) {
	      //일별 데이터는  날짜, 가격(합산), 건당.
	      SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
	      
	      sdf.format(object);
	      sdf.format(object2);
	      
	      if(salesview.scrollPane!=null) {
	    	  salesview.contentPane.remove(salesview.scrollPane);         
	      }
	      before = sdf.format(salesview.spinner.getModel().getValue());
	      after = sdf.format(salesview.spinner_1.getModel().getValue());
	      
	   
	      SalesDTO dto = new SalesDTO();
	      dto.setBefore(before);
	      dto.setAfter(after);
	      
	      
	      salesview.table_1 = new JTable();   
	      salesview.scrollPane = new JScrollPane(salesview.table_1);

	      Vector<String> column  = new Vector<String>();
	      
	      column.add("결제시간");
	      column.add("테이블NO");
	      column.add("결제메뉴");
	      column.add("수량");
	      column.add("가격");
	      
	      DefaultTableModel df3 = new DefaultTableModel(column,0);
	      System.out.println(column);
	      
	      
	      salesview.saleslist = new SalesDAO().sullist(dto);
	      for (SalesDTO ee: new SalesDAO().sullist(dto)) {
	         
	         Vector<Object> vctsul = new Vector<Object>();
	         

	         vctsul.add(ee.getBILLS_TIME());
	         vctsul.add(ee.getBILLS_TABLENUM());
	         vctsul.add(ee.getBILLS_ORDEREDMENU());
	         vctsul.add(ee.getBILLS_COUNT());
	         vctsul.add(ee.getBILLS_PRICE());
	         df3.addRow(vctsul);
	 
	      }
	      salesview.table_1.setModel(df3);
	      salesview.contentPane.add(salesview.scrollPane, BorderLayout.CENTER);

	         System.out.println(tot);
	      

	   
	      
	      
	      
	   }

}