package com.ltns.rest_area.domain.admin;

import java.util.List;

import com.ltns.rest_area.domain.DAO;
import com.ltns.rest_area.domain.DTO;

public interface DashBoardDAO extends DAO{
	int selectCnts();	
	int select_totalPost();
	int select_todayPost();
	int select_todayReports_post();

	int select_todayComent();

	
	int select_todayNotice();
	
	List<DTO> bestArea();
	List<DTO> bestFood();
	List<DTO> bestGas();
	List<DTO> totalLike();
	List<DTO> memberListChart();
	List<DTO> monthPostChart();
	List<DTO> chipGas();
	
	
}
