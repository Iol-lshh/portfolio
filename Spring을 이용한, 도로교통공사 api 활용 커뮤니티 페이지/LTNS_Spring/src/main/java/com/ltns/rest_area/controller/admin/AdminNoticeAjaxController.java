package com.ltns.rest_area.controller.admin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ltns.rest_area.domain.AjaxList;
import com.ltns.rest_area.domain.DTO;
import com.ltns.rest_area.domain.admin.FIleDatas;
import com.ltns.rest_area.domain.admin.ResultData;
import com.ltns.rest_area.postInfo.postInfoDTO;
import com.ltns.rest_area.service.admin.NoticeService;

@RestController
@RequestMapping(value="/admin")
public class AdminNoticeAjaxController {

	
	
	@Autowired
	NoticeService notice_Service;
	
	@GetMapping("/notice/{pageNo}/{pagenationPage}")
	@ResponseBody
	public AjaxList noticeList(
			@PathVariable int pageNo,	//현재 페이지
			@PathVariable int pagenationPage) {
		
		
		StringBuffer message = new StringBuffer();
		String status ="FAIL";
		List<DTO> list = null;
		int  totalCnt = 0;
		int totalPage = 0;
		int WritePages = 10; 
		try {
			
			totalCnt = notice_Service.selectTotal();
			totalPage = (int)Math.ceil(totalCnt / (double)pagenationPage);
			
			int from = ( pageNo - 1 ) * pagenationPage +1;
			list = notice_Service.list(from, pagenationPage);
			
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
	
	
	
	//뷰
	@GetMapping("/notice/{id}")
	public ResultData noticeView(@PathVariable int id , HttpServletRequest request) {
		
		String line ="";
		List<postInfoDTO> list = null; 
		StringBuffer message = new StringBuffer();
		String status ="FAIL";
		list = notice_Service.noticeView(id);
		String filename ="";
		for (postInfoDTO postInfoDTO : list) {
		
			filename = postInfoDTO.getNotice_content();
		}
		
		try {
		
			
			if(list != null) {
				status ="OK";
			}
		} catch (Exception e) {
			message.append(e.getMessage() + "트랜잭션 오류");
		}
		
		ResultData result = new ResultData();
		
		result.setMessage(message.toString());
		result.setStatus(status);
		result.setList(list);
		
		String path =
				request.getSession().getServletContext().getRealPath("/resource/data/");
	
		String directory = path + filename + ".txt";
		
		String resultData ="";
		
		
		try {
			File f = new File(directory);
			FileReader fr = new FileReader(f);
			BufferedReader bufReader = new BufferedReader(fr);
			
		while((line = bufReader.readLine()) != null) {
			resultData += line;
		}
		   bufReader.close();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			
		}
		
		
		
		
		result.setResultData(resultData);
		
		
		
		
		
		
		return result;
	}
	
	//글 등록
	@PostMapping("insertNotice")
	public AjaxList insertNotice(@RequestBody postInfoDTO dto,  HttpServletRequest request ) {
		int totalCnt = 0; 
		StringBuffer message = new StringBuffer();
		String status ="FAIL";
		totalCnt = notice_Service.noticeInsert(dto);
		try {
			
			if(totalCnt != 0) {
				status ="OK";
			}
		} catch (Exception e) {
			message.append(e.getMessage() + "트랜잭션 오류");
		}
		
		AjaxList result = new AjaxList();
		
		result.setMessage(message.toString());
		result.setStatus(status);
		System.out.println(result.getStatus());
		result.setTotalCnt(totalCnt);
		
		String path =
				request.getSession().getServletContext().getRealPath("/resource/data/");

		FIleDatas f = new FIleDatas(dto.getContent(), path,  dto.getDataSet());
		
		
		
		
		
		return result;
	}
	
	//글 수정
	@PostMapping("updateNotice")
	public ResultData updateNotice(@RequestBody postInfoDTO dto,  HttpServletRequest request ) {
		int totalCnt = 0; 
		StringBuffer message = new StringBuffer();
		String status ="FAIL";
		String line= "";
		String filepath ="";
		
		List<postInfoDTO> list = notice_Service.allSelect();
		totalCnt = notice_Service.noticeUpdate(dto);
		
		try {
		
			for (postInfoDTO postInfoDTO : list) {
				filepath = postInfoDTO.getNotice_content();
			}
			
			
			
			if(totalCnt != 0) {
				status ="OK";
			}
			
			
		} catch (Exception e) {
			message.append(e.getMessage() + "트랜잭션 오류");
		}
		
		ResultData result = new ResultData();
		result.setMessage(message.toString());
		result.setStatus(status);
		
		
		
		//파일 읽기

		String path =
				request.getSession().getServletContext().getRealPath("/resource/data/");
	
		String directory = path + filepath + ".txt";
		String resultData ="";
		
		
		try {
			File f = new File(directory);
			FileReader fr = new FileReader(f);
			BufferedReader bufReader = new BufferedReader(fr);
			
			while((line = bufReader.readLine()) != null) {
			resultData += line;
		}
		 	
		   bufReader.close();
		   
		   // 파일 내용 수정하기 
		    BufferedWriter bufw = new BufferedWriter(new FileWriter(f));
		    bufw.write(dto.getChangeD());
		    bufw.flush();
		    bufw.close();
		    		
		   
		
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			
		}
		
		result.setResultData(dto.getChangeD());
		return result;
	}
	
	
	//글삭제
	@DeleteMapping("DeleteNotice")
	public ResultData deleteNotice(@RequestBody postInfoDTO dto,  HttpServletRequest request ) {
		int cnt = 0; 
		StringBuffer message = new StringBuffer();
		String status ="FAIL";
		String filepath = "";
		List<postInfoDTO> list = notice_Service.allSelect();
		cnt = notice_Service.NoticeDelete(dto.getN_id());
		try {
			
			if(cnt != 0) {
				status ="OK";
			}
			
			for (postInfoDTO postInfoDTO : list) {
				filepath = postInfoDTO.getNotice_content();
			}
			
			
			String path =
					request.getSession().getServletContext().getRealPath("/resource/data/");
		
			String directory = path + filepath + ".txt";
			File f = new File(directory);
			f.delete();
			
			
		} catch (Exception e) {
			message.append(e.getMessage() + " 트랜잭션 오류");
		}
		
		ResultData result = new ResultData();
		result.setMessage(message.toString());
		result.setStatus(status);
		
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
