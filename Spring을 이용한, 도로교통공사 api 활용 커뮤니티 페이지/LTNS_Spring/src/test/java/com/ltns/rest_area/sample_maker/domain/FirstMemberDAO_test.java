package com.ltns.rest_area.sample_maker.domain;

import java.sql.SQLException;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

public class FirstMemberDAO_test extends AbstractDAO_test {

	final static String INSERT_MEMBER="INSERT INTO UserMember(um_uid, um_username, um_password ,um_nickname)"+ 
									"VALUES(?, ?, ? , ?)";
	final static String INSERT_AUTH="INSERT INTO Auth VALUES(?, ?)";

	public FirstMemberDAO_test(){
		super();
	}
	
	public void mkInsertMember(int um_uid, String um_username, String um_password, String um_nickname) throws SQLException {
		pstmt.clearParameters();
		pstmt.setInt(1, um_uid);
		pstmt.setString(2, um_username);
		pstmt.setString(3, um_password);
		pstmt.setString(4, um_nickname);
		
	}
	
	public void mkInsertAuth(String auth,int um_uid) throws SQLException {
		pstmt.clearParameters();
		pstmt.setString(1, auth);
		pstmt.setInt(2, um_uid);
	}
	
	public void InsertfirstMember() throws SQLException {
		FirstMemberDTO[] firstmember_dtos= {
				new FirstMemberDTO(1,"admin","$2a$10$.ty2lbI.rSz7bjpmWXRop.S5SZZPGzNQuKmPFDgHscDhjijAPlhai","운영자"),
				new FirstMemberDTO(2,"hong","$2a$10$QneCaQwCDQIWEnnYT64/pOk.88K91rDL81LlZ4NxAzdJtRXiNMOoq","홍성혁"),
				new FirstMemberDTO(3,"hyun","$2a$10$6s1NtNSY3BEULN7krTuw3OfECZYR0WdICc/HYzRLVdK4ZiDR337nG","김현준"),
				new FirstMemberDTO(4,"hwi","$2a$10$suS4tGXX5zhdBGln/5QfXutWjpe6VwNRN2mee9FZ52Z7KWdf35jwi","김휘진"),
				new FirstMemberDTO(5,"park","$2a$10$5Yy/c52u4Ped7j11mMxKEeuHAIRHjiZyg5NyltWqpW5MGIh6zk/Ky","박성언")
		};
		String[] auth_kind= {"ROLE_ADMIN","ROLE_MEMBER"};
		
		//member 넣기
		pstmt=conn.prepareStatement(INSERT_MEMBER);
		for(FirstMemberDTO dto:firstmember_dtos) {
			mkInsertMember(dto.um_uid, dto.um_username, dto.um_password, dto.um_nickname);
			pstmt.addBatch();
			
		}
		pstmt.executeBatch();
		pstmt.clearBatch();
		
		
		//auth 넣기
		pstmt=conn.prepareStatement(INSERT_AUTH);
		for(FirstMemberDTO dto:firstmember_dtos) {
			mkInsertAuth(auth_kind[0], dto.um_uid);
			pstmt.addBatch();
			mkInsertAuth(auth_kind[1], dto.um_uid);
			pstmt.addBatch();
		}
		pstmt.executeBatch();
		pstmt.clearBatch();
		
	}
	
	@AllArgsConstructor
	class FirstMemberDTO{
		int um_uid;
		String um_username;
		String um_password;
		String um_nickname;
	}
	
}

/*예안*/
//INSERT INTO userMember(um_uid, um_username, um_password ,um_nickname) VALUES(SEQ_usermember_um_uid.NEXTVAL, 'admin', '$2a$10$.ty2lbI.rSz7bjpmWXRop.S5SZZPGzNQuKmPFDgHscDhjijAPlhai',  '운영자');
//INSERT INTO auth(um_uid) VALUES(1);
//INSERT INTO auth VALUES('ROLE_ADMIN', 1);
//INSERT INTO userMember(um_uid, um_username, um_password ,um_nickname) VALUES(SEQ_usermember_um_uid.NEXTVAL, 'hong', '$2a$10$QneCaQwCDQIWEnnYT64/pOk.88K91rDL81LlZ4NxAzdJtRXiNMOoq',  '홍성혁');
//INSERT INTO auth(um_uid) VALUES(2);
//INSERT INTO userMember(um_uid, um_username, um_password ,um_nickname) VALUES(SEQ_usermember_um_uid.NEXTVAL, 'hyun', '$2a$10$6s1NtNSY3BEULN7krTuw3OfECZYR0WdICc/HYzRLVdK4ZiDR337nG',  '김현준');
//INSERT INTO auth(um_uid) VALUES(3);
//INSERT INTO userMember(um_uid, um_username, um_password ,um_nickname) VALUES(SEQ_usermember_um_uid.NEXTVAL, 'hwi', '$2a$10$suS4tGXX5zhdBGln/5QfXutWjpe6VwNRN2mee9FZ52Z7KWdf35jwi',  '김휘진');
//INSERT INTO auth(um_uid) VALUES(4);
//INSERT INTO userMember(um_uid, um_username, um_password ,um_nickname) VALUES(SEQ_usermember_um_uid.NEXTVAL, 'park', '$2a$10$5Yy/c52u4Ped7j11mMxKEeuHAIRHjiZyg5NyltWqpW5MGIh6zk/Ky', '박성언');
//INSERT INTO auth(um_uid) VALUES(5);
