USE homestudy;
DROP TABLE IF EXISTS board;

CREATE TABLE board(
	boardNo int AUTO_INCREMENT PRIMARY KEY,
	subject varchar(45) NOT NULL,
	content TEXT(20000), 
	day Date NOT NULL,
	writer VARCHAR(30) REFERENCES user(uid), 
	answer TEXT(20000), 
	answerer VARCHAR(30) REFERENCES user(uid),
	likes INT
);
