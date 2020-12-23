package main.java.com.command.mainpage;

import java.sql.SQLException;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import main.java.com.command.By_AJAX;
import main.java.com.command.Command;
import main.java.com.model.DAO;
import main.java.com.model.DTO;
import main.java.com.model.JSON;
import main.java.com.model.mainpage.Mainpage_DAO;
import main.java.com.model.mainpage.Post_DTO;

public class Mainpage_Add_NearestBoard_Command_By_AJAX implements Command, By_AJAX {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// parameter 받아오기, 없으면 json 으로 response(디폴트)
		int mm_id=(int)request.getSession().getAttribute("writer");
		String reqType = request.getParameter("reqType");
		if(reqType == null) reqType = "json";

		// "xml" 혹은 "json" 으로 response 하기
//		switch(reqType) {
//		
//		case "xml":
//			responseXML(request, response);  // jdom 사용하여 xml response
//			break;
//			
//		case "json":
//			responseJSON(request, response);  // org.json 사용
//			break;
//			
//		default:
			responseJSON(request, response);  // org.json 사용
//			break;		
//		
//		} // end switch
		
	} // end execute()

	@Override
	public void responseJSON(HttpServletRequest request, HttpServletResponse response) {
		try {
			responseJSONbyJackson(request,response);
		}catch(Exception e) {
		}
	}

	@Override
	public void responseXML(HttpServletRequest request, HttpServletResponse response) {
		try {
			responseXMLbyJackson(request,response);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void responseJSONbyJackson(HttpServletRequest request, HttpServletResponse response) {
		String regdate=request.getParameter("regdate");
		String post_id=request.getParameter("post_id");
		//트랜잭션 수행
		DTO[] dtoArr=null;
		try {
			System.out.println("dhkdkdkdkkdk");
			System.out.println("오긴했냐.."+post_id);
			System.out.println("아니.."+Integer.parseInt(post_id));
			dtoArr = new Mainpage_DAO().selectBySQL(Mainpage_DAO.SELECT_NEXT_3_POSTS_BY_NEAREST, Integer.parseInt(post_id));
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
			list.setCount(dtoArr.length);
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
	
	public void responseXMLbyJackson(HttpServletRequest request, HttpServletResponse response) {
		
		
	}
}
