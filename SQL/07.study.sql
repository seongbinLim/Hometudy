USE homestudy;
DROP TABLE IF EXISTS study;


create TABLE study(
roomNo int REFERENCES room(roomNo),
uid VARCHAR(30) REFERENCES user(uid)
);