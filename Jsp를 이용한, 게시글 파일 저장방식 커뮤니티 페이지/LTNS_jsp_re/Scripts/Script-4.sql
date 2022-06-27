/*회원관리-----------------------------------------------------------------------------*/
/*
 * grade : ADMIN : 관리자
 * 		 : MEMBER : 일반 회원
 * */
/*insert sample MEMBER*/
INSERT INTO mm_table(mm_id,id,password,nickname,email,grade)
VALUES (1,'sampleid','sample_pwd1','샘플닉네임','sampleemail@sample.com','MEMBER');

--AI 활용
INSERT INTO mm_table(mm_id,id,password,nickname,email,grade)
VALUES (SEQ_mm_table_mm_id.NEXTVAL,'sampleid2','sample_pwd1','샘플닉네임2','sample2email@sample.com','MEMBER');

--최신 AI 값
SELECT SEQ_mm_table_mm_id.CURRVAL FROM DUAL;

/*확인*/
SELECT * FROM mm_table;




DELETE FROM mm_table;




/*파일-----------------------------------------------------------------------------*/
/*insert sample file */
/*
 * filekind : POST : html 포스트 문서
 * 			 : IMAGE : 이미지
 * 			 : ELSE : 기타
 * */
INSERT INTO file_table(file_id,filekind,real_filename,filename)
VALUES (1,'POST','?','?');

--AI 활용 DOC 게시글 문서 집어넣기
INSERT INTO file_table(file_id,filekind,real_filename,filename)
VALUES (SEQ_FILE_TABLE_FILE_ID.NEXTVAL,'POST','?','?');

--최신 AI 값
SELECT SEQ_file_table_file_id.CURRVAL FROM DUAL;

/*확인*/
SELECT * FROM file_table;
DELETE FROM file_table;




/*게시글-----------------------------------------------------------------------------*/
/*insert sample post*/
/*
 * category : NOTICE : 공지사항
 * 			: MOVIEW : 영화
 * 			: BOOK : 도서
 * 			: SPORTS : 운동
 * 			: GAME : 게임
 * */
--AI 활용
INSERT INTO post_table(post_id,title,writer,category,regdate,post_contents,viewCnt)
VALUES (SEQ_post_table_post_id.NEXTVAL,'샘플 글입니다',회원id(mm_id)를입력하세요,'NOTICE',SYSTIMESTAMP,파일id(file_id)를입력하세요,0);

INSERT INTO post_table(post_id,title,writer,category,regdate,post_contents,viewCnt)
VALUES (SEQ_post_table_post_id.NEXTVAL,'샘플 글입니다',2,'NOTICE',SYSTIMESTAMP,3,0);

--최신 AI 값
SELECT SEQ_post_table_post_id.CURRVAL FROM DUAL;

/*조회수 늘리기*/
UPDATE post_table SET viewCnt=viewCnt+1 WHERE post_id='포스트아이디를 입력해주세요';

/*확인*/
SELECT * FROM post_table;
DELETE FROM file_table;


/*댓글-----------------------------------------------------------------------------*/
/*insert sample comment*/
INSERT INTO comment_table(comment_id,comment_contents,writer,post_id,regdate)
VALUES (1,'샘플댓글입니다샘플댓글입니다샘플댓글이라구샘플댓글입니다샘플댓글입니다',2,3,SYSTIMESTAMP);

--AI 활용
INSERT INTO comment_table(comment_id,comment_contents,writer,post_id,regdate)
VALUES (SEQ_comment_table_comment_id.NEXTVAL,'샘플댓글입니다샘퓨ㅡㄽㅁ니아럼니아험ㄴ이ㅏ',2,3,SYSTIMESTAMP);


--최신 AI 값
SELECT SEQ_comment_table_comment_id.CURRVAL FROM DUAL;

/*확인*/
SELECT * FROM comment_table;
DELETE FROM comment_table;





