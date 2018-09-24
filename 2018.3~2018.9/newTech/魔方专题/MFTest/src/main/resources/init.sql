create database mfDemo;

use mfDemo;
create table mf_substatus(
	id int(11) not null auto_increment,
	status varchar(128) not null,
	parent_status varchar(128) null default null,
	score int(4) not null,
	move_step varchar(128) null default null,
	PRIMARY KEY (id)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB;
