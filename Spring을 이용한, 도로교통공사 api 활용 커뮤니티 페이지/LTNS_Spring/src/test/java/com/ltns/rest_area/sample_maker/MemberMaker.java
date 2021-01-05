package com.ltns.rest_area.sample_maker;

import java.sql.SQLException;
import java.util.ArrayList;

import com.ltns.rest_area.sample_maker.domain.AbstractDAO_test;
import com.ltns.rest_area.sample_maker.domain.RefreshTableDAO;

class MemberMaker extends AbstractDAO_test  {

	

		//title
		String mkPw() {
			String result = "";
			String [] arr = {"a7898a8sda8s8","a7898a8sda8s8","a7898a8sda8s8"};
			int size = arr.length;
			int random  = (int)(Math.random()*size);
			result = arr[random];
			
			return result;
		}
	
		//내용
		String mkId() {
				String result="";
				int whilelimit=(int)(Math.random()*8)+4;
				for(int i=0;i<whilelimit;i++) {
					char[] asciiiii= {'a','0'};
					//1: 아스키코드 이용 a~z
					//2: 0~9
					char randomchar=asciiiii[(int)(Math.random()*2)];
					if(randomchar>61)
						randomchar+=(int)(Math.random()*26);
					else
						randomchar+=(int)(Math.random()*10);
					result+=randomchar;
				}
				
				return result;
		}
		
		
		public String postDate() {
			String result ="2020-12-";
			int random = (int)(Math.random()*30);
			result+= random;
			return result;
		}
	
	
	
	
	
	public static void main(String[] args) {
		RefreshTableDAO dao = new RefreshTableDAO();
		MemberMaker m = new MemberMaker();
		dao.insert_Member(7,m.mkId(), m.mkPw(), m.mkId(), m.postDate());
		
	}
	
	
}
