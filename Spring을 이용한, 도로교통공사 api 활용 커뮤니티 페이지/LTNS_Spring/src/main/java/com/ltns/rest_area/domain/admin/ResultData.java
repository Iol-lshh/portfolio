package com.ltns.rest_area.domain.admin;


import java.util.List;

import com.ltns.rest_area.domain.DTO;
import com.ltns.rest_area.postInfo.postInfoDTO;

import lombok.Data;

@Data
public class ResultData {
	int count;	// 데이터 개수
	int totalCounts;
	private String resultData;
	String status;	// 처리결과
	String message;	// 결과 메세지
	List<postInfoDTO> list;

}
