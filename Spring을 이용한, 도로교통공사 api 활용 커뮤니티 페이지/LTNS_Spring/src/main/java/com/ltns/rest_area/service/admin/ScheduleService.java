package com.ltns.rest_area.service.admin;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ltns.rest_area.domain.DAO;
import com.ltns.rest_area.domain.DTO;
import com.ltns.rest_area.domain.admin.ScheduleDAO;
import com.ltns.rest_area.domain.admin.ScheduleDTO;

@Service
public class ScheduleService {

	@Autowired
	private SqlSession sqlSession;
	DAO dao;
	
	
	
	//달력 서비스 view 
	public List<DTO> showSchedule()  {
		dao = sqlSession.getMapper(ScheduleDAO.class);
		return dao.selectAll();
	}
	
	//달력서비스 insert 
	public int addSchedule(DTO dto) {
		dao = sqlSession.getMapper(ScheduleDAO.class);
		return dao.insertByDTO(dto);
	}
	
	// delete 
	public int deleteSchedule(String str) {
		dao = sqlSession.getMapper(ScheduleDAO.class);
		return dao.deleteByString(str);
	}
	
	// update 
	
	public int updateSchedule(DTO dto) {
		dao = sqlSession.getMapper(ScheduleDAO.class);
		return dao.updateByDTO(dto);
	}

	
	

		
}
