/*
    테이블 초기화
    MEMBER, ROLE, MEMBER_ROLE 테이블이 존재할 경우 DROP 한다.
*/
DROP TABLE IF EXISTS MEMBER;
DROP TABLE IF EXISTS ROLE;
DROP TABLE IF EXISTS MEMBER_ROLE;

/*
    회원 테이블 생성
    MEMBER(회원) 테이블을 생성한다.
*/
CREATE TABLE IF NOT EXISTS MEMBER (
    ID        BIGINT        NOT NULL AUTO_INCREMENT UNIQUE,
    EMAIL       VARCHAR(255) NOT NULL UNIQUE,
    PASSWORD    VARCHAR(255) NOT NULL,
    NICKNAME    VARCHAR(255) NOT NULL UNIQUE,
    CREATED_DT  DATETIME      DEFAULT CURRENT_TIMESTAMP,
    MODIFIED_DT DATETIME      DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (ID)
);

/*
    역할 테이블 생성
    ROLE(역할) 테이블을 생성한다.
*/
CREATE TABLE IF NOT EXISTS ROLE (
    ID        BIGINT      NOT NULL AUTO_INCREMENT UNIQUE,
    NAME        VARCHAR(20) NOT NULL UNIQUE,
    PRIMARY KEY (ID)
);

/*
    회원-역할 테이블 생성
    MEMBER(회원)와 ROLE(역할) 테이블을 ID로 연결하는 JOIN 테이블을 생성한다.
*/
CREATE TABLE IF NOT EXISTS MEMBER_ROLE (
    M_ID BIGINT NOT NULL,
    R_ID BIGINT NOT NULL,
    PRIMARY KEY (M_ID, R_ID),
    FOREIGN KEY (M_ID) REFERENCES MEMBER(ID),
    FOREIGN KEY (R_ID) REFERENCES ROLE(ID)
);