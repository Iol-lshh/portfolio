package main.java.com.util;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

public class Gmail {

	private String host;

	public Gmail(HttpServletRequest request) {
		this.host = request.getRequestURL().toString();
		this.host = host.substring(0, host.lastIndexOf("/") + 1);
	}



	public void emailSend(String fromId, String toId, String subjectText, String contentText) {

		String from = fromId;
		String to = toId;
		String subject = subjectText;
		String content = contentText;

		Properties p = new Properties();
		p.put("mail.smtp.user", from);
		p.put("mail.smtp.host", "smtp.googlemail.com");
		p.put("mail.smtp.port", "465");
		p.put("mail.smtp.starttls.enable", "true");
		p.put("mail.smtp.auth", "true");
		p.put("mail.smtp.debug", "true");
		p.put("mail.smtp.socketFactory.port", "465");
		p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		p.put("mail.smtp.socketFactory.fallback", "false");
		try {	
			Authenticator auth = new MailInfo();
			Session ses = Session.getInstance(p, auth);
			ses.setDebug(true);
			MimeMessage msg = new MimeMessage(ses);
			msg.setSubject(subject);
			Address fromAddr = new InternetAddress(from);
			msg.setFrom(fromAddr);
			Address toAddr = new InternetAddress(to);
			msg.addRecipient(Message.RecipientType.TO, toAddr);
			msg.setContent(content, "text/html; charset=UTF-8");
			Transport.send(msg);
			System.out.println("메일전송완료");
		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.error(e.getMessage());
		}
	}

}
