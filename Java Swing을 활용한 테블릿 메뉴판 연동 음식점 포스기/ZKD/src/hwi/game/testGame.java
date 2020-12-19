package hwi.game;

import aaaaaaaaaaaaaaa.InitData;
import hong.client.DefaultClient;
import hong.client.ReceiverObjFromClient;
import hong.server.MessageObject;

public class testGame implements ReceiverObjFromClient {

	public static void main(String[] args) {
		new testGame().doo();;
	}

	void doo() {
		new GameJF_2(9, new DefaultClient("temp", this, InitData.ip, 7777), "",null);

	}
	@Override
	public void getMsgObjectFromClient(MessageObject msgObject) {
		// TODO Auto-generated method stub
		
	}

}
