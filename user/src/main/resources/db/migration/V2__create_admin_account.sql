-- password = admin
insert into users(login, password, role)
values ('admin',
        '$argon2id$v=19$m=15360,t=2,p=1$ZmFyZWw$7RodV4cxBGiYbSSclRirBczZz7RUlifQhswExmrkYpw',
        'ADMIN')