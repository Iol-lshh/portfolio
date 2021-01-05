package com.ltns.rest_area.domain.restarea;

import com.ltns.rest_area.domain.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RestAreaDTO implements DTO{
	private String ra_code;	//휴게소 코드
	private String ra_name;	//휴게소 이름
	
	private String ra_routeNo;	//노선 코드
	private String ra_routeName;	//노선명
	private String ra_destination;	//행선 구분
	private String ra_xValue; //x좌표
	private String ra_yValue; //y좌표

	private int ra_like_cnt; //좋아요 갯수.. BY_LIKE에서 갯수 불러올때 사용
}
