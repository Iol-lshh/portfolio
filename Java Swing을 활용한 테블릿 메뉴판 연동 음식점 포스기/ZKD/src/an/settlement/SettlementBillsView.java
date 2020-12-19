package an.settlement;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import an.tableBoard.PaymentDTO;
import an.tableBoard.PaymentView;
import ji.tablet.TabletDAO;
import ji.tablet.TabletDTO;
import sup.menu.OrderListDBControl;

import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import javax.swing.SwingConstants;

public class SettlementBillsView extends JFrame {

	JPanel contentPane;
	SettlementBillsControl billscontrol;

	public static void main() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public SettlementBillsView(SettlementBillsControl billscontrol) {
		this.billscontrol = billscontrol;

		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(520, 265, 400, 214);
		setTitle("결제 하시겠습니까?");
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
	
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("결제 처리");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				System.out.println(new SettlementReset(billscontrol.payView));
				for (PaymentDTO pay : billscontrol.payView.payment) {
					new OrderListDBControl("").billsInsert(pay,billscontrol.payView.payControl.tableNum);
				}
				
				
				billscontrol.payView.textField_11.setText("");
				// 이부분을 통해서 리셋을 시키려고함 !
				
				billscontrol.resetGender("", "");
				//오더리스트 초기화
				new OrderListDBControl("").del_table(billscontrol.payView.payControl.tableNum);
				//오더리스트 테이블 디비 초기화
				new TabletDAO().modify(new TabletDTO(),billscontrol.payView.payControl.tableNum);
				
				//테이블 리셋
				billscontrol.payView.payControl.tableView.boardControl.reset(billscontrol.payView.payControl.tableNum);
				dispose();
		
			}
		});
		panel_1.add(btnNewButton);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblNewLabel_4 = new JLabel("결제를 하시겠습니까?");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblNewLabel_4);
		
		JLabel lblNewLabel_3_2 = new JLabel("\uC6D0\uD558\uC2DC\uBA74 \uACB0\uC81C\uCC98\uB9AC\uB97C \uB20C\uB7EC\uC8FC\uC138\uC694.");
		lblNewLabel_3_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblNewLabel_3_2);
		
		setVisible(true);
	}
 
}
