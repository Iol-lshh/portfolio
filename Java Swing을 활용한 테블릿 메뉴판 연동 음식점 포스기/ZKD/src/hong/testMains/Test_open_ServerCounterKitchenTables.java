package hong.testMains;

import aaaaaaaaaaaaaaa.InitData;
import an.tableBoard.TableBoardControl;
import hong.server.WholeServer;
import hong.table.TableMainController;
import sup.kitchen.KitchenMainController;

public class Test_open_ServerCounterKitchenTables {

	public static void main(String[] args) {
		new SetWholeServer().start();
		InitData.setIp("localhost");
		new KitchenMainController();
		new TableMainController("테이블_1");
		new TableMainController("테이블_3");
		
		new TableBoardControl();

	}
	
}
class SetWholeServer extends Thread{
	@Override
	public void run() {
		new WholeServer();
	}
}
