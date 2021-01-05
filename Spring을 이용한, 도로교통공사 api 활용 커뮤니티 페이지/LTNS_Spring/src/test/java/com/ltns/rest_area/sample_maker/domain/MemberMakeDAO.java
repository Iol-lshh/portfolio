package com.ltns.rest_area.sample_maker.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ltns.rest_area.domain.DAO;
import com.ltns.rest_area.domain.DTO;
import com.ltns.rest_area.domain.VO;
import com.ltns.rest_area.domain.user.UserDTO;

public class MemberMakeDAO extends AbstractDAO_test {

	final static String DELETE_ALL_USERMEMBER="";
	final static String INSERT_USERMEMBER_RAMDOM="";
	final static String INSERT_USER_AUTHORITY="";
	final static String SELECT_ALL_USER="";
	
	public MemberMakeDAO() {
		super();
	}
	
	//deleteAll : 멤버 전부 삭제
	public int deleteAll(){
		try {
			justExcuteBySQL(DELETE_ALL_USERMEMBER);
		} catch (SQLException e) {
			System.out.println("멤버 delete all 에러");
			e.printStackTrace();
		}
		return 0;
	}
	
	//makeMember(int num) : num 만큼 멤버 무작위 생성
	public void makeMember(int num) throws SQLException {
		
		for(int i=0;i<num;i++) {
			pstmt=conn.prepareStatement(INSERT_USERMEMBER_RAMDOM);
//			pstmt.setInt(0, x);
			pstmt=conn.prepareStatement(INSERT_USER_AUTHORITY);
		}
	}	
	
	//그냥 sql문 실행하는 메서드
	public void justExcuteBySQL(String sql) throws SQLException {
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.executeQuery();
		} catch (SQLException e) {
			System.out.println("에러! : "+sql);
			e.printStackTrace();
		}finally {
			conn.commit();
//			close();
		}
	}
	
	public List<DTO> selectAllUser(){
		List<DTO> result=new ArrayList<DTO>();
		try {
		pstmt=conn.prepareStatement(SELECT_ALL_USER);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			String um_username=rs.getString("um_username");
			result.add(new UserDTO().builder().um_username(um_username).build());
		}
		} catch (SQLException e) {
			System.out.println("MemberMakerDAO selectAllUser SQL 에러!");
			e.printStackTrace();
		}
		
		return  result;
	}


}
