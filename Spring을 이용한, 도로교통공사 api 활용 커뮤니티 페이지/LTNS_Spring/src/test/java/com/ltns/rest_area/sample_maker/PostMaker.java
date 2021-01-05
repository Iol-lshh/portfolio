package com.ltns.rest_area.sample_maker;

import static org.hamcrest.CoreMatchers.containsString;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;

import com.ltns.rest_area.domain.memberInfo.memberInfoDTO;
import com.ltns.rest_area.sample_maker.domain.AbstractDAO_test;
import com.ltns.rest_area.sample_maker.domain.RefreshTableDAO;
import com.ltns.rest_area.service.admin.DashBoardService;


public class PostMaker extends AbstractDAO_test  {

	
	
	
	//title
	String mkTitle() {
		String result = "";
		String [] arr = {"휴게소","주유소","음식"};
		int size = arr.length;
		int random  = (int)(Math.random()*size);
		
		result = arr[random];
		
		return result;
	}
	
	//report 
	int mkReport() {
		int random = (int)(Math.random()*100);
		
	
		return random;
	}
	
	//내용
	String mkContent() {
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
	
	
	
	
	
	
	public String mkRacode() throws SQLException {
		RefreshTableDAO dao = new RefreshTableDAO();
		ArrayList<String> list = dao.RAcode();
		int random = (int)(Math.random()*200);
		String result = list.get(random);
		return result;
		
	}
	
	public String postDate() {
		String result ="2020-12-";
		int random = (int)(Math.random()*30);
		result+= random;
		return result;
	}
	
	
	
	public static void main(String[] args) throws SQLException {
		PostMaker p = new PostMaker();

		
//		int random = (int)(Math.random()*5);
//		RefreshTableDAO_test dao = new RefreshTableDAO_test();
		int random = 0;
		RefreshTableDAO dao = new RefreshTableDAO();
		ArrayList<String> list = new ArrayList<String>();
		ArrayList<Integer> counts = new ArrayList<Integer>();
		HashMap<Integer, String> map = dao.memberInfo();
		for (Entry<Integer, String> data : map.entrySet()) {
			list.add(data.getValue());
			counts.add(data.getKey());
		}
		String datas = "";
		int dataCnt = 0;
		int cnt = 0;
		
		int randomsize = 50;
		for (int i = 0; i < randomsize; i++) {
			random = (int)(Math.random()*5);
			p.mkTitle(); //타이틀
			p.mkContent(); // 내용 
			p.mkRacode();
			p.mkReport();  // 신고횟수 
			p.postDate();
			dao.Post_Go(p.mkTitle(), p.mkContent(), counts.get(random), list.get(random), p.postDate(), p.mkRacode(), p.mkReport());
		}
	

	}
	
}
