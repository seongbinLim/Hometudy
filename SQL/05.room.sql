USE homestudy;
DROP TABLE IF EXISTS room;


create TABLE room(
roomNo INT PRIMARY KEY AUTO_INCREMENT,
savedMoney INT,
roomName varchar(48),
headCount INT,
description varchar(1000),
rule TEXT(2000),
keyword TEXT(2000),
banUser TEXT(2000)
);