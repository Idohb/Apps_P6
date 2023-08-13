insert into user(first_name,last_name,email,password,balance,roles) values('Bodivann','KHEK','bodivann@mail.com','$2a$10$QOXE9W4UC1tt/8/qUU/2QeNyLxiibfxo6y8n2y9Cpqp3o16GZ/FL2',3000,'ROLE_USER');
insert into user(first_name,last_name,email,password,balance,roles) values('tata','titi','tata@mail.com','$2a$10$ybUDJ3nI6wQIrDkN5RGqMOB9BRRRyNTk2BF2p703IBGNw66D5oCHq',2500,'ROLE_USER');
insert into user(first_name,last_name,email,password,balance,roles) values('tutu','toto','tutu@mail.com','$2a$10$7zqt5K1dyY6Nooyc1jiUYOyr/pC4au3QD/U/fuetRsS8oKmkOHKQi',2000,'ROLE_USER');
commit;

insert into friend(user_iduser, user_iduser1) values (1, 3);
insert into friend(user_iduser, user_iduser1) values (3, 1);


insert into bank(iban, amount_bank, user_id) values('a',30000,1);
insert into bank(iban, amount_bank, user_id) values('b',20000,2);
insert into bank(iban, amount_bank, user_id) values('c',10000,3);
commit;


