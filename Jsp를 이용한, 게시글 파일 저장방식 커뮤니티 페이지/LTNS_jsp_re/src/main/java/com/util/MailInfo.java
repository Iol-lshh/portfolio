package main.java.com.util;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class MailInfo extends Authenticator {
	
	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication("LTNSReport", "!admin123");
		// 구글 => 해당 아이디 로그인 => 설정 => 로그인 및 보안 => 계정 액세스 권한을 가진 앱 => 보안 수준이 낮은 앱 => 사용함
	}
	
}
