package com.ltns.rest_area.domain.admin;

import com.ltns.rest_area.domain.DTO;

import lombok.Data;

@Data
public class DashBoardDTO implements DTO {

	// 총합
	private int total_acount;	 // 총 회원수 
	private int total_post;      //총 게시물 수
	private int total_coment;	// 총합 댓글 
	
	
	// 일일 총 갯수 
	private int today_acount;    //오늘 가입한 회원 수 총합
	private int today_post;		 //오늘 작성된 글 수 
	private int today_report;	 //금일 신고된 신고 갯수
	private int today_coment;	 //오늘 작성된 댓글
	
	

	//주유소 정보 
	
	private String gs_code;
	private String gs_name;
	
	private String GS_DIESEL;
	private String 	GS_GASOLINE;
	private String 	GS_LPG;
	
	//휴게소 정보들 
	private String RA_code;
	private String UM_USERNAME;  
	private String RA_name;
	private String FM_name;
	private int FM_PRICE;
	private int BEST_RA;    // 휴게소 추천수 
	
	
	private int FMLIKE;
	private int GSLIKE;
	private int RALIKE;
	
	private int totaldata;
	private int ACCOUNT;
	
	// 메인 그래프 데이터 
	private String addAcount ; 
	private int addAcountTotal;
	
	private String membersdays;
	private int month_total;
	
	private int count;
}
