
-- 10�� ȸ�� �ʱ�ȭ ����
use space;
show databases;
show tables;

insert into business (business_idx, business_name) values(1, 'it');
insert into business (business_idx, business_name) values(2, 'market');
insert into business (business_idx, business_name) values(3, 'media');
insert into business (business_idx, business_name) values(4, 'etc');

shows  columns from 'table �̸�';	
insert into members values 
('hwiwon.kang@ktds.com','hwiwon123','���ֿ�','01011111111','kt ds', 'G',0);

insert into members values 
('nahyun.kim@ktds.com','nahyun123','�質��','01022222222','kt ds', 'G',0);

insert into members values 
('minji.kim@ktds.com','minji123','�����','01033333333','kt ds', 'G',0);

insert into members values 
('minhyun.kim@ktds.com','minhyun123','�����','01044444444','kt ds', 'G',0);

insert into members values 
('sookyung.kim@ktds.com','sookyung123','�����','01055555555','kt ds', 'G',0);

insert into members values 
('eunbyul.kim@ktds.com','eunbyul123','������','01066666666','kt ds', 'G',0);

insert into members values 
('jitak.kim@ktds.com','jitak123','������','01077777777','kt ds', 'G',0);

insert into members values 
('hyungjun.kim@ktds.com','hyunhjun123','������','01088888888','kt ds', 'G',0);

insert into members values 
('gunjoo.ra@ktds.com','gunjoo123','�����','01099999999','kt ds', 'G',0);

insert into members values 
('seonghyun.park@ktds.com','seonghyun123','�ڽ���','01012222222','kt ds', 'G',0);

insert into members values 
('jihyun.park@ktds.com','jihyun123','������','01013333333','kt ds', 'G',0);

insert into members values 
('haewon.park@ktds.com','haewon123','������','01014444444','kt ds', 'G',0);

insert into members values 
('sunkyung.an@ktds.com','sunkyung123','�ȼ���','01015555555','kt ds', 'G',0);

insert into members values 
('donghyun.lee@ktds.com','123456','�̵���','01016666666','kt ds', 'G',0);

insert into members values 
('jaejin.lee@ktds.com','jaejin123','������','01017777777','kt ds', 'G',0);

insert into members values 
('hokyung.lee@ktds.com','hokyung123','��ȣ��','01018888888','kt ds', 'G',0);

insert into members values 
('sunghan.jeun@ktds.com','sunghan123','������','01019999999','kt ds', 'G',0);

insert into members values 
('hana.jeong@ktds.com','123456','���ϳ�','01021111111','kt ds', 'G',0);

insert into members values 
('hyunyeong.jeong@ktds.com','hyunyeong123','������','01023333333','kt ds', 'G',0);

insert into members values 
('woosuk.ji@ktds.com','woosuk123','���켮','01024444444','kt ds', 'G',0);

insert into members values 
('sunhyun.kim@kt.com','sunhyun123','�輱��','01025555555','kt', 'G',0);

insert into members values 
('junhyung.kim@kt.com','junhyung123','������','01026666666','kt', 'G',0);

insert into members values 
('hanseul.kim@kt.com','hanseul123','���ѽ�','01027777777','kt', 'G',0);

insert into members values 
('sejin.park@kt.com','sejin123','�ڼ���','01028888888','kt', 'G',0);

insert into members values 
('somang.park@kt.com','somang123','�ڼҸ�','01029999999','kt', 'G',0);

insert into members values 
('jaehee.park@kt.com','jaehee123','������','01031111111','kt', 'G',0);

insert into members values 
('hyunjun.park@kt.com','hyunjun123','������','01032222222','kt', 'G',0);

insert into members values 
('cheonjoo.yun@kt.com','cheonjoo123','��õ��','01034444444','kt', 'G',0);

insert into members values 
('seongjun.lee@kt.com','seongjun123','�̽���','01035555555','kt', 'G',0);

insert into members values 
('yeong.hwangbo@kt.com','yeong123','Ȳ����','01036666666','kt', 'G',0);
commit;
/*
	������ ���� ����
*/
rollback;
select * from members;
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

