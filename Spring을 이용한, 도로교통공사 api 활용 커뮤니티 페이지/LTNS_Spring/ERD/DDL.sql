 -- 테이블 순서는 관계를 고려하여 한 번에 실행해도 에러가 발생하지 않게 정렬되었습니다.

DROP SEQUENCE SEQ_usermember_um_uid;
DROP SEQUENCE Post_SEQ;
DROP SEQUENCE Comments_SEQ;

DROP TABLE UserMember CASCADE CONSTRAINTS;
DROP TABLE Auth CASCADE CONSTRAINTS;
DROP TABLE RestArea CASCADE CONSTRAINTS;
DROP TABLE GasStation CASCADE CONSTRAINTS;
DROP TABLE FoodMenu CASCADE CONSTRAINTS;
DROP TABLE Post CASCADE CONSTRAINTS;
DROP TABLE Comments CASCADE CONSTRAINTS;
DROP TABLE RA_like CASCADE CONSTRAINTS;
DROP TABLE GS_like CASCADE CONSTRAINTS;
DROP TABLE FM_like CASCADE CONSTRAINTS;
DROP TABLE Post_like CASCADE CONSTRAINTS;

DROP VIEW userView;


/* Create Sequences */

CREATE SEQUENCE SEQ_usermember_um_uid INCREMENT BY 1 START WITH 1;



/* Create Tables */

CREATE TABLE auth
(
	-- ROLE_GUEST
	-- ROLE_MEMBER
	-- ROLE_ADMIN
	authority varchar2(100) DEFAULT 'ROLE_MEMBER' NOT NULL,
	um_uid number NOT NULL,
	PRIMARY KEY (authority, um_uid)
);


CREATE TABLE usermember
(
	um_uid number NOT NULL,
	um_username varchar2(100) NOT NULL UNIQUE,
	um_password varchar2(200) NOT NULL,
	um_nickname varchar2(100) NOT NULL UNIQUE,
	um_regDate timestamp DEFAULT SYSDATE NOT NULL,
	um_enabled char(1) DEFAULT '1' NOT NULL,
	PRIMARY KEY (um_uid)
);



/* Create Foreign Keys */

ALTER TABLE auth
	ADD FOREIGN KEY (um_uid)
	REFERENCES usermember (um_uid)
;


/* Create Views */

CREATE OR REPLACE VIEW userView AS SELECT 
u.um_username AS username,
u.um_password AS password,
u.um_enabled AS enabled,
a.authority
FROM userMember u
JOIN auth a
ON
u.um_uid = a.um_uid;


-- RestArea Table Create SQL
CREATE TABLE RestArea
(
    ra_code           VARCHAR2(100)    NOT NULL, 
    ra_name           VARCHAR2(100)    NOT NULL, 
    ra_routeNo        VARCHAR2(100)    NOT NULL, 
    ra_routeName      VARCHAR2(100)    NOT NULL, 
    ra_destination    VARCHAR2(100)    NOT NULL, 
    ra_xValue    	  VARCHAR2(100)    NOT NULL, 
    ra_yValue         VARCHAR2(100)    NOT NULL, 
    CONSTRAINT RESTAREA_PK PRIMARY KEY (ra_code)
);
/

--COMMENT ON TABLE RestArea IS '휴게소'
--/
--
--COMMENT ON COLUMN RestArea.ra_code IS '휴게소 코드'
--/
--
--COMMENT ON COLUMN RestArea.ra_name IS '휴게소 이름'
--/
--
--COMMENT ON COLUMN RestArea.ra_routeNo IS '노선코드'
--/
--
--COMMENT ON COLUMN RestArea.ra_routeName IS '노선명'
--/
--
--COMMENT ON COLUMN RestArea.ra_updownType IS '방향'
--/
--
--COMMENT ON COLUMN RestArea.ra_destination IS '행선 구분'
--/
--
--COMMENT ON COLUMN RestArea.ra_locName IS '거점으로부터 거리'
--/


-- Post Table Create SQL
CREATE TABLE Post
(
    post_id          number     	   NOT NULL, 
    post_title       VARCHAR2(1000)    NOT NULL, 
    post_contents    CLOB			   NOT NULL, 
    um_uid           VARCHAR2(100)     NOT NULL, 
    um_username      VARCHAR2(100)     NOT NULL, 
    post_regdate     TIMESTAMP         NOT NULL, 
    ra_code          VARCHAR2(100)     NOT NULL, 
    post_reported    VARCHAR2(100)     , 
    CONSTRAINT POST_PK PRIMARY KEY (post_id)
);
/

CREATE SEQUENCE Post_SEQ
START WITH 1
INCREMENT BY 1;
/

