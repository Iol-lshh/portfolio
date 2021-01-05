package com.ltns.rest_area.domain.admin;

import com.ltns.rest_area.domain.DTO;

import lombok.Data;

@Data
public class AreaDataDTO implements DTO {

	private String GS_NAME; // 주유소 이름
	private String GS_CODE; // 주유소 코드 
	private String GS_COMPANY; // 주유소 회사
	private String GS_DIESEL; // 디젤
	private String GS_GASOLINE; // 가솔린 
	private String GS_LPG; // 엘피지
	
	
	
	private String RA_CODE; //휴게소 코드
	private String RA_NAME; // 휴게소 이름
	private String RA_ROUTENO; // 고속도로 방향 넘버
	private String RA_ROUTENAME;  // 고속도로 휴게소 방향 
	private String RA_DESTINATION; // 휴게소 지역 
	private String RA_XVALUE; // X
	private String RA_YVALUE; // Y 
	
	
	
	
	private int FM_ID; // UID 
	private int FM_CODE; // UID
	private String FM_NAME; // UID
	private int FM_price; // UID
	
	
	private String code; //업데이트 받을정보 
	private String areaName;
	private String areaDe;
	private String outName;
	private String outNum;
	
	
	
	
	
	
	int CNT;
	private String target;
	private String content; 
	private String names;

	
}
