package com.hometudy.dto;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import java.io.Serializable;
import lombok.Data;

@Entity
@Data
public class Likes implements Serializable {
	@Id
	@JoinColumn(name="boardNo")
	int boardNo;
	@Id
	@JoinColumn(name="uid")
	String uid;
}
