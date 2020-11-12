package com.hometudy.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PenaltyId implements Serializable {
    @Column(name="roomno", nullable=false)
    private int roomno;

    @Column(name="uid", nullable=false)
    private String uid;

    @Column(name="reason", nullable=false)
    private String reason;
}
