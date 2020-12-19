package hwi.game;


import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import hong.client.DefaultClient;
import hong.server.MessageObject;
import hong.table.TableMainController;


public class GameMainController {
	int bombNum;
	AskGameJF askView;
	DefaultClient dc;
	MessageObject msgObject;
	String me;
	
	//신청인
	public GameMainController(DefaultClient dc,String me,String you) {
		rasj=new ReadyAnswerJrame("대답을 대기중입니다...");
		this.dc=dc;
	}
	//피신청인
	public GameMainController(DefaultClient dc,String me, MessageObject you) {
		askView=new AskGameJF(this, dc, me , you);
		askView.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		this.dc=dc;
		this.me=me;
		this.msgObject=you;
	}

	//////////////////////////////////////////////////////////////////////////////
	//신청인용 함수
	ReadyAnswerJrame rasj;

	public void doAfterReply(boolean answer,MessageObject msgObject) {
		rasj.dispose();
		if(answer) { //수락 메세지 오면, 게임 열겨
			System.out.println("me>>>>>>>>>>>>  222"+me);
			new GameJF_2((int)msgObject.getMessageMain(), dc, me, msgObject);	
		}
		else {//거절 메세지 받으면, 슈밤
			dc.sendMessage("","테이블상태변경",me);
			NoAnswerGameJF temp=new NoAnswerGameJF();
			temp.setVisible(true);
		}
	}
	//////////////////////////////////////////////////////////////////////////////
	//피신청인용 함수
	public void doAfterAnswer(boolean answer) {
		if(answer) { //내가 수락하면 수락 메세지 보내고, 게임 열겨
			System.out.println();
			bombNum=(int)(Math.random()*20);
			dc.sendMessage(bombNum, "게임수락", msgObject.getSender());
			askView.dispose();
			System.out.println("doAfterAnswer >>>"+me);
			System.out.println("doAfterAnswer >>>"+msgObject.getSender());
			new GameJF_2(bombNum, dc, me, msgObject);
		}
		else {//거절 메세지 보내기
			dc.sendMessage("", "게임거절", msgObject.getSender());

			askView.dispose();
		}
		System.out.println("수락 / 거절 버튼 :"+answer+","+msgObject.getSender());
	}
	
	//////////////////////////////////////////////////////////////////////////////
	//수락 대기창
	class ReadyAnswerJrame extends JFrame{
		ReadyAnswerJrame(String msg){

			setBounds(520,265,400,214);
			setTitle("수락 대기 창");
			setResizable(false);
			JLabel label=new JLabel( msg);
			add(label);
			setVisible(true);
		}


	}
}
