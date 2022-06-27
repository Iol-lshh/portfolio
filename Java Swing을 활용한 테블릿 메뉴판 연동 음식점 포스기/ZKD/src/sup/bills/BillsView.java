package sup.bills;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.border.MatteBorder;

import sup.menu.OrderMenu;

import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BillsView extends JFrame {
	BillsControl bils_control;
	JPanel panel_2;
	JLabel totLable;
	JScrollPane scrollPane;
	JPanel panel_orders;
	public BillsView(BillsControl bils_control) {
		this.bils_control=bils_control;
		
		panel_orders = new JPanel();
		setBounds(470,70,450,600);
		setTitle("계산서");
		setResizable(false);
		panel_orders.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		getContentPane().add(panel_orders, BorderLayout.CENTER);
		panel_orders.setLayout(new BorderLayout(0, 0));
		
		panel_2 = new JPanel();
		scrollPane = new JScrollPane(panel_2);
		panel_orders.add(scrollPane, BorderLayout.CENTER);
		
		panel_2.setLayout(new GridLayout(30, 1, 0, 0));
		
		JPanel panel_tot = new JPanel();
		panel_tot.setPreferredSize(new Dimension(10, 30));
		getContentPane().add(panel_tot, BorderLayout.SOUTH);
		panel_tot.setLayout(new BorderLayout(0, 0));
		
		totLable = new JLabel("0");
		totLable.setBorder(new EmptyBorder(0, 0, 0, 10));
		totLable.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_tot.add(totLable, BorderLayout.CENTER);
		
		JLabel wonLable = new JLabel("\uC6D0");
		wonLable.setPreferredSize(new Dimension(30, 15));
		wonLable.setHorizontalAlignment(SwingConstants.CENTER);
		panel_tot.add(wonLable, BorderLayout.EAST);
		
		JLabel chongLable = new JLabel("\uCD1D");
		chongLable.setHorizontalAlignment(SwingConstants.CENTER);
		chongLable.setPreferredSize(new Dimension(40, 15));
		panel_tot.add(chongLable, BorderLayout.WEST);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("\uACC4\uC0B0\uC11C");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 20));
		lblNewLabel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel.add(lblNewLabel);
		
		JButton backBtn = new JButton("◀");
		backBtn.setPreferredSize(new Dimension(85, 30));
		panel.add(backBtn, BorderLayout.WEST);
		backBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				bils_control.closeBills();
			}
		});
		
		JPanel panel_1 = new JPanel();
		panel_1.setPreferredSize(new Dimension(85, 10));
		panel.add(panel_1, BorderLayout.EAST);
//		setVisible(true);
		
	}
}
class OrderedMenusLable extends JPanel implements ActionListener{
	
	JLabel menuName;
	JLabel menuCnt;
	JLabel menuPrice;
	JLabel menuState;
	JButton cancelButton;
	BillsView billsMain;
	OrderMenu orderMenu;
	
	
	public OrderedMenusLable(BillsView billsMain, OrderMenu orderMenu) {
		setLayout(new GridLayout(1,5));
		this.billsMain = billsMain;
		this.orderMenu = orderMenu;
		
		menuName = new JLabel();
		menuCnt = new JLabel();
		menuPrice = new JLabel();
		menuState = new JLabel();
		cancelButton = new JButton("취소");
		
		menuName.setText(orderMenu.getMenuName());
		menuCnt.setText(orderMenu.getCnt()+"");
		menuPrice.setText(orderMenu.getPrice()+"");
		menuState.setText(orderMenu.getState());
		
		
		add(menuState);
		add(menuName);
		add(menuPrice);
		add(menuCnt);
		add(cancelButton);
		System.out.println(orderMenu.getCancel());
		if(orderMenu.getCancel()==0) {
			cancelButton.setEnabled(false);
		}
		
		billsMain.panel_2.add(this);
		setVisible(true);
		
		cancelButton.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		billsMain.bils_control.cancelOrder(orderMenu);
		
	}
}