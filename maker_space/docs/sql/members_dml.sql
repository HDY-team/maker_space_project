
-- 10�� ȸ�� �ʱ�ȭ ����
use space;
show databases;
show tables;

insert into business (business_idx, business_name) values(1, 'it');
insert into business (business_idx, business_name) values(2, 'media');
insert into business (business_idx, business_name) values(3, 'market');
insert into business (business_idx, business_name) values(4, 'etc');

-- * ȸ�����
insert into members (MEMBER_ID, MEMBER_PW, NAME, MOBILE, EMAIL, ENTRY_DATE, GRADE, POSITION)
values ('user00', 'pass00', '�̵���', '01025613350', 'donghyun3350@gmail.com', to_char(sysdate, 'yyyy.mm.dd'), 'A', '������2');
commit;

select glade from memebers where name = '�̵���' and mobile = '01072463363'; 

-- * ���̵�ã��
-- �̸��� �ڵ��� ��ȣ�� ���̵� ã�� ����
select MEMBER_ID from members where name = '�̵���' and mobile = '01025613350';

-- * ��й�ȣã��
-- ���̵� �ڵ��� ��ȣ�� ��й�ȣ ã������
select MEMBER_PW from members where MEMBER_ID = 'user00' and mobile = '01025613350';

-- * ��������ȸ
select * from members where member_id = 'user00';
-- * ��üȸ��������ȸ
select * from members;
-- * �� ��������
update members set MOBILE = '01125613363'
where MEMBER_ID = 'user00';
commit;
-- * ��ȣ�����ϱ�
update members set MEMBER_PW = 'pass22'
where MEMBER_ID = 'user00';
commit;
-- * ȸ��Ż��
delete members where MEMBER_ID = 'user00'
commit;
-- * �Խ��� ����
insert into boards values(boards_boardnumber_seq.nextval, '�Խ���00', '���Ӱ� �߰��� ���̿���!', '�̵���', to_char(sysdate, 'yyyy.mm.dd'), 'user01');
commit;
-- * �Խ��� �����ϱ�
update boards set title = '�Խ���01', content = '�����غ��Ҿ��!'
where board_number = 6;
commit;
-- * �Խ��ǿ� ��� ���� 
insert into comments values(comments_commentnumber_seq.nextval, 6,'�ȳ� ù��° ��� �޾Ҿ��', '�̵���', to_char(sysdate, 'yyyy.mm.dd'));
-- * �̸����� �Խ��� ã��
select * from boards where name = '�̵���';
-- * �Խ��� ����
delete boards where board_number = '6';
commit;

