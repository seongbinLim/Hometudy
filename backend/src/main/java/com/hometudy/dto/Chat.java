package com.hometudy.dto;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Chat {
	@Id
	int roomNo;
	String chatFilename;
	String src;
}
