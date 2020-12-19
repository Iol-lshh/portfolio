package hwi.chat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JFrame;

import aaaaaaaaaaaaaaa.InitData;


	
	

	
	
	


public class ChatController{
	
	ChatJF cj;
	
	DefaultClient dc;
	
	public ChatController(DefaultClient dc) {
		this.dc = dc;
		cj = new ChatJF(this);
	}

	
	
	
	void BtnAction() {
		
		cj.dispose();
//		System.exit(0);

		
		
	}
	
	void BtnAction2() {
		String a = cj.chatField.getText();
		
		dc.sendMessage(a, "",InitData.ip );				
		cj.chatField.setText("");
		cj.chatField.setFocusable(true);
		cj.chatViewPane.append(a+"\n");

//		chatViewPane.
		
	}
	
	
	void ChatWrite(ObjectInputStream ois) {
		
		
			while(ois!=null) {
				try {
					cj.chatViewPane.append(ois.readUTF()+"\n");
					cj.chatViewPane.setCaretPosition(cj.chatViewPane.getDocument().getLength());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		
	}
	

	


	public static void main(String[] args) {
		
		new ChatController(new DefaultClient( 7777));
	}

}
