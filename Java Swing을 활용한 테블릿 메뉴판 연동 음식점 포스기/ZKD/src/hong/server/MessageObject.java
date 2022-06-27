package hong.server;

import java.io.Serializable;
import java.util.ArrayList;

//메세지 양식
public class MessageObject implements Serializable{
	String sender;
	ArrayList<String> receivers=new ArrayList<String>(); //수신인들
	String type=""; //메세지 타입
	Object messageMain; //메세지 본문 객체

	public ArrayList<String> getReceiver() {
		return receivers;
	}
	public void setReceiver(ArrayList<String> receivers) {
		this.receivers = receivers;
	}
	public String getType() {
		return type;
	}
	public String getSender() {
		return sender;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Object getMessageMain() {
		return messageMain;
	}
	public void setMessageMain(Object messageMain) {
		this.messageMain = messageMain;
	}
	
	//일반 메세지 생성자 : 양식을 파악 후, 사용해줄것!
	//1. 메세지, 타입, 송신인, 수신인들(배열로)
	public MessageObject(Object messageMain, String type, String senderName,String ...receivers){
		init(messageMain,type,senderName);
		setReceiver(receivers);
	}
	//2. 메세지, 타입, 송신인, 수신인들(어레이리스트로)
	public MessageObject(Object messageMain, String type, String senderName,ArrayList<String> receivers){
		init(messageMain, type, senderName);
		this.receivers=receivers;
	}
	void init(Object messageMain, String type, String senderName) {
		this.sender=senderName;
		this.messageMain=messageMain;
		this.type=type;
	}
	void setReceiver(String ...receivers) {
		for(String r:receivers) {
			this.receivers.add(r);
		}
	}
	@Override
	public String toString() {
		String str="";
		str+="messageMain:"+messageMain;
		return str;
	}
}
