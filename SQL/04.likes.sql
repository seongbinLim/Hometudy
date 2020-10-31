USE homestudy;
DROP TABLE IF EXISTS likes;


create table likes(
boardNo int AUTO_INCREMENT PRIMARY KEY,
uid VARCHAR(30) REFERENCES user(uid)
);