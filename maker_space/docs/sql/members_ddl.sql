--CREATE database space;
--DROP database example;

-- 1. 회원테이블 관련 총 1개
DROP TABLE IF EXISTS members;
-- 2. 사업테이블 관련 총 7개
SET foreign_key_checks = 0; -- ** 반드시 check = 0 후에 = 1 만들어주기! (제약 테이블 지우는 방법임.)
DROP TABLE IF EXISTS business;
DROP TABLE IF EXISTS business_boards;
DROP TABLE IF EXISTS scraps_boards;
DROP TABLE IF EXISTS select_boards;
DROP TABLE IF EXISTS my_idea_boards;
DROP TABLE IF EXISTS select_complete_boards;
DROP TABLE IF EXISTS hashtags;

-- 3. 꿀팁테이블 관련 총 4개
DROP TABLE IF EXISTS tip_boards;
DROP TABLE IF EXISTS tip_scraps_boards;
DROP TABLE IF EXISTS tip_my_boards;
DROP TABLE IF EXISTS tip_hashtags;
SET foreign_key_checks = 1;

-- => 총 16개 테이블 존재.
/*
 * 회원 테이블
 */

-- 회원 테이블
create table members (

    email varchar(40) NOT NULL PRIMARY KEY,
    password varchar(15) NOT NULL,
    name varchar(50) NOT NULL,
    mobile varchar(30) NOT NULL UNIQUE,
    company varchar(50) NOT NULL,
    grade varchar(1) NOT NULL,
    point int NOT NULL
) ENGINE = InnoDB;

/*
 * 사업 테이블
 */

-- 사업분야 테이블
create table business (

    business_idx int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    business_name varchar(50) NOT NULL
) ENGINE = InnoDB;

-- 사업종합게시판 테이블
create table business_boards (
    business_idx int NOT NULL,
    business_boards_idx int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    title varchar(255)NOT NULL,
    content varchar(255),
    result varchar(255),
    files varchar(255),
    hits int NOT NULL,
    email varchar(40) NOT NULL,
    write_date varchar(20) NOT NULL,
    name varchar(50) NOT NULL,
  	INDEX(business_idx),
  	FOREIGN KEY(business_idx) REFERENCES business(business_idx) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB;

-- 사업 나의 스크랩 게시판 테이블
create table scraps_boards (

    business_boards_idx int NOT NULL,
    INDEX(business_boards_idx),
  	FOREIGN KEY(business_boards_idx) REFERENCES business_boards(business_boards_idx) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB;

-- 사업 채택진행중 게시판 테이블
create table select_boards (

    business_boards_idx int NOT NULL,
    INDEX(business_boards_idx),
  	FOREIGN KEY(business_boards_idx) REFERENCES business_boards(business_boards_idx) ON DELETE CASCADE ON UPDATE CASCADE

) ENGINE = InnoDB;

-- 사업 아이디어 게시판 테이블
create table my_idea_boards (

    business_boards_idx int NOT NULL,
    INDEX(business_boards_idx),
  	FOREIGN KEY(business_boards_idx) REFERENCES business_boards(business_boards_idx) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB;

-- 사업 채택완료 게시판 테이블
create table select_complete_boards (

    business_boards_idx int NOT NULL,
    INDEX(business_boards_idx),
  	FOREIGN KEY(business_boards_idx) REFERENCES business_boards(business_boards_idx) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB; 

-- 사업 해시테그 테이블
create table hashtags (

    business_boards_idx int NOT NULL,
    hash_tag varchar(10),
    INDEX(business_boards_idx),
  	FOREIGN KEY(business_boards_idx) REFERENCES business_boards(business_boards_idx) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB; 

/*
 * 꿀팁 테이블
 */

-- 꿀팁 게시판 테이블
create table tip_boards (

    tip_idx int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    title varchar(255)NOT NULL,
    content varchar(255),
    result varchar(255),
    files varchar(255),
    hits int NOT NULL,
    scraps int NOT NULL,
    email varchar(40) NOT NULL UNIQUE,
    write_date varchar(20) NOT NULL,
    name varchar(50) NOT NULL
) ENGINE = InnoDB; 

-- 꿀팁 내아이디어 게시판 테이블
create table tip_scraps_boards (

    tip_idx int NOT NULL,
    INDEX(tip_idx),
  	FOREIGN KEY(tip_idx) REFERENCES tip_boards(tip_idx) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB;  

-- 꿀팁 나의 스크랩 게시판 테이블
create table tip_my_boards (

    tip_idx int NOT NULL,
    INDEX(tip_idx),
  	FOREIGN KEY(tip_idx) REFERENCES tip_boards(tip_idx) ON DELETE CASCADE ON UPDATE CASCADE

) ENGINE = InnoDB; 

-- 끌팁 해시테크 테이블
create table tip_hashtags (

    tip_idx int NOT NULL,
    hash_tag varchar(10),
    INDEX(tip_idx),
  	FOREIGN KEY(tip_idx) REFERENCES tip_boards(tip_idx) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB;  
