package com.ltns.rest_area.sample_maker.domain;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.ltns.rest_area.domain.DAO;
import com.ltns.rest_area.domain.DTO;
import com.ltns.rest_area.domain.VO;
import com.ltns.rest_area.domain.memberInfo.memberInfoDTO;

public class RefreshTableDAO extends AbstractDAO_test {
	
	//drop
	final static String DROP_USERMEMBER_SEQ="DROP SEQUENCE SEQ_usermember_um_uid";
	final static String DROP_POST_SEQ =	"DROP SEQUENCE Post_SEQ";
	final static String DROP_COMMENTS_SEQ="DROP SEQUENCE Comments_SEQ";
	final static String DROP_SCHDULE_SEQ = "DROP SEQUENCE s_sid_seq";
	final static String DROP_NOTICE_SEQ ="DROP SEQUENCE notice_id_seq"; 
	
	final static String DROP_USERMEMBER_TABLE="DROP TABLE UserMember CASCADE CONSTRAINTS";
	final static String DROP_AUTH_TABLE="DROP TABLE Auth CASCADE CONSTRAINTS";
	final static String DROP_RESTAREA_TABLE="DROP TABLE RestArea CASCADE CONSTRAINTS";
	final static String DROP_GASSTATION_TABLE="DROP TABLE GasStation CASCADE CONSTRAINTS";
	final static String DROP_FOODMENU_TABLE="DROP TABLE FoodMenu CASCADE CONSTRAINTS";
	final static String DROP_POST_TABLE="DROP TABLE Post CASCADE CONSTRAINTS";
	final static String DROP_COMMENTS_TABLE="DROP TABLE Comments CASCADE CONSTRAINTS";
	final static String DROP_RA_LIKE_TABLE="DROP TABLE RA_like CASCADE CONSTRAINTS";
	final static String DROP_GS_LIKE_TABLE="DROP TABLE GS_like CASCADE CONSTRAINTS";
	final static String DROP_FM_LIKE_TABLE="DROP TABLE FM_like CASCADE CONSTRAINTS";
	final static String DROP_POST_LIKE_TABLE="DROP TABLE Post_like CASCADE CONSTRAINTS";
	
	final static String DROP_SCHEDULE_TABLE="DROP TABLE schedule CASCADE CONSTRAINTS";
	final static String DROP_NOTICE_TABLE="DROP TABLE ADMINNOTICE";
	
	
	
	final static String DROP_USER_VIEW="DROP VIEW userView";
	
	
	
	
	//create
	final static String CREATE_AUTH_TABLE="CREATE TABLE auth " + 
			"( " + 
			"	authority varchar2(100) DEFAULT 'ROLE_MEMBER' NOT NULL, " + 
			"	um_uid number NOT NULL, " + 
			"	PRIMARY KEY (authority, um_uid) " + 
			")";
	
	final static String CREATE_USERMEMBER_TABLE="CREATE TABLE usermember " + 
			"( " + 
			"	um_uid number NOT NULL, " + 
			"	um_username varchar2(100) NOT NULL UNIQUE, " + 
			"	um_password varchar2(200) NOT NULL, " + 
			"	um_nickname varchar2(100) NOT NULL UNIQUE, " + 
			"	um_regDate timestamp DEFAULT SYSDATE NOT NULL, " + 
			"	um_enabled char(1) DEFAULT '1' NOT NULL, " + 
			"	PRIMARY KEY (um_uid) " + 
			")";
	
	final static String CREATE_USERMEMBER_SEQ ="CREATE SEQUENCE SEQ_usermember_um_uid INCREMENT BY 1 START WITH 1";
	
	final static String ALTER_AUTH_UM_UID_FOREIGN_KEY="ALTER TABLE auth " + 
			"	ADD FOREIGN KEY (um_uid) " + 
			"	REFERENCES usermember (um_uid)";
	
	final static String CREATE_USER_VIEW="CREATE OR REPLACE VIEW userView AS SELECT " + 
			"um_username AS username, " + 
			"um_password AS passowrd, " + 
			"authority " + 
			"FROM userMember u " + 
			"JOIN auth a " + 
			"ON " + 
			"u.um_uid = a.um_uid ";
	
	
	
	final static String CREATE_RESTAREA_TABLE="CREATE TABLE RestArea " + 
			"( " + 
			"    ra_code           VARCHAR2(100)    NOT NULL,  " + 
			"    ra_name           VARCHAR2(100)    NOT NULL,  " + 
			"    ra_routeNo        VARCHAR2(100)    NOT NULL,  " + 
			"    ra_routeName      VARCHAR2(100)    NOT NULL,  " + 
			"    ra_destination    VARCHAR2(100)    NOT NULL,  " + 
			"    ra_xValue         VARCHAR2(100)    NOT NULL,  " + 
			"    ra_yValue         VARCHAR2(100)    NOT NULL,  " + 
			"    CONSTRAINT RESTAREA_PK PRIMARY KEY (ra_code) " + 
			")";
	
	final static String CREATE_GASSTATION_TABLE="CREATE TABLE GasStation " + 
			"( " + 
			"    gs_code        VARCHAR2(100)    NOT NULL,  " + 
			"    gs_name        VARCHAR2(100)    NOT NULL,  " + 
			"    ra_code        VARCHAR2(100)    ,  " +
			"    ra_name		VARCHAR2(100)    ,  "+
			"    gs_company     VARCHAR2(100)    NOT NULL,  " + 
			"    gs_diesel      VARCHAR2(100)    ,  " + 
			"    gs_gasoline    VARCHAR2(100)    ,  " + 
			"    gs_lpg         VARCHAR2(100)    ,  " + 
			"    CONSTRAINT GASSTATION_PK PRIMARY KEY (gs_code) " + 
			")";
	
	final static String CREATE_FOODMENU_TABLE="CREATE TABLE FoodMenu " + 
			"( " + 
			"    fm_id          VARCHAR2(100)      NOT NULL,  " + 
			"    fm_code        VARCHAR2(100)      NOT NULL,  " + 
			"    ra_code        VARCHAR2(100)      NOT NULL,  " + 
			"    ra_name		VARCHAR2(100)   	,  "+
			"    fm_name        VARCHAR2(100)      NOT NULL,  " + 
			"    fm_price       number       ,  " + 
			"    fm_material    CLOB			    ,  " + 
			"    fm_etc         CLOB    			,  " + 
			"    CONSTRAINT FOODMENU_PK PRIMARY KEY (fm_id) " + 
			")";
	
	final static String ALTER_FOODMENU_RACODE_FOREIGN_KEY="ALTER TABLE FoodMenu " + 
			"    ADD CONSTRAINT FK_FoodMenu_ra_code_RestArea_r FOREIGN KEY (ra_code) " + 
			"        REFERENCES RestArea (ra_code)";
	
	final static String CREATE_POST_TABLE="CREATE TABLE Post " + 
			"( " + 
			"    post_id          number            NOT NULL,  " + 
			"    post_title       VARCHAR2(1000)    NOT NULL,  " + 
			"    post_contents    CLOB  			NOT NULL,  " + 
			"    um_uid           number            NOT NULL,  " + 
			"    um_username      VARCHAR2(100)     NOT NULL,  " + 
			"    post_regdate     TIMESTAMP         NOT NULL,  " + 
			"    ra_code          VARCHAR2(100)     NOT NULL,  " + 
			"    post_reported    VARCHAR2(100)      ,  " + 
			"    CONSTRAINT POST_PK PRIMARY KEY (post_id) " + 
			")";
	
	final static String CREATE_POST_SEQ ="CREATE SEQUENCE Post_SEQ " + 
			"START WITH 1 " + 
			"INCREMENT BY 1 ";
	
	final static String ALTER_POST_UM_UID_FOREIGN_KEY="ALTER TABLE Post " + 
			"    ADD CONSTRAINT FK_Post_um_uid_UserMember_um_u FOREIGN KEY (um_uid) " + 
			"        REFERENCES UserMember (um_uid)";
	
//	final static String ALTER_POST_RA_CODE_FOREIGN_KEY="ALTER TABLE Post " + 
//			"    ADD CONSTRAINT FK_Post_ra_code_RestArea_ra_co FOREIGN KEY (ra_code) " + 
//			"        REFERENCES RestArea (ra_code)";
//	

