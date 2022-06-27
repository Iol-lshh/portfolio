package main.java.com.command.post;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.java.com.command.Command;
import main.java.com.view.recomendDAO;
import main.java.com.view.recomendDTO;

public class recomendCommend implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		int cnt =0;
		
		
		HttpSession session =  request.getSession();
		int post_id = Integer.parseInt(request.getParameter("post_id"));
		//int rec_chkes =  (int) session.getAttribute("rec_chk_chks"); // 1 공감 ++ , 0 공감 -- 
		int mm_id =  (int) session.getAttribute("writer");
		int cns = 9999;
		
		
		
		
		//추천값 조회 
		if(post_id != 0 && mm_id !=0) {
			 recomendDAO dao = new recomendDAO();
			 cns = dao.empathize_select(post_id, mm_id);
		}
		
		
		if(cns == 0) {
			recomendDAO dao = new recomendDAO();
			cnt = dao.empathize_insert(post_id, mm_id);
			request.setAttribute("rec", cnt);
			request.setAttribute("like", 0);
		}else if(cns == 1) {
			recomendDAO dao = new recomendDAO();
			cnt = dao.empathize_delete(post_id, mm_id);
			request.setAttribute("rec", cnt);
			request.setAttribute("like", 1);
		}
		
		
		
		
		
	}

	

}
