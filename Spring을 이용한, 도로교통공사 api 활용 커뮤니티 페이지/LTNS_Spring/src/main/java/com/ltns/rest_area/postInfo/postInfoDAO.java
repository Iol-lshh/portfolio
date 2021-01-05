package com.ltns.rest_area.postInfo;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ltns.rest_area.domain.DAO;
import com.ltns.rest_area.domain.DTO;

public interface postInfoDAO extends DAO{
	List<DTO> selectAlls();	//dao 상속받아서 써버리깅
	List<DTO> Allselect();	//dao 상속받아서 써버리깅
	List<postInfoDTO> selectByInts(int id);
	int allselect();
	
	int insertByDTO_Notice(postInfoDTO dto);
	
	
	
	int updateReport(int post_id);
	
	List<postInfoDTO> FileAllselect();
	int updateByDTOS(postInfoDTO dto);
	int deleteByInts(int i);	
	public List<DTO> select_Rows(
			@Param("from") int from, @Param("pagenationPage") int pagenationPage
	);
	
}