--CREATE OR REPLACE TRIGGER Post_AI_TRG
--BEFORE INSERT ON Post 
--REFERENCING NEW AS NEW FOR EACH ROW 
--BEGIN 
--    SELECT Post_SEQ.NEXTVAL
--    INTO :NEW.post_id
--    FROM DUAL;
--END;
/

--DROP TRIGGER Post_AI_TRG;
/

--DROP SEQUENCE Post_SEQ;
/

--COMMENT ON TABLE Post IS '게시글'
--/
--
--COMMENT ON COLUMN Post.post_id IS '게시글 고유번호'
--/
--
--COMMENT ON COLUMN Post.post_title IS '게시글 제목'
--/
--
--COMMENT ON COLUMN Post.post_contents IS '게시글 내용'
--/
--
--COMMENT ON COLUMN Post.um_uid IS '작성자'
--/
--
--COMMENT ON COLUMN Post.um_username IS '작성자 아이디'
--/
--
--COMMENT ON COLUMN Post.post_regdate IS '작성일'
--/
--
--COMMENT ON COLUMN Post.ra_code IS '휴게소 코드'
--/
--
--COMMENT ON COLUMN Post.post_reported IS '신고'
/

ALTER TABLE Post
    ADD CONSTRAINT FK_Post_um_uid_UserMember_um_u FOREIGN KEY (um_uid)
        REFERENCES UserMember (um_uid);
/

ALTER TABLE Post
    ADD CONSTRAINT FK_Post_ra_code_RestArea_ra_co FOREIGN KEY (ra_code)
        REFERENCES RestArea (ra_code);
/


-- GasStation Table Create SQL
CREATE TABLE GasStation
(
    gs_code        VARCHAR2(100)    NOT NULL,
    gs_name        VARCHAR2(100)   NOT NULL,
    ra_code        VARCHAR2(100)   ,
    ra_name		   VARCHAR2(100)   ,
    gs_company     VARCHAR2(100)   NOT NULL, 
    gs_diesel      VARCHAR2(100)   NOT NULL, 
    gs_gasoline    VARCHAR2(100)    NOT NULL, 
    gs_lpg         VARCHAR2(100)   NOT NULL, 
    CONSTRAINT GASSTATION_PK PRIMARY KEY (gs_code)
);
/

--COMMENT ON TABLE GasStation IS '주유소'
--/
--
--COMMENT ON COLUMN GasStation.gs_id IS '주유소 고유번호'
--/
--
--COMMENT ON COLUMN GasStation.ra_code IS '휴게소 코드'
--/
--
--COMMENT ON COLUMN GasStation.gs_company IS '정유소'
--/
--
--COMMENT ON COLUMN GasStation.gs_diesel IS '경유'
--/
--
--COMMENT ON COLUMN GasStation.gs_gasoline IS '휘발유'
--/
--
--COMMENT ON COLUMN GasStation.gs_lpg IS 'LPG'
/



-- FoodMenu Table Create SQL
CREATE TABLE FoodMenu
(
    fm_id          VARCHAR2(100)     NOT NULL, 
    fm_code        VARCHAR2(100)     NOT NULL, 
    ra_code        VARCHAR2(100)      NOT NULL,
    ra_name		   VARCHAR2(100)   ,
    fm_name        VARCHAR2(100)     NOT NULL, 
    fm_price       VARCHAR2(100)     NOT NULL, 
    fm_material    CLOB			    , 
    fm_etc         CLOB    			, 
    CONSTRAINT FOODMENU_PK PRIMARY KEY (fm_id)
);
/

--COMMENT ON TABLE FoodMenu IS '음식메뉴'
--/
--
--COMMENT ON COLUMN FoodMenu.fm_id IS '음식 고유번호'
--/
--
--COMMENT ON COLUMN FoodMenu.fm_code IS '음식 코드'
--/
--
--COMMENT ON COLUMN FoodMenu.ra_code IS '휴게소 코드'
--/
--
--COMMENT ON COLUMN FoodMenu.fm_name IS '음식 이름'
--/
--
--COMMENT ON COLUMN FoodMenu.fm_price IS '음식 가격'
--/
--
--COMMENT ON COLUMN FoodMenu.fm_material IS '음식 재료'
--/
--
--COMMENT ON COLUMN FoodMenu.fm_etc IS '음식 상세 내역'
/

ALTER TABLE FoodMenu
    ADD CONSTRAINT FK_FoodMenu_ra_code_RestArea_r FOREIGN KEY (ra_code)
        REFERENCES RestArea (ra_code);
/


-- FM_like Table Create SQL
CREATE TABLE FM_like
(
    um_uid    number    NOT NULL, 
    fm_id     VARCHAR2(100)    NOT NULL
);
/

