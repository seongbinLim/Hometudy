package com.hometudy.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Account {
	@Id
	@GeneratedValue
	int accountId;
	int savedMoney;
	int penaltyCount;
}
