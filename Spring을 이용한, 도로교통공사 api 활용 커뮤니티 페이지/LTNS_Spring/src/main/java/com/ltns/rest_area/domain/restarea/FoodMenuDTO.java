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
public class FoodMenuDTO implements DTO {
	private String fm_id;	//고유 음식 아이디(기본키)
	private String fm_code;	//음식 코드
	private String ra_code;	//휴게소 코드(외래키)
	private String ra_name;
	private String fm_name;	//음식 이름
	private int fm_price; //음식 가격
	private String fm_material;	//음식 재료
	private String fm_etc;	//음식 상세 내역
	
	private int fm_like_cnt;
}
