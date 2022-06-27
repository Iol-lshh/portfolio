package com.ltns.rest_area.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ltns.rest_area.domain.AjaxList;
import com.ltns.rest_area.domain.AjaxResult;
import com.ltns.rest_area.domain.DTO;
import com.ltns.rest_area.domain.admin.AreaDataDTO;
import com.ltns.rest_area.domain.admin.ResultData;
import com.ltns.rest_area.service.admin.DashBoardService;

@RestController
@RequestMapping(value="/admin")
public class AdminDashBoardAjaxController {

	
	@Autowired
	DashBoardService Dservice;
	
	
	
	@PostMapping("total_Acount")
	public AjaxResult total_Acount() {
		int cnt = 0; 
		StringBuffer message = new StringBuffer();
		String status = "FAIL";
		
		cnt = Dservice.total_acount();
		
		if(cnt == 0) {
			message.append("트랜잭션 실패 ");
		}else {
			status ="OK";
		}
		
		AjaxResult result = new AjaxResult();
		result.setCount(cnt);
		result.setMessage(message.toString());
		result.setStatus(status);
		return result;
	}
	
	
	@PostMapping("today_acount")
	public AjaxResult today_acount() {
		int cnt = 0; 
		StringBuffer message = new StringBuffer();
		String status = "FAIL";
		
		cnt = Dservice.today_acount();
		
		if(cnt == 0) {
			message.append("트랜잭션 실패 ");
		}else {
			status ="OK";
		}
		
		AjaxResult result = new AjaxResult();
		result.setCount(cnt);
		result.setMessage(message.toString());
		result.setStatus(status);
		return result;
	}
	

	@PostMapping("today_post")
	public AjaxResult today_post() {
		int cnt = 0; 
		StringBuffer message = new StringBuffer();
		String status = "FAIL";
		
		cnt = Dservice.today_post();
		
		if(cnt == 0) {
			message.append("트랜잭션 실패 ");
		}else {
			status ="OK";
		}
		
		AjaxResult result = new AjaxResult();
		result.setCount(cnt);
		result.setMessage(message.toString());
		result.setStatus(status);
		return result;
	}
	

	@PostMapping("total_post")
	public AjaxResult total_post() {
		int cnt = 0; 
		StringBuffer message = new StringBuffer();
		String status = "FAIL";
		
		cnt = Dservice.total_post();
		
		if(cnt == 0) {
			message.append("트랜잭션 실패 ");
		}else {
			status ="OK";
		}
		
		AjaxResult result = new AjaxResult();
		result.setCount(cnt);
		result.setMessage(message.toString());
		result.setStatus(status);
		return result;
	}
	

	@PostMapping("today_report")
	public AjaxResult today_report() {
		int cnt = 0; 
		StringBuffer message = new StringBuffer();
		String status = "FAIL";
		
		cnt = Dservice.today_report_post();
		
		if(cnt == 0) {
			message.append("트랜잭션 실패 ");
		}else {
			status ="OK";
		}
		
		AjaxResult result = new AjaxResult();
		result.setCount(cnt);
		result.setMessage(message.toString());
		result.setStatus(status);
		return result;
	}
	
	//팝업
	@PostMapping("repostPopup")
	public AjaxList todays_report_post(){
		
		StringBuffer message = new StringBuffer();
		String status = "FAIL";
		List<DTO> list = null; 
		
		try {
			list = Dservice.today_report_Popup();
			
			if(list != null) {
				status ="OK";
			}		
			
		} catch (Exception e) {
			e.printStackTrace();
			message.append("[Error]" + e.getMessage());
		}
		
		AjaxList result = new AjaxList();
		
		result.setStatus(status);
		result.setMessage(message.toString());
		
		if(list != null) {
			result.setCount(list.size());
			result.setList(list);
		}
			
		
		return result;
	}
	
	
	
	@PostMapping("ChipGas")
	public AjaxList ChipGas() {
		
		String status ="FAIL";
		StringBuffer message = new StringBuffer();
		AjaxList result = new AjaxList();
		
		List<DTO> list = null;
		
		
		try 	
		{
				list = Dservice.chip_Gas();
			
				if(list != null) {
					status="OK";
				}
			
		} catch (Exception e) {
			message.append("트랜잭션오류 "+e.getMessage());
		}
		
		result.setList(list);
		result.setStatus(status);
		result.setCount(list.size());
		result.setMessage(message.toString());
	
		
		
		
		
		return result;
	}
	
	
	//베스트 휴게소
	@PostMapping("bestArea")
	public AjaxList bestArea() {
		
		StringBuffer message = new StringBuffer();
		String status = "FAIL";
		List<DTO> list = null; 
		
		try {
			list = Dservice.bestArea();
			
			if(list != null) {
				status ="OK";
			}		
			
		} catch (Exception e) {
			e.printStackTrace();
			message.append("[Error]" + e.getMessage());
		}
		
		AjaxList result = new AjaxList();
		
		result.setStatus(status);
		result.setMessage(message.toString());
		
		if(list != null) {
			result.setCount(list.size());
			result.setList(list);
		}
			
		
		return result;
		
	}
	
	
	//베스트 푸드 
	
