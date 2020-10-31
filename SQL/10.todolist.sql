USE homestudy;
DROP TABLE IF EXISTS todolist;


create TABLE todolist(
roomNo INT REFERENCES room(roomNo),
uid varchar(30) REFERENCES user(uid),
day DATE,
goalList TEXT(10000),
done TEXT(10000),
studytime INT,
attendance INT,
filename TEXT(20000)
);