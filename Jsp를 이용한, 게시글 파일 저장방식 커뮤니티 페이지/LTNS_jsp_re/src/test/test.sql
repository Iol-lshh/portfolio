-- 댓글
CREATE TABLE comment_table
(
   -- 댓글 고유번호
   comment_id number ,
   -- 내용
   comment_contents varchar2(1024),
   -- 작성자
   writer number,
   -- 게시글 고유번호
   post_id number,
   -- 작성일
   regdate timestamp
);


-- 공감 테이블
CREATE TABLE empathize_table
(
   -- 게시글 고유번호
   post_id number,
   -- 회원고유번호
   mm_id number
  
);



-- 파일 테이블
CREATE TABLE file_table
(
   -- 파일 고유번호
   file_id number,
   -- 분류 : _FILEKIND 도메인을 사용
   filekind varchar2(30),
   -- 실제파일명
   real_filename varchar2(256),
   -- 업로드 파일명
   filename varchar2(256)
);

-- 회원
CREATE TABLE mm_table
(
   -- 회원고유번호
   mm_id number,
   -- 회원id : 영문 대소문자 숫자 조합
   id varchar2(20),
   -- 비밀번호 : 특수문자 영문 숫자 8~25
   password varchar2(50),
   -- 닉네임
   nickname varchar2(20),
   -- 이메일 : 이메일 형식 @ 검사
   email varchar2(100),
   -- 등급 : 도메인 grade 사용
   grade  varchar2(30)
);

-- 게시글
CREATE TABLE post_table
(
   -- 게시글 고유번호
   post_id number,
   -- 제목
   title varchar2(100),
   -- 작성자 : member 테이블의 mem_id 외래키
   writer number,
   -- 카테고리 : _CATEGORY  도메인을 따름
   category varchar2(30),
   -- 작성일
   regdate timestamp,
   -- 게시글 내용 파일 고유번호
   post_contents NUMBER,
   -- 조회수
   viewCnt number
);

desc mm_table;
insert into mm_table (mm_id, id, PASSWORD, NICKNAME, EMAIL, GRADE) values (1, 't1', 't1', 't1', 't1', '일반');
insert into mm_table (mm_id, id, PASSWORD, NICKNAME, EMAIL, GRADE) values (2, 't2', 't2', 't2', 't2', '일반');
insert into mm_table (mm_id, id, PASSWORD, NICKNAME, EMAIL, GRADE) values (3, 't3', 't3', 't3', 't3', '일반');
insert into mm_table (mm_id, id, PASSWORD, NICKNAME, EMAIL, GRADE) values (4, 't4', 't4', 't4', 't4', '일반');
insert into mm_table (mm_id, id, PASSWORD, NICKNAME, EMAIL, GRADE) values (5, 't5', 't5', 't5', 't5', '일반');
insert into mm_table (mm_id, id, PASSWORD, NICKNAME, EMAIL, GRADE) values (6, 't6', 't6', 't6', 't6', '일반');
insert into mm_table (mm_id, id, PASSWORD, NICKNAME, EMAIL, GRADE) values (7, 't7', 't7', 't7', 't7', '일반');
insert into mm_table (mm_id, id, PASSWORD, NICKNAME, EMAIL, GRADE) values (8, 't8', 't8', 't8', 't8', '일반');
insert into mm_table (mm_id, id, PASSWORD, NICKNAME, EMAIL, GRADE) values (9, 't9', 't9', 't9', 't9', '일반');
insert into mm_table (mm_id, id, PASSWORD, NICKNAME, EMAIL, GRADE) values (10, 't10', 't10', 't10', 't10', '일반');
insert into mm_table (mm_id, id, PASSWORD, NICKNAME, EMAIL, GRADE) values (11, 't11', 't11', 't11', 't11', '일반');
insert into mm_table (mm_id, id, PASSWORD, NICKNAME, EMAIL, GRADE) values (12, 't12', 't12', 't12', 't12', '일반');
insert into mm_table (mm_id, id, PASSWORD, NICKNAME, EMAIL, GRADE) values (13, 't13', 't13', 't13', 't13', '일반');
insert into mm_table (mm_id, id, PASSWORD, NICKNAME, EMAIL, GRADE) values (14, 't14', 't14', 't14', 't14', '일반');
insert into mm_table (mm_id, id, PASSWORD, NICKNAME, EMAIL, GRADE) values (15, 't15', 't15', 't15', 't15', '일반');
insert into mm_table (mm_id, id, PASSWORD, NICKNAME, EMAIL, GRADE) values (16, 't16', 't16', 't16', 't16', '일반');
insert into mm_table (mm_id, id, PASSWORD, NICKNAME, EMAIL, GRADE) values (17, 't17', 't17', 't17', 't17', '일반');
insert into mm_table (mm_id, id, PASSWORD, NICKNAME, EMAIL, GRADE) values (18, 't18', 't18', 't18', 't18', '일반');
insert into mm_table (mm_id, id, PASSWORD, NICKNAME, EMAIL, GRADE) values (19, 't19', 't19', 't19', 't19', '일반');
insert into mm_table (mm_id, id, PASSWORD, NICKNAME, EMAIL, GRADE) values (20, 't20', 't20', 't20', 't20', '일반');
insert into mm_table (mm_id, id, PASSWORD, NICKNAME, EMAIL, GRADE) values (21, 't21', 't21', 't21', 't21', '일반');

