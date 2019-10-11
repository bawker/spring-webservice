insert into posts (title, author, content, created_date, modified_date) values ('테스트1', 'test1@gmail.com', '테스트1의 본문', now(), now());
insert into posts (title, author, content, created_date, modified_date) values ('테스트2', 'test2@gmail.com', '테스트2의 본문', now(), now());
insert into ACCOUNTS (name, email, password, created_date, modified_date) values ('관리자', 'admin@admin.com', '1111', now(), now());
insert into USER (id, user_id, password, name, email, created_date, modified_date) values (1, 'psh', '1111', '박세현', 'test@test.com', now(), now());
insert into USER (id, user_id, password, name, email, created_date, modified_date) values (2, 'tester', '2222', '테스터', 'tester@test.com', now(), now());
insert into QNA (id, title, content, writer_id, created_date, modified_date) values (1, 'test', 'test Contents', '2', now(), now());
insert into QNA (id, title, content, writer_id, created_date, modified_date) values (2, 'psh 질문', '질문입니다.', '1', now(), now());