-- 테이블 스페이스 생성r
-- 크기는 10MB 로 하며, 용량이 꽉찼을씨 2MB의 크기로 자동으로 증가합니다.
CREATE TABLESPACE LTNSspring
DATAFILE 'C:\ProjectWork\LTNS2\OracleDB\LTNSspring.dbf'
SIZE 10M AUTOEXTEND ON NEXT 2M MAXSIZE UNLIMITED
PERMANENT
EXTENT MANAGEMENT LOCAL AUTOALLOCATE
BLOCKSIZE 8K
SEGMENT SPACE MANAGEMENT MANUAL
FLASHBACK ON;

-- 계정 생성
CREATE USER ltns2 IDENTIFIED BY ltns2
DEFAULT TABLESPACE LTNSspring
TEMPORARY TABLESPACE temp;


-- 유저 권한 부여
GRANT RESOURCE, CONNECT, CREATE VIEW, CREATE SEQUENCE, CREATE TABLE, CREATE PROCEDURE TO ltns2;
