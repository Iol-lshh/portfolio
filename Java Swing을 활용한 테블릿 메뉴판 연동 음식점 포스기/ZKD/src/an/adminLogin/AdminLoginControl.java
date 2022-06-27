package an.adminLogin;

import javax.swing.JFrame;

import an.adminMain.AdminMainControl;
import an.tableBoard.TableBoardView;

public class AdminLoginControl {

	public AdminLoginView loginview; // 로그인 뷰 불러오기.
	public TableBoardView boardView;
	ChangePWFrame cPWFrame;
//	public an_adminLoginControl() {
//		loginview = new an_adminLoginView(this);
//	}

	public AdminLoginControl(TableBoardView boardView) {
		this.boardView = boardView;
		this.loginview = new AdminLoginView(this);
	}

	
	void loginViewClose() {

		// loginview.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loginview.dispose();
		new AdminMainControl(this);
	}

	void changePW(String aD_PW) {
		AdminLogDTO dto = new AdminLogDTO();
		dto.setAD_PW(aD_PW);
		new AdminLogDAO().modify(dto);
	}

}
