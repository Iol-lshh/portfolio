package hwi.game;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import hong.client.DefaultClient;
import hong.client.ReceiverObjFromClient;
import hong.server.MessageObject;
import ji.tablet.TabletDAO;

public class GameJF_2 extends JFrame implements ReceiverObjFromClient {

	private JPanel contentPane;

	DefaultClient dc;

	TeethBtn[] btn;


	String me;

	MessageObject you;

	JLabel lblNewLabel;

	boolean turn = true;

	int bombNum = -1;

	//	int bombNum = (int)(Math.random()*jb.size());

	class TeethBtn extends JButton implements ActionListener {

		int i;//버튼 번호

		String [] imgStr= {"img/악어이빨3.png","img/악어이빨3.png","img/악어이빨3.png","img/악어이빨3.png","img/악어이빨3.png",
				"img/악어이빨3.png","img/악어이빨3.png","img/악어이빨13.png","img/악어이빨13.png","img/악어이빨13.png",
				"img/악어이빨13.png","img/악어이빨13.png","img/악어이빨13.png","img/악어이빨13.png","img/악어이빨13.png",
				"img/악어이빨13.png","img/악어이빨13.png","img/악어이빨3.png","img/악어이빨3.png","img/악어이빨3.png"};
		public TeethBtn(int x, int y, int width, int height) {
			super();

			setBounds(x, y, width, height);
			setIcon(new ImageIcon("img/이빨.png"));
			setContentAreaFilled(false);//////버튼 이미지 조정을 위해 주석처리
			setVisible(true);
			addActionListener(this);
			setLayout(new BorderLayout(0, 0));

		}

		public void actionPerformed(ActionEvent e) {
			System.out.println(i);

			if (turn) {//내차례
				String msg = i + "";

				if (bombNum == i) {//
					System.out.println("펑~~~~~~~~~~~~~~~~~~~~~`");
					msg = "펑";
					// 내가 짐
					lblNewLabel.setIcon(new ImageIcon("img/패배.png"));

					// 진거 후속 처리
					for (int i = 0; i < btn.length; i++) {
						btn[i].setEnabled(false);
					}
					//					new TabletDAO().modifyEnd(me, you.getMessageMain());
					//					dc.sendMessage("", "테이블상태변경", "테이블_1","테이블_2","테이블_3","테이블_4","테이블_5",
					//							"테이블_6","테이블_7","테이블_8");
				} else {
					setIcon(new ImageIcon(imgStr[i]));
					setContentAreaFilled(true);
					setEnabled(false);
				}

				dc.sendMessage(msg, "game", you.getSender());

				turn = false;

			} else {
				System.out.println("기다려!");
			}
		}
	}

	@Override
	public void getMsgObjectFromClient(MessageObject mo) {

		//상대가 강제 종료되었을 때, 나도 빠져나감
		if(mo.getType().equals("강제종료")&&mo.getMessageMain().equals(you.getSender())) {
			new TabletDAO().modifyEnd(me, you.getSender());

			dc.sendMessage("", "테이블상태변경", "테이블_1","테이블_2","테이블_3","테이블_4","테이블_5",
					"테이블_6","테이블_7","테이블_8");
			dispose();
			dc.removeReceiverObjFromClient(this);
		}
		else {
			String ri = (String) mo.getMessageMain();
			System.out.println("내가 받는다 확씨!!! " + ri);
			if (ri.equals("펑")) {
				System.out.println("내가 이김");

				/// 이긴 화면 보여줘
				lblNewLabel.setIcon(new ImageIcon("img/승리.jpg"));

				/// 이긴거 후속 처리
				for (int i = 0; i < btn.length; i++) {

					btn[i].setEnabled(false);

				}
				try {
					Thread.sleep(5000);
					new TabletDAO().modifyEnd(me, you.getSender());
					dc.sendMessage("닫아", "game", you.getSender());
					dispose();
					dc.removeReceiverObjFromClient(this);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}


			}else if (ri.equals("닫아")) { 
				new TabletDAO().modifyEnd(me, you.getSender());

				dc.sendMessage("", "테이블상태변경", "테이블_1","테이블_2","테이블_3","테이블_4","테이블_5",
						"테이블_6","테이블_7","테이블_8");
				dispose();
				dc.removeReceiverObjFromClient(this);

			}else {

				int ti = Integer.parseInt(ri);

				btn[ti].setIcon(new ImageIcon("img/이빨빠짐.png"));
				btn[ti].setContentAreaFilled(true);
				btn[ti].setEnabled(false);

				turn = true;
			}
		}
	}

	public GameJF_2(int bombNum, DefaultClient dc, String me,MessageObject you) {

		//	

		this.bombNum = bombNum;

		//
		//		this.bombNum=1;

		this.dc = dc;
		this.me = me;
		this.you = you;

		setResizable(false);
		setBounds(220, 70, 950, 600);
		setTitle("악어 복불복 게임");
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("img/악어입벌림.png"));
		lblNewLabel.setBounds(0, 0, 944, 571);
		contentPane.add(lblNewLabel);


		btn = new TeethBtn[] { new TeethBtn(215, 87, 90, 51), new TeethBtn(350, 73, 90, 51),
				new TeethBtn(498, 73, 90, 51), new TeethBtn(636, 87, 90, 51), new TeethBtn(687, 129, 90, 51),
				new TeethBtn(687, 184, 90, 51), new TeethBtn(675, 239, 90, 51), new TeethBtn(678, 317, 90, 51),
				new TeethBtn(678, 371, 90, 51),new TeethBtn(687, 424, 90, 51),new TeethBtn(638, 471, 90, 51)   ,
				new TeethBtn(504, 480, 90, 51),new TeethBtn(353, 480, 90, 51),new TeethBtn(223, 471, 90, 51)   
				,new TeethBtn(164, 424, 90, 51),new TeethBtn(170, 371, 90, 51),new TeethBtn(180, 317, 90, 51)
				,new TeethBtn(180, 239, 90, 51),new TeethBtn(170, 184, 90, 51),
				new TeethBtn(164, 129, 90, 51) };
		//이빨 번호 부여(인덱스 포인터) 및, 패널에 이빨 (버튼) 추가
		for (int i = 0; i < btn.length; i++) {
			contentPane.add(btn[i]);
			btn[i].i = i;
		}

		setVisible(true);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		System.out.println("게임 생성자 실행 >>>>>>"+dc);
		dc.addReceiverObjFromClient(this);

	}
}
