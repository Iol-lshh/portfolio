package main.java.com.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.com.command.Command;
import main.java.com.command.mainpage.Mainpage_Command;
import main.java.com.command.membermanage.FindOK_command;
import main.java.com.command.membermanage.LoginOK_command;
import main.java.com.command.membermanage.Signup_complete_command;
import main.java.com.command.membermanage.LogOut_command;
import main.java.com.command.mypage.Mypage_command;
import main.java.com.command.mypage.MyPagerev_Ok;
import main.java.com.command.post.WriteCommend;
import main.java.com.command.post.deleteCommend;
import main.java.com.command.post.recomendCommend;
import main.java.com.command.post.report_Cmd;
import main.java.com.command.post.updateCommend;
import main.java.com.command.post.updateOkCommend;
import main.java.com.command.post.ViewCommend;

@WebServlet("*.do")
public class DoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 
	   public DoController() {
	        super();
	    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actionDo(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actionDo(request, response);
	}
	
	private void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		// url로부터 uri contextpath, commend 분리 
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String com = uri.substring(conPath.length());
				
		System.out.println("uri:"+ uri);
		System.out.println("conPath:"+ conPath);
		System.out.println("com:"+ com);
				
				// 컨트롤러는 아래 두가지를 결정해야 한다. 
		Command cmd = null; // 어떠한 로직을 수행할지 결정 
		String viewPage = null; // 어떠한 페이지를 보여줄지 결정 
		
		if((com.equals("/membermanage/loginmain.do"))&&(com.equals("/membermanage/loginOk.do"))&&(com.equals("/membermanage/sign-up-complete.do"))) {
			if(request.getSession().getAttribute("login") == null)  {
				request.getSession().setAttribute("messageType", "오류 메시지");
				request.getSession().setAttribute("messageContent", "로그인이 필요한 서비스 입니다.");
				response.sendRedirect(request.getContextPath() + "/membermanage/loginmain.jsp");
				return;
			}
		}
		
		switch (com) {

		case "/board/board_list.do":
			viewPage = "board_list.jsp";
			break;
			
		case "/post/write.do":
			if(request.getSession().getAttribute("login") == null)  {
				response.sendRedirect(request.getContextPath() + "/membermanage/loginmain.jsp");
				return;
			}
			viewPage = "write.jsp";
			break;
			
		case "/post/writeOk.do":
			cmd = new WriteCommend();
			cmd.execute(request, response);
			viewPage = "writeOk.jsp";
			break;
			
			
		case "/post/view.do":
			if(request.getSession().getAttribute("login") == null)  {
				response.sendRedirect(request.getContextPath() + "/membermanage/loginmain.jsp");
				return;
			}
			cmd = new ViewCommend();
			cmd.execute(request, response);
			viewPage = "view.jsp";
			break;	

		case "/post/deleteOk.do":
			if(request.getSession().getAttribute("login") == null)  {
				response.sendRedirect(request.getContextPath() + "/membermanage/loginmain.jsp");
				return;
			}
			cmd = new deleteCommend();
			cmd.execute(request, response);
			viewPage = "deleteOk.jsp";
			break;
		
		case "/post/update.do":
			if(request.getSession().getAttribute("login") == null)  {
				response.sendRedirect(request.getContextPath() + "/membermanage/loginmain.jsp");
				return;
			}
			cmd = new updateCommend();
			cmd.execute(request, response);
			viewPage = "update.jsp";
			break;
			
		case "/post/updateOk.do":
			cmd = new updateOkCommend();
			cmd.execute(request, response);
			viewPage = "updateOk.jsp";
			break;
			
			
			
		case "/post/recomendOk.do":	
			cmd = new recomendCommend();
			cmd.execute(request, response);
			viewPage = "recomendOk.jsp";
			break;
	
		
		case "/mainpage/mainpage.do":
			cmd=new Mainpage_Command();
			cmd.execute(request, response);
			viewPage="mainpage.jsp";
			break;
			
		case "/mypage/mypage.do":
			 cmd=new Mypage_command();
			 cmd.execute(request, response);
			 viewPage="mypage.jsp";
			break;
			
		case "/membermanage/loginmain.do":
			viewPage= "loginmain.jsp";
			break;
			
		case "/membermanage/loginOk.do":
			cmd = new LoginOK_command();
			cmd.execute(request, response);
			viewPage= "loginOk.jsp";
			break;
		
		case "/membermanage/logoutOk.do":
			cmd = new LogOut_command();
			cmd.execute(request, response);
			viewPage= "logoutOk.jsp";
			break;
			
		case "/mypage/myPage_ok.do":
			cmd = new MyPagerev_Ok();
			cmd.execute(request, response);
			viewPage = "myPage_ok.jsp";
			break;
			
		
		case "/membermanage/sign-up-complete.do":
			cmd = new Signup_complete_command();
			cmd.execute(request, response);
			viewPage= "sign-up-complete.jsp";
			break;
			
			
		case "/post/report_ok.do":
			cmd = new report_Cmd();
			cmd.execute(request, response);
			viewPage = "report_ok.jsp";
			break;
			
		case "/membermanage/findOK.do":
			cmd = new FindOK_command();
			cmd.execute(request, response);
			viewPage = "findOK.jsp";
			break;
			
			
		}//end swithc
		
		if(viewPage != null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
			dispatcher.forward(request, response);
		}
		
	}// end actiondo

}
