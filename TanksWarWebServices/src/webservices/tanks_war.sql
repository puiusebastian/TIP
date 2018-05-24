create database Tanks_War;
use Tanks_War;
create table users(id INT unsigned NOT NULL AUTO_INCREMENT, 
username varchar(30) NOT NULL UNIQUE,
password varchar(30) NOT NULL,
CONSTRAINT PK_USERS PRIMARY KEY(id)); 
create table tanks(tank_id INT unsigned NOT NULL AUTO_INCREMENT, 
speed INT unsigned NOT NULL, 
health INT unsigned NOT NULL,
damage INT unsigned,
tank_range INT unsigned,
CONSTRAINT PK_TANKS PRIMARY KEY(tank_id)); 
create table user_tank(user_id INT unsigned NOT NULL,
tank_id INT unsigned NOT NULL,
UNIQUE (user_id,tank_id),
Foreign key (user_id) references users(id),
Foreign key (tank_id) references tanks(tank_id)); 
insert into users values(1,'seby_boss','parola');
ALTER TABLE tanks ADD tank_name VARCHAR(50);
insert into tanks values(1,4,220,50,280,'M3_Light');
insert into tanks values(2,2,1600,310,420,'XM5_Sheridan');
insert into tanks values(3,4,810,270,370,'Panzer_SPIC');
insert into tanks values(4,3,1050,370,360,'Commet');
insert into tanks values(5,3,1420,320,380,'STA_2');
insert into tanks values(6,7,1800,420,410,'TVP-T_50/51');
insert into tanks values(7,6,1700,530,380,'T-10');
insert into tanks values(8,4,2500,950,400,'VK_70.01(K)');
alter table users add name varchar(50);
alter table users add email varchar(50);
alter table users add age INT unsigned NOT NULL ; 
alter table users add Constraint age_CK check(age>=10 and age<100); 
UPDATE users
SET name = 'Sebastian Puiu', email = 'sebastianpuiu@gmail.com', age='23'
WHERE id=1;
create table tank_picked(user varchar(30),
tank INT unsigned NOT NULL,
CONSTRAINT user_pk PRIMARY key(user),
CONSTRAINT user_fk FOREIGN key (user) references users(username),
CONSTRAINT tank_fk FOREIGN key (tank) references tanks(tank_id));

ALTER table users add money INT unsigned NOT NULL;
ALTER table users add games_played INT unsigned NOT NULL;
ALTER table users add games_won INT unsigned NOT NULL;

ALTER table tanks add price INT unsigned NOT NULL;

UPDATE tanks set price='1000' where tank_id=1;
UPDATE tanks set price='1500' where tank_id=2;
UPDATE tanks set price='2500' where tank_id=3;
UPDATE tanks set price='2700' where tank_id=4;
UPDATE tanks set price='2800' where tank_id=5;
UPDATE tanks set price='3100' where tank_id=6;
UPDATE tanks set price='3500' where tank_id=7;
UPDATE tanks set price='4050' where tank_id=8;

