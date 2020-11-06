package com.hometudy.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import lombok.Data;

@Entity
@Data
public class Goal {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    int goalno;
    String goal;
    boolean done;
    @JoinColumn(name="todolistno")
    int todolistno;
}
