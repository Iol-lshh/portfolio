package main.java.com.command.post;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.java.com.command.Command;
import main.java.com.util.Gmail;

public class report_Cmd implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
	

		HttpSession session =  request.getSession();
		String nickname = (String) session.getAttribute("nickname");
		int post_id = Integer.parseInt(request.getParameter("post_id"));
		
		
		
		String titles = request.getParameter("subjects");
		String artcle = request.getParameter("artcle");
		String select = request.getParameter("select");
		
		StringBuffer sbu = new StringBuffer();
		
		
		sbu.append("<p> 게시글 번호:"+post_id +"</p>");
		sbu.append("<p> 발신한 사람:"+nickname +"</p>");
		sbu.append("<p> 신고 사유 :"+select +"</p>");
		sbu.append("<p> 제목 : "+titles +"</p>");
		sbu.append("<p> 내용 : "+artcle +"</p>");
		
		String contents = sbu.toString();
		
		
		
		String myMail= "nbnloper@gmail.com";
		new Gmail(request).emailSend(myMail, "LTNSReport@gmail.com", titles, contents);
		
	
		request.setAttribute("mailOk", post_id);

		
	}

}
