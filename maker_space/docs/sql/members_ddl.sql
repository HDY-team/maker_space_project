CREATE database space;
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
DROP TABLE IF EXISTS tip_my_idea_boards;
DROP TABLE IF EXISTS tip_hashtags;
-- 4. 사업 분리설계 테이블 관련 총 4개 
DROP TABLE IF EXISTS it_boards;
DROP TABLE IF EXISTS media_boards;
DROP TABLE IF EXISTS market_boards;
DROP TABLE IF EXISTS etc_boards;
SET foreign_key_checks = 1;

-- => 총 16개 테이블 존재.

-- 테이블 초기화: 제약 무관 (mysql에서는  안됨..)
--drop table members cascade constraint purge;
--drop table business cascade constraint purge;
--drop table comments cascade constraint purge;

/*
 * 회원 테이블
 */

-- 회원 테이블
create table members (

    email varchar(40) NOT NULL,
    password varchar(15) NOT NULL,
    name varchar(50) NOT NULL,
    mobile varchar(30) NOT NULL,
    company varchar(50) NOT NULL,
    grade varchar(1) NOT NULL,
    point int NOT NULL
); 

alter table members
add (constraint members_memberid_pk primary key(email));
alter table members
add (constraint members_mobile_uk unique(mobile));
/*
 * 사업 테이블
 */

-- 사업분야 테이블
create table business (

    business_idx int NOT NULL AUTO_INCREMENT
); 

alter table business
add (constraint business_businessidx_pk primary key(business_idx));

-- 사업종합게시판 테이블
create table business_boards (

    business_idx int NOT NULL,
    business_boards_idx int NOT NULL AUTO_INCREMENT,
    title varchar(255)NOT NULL,
    content varchar(255),
    result varchar(255),
    files varchar(255),
    hits int NOT NULL,
    email varchar(40) NOT NULL,
    write_date varchar(12) NOT NULL
); 

alter table business_boards
add (constraint businessboards_businessboardsidx_pk primary key(business_boards_idx));

alter table business_boards
add (constraint businessboards_businessidx_fk FOREIGN KEY (business_idx) REFERENCES business(business_idx) ON DELETE CASCADE ON UPDATE CASCADE);

-- 사업 나의 스크랩 게시판 테이블
create table scraps_boards (

    business_boards_idx int NOT NULL
); 

alter table scraps_boards
add (constraint scrapsboards_businessboardsidx_fk FOREIGN KEY (business_boards_idx) REFERENCES business_boards(business_boards_idx) ON DELETE CASCADE ON UPDATE CASCADE);

-- 사업 채택진행중 게시판 테이블
create table select_boards (

    business_boards_idx int NOT NULL
); 

alter table select_boards
add (constraint selectsboards_businessboardsidx_fk FOREIGN KEY (business_boards_idx) REFERENCES business_boards(business_boards_idx) ON DELETE CASCADE ON UPDATE CASCADE);

-- 사업 아이디어 게시판 테이블
create table my_idea_boards (

    business_boards_idx int NOT NULL
); 

alter table my_idea_boards
add (constraint myideaboards_businessboardsidx_fk FOREIGN KEY (business_boards_idx) REFERENCES business_boards(business_boards_idx) ON DELETE CASCADE ON UPDATE CASCADE);

-- 사업 채택완료 게시판 테이블
create table select_complete_boards (

    business_boards_idx int NOT NULL
); 

alter table select_complete_boards
add (constraint selectcompleteboards_businessboardsidx_fk FOREIGN KEY (business_boards_idx) REFERENCES business_boards(business_boards_idx) ON DELETE CASCADE ON UPDATE CASCADE);

-- 사업 해시테그 테이블
create table hashtags (

    business_boards_idx int NOT NULL,
    hash_tag1 varchar(10),
    hash_tag2 varchar(10),
    hash_tag3 varchar(10),
    hash_tag4 varchar(10),
    hash_tag5 varchar(10)
); 

