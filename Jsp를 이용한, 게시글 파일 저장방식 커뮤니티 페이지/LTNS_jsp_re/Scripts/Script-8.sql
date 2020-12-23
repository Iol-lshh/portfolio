SELECT * FROM POST_TABLE pt ;

DELETE FROM POST_TABLE pt WHERE POST_ID =143;

SELECT  * FROM POST_TABLE pt WHERE POST_ID = 40;

delete from post_table where post_id = 190;
SELECT * FROM COMMENT_TABLE ct ;

INSERT INTO comment_table
(comment_id,comment_contents,writer,post_id,regdate) 
VALUES
(SEQ_comment_table_comment_id.NEXTVAL,'asdf',2,40,SYSTIMESTAMP);

SELECT * FROM  COMMENT_TABLE ct ;



INSERT INTO comment_table
(comment_id,comment_contents,writer,post_id,regdate) 
VALUES
(SEQ_comment_table_comment_id.NEXTVAL,'a',2,40,SYSTIMESTAMP);