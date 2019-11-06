-- insert into posts (title, author, content, created_date, modified_date) values ('테스트1', 'test1@gmail.com', '테스트1의 본문', now(), now());
-- insert into posts (title, author, content, created_date, modified_date) values ('테스트2', 'test2@gmail.com', '테스트2의 본문', now(), now());
-- insert into ACCOUNTS (name, email, password, created_date, modified_date) values ('관리자', 'admin@admin.com', '1111', now(), now());
-- insert into USER (id, user_id, password, name, email, created_date, modified_date) values (1, 'psh', '1111', '박세현', 'test@test.com', now(), now());
-- insert into USER (id, user_id, password, name, email, created_date, modified_date) values (2, 'tester', '2222', '테스터', 'tester@test.com', now(), now());
-- insert into QNA (id, title, content, writer_id, created_date, modified_date) values (1, 'test', 'test Contents', '2', now(), now());
-- insert into QNA (id, title, content, writer_id, created_date, modified_date) values (2, 'psh 질문', '질문입니다.', '1', now(), now());
insert into ETO_ACCOUNTS (id, email, password, name, phone, subway_by_line, station_cd, station_nm, created_date, modified_date)
        values (1, 'psh',  '$2a$10$UzReJL9HPkj.o9TsyqfRRO.nqZxwEDNyOB5TbqovwFWfAuYk6EhXO', '박세현', '01029181370', '01호선', '0150', '서울역', now(), now());
insert into ETO_ACCOUNTS_ROLE (id, role_name, roles_id, created_date, modified_date) values (1, 'ADMIN', 1, now(), now());
insert into ETO_ACCOUNTS (id, email, password, name, phone, subway_by_line, station_cd, station_nm, created_date, modified_date)
        values (2, 'test',  '$2a$10$UzReJL9HPkj.o9TsyqfRRO.nqZxwEDNyOB5TbqovwFWfAuYk6EhXO', 'test', '01029181370', '01호선', '0150', '서울역', now(), now());
insert into ETO_ACCOUNTS_ROLE (id, role_name, roles_id, created_date, modified_date) values (2, 'ADMIN', 2, now(), now());

insert into ETO_MENU (id, description, file_name, file_type, price, title, upload_path, eto_accounts_id, created_date, modified_date)
        values (1, '설명란', 'test2.jpg', 'image/jpeg', 10000, '1번상품', '/static/image/', '1', now(), now());
insert into ETO_MENU (id, description, file_name, file_type, price, title, upload_path, eto_accounts_id, created_date, modified_date)
        values (2, '설명란', 'test1.jpg', 'image/jpeg', 20000, '2번상품', '/static/image/', '1', now(), now());
insert into ETO_MENU (id, description, file_name, file_type, price, title, upload_path, eto_accounts_id, created_date, modified_date)
        values (3, '설명란', 'test2.jpg', 'image/jpeg', 15000, '3번상품', '/static/image/', '2', now(), now());