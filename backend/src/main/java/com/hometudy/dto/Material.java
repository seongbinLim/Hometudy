package com.hometudy.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import lombok.Data;
import java.io.Serializable;
@Entity
@Data
public class Material implements Serializable{
	@Id
	@JoinColumn(name="roomNo")
	int roomNo;
	@Id
	String filename;
	String src;
	@JoinColumn(name="uid")
	String uid;
}
