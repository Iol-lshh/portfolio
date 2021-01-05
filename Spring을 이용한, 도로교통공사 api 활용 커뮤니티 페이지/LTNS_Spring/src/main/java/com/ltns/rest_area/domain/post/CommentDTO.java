package com.ltns.rest_area.domain.post;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentDTO {
	private int comment_id;
	private String comment_contents;
	private int um_uid;	//작성자 - 회원 uid(외래키)
	private String um_username;
	private Timestamp comment_regdate;	
	private int post_id;
	private String comment_reported;
}
