package hwi.chat;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.Dimension;
import javax.swing.JTextPane;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;

public class ChatJF extends JFrame {

	private JPanel contentPane;
	private JPanel panel;
	private JButton exitBtn;//나가기버튼
	private JPanel panel_1;
	private JLabel lblNewLabel;
	private JPanel panel_4;
	JTextField chatField;//채팅쓰는곳
	private JButton chatBtn;//채팅보내기
	private JScrollPane scrollPane;
	private JPanel panel_7;
	JTextArea chatViewPane;//채팅창

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ChatJF frame = new ChatJF();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public ChatJF(ChatController control) {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 860, 908);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		chatViewPane = new JTextArea();
		panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		exitBtn = new JButton("채팅방 나가기");
		exitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.BtnAction();
				//채팅방나가기
			}
		});
		panel.add(exitBtn, BorderLayout.EAST);
		
		lblNewLabel = new JLabel("(닉네임)");
		panel.add(lblNewLabel, BorderLayout.CENTER);
		
		panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		panel_4 = new JPanel();
		panel_1.add(panel_4, BorderLayout.SOUTH);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		chatField = new JTextField();
		panel_4.add(chatField);
		chatField.setColumns(10);
		chatField.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				control.BtnAction2();
			}
		});
		chatBtn = new JButton("전송");
			
		chatBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				control.BtnAction2();
			}
		});
		panel_4.add(chatBtn, BorderLayout.EAST);
		
		scrollPane = new JScrollPane();
		panel_1.add(scrollPane, BorderLayout.CENTER);
		
		panel_7 = new JPanel();
		scrollPane.setViewportView(panel_7);
		panel_7.setLayout(new GridLayout(0, 1, 20, 20));
		
		//채팅출력창 
		panel_7.add(chatViewPane);
		
		setVisible(true);
	}
}
