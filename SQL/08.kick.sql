USE homestudy;
DROP TABLE IF EXISTS kick;


create TABLE kick(
uid varchar(30) REFERENCES user(uid), 
roomNo INT REFERENCES room(roomNo),
day date

);