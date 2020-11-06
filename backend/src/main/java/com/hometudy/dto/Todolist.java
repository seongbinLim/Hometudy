package com.hometudy.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;

import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import lombok.Data;

@Entity
@Data
public class Todolist implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int todolistno;

	//@JoinColumn(name="roomno")
	int roomno;

	//@JoinColumn(name="uid")
	String uid;

	@CreationTimestamp
	LocalDate day;

	int attendance; //0 : 불참, 1 : 참석

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name="todolistno")
	List<Goal> goalList = new ArrayList<>();
	
}
