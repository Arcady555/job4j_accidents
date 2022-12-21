insert into users (username, enabled, password, authority_id)
values ('admin', true, '$2a$10$dcs2gthk3SKosa3m5eSyb.1fC4711VtbB3Tkwpjf.q8dKE/uWqz/e',
(select id from authorities where authority = 'ROLE_ADMIN'));