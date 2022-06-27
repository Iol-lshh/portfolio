package com.ltns.rest_area.domain.post;

import java.sql.Timestamp;

import com.ltns.rest_area.domain.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PostDTO implements DTO {
	private int post_id;
	private String post_title;
	private String post_contents;
	private int um_uid;	//작성자 - 회원 uid(외래키)
	private String um_username;
	private String post_regdate;
	private String ra_code;
	private String post_reported;
	
	private int post_like_cnt;
	private int count;
}
