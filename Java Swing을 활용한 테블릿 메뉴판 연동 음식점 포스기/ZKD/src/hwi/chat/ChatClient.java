package hwi.chat;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

import aaaaaaaaaaaaaaa.InitData;
import hong.server.MessageObject;


//서버가 받는 포트 7777


///////////////////////////////
//클라이언트 양식
class DefaultClient{
	
	int serverPort;
	ObjectOutputStream oos;

	public DefaultClient( int serverPort) {
		setServer( serverPort);
		try {
			//서버로부터의 수신
			Socket socket = new Socket(InitData.ip,serverPort);
			new Receiver(socket).start();
			oos=new ObjectOutputStream(socket.getOutputStream());//보내기 스트림 생성
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	void setServer(	int serverPort) {
		
		this.serverPort=serverPort;
	}

	//
	class Receiver extends Thread{
		String sender; //송신인 ip
		ObjectInputStream ois;

		//소켓은 MessageObject객체를 옮길 것. name은 address와 송신인을 받을것!
		public Receiver(Socket socket) { 
			try {
				ois=new ObjectInputStream(socket.getInputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void run() {
			System.out.println("서버 듣기");
			while(ois!=null) {
				try {				
					//여기서 수신 다 받음!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
					MessageObject msgObject =(hong.server.MessageObject) ois.readObject();//여기서 받음
					//여기서 object로 보내기 때문에!!!!! 반드시 다운캐스팅해서 사용할것!!!!!!!!!!!!!!!!!!!!!!!!!!!!
					System.out.println(sender+"수신 완료"+(msgObject.getMessageMain()));
				} catch (Exception e) {
				}
			}
		}
	}

	//보낼 메세지와 받을 사람들의 주소를 매개변수로 받는다. 이걸로 메세지를 원하는 곳에 보내면 됩니다!!!
	void sendMessage(Object msgMain,String type, ArrayList<String> receivers) {
		String [] receiversArr=new String[receivers.size()];
		for(int i=0;i<receivers.size();i++) {
			receiversArr[i]=receivers.get(i);
		}
		sendMessage(msgMain, type, receiversArr);
	}
	void sendMessage(Object msgMain,String type, String ...receivers) {
		try {
			System.out.println("myName:"+InetAddress.getLocalHost().getHostAddress());
			MessageObject msgObject=new MessageObject(msgMain, type, InetAddress.getLocalHost().getHostAddress(), receivers);
			oos.writeObject(msgObject);
			oos.flush();
			oos.reset();
		}catch (Exception e) {
			e.printStackTrace();
		}		
	}
}

//로컬 테스트
public class ChatClient {

	public static void main(String[] args) {
		DefaultClient a=new DefaultClient(7777);
		
		String msgMain="이양";
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		a.sendMessage(msgMain, "",InitData.ip);
	}

}
