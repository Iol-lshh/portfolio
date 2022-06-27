package main.java.com.command.membermanage;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import main.java.com.command.Command;

public class LogOut_command implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session =  request.getSession();
		
		session.invalidate();
		
		System.out.println("세션 삭제 완료 ");
		

		
		

		
		
		
	}

}
