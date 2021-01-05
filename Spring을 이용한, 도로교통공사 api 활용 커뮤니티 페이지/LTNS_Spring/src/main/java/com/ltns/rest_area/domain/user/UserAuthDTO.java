package com.ltns.rest_area.domain.user;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import com.ltns.rest_area.domain.DTO;

import lombok.Data;

@Data
public class UserAuthDTO implements DTO{
	
	long uid;
	String username;
	String password;
	String nickname;
	char enabled;
	String regdate;
	String authority;
	String addAuthority;
	String prevAuthority;
	String nextAuthority;
	String message;
	
	public void setRegdate(Timestamp regdate) {
		this.regdate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(regdate);
	}
	
}
