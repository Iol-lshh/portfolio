package hwi.game;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import hong.client.DefaultClient;
import hong.server.MessageObject;
import ji.tablet.TabletDAO;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;

public class AskGameJF extends JFrame {

	boolean answer;
	private JPanel contentPane;


	public AskGameJF(GameMainController gmc, DefaultClient df, String me, MessageObject you) {
		
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(520, 265, 400, 214);
		setTitle("게임 요청 창");
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 3, 0));
		
		JLabel askTableLab = new JLabel((String)you.getSender());
		askTableLab.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(askTableLab);
		JLabel askTableLab_ = new JLabel("닉네임 : "+(String)you.getMessageMain());
		askTableLab_.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(askTableLab_);
		
		JLabel gameMsgLab = new JLabel("게임 한판 하실래요? (찡긋)");
		gameMsgLab.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(gameMsgLab);
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JButton gameYesBtn = new JButton("수락");
		panel.add(gameYesBtn);
		gameYesBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new TabletDAO().modifystartGame(me, you.getSender());
				df.sendMessage("", "테이블상태변경", "테이블_1","테이블_2","테이블_3","테이블_4","테이블_5",
						"테이블_6","테이블_7","테이블_8");
				gmc.doAfterAnswer(true);
			}
		});
		
		JButton gameNOBtn = new JButton("거절");
		panel.add(gameNOBtn);
		gameNOBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gmc.doAfterAnswer(false);			
			}
		});
		setVisible(true);
	}
}
