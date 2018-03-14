
-- 10명 회원 초기화 쿼리
use space;
show databases;
show tables;

insert into business (business_idx, business_name) values(1, 'it');
insert into business (business_idx, business_name) values(2, 'market');
insert into business (business_idx, business_name) values(3, 'media');
insert into business (business_idx, business_name) values(4, 'etc');

shows  columns from 'table 이름';	
insert into members values 
('hwiwon.kang@ktds.com','hwiwon123','강휘원','01011111111','kt ds', 'G',0);

insert into members values 
('nahyun.kim@ktds.com','nahyun123','김나현','01022222222','kt ds', 'G',0);

insert into members values 
('minji.kim@ktds.com','minji123','김민지','01033333333','kt ds', 'G',0);

insert into members values 
('minhyun.kim@ktds.com','minhyun123','김민현','01044444444','kt ds', 'G',0);

insert into members values 
('sookyung.kim@ktds.com','sookyung123','김수경','01055555555','kt ds', 'G',0);

insert into members values 
('eunbyul.kim@ktds.com','eunbyul123','김은별','01066666666','kt ds', 'G',0);

insert into members values 
('jitak.kim@ktds.com','jitak123','김지택','01077777777','kt ds', 'G',0);

insert into members values 
('hyungjun.kim@ktds.com','hyunhjun123','김형준','01088888888','kt ds', 'G',0);

insert into members values 
('gunjoo.ra@ktds.com','gunjoo123','라건주','01099999999','kt ds', 'G',0);

insert into members values 
('seonghyun.park@ktds.com','seonghyun123','박승현','01012222222','kt ds', 'G',0);

insert into members values 
('jihyun.park@ktds.com','jihyun123','박지현','01013333333','kt ds', 'G',0);

insert into members values 
('haewon.park@ktds.com','haewon123','박혜원','01014444444','kt ds', 'G',0);

insert into members values 
('sunkyung.an@ktds.com','sunkyung123','안선경','01015555555','kt ds', 'G',0);

insert into members values 
('donghyun.lee@ktds.com','123456','이동현','01016666666','kt ds', 'G',0);

insert into members values 
('jaejin.lee@ktds.com','jaejin123','이재진','01017777777','kt ds', 'G',0);

insert into members values 
('hokyung.lee@ktds.com','hokyung123','이호경','01018888888','kt ds', 'G',0);

insert into members values 
('sunghan.jeun@ktds.com','sunghan123','전성한','01019999999','kt ds', 'G',0);

insert into members values 
('hana.jeong@ktds.com','123456','정하나','01021111111','kt ds', 'G',0);

insert into members values 
('hyunyeong.jeong@ktds.com','hyunyeong123','정현영','01023333333','kt ds', 'G',0);

insert into members values 
('woosuk.ji@ktds.com','woosuk123','지우석','01024444444','kt ds', 'G',0);

insert into members values 
('sunhyun.kim@kt.com','sunhyun123','김선현','01025555555','kt', 'G',0);

insert into members values 
('junhyung.kim@kt.com','junhyung123','김준형','01026666666','kt', 'G',0);

insert into members values 
('hanseul.kim@kt.com','hanseul123','김한슬','01027777777','kt', 'G',0);

insert into members values 
('sejin.park@kt.com','sejin123','박세진','01028888888','kt', 'G',0);

insert into members values 
('somang.park@kt.com','somang123','박소망','01029999999','kt', 'G',0);

insert into members values 
('jaehee.park@kt.com','jaehee123','박재희','01031111111','kt', 'G',0);

insert into members values 
('hyunjun.park@kt.com','hyunjun123','박현준','01032222222','kt', 'G',0);

insert into members values 
('cheonjoo.yun@kt.com','cheonjoo123','윤천주','01034444444','kt', 'G',0);

insert into members values 
('seongjun.lee@kt.com','seongjun123','이승준','01035555555','kt', 'G',0);

insert into members values 
('yeong.hwangbo@kt.com','yeong123','황보영','01036666666','kt', 'G',0);
commit;
/*
	각각의 서비스 쿼리
*/
rollback;
select * from members;
-- * 회원등록
insert into members (MEMBER_ID, MEMBER_PW, NAME, MOBILE, EMAIL, ENTRY_DATE, GRADE, POSITION)
values ('user00', 'pass00', '이동순', '01025613350', 'donghyun3350@gmail.com', to_char(sysdate, 'yyyy.mm.dd'), 'A', '관리자2');
commit;

select glade from memebers where name = '이동순' and mobile = '01072463363'; 

-- * 아이디찾기
-- 이름과 핸드폰 번호로 아이디 찾기 쿼리
select MEMBER_ID from members where name = '이동순' and mobile = '01025613350';

-- * 비밀번호찾기
-- 아이디 핸드폰 번호로 비밀번호 찾기쿼리
select MEMBER_PW from members where MEMBER_ID = 'user00' and mobile = '01025613350';

-- * 내정보조회
select * from members where member_id = 'user00';
-- * 전체회원정보조회
select * from members;
-- * 내 정보변경
update members set MOBILE = '01125613363'
where MEMBER_ID = 'user00';
commit;
-- * 암호변경하기
update members set MEMBER_PW = 'pass22'
where MEMBER_ID = 'user00';
commit;
-- * 회원탈퇴
delete members where MEMBER_ID = 'user00'
commit;
-- * 게시판 쓰기
insert into boards values(boards_boardnumber_seq.nextval, '게시판00', '새롭게 추가한 글이예요!', '이동현', to_char(sysdate, 'yyyy.mm.dd'), 'user01');
commit;
-- * 게시판 수정하기
update boards set title = '게시판01', content = '수정해보았어요!'
where board_number = 6;
commit;
-- * 게시판에 댓글 쓰기 
insert into comments values(comments_commentnumber_seq.nextval, 6,'안녕 첫번째 댓글 달았어요', '이동현', to_char(sysdate, 'yyyy.mm.dd'));
-- * 이름으로 게시판 찾기
select * from boards where name = '이동현';
-- * 게시판 삭제
delete boards where board_number = '6';
commit;

