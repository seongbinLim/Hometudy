package com.hometudy.dto;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import java.io.Serializable;
import lombok.Data;

@Entity
@Data
public class Kick implements Serializable {
	@Id
	@JoinColumn(name="uid")
	String uid;
	@Id
	@JoinColumn(name="roomNo")
	int roomNo;
	LocalDateTime day;
}
