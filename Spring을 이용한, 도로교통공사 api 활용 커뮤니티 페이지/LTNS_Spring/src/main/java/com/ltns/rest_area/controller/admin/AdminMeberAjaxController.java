
package com.ltns.rest_area.controller.admin;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.apache.ibatis.executor.ReuseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ltns.rest_area.domain.AjaxList;
import com.ltns.rest_area.domain.AjaxResult;
import com.ltns.rest_area.domain.DTO;
import com.ltns.rest_area.domain.memberInfo.mailDTO;
import com.ltns.rest_area.domain.memberInfo.memberInfoDTO;
import com.ltns.rest_area.domain.post.PostDTO;
import com.ltns.rest_area.postInfo.postInfoDTO;
import com.ltns.rest_area.service.admin.MemberInfoService;

@RestController
@RequestMapping(value="/admin")
public class AdminMeberAjaxController {


	int pageNo = 0;
	int pagenationPage = 0;
	
	
	@Autowired
	MemberInfoService member_service;
	
	@Autowired
	JavaMailSender mailSender;
	
	
	// 멤버 정보 
	@GetMapping("/memberInfo/{pageNo}/{pagenationPage}")
	@ResponseBody
	public AjaxList list(
			@PathVariable int pageNo,	//현재 페이지
			@PathVariable int pagenationPage	) {
	
		
		
		
		StringBuffer message = new StringBuffer();
		String status ="FAIL";
		
		
		int totalPage = 0;	//총 페이지 갯수
		int WritePages = 10; //총 리스트 수
		int totalCnt = 0; //총 회원수 
		List<DTO> list = null;
		
		
		try {

			totalCnt = member_service.countAll();
			totalPage = (int)Math.ceil(totalCnt / (double)pagenationPage);
			
			int from = ( pageNo - 1 ) * pagenationPage +1;
			list = member_service.list(from, pagenationPage);
			
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
		
	} // end page
	
	
	// 검색 
	@DeleteMapping("SEACH")
	@ResponseBody
	public AjaxList Serach(@RequestBody Map<String, String> data) {
	
			String id = "";
			String nickname = "";
			String contents ="";
			
		for (Entry<String, String> datas : data.entrySet()) {
			if(datas.getValue().equals("id")) {
				id = datas.getValue();
			}else if(datas.getKey().equals("text")) {
				contents = datas.getValue();
			}else if(datas.getValue().equals("nickName")) {
				nickname = datas.getValue();
			}
		}
		StringBuffer message = new StringBuffer();
		String status ="FAIL";
		
		
		List<DTO> list = null;
		
		try {

			if(id.equals(id)) {
				list = member_service.seachId(contents);
			}else if(nickname.equals("nickName")){
				list = member_service.seachNickname(contents); 
			}
			
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
		
	
		return result;
		
	} // end page
	
	//가장많은 댓글을 단 회원 
	
	@PostMapping("TopComent")
	public AjaxResult TopComent() {
		
		StringBuffer message = new StringBuffer();
		String status ="FAIL";
		List<DTO> list = null;
		
		try {

			list = member_service.serachComents();
			
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
		
		return result;
		
	}
	
	
	//가장 많은글을 쓴유저 
	@GetMapping("TopPostUser")
	public AjaxResult TopPostUser() {
		StringBuffer message = new StringBuffer();
		String status= "FAIL";
		
		List<DTO> list = null;
		try {
			list = member_service.serachPost();
			if(list == null) {
				message.append("[List data is not defind]");
			} else {
				status = "OK";
			}
			
		} catch (Exception e) {
			message.append("트랜잭션 오류" + e.getMessage());
		}
	
		AjaxList result = new AjaxList();
		result.setMessage(message.toString());
		result.setStatus(status);
		
		
		if(list != null) {
			result.setCount(list.size());
			result.setList(list);
		}
		
		
		return result;
	}
	
	
	
	
	
	
	@PostMapping("mailSendAjax")
	public AjaxResult mailSend(@RequestBody mailDTO dto) {
		
		MimeMessage message = mailSender.createMimeMessage();
		AjaxResult result = new AjaxResult();
		String status = "FAIL";
		String titles = "";
		if(dto.getTitle().equals("intro")) {
			titles = "안녕하세요 LTNS 팀입니다.";
		}else {
			titles = "게시물의 신고가 많이 접수되어 있습니다.";
		}
		
		
		
		try {
			message.setSubject(titles);// 제목
			message.setRecipient(RecipientType.TO , new InternetAddress(dto.getUsername())); // 수신자
			message.setText(dto.getTextMail());
			
			mailSender.send(message);
			status = "OK";
			
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		result.setStatus(status);
		
		
		return result;
		
	}

	
	//권한 변경
	@GetMapping("Change_Grage/{checkData}")
	public AjaxList Change_Grage(@PathVariable int checkData) {
		
		AjaxList result = new AjaxList();
		String status = "FAIL";
		int cnt = 0;
		List<memberInfoDTO> list = null;
		list = member_service.uidSerch(checkData);
		System.out.println(checkData);
		try {
			if( list != null ) {
				for (memberInfoDTO memberInfoDTO : list) {
						cnt = member_service.updateGrade(memberInfoDTO);
				}
			}
			
			if(cnt !=0 ) {
				status = "OK";
			}
			
			
		} catch (Exception e) {e.getStackTrace();}
		
		
		result.setStatus(status);
		result.setCount(cnt);
		
		return result;
		
	}
	
	
	@GetMapping("delete_Grage/{checkData}")
	public AjaxList delete_Grage(@PathVariable int checkData) {
		
		AjaxList result = new AjaxList();
		String status = "FAIL";
		int cnt = 0;
		List<memberInfoDTO> list = null;
		list = member_service.uidSerch(checkData);
		
		try {
			if( list != null ) {
				for (memberInfoDTO memberInfoDTO : list) {
					
					memberInfoDTO.setAUTHORITY("ROLE_MEMBER");
					cnt = member_service.updateGrade(memberInfoDTO);
				}
				
			}
			
			if(cnt !=0 ) {
				status = "OK";
			}
			
			
		} catch (Exception e) {e.getStackTrace();}
		
		
		result.setStatus(status);
		result.setCount(cnt);
		
		return result;
		
	}
	
	
	
	
}
