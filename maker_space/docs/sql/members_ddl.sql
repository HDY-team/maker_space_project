--CREATE database space;
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
/*
 * ȸ�� ���̺�
 */

-- ȸ�� ���̺�
create table members (

    email varchar(40) NOT NULL PRIMARY KEY,
    password varchar(15) NOT NULL,
    name varchar(50) NOT NULL,
    mobile varchar(30) NOT NULL UNIQUE,
    company varchar(50) NOT NULL,
    grade varchar(1) NOT NULL,
    point int NOT NULL
) ENGINE = InnoDB;; 

/*
 * ��� ���̺�
 */

-- ����о� ���̺�
create table business (

    business_idx int NOT NULL AUTO_INCREMENT PRIMARY KEY
) ENGINE = InnoDB;; 

-- ������հԽ��� ���̺�
create table business_boards (

    business_idx int NOT NULL,
    business_boards_idx int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    title varchar(255)NOT NULL,
    content varchar(255),
    result varchar(255),
    files varchar(255),
    hits int NOT NULL,
    email varchar(40) NOT NULL,
    write_date varchar(12) NOT NULL,
  	INDEX(business_idx),
  	FOREIGN KEY(business_idx) REFERENCES business(business_idx) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB;

-- ��� ���� ��ũ�� �Խ��� ���̺�
create table scraps_boards (

    business_boards_idx int NOT NULL,
    INDEX(business_boards_idx),
  	FOREIGN KEY(business_boards_idx) REFERENCES business_boards(business_boards_idx) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB;

-- ��� ä�������� �Խ��� ���̺�
create table select_boards (

    business_boards_idx int NOT NULL,
    INDEX(business_boards_idx),
  	FOREIGN KEY(business_boards_idx) REFERENCES business_boards(business_boards_idx) ON DELETE CASCADE ON UPDATE CASCADE

) ENGINE = InnoDB;

-- ��� ���̵�� �Խ��� ���̺�
create table my_idea_boards (

    business_boards_idx int NOT NULL,
    INDEX(business_boards_idx),
  	FOREIGN KEY(business_boards_idx) REFERENCES business_boards(business_boards_idx) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB;

-- ��� ä�ÿϷ� �Խ��� ���̺�
create table select_complete_boards (

    business_boards_idx int NOT NULL,
    INDEX(business_boards_idx),
  	FOREIGN KEY(business_boards_idx) REFERENCES business_boards(business_boards_idx) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB; 

-- ��� �ؽ��ױ� ���̺�
create table hashtags (

    business_boards_idx int NOT NULL,
    hash_tag varchar(10),
    INDEX(business_boards_idx),
  	FOREIGN KEY(business_boards_idx) REFERENCES business_boards(business_boards_idx) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB; 

/*
 * ���� ���̺�
 */

-- ���� �Խ��� ���̺�
create table tip_boards (

    tip_idx int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    title varchar(255)NOT NULL,
    content varchar(255),
    result varchar(255),
    files varchar(255),
    hits int NOT NULL,
    scraps int NOT NULL,
    email varchar(40) NOT NULL UNIQUE,
    write_date varchar(12) NOT NULL
) ENGINE = InnoDB; 

-- ���� �����̵�� �Խ��� ���̺�
create table tip_scraps_boards (

    tip_idx int NOT NULL,
    INDEX(tip_idx),
  	FOREIGN KEY(tip_idx) REFERENCES tip_boards(tip_idx) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB;  

-- ���� ���� ��ũ�� �Խ��� ���̺�
create table tip_my_idea_boards (

    tip_idx int NOT NULL,
    INDEX(tip_idx),
  	FOREIGN KEY(tip_idx) REFERENCES tip_boards(tip_idx) ON DELETE CASCADE ON UPDATE CASCADE

) ENGINE = InnoDB; 

-- ���� �ؽ���ũ ���̺�
create table tip_hashtags (

    tip_idx int NOT NULL,
    hash_tag1 varchar(10),
    hash_tag2 varchar(10),
    hash_tag3 varchar(10),
    hash_tag4 varchar(10),
    hash_tag5 varchar(10),
    INDEX(tip_idx),
  	FOREIGN KEY(tip_idx) REFERENCES tip_boards(tip_idx) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB;  

/*
 * ��� ������ ���̺�
 */

-- ������� �Խ��� ���̺�
create table it_boards (

    it_boards_idx int NOT NULL AUTO_INCREMENT PRIMARY KEY,	
    title varchar(255)NOT NULL,
    content varchar(255),
    result varchar(255),
    files varchar(255),
    hits int NOT NULL,
    email varchar(40) NOT NULL,
    write_date varchar(12) NOT NULL
) ENGINE = InnoDB; 

-- �̵�� �Խ��� ���̺�
create table media_boards (

    media_boards_idx int NOT NULL AUTO_INCREMENT PRIMARY KEY,	
    title varchar(255)NOT NULL,
    content varchar(255),
    result varchar(255),
    files varchar(255),
    hits int NOT NULL,
    email varchar(40) NOT NULL,
    write_date varchar(12) NOT NULL
) ENGINE = InnoDB;  

-- ����/������ ���̺�
create table market_boards (

    market_boards_idx int NOT NULL AUTO_INCREMENT PRIMARY KEY,	
    title varchar(255)NOT NULL,
    content varchar(255),
    result varchar(255),
    files varchar(255),
    hits int NOT NULL,
    email varchar(40) NOT NULL,
    write_date varchar(12) NOT NULL
) ENGINE = InnoDB;  

-- ��Ÿ ���̺�
create table etc_boards (

    etc_boards_idx int NOT NULL AUTO_INCREMENT PRIMARY KEY,	
    title varchar(255)NOT NULL,
    content varchar(255),
    result varchar(255),
    files varchar(255),
    hits int NOT NULL,
    email varchar(40) NOT NULL,
    write_date varchar(12) NOT NULL
) ENGINE = InnoDB;  
