/*회원관리-----------------------------------------------------------------------------*/
/*
 * grade : ADMIN : 관리자
 * 		 : MEMBER : 일반 회원
 * */
/*insert sample MEMBER*/
INSERT INTO mm_table(mm_id,id,password,nickname,email,grade)
VALUES (1,'sampleid','sample_pwd1','샘플닉네임','sampleemail@sample.com','MEMBER');

INSERT INTO mm_table(mm_id,id,password,nickname,email,grade)
VALUES (SEQ_mm_table_mm_id.NEXTVAL,?,?,?,?,?);

--AI 활용
INSERT INTO mm_table(mm_id,id,password,nickname,email,grade)
VALUES (SEQ_mm_table_mm_id.NEXTVAL,'sampleid23','sample_pwd1','샘플닉네임23','sample23email@sample.com','MEMBER');

--최신 AI 값
SELECT SEQ_mm_table_mm_id.CURRVAL FROM DUAL;

/*확인*/
SELECT * FROM mm_table;


SELECT mm_id FROM mm_table;

DELETE FROM mm_table
WHERE mm_id=?;


--시퀀스 테이블 확인
SELECT * FROM USER_SEQUENCES;


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
 * 			: MOVIE : 영화
 * 			: BOOK : 도서
 * 			: SPORTS : 운동
 * 			: GAME : 게임
 * 
 * 문제는!
 * 파일은 무적임.. cascade로 연결되지가 못해.. 반듯반듯반드시 게시글 지울때 찾아가서 지워줘야함..
 * */
--AI 활용
INSERT INTO post_table(post_id,title,writer,category,regdate,post_contents,viewCnt)
VALUES (SEQ_post_table_post_id.NEXTVAL,'샘플 글입니다',2,'NOTICE',SYSTIMESTAMP,?,0);

INSERT INTO post_table(post_id,title,writer,category,regdate,post_contents,viewCnt)
VALUES (SEQ_post_table_post_id.NEXTVAL,?,?,?,SYSTIMESTAMP,?,0);

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



/*공감-----------------------------------------------------------------------------*/
/*insert sample empathize*/
INSERT INTO empathize_table(post_id,mm_id)
VALUES (post_id를넣어주세요, mm_id를넣어주세요);

SELECT * FROM EMPATHIZE_TABLE;


/*-----------------------------------메인페이지 관련-----------------------------------*/
/*--공감수 순으로 뷰를 정렬하여,포스트 3개  가져오기--*/
SELECT *
FROM
(SELECT P.post_id AS post_id, M.mm_id AS mm_id, M.ID AS id, M.nickname AS nickname, P.title AS title, P.regdate AS regdate, P.category AS category, F.real_filename AS real_filename, V.empathize_cnt AS empathize_cnt, P.viewcnt AS viewcnt
FROM tot_post_view V 
LEFT OUTER JOIN post_table P
ON V.post_id=P.post_id
LEFT OUTER JOIN mm_table M
ON P.writer=M.mm_id
LEFT OUTER JOIN file_table F
ON P.post_contents=F.file_id
ORDER by empathize_cnt DESC NULLS LAST, post_id DESC 
)
WHERE ROWNUM <=3
;

SELECT * FROM TOT_POST_VIEW ORDER by empathize_cnt DESC NULLS LAST;

--viewCnt와 file_realname 뽑아야 한다!!
/*--조회수 순으로 뷰를 정렬하여,포스트 6개  가져오기--*/
SELECT  *
FROM 
(
SELECT P.post_id AS post_id, M.mm_id AS mm_id, M.ID AS id, M.nickname AS nickname, P.title AS title, P.regdate AS regdate, P.category AS category, F.real_filename AS real_filename, V.empathize_cnt AS empathize_cnt, P.viewcnt AS viewcnt
FROM tot_post_view V 
LEFT OUTER JOIN post_table P
ON V.post_id=P.post_id
LEFT OUTER JOIN mm_table M
ON P.writer=M.mm_id
LEFT OUTER JOIN file_table F
ON P.post_contents=F.file_id
ORDER by P.viewcnt DESC, post_id DESC 
)
WHERE ROWNUM <= 6
;
				

/*--최신 순으로 포스트 5개 가져오기--*/
SELECT *
FROM 
(
SELECT P.post_id AS post_id, M.mm_id AS mm_id, M.ID AS id, M.nickname AS nickname, P.title AS title, P.regdate AS regdate, P.category AS category, F.real_filename AS real_filename, V.empathize_cnt AS empathize_cnt, P.viewcnt AS viewcnt
FROM tot_post_view V 
LEFT OUTER JOIN post_table P
ON V.post_id=P.post_id
LEFT OUTER JOIN mm_table M
ON P.writer=M.mm_id
LEFT OUTER JOIN file_table F
ON P.post_contents=F.file_id
ORDER BY regdate DESC, post_id DESC 
)
WHERE ROWNUM <= 6
;


