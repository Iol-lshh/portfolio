package com.ltns.rest_area.domain.post;

import java.util.List;

import com.ltns.rest_area.domain.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ViewDTO implements DTO {
	DTO raDTO;
	List<DTO> gsDTOs;
	List<DTO> fmDTOs;
	List<DTO> postDTOs;
}
