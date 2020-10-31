USE homestudy;
DROP TABLE IF EXISTS USER;

CREATE TABLE USER(
	uid VARCHAR(30) PRIMARY KEY,
	email VARCHAR(100),
	password VARCHAR(30),
	name VARCHAR(10),
	phone VARCHAR(20),
	money INT
);


INSERT INTO USER(uid,email,PASSWORD,NAME,phone,money) VALUES ("ssafy","wlsgid2378@hanmail.com","ssafy","이진향","01097422378",50000);
