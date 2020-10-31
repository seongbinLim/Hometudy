package com.hometudy.dto;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import java.io.Serializable;
import lombok.Data;

@Entity
@Data
public class Todolist implements Serializable {
	@Id
	@JoinColumn(name="roomNo")
	int roomNo;

	@Id
	@JoinColumn(name="uid")
	String uid;

	@Id
	LocalDateTime day;
	
	String goalList;
	String done;
	int studytime;
	int attendance;
	String filename;

}
