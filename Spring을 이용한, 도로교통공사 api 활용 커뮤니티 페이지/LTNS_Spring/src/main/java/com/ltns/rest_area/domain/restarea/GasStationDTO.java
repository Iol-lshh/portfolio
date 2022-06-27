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
public class GasStationDTO implements DTO {
	private String gs_code;
	private String gs_name;
	private String ra_code;	//휴게소 코드(외래키)
	private String ra_name;
	private String gs_company;	//정유소
	private String gs_diesel;
	private String gs_gasoline;
	private String gs_lpg;
	
	private int gs_like_cnt;
}
