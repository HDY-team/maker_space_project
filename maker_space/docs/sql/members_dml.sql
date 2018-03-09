
-- 10명 회원 초기화 쿼리
use space;
show databases;
show tables;

insert into business (business_idx, business_name) values(1, 'it');
insert into business (business_idx, business_name) values(2, 'media');
insert into business (business_idx, business_name) values(3, 'market');
insert into business (business_idx, business_name) values(4, 'etc');

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

