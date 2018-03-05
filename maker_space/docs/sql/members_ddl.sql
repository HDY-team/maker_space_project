CREATE database space;
--DROP database example;

-- 1. ȸ�����̺� ���� �� 1��
DROP TABLE IF EXISTS members;
-- 2. ������̺� ���� �� 7��
SET foreign_key_checks = 0; -- ** �ݵ�� check = 0 �Ŀ� = 1 ������ֱ�! (���� ���̺� ����� �����.)
DROP TABLE IF EXISTS business;
DROP TABLE IF EXISTS business_boards;
DROP TABLE IF EXISTS scraps_boards;
DROP TABLE IF EXISTS select_boards;
DROP TABLE IF EXISTS my_idea_boards;
DROP TABLE IF EXISTS select_complete_boards;
DROP TABLE IF EXISTS hashtags;

-- 3. �������̺� ���� �� 4��
DROP TABLE IF EXISTS tip_boards;
DROP TABLE IF EXISTS tip_scraps_boards;
DROP TABLE IF EXISTS tip_my_idea_boards;
DROP TABLE IF EXISTS tip_hashtags;
-- 4. ��� �и����� ���̺� ���� �� 4�� 
DROP TABLE IF EXISTS it_boards;
DROP TABLE IF EXISTS media_boards;
DROP TABLE IF EXISTS market_boards;
DROP TABLE IF EXISTS etc_boards;
SET foreign_key_checks = 1;

-- => �� 16�� ���̺� ����.

-- ���̺� �ʱ�ȭ: ���� ���� (mysql������  �ȵ�..)
--drop table members cascade constraint purge;
--drop table business cascade constraint purge;
--drop table comments cascade constraint purge;

/*
 * ȸ�� ���̺�
 */

-- ȸ�� ���̺�
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
 * ��� ���̺�
 */

-- ����о� ���̺�
create table business (

    business_idx int NOT NULL AUTO_INCREMENT
); 

alter table business
add (constraint business_businessidx_pk primary key(business_idx));

-- ������հԽ��� ���̺�
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

-- ��� ���� ��ũ�� �Խ��� ���̺�
create table scraps_boards (

    business_boards_idx int NOT NULL
); 

alter table scraps_boards
add (constraint scrapsboards_businessboardsidx_fk FOREIGN KEY (business_boards_idx) REFERENCES business_boards(business_boards_idx) ON DELETE CASCADE ON UPDATE CASCADE);

-- ��� ä�������� �Խ��� ���̺�
create table select_boards (

    business_boards_idx int NOT NULL
); 

alter table select_boards
add (constraint selectsboards_businessboardsidx_fk FOREIGN KEY (business_boards_idx) REFERENCES business_boards(business_boards_idx) ON DELETE CASCADE ON UPDATE CASCADE);

-- ��� ���̵�� �Խ��� ���̺�
create table my_idea_boards (

    business_boards_idx int NOT NULL
); 

alter table my_idea_boards
add (constraint myideaboards_businessboardsidx_fk FOREIGN KEY (business_boards_idx) REFERENCES business_boards(business_boards_idx) ON DELETE CASCADE ON UPDATE CASCADE);

-- ��� ä�ÿϷ� �Խ��� ���̺�
create table select_complete_boards (

    business_boards_idx int NOT NULL
); 

alter table select_complete_boards
add (constraint selectcompleteboards_businessboardsidx_fk FOREIGN KEY (business_boards_idx) REFERENCES business_boards(business_boards_idx) ON DELETE CASCADE ON UPDATE CASCADE);

-- ��� �ؽ��ױ� ���̺�
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
 * ���� ���̺�
 */

-- ���� �Խ��� ���̺�
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

-- ���� �����̵�� �Խ��� ���̺�
create table tip_scraps_boards (

    tip_idx int NOT NULL
); 

alter table tip_scraps_boards
add (constraint tipscrapsboards_tipidx_fk FOREIGN KEY (tip_idx) REFERENCES tip_boards(tip_idx) ON DELETE CASCADE ON UPDATE CASCADE);

-- ���� ���� ��ũ�� �Խ��� ���̺�
create table tip_my_idea_boards (

    tip_idx int NOT NULL
); 

alter table tip_my_idea_boards
add (constraint tipmyideaboards_tipidx_fk FOREIGN KEY (tip_idx) REFERENCES tip_boards(tip_idx) ON DELETE CASCADE ON UPDATE CASCADE);

-- ���� �ؽ���ũ ���̺�
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
 * ��� ������ ���̺�
 */

-- ������� �Խ��� ���̺�
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

-- �̵�� �Խ��� ���̺�
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

-- ����/������ ���̺�
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

-- ��Ÿ ���̺�
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