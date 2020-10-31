USE homestudy;
DROP TABLE IF EXISTS penalty;


create TABLE penalty(
roomNo INT REFERENCES room(roomNo),
uid varchar(30) REFERENCES user(uid),
reason varchar(60)
);
