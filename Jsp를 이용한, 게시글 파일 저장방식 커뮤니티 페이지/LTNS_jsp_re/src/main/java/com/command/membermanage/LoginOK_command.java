package main.java.com.command.membermanage;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.com.command.Command;
import main.java.com.view.MemberDAO;
import main.java.com.view.MemberDTO;

public class LoginOK_command implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		int cnt = 0;
		MemberDAO dao = new MemberDAO();
	
		
		try {
		String id = request.getParameter("id");
		String pw = request.getParameter("password");
	
		
		
		//로그인 
		if(id != null && pw != null) {
			try {
				cnt = dao.login_member(id, pw);
				request.setAttribute("loginOk", cnt);
			} catch (SQLException e) {
				System.out.println("에러1");
				e.printStackTrace();
			}
		}
		
		
		// 정보 읽어오기 
		
			MemberDAO daos = new MemberDAO();
			MemberDTO[] arr = null; 
			try {
				arr = daos.member_INFO_Select(id);
				request.setAttribute("info_chk", arr);
			} catch (SQLException e) {
				System.out.println("에러1");
				e.printStackTrace();
			}
		
	
		}catch (Exception e) {
			// TODO: handle exception
		}

	}

}
