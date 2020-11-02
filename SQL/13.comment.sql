USE homestudy;
DROP TABLE IF EXISTS comment;


create TABLE comment(
commentNo int AUTO_INCREMENT PRIMARY KEY,
content TEXT(20000), 
day Date NOT NULL,
boardNo int REFERENCES board(boardNo),
uid VARCHAR(30) REFERENCES user(uid)
); 