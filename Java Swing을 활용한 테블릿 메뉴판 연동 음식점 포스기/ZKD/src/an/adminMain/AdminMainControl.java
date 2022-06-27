package an.adminMain;

import java.awt.EventQueue;

import javax.swing.JOptionPane;

import aaaaaaaaaaaaaaa.InitData;
import an.adminLogin.AdminLoginControl;
import hong.client.DefaultClient;
import hong.client.ReceiverObjFromClient;
import hong.server.MessageObject;

public class AdminMainControl {
	public AdminMainView adminView;
	public AdminLoginControl adminloginControl;

	public AdminMainControl(AdminLoginControl adminControl) {
		this.adminloginControl = adminControl;
		adminView = new AdminMainView(this);

	}
	
}

