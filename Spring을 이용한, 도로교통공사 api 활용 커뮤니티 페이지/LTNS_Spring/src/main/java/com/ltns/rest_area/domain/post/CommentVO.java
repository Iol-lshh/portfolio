package com.ltns.rest_area.domain.post;

import com.ltns.rest_area.domain.VO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentVO implements VO {

	private String orderBy;		
	
	private int fromRow;	//가져올 시작 row
	private int numOfRows;	//한 페이지에 리스트할 dto 갯수
	
	//정보
	private int comment_id;
	private String comment_contents;
	private int um_uid;
	private String um_username;
	private String um_regdate;
	private int post_id;	//포스트 아이디
	private String comment_reported;
}