desc file_table;
insert into file_table (FILE_ID, FILEKIND, REAL_FILENAME, FILENAME) values (1, '노가다', '1.txt', '1.txt');
insert into file_table (FILE_ID, FILEKIND, REAL_FILENAME, FILENAME) values (2, '노가다', '2.txt', '2.txt');
insert into file_table (FILE_ID, FILEKIND, REAL_FILENAME, FILENAME) values (3, '노가다', '3.txt', '3.txt');
insert into file_table (FILE_ID, FILEKIND, REAL_FILENAME, FILENAME) values (4, '노가다', '4.txt', '4.txt');
insert into file_table (FILE_ID, FILEKIND, REAL_FILENAME, FILENAME) values (5, '노가다', '5.txt', '5.txt');
insert into file_table (FILE_ID, FILEKIND, REAL_FILENAME, FILENAME) values (6, '노가다', '6.txt', '6.txt');
insert into file_table (FILE_ID, FILEKIND, REAL_FILENAME, FILENAME) values (7, '노가다', '7.txt', '7.txt');
insert into file_table (FILE_ID, FILEKIND, REAL_FILENAME, FILENAME) values (8, '노가다', '8.txt', '8.txt');
insert into file_table (FILE_ID, FILEKIND, REAL_FILENAME, FILENAME) values (9, '노가다', '9.txt', '9.txt');
insert into file_table (FILE_ID, FILEKIND, REAL_FILENAME, FILENAME) values (10, '노가다', '10.txt', '10.txt');
insert into file_table (FILE_ID, FILEKIND, REAL_FILENAME, FILENAME) values (11, '노가다', '11.txt', '11.txt');
insert into file_table (FILE_ID, FILEKIND, REAL_FILENAME, FILENAME) values (12, '노가다', '12.txt', '12.txt');
insert into file_table (FILE_ID, FILEKIND, REAL_FILENAME, FILENAME) values (13, '노가다', '13.txt', '13.txt');
insert into file_table (FILE_ID, FILEKIND, REAL_FILENAME, FILENAME) values (14, '노가다', '14.txt', '14.txt');
insert into file_table (FILE_ID, FILEKIND, REAL_FILENAME, FILENAME) values (15, '노가다', '15.txt', '15.txt');
insert into file_table (FILE_ID, FILEKIND, REAL_FILENAME, FILENAME) values (16, '노가다', '16.txt', '16.txt');
insert into file_table (FILE_ID, FILEKIND, REAL_FILENAME, FILENAME) values (17, '노가다', '17.txt', '17.txt');
insert into file_table (FILE_ID, FILEKIND, REAL_FILENAME, FILENAME) values (18, '노가다', '18.txt', '18.txt');
insert into file_table (FILE_ID, FILEKIND, REAL_FILENAME, FILENAME) values (19, '노가다', '19.txt', '19.txt');
insert into file_table (FILE_ID, FILEKIND, REAL_FILENAME, FILENAME) values (20, '노가다', '20.txt', '20.txt');

