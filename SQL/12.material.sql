USE homestudy;
DROP TABLE IF EXISTS material;


create TABLE material(
roomNo int REFERENCES room(roomNo),
filename varchar(45),
src varchar(100),
uid VARCHAR(30) REFERENCES user(uid)
); 