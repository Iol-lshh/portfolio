/*회원관리-----------------------------------------------------------------------------*/
/*
 * grade : ADMIN : 관리자
 * 		 : MEMBER : 일반 회원
 * */
/*insert sample MEMBER*/
INSERT INTO mm_table(mm_id,id,password,nickname,email,grade)
VALUES (1,'a','sample_pwd1','샘플닉네임','sampleemail@sample.com','MEMBER');

--AI 활용
INSERT INTO mm_table(mm_id,id,password,nickname,email,grade)
VALUES (SEQ_mm_table_mm_id.NEXTVAL,'a','b','ab','sample23email@ab.com','as');

--최신 AI 값
SELECT SEQ_mm_table_mm_id.CURRVAL FROM DUAL;

/*확인*/

DELETE FROM mm_table;
SELECT * FROM MM_TABLE mt ;

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
SELECT * FROM MM_TABLE mt ;
DELETE FROM file_table WHERE FILE_ID =2;



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
VALUES (SEQ_post_table_post_id.NEXTVAL,'샘플 글입니다',2,'NOTICE',SYSTIMESTAMP,SEQ_file_table_file_id.CURRVAL,0);

INSERT INTO post_table(post_id,title,writer,category,regdate,post_contents,viewCnt)
VALUES (SEQ_post_table_post_id.NEXTVAL,'샘플 글입니다',3,'NOTICE',SYSTIMESTAMP,4,0);

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

SELECT * FROM MM_TABLE mt ;

DELETE FROM  MM_TABLE mt ;
DELETE FROM  POST_TABLE pt ;
DELETE FROM  FILE_TABLE ft ;


/*공감-----------------------------------------------------------------------------*/
/*insert sample empathize*/
INSERT INTO empathize_table(post_id,mm_id)
VALUES (3, 2);




/*-----------------------------------메인페이지 관련-----------------------------------*/
/*--공감수 순으로 뷰를 정렬하여,포스트 3개  가져오기--*/
SELECT P.post_id AS post_id, M.mm_id AS mm_id, M.ID AS id, M.nickname AS nickname, P.title AS title, P.regdate AS regdate, P.category AS category, F.real_filename AS real_filename, V.empathize_cnt AS empathize_cnt, P.viewcnt AS viewcnt
FROM tot_post_view V 
LEFT OUTER JOIN post_table P
ON V.post_id=P.post_id
LEFT OUTER JOIN mm_table M
ON P.writer=M.mm_id
LEFT OUTER JOIN file_table F
ON P.post_contents=F.file_id
WHERE ROWNUM <= 3
ORDER by V.empathize_cnt DESC
;

--viewCnt와 file_realname 뽑아야 한다!!
/*--조회수 순으로 뷰를 정렬하여,포스트 6개  가져오기--*/
SELECT P.post_id AS post_id, M.mm_id AS mm_id, M.ID AS id, M.nickname AS nickname, P.title AS title, P.regdate AS regdate, P.category AS category, F.real_filename AS real_filename, V.empathize_cnt AS empathize_cnt, P.viewcnt AS viewcnt
FROM tot_post_view V 
LEFT OUTER JOIN post_table P
ON V.post_id=P.post_id
LEFT OUTER JOIN mm_table M
ON P.writer=M.mm_id
LEFT OUTER JOIN file_table F
ON P.post_contents=F.file_id
WHERE ROWNUM <= 6
ORDER by P.viewcnt DESC
;
			
SELECT * FROM MM_TABLE mt WHERE nickname = 'ㅁㄴㅇ'; 

SELECT * FROM MM_TABLE mt2 ;
SELECT * FROM MM_TABLE mt  WHERE NICKNAME ='운영자';

/*--최신 순으로 포스트 5개 가져오기--*/
SELECT P.post_id AS post_id, M.mm_id AS mm_id, M.ID AS id, M.nickname AS nickname, P.title AS title, P.regdate AS regdate, P.category AS category, F.real_filename AS real_filename, V.empathize_cnt AS empathize_cnt, P.viewcnt AS viewcnt
FROM tot_post_view V 
LEFT OUTER JOIN post_table P
ON V.post_id=P.post_id
LEFT OUTER JOIN mm_table M
ON P.writer=M.mm_id
LEFT OUTER JOIN file_table F
ON P.post_contents=F.file_id
WHERE ROWNUM <= 5
ORDER by P.regdate DESC
;


/*--최신 순으로, n번째 이후, 다음 포스트 3개 가져오기 매개변수로 regdate 받을것!--*/
SELECT P.post_id AS post_id, M.mm_id AS mm_id, M.ID AS id, M.nickname AS nickname, P.title AS title, P.regdate AS regdate, P.category AS category, F.real_filename AS real_filename, V.empathize_cnt AS empathize_cnt, P.viewcnt AS viewcnt
FROM tot_post_view V 
LEFT OUTER JOIN post_table P
ON V.post_id=P.post_id
LEFT OUTER JOIN mm_table M
ON P.writer=M.mm_id
LEFT OUTER JOIN file_table F
ON P.post_contents=F.file_id
WHERE P.regdate<'2020-11-02 2:00:00' AND rownum <=5
ORDER BY P.regdate DESC
;



/*--파일 가져오기--*/
SELECT file_id, filekind, filename, real_filename
FROM file_table
WHERE file_id=?
;

SELECT * FROM EMPATHIZE_TABLE et WHERE post_id =45 AND MM_ID =1; 

INSERT INTO empathize_table(post_id,mm_id)
VALUES (6, 2);



SELECT * FROM EMPATHIZE_TABLE et WHERE post_id =43 AND MM_ID =1 ;


DELETE EMPATHIZE_TABLE ;

SELECT * FROM MM_TABLE mt ;

SELECT * FROM tot_post_view WHERE post_id =60;



SELECT * FROM MM_TABLE WHERE id = 'sampleid23' AND PASSWORD = '123123';

SELECT * FROM MM_TABLE mt WHERE id = 'a'; 


SELECT * FROM FILE_TABLE ft WHERE FILE_ID =3321;
SELECT * FROM POST_TABLE pt  WHERE  POST_ID =2321; 
SELECT * FROM mm_table; 


SELECT * FROM MM_TABLE mt WHERE id = "a";


SELECT * FROM MM_TABLE mt WHERE MM_ID = 1;

SELECT * FROM MM_TABLE mt WHERE id = 'a'; 
SELECT * FROM MM_TABLE ;
DELETE  FROM MM_TABLE ;

SELECT * FROM MM_TABLE WHERE ID='a' AND NICKNAME= '우리의미족' AND EMAIL='admin@LTNS.com';

DELETE FROM POST_TABLE pt ;
DELETE FROM FILE_TABLE ft ;

SELECT * FROM POST_TABLE pt;
SELECT * FROM FILE_TABLE ft;
SELECT * FROM MM_TABLE mt ;



DELETE FROM FILE_TABLE ft ;

SELECT * FROM FILE_TABLE ft WHERE REAL_FILENAME ="C:\WebProJ\LT_PJ\LTNS_jsp_re\WebContent\data\20201113163105_sample.txt";


DELETE FILE_TABLE WHERE FILE_ID = 3;
SELECT * FROM FILE_TABLE ft WHERE FILE_ID =4;

UPDATE FILE_TABLE  SET FILENAME = 'test.tste' , REAL_FILENAME  = 'te.tst' WHERE file_id = 79;

UPDATE MM_TABLE  SET PASSWORD = 'a' WHERE mm_id = 1;

COMMIT;

SELECT * FROM MM_TABLE WHERE ID=? AND NICKNAME= ? AND EMAIL=? 
SELECT * FROM MM_TABLE mt ;

SELECT * FROM POST_TABLE pt;
SELECT * FROM FILE_TABLE ft;
SELECT * FROM MM_TABLE mt;

SELECT * FROM POST_TABLE pt, MM_TABLE mt WHERE POST_ID = 104;

SELECT * FROM MM_TABLE mt;