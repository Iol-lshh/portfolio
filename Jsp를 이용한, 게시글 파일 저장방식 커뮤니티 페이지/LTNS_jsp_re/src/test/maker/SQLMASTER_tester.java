package test.maker;

public interface SQLMASTER_tester {
	/* refresh 관련 */
	String DROP_COMMENT_AI_SQ ="DROP SEQUENCE SEQ_comment_table_comment_id";
	String DROP_FILE_AI_SQ="DROP SEQUENCE SEQ_file_table_file_id";
	String DROP_MM_AI_SQ="DROP SEQUENCE SEQ_mm_table_mm_id";
	String DROP_POST_AI_SQ="DROP SEQUENCE SEQ_post_table_post_id";
	
	String CREATE_COMMENT_AI_SQ ="CREATE SEQUENCE SEQ_comment_table_comment_id INCREMENT BY 1 START WITH 1";
	String CREATE_FILE_AI_SQ="CREATE SEQUENCE SEQ_file_table_file_id INCREMENT BY 1 START WITH 1";
	String CREATE_MM_AI_SQ="CREATE SEQUENCE SEQ_mm_table_mm_id INCREMENT BY 1 START WITH 1";
	String CREATE_POST_AI_SQ="CREATE SEQUENCE SEQ_post_table_post_id INCREMENT BY 1 START WITH 1";
	
	String DROP_COMMENT_TABLE="DROP TABLE comment_table CASCADE CONSTRAINTS";
	String DROP_EMPATHIZE_TABLE="DROP TABLE empathize_table CASCADE CONSTRAINTS";
	String DROP_FILE_TABLE="DROP TABLE file_table CASCADE CONSTRAINTS";
	String DROP_POST_TABLE="DROP TABLE post_table CASCADE CONSTRAINTS";
	String DROP_MM_TABLE="DROP TABLE mm_table CASCADE CONSTRAINTS";
	
	
	String CREATE_COMMENT_TABLE="CREATE TABLE comment_table " + 
			"( comment_id number NOT NULL, " +
			"   comment_contents varchar2(1024) NOT NULL, " +  
			"   writer number NOT NULL, " + 
			"   post_id number NOT NULL, " +
			"   regdate timestamp NOT NULL, " + 
			"   PRIMARY KEY (comment_id) " + 
			")";
	String CREATE_EMPATHIZE_TABLE="CREATE TABLE empathize_table " + 
			"( post_id number NOT NULL, " + 
			"  mm_id number NOT NULL, " + 
			"  CONSTRAINT empath UNIQUE (post_id, mm_id) " + 
			")";
	String CREATE_FILE_TABLE="CREATE TABLE file_table " + 
			"( file_id number NOT NULL, " + 
			"  filekind varchar2(30) NOT NULL, " + 
			"  real_filename varchar2(256) NOT NULL, " + 
			"  filename varchar2(256) NOT NULL, " + 
			"  PRIMARY KEY (file_id) " + 
			")";
	String CREATE_MM_TABLE="CREATE TABLE mm_table " + 
			"( mm_id number NOT NULL, " + 
			"  id varchar2(20) NOT NULL UNIQUE, " + 
			"  password varchar2(50) NOT NULL, " + 
			"  nickname varchar2(20) NOT NULL UNIQUE, " + 
			"  email varchar2(100) NOT NULL UNIQUE, " + 
			"  grade  varchar2(30) NOT NULL, " + 
			"  PRIMARY KEY (mm_id) " + 
			")";
	String CREATE_POST_TABLE="CREATE TABLE post_table " + 
			"( post_id number NOT NULL, " + 
			"  title varchar2(100) NOT NULL, " + 
			"  writer number NOT NULL, " + 
			"  category varchar2(30) NOT NULL, " + 
			"  regdate timestamp NOT NULL, " + 
			"  post_contents NUMBER NOT NULL, " + 
			"  viewCnt number DEFAULT 0 NOT NULL, " + 
			"  PRIMARY KEY (post_id) " + 
			")";
	
