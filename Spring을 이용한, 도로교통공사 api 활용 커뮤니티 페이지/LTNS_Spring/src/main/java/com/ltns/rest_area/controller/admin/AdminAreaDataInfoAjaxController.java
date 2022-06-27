package com.ltns.rest_area.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ltns.rest_area.domain.AjaxList;
import com.ltns.rest_area.domain.AjaxResult;
import com.ltns.rest_area.domain.DTO;
import com.ltns.rest_area.domain.admin.AreaDataDTO;
import com.ltns.rest_area.domain.admin.ResultData;
import com.ltns.rest_area.domain.admin.ScheduleDTO;
import com.ltns.rest_area.service.admin.AreaDataService;

@RestController
@RequestMapping(value="/admin")
public class AdminAreaDataInfoAjaxController {


	@Autowired
	AreaDataService Aservice;
	
	// 휴게소
	@GetMapping("areaInfo/{pageNo}/{pagenationPage}")
	public AjaxList AreaInfo(@PathVariable int pageNo, @PathVariable int pagenationPage) {
		

		StringBuffer message = new StringBuffer();
		String status ="FAIL";
		List<DTO> list = null;
		int  totalCnt = 0;
		int totalPage = 0;
		int WritePages = 10; 
		try {
			
			totalCnt = Aservice.AreaTotal();
			totalPage = (int)Math.ceil(totalCnt / (double)pagenationPage);
			
			int from = ( pageNo - 1 ) * pagenationPage +1;
			list = Aservice.selectFromRow(from, pagenationPage);
			
			if(list == null) {
				message.append("[List data is not defind]");
			} else {
				status = "OK";
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
		
		result.setPageNo(pageNo);
		result.setTotalPage(totalPage);
		result.setWritePages(WritePages);
		result.setPagenationPage(pagenationPage);
		result.setTotalCnt(totalCnt);
		return result;
		
		
	}
	

	
	//주유소 
	@GetMapping("Gasstion/{pageNo}/{pagenationPage}")
	public AjaxList Gasstion(@PathVariable int pageNo, @PathVariable int pagenationPage) {
		

		StringBuffer message = new StringBuffer();
		String status ="FAIL";
		List<DTO> list = null;
		int  totalCnt = 0;
		int totalPage = 0;
		int WritePages = 10; 
		try {
			
			totalCnt = Aservice.GasTotal();
			totalPage = (int)Math.ceil(totalCnt / (double)pagenationPage);
			
			int from = ( pageNo - 1 ) * pagenationPage +1;
			list = Aservice.selectFromGasRow(from, pagenationPage);
			
			if(list == null) {
				message.append("[List data is not defind]");
			} else {
				status = "OK";
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
		
		result.setPageNo(pageNo);
		result.setTotalPage(totalPage);
		result.setWritePages(WritePages);
		result.setPagenationPage(pagenationPage);
		result.setTotalCnt(totalCnt);
		return result;
		
		
	}
	
	
	
	//주유소 
		@GetMapping("FoodMenu/{pageNo}/{pagenationPage}")
		public AjaxList FoodMenu(@PathVariable int pageNo, @PathVariable int pagenationPage) {
			

			StringBuffer message = new StringBuffer();
			String status ="FAIL";
			List<DTO> list = null;
			int  totalCnt = 0;
			int totalPage = 0;
			int WritePages = 10; 
			try {
				
				totalCnt = Aservice.MenuTotal();
				totalPage = (int)Math.ceil(totalCnt / (double)pagenationPage);
				
				int from = ( pageNo - 1 ) * pagenationPage +1;
				list = Aservice.selectFromMenuRow(from, pagenationPage);
				
				if(list == null) {
					message.append("[List data is not defind]");
				} else {
					status = "OK";
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
			
			result.setPageNo(pageNo);
			result.setTotalPage(totalPage);
			result.setWritePages(WritePages);
			result.setPagenationPage(pagenationPage);
			result.setTotalCnt(totalCnt);
			return result;
			
			
		}
		
		
		//
		

		//Seach
			
		@PostMapping("areaInFoSeach")
		public AjaxList areaInFoSeach(@RequestBody AreaDataDTO dto) {

			StringBuffer message = new StringBuffer();
			String status ="FAIL";
			List<DTO> list = null;
			
			
			if(dto.getTarget().equals("values_1")) {
				list = Aservice.seachArea(dto.getContent()+"%");
				try {
					if(list != null ) {
						status = "OK";
					}
				} catch (Exception e) {
					message.append(e.getMessage() + "트랜잭션 오류");
				}
				
			}else if(dto.getTarget().equals("values_2")) {
				
				list = Aservice.seachGas(dto.getContent()+"%");
				try {
					if(list != null ) {
						status = "OK";
					}
				} catch (Exception e) {
					message.append(e.getMessage() + "트랜잭션 오류");
				}
			}else {
				list = Aservice.seachFood(dto.getContent()+"%");
				try {
					if(list != null ) {
						status = "OK";
					}
				} catch (Exception e) {
					message.append(e.getMessage() + "트랜잭션 오류");
				}
			}
			AjaxList result = new AjaxList();
			result.setMessage(message.toString());
			result.setStatus(status);
			result.setList(list);
			result.setCount(list.size());
			return result;
		}
		
		
	
		
	@PostMapping("/areaInfoPopUp/XYDATAREAD")
	public AjaxList XYDATAREAD(@RequestBody AreaDataDTO dto ) {
		StringBuffer message = new StringBuffer();
		String status ="FAIL";
		List<DTO> list = null;
		
		try {
			list = Aservice.xyData(dto.getNames());
			
			if(list != null) {
				status="OK";
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		AjaxList result = new AjaxList();
		result.setMessage(message.toString());
		result.setStatus(status);
		result.setList(list);
		result.setCount(list.size());
		return result;

	}
	
}
