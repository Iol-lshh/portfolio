
package com.ltns.rest_area.domain;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import com.ltns.rest_area.domain.admin.ScheduleDTO;
import com.ltns.rest_area.domain.restarea.RestAreaDTO;

public interface DAO {
	/*selectCnt : 갯수를 가져올 때 사용합니다*/
	int selectCnt();						//조건없이 갯수 전부 셀 때 사용하세요
	int selectCntByInt(int i);
	int selectCntByString(String str);
	int selectCntByVO(VO vo);
	int selectCntByObject(Object obj);		//이외의 다양한 자료형에 사용하세요
	
	/*select : 컬럼을 가져올 때 사용합니다*/
	List<DTO> selectAll();					//조건없이 전부 가져올 때 사용하세요
	List<DTO> selectByInt(int i);
	List<DTO> selectByString(String str);
	List<DTO> selectByDTO(DTO dto);
	List<DTO> selectByVO(VO vo);
	List<DTO> selectByObject(Object obj);	//이외의 다양한 자료형에 사용하세요
	
	
	/*insert*/
	int insertByDTO(DTO dto);
	int insertByVO(VO vo);
	int insertByObject(Object obj);			//다양한 자료형에 사용하세요
	int insertAllByDTOs(List<DTO> dtos);	//foreach문에 사용하세요
	
	//이단 제거완료

	
	/*update*/
	int updateByDTO(DTO dto);
	int updateByVO(VO vo);
	int updateByObject(Object obj);			//다양한 자료형에 사용하세요
	int updateAllByDTOs(List<DTO> dtos);	//foreach문에 사용하세요
	

	
	/*delete*/
	int deleteAll();						//조건없이 전부 지울 때 사용하세요
	int deleteByInt(int i);
	int deleteByString(String str);
	int deleteByVO(VO vo);
	int deleteByObject(Object obj);			//이외의 다양한 자료형에 사용하세요
	
	//vo를 사용합시다...
	int deleteByUid(int [] uids);
	public List<DTO> selectFromRow(
			@Param("from") int from, @Param("pagenationPage") int pagenationPage
	);
	
}

