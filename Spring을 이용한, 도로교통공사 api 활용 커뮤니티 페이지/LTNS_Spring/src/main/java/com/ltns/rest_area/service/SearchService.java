package com.ltns.rest_area.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ltns.rest_area.domain.DAO;
import com.ltns.rest_area.domain.DTO;
import com.ltns.rest_area.domain.restarea.FoodMenuDAO;
import com.ltns.rest_area.domain.restarea.GasStationDAO;
import com.ltns.rest_area.domain.restarea.RestAreaVO;
import com.ltns.rest_area.domain.restarea.RestAreaDAO;

@Service
public class SearchService {
	
	DAO dao;

	@Autowired
	private SqlSession sqlSession;

	public int raCount() {
		dao=sqlSession.getMapper(RestAreaDAO.class);
		return dao.selectCnt();
	}

	public List<DTO> selectSomeRaDTOs(String routeName, String destination, String orderBy, int fromRow, int numOfRows) {
		dao=sqlSession.getMapper(RestAreaDAO.class);
		//orderBy : default 는 암것도 없기~~ ra_Code 순, 
		return dao.selectByVO(new RestAreaVO().builder().routeName(routeName).destination(destination).orderBy(orderBy).fromRow(fromRow).numOfRows(numOfRows).build());
	}

	public int gsCount() {
		dao=sqlSession.getMapper(GasStationDAO.class);
		return dao.selectCnt();
	}

	public List<DTO> selectSomeGsDTOs(String routeName, String destination, String orderBy, int fromRow, int numOfRows) {
		dao=sqlSession.getMapper(GasStationDAO.class);
		//orderBy : default 는 암것도 없기~~ ra_Code 순, 
		return dao.selectByVO(new RestAreaVO().builder().routeName(routeName).destination(destination).orderBy(orderBy).fromRow(fromRow).numOfRows(numOfRows).build());
	}

	public int fmCount() {
		dao=sqlSession.getMapper(FoodMenuDAO.class);
		return dao.selectCnt();
	}

	public List<DTO> selectSomeFmDTOs(String routeName, String destination, String orderBy, int fromRow, int numOfRows) {
		dao=sqlSession.getMapper(FoodMenuDAO.class);
		//orderBy : default 는 암것도 없기~~ ra_Code 순, 
		return dao.selectByVO(new RestAreaVO().builder().routeName(routeName).destination(destination).orderBy(orderBy).fromRow(fromRow).numOfRows(numOfRows).build());
	}

	public List<DTO> requestComboList(RestAreaVO dto) {
		dao=sqlSession.getMapper(RestAreaDAO.class);
		return dao.selectByObject(dto);
	}


	
}