	final static String CREATE_RA_LIKE_TABLE="CREATE TABLE RA_like " + 
			"( " + 
			"    um_uid     number    NOT NULL,  " + 
			"    ra_code    VARCHAR2(100)    NOT NULL " + 
			")";
	final static String ALTER_RA_LIKE_UM_UID_FOREIGN_KEY="ALTER TABLE RA_like " + 
			"    ADD CONSTRAINT FK_RA_like_um_uid_UserMember_u FOREIGN KEY (um_uid) " + 
			"        REFERENCES UserMember (um_uid)";
	final static String ALTER_RA_LIKE_RA_CODE_FOREIGN_KEY="ALTER TABLE RA_like " + 
			"    ADD CONSTRAINT FK_RA_like_ra_code_RestArea_ra FOREIGN KEY (ra_code) " + 
			"        REFERENCES RestArea (ra_code)";
	
	final static String CREATE_GS_LIKE_TABLE="CREATE TABLE GS_like " + 
			"( " + 
			"    um_uid    number    NOT NULL,  " + 
			"    gs_code     VARCHAR2(100)    NOT NULL " + 
			")";
	final static String ALTER_GS_LIKE_GS_ID_FOREIGN_KEY="ALTER TABLE GS_like " + 
			"    ADD CONSTRAINT FK_GS_like_gs_id_GasStation_gs FOREIGN KEY (gs_code) " + 
			"        REFERENCES GasStation (gs_code)";
	final static String ALTER_GS_LIKE_UM_UID_FOREIGN_KEY="ALTER TABLE GS_like " + 
			"    ADD CONSTRAINT FK_GS_like_um_uid_UserMember_u FOREIGN KEY (um_uid) " + 
			"        REFERENCES UserMember (um_uid)";
	
	final static String CREATE_FM_LIKE_TABLE="CREATE TABLE FM_like " + 
			"( " + 
			"    um_uid    number    NOT NULL, " + 
			"    fm_id     VARCHAR2(100)    NOT NULL " + 
			")";
	final static String ALTER_FM_LIKE_FM_ID_FOREIGN_KEY="ALTER TABLE FM_like " + 
			"    ADD CONSTRAINT FK_FM_like_fm_id_FoodMenu_fm_i FOREIGN KEY (fm_id) " + 
			"        REFERENCES FoodMenu (fm_id)";
	final static String ALTER_FM_LIKE_UM_UID_FOREIGN_KEY="ALTER TABLE FM_like " + 
			"    ADD CONSTRAINT FK_FM_like_um_uid_UserMember_u FOREIGN KEY (um_uid) " + 
			"        REFERENCES UserMember (um_uid)";
	
	final static String CREATE_POST_LIKE_TABLE="CREATE TABLE Post_like " + 
			"( " + 
			"    um_uid     number    NOT NULL,  " + 
			"    post_id    number    NOT NULL " + 
			")";
	final static String ALTER_POST_LIKE_POST_ID_FOREIGN_KEY="ALTER TABLE Post_like " + 
			"    ADD CONSTRAINT FK_Post_like_post_id_Post_post FOREIGN KEY (post_id) " + 
			"        REFERENCES Post (post_id)";
	final static String ALTER_POST_LIKE_UM_UID_FOREIGN_KEY="ALTER TABLE Post_like " + 
			"    ADD CONSTRAINT FK_Post_like_um_uid_UserMember FOREIGN KEY (um_uid) " + 
			"        REFERENCES UserMember (um_uid)";

	
	final static String CREATE_COMMENTS_TABLE="CREATE TABLE Comments " + 
			"( " + 
			"    comment_id          number               NOT NULL,  " + 
			"    comment_contents    CLOB     			  NOT NULL,  " + 
			"    um_uid              number               NOT NULL,  " + 
			"    um_username         VARCHAR2(100)        NOT NULL,  " + 
			"    um_regdate          TIMESTAMP            NOT NULL,  " + 
			"    post_id             number               NOT NULL,  " + 
			"    comment_reported    VARCHAR2(100)      ,  " + 
			"    CONSTRAINT COMMENT_PK PRIMARY KEY (comment_id) " + 
			")";
	
	final static String CREATE_SCHEDULE_TABLE = "CREATE TABLE schedule " + 
			"(" + 
			"	s_sid number NOT NULL," + 
			"	subject varchar2(20) NOT NULL," + 
			"	startdate varchar2(20) NOT NULL," + 
			"	enddate varchar2(20) NOT NULL," + 
			"	memo varchar2(100)," + 
			"	PRIMARY KEY (s_sid)" + 
			")";
	
