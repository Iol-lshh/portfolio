package an.adminLogin;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import an.tableBoard.TableBoardView;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminLoginView extends JFrame {

	private JPanel contentPane;
	private JPasswordField password;
	private JLabel result;
	public TableBoardView tableBoardView;
	AdminLoginControl control; // 로그인 컨트롤 클래스.

	
	/**
	 * @wbp.parser.constructor
	 */
	public AdminLoginView(TableBoardView tableBoardView) {
		this.tableBoardView = tableBoardView;
		init();
		
	}

	public AdminLoginView(AdminLoginControl control) {
		this.control = control;
		init();

	}

	void init() {
		setVisible(true);
		this.tableBoardView = tableBoardView;
		
		setBounds(410, 40, 600, 600);
		setTitle("로그인 창");
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));

		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.WEST);

		JPanel panel_2 = new JPanel();
		panel.add(panel_2, BorderLayout.CENTER);

		JLabel lblNewLabel = new JLabel("User :");
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 30));
		panel_2.add(lblNewLabel);

	
		password = new JPasswordField(10);
		password.setFont(new Font("굴림", Font.PLAIN, 30));
		panel_2.add(password);

		
		JButton btnNewButton = new JButton("로그인");
		btnNewButton.setFont(new Font("굴림", Font.PLAIN, 26));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String strPw = String.valueOf(password.getPassword());
				

				AdminLogDTO dto = new AdminLogDAO().detail(strPw);
				if (dto!=null) {
					result.setText("로그인이 되었습니다");
					result.setForeground(Color.blue);

					control.loginViewClose();// 로그인 성공되면, an_adminLoginControl 클래스의 loginViewClose() 메소드 실행

				} else {
					result.setText("비밀번호가 일치하지 않습니다.");
					result.setFont(new Font("굴림", Font.BOLD, 24));
					result.setForeground(Color.RED);
				}

			}
		});
		panel_2.add(btnNewButton);
		result = new JLabel(" ");
		panel_2.add(result);

	}

}
