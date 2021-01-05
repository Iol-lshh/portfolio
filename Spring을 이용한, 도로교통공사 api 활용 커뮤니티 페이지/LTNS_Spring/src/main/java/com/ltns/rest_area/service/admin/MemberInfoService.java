package com.ltns.rest_area.service.admin;


import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ltns.rest_area.domain.DAO;
import com.ltns.rest_area.domain.DTO;
import com.ltns.rest_area.domain.memberInfo.memberInfoDAO;
import com.ltns.rest_area.domain.memberInfo.memberInfoDTO;
import com.ltns.rest_area.postInfo.postInfoDTO;

@Service
public class MemberInfoService {
	
	
	DAO dao;
	memberInfoDAO memberInfoDAO;
	
	@Autowired
	private SqlSession sqlSession;

	//list 
	public List<DTO> list(int from,int pagenationPage) {
		dao = sqlSession.getMapper(memberInfoDAO.class);
		return dao.selectFromRow(from, pagenationPage);
	}
	
	
	//모든 회원수 
	public int countAll() {
		dao = sqlSession.getMapper(memberInfoDAO.class);
		return dao.selectCnt();
	}
	
	// 아이디 검색
	public List<DTO> seachId(String id) {
		dao = sqlSession.getMapper(memberInfoDAO.class);
		return dao.selectByString(id);
	}
	
	
	// 닉네임 검색
	public List<DTO> seachNickname(String nickname) {
		dao = sqlSession.getMapper(memberInfoDAO.class);
		return dao.selectByString(nickname);
	}
	
	//댓글을 가장많이단 유저 
	public List<DTO> serachComents(){
		dao = sqlSession.getMapper(memberInfoDAO.class);
		return dao.selectAll();
	}
	
	// 게시글을 가장 많이 쓴 유저 
	public List<DTO> serachPost(){
		memberInfoDAO = sqlSession.getMapper(memberInfoDAO.class);
		return memberInfoDAO.Allselect();
	}
	
	// uid serch
	
	public List<memberInfoDTO> uidSerch(int UM_UID){
		memberInfoDAO = sqlSession.getMapper(memberInfoDAO.class);
		return memberInfoDAO.selectByUids(UM_UID);
		
	}
	
	// update RoleMember
	
	public int updateGrade(memberInfoDTO list){
		memberInfoDAO = sqlSession.getMapper(memberInfoDAO.class);
		return memberInfoDAO.updateByAuth(list);
	}
	
	
	
	
	
	
}
