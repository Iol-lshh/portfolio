INSERT INTO file_table
(file_id, filekind, real_filename, filename)
VALUES
(1,'2sasdf','testasdf','tests');

INSERT INTO POST_TABLE
(post_id, title, writer, category, regdate, post_contents, viewCnt)
VALUES
(1,'test', 1, '2' , sysdate,0,0);

INSERT INTO MM_TABLE
(mm_id, id, password, nickname, email, grade)
VALUES
(1,'dd','ddsw','dds','dddd','ddd');


SELECT * FROM  mm_table;

select * from file_table;
select * from post_table;






