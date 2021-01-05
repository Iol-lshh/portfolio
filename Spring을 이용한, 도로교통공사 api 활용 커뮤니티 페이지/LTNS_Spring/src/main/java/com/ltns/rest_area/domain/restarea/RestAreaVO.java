package com.ltns.rest_area.domain.restarea;

import com.ltns.rest_area.domain.VO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RestAreaVO implements VO{
	private String routeName;
	private String destination;	//전체 'ALL'
	
	private String orderBy;		//경로순 'BY_PATH', 좋아요순 'BY_LIKE'
								//GS용	'BY_DIESEL' 'BY_GASOLINE' 'BY_LPG'
								//FM용	'BY_PRICE'
	private int fromRow;
	private int numOfRows;
	
	private String requestDataKind; //노선 요청 routeName, 방향 요청 destination => routeName을 담아서 요청
	
}
