package com.hometudy.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import org.hibernate.annotations.CreationTimestamp;
import lombok.Data;

@Entity
@Data
public class Kick {
	@EmbeddedId
	private KickId id;

	@CreationTimestamp
	LocalDateTime day;
}