/*--최신 순으로, n번째 이후, 다음 포스트 3개 가져오기 매개변수로 regdate 받을것!--*/
SELECT *
FROM 
(
SELECT P.post_id AS post_id, M.mm_id AS mm_id, M.ID AS id, M.nickname AS nickname, P.title AS title, P.regdate AS regdate, P.category AS category, F.real_filename AS real_filename, V.empathize_cnt AS empathize_cnt, P.viewcnt AS viewcnt
FROM tot_post_view V 
LEFT OUTER JOIN post_table P
ON V.post_id=P.post_id
LEFT OUTER JOIN mm_table M
ON P.writer=M.mm_id
LEFT OUTER JOIN file_table F
ON P.post_contents=F.file_id
WHERE P.regdate<'2020-11-02 2:00:00'
ORDER BY regdate DESC, post_id DESC 
)
WHERE ROWNUM<=3
;


/*--파일 가져오기--*/
SELECT file_id, filekind, filename, real_filename
FROM file_table
WHERE file_id=?
;


/*-----------------------------------마이페이지 관련-----------------------------------*/
/*--내가 작성한 글을 최신순으로, 포스트 6개  가져오기--*/
SELECT * FROM (
SELECT P.post_id AS post_id, M.mm_id AS mm_id, M.ID AS id, M.nickname AS nickname, P.title AS title, P.regdate AS regdate, P.category AS category, F.real_filename AS real_filename, V.empathize_cnt AS empathize_cnt, P.viewcnt AS viewcnt
FROM tot_post_view V 
LEFT OUTER JOIN post_table P
ON V.post_id=P.post_id
LEFT OUTER JOIN mm_table M
ON P.writer=M.mm_id
LEFT OUTER JOIN file_table F
ON P.post_contents=F.file_id
WHERE M.mm_id=1
ORDER BY P.regdate DESC, P.post_id DESC 
)
WHERE ROWNUM <= 6
;

--count 가져오기(pagination 위함)
SELECT COUNT(*) AS post_cnt 
FROM post_table
WHERE writer=?
GROUP BY writer
;


--post_id, post_title, writer, regdate
SELECT * FROM(
SELECT P.post_id AS post_id, M.mm_id AS mm_id, M.ID AS id, M.nickname AS nickname, P.title AS title, P.regdate AS regdate, P.category AS category, F.real_filename AS real_filename, P.viewcnt AS viewcnt
FROM  mm_table M
LEFT OUTER JOIN post_table P
ON M.mm_id=P.writer
LEFT OUTER JOIN file_table F
ON P.post_contents=F.file_id
WHERE M.mm_id=1 
ORDER BY regdate DESC , post_id DESC 
)WHERE ROWNUM >= 1 AND ROWNUM <= 1+5
;

-----------------------------------------------------------------
/*--내가 작성한 댓글을 최신순으로, 댓글 6개  가져오기--*/
--comment_id, post_id, regdate, contents
SELECT * FROM (
SELECT  comment_id, post_id, regdate, comment_contents
FROM comment_table
WHERE writer=1
ORDER BY regdate DESC, comment_id DESC
)WHERE ROWNUM <=6
;

--count 가져오기(pagination 위함)
SELECT COUNT(*) AS comment_cnt 
FROM comment_table
WHERE writer=?
GROUP BY writer
;

/*--내가 작성한 댓글을 최신순으로, 댓글 6개  가져오기--*/
--comment_id, post_id, regdate, contents
SELECT * FROM (
SELECT  comment_id, post_id, regdate, comment_contents
FROM comment_table
WHERE writer=? 
ORDER BY regdate DESC, comment_id DESC
)WHERE ROWNUM >= ? AND ROWNUM <= ?+5
;

-------------------------------------------------------------
/*--내가 공감한 게시글을 최신순으로, 포스트 6개 가져오기--*/
--post_id, post_title, writer, regdate
SELECT * FROM(
SELECT P.post_id AS post_id, M.mm_id AS mm_id, M.ID AS id, M.nickname AS nickname, P.title AS title, P.regdate AS regdate, P.category AS category, F.real_filename AS real_filename, P.viewcnt AS viewcnt
FROM empathize_table E
LEFT OUTER JOIN mm_table M
ON E.mm_id=M.mm_id
LEFT OUTER JOIN post_table P
ON E.post_id=P.post_id
LEFT OUTER JOIN file_table F
ON P.post_contents=F.file_id
WHERE E.mm_id=1
ORDER BY regdate DESC, post_id DESC 
)WHERE ROWNUM <= 6
;

--count 가져오기(pagination 위함)
SELECT COUNT(*) AS empathize_post_cnt
FROM empathize_table E
WHERE mm_id=?
GROUP BY mm_id
;

/*--내가 공감한 게시글을 최신순으로, 포스트 6개 가져오기--*/
--post_id, post_title, writer, regdate
SELECT * FROM(
SELECT P.post_id AS post_id, M.mm_id AS mm_id, M.ID AS id, M.nickname AS nickname, P.title AS title, P.regdate AS regdate, P.category AS category, F.real_filename AS real_filename, P.viewcnt AS viewcnt
FROM empathize_table E
LEFT OUTER JOIN mm_table M
ON E.mm_id=M.mm_id
LEFT OUTER JOIN post_table P
ON E.post_id=P.post_id
LEFT OUTER JOIN file_table F
ON P.post_contents=F.file_id
WHERE E.mm_id=1
ORDER BY regdate DESC, post_id DESC 
)WHERE ROWNUM >= 1 AND ROWNUM <= 1+5
;