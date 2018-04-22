create database Tanks_War;
use Tanks_War;
create table users(id INT unsigned NOT NULL AUTO_INCREMENT, 
username varchar(30) NOT NULL,
password varchar(30) NOT NULL,
PRIMARY KEY(id)); 
create table tanks(tank_id INT unsigned NOT NULL AUTO_INCREMENT, 
speed INT unsigned NOT NULL, 
health INT unsigned NOT NULL,
damage INT unsigned,
range INT unsigned,
PRIMARY KEY(tank_id)); 
create table user_tank(user_id INT unsigned NOT NULL,
tank_id INT unsigned NOT NULL,
Foreign key (user_id) references users(id),
Foreign key (tank_id) references tanks(tank_id)
,UNIQUE (user_id,tank_id)); 
insert into users values(1,'seby_boss','parola');
