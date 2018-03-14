-- 1. ȸ�����̺� ���� �� 1��
-- 2. ������̺� ���� �� 7��
SET foreign_key_checks = 0; -- ** �ݵ�� check = 0 �Ŀ� = 1 ������ֱ�! (���� ���̺� ����� �����.)
DROP TABLE IF EXISTS members;
DROP TABLE IF EXISTS business;
SET foreign_key_checks = 0;
DROP TABLE IF EXISTS business_boards;
DROP TABLE IF EXISTS select_boards;
DROP TABLE IF EXISTS select_complete_boards;
DROP TABLE IF EXISTS hashtags;

-- 3. �������̺� ���� �� 4��

DROP TABLE IF EXISTS tip_boards;
DROP TABLE IF EXISTS tip_scraps_boards;
DROP TABLE IF EXISTS tip_hashtags;
DROP TABLE IF EXISTS tip_boards;
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
) ENGINE = InnoDB;

/*
 * ��� ���̺�
 */

-- ����о� ���̺�
create table business (

    business_idx int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    business_name varchar(50) NOT NULL
) ENGINE = InnoDB;

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
    write_date varchar(20) NOT NULL,
    name varchar(50) NOT NULL,
  	INDEX(business_idx),
  	FOREIGN KEY(business_idx) REFERENCES business(business_idx) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB;

-- ��� ���� ä�������� �Խ��� ���̺�
create table select_boards (

    business_boards_idx int NOT NULL,
    email varchar(40) NOT NULL,
    INDEX(business_boards_idx),
  	FOREIGN KEY(business_boards_idx) REFERENCES business_boards(business_boards_idx) ON DELETE CASCADE ON UPDATE CASCADE,
  	 INDEX(email),
  	FOREIGN KEY(email) REFERENCES members(email) ON DELETE CASCADE ON UPDATE CASCADE

) ENGINE = InnoDB;
-- ��� ä�ÿϷ� �Խ��� ���̺�
create table select_complete_boards (

    business_boards_idx int NOT NULL,
    email varchar(40) NOT NULL,
    INDEX(business_boards_idx),
  	FOREIGN KEY(business_boards_idx) REFERENCES business_boards(business_boards_idx) ON DELETE CASCADE ON UPDATE CASCADE,
 	 INDEX(email),
  	FOREIGN KEY(email) REFERENCES members(email) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB; 

-- ��� �ؽ��ױ� ���̺�
create table hashtags (

    business_boards_idx int NOT NULL,
    hash_tag varchar(10),
    email varchar(40) NOT NULL,
    INDEX(business_boards_idx),
  	FOREIGN KEY(business_boards_idx) REFERENCES business_boards(business_boards_idx) ON DELETE CASCADE ON UPDATE CASCADE,
  	 INDEX(email),
  	FOREIGN KEY(email) REFERENCES members(email) ON DELETE CASCADE ON UPDATE CASCADE
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
    email varchar(40) NOT NULL ,
    write_date varchar(20) NOT NULL,
    name varchar(50) NOT NULL
) ENGINE = InnoDB; 

-- ���� ���� ��ũ�� �Խ��� ���̺�
create table tip_scraps_boards (

    tip_idx int NOT NULL,
    email varchar(50) NOT NULL,
    INDEX(tip_idx),
  	FOREIGN KEY(tip_idx) REFERENCES tip_boards(tip_idx) ON DELETE CASCADE ON UPDATE CASCADE,
  	INDEX(email),
  	FOREIGN KEY(email) REFERENCES members(email) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB;  

-- ���� �ؽ���ũ ���̺�
create table tip_hashtags (

    tip_idx int NOT NULL,
    email varchar(50) NOT NULL,
    hash_tag varchar(10),
    INDEX(tip_idx),
  	FOREIGN KEY(tip_idx) REFERENCES tip_boards(tip_idx) ON DELETE CASCADE ON UPDATE CASCADE,
  	INDEX(email),
  	FOREIGN KEY(email) REFERENCES members(email) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB;  
