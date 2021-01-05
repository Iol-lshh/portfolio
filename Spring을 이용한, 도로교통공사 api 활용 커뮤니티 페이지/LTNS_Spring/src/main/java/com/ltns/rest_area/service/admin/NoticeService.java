package com.ltns.rest_area.service.admin;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ltns.rest_area.domain.DTO;
import com.ltns.rest_area.postInfo.postInfoDAO;
import com.ltns.rest_area.postInfo.postInfoDTO;

@Service
public class NoticeService {
	
	

	
	@Autowired
	private SqlSession sqlSession;
	postInfoDAO DAO;
	
	//list 
	public List<DTO> list(int from,int pagenationPage) {
		DAO = sqlSession.getMapper(postInfoDAO.class);
		return DAO.select_Rows(from, pagenationPage);
	}
	
	public int selectTotal() {
		DAO = sqlSession.getMapper(postInfoDAO.class);
		return DAO.allselect();
	}
	
	public List<postInfoDTO> noticeView(int id){
		DAO = sqlSession.getMapper(postInfoDAO.class);
		return DAO.selectByInts(id);
	}
	
	public int noticeInsert(postInfoDTO dto){
		DAO = sqlSession.getMapper(postInfoDAO.class);
		return DAO.insertByDTO_Notice(dto);
	}
	
	public int noticeUpdate(postInfoDTO dto){
		DAO = sqlSession.getMapper(postInfoDAO.class);
		return DAO.updateByDTOS(dto);
	}
	
	public List<postInfoDTO> allSelect(){
		DAO = sqlSession.getMapper(postInfoDAO.class);
		return DAO.FileAllselect();
	}
	
	public int NoticeDelete (int notice_id) {
		DAO = sqlSession.getMapper(postInfoDAO.class);
		return DAO.deleteByInts(notice_id);
	}
	
}