	final static String CREATE_NOTICE_TABLE ="CREATE TABLE ADMINNOTICE " + 
			"("  + 
			"    notice_id         INT            NOT NULL, " + 
			"    notice_subject    VARCHAR2(40)   NOT NULL, " + 
			"    notice_regdate    DATE  		  NOT NULL, " + 
			"    notice_writer     VARCHAR2(20)   NOT NULL, " + 
			" 	 notice_content    VARCHAR2(200)   NOT NULL, " + 
			"    PRIMARY KEY (notice_id)" + 
			")";
	
	
	final static String ALTER_COMMENTS_POST_ID_FOREIGN_KEY="ALTER TABLE Comments " + 
			"    ADD CONSTRAINT FK_Comment_post_id_Post_post_i FOREIGN KEY (post_id) " + 
			"        REFERENCES Post (post_id)";
	final static String ALTER_COMMENTS_UM_UID_FOREIGN_KEY="ALTER TABLE Comments\r\n" + 
			"    ADD CONSTRAINT FK_Comment_um_uid_UserMember_u FOREIGN KEY (um_uid)\r\n" + 
			"        REFERENCES UserMember (um_uid)";

	final static String CREATE_COMMENTS_SEQ ="CREATE SEQUENCE Comments_SEQ " + 
			"START WITH 1 " + 
			"INCREMENT BY 1";
	
	final static String CREATE_SEQUENCE_SCHDULE = "CREATE SEQUENCE s_sid_seq " + 
			"START WITH 1 "+ 
			"INCREMENT BY 1";
	
	final static String CREATE_SEQUENCE_NOTICE = "CREATE SEQUENCE notice_id_seq " + 
			"START WITH 1 "+ 
			"INCREMENT BY 1";
	
	//select 
	
	 final static String SELECT_USERMEMBER = "SELECT * FROM USERMEMBER " ;
	 final static String SELECT_AREA = "SELECT * FROM RESTAREA";
	 final static String SELECT_GAS = "SELECT * FROM GASSTATION";
	 final static String SELECT_FOOD = "SELECT * FROM FOODMENU";
	 
	 final static String INSERT_MEMBER_DATA="INSERT INTO UserMember(um_uid, um_username, um_password ,um_nickname,um_regdate)"+ 
				"VALUES(?, ?, ?, ?, ?)";
	 
	 final static String SQL_INSERT_POST =
				"INSERT INTO POST"
				+"(POST_ID,POST_TITLE,POST_CONTENTS,UM_UID,UM_USERNAME,POST_REGDATE,RA_CODE,POST_REPORTED) "
				+"VALUES "
				+"(Post_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?)";
	 
	 final static String SQL_INSERT_LIKE_AREA = 
		"INSERT INTO RA_LIKE" 
		+"(UM_UID, RA_CODE)"
		+"VALUES "
		+"(?,?)";
	 
	 final static String SQL_INSERT_LIKE_GAS= 
        "INSERT INTO GS_LIKE" 
	    +"(UM_UID, GS_CODE)"
	    +"VALUES "
	    +"(?,?)";
	 
