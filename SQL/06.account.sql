USE homestudy;
DROP TABLE IF EXISTS account;


create TABLE account(
	accountId INT  PRIMARY KEY,
	savedMoney INT,
	penaltyCount INT
);