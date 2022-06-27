  
package com.ltns.rest_area.domain.post;

import com.ltns.rest_area.domain.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Post implements Entity {

 	private int post_id;
  private String post_title;
 	private String post_contents;
	private int um_uid;
	private String um_username;
	private String post_regdate;
	private String ra_code;	//휴게소 정보
	private String post_reported;
	
}