	 final static String SQL_INSERT_LIKE_FOOD= 
		        "INSERT INTO FM_LIKE" 
			    +"(UM_UID, FM_ID)"
			    +"VALUES "
			    +"(?,?)";

			 
	 
	 
	public RefreshTableDAO() {
		super();
	}
	
	
	//db 테이블, 시퀀스, 제약조건 전부 초기화
	public void refreshAll() throws SQLException {
		deleteAll();
		createAll();
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
	
	//조회해서 가져오기 
	public HashMap<Integer,String> memberInfo() throws SQLException {
		int cnt = 0;
		int i=0;
		HashMap<Integer,String> Map = new HashMap<Integer, String>();
	
		try {
			pstmt=conn.prepareStatement(SELECT_USERMEMBER);
			pstmt.executeQuery();
			rs = pstmt.executeQuery(SELECT_USERMEMBER); 
			while(rs.next()) {
				i++;
				if(i<=5) {
				Map.put(rs.getInt(1), rs.getString(4));
				}else {
					break;
				}
			}
		}catch (Exception e) {
			e.getMessage();
		}
		
		return Map;
	}
	
	//휴게소 검색
	public ArrayList<String> RAcode() throws SQLException {
	
		ArrayList<String> list = new ArrayList<String>();
		try {
			pstmt=conn.prepareStatement(SELECT_AREA);
			pstmt.executeQuery();
			rs = pstmt.executeQuery(SELECT_AREA); 
			while(rs.next()) {
				list.add(rs.getString(1));
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return list;
	}
	
	//주유소 검색
	public ArrayList<String> GScode() throws SQLException {
		
		ArrayList<String> list = new ArrayList<String>();
		try {
			pstmt=conn.prepareStatement(SELECT_GAS);
			pstmt.executeQuery();
			rs = pstmt.executeQuery(SELECT_GAS); 
			while(rs.next()) {
				list.add(rs.getString(1));
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return list;
	}
	
	
	//푸드 검색
		public ArrayList<String> Fcode() throws SQLException {
			
			ArrayList<String> list = new ArrayList<String>();
			try {
				pstmt=conn.prepareStatement(SELECT_FOOD);
				pstmt.executeQuery();
				rs = pstmt.executeQuery(SELECT_FOOD); 
				while(rs.next()) {
					list.add(rs.getString(1));
				}
			} catch (Exception e) {
				e.getMessage();
			}
			return list;
		}
	
	
	
	
	//insert 
	
	public int Post_Go(String title, String cotent, int uid, String nick, String dates, String code, int report ) {
		int cnt = 0;
		System.out.println(title + " : " + cotent + " : " + uid + " : " + nick + " : " + code + " : " + report + " :");
		try {
			pstmt = conn.prepareStatement(SQL_INSERT_POST);
			pstmt.setString(1, title);
			pstmt.setString(2, cotent);
			pstmt.setInt(3, uid);
			pstmt.setString(4, nick);
			pstmt.setString(5, dates);
			pstmt.setString(6, code);
			pstmt.setInt(7, report);
			cnt = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("에러! : "+ SQL_INSERT_POST);
			e.printStackTrace();
		}
		
		return cnt;
	}
	
	

	public int insert_Member(int uid, String um_username, String um_password, String um_nickname, String data) {
		int cnt = 0;
		System.out.println(um_username + " : " + um_password + " : "  + um_nickname + " : " + data);
		try {
			pstmt = conn.prepareStatement(INSERT_MEMBER_DATA);
			pstmt.setInt(1, uid);
			pstmt.setString(2, um_username);
			pstmt.setString(3, um_password);
			pstmt.setString(4, um_nickname);
			pstmt.setString(5, data);
			cnt = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("에러! : "+ INSERT_MEMBER_DATA);
			e.printStackTrace();
		}
		
		return cnt;
	}
	
	
	
	//휴게소 추천
	public int like_Area(int uid, String code) {
		int cnt = 0;
		System.out.println(uid + " : " + code);
		try {
			pstmt = conn.prepareStatement(SQL_INSERT_LIKE_AREA);
			pstmt.setInt(1, uid);
			pstmt.setString(2, code);
			cnt = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("에러! : "+ SQL_INSERT_LIKE_AREA);
			e.printStackTrace();
		}
		
		return cnt;
	}
	
	//주유소 추천
		public int like_Gas(int uid, String Gcode) {
			int cnt = 0;
			System.out.println(uid + " : " + Gcode);
			try {
				pstmt = conn.prepareStatement(SQL_INSERT_LIKE_GAS);
				pstmt.setInt(1, uid);
				pstmt.setString(2, Gcode);
				cnt = pstmt.executeUpdate();
			} catch (SQLException e) {
				System.out.println("에러! : "+ SQL_INSERT_LIKE_GAS);
				e.printStackTrace();
			}
			
			return cnt;
		}
		
		//음식 추천
		public int like_Food(int uid, String Fcode) {
			int cnt = 0;
			System.out.println(uid + " : " + Fcode);
			try {
				pstmt = conn.prepareStatement(SQL_INSERT_LIKE_FOOD);
				pstmt.setInt(1, uid);
				pstmt.setString(2, Fcode);
				cnt = pstmt.executeUpdate();
			} catch (SQLException e) {
				System.out.println("에러! : "+ SQL_INSERT_LIKE_FOOD);
				e.printStackTrace();
			}
			
			return cnt;
		}
		
	
	

	@Override
	public int deleteAll() {
		try {
			justExcuteBySQL(DROP_USERMEMBER_SEQ);
			justExcuteBySQL(DROP_POST_SEQ);
			justExcuteBySQL(DROP_COMMENTS_SEQ);
			justExcuteBySQL(DROP_USERMEMBER_TABLE);
			justExcuteBySQL(DROP_AUTH_TABLE);
			justExcuteBySQL(DROP_RESTAREA_TABLE);
			justExcuteBySQL(DROP_GASSTATION_TABLE);
			justExcuteBySQL(DROP_FOODMENU_TABLE);
			justExcuteBySQL(DROP_POST_TABLE);
			justExcuteBySQL(DROP_COMMENTS_TABLE);
			justExcuteBySQL(DROP_RA_LIKE_TABLE);
			justExcuteBySQL(DROP_GS_LIKE_TABLE);
			justExcuteBySQL(DROP_FM_LIKE_TABLE);
			justExcuteBySQL(DROP_POST_LIKE_TABLE);
			justExcuteBySQL(DROP_USER_VIEW);
			
			justExcuteBySQL(DROP_SCHEDULE_TABLE);
			justExcuteBySQL(DROP_NOTICE_TABLE);
			
			justExcuteBySQL(DROP_SCHDULE_SEQ);
			justExcuteBySQL(DROP_NOTICE_SEQ);
			
			
		} catch (SQLException e) {
			System.out.println("drop 에러");
			e.printStackTrace();
			return 0;
		}
		return 1;
	}
	
	public int createAll() {
		try {
			justExcuteBySQL(CREATE_AUTH_TABLE);
			justExcuteBySQL(CREATE_USERMEMBER_TABLE);
			justExcuteBySQL(CREATE_USERMEMBER_SEQ);
			justExcuteBySQL(ALTER_AUTH_UM_UID_FOREIGN_KEY);
			justExcuteBySQL(CREATE_USER_VIEW);
			
			justExcuteBySQL(CREATE_RESTAREA_TABLE);
			justExcuteBySQL(CREATE_GASSTATION_TABLE);
			justExcuteBySQL(CREATE_FOODMENU_TABLE);
			justExcuteBySQL(ALTER_FOODMENU_RACODE_FOREIGN_KEY);
			justExcuteBySQL(CREATE_POST_TABLE);
			justExcuteBySQL(CREATE_POST_SEQ);
			justExcuteBySQL(ALTER_POST_UM_UID_FOREIGN_KEY);
//			justExcuteBySQL(ALTER_POST_RA_CODE_FOREIGN_KEY); //api 초기화를 염두함..
			
			justExcuteBySQL(CREATE_RA_LIKE_TABLE);
			justExcuteBySQL(ALTER_RA_LIKE_UM_UID_FOREIGN_KEY);
			justExcuteBySQL(ALTER_RA_LIKE_RA_CODE_FOREIGN_KEY);
			justExcuteBySQL(CREATE_GS_LIKE_TABLE);
			justExcuteBySQL(ALTER_GS_LIKE_UM_UID_FOREIGN_KEY);
			justExcuteBySQL(ALTER_GS_LIKE_GS_ID_FOREIGN_KEY);
			justExcuteBySQL(CREATE_FM_LIKE_TABLE);
			justExcuteBySQL(ALTER_FM_LIKE_UM_UID_FOREIGN_KEY);
			justExcuteBySQL(ALTER_FM_LIKE_FM_ID_FOREIGN_KEY);
			justExcuteBySQL(CREATE_POST_LIKE_TABLE);
			justExcuteBySQL(ALTER_POST_LIKE_UM_UID_FOREIGN_KEY);
			justExcuteBySQL(ALTER_POST_LIKE_POST_ID_FOREIGN_KEY);
			
			justExcuteBySQL(CREATE_COMMENTS_TABLE);
			justExcuteBySQL(ALTER_COMMENTS_UM_UID_FOREIGN_KEY);
			justExcuteBySQL(ALTER_COMMENTS_POST_ID_FOREIGN_KEY);
			justExcuteBySQL(CREATE_COMMENTS_SEQ);
			
			
			justExcuteBySQL(CREATE_SCHEDULE_TABLE);
			justExcuteBySQL(CREATE_NOTICE_TABLE);
			justExcuteBySQL(CREATE_SEQUENCE_SCHDULE);
			justExcuteBySQL(CREATE_SEQUENCE_NOTICE);
		} catch (SQLException e) {
			System.out.println("create 에러");
			e.printStackTrace();
			return 0;
		}
		return 1;
	}



}
