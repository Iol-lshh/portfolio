package com.ltns.rest_area.sample_maker.domain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.ltns.rest_area.domain.DAO;
import com.ltns.rest_area.domain.DB;
import com.ltns.rest_area.domain.DTO;
import com.ltns.rest_area.domain.VO;

public abstract class AbstractDAO_test implements DAO {
	Connection conn;
	PreparedStatement pstmt;
	Statement stmt;
	ResultSet rs;
	
	public AbstractDAO_test() {
		try {
			//System.out.println("DAO 생성 중..");
			Class.forName(DB.DRIVER);
			conn=DriverManager.getConnection(DB.URL,DB.USERID,DB.USERPW);
			//System.out.println("Master_DAO_tester 생성 성공!, DB 연결");
		}catch (Exception e) {
			e.printStackTrace();
		}// end try
	}// 생성자 end
	
	//DB 자원 반납
	public void close() throws SQLException {
		if(rs != null) rs.close();
		if(pstmt != null) pstmt.close();
		if(stmt != null) stmt.close();
		if(conn != null) conn.close(); 
	}//end close()
	
	//커밋
	public void commit() throws SQLException {
		if(conn!=null) conn.commit();
	}
	
	
	@Override
	public int selectCnt() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int selectCntByInt(int i) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int selectCntByString(String str) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int selectCntByVO(VO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int selectCntByObject(Object obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<DTO> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DTO> selectByInt(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DTO> selectByString(String str) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DTO> selectByDTO(DTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DTO> selectByVO(VO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DTO> selectByObject(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertByDTO(DTO dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertByVO(VO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertByObject(Object obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertAllByDTOs(List<DTO> dtos) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByDTO(DTO dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByVO(VO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByObject(Object obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateAllByDTOs(List<DTO> dtos) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteAll() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteByInt(int i) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteByString(String str) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteByVO(VO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteByObject(Object obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteByUid(int[] uids) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<DTO> selectFromRow(int from, int pagenationPage) {
		// TODO Auto-generated method stub
		return null;
	}
}
