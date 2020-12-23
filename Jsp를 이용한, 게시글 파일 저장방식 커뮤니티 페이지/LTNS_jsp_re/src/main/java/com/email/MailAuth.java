package main.java.com.email;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class MailAuth extends Authenticator{
	
	PasswordAuthentication pa;
	
	public MailAuth() {
		String mail_id = "LTNSReport";
		String mail_pw = "!admin123";
		
		pa = new PasswordAuthentication(mail_id, mail_pw);
	}
	
	public PasswordAuthentication getPasswordAuthentication() {
		return pa;
	}

}
