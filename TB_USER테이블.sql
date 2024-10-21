CREATE TABLE TB_USER(
	USER_NO     NUMBER CONSTRAINT TB_USER_PK PRIMARY KEY,
	USER_ID     VARCHAR2(30) NOT NULL,
	USER_PW     VARCHAR2(30) NOT NULL,
	USER_NAME   VARCHAR2(30) NOT NULL,
	ENROLL_DATE DATE DEFAULT SYSDATE
);

COMMENT ON COLUMN TB_USER.USER_NO     IS '사용자 번호';
COMMENT ON COLUMN TB_USER.USER_ID     IS '사용자 아이디';
COMMENT ON COLUMN TB_USER.USER_PW     IS '사용자 비밀번호';
COMMENT ON COLUMN TB_USER.USER_NAME   IS '사용자 이름';
COMMENT ON COLUMN TB_USER.ENROLL_DATE IS '사용자 가입일';


-- USER_NO 컬럼에 삽입될 시퀀스 생성
CREATE SEQUENCE SEQ_USER_NO NOCACHE;

-- 샘플 데이터 INSERT
INSERT INTO TB_USER 
VALUES(SEQ_USER_NO.NEXTVAL, 'user01', 'pass01', '유저일', DEFAULT );
INSERT INTO TB_USER 
VALUES(SEQ_USER_NO.NEXTVAL, 'user02', 'pass02', '유저이', DEFAULT );

SELECT * FROM TB_USER;

COMMIT;

--DROP TABLE TB_USER;

SELECT * FROM TB_USER
WHERE USER_ID = 'user01' AND USER_PW = 'pass01' AND USER_NAME = '유저일';

SELECT USER_NO, USER_ID, USER_PW, USER_NAME,
TO_CHAR(ENROLL_DATE, 'YYYY"년" MM"월" DD"일"') ENROLL_DATE
FROM TB_USER
ORDER BY 1;

SELECT USER_NO, USER_ID, USER_NAME
FROM TB_USER
WHERE USER_NAME LIKE '%유%';

SELECT USER_NO, USER_ID, USER_PW, USER_NAME,
TO_CHAR(ENROLL_DATE, 'YYYY"년" MM"월" DD"일"') ENROLL_DATE
FROM TB_USER
WHERE USER_NO =1;

DELETE FROM TB_USER WHERE USER_NO = 5;