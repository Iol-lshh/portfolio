package com.ltns.rest_area.domain.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AuthDTO {

	  private long um_uid;
	    private String authority;
	    private String message;
}
