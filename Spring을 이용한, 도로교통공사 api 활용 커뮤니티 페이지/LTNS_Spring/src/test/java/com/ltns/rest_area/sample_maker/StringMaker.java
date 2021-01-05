package com.ltns.rest_area.sample_maker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ltns.rest_area.domain.DTO;
import com.ltns.rest_area.domain.user.UserDTO;
import com.ltns.rest_area.sample_maker.domain.MemberMakeDAO;

public class StringMaker {
	
	private String randomAlphabetString(int limitSize) {
		StringBuffer str=new StringBuffer();
		char c='a';
		for(int i=0;i<limitSize;i++) {
			str.append(c+(int)(Math.random()*27));
		}
		return str.toString();
	}
	
	private String randomCapitalAlphabetString(int limitSize) {
		StringBuffer str=new StringBuffer();
		char c='A';
		for(int i=0;i<limitSize;i++) {
			str.append(c+(int)(Math.random()*27));
		}
		return str.toString();
	}
	
	private String randomNumberString(int limitSize) {
		StringBuffer str=new StringBuffer();
		char c='0';
		for(int i=0;i<limitSize;i++) {
			str.append(c+(int)(Math.random()*11));
		}
		return str.toString();
	}
	
	private String randomSwitch(int limitSize) {
		StringBuffer str=new StringBuffer();
		char c='0';
		for(int i=0;i<limitSize;i++) {
			String _str="";
			switch ((int)(Math.random()*3)) {
			case 0:
				_str=randomNumberString(1);
				break;
			case 1:
				_str=randomAlphabetString(1);
				break;
			case 2:
				_str=randomCapitalAlphabetString(1);
				break;
			}
			str.append(_str);
		}
		return str.toString();
	}
	
	
	public List<DTO> makeRandomMember(int num){
		List<DTO> result=new ArrayList<DTO>();
		Map<String,Integer> map1=new HashMap<String,Integer>();
		Map<String,Integer> map2=new HashMap<String,Integer>();

		String [] email= {"naver.com","google.com","daum.com"};

		//um_username을 셋1으로 받는다..
		for (DTO v : new MemberMakeDAO().selectAllUser()) {
			map1.put(((UserDTO) v).getUm_username(),1);
		}
		
		for(int i=0;i<num;i++) {
			String um_username=randomSwitch((int)(Math.random()*8))+email[(int)(Math.random()*3)];
			
			//확인=>없으면 셋2에 집어넣고
			if(map1.get(um_username)==1 && map2.get(um_username)==1) {
				continue;
			}
			map2.put(um_username, 1);
			
			//비밀번호
			String um_password=randomSwitch((int)(Math.random()*8));
			
			//dto를 생성한다.
			DTO dto = new UserDTO().builder().um_username(um_username).um_password(um_password).build();
			
		}
		
		return result;
	}
}