	@PostMapping("bestFood")
	public AjaxList bestFood() {
		
		StringBuffer message = new StringBuffer();
		String status = "FAIL";
		List<DTO> list = null; 
		
		try {
			list = Dservice.bestFood();
			
			if(list != null) {
				status ="OK";
			}		
			
		} catch (Exception e) {
			e.printStackTrace();
			message.append("[Error]" + e.getMessage());
		}
		
		AjaxList result = new AjaxList();
		
		result.setStatus(status);
		result.setMessage(message.toString());
		
		if(list != null) {
			result.setCount(list.size());
			result.setList(list);
		}
			
		
		return result;
		
	}
	
	
	
	//베스트 주유소
	@PostMapping("bestGas")
	public AjaxList bestGas() {
		
		StringBuffer message = new StringBuffer();
		String status = "FAIL";
		List<DTO> list = null; 
		
		try {
			list = Dservice.bestGas();
			
			if(list != null) {
				status ="OK";
			}		
			
		} catch (Exception e) {
			e.printStackTrace();
			message.append("[Error]" + e.getMessage());
		}
		
		AjaxList result = new AjaxList();
		
		result.setStatus(status);
		result.setMessage(message.toString());
		
		if(list != null) {
			result.setCount(list.size());
			result.setList(list);
		}
			
		
		return result;
		
	}
	
	
	
	
	//추천수 총합
	@PostMapping("totalLike")
	public AjaxList totalLike() {
		
		StringBuffer message = new StringBuffer();
		String status = "FAIL";
		List<DTO> list = null; 
		
		try {
			list = Dservice.totalLike();
			
			if(list != null) {
				status ="OK";
			}		
			
		} catch (Exception e) {
			e.printStackTrace();
			message.append("[Error]" + e.getMessage());
		}
		
		AjaxList result = new AjaxList();
		
		result.setStatus(status);
		result.setMessage(message.toString());
		
		if(list != null) {
			result.setCount(list.size());
			result.setList(list);
		}
			
		
		return result;
		
	}
	
	

	//일일 그래프
	@PostMapping("monthPostChart")
	public AjaxList monthPostChart() {
		
		StringBuffer message = new StringBuffer();
		String status = "FAIL";
		List<DTO> list = null; 
		
		try {
			list = Dservice.monthPostChart();
			
			if(list != null) {
				status ="OK";
			}		
			
		} catch (Exception e) {
			e.printStackTrace();
			message.append("[Error]" + e.getMessage());
		}
		
		AjaxList result = new AjaxList();
		
		result.setStatus(status);
		result.setMessage(message.toString());
		
		if(list != null) {
			result.setCount(list.size());
			result.setList(list);
		}
			
		
		return result;
		
	}
	
	

	//일일 그래프 회원 
	@PostMapping("memberListChart")
	public AjaxList memberListChart() {
		
		StringBuffer message = new StringBuffer();
		String status = "FAIL";
		List<DTO> list = null; 
		
		try {
			list = Dservice.memberListChart();
			
			if(list != null) {
				status ="OK";
			}		
			
		} catch (Exception e) {
			e.printStackTrace();
			message.append("[Error]" + e.getMessage());
		}
		
		AjaxList result = new AjaxList();
		
		result.setStatus(status);
		result.setMessage(message.toString());
		
		if(list != null) {
			result.setCount(list.size());
			result.setList(list);
		}
			
		
		return result;
		
	}
	
	
	
	
	
	@PostMapping("alram")
	public AjaxList alram() {
		int cnt = 0;
		StringBuffer message = new StringBuffer();
		String status = "FAIL";
		AjaxList result = new AjaxList();
		
		cnt = Dservice.select_ToDAY();
		try {
			if(cnt != 0) {
				status = "OK";
			}
		} catch (Exception e) {
			message.append(e.getMessage()+ "트랜잭션 에러");
		}
		
		result.setCount(cnt);
		result.setStatus(status);
		result.setMessage(message.toString());
		return result;
		
	}
	
	
	
	
	
}
