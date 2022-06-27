package com.ltns.rest_area.service.admin;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ltns.rest_area.domain.DAO;
import com.ltns.rest_area.domain.DTO;
import com.ltns.rest_area.domain.admin.AreaDataDAO;
import com.ltns.rest_area.domain.admin.AreaDataDTO;

@Service
public class AreaDataService {

	
	
	@Autowired
	SqlSession sqlSession;
	
	DAO dao;
	AreaDataDAO vo;
	
	
	public List<DTO> selectFromRow(int from,int pagenationPage){
		dao = sqlSession.getMapper(AreaDataDAO.class);
		return dao.selectFromRow(from, pagenationPage);
	}
	
	public List<DTO> selectFromGasRow(int from,int pagenationPage){
		vo = sqlSession.getMapper(AreaDataDAO.class);
		return vo.selectFromGasRow(from, pagenationPage);
	}
	
	public List<DTO> selectFromMenuRow(int from,int pagenationPage){
		vo = sqlSession.getMapper(AreaDataDAO.class);
		return vo.selectFromMenuRow(from, pagenationPage);
	}
	
	public List<DTO> seachFood(String foodName){
		vo = sqlSession.getMapper(AreaDataDAO.class);
		return vo.SerachFoodMenu(foodName);
	}
	
	public List<DTO> seachArea(String RName){
		vo = sqlSession.getMapper(AreaDataDAO.class);
		return vo.ReastAreaName(RName);
	}
	
	public List<DTO> seachGas(String GName){
		vo = sqlSession.getMapper(AreaDataDAO.class);
		return vo.GasstionName(GName);
	}
	
	
	
	public int AreaTotal() {
		dao = sqlSession.getMapper(AreaDataDAO.class);
		return dao.selectCnt();
	}
	
	public int GasTotal() {
		vo = sqlSession.getMapper(AreaDataDAO.class);
		return vo.SelectTotalGas();
	}
	
	public int MenuTotal() {
		vo = sqlSession.getMapper(AreaDataDAO.class);
		return vo.SelectTotalMenu();
	}
	
	
	public List<DTO> xyData(String names){
		vo = sqlSession.getMapper(AreaDataDAO.class);
		return vo.SeachXY(names);
	}
	

	
}
