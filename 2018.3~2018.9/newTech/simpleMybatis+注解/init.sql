create table user(
	id int auto_increment primary key,
    name varchar(20) not null,
	password varchar(20) not null
)engine=innoDB;
 
insert into user(name,password) values("hjy","123456");
insert into user(name,password) values("hjy","666666");
