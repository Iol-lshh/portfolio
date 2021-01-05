package com.ltns.rest_area.domain.memberInfo;


import java.util.List;

import com.ltns.rest_area.domain.DAO;
import com.ltns.rest_area.domain.DTO;
import com.ltns.rest_area.postInfo.postInfoDTO;

public interface memberInfoDAO extends DAO{
	List<DTO> selectAlls();	//dao 상속받아서 써버리깅
	List<DTO> Allselect();	//dao 상속받아서 써버리깅
	List<memberInfoDTO> selectByUids(int i);
	int updateByAuth(memberInfoDTO dto);
	int insertByAuth(memberInfoDTO dto);
	
	
	
	
}
