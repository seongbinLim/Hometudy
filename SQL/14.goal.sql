USE homestudy;
DROP TABLE IF EXISTS goal;


create TABLE goal(
goalNo INT AUTO_INCREMENT PRIMARY KEY,
todolistNo INT REFERENCES todolist(todolistNo),
goal varchar(30),
done BOOLEAN
);