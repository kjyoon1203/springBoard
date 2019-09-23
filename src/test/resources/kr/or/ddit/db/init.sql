-- 해당 테이블은 테스트 계정에만 있기 때문에
-- 개발자가 실수로 운영디비 설정을 사용해도 해당 sql이 정상적으로 실행되지 않으면서
-- 이후 등장하는 sql들은 실행되지 않는다.
select * from not_exists_in_prd;

-- users 테이블 데이터 전체 지우기
-- delete users;		-- 복구 가능(아카이브 로그를 남긴다)
truncate table users; 	-- 복구 불가(아카이브 로그를 남기지 않는다)

Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME) values ('brown','브라운','c6347b73d5b1f7c77f8be828ee3e871c819578f23779c7d5e082ae2b36a44',to_date('2019/01/28','YYYY/MM/DD'),'곰',null,null,null,'brown.png','E:\A_TeachingMaterial\7.JspSpring\레인저스사진\brown.png');
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME) values ('cony','코니','de1153428acef7fd7b999f1227d4882146bd7ea16b595b43bf5090d41d3637',to_date('2019/01/28','YYYY/MM/DD'),'오리',null,null,null,'cony.png','E:\A_TeachingMaterial\7.JspSpring\레인저스사진\cony.png');
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME) values ('sally','샐리','4aeaaaccd26ed685e4e3c563bcdb1f9d1dabd77f1b7b819625679936648d49c9',to_date('2019/01/28','YYYY/MM/DD'),'토끼',null,null,null,'sally.png','E:\A_TeachingMaterial\7.JspSpring\레인저스사진\sally.png');
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME) values ('james','제임스','james1234',to_date('2019/01/28','YYYY/MM/DD'),'사람',null,null,null,'james.png','E:\A_TeachingMaterial\7.JspSpring\레인저스사진\james.png');
Insert into USERS (USERID,USERNM,PASS,REG_DT,ALIAS,ADDR1,ADDR2,ZIPCODE,FILENAME,REALFILENAME) values ('moon','문','moon1234',to_date('2019/01/28','YYYY/MM/DD'),'달',null,null,null,'moon.png','E:\A_TeachingMaterial\7.JspSpring\레인저스사진\moon.png');

commit;