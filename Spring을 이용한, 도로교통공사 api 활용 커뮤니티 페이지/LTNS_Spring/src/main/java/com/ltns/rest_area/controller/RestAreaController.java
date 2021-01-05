package com.ltns.rest_area.controller;

import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ltns.rest_area.domain.AjaxList;
import com.ltns.rest_area.domain.DTO;
import com.ltns.rest_area.domain.restarea.RestAreaVO;
import com.ltns.rest_area.service.SearchService;

@RestController
@RequestMapping("/restarea")
public class RestAreaController {

	@Autowired
	private SearchService searchService;

	// 요청
	@GetMapping("/{requestDataKind}")
	public AjaxList requestDestinationList(@PathVariable String requestDataKind) {
		AjaxList result=new AjaxList();
		RestAreaVO vo=new RestAreaVO().builder().requestDataKind(requestDataKind).build();
		List<DTO> list=searchService.requestComboList(vo);
		result.setList(list);
		return result;
	}
	
	@GetMapping("/{requestDataKind}/{routeName}")
	public AjaxList requestDestinationList(@PathVariable String requestDataKind, @PathVariable String routeName) {
		AjaxList result=new AjaxList();
		RestAreaVO vo=new RestAreaVO().builder().requestDataKind(requestDataKind).routeName(routeName).build();
		List<DTO> list=searchService.requestComboList(vo);
		result.setList(list);
		return result;
	}
	
	// 휴게소로 검색
	@GetMapping("/{listSort}/{routeName}/{destination}/{orderBy}")
	public AjaxList getList(@PathVariable String listSort, @PathVariable String routeName, @PathVariable String destination, @PathVariable String orderBy) {
		//한 페이지에 리스트할 dto 갯수(numOfRows) 추가
		AjaxList result = moreList(listSort, routeName, destination, orderBy, "10");
		return result;
	}

	@GetMapping("/{listSort}/{routeName}/{destination}/{orderBy}/{numOfRows}")
	public AjaxList moreList(@PathVariable String listSort, @PathVariable String routeName, @PathVariable String destination, @PathVariable String orderBy, @PathVariable String numOfRows) {
		int _numOfRows=0;
		if(numOfRows.equals("ALL")) {
			_numOfRows=searchService.raCount();  //최대 휴게소 이상으로 정해줘야 함!
		}else if(Pattern.matches("[0-9]",numOfRows)) {
			_numOfRows=Integer.parseInt(numOfRows);
		}else {
			_numOfRows=10;
		}
		//lastIndex를 0으로 추가
		AjaxList result = moreList(listSort, routeName, destination, orderBy, _numOfRows, 0);
		return result;
	}
	
	// 추가 리스트 호출(아래로 스크롤) pageNo 이후부터 10개 추가
	@GetMapping("/{listSort}/{routeName}/{destination}/{orderBy}/{numOfRows}/{lastIndex}")
	public AjaxList moreList(@PathVariable String listSort, @PathVariable String routeName, @PathVariable String destination, @PathVariable String orderBy, @PathVariable int numOfRows, @PathVariable int lastIndex) {

		System.out.println("request : /"+listSort+"/"+routeName+"/"+destination+"/"+orderBy+"/"+numOfRows+"/"+lastIndex);
		// response 에 필요한 값들
		StringBuffer message = new StringBuffer();
		String status = "FAIL";
		
		//dto 데이터
		List<DTO> list=null;

		// 페이징 관련 세팅 값들
		int totalCnt=0;	//총 dto 갯수

		
		int totalPage=0;	//총 페이지 갯수

		int pagenationPage=10;	//페이지네이션에 표시할 페이지 갯수
		
		int fromRow=lastIndex+1;
		try {
			switch (listSort) {
			case "ra":
				//전체 휴게소 갯수
				totalCnt=searchService.raCount();
				
				//총 페이지
				totalPage=(int)Math.ceil(totalCnt/(double)numOfRows);
				
				//데이터 가져오기
				list=searchService.selectSomeRaDTOs(routeName,destination,orderBy, fromRow, numOfRows);
				break;
				
				
			case "gs":
				//전체 휴게소 갯수
				totalCnt=searchService.gsCount();
				
				//총 페이지
				totalPage=(int)Math.ceil(totalCnt/(double)numOfRows);
				
				//데이터 가져오기
				list=searchService.selectSomeGsDTOs(routeName,destination,orderBy, fromRow, numOfRows);
				break;
				
				
			case "fm":
				//전체 휴게소 갯수
				totalCnt=searchService.fmCount();
				
				//총 페이지
				totalPage=(int)Math.ceil(totalCnt/(double)numOfRows);
				
				//데이터 가져오기
				list=searchService.selectSomeFmDTOs(routeName,destination,orderBy, fromRow,numOfRows);
				break;
			}
			
			if(list==null) {
				message.append("[리스트할 데이터가 없습니다]");
			}else {
				status="OK";
			}
		}catch(Exception e) {
			e.printStackTrace();
			message.append("[트랜잭션 에러 : "+e.getMessage()+"]");
		}
		
		AjaxList result=new AjaxList();
		result.setStatus(status);
		result.setMessage(message.toString());
		
		if(list!=null) {
			result.setCount(list.size());
			result.setList(list);
		}
		result.setPageNo(0);//필요없다 페이지네이션이 아닌 무한스크롤 방식이기 때문
		result.setTotalPage(totalPage);
		result.setNumOfRows(numOfRows);
		result.setTotalCnt(totalCnt);
		
		result.setPagenationPage(pagenationPage);

		return result;
	}

}
