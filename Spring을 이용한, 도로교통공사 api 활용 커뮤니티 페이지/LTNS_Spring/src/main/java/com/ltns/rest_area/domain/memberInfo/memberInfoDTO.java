package com.ltns.rest_area.domain.memberInfo;

import com.ltns.rest_area.domain.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class memberInfoDTO implements DTO   {
	
	private int UM_UID;
	private String UM_USERNAME;
	private String UM_NICKNAME;
	private String UM_REGDATE;
	private String user_regdate;
	private String UM_ENABLED;
	private String UM_PASSWORD;
	private int cnt;
	private String title;
	private String textMail;

	private String AUTHORITY;
	
	
	
	
}