--COMMENT ON TABLE FM_like IS '음식 좋아요'
--/
--
--COMMENT ON COLUMN FM_like.um_uid IS '회원 고유번호'
--/
--
--COMMENT ON COLUMN FM_like.fm_id IS '음식 고유번호'
/

ALTER TABLE FM_like
    ADD CONSTRAINT FK_FM_like_fm_id_FoodMenu_fm_i FOREIGN KEY (fm_id)
        REFERENCES FoodMenu (fm_id);
/

ALTER TABLE FM_like
    ADD CONSTRAINT FK_FM_like_um_uid_UserMember_u FOREIGN KEY (um_uid)
        REFERENCES UserMember (um_uid);
/


-- GS_like Table Create SQL
CREATE TABLE GS_like
(
    um_uid    number    NOT NULL, 
    gs_code     VARCHAR2(100)    NOT NULL
);
/
--
--COMMENT ON TABLE GS_like IS '주유소 좋아요'
--/
--
--COMMENT ON COLUMN GS_like.um_uid IS '회원 고유번호'
--/
--
--COMMENT ON COLUMN GS_like.gs_id IS '주유소 고유번호'
/

ALTER TABLE GS_like
    ADD CONSTRAINT FK_GS_like_gs_id_GasStation_gs FOREIGN KEY (gs_code)
        REFERENCES GasStation (gs_code);
/

ALTER TABLE GS_like
    ADD CONSTRAINT FK_GS_like_um_uid_UserMember_u FOREIGN KEY (um_uid)
        REFERENCES UserMember (um_uid);
/


-- RA_like Table Create SQL
CREATE TABLE RA_like
(
    um_uid     number    NOT NULL, 
    ra_code    VARCHAR2(100)    NOT NULL
);
/

--COMMENT ON TABLE RA_like IS '휴게소 좋아요'
--/
--
--COMMENT ON COLUMN RA_like.um_uid IS '회원 고유번호'
--/
--
--COMMENT ON COLUMN RA_like.ra_code IS '휴게소 코드'
/

ALTER TABLE RA_like
    ADD CONSTRAINT FK_RA_like_um_uid_UserMember_u FOREIGN KEY (um_uid)
        REFERENCES UserMember (um_uid);
/

ALTER TABLE RA_like
    ADD CONSTRAINT FK_RA_like_ra_code_RestArea_ra FOREIGN KEY (ra_code)
        REFERENCES RestArea (ra_code);
/


-- Post_like Table Create SQL
CREATE TABLE Post_like
(
    um_uid     number    NOT NULL, 
    post_id    number    NOT NULL
);
/

--COMMENT ON TABLE Post_like IS '게시글 좋아요'
--/
--
--COMMENT ON COLUMN Post_like.um_uid IS '회원 고유번호'
--/
--
--COMMENT ON COLUMN Post_like.post_id IS '게시글 고유번호'
/

ALTER TABLE Post_like
    ADD CONSTRAINT FK_Post_like_post_id_Post_post FOREIGN KEY (post_id)
        REFERENCES Post (post_id);
/

ALTER TABLE Post_like
    ADD CONSTRAINT FK_Post_like_um_uid_UserMember FOREIGN KEY (um_uid)
        REFERENCES UserMember (um_uid);
/



-- Comment Table Create SQL
CREATE TABLE Comments
(
    comment_id          number              NOT NULL, 
    comment_contents    CLOB			    NOT NULL, 
    um_uid              number              NOT NULL, 
    um_username         VARCHAR2(100)       NOT NULL, 
    um_regdate          TIMESTAMP           NOT NULL, 
    post_id             number              NOT NULL, 
    comment_reported    VARCHAR2(100)       , 
    CONSTRAINT COMMENT_PK PRIMARY KEY (comment_id)
);
/

CREATE SEQUENCE Comments_SEQ
START WITH 1
INCREMENT BY 1;
/

--CREATE OR REPLACE TRIGGER Comment_AI_TRG
--BEFORE INSERT ON Comment 
--REFERENCING NEW AS NEW FOR EACH ROW 
--BEGIN 
--    SELECT Comment_SEQ.NEXTVAL
--    INTO :NEW.comment_id
--    FROM DUAL;
--END;
/

--DROP TRIGGER Comment_AI_TRG;
/

--DROP SEQUENCE Comment_SEQ;
/

ALTER TABLE Comments
    ADD CONSTRAINT FK_Comment_post_id_Post_post_i FOREIGN KEY (post_id)
        REFERENCES Post (post_id);
/

ALTER TABLE Comments
    ADD CONSTRAINT FK_Comment_um_uid_UserMember_u FOREIGN KEY (um_uid)
        REFERENCES UserMember (um_uid);
/



select * from schedule;


