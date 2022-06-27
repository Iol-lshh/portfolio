package com.ltns.rest_area.sample_maker;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import com.ltns.rest_area.sample_maker.domain.FirstMemberDAO_test;
import com.ltns.rest_area.sample_maker.domain.RefreshTableDAO_test;

public class TableResetter {
	
	@Test
	public void refreshAllTable() throws SQLException {
		RefreshTableDAO_test dao1=new RefreshTableDAO_test();
	
		dao1.refreshAll();
		dao1.close();
		
		System.out.println("테이블 세팅 완료!");
		
		FirstMemberDAO_test dao2 =new FirstMemberDAO_test();
		dao2.InsertfirstMember();
		dao2.close();
		
		System.out.println("FirstMember 세팅 완료!");
	}
	
	
}
