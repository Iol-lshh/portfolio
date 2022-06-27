package hwi.chat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import aaaaaaaaaaaaaaa.InitData;

public class TCPGUIClient extends JFrame{
	
	JTextArea jarea;
	JTextField jf, serverJF, portJF;
	
	Socket socket;
	public TCPGUIClient() {
		try {
			
			
			
			setBounds(1200, 50, 500, 600);
			
			
			JPanel jp = new JPanel();
			jp.setLayout(new BoxLayout(jp, BoxLayout.X_AXIS));
			
			serverJF = new JTextField(InitData.ip);
			portJF = new JTextField("7777");
			JButton btn = new JButton("서버연결");
			
			btn.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						socket = new Socket(serverJF.getText(), 
								Integer.parseInt(portJF.getText())
								);
						System.out.println("클라이언트 서버 연결 성공");
						jf.setEnabled(true);
						jf.addActionListener(new JFAction(socket));

						new TCPClientReciever(socket).start();
						
						btn.setEnabled(false);
						serverJF.setEditable(false);
						portJF.setEditable(false);
						jf.setFocusable(true);
						
						
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			});
			
			
			jp.add(serverJF);
			jp.add(portJF);
			jp.add(btn);
			
			add(jp,"North");
			
			jarea = new JTextArea();
			jarea.setEditable(false);
			JScrollPane js = new JScrollPane(jarea);
			add(js,"Center");
			
			jf = new JTextField();
			jf.setEnabled(false);
			add(jf,"South");
			
			setVisible(true);
			
			
			
			
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	class TCPClientReciever extends Thread{
		ObjectInputStream ois;
		
		public TCPClientReciever(Socket socket) {

			try {
				ois = new ObjectInputStream(socket.getInputStream());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		@Override
		public void run() {
			while(ois!=null) {
				try {
					jarea.append(ois.readUTF()+"\n");
					jarea.setCaretPosition(jarea.getDocument().getLength());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	
	
	class JFAction implements ActionListener{
		
		ObjectOutputStream oos;
		
		public JFAction(Socket socket) {
			try {
				oos = new ObjectOutputStream(socket.getOutputStream());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		

		@Override
		public void actionPerformed(ActionEvent e) {

			try {
				String msg = jf.getText();
				//System.out.println(msg);
				oos.writeUTF(msg);
				oos.flush();
				oos.reset();
				
				jf.setText("");
				jf.setFocusable(true);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
	}
	
	
	
	public static void main(String[] args) {
		
		new TCPGUIClient();
	}

}
