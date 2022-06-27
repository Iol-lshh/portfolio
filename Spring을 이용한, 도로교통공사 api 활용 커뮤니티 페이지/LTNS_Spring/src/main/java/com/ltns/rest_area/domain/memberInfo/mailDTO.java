package com.ltns.rest_area.domain.memberInfo;

import com.ltns.rest_area.domain.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class mailDTO implements DTO   {
	
	private String username;
	private String title;
	private String textMail;

	
	
	
	
}
