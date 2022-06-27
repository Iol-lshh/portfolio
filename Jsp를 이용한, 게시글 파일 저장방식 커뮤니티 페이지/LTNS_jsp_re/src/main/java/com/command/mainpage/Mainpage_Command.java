package main.java.com.command.mainpage;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.com.command.Command;
import main.java.com.model.DAO;
import main.java.com.model.DTO;
import main.java.com.model.mainpage.Mainpage_DAO;
import main.java.com.model.mainpage.Post_DTO;

public class Mainpage_Command implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		DTO [] arr1 = null;
		DTO [] arr2 = null;
		DTO [] arr3 = null;

		//인기게시판- 트랜잭션 수행
		try {
			arr1=new Mainpage_DAO().selectBySQL(Mainpage_DAO.SELECT_6_POSTS_BY_VIEWCNT);
			System.out.println("서버 확인-뷰카운트 ");
		}catch(Exception e){
			System.out.println("뷰카운트 없음");
		}
		
		request.setAttribute("viewcnt_board", arr1);
		
		//공감게시판
		try {
			arr2=new Mainpage_DAO().selectBySQL(Mainpage_DAO.SELECT_3_POSTS_BY_EMPATHIZE_CNT);
			System.out.println("서버 확인-공감 ");
		}catch (Exception e) {
			System.out.println("공감 없음");
		}
		request.setAttribute("empath_board", arr2);
		
		//최신순게시판
		try {
			arr3=new Mainpage_DAO().selectBySQL(Mainpage_DAO.SELECT_5_POSTS_BY_NEAREST);
		}catch (Exception e) {
			System.out.println("최신글 없음");
		}
			request.setAttribute("nearest_board", arr3);
			// "~_board" 란 name 으로 request 에 arr 값 저장
			// 즉, request 에 담아서 컨트롤러에 전달되는 셈
	}

}
