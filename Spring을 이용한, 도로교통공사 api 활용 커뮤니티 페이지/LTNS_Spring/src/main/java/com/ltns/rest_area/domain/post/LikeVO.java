package com.ltns.rest_area.domain.post;

import com.ltns.rest_area.domain.VO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LikeVO implements VO {
	private int um_uid;
	private String kind;
	private String kind_id;
}
