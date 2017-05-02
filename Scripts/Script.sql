DROP DATABASE IF EXISTS ncs_erp;
CREATE DATABASE ncs_erp;
USE ncs_erp;

GRANT USAGE ON *.* TO 'ncs_user'@'localhost' IDENTIFIED BY 'rootroot';
DROP USER 'ncs_user'@'localhost';
UPDATE mysql.user SET Password=PASSWORD('rootroot') WHERE User='ncs_user' AND Host='%' ;
GRANT DELETE ON ncs_erp.* TO 'ncs_user'@'%' ;
GRANT INSERT ON ncs_erp.* TO 'ncs_user'@'%' ;
GRANT SELECT ON ncs_erp.* TO 'ncs_user'@'%' ;
GRANT UPDATE ON ncs_erp.* TO 'ncs_user'@'%' ;

if db_id("ncs_erp") is not null print "db EXISTS";
   
SELECT schema_name FROM information_schema.schemata WHERE schema_name = 'ncs_user';

CREATE TABLE title (
	tcode INT(11) 		NOT NULL, 
	tname VARCHAR(10),
	PRIMARY KEY (tcode)
);

CREATE TABLE employee (
	eno		 INT(11)	 NOT NULL,
	ename	 VARCHAR(20) NOT NULL,	 
	salary	 INT(11),
	dno		 INT(11),
	gentder	 TINYINT(1),
	joindate DATE,
	title	 INT(11),
	PRIMARY KEY (eno)
);

CREATE TABLE department (
	dcode	INT(11)	 NOT NULL,
	dname	CHAR(10) NOT NULL,	
	floor	INT(11),
	PRIMARY KEY(dcode)
);

USE ncs_erp;
SHOW tables;
SELECT * FROM department;
SELECT * FROM employee;
SELECT * FROM title;
