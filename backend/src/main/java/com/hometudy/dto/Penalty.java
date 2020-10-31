package com.hometudy.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import java.io.Serializable;
import lombok.Data;

@Entity
@Data
public class Penalty implements Serializable {
	@Id
	@JoinColumn(name="roomNo")
	int roomNo;
	@Id
	@JoinColumn(name="uid")
	String uid;
	String reason;
}
