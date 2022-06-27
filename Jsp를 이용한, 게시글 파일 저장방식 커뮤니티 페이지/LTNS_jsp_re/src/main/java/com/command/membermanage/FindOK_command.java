package main.java.com.command.membermanage;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.com.command.Command;
import main.java.com.view.MemberDAO;
import main.java.com.view.MemberDTO;

public class FindOK_command implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			request.setCharacterEncoding("utf-8");
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String email = request.getParameter("email");
		
		MemberDAO dao;
		MemberDTO[] dto = null;
		
		int cnt = 0 ;
		
		if (email != null)  {
			
			try {
				cnt = new MemberDAO().email_overlap(email);
				request.setAttribute("emailovlapOK", cnt);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			
		}
		
		if (cnt == 1) {
			
			try {
				dto = new MemberDAO().member_FIND_Select(email);
				
				
				request.setAttribute("emailOK", dto);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}

}
