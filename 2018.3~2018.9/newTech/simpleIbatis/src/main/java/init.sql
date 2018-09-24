create database 3rdshop;

use 3rdshop;
create table clawer_url(
	id int(11) not null auto_increment,
	url varchar(512),
	PRIMARY KEY (id)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB;


INSERT INTO `clawer_url` (`id`, `url`) VALUES (1, 'pwh');