	String ALTER_FOREIGN_KEY_POST_CONTENTS_REF_FILE_ID="ALTER TABLE post_table " + 
			"   ADD FOREIGN KEY (post_contents) " + 
			"   REFERENCES file_table (file_id) " + 
			"      ON DELETE CASCADE";
	String ALTER_FOREIGN_KEY_C_WRITER_REF_MM_ID="ALTER TABLE comment_table " + 
			"   ADD FOREIGN KEY (writer) " + 
			"   REFERENCES mm_table (mm_id) " + 
			"      ON DELETE CASCADE";
	String ALTER_FOREIGN_KEY_MM_ID_REF_MM_ID="ALTER TABLE empathize_table " + 
			"   ADD FOREIGN KEY (mm_id) " + 
			"   REFERENCES mm_table (mm_id) " + 
			"      ON DELETE CASCADE";
	String ALTER_FOREIGN_KEY_P_WRITER_REF_MM_ID="ALTER TABLE post_table " + 
			"   ADD FOREIGN KEY (writer) " + 
			"   REFERENCES mm_table (mm_id) " + 
			"      ON DELETE CASCADE";
	String ALTER_FOREIGN_KEY_C_POST_ID_REF_P_POST_ID="ALTER TABLE comment_table " + 
			"   ADD FOREIGN KEY (post_id) " + 
			"   REFERENCES post_table (post_id) " + 
			"      ON DELETE CASCADE";
	String ALTER_FOREIGN_KEY_E_POST_ID_REF_P_POST_ID="ALTER TABLE empathize_table " + 
			"   ADD FOREIGN KEY (post_id) " + 
			"   REFERENCES post_table (post_id) " + 
			"      ON DELETE CASCADE";
	
	String SELECT_ALL_FROM_COMMENT_TABLE="SELECT * FROM comment_table";
	String SELECT_ALL_FROM_EMPATHIZE_TABLE="SELECT * FROM empathize_table";
	String SELECT_ALL_FROM_FILE_TABLE="SELECT * FROM file_table";
	String SELECT_ALL_FROM_MM_TABLE="SELECT * FROM mm_table";
	String SELECT_ALL_FROM_POST_TABLE="SELECT * FROM post_table";
	
	String DROP_POST_VIEW="DROP VIEW tot_post_view";
	String CREATE_TOT_POST_VIEW="CREATE VIEW tot_post_view (post_id,regdate,empathize_cnt,view_cnt) " + 
			"AS (SELECT P.post_id, P.regdate, E.empathCnt, P.viewCnt " + 
			"FROM post_table P LEFT OUTER JOIN ( " + 
			"                           SELECT post_id, COUNT(*) AS empathCnt " + 
			"                           FROM empathize_table " + 
			"                           GROUP BY post_id " + 
			"                           ) E  " + 
			"ON P.post_id=E.post_id )";
	
	String SELECT_ALL_FROM_TOT_POST_VIEW="SELECT * FROM tot_post_view";
	
	
	
	/*MM*/
	//id, password, nickname, email, grade
	String INSERT_NEW_MM="INSERT INTO mm_table(mm_id,id,password,nickname,email,grade) " + 
			"VALUES (SEQ_mm_table_mm_id.NEXTVAL,?,?,?,?,?)";
	
	/*post*/
	//1.문서 파일을 집어넣는다
	String INSERT_NEW_FILE="INSERT INTO file_table(file_id,filekind,real_filename,filename) " + 
			"VALUES (SEQ_FILE_TABLE_FILE_ID.NEXTVAL,'POST',?,?)";
	//2.문서 파일이 들어간 file_id를 가져온다 
	String SELECT_FILE_ID_CURRVAL="SELECT SEQ_file_table_file_id.CURRVAL FROM DUAL";
	//3.post_table에 집어넣는다
	String INSERT_NEW_POST="INSERT INTO post_table(post_id,title,writer,category,regdate,post_contents,viewCnt) " + 
			"VALUES (SEQ_post_table_post_id.NEXTVAL,'샘플 글입니다',3,'NOTICE',SYSTIMESTAMP,4,0";
}
