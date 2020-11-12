package com.hometudy.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class KickId implements Serializable {
    @Column(name="roomno", nullable=false)
    private int roomno;

    @Column(name="uid", nullable=false)
    private String uid;
}