package main.java.com.command.membermanage;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.com.command.Command;
import main.java.com.view.MemberDAO;
import main.java.com.email.*;

public class Signup_ajax_command implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		
		try {
			request.setCharacterEncoding("utf-8");
			
		} catch (Exception e) {
			e.printStackTrace();
			
			
		}
		
		String id = request.getParameter("id");
		
		MemberDAO dao;
		
		
		int cnt = 0;
		
		if (id != null) {
			
			try {
				cnt = new MemberDAO().id_overlap(id);
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		System.out.println(cnt);
		
		try {
			response.setContentType("text/html; charset=utf-8");
			response.getWriter().write(cnt+"");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		String nik = request.getParameter("nik");
		
		
		if(nik != null) {
			
			try {
				cnt = new MemberDAO().nik_overlap(nik);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		response.setContentType("text/html; charset=utf-8");
		try {
			response.getWriter().write(cnt+"");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	String email = request.getParameter("email");
		
		
		if(email != null) {
			
			try {
				cnt = new MemberDAO().email_overlap(email);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		response.setContentType("text/html; charset=utf-8");
		try {
			response.getWriter().write(cnt+"");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		

		
	
		

	}
	

	
	
	
	
	
	
	

}
