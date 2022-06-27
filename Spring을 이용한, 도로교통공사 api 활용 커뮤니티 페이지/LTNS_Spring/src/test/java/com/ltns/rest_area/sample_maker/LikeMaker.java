package com.ltns.rest_area.sample_maker;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import com.ltns.rest_area.sample_maker.domain.AbstractDAO_test;
import com.ltns.rest_area.sample_maker.domain.RefreshTableDAO;

public class LikeMaker extends AbstractDAO_test {


	public Integer mk_Uid() throws SQLException {
		RefreshTableDAO dao = new RefreshTableDAO();
		ArrayList<Integer> counts = new ArrayList<Integer>();
		HashMap<Integer, String> map = dao.memberInfo();
		for (Entry<Integer, String> data : map.entrySet()) {
			counts.add(data.getKey());
		}
		int random = (int)(Math.random()*5);
		int result = counts.get(random);
		return result;
	}
	
	
	public String mkRacode() throws SQLException {
		RefreshTableDAO dao = new RefreshTableDAO();
		ArrayList<String> list = dao.RAcode();
		int random = (int)(Math.random()*200);
		String result = list.get(random);
		return result;
	}
	
	
	public String mkGcode() throws SQLException {
		RefreshTableDAO dao = new RefreshTableDAO();
		ArrayList<String> list = dao.GScode();
		int random = (int)(Math.random()*165);
		String result = list.get(random);
		System.out.println(result);
		return result;
	}
	
	public String mkFcode() throws SQLException {
		RefreshTableDAO dao = new RefreshTableDAO();
		ArrayList<String> list = dao.Fcode();
		int random = (int)(Math.random()*2400);
		String result = list.get(random);
		System.out.println(result);
		return result;
		
	}
	
	
	
	
	
	
	
	public static void main(String[] args) throws SQLException {

		RefreshTableDAO dao = new RefreshTableDAO();
		LikeMaker like = new LikeMaker();
		
		int dataSize = 10;
		
		for (int i = 0; i < dataSize; i++) {
			dao.like_Food(like.mk_Uid(), like.mkFcode());
			dao.like_Gas(like.mk_Uid(), like.mkGcode());
			dao.like_Area(like.mk_Uid(), like.mkRacode());
				
		}
		
	}
	
}
