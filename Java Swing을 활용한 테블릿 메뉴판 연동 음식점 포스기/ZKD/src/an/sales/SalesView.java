package an.sales;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import an.adminMain.AdminMainView;
import an.settlement.SettlementReset;

import java.awt.Rectangle;
import java.awt.Scrollbar;
import java.awt.Color;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JSpinner;
import java.awt.FlowLayout;
import javax.swing.JScrollBar;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;


public class SalesView extends JDialog {

	SalesDTO dto;
	ArrayList<SalesDTO> saleslist;
	JSpinner spinner;
	JSpinner spinner_1;
	JPanel contentPane;
	SalesControl salescontrol;
	JPanel panel;
	JButton btnNewButton_1;
	JButton btnNewButton_2;
	JScrollPane scrollPane;
	//JTable table;
	JButton btnNewButton;
	JPanel panel_1;
	JLabel lblNewLabel;
	JTable table_1;
	private JLabel label;
	public JTextField textField;   // 텍스트 필드 <-- 매출내역 총합 주기.
	
	JToggleButton tb1;
	JToggleButton tb2;
	JToggleButton tb3;
	private JButton btnNewButton_3;

	public SalesView(SalesControl salescontrol,JFrame adminView) {
		super(adminView,"매출내역",true);
		this.salescontrol = salescontrol;


		setBounds(200, 40, 1000, 700);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBounds(new Rectangle(0, 0, 2400, 1600));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout());

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 0, 235, 112);
		contentPane.add(panel_2, BorderLayout.WEST);



		Date vv = new Date(2000-1900, 1-1,1);
		Date vv2 = new Date(2020-1900, 1-1,1);
		//start 2020 현재날자로만들기
		Date start = new Date(2000-1900, 1-1, 1);

		Date end = new Date(2030-1900, 12-1, 1);
		SpinnerDateModel spi = new SpinnerDateModel(vv,start,end,Calendar.MONTH);
		panel_2.setLayout(new GridLayout(10, 2, 0, 0));
		//////////////////////////////
		spinner = new JSpinner();
		spinner.setModel(spi);
		spinner.setEditor(new JSpinner.DateEditor(spinner, "yyyy-MM-dd"));
		panel_2.add(spinner);

		SpinnerDateModel spi2 = new SpinnerDateModel(vv2,start,end,Calendar.MONTH);

		spinner_1 = new JSpinner();
		spinner_1.setModel(spi2);
		spinner_1.setEditor(new JSpinner.DateEditor(spinner_1, "yyyy-MM-dd"));
		panel_2.add(spinner_1);

		panel = new JPanel();
		panel_2.add(panel);
		panel.setLayout(new GridLayout(1, 0, 0, 0));

		ButtonGroup bg1 = new ButtonGroup();
		
		
		
		
		tb2 = new JToggleButton("안주류");/////////////////////////////////////
		tb2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				salescontrol.anjuSettle(spinner.getModel().getValue(),
						spinner.getModel().getValue()); 


				salescontrol.totPrice();
				
				SalesView.this.setVisible(false);
				SalesView.this.setVisible(true);

			}
		});
		bg1.add((AbstractButton) panel.add(tb2));

		tb3 = new JToggleButton("주류");/////////////////////////////////////////
		tb3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		
				salescontrol.sulSettle(spinner.getModel().getValue(),
						spinner.getModel().getValue()); 

				salescontrol.totPrice();
				
				SalesView.this.setVisible(false);
				SalesView.this.setVisible(true);
				}
			
			

		});
		bg1.add((AbstractButton) panel.add(tb3));


		table_1 = new JTable();

		//btnNewButton = new JButton("기간 별");
		tb1 = new JToggleButton("기간 별");//////////////////////////////////////////
		bg1.add((AbstractButton) panel.add(tb1));

		panel_1 = new JPanel();
		panel_2.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 2, 0, 0));

		lblNewLabel = new JLabel("총 액   :");
		panel_1.add(lblNewLabel);
		//////////////////////////////
		textField = new JTextField();
		panel_1.add(textField);
		textField.setColumns(10);
		
		btnNewButton_3 = new JButton("초기화");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new SalesReset(salescontrol.salesview);
				salescontrol.salesview.textField.setText("");
			
			}
		});
		panel_2.add(btnNewButton_3);

		 tb1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				

				salescontrol.dateSettle(spinner.getModel().getValue(),
						spinner.getModel().getValue()); 

				salescontrol.totPrice(); // 매출관리 총 액 계산부분.

				SalesView.this.setVisible(false);
				SalesView.this.setVisible(true);

			}
		});
		

		 System.out.println("asdfasdgakhlarkfalsk");
//		setContentPane(contentPane);
//		setVisible(true);

	}
}