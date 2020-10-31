package com.hometudy.dto;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Board {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int boardNo;
	String subject;
	String context;
	LocalDateTime day;
	String writer;
	String answer;
	String answerer;
	int like;
}
