package com.ltns.rest_area.service.admin;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ltns.rest_area.domain.DTO;
import com.ltns.rest_area.domain.admin.DashBoardDAO;

@Service
public class DashBoardService {

	
	@Autowired
	SqlSession sqlSession;
	
	
	
	DashBoardDAO Ddao;
	
	public int total_acount(){
		Ddao = sqlSession.getMapper(DashBoardDAO.class);
		return Ddao.selectCnt();
	}
	
	public int today_acount(){
		Ddao = sqlSession.getMapper(DashBoardDAO.class);
		return Ddao.selectCnts();
	}
	
	public int total_post(){
		Ddao = sqlSession.getMapper(DashBoardDAO.class);
		return Ddao.select_totalPost();
	}
	
	public int today_post(){
		Ddao = sqlSession.getMapper(DashBoardDAO.class);
		return Ddao.select_todayPost();
	}
	
	public int today_report_post() {
		Ddao = sqlSession.getMapper(DashBoardDAO.class);
		return Ddao.select_todayReports_post();
	}
	
	public List<DTO> today_report_Popup(){
		Ddao = sqlSession.getMapper(DashBoardDAO.class);
		return Ddao.selectAll();
	}
	
	
	
	public int todayComent(){
		Ddao = sqlSession.getMapper(DashBoardDAO.class);
		return Ddao.select_todayComent();
	}
	
	public List<DTO> chip_Gas() {
		Ddao = sqlSession.getMapper(DashBoardDAO.class);
		return Ddao.chipGas();
	}
	
	
	public List<DTO> bestArea(){
		Ddao = sqlSession.getMapper(DashBoardDAO.class);
		return Ddao.bestArea();
	}
	
	
	public List<DTO> bestFood(){
		Ddao = sqlSession.getMapper(DashBoardDAO.class);
		return Ddao.bestFood();
	}
	
	public List<DTO> bestGas(){
		Ddao = sqlSession.getMapper(DashBoardDAO.class);
		return Ddao.bestGas();
	}
	
	public List<DTO> totalLike(){
		Ddao = sqlSession.getMapper(DashBoardDAO.class);
		return Ddao.totalLike();
	}
	
	public List<DTO> monthPostChart(){
		Ddao = sqlSession.getMapper(DashBoardDAO.class);
		return Ddao.monthPostChart();
	}
	
	
	public List<DTO> memberListChart(){
		Ddao = sqlSession.getMapper(DashBoardDAO.class);
		return Ddao.memberListChart();
	}
	
	
	public int select_ToDAY() {
		Ddao = sqlSession.getMapper(DashBoardDAO.class);
		return Ddao.select_todayNotice();
	}
	
}
