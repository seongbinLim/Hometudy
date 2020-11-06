USE homestudy;
DROP TABLE IF EXISTS todolist;


create TABLE todolist(
todolistNo INT AUTO_INCREMENT PRIMARY KEY,
roomNo INT REFERENCES room(roomNo),
uid varchar(30) REFERENCES user(uid),
day DATE,
attendance INT
);