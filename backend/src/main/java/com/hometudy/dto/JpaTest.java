package com.hometudy.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

//@Entity
public class JpaTest {
    @Id
    //@GeneratedValue(strategy=GenerationType.IDENTITY) //mysql 에게 위임
    @GeneratedValue(strategy=GenerationType.SEQUENCE) //mysql 에게 위임
    private Long id;



    @Column(name = "name", nullable = false)
    private String username;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDate createdate; // 년월일
    private LocalDateTime createdateTime; // 년월일시간

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @Lob
    private String desciprtion;

    @Transient
    private int temp; //NOT 매핑

}
