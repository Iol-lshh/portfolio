package main.java.com.command.mypage;

import java.sql.SQLException;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import main.java.com.command.By_AJAX;
import main.java.com.command.Command;
import main.java.com.model.DTO;
import main.java.com.model.JSON;
import main.java.com.model.mainpage.Mainpage_DAO;
import main.java.com.model.mypage.Count_DAO;
import main.java.com.model.mypage.Count_DTO;
import main.java.com.model.mypage.Mypage_DAO;

public class Mypage_Add_MyPost_Command_By_AJAX implements Command, By_AJAX {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("세션살아있니?"+(int)request.getSession().getAttribute("writer"));

		String reqType = request.getParameter("reqType");
		if(reqType == null) reqType = "json";
			responseJSON(request, response);  // org.json 사용

	}

	@Override
	public void responseJSON(HttpServletRequest request, HttpServletResponse response) {
		try {
			responseJSONbyJackson(request,response);
		}catch(Exception e) {
		}
	}

	/////////////////////////
	public void responseJSONbyJackson(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("세션살아있니?"+(int)request.getSession().getAttribute("writer"));
		//mm_id만 받자!
		int mm_id=(int)request.getSession().getAttribute("writer");
		int btn_id=Integer.parseInt(request.getParameter("btn_id"));
		//트랜잭션 수행
		DTO[] dtoArr=null;
		int count=0;
		try {
			System.out.println("dhkdkdkdkkdk");
			System.out.println("아니.."+mm_id);
			dtoArr = new Mypage_DAO().selectBySQL(Mypage_DAO.SELECT_6_MY_POSTS_NTH,mm_id,btn_id);
			count= ((Count_DTO)new Count_DAO().selectBySQL(Mypage_DAO.SELECT_MY_POSTS_CNT, mm_id)[0]).getCount();
			System.out.println("dhkdkdkdkkdk2"+dtoArr);
			for(DTO dto : dtoArr) {
				System.out.println("dto:"+dto);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		//JSON 만들기
		JSON list = new JSON(); // response 할 Java 객체
		
		if(dtoArr ==null) {
			list.setStatus("FAIL");
		}else {
			list.setStatus("OK");
			list.setCount(count);
			list.setList(Arrays.asList(dtoArr));
		}
		
		ObjectMapper mapper = new ObjectMapper(); //JSON 매핑 수행하는 객체
		
		try {
			String jsonString = mapper.writeValueAsString(list); //JSON 문자열로 변환
			
			response.setContentType("application/json; charset=utf-8");
			System.out.println(jsonString);
			response.getWriter().write(jsonString);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void responseXML(HttpServletRequest request, HttpServletResponse response) {
	}

}
