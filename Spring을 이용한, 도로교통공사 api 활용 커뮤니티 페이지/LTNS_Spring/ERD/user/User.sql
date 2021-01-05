
/* Drop Views */

DROP VIEW userView;


/* Drop Tables */

DROP TABLE auth CASCADE CONSTRAINTS;
DROP TABLE usermember CASCADE CONSTRAINTS;



/* Drop Sequences */

DROP SEQUENCE SEQ_usermember_um_uid;




/* Create Sequences */

CREATE SEQUENCE SEQ_usermember_um_uid INCREMENT BY 1 START WITH 1;



/* Create Tables */

CREATE TABLE auth
(
	-- ROLE_GUEST
	-- ROLE_MEMBER
	-- ROLE_ADMIN
	authority varchar2(50) DEFAULT 'ROLE_MEMBER' NOT NULL,
	um_uid number NOT NULL,
	PRIMARY KEY (authority, um_uid)
);


CREATE TABLE usermember
(
	um_uid number NOT NULL,
	um_username varchar2(50) NOT NULL UNIQUE,
	um_password varchar2(200) NOT NULL,
	um_nickname varchar2(50) NOT NULL UNIQUE,
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



/* Comments */

COMMENT ON COLUMN auth.au_authority IS 'ROLE_GUEST
ROLE_MEMBER
ROLE_ADMIN';



/* TestData */

INSERT INTO userMember(um_uid, um_username, um_password ,um_nickname) VALUES(SEQ_usermember_um_uid.NEXTVAL, 'admin', '$2a$10$.ty2lbI.rSz7bjpmWXRop.S5SZZPGzNQuKmPFDgHscDhjijAPlhai',  '운영자');
INSERT INTO auth(um_uid) VALUES(1);
INSERT INTO auth VALUES('ROLE_ADMIN', 1);
INSERT INTO userMember(um_uid, um_username, um_password ,um_nickname) VALUES(SEQ_usermember_um_uid.NEXTVAL, 'hong', '$2a$10$QneCaQwCDQIWEnnYT64/pOk.88K91rDL81LlZ4NxAzdJtRXiNMOoq',  '홍성혁');
INSERT INTO auth(um_uid) VALUES(2);
INSERT INTO userMember(um_uid, um_username, um_password ,um_nickname) VALUES(SEQ_usermember_um_uid.NEXTVAL, 'hyun', '$2a$10$6s1NtNSY3BEULN7krTuw3OfECZYR0WdICc/HYzRLVdK4ZiDR337nG',  '김현준');
INSERT INTO auth(um_uid) VALUES(3);
INSERT INTO userMember(um_uid, um_username, um_password ,um_nickname) VALUES(SEQ_usermember_um_uid.NEXTVAL, 'hwi', '$2a$10$suS4tGXX5zhdBGln/5QfXutWjpe6VwNRN2mee9FZ52Z7KWdf35jwi',  '김휘진');
INSERT INTO auth(um_uid) VALUES(4);
INSERT INTO userMember(um_uid, um_username, um_password ,um_nickname) VALUES(SEQ_usermember_um_uid.NEXTVAL, 'park', '$2a$10$5Yy/c52u4Ped7j11mMxKEeuHAIRHjiZyg5NyltWqpW5MGIh6zk/Ky', '박성언');
INSERT INTO auth(um_uid) VALUES(5);
commit;
SELECT * FROM usermember;
SELECT * FROM auth;
SELECT * FROM userView;