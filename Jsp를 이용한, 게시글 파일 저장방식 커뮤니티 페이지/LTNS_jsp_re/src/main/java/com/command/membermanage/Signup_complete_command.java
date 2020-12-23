package main.java.com.command.membermanage;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.com.command.Command;
import main.java.com.view.MemberDAO;
import main.java.com.view.MemberDTO;

public class Signup_complete_command implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		
		
	try {
		request.setCharacterEncoding("utf-8");
		
		
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String nik = request.getParameter("nik");
		String email = request.getParameter("email");
		
		System.out.println(id);
		System.out.println(pw);
		System.out.println(nik);
		System.out.println(email);
		
		
		MemberDAO dao;
		MemberDTO[] dto = null;
		System.out.println("확인");
		

		
		int cnt = 0;
		//쿼리 실패 0 성공 1
		if (id != null)  {
			System.out.println("확인");
			
			try {
				cnt = new MemberDAO().select_join(id, nik, email);
				request.setAttribute("joinOK", cnt);
				System.out.println("두번째확인 뒤"+cnt);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			
		}
		
		
		
		
		
		
		if(cnt == 0) { //유니크 조회 성공
			
			int cnt2=0;
			System.out.println("확인");
			try {
				cnt2 = new MemberDAO().insert_join(id, pw, nik, email);
				
				
				
				request.setAttribute("insertOK",cnt2);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			try {
				dto = new MemberDAO().member_INFO_Select(id);
				
				System.out.println("확인Signup"+dto);
				
				request.setAttribute("signOK", dto);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		}
		
	
		
		
		
		
		
		
	
	

	}

}
