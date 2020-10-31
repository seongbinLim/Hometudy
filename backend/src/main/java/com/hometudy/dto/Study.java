package com.hometudy.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import lombok.Data;

@Entity
@Table(name="study")
@Data
public class Study implements Serializable {
	@Id
	@JoinColumn(name="roomNo")
	int roomNo;

	@Id
	@JoinColumn(name="uid")
	String uid;
}