desc post_table;
insert into post_table (POST_ID, TITLE, WRITER, CATEGORY, REGDATE, POST_CONTENTS, VIEWCNT) values (1, '타이틀', 1, 'movie', sysdate, 1, 0);
insert into post_table (POST_ID, TITLE, WRITER, CATEGORY, REGDATE, POST_CONTENTS, VIEWCNT) values (2, '타이틀', 2, 'movie', sysdate, 2, 3);
insert into post_table (POST_ID, TITLE, WRITER, CATEGORY, REGDATE, POST_CONTENTS, VIEWCNT) values (3, '타이틀', 3, 'movie', sysdate, 3, 0);
insert into post_table (POST_ID, TITLE, WRITER, CATEGORY, REGDATE, POST_CONTENTS, VIEWCNT) values (4, '타이틀', 4, 'movie', sysdate, 4, 7);
insert into post_table (POST_ID, TITLE, WRITER, CATEGORY, REGDATE, POST_CONTENTS, VIEWCNT) values (5, '타이틀', 5, 'movie', sysdate, 5, 0);
insert into post_table (POST_ID, TITLE, WRITER, CATEGORY, REGDATE, POST_CONTENTS, VIEWCNT) values (6, '타이틀', 6, 'movie', sysdate, 6, 0);
insert into post_table (POST_ID, TITLE, WRITER, CATEGORY, REGDATE, POST_CONTENTS, VIEWCNT) values (7, '타이틀', 7, 'movie', sysdate, 7, 10);
insert into post_table (POST_ID, TITLE, WRITER, CATEGORY, REGDATE, POST_CONTENTS, VIEWCNT) values (8, '타이틀', 8, 'movie', sysdate, 8, 0);
insert into post_table (POST_ID, TITLE, WRITER, CATEGORY, REGDATE, POST_CONTENTS, VIEWCNT) values (9, '타이틀', 9, 'movie', sysdate, 9, 0);
insert into post_table (POST_ID, TITLE, WRITER, CATEGORY, REGDATE, POST_CONTENTS, VIEWCNT) values (10, '타이틀', 10, 'movie', sysdate, 10, 0);
insert into post_table (POST_ID, TITLE, WRITER, CATEGORY, REGDATE, POST_CONTENTS, VIEWCNT) values (11, '타이틀', 11, 'movie', sysdate, 11, 0);
insert into post_table (POST_ID, TITLE, WRITER, CATEGORY, REGDATE, POST_CONTENTS, VIEWCNT) values (12, '타이틀', 12, 'movie', sysdate, 12, 3);
insert into post_table (POST_ID, TITLE, WRITER, CATEGORY, REGDATE, POST_CONTENTS, VIEWCNT) values (13, '타이틀', 13, 'movie', sysdate, 13, 0);
insert into post_table (POST_ID, TITLE, WRITER, CATEGORY, REGDATE, POST_CONTENTS, VIEWCNT) values (14, '타이틀', 14, 'movie', sysdate, 14, 7);
insert into post_table (POST_ID, TITLE, WRITER, CATEGORY, REGDATE, POST_CONTENTS, VIEWCNT) values (15, '타이틀', 15, 'movie', sysdate, 15, 7);
insert into post_table (POST_ID, TITLE, WRITER, CATEGORY, REGDATE, POST_CONTENTS, VIEWCNT) values (16, '타이틀', 16, 'movie', sysdate, 16, 0);
insert into post_table (POST_ID, TITLE, WRITER, CATEGORY, REGDATE, POST_CONTENTS, VIEWCNT) values (17, '타이틀', 17, 'movie', sysdate, 17, 15);
insert into post_table (POST_ID, TITLE, WRITER, CATEGORY, REGDATE, POST_CONTENTS, VIEWCNT) values (18, '타이틀', 18, 'movie', sysdate, 18, 0);
insert into post_table (POST_ID, TITLE, WRITER, CATEGORY, REGDATE, POST_CONTENTS, VIEWCNT) values (19, '타이틀', 19, 'movie', sysdate, 19, 3);
insert into post_table (POST_ID, TITLE, WRITER, CATEGORY, REGDATE, POST_CONTENTS, VIEWCNT) values (20, '타이틀', 20, 'movie', sysdate, 20, 101);

desc empathize_table;
insert into empathize_table (POST_ID, MM_ID) values (1, 20);
insert into empathize_table (POST_ID, MM_ID) values (2, 19);
insert into empathize_table (POST_ID, MM_ID) values (3, 18);
insert into empathize_table (POST_ID, MM_ID) values (4, 17);
insert into empathize_table (POST_ID, MM_ID) values (5, 16);
insert into empathize_table (POST_ID, MM_ID) values (6, 15);
insert into empathize_table (POST_ID, MM_ID) values (7, 14);
insert into empathize_table (POST_ID, MM_ID) values (8, 13);
insert into empathize_table (POST_ID, MM_ID) values (9, 12);
insert into empathize_table (POST_ID, MM_ID) values (10, 11);
insert into empathize_table (POST_ID, MM_ID) values (11, 10);
insert into empathize_table (POST_ID, MM_ID) values (12, 9);
insert into empathize_table (POST_ID, MM_ID) values (13, 8);
insert into empathize_table (POST_ID, MM_ID) values (14, 7);
insert into empathize_table (POST_ID, MM_ID) values (15, 6);
insert into empathize_table (POST_ID, MM_ID) values (16, 5);
insert into empathize_table (POST_ID, MM_ID) values (17, 4);
insert into empathize_table (POST_ID, MM_ID) values (18, 3);
insert into empathize_table (POST_ID, MM_ID) values (19, 2);
insert into empathize_table (POST_ID, MM_ID) values (20, 1);


select * from post_table;
select * from file_table;
select count(*) from empathize_table where post_id = 10;

