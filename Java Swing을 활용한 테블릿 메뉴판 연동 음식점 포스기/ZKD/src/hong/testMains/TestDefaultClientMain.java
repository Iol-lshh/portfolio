package hong.testMains;

import aaaaaaaaaaaaaaa.InitData;
import hong.client.DefaultClient;
import hong.client.ReceiverObjFromClient;
import hong.server.MessageObject;

class TestDefaultClient implements ReceiverObjFromClient{
	String clientName="temp2";
	
	TestDefaultClient()	{
		//서버 연결하기 -반드시 해줄것!
		//1. DefaultClient를 멤버변수로 생성합니다.
		DefaultClient a=new DefaultClient(clientName,this,InitData.ip,7777);


		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}


		//이걸로 메세지를 원하는 사람에게 보냄!
		String senderName="temp";
		String msgMain="김연지 미워!";
		a.sendMessage(msgMain, "",senderName);
	}

	//요렇게 ReceiverObjFromClient를 implements해서,
	//getMsgObjectFromClient를 구현하여,
	//msg를 받으시면 됩니다!!!! 
	@Override
	public void getMsgObjectFromClient(MessageObject msgObject) {
		System.out.println("받았당");
		System.out.println(msgObject.getMessageMain());
	}
}

public class TestDefaultClientMain {

	public static void main(String[] args) {
		new TestDefaultClient();
	}

}
