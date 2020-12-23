package main.java.com.command.membermanage;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.com.command.Command;
import main.java.com.email.MailSend;

public class Email_ajax_command implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String email = request.getParameter("email");
		System.out.println(email);
		
		
		MailSend ms = new MailSend();
		ms.MailSend(email);
		
		System.out.println(email);	
		
		
		
		String emailck1 = Integer.toString(ms.random);
		
		System.out.println(emailck1);
		
		response.setContentType("text/html; charset=utf-8");
		
		try {
			response.getWriter().write(emailck1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		

	}

}
