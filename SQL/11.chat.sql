USE homestudy;
DROP TABLE IF EXISTS chat;


create TABLE chat(
roomNo int REFERENCES room(roomNo),
chatFilename varchar(45),
src varchar(100)
);
