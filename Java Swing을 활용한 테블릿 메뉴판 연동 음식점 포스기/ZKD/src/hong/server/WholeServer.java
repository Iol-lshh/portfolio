
package hong.server;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.regex.Pattern;

import ji.tablet.TabletDAO;
import ji.tablet.TabletDTO;

//서버
public class WholeServer {

	HashMap<String,ObjectOutputStream> clients;

	public WholeServer()  {
		try {
			clients=new HashMap<String, ObjectOutputStream>(); //수신 중인 리스트.. 동기화를 위함.
			Collections.synchronizedMap(clients);//보낼때, clients에 집어넣고 보내기.(스레드로 인한 복잡성을 sychronized하여 충돌을 막기 위함!)

			ServerSocket server = new ServerSocket(7777);
			System.out.println("서버시작");

			while(true) {
				Socket client = server.accept();
				System.out.println("클라이언트 접속");
				new Receiver(client).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	class Receiver extends Thread{

		String senderAddress; //송신인 주소
		String sender;
		ObjectOutputStream oos;
		ObjectInputStream ois;

		//소켓은 MessageObject객체를 옮길 것. name은 address와 송신인을 받을것!
		public Receiver(Socket socket) {
			senderAddress = ""+socket.getInetAddress(); //송신인 ip
			senderAddress=senderAddress.substring(1);
			//예외처리 : 특수문자나 이물질 들어가는걸 주의할 것!
			try {
				oos = new ObjectOutputStream(socket.getOutputStream());
				System.out.println(oos);
				ois = new ObjectInputStream(socket.getInputStream()); //messageObject가 스트림에 들어옴
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		@Override
		public void run() {
			try {
				System.out.println("<<"+senderAddress+" 접속>>");

				System.out.println(senderAddress+"의 "+oos);
				while(ois!=null) {

					//송신, 수신, 타입, 객체 다 받음
					Object object = ois.readObject();//여기서 받음
					//여기서 다운 캐스팅이 아주 중요!!!!!!!!!!!!!! 클라이언트에서도 패키지 명까지 따라 써줄것!!
					MessageObject msgObject=(hong.server.MessageObject) object; 
					String type=msgObject.type;	// 이걸로 구분시키는 용도로 쓸것!
					sender=msgObject.sender;
					System.out.println("보낸 이 : "+sender);
					System.out.print("받는 이 : ");
					for(String r:msgObject.receivers) {
						System.out.print(r +" ");
					}
					System.out.println("\n메세지  : "+msgObject.messageMain);
					System.out.println("타 입   : "+type);

					//메세지의 type이 ""ConnectServer""이면, oos가 clients에 들어간다.
					if(type.equals("ConnectServer")) { //여기서 첫 접속과 클라이언트 넣기가 이뤄진다!

						clients.put(sender, oos);//여기에서 client가 들어간다
						System.out.println(clients.get(sender)+"의 oos가 들어감");
					}

					//보내기 함수 호출
					System.out.println(msgObject.messageMain);

					sendToReceiver(msgObject);

				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch(UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}finally {
				clients.remove(sender);
				System.out.println(sender+"종료");
				//소켓이 끊겨, 종료 메세지를 받을시, 게임중을 게임 대기로 바꾸고, 전체 테이블에 메세지를 보내주기
				ifQuitForced(sender);
			}
		}
	}

	//테이블의 강제종료시 처리 : (+게임중을 게임 대기로)
	void ifQuitForced(String sender) {
		if(!(sender.equals("주방")||sender.equals("카운터"))){
			ArrayList<String> clients=new ArrayList<String>(this.clients.keySet());
			new TabletDAO().modify(new TabletDTO(), sender);
			sendToAll(new MessageObject(sender, "강제종료", "서버",clients ));
			//강제 종료한 사람을 msgMain에 담아 알려줌
		}
	}

	//방송
	void sendToAll(MessageObject msgObject) {
		for(String r : clients.keySet()) {
			sendToReceiver(msgObject,r);
		}
	}
	/////////<receiver 들에게 msgObject를 송신하는 함수>/////////
	//arrayList에 있는 receiver들을 전부 탐방
	void sendToReceiver(MessageObject msgObject) {
		for(String r:msgObject.receivers) {
			sendToReceiver(msgObject, r);
		}
	}
	//본체. 단일 receiver에게 보내기
	//안열린 address일 경우 예외처리 필요!
	void sendToReceiver(MessageObject msgObject, String receiver) {		
		try {
			ObjectOutputStream oos=clients.get(receiver); //여기서 문제
			System.out.println("=>sendToReceiver에서 ["+oos+"]를  "+receiver +"에게 전송");

			oos.writeObject(msgObject);
			oos.flush();
			oos.reset();
		} catch (IOException e) {
			e.printStackTrace();
		}catch (NullPointerException e) {
			System.out.println(receiver+"는 접속해있지 않음");
			sendToReceiver(new MessageObject(receiver+"는 접속해있지 않음", msgObject.getType(), msgObject.getSender(), msgObject.getSender()));
		}
	}

	public static void main(String[] args) {
		new WholeServer();
	}
}
