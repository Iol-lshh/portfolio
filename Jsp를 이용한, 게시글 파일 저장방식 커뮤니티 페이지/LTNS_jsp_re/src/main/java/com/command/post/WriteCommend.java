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
import javax.servlet.http.HttpSession;

import main.java.com.command.Command;
import main.java.com.model.post.FileWriteDAO;
import main.java.com.model.post.WriteDAO;

public class WriteCommend implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		// 1. 내용 파일화
		String encoding = "utf-8";
		String filePath =  null;
		
		try {
			request.setCharacterEncoding(encoding);
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} 
		// request data
	    
        

        	String title = request.getParameter("title"); // 요청에서, title, content라는 name을 가진 파라미터 리턴
    		String content = request.getParameter("content");
    		String saveDirectory = request.getServletContext().getRealPath("/") + "data";
            String category = request.getParameter("category");
            
        
        
       HttpSession session =  request.getSession();
       int writer =  (int) session.getAttribute("writer");
        
		
		
		Date d = new Date();
		SimpleDateFormat sm = new SimpleDateFormat("yyyyMMddHHmmss");

		String fName = sm.format(d) + ".txt"; // DB연동이되면 UID값 추가할 예정

		if (title.isEmpty() || content.isEmpty()) // 내용이없으면
		{
			try {
				response.sendRedirect("write.jsp"); //복귀시키기
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("복귀실패");
			}
			
		} else {
			PrintWriter pw = null; //
			try {
				
				File file = new File(saveDirectory);
				if (!file.exists()) { // 디렉토리가 없으면 만들어줌
					file.mkdirs();
				}
				filePath = saveDirectory + file.separator + fName; // 파일경로
				// 내용저장객체 생성
				pw = new PrintWriter(filePath);
				pw.println("title" + title);
				pw.println(content);
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
		

		//2.1 파일등록 
		int file_quert_cnt = 0;
		FileWriteDAO filedao = new FileWriteDAO();

		String filekind = "txt";
		
		if (fName != null && filePath != null) {
			try {
				file_quert_cnt = filedao.fileInsert(filekind, filePath, fName);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		request.setAttribute("fileOk", file_quert_cnt);
				
		
		
		//2.글 등록  
		int cnt = 0;
		WriteDAO dao = new WriteDAO();
		if( title != null && title.trim().length() > 0) {
			try {
				cnt = dao.wr_insert(title, category, writer, file_quert_cnt);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}// end if 
		
		
		request.setAttribute("result", cnt);
      
        
	}

}
