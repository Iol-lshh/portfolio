
/* Drop Tables */

 
DROP SEQUENCE s_sid_seq;
DROP TABLE schedule CASCADE CONSTRAINTS;

DROP SEQUENCE notice_id_seq;
DROP TABLE ADMINNOTICE;



/* Create Tables */

--현준 추가 



CREATE SEQUENCE notice_id_seq
START WITH 1;


CREATE SEQUENCE s_sid_seq
START WITH 1;


CREATE TABLE schedule
(
	s_sid number NOT NULL,
	subject varchar2(20) NOT NULL,
	startdate varchar2(20) NOT NULL,
	enddate varchar2(20) NOT NULL,
	memo varchar2(100),
	PRIMARY KEY (s_sid)
);




CREATE TABLE ADMINNOTICE
(
    notice_id         INT          NOT NULL, 
    notice_subject    VARCHAR2(40)    NOT NULL, 
    notice_regdate    DATE  		  NOT NULL, 
    notice_writer     VARCHAR2(20)    NOT NULL, 
 	notice_content    VARCHAR2(200)   NOT NULL,
    PRIMARY KEY (notice_id)
);



select * from schedule;
SELECT s_sid_seq.nextval FROM DUAL ;
SELECT * FROM ADMINNOTICE;
SELECT notice_id_seq.nextval FROM DUAL ;

insert into(s_sid, subject, startdate, enddate)
values(s_sid_seq.nextval, 'todo', '2020-12-9', '2020-12-9');




INSERT INTO ADMINNotice (notice_id, notice_subject, notice_regdate, NOTICE_WRITER ,notice_content) values(notice_id_seq.nextval, '공지사항', sysdate, 'title', '요즘 신고건수가 많습니다')




