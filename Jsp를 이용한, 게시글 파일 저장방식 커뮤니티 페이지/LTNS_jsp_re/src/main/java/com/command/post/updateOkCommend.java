package main.java.com.command.post;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.com.command.Command;
import main.java.com.model.post.FileWriteDAO;
import main.java.com.model.post.FileWriteDTO;
import main.java.com.model.post.WriteDAO;
import main.java.com.model.post.WriteDTO;

public class updateOkCommend implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		String encoding = "utf-8";
		
		try {
			request.setCharacterEncoding(encoding);
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} 
		//DB접근하기위한 자료들
		WriteDAO dao = new WriteDAO();
		WriteDTO[] arr = null;
		FileWriteDTO[] file = null;
		FileWriteDAO filedao = new FileWriteDAO();
		int post_contents ;
		
		try {
			int post_id = Integer.parseInt(request.getParameter("post_id"));
			
		
		
		
		
		//정보들을 가지고옴 
		String title = request.getParameter("title"); // 요청에서, title, content라는 name을 가진 파라미터 리턴
		String content = request.getParameter("content");
		String saveDirectory = request.getServletContext().getRealPath("/") + "data";
        String category = request.getParameter("category");
        String deleteFilesPath =request.getParameter("urls");
    	String realPath = "";
		
		
		
		// 수정한 post_id 의 유저정보를 가지고옴
		try {
			arr = dao._post_update(post_id);
			request.setAttribute("update", arr);
		} catch (Exception e) {
			e.printStackTrace();
		} // end try

		post_contents = arr[0].getPost_contents();

		// 파일정보읽어오기 위함
		if (post_contents != 0) {
			try {
				file = filedao.view_cotent(post_contents);
				request.setAttribute("file_view", file);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} // end try
        
		
		String dbFilename = deleteFilesPath + file[0].filename;
		
		
		Date d = new Date();
		SimpleDateFormat sm = new SimpleDateFormat("yyyyMMddHHmmss");
		String fName = sm.format(d) + ".txt"; // DB연동이되면 UID값 추가할 예정

		
		
		if(dbFilename != null) {
			File fileDelete = new File(dbFilename);
			if( fileDelete.delete()) {
				System.out.println(fileDelete + "파일삭제성공");
			}else { System.out.println("실패");}
			
		}// end if 
		
		
		
		// 파일 생성 
		if (title.isEmpty() || content.isEmpty()) // 내용이없으면
		{
			try {
				response.sendRedirect("update.do"); //복귀시키기
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("복귀실패");
			}
			
		} else {
			PrintWriter pw = null; 
			try {
				
				File files = new File(saveDirectory);

				if (!files.exists()) { // 디렉토리가 없으면 만들어줌
					files.mkdirs();
				}

				realPath = saveDirectory + File.separator + fName  ; // 파일경로
				// 내용저장객체 생성
				pw = new PrintWriter(realPath);
				pw.println("title" + title);
				pw.print(content);
				
				System.out.println("저장되었습니다");
			} catch (IOException e) {
				System.out.println("저장 실패 : 파일에 데이터를 쓸 수 없습니다.");
			} finally {
				try {
					pw.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}// end else;
		
		
		
		//파일업데이트 		
		int file_quert_cnt = 0;
		if (realPath != null) {
			try {
				FileWriteDAO fileupdate = new FileWriteDAO();
				file_quert_cnt = fileupdate.File_Update(fName, realPath, post_contents);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}// end if 

		request.setAttribute("fileUpdateOk", file_quert_cnt);
		
		
		
		
		//글 정보 업데이트 
		
		int write_update_cnt = 0;
		
		if(title != null) {
			try {
				WriteDAO wrdao = new WriteDAO();
				write_update_cnt = wrdao.post_Update(title, category,post_id);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}// end if
	
	}catch (Exception e) {
		System.out.println("넘범폽맵입셉션");
	}//end try
		
	}

}
