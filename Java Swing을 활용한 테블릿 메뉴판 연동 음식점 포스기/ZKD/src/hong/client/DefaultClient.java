package hong.client;

import java.awt.Image;
import java.io.*;
import java.net.*;
import java.util.ArrayList;

import hong.server.MessageObject;


//서버가 받는 포트 7777


///////////////////////////////
//클라이언트 양식
public class DefaultClient{
	String clientName;
	String serverIP;
	int serverPort;
	ObjectOutputStream oos;
	MessageObject messageObject;//클라이언트를 멤버변수로 받는 객체

	public ArrayList<ReceiverObjFromClient> receiverObjFromClients=new ArrayList<ReceiverObjFromClient>();//이 객체를 이용하는 객체를 위한 변수

	public DefaultClient(String clientName,ReceiverObjFromClient receiverObjFromClient, String serverIP, int serverPort) {
		this.clientName=clientName;
		receiverObjFromClients.add(receiverObjFromClient);
		try {
			System.out.println("myAddress:"+InetAddress.getLocalHost().getHostAddress());
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		}


		setServer(serverIP, serverPort);
		try {
			//서버로부터의 수신
			Socket socket = new Socket(serverIP,serverPort);
			new Receiver(socket).start();
			oos=new ObjectOutputStream(socket.getOutputStream());//보내기 스트림 생성

		} catch (UnknownHostException e) {
			e.printStackTrace();
			System.out.println("client error : 알 수 없는 호스트");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("client error : 서버를 찾을 수 없습니다!");
		}
		try {
			//처음 실행시 보내는 서버 등록 메세지
			sendMessage("서버연결 완료", "ConnectServer", clientName);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("client error : 클라이언트 실종");
		}
	}
	void setServer(String serverIP,	int serverPort) {
		this.serverIP=serverIP;
		this.serverPort=serverPort;
	}
	
	//ReceiverObjFromClient 객체를 어레이리스트에 추가 함수 =>게임
	public void addReceiverObjFromClient(ReceiverObjFromClient receiverObjFromClient) {

		receiverObjFromClients.add(receiverObjFromClient);
		System.out.println("receiver 추가됨! : "+receiverObjFromClient);
	}
	//ReceiverObjFromClient 객체를 어레이리스트에서 제거 함수 =>게임
	public void removeReceiverObjFromClient(ReceiverObjFromClient receiverObjFromClient) {
		receiverObjFromClients.remove(receiverObjFromClient);
		System.out.println("receiver 제거됨! : "+receiverObjFromClient);
	}
	//리시버
	class Receiver extends Thread{
		String sender; //송신인 ip
		ObjectInputStream ois;

		//소켓은 MessageObject객체를 옮길 것. name은 address와 송신인을 받을것!
		public Receiver(Socket socket) { 
			try {
				ois=new ObjectInputStream(socket.getInputStream());
			} catch (IOException e) {
				System.out.println("client error : 소켓을 읽을 수 없습니다!");
			}
		}

		@Override
		public void run() {
			System.out.println("서버 듣기");
			while(ois!=null) {
				try {				
					//여기서 수신 다 받음!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
					messageObject =(hong.server.MessageObject) ois.readObject();//여기서 받음
					//여기서 object로 보내기 때문에!!!!! 반드시 다운캐스팅해서 사용할것!!!!!!!!!!!!!!!!!!!!!!!!!!!!
					System.out.println(messageObject.getSender()+"로부터 수신 완료 :"+(messageObject.getMessageMain()));
					////////////////////////여기서 필요

					System.out.println(receiverObjFromClients.size()+":"+ receiverObjFromClients);
					for(ReceiverObjFromClient receiverObjFromClient:receiverObjFromClients) {
						receiverObjFromClient.getMsgObjectFromClient(messageObject);
					}
				} catch (Exception e) {
					e.getStackTrace();
					//					System.out.println("client error : 메세지를 읽을 수 없습니다!");
				}
			}
		}
	}

	//보낼 메세지와 받을 사람들의 주소를 매개변수로 받는다. 이걸로 메세지를 원하는 곳에 보내면 됩니다!!!
	public void sendMessage(Object msgMain,String type, ArrayList<String> receivers) {
		String [] receiversArr=new String[receivers.size()];
		for(int i=0;i<receivers.size();i++) {
			receiversArr[i]=receivers.get(i);
		}
		sendMessage(msgMain, type, receiversArr);
	}
	//본문
	public void sendMessage(Object msgMain,String type, String ...receivers) {
		try {
			MessageObject msgObject=new MessageObject(msgMain, type, clientName, receivers);
			oos.writeObject(msgObject);
			oos.flush();
			oos.reset();
		}catch (Exception e) {
			e.printStackTrace();
			//			System.out.println("client error : oos를 찾을 수 없습니다!");
		}		
	}
	//이미지 객체를 보낼 때
	public void sendMessage(Image image,String type, String ...receivers) {
		Object msgMain=arrayToArrayList(objectToByteArr(image));
		sendMessage(msgMain, type, receivers);	
	}
	//배열을 객체로 만들어줌
	public ArrayList arrayToArrayList(Object...objects) {
		ArrayList<Object> arrayList=new ArrayList<Object>();
		for(Object o:objects) {
			arrayList.add(o);
		}
		return arrayList;
	}
	//기타 serializable이 안되는 객체를 byteArr로 만들어주는 함수
	public byte[] objectToByteArr(Object o) {
		byte[] serializedObject = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(baos);
			oos.writeObject(o);
			serializedObject = baos.toByteArray();
			System.out.println(serializedObject);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return serializedObject;
	}

	//이미지를 서버에서 받아야 할 경우 이 함수를 사용해 줄것!
	//byteArr가 된 기타 serializable이 안되는 객체를 다시 Object로 만들어주는 함수(gui에 사용)
	public Object byteArrToObject(byte ...byteArr) {
		Object object = null;
		try {
			ByteArrayInputStream bais = new ByteArrayInputStream(byteArr);
			ObjectInputStream ois = new ObjectInputStream(bais);
			object = ois.readObject();
			System.out.println(object);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return object;
	}
}


