
package com.ltns.rest_area.domain.admin;
import com.ltns.rest_area.domain.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleDTO implements DTO {
	
	private String add_subject;
	private String subject;
	private String startDate; 
	private String endDate;



}
