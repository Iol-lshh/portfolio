package com.ltns.rest_area.domain.post;

import java.util.List;

import com.ltns.rest_area.domain.DAO;
import com.ltns.rest_area.domain.DTO;
import com.ltns.rest_area.domain.VO;

public interface LikeDAO extends DAO {
	
	//like 존재여부 확인
	@Override
	List<DTO> selectByVO(VO vo);
	
	//존재 안할 때, 넣어주기
	@Override
	int insertByVO(VO vo);
	
	//반환할 cnt 뽑아주기 
	@Override
	int selectCntByVO(VO vo);
	
	//삭제
	@Override
	int deleteByVO(VO vo);
}
