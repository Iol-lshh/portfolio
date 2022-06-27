package main.java.com.command.membermanage;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.com.command.By_AJAX;
import main.java.com.command.Command;
import main.java.com.model.membermanage.MM_DAO;
import main.java.com.model.membermanage.MM_DTO;

public class DeleteMM_Command_By_AJAX implements Command, By_AJAX {

	@Override
	public void responseJSON(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
	}

	@Override
	public void responseXML(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("세션살아있니?" + (int) request.getSession().getAttribute("writer"));
		int mm_id = (int) request.getSession().getAttribute("writer");
		try {
			response.setContentType("text/html; charset=utf-8");
			try {
				int result=new MM_DAO().deleteBySQL_withDTO(MM_DAO.DELETE_MM_BY_DTO, new MM_DTO(mm_id));
				response.getWriter().write(""+result);
			} catch (SQLException e) {
				System.out.println("sql 문제");
				response.getWriter().write("0");
			}
		} catch (IOException e) {
			System.out.println("response 에러");
		}
	}

}