alter table hashtags
add (constraint hashtags_businessboardsidx_fk FOREIGN KEY (business_boards_idx) REFERENCES business_boards(business_boards_idx) ON DELETE CASCADE ON UPDATE CASCADE);

/*
 * 꿀팁 테이블
 */

-- 꿀팁 게시판 테이블
create table tip_boards (

    tip_idx int NOT NULL AUTO_INCREMENT,
    title varchar(255)NOT NULL,
    content varchar(255),
    result varchar(255),
    files varchar(255),
    hits int NOT NULL,
    scraps int NOT NULL,
    email varchar(40) NOT NULL,
    write_date varchar(12) NOT NULL
); 

alter table tip_boards
add (constraint tipboards_tipidx_pk primary key(tip_idx));

-- 꿀팁 내아이디어 게시판 테이블
create table tip_scraps_boards (

    tip_idx int NOT NULL
); 

alter table tip_scraps_boards
add (constraint tipscrapsboards_tipidx_fk FOREIGN KEY (tip_idx) REFERENCES tip_boards(tip_idx) ON DELETE CASCADE ON UPDATE CASCADE);

-- 꿀팁 나의 스크랩 게시판 테이블
create table tip_my_idea_boards (

    tip_idx int NOT NULL
); 

alter table tip_my_idea_boards
add (constraint tipmyideaboards_tipidx_fk FOREIGN KEY (tip_idx) REFERENCES tip_boards(tip_idx) ON DELETE CASCADE ON UPDATE CASCADE);

-- 끌팁 해시테크 테이블
create table tip_hashtags (

    tip_idx int NOT NULL,
    hash_tag1 varchar(10),
    hash_tag2 varchar(10),
    hash_tag3 varchar(10),
    hash_tag4 varchar(10),
    hash_tag5 varchar(10)
); 

alter table tip_hashtags
add (constraint tiphashtags_tipidx_fk FOREIGN KEY (tip_idx) REFERENCES tip_boards(tip_idx) ON DELETE CASCADE ON UPDATE CASCADE);

/*
 * 사업 각각의 테이블
 */

-- 정보통신 게시판 테이블
create table it_boards (

    it_boards_idx int NOT NULL AUTO_INCREMENT,	
    title varchar(255)NOT NULL,
    content varchar(255),
    result varchar(255),
    files varchar(255),
    hits int NOT NULL,
    email varchar(40) NOT NULL,
    write_date varchar(12) NOT NULL
); 

alter table it_boards
add (constraint itboards_itboardsidx_pk primary key(it_boards_idx));

-- 미디어 게시판 테이블
create table media_boards (

    media_boards_idx int NOT NULL AUTO_INCREMENT,	
    title varchar(255)NOT NULL,
    content varchar(255),
    result varchar(255),
    files varchar(255),
    hits int NOT NULL,
    email varchar(40) NOT NULL,
    write_date varchar(12) NOT NULL
); 

alter table media_boards
add (constraint mediaboards_mediaboardsidx_pk primary key(media_boards_idx));

-- 영업/마켓팅 테이블
create table market_boards (

    market_boards_idx int NOT NULL AUTO_INCREMENT,	
    title varchar(255)NOT NULL,
    content varchar(255),
    result varchar(255),
    files varchar(255),
    hits int NOT NULL,
    email varchar(40) NOT NULL,
    write_date varchar(12) NOT NULL
); 

alter table market_boards
add (constraint marketboards_marketboardsidx_pk primary key(market_boards_idx));

-- 기타 테이블
create table etc_boards (

    etc_boards_idx int NOT NULL AUTO_INCREMENT,	
    title varchar(255)NOT NULL,
    content varchar(255),
    result varchar(255),
    files varchar(255),
    hits int NOT NULL,
    email varchar(40) NOT NULL,
    write_date varchar(12) NOT NULL
); 

alter table etc_boards
add (constraint etcboards_etcboardsidx_pk primary key(etc_boards_idx));