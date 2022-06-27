package main.java.com.command.post;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.com.command.Command;
import main.java.com.model.post.FileWriteDAO;
import main.java.com.model.post.FileWriteDTO;
import main.java.com.model.post.WriteDAO;
import main.java.com.model.post.WriteDTO;

public class updateCommend implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		WriteDAO dao = new WriteDAO();
		WriteDTO[] arr = null;
		FileWriteDTO[] file = null;
		FileWriteDAO filedao = new FileWriteDAO();
		
		int post_contents ;
		
		try {
		int post_id = Integer.parseInt(request.getParameter("post_id"));
		
		
		//수정한 post_id 의 유저정보를 가지고옴 
		try {
			arr = dao._post_update(post_id);
			request.setAttribute("update", arr);
		} catch (Exception e) {
			e.printStackTrace();
		} // end try

		
		post_contents = arr[0].getPost_contents();

		//파일정보읽어오기 위함 
		if (post_contents != 0) {
			try {
				file = filedao.view_cotent(post_contents);
				request.setAttribute("file_view", file);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} // end try
	
		
		}catch (Exception e) {
			
		} 
		
		
		
	
	}	

}
