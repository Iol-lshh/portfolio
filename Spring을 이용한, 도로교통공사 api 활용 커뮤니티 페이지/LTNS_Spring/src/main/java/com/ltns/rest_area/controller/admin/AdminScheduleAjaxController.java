
package com.ltns.rest_area.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ltns.rest_area.domain.AjaxResult;
import com.ltns.rest_area.domain.admin.ScheduleDTO;
import com.ltns.rest_area.service.admin.ScheduleService;

@RestController
@RequestMapping(value="/admin")
public class AdminScheduleAjaxController {
	
	@Autowired
	ScheduleService service;

	
	//스케줄 인설트 
	@PostMapping("/schdule/schedule")
	public AjaxResult addSchedule(@RequestBody ScheduleDTO dto , Model m) {
		
		int cnt = 0 ;
		String status = "FAIL";
		StringBuffer message = new StringBuffer();
		cnt = service.addSchedule(dto);
		AjaxResult result = new AjaxResult();
		if(cnt != 0) {
			status = "OK";
			message.append("성공");
		}else {
			message.append("실패");
		}
		
		result.setCount(cnt);
		result.setStatus(status);
		result.setMessage(message.toString());
		
		return result;
		
	}
	
	
	//스케줄 업데이트 
	@PutMapping("/schdule/schedule")
	public AjaxResult updateSchedules( @RequestBody ScheduleDTO dto) {
			
		int cnt = 0 ;
		String status = "FAIL";
		StringBuffer message = new StringBuffer();
		cnt =	service.updateSchedule(dto);
		AjaxResult result = new AjaxResult();
		
		if(cnt != 0) {
			status = "OK";
			message.append("성공");
		}else {
			message.append("실패");
		}
		
		result.setCount(cnt);
		result.setStatus(status);
		result.setMessage(message.toString());
		
		
		return result;
	
		
	}
	
	
	//일정 삭제 
	@DeleteMapping("/schdule/schedule")
	public AjaxResult deleteSchedule(@RequestBody ScheduleDTO dto) {
		
		int cnt = 0 ;
		String status = "FAIL";
		StringBuffer message = new StringBuffer();
		cnt = service.deleteSchedule(dto.getSubject());
		AjaxResult result = new AjaxResult();
		
		if(cnt != 0) {
			status = "OK";
			message.append("성공");
		}else {
			message.append("실패");
		}
		
		result.setCount(cnt);
		result.setStatus(status);
		result.setMessage(message.toString());
		
		
		return result;
	}
}