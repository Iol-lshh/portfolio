package main.java.com.command.post;

import java.io.File;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.com.command.Command;
import main.java.com.model.post.FileWriteDAO;
import main.java.com.model.post.FileWriteDTO;
import main.java.com.model.post.WriteDAO;

public class deleteCommend implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		int cnt = 0;
		FileWriteDAO dao = new FileWriteDAO();
		FileWriteDTO[] arr = null;
		int requestsdata = Integer.parseInt(request.getParameter("post_content"));
		String realfilename;
		try {
			arr = dao.view_cotent(requestsdata);
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(requestsdata + " 캐치");
		} // end try

		request.setAttribute("deletePost", cnt);
		realfilename = arr[0].real_filename; //패스 경로 꺼내옴 
		
		//경로에 있는 파일 삭제  
		 if(realfilename != null) {
				File fileDelete = new File(realfilename);
				if( fileDelete.delete()) {
					System.out.println(fileDelete + "파일삭제성공");
				}else { System.out.println("실패");}
				
		 }// end if 
		
		 
		 //DB 삭제
			try {
				FileWriteDAO daos = new FileWriteDAO();
				cnt = daos.file_delete(requestsdata);
				request.setAttribute("delteOk", cnt);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 	
		
	}

}
