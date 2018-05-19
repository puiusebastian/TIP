create database Tanks_War;
use Tanks_War;
create table users(id INT unsigned NOT NULL AUTO_INCREMENT, 
username varchar(30) NOT NULL,
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
insert into tanks values(1,60,220,50,280,'M3_Light');
insert into tanks values(2,50,1600,310,420,'XM5_Sheridan');
insert into tanks values(3,55,810,270,370,'Panzer_SPIC');
insert into tanks values(4,51,1050,370,360,'Commet');
insert into tanks values(5,55,1420,320,380,'STA-2');
insert into tanks values(6,60,1800,420,410,'TVP-T_50/51');
insert into tanks values(7,50,1700,530,380,'T-10');
insert into tanks values(8,33,2500,950,400,'VK_70.01(K)');
alter table users add name varchar(50);
alter table users add email varchar(50);
alter table users add age INT unsigned NOT NULL ; 
alter table users add Constraint age_CK check(age>=10 and age<100); 
UPDATE users
SET name = 'Sebastian Puiu', email = 'sebastianpuiu@gmail.com', age='23'
WHERE id=1;
create table tank_picked(user varchar(30),
tank INT unsigned NOT NULL,
--UNIQUE (user,tank),
CONSTRAINT user_pk PRIMARY key(user),
CONSTRAINT user_fk FOREIGN key (user) references users(username),
CONSTRAINT tank_fk FOREIGN key (tank) references tanks(tank_id));

