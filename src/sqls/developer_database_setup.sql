create database schultzco;

drop user 'schultz'@'%' ;
drop user 'schultz'@'localhost' ;

create user 'schultz'@'%' identified by 'co';
create user 'schultz'@'localhost' identified by 'co';

grant all privileges on schultzco.* to 'schultz'@'%' ;
grant all privileges on schultzco.* to 'schultz'@'localhost' ;

flush privileges;

-- be sure to set table engine
-- create table t(...) ENGINE = INNODB