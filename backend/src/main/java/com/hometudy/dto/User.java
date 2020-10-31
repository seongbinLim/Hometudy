package com.hometudy.dto;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonInclude;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter @Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {
	@Id
	String uid;
	String email;
	String password;
	String name;
	String phone;
	int money;
}
