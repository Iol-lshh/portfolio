package main.java.com.command.mypage;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.java.com.command.Command;
import main.java.com.model.membermanage.MM_DAO;
import main.java.com.model.membermanage.MM_DTO;
import main.java.com.view.MemberDAO;
import main.java.com.view.MemberDTO;

public class MyPagerev_Ok implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
			String password = request.getParameter("password");
			String nickname = request.getParameter("nickname");
			MemberDTO[] arrs = null;
			HttpSession session =  request.getSession();
			int mm_id =  (int) session.getAttribute("writer");
			
		
			System.out.println(nickname + "닉네임");
			System.out.println(mm_id + "mm id");
			
		
			//닉네임 중복 cnt 
			String chk_name ="";
			
			if(password != null) {
				try {
					MemberDAO dao = new MemberDAO();
					int cnt = 0; 
					cnt = dao.update_pw(password, mm_id);
					System.out.println("비번변경진입");
					request.setAttribute("rev_pw", cnt);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 }
			
			
			
			if(nickname != null) {
				
				MemberDAO dao = new MemberDAO();
				System.out.println("진입??");
				try {
					System.out.println("진입??");

					try {
					arrs = dao.member_MM_NICK_Select(nickname);
					chk_name = arrs[0].getNickname();
					}catch (NullPointerException e) {
						chk_name = "clean";
						System.out.println("중복 닉네임 없음");
					}
					
				} catch (SQLException e) {
					e.printStackTrace();

				}
				
			}
			
			
			if(chk_name.equals("clean")) {
			
			if(nickname != null) {
				MemberDAO dao = new MemberDAO();
				int cnt = 0; 
				try {
					cnt = dao.update_nick(nickname, mm_id);
					request.setAttribute("rev_pw", cnt);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			}
			
			
			if(nickname != null) {
				MemberDTO[] arr = null;
				MemberDAO das = new MemberDAO();
				try {
					arr = das.member_MM_ID_Select(mm_id);
					request.setAttribute("rev_nick", arr);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
			}
			
			
	}

}
