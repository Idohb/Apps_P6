insert into user(first_name,last_name,email,password,balance) values('Bodivann','KHEK','bodivann@mail.com','letmeinbodi',3000);
insert into user(first_name,last_name,email,password,balance) values('tata','titi','tata@mail.com','letmeintata',2500);
insert into user(first_name,last_name,email,password,balance) values('tutu','toto','tutu@mail.com','letmeintutu',2000);
commit;

insert into friend(user_iduser, user_iduser1) values (1, 3);
insert into friend(user_iduser, user_iduser1) values (3, 1);


insert into bank(iban, amount_bank, user_id) values('a',30000,1);
insert into bank(iban, amount_bank, user_id) values('b',20000,2);
insert into bank(iban, amount_bank, user_id) values('c',10000,3);
commit;


