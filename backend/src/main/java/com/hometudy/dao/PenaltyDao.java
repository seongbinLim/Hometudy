package com.hometudy.dao;

import com.hometudy.dto.Kick;
import com.hometudy.dto.KickId;
import com.hometudy.dto.Penalty;
import com.hometudy.dto.PenaltyId;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

public interface PenaltyDao extends JpaRepository<Penalty, PenaltyId> {
    public Penalty save(Penalty penalty);
    public long count();
    @Transactional
    public void delete(Penalty penalty);
    public List<Penalty> findPenaltyByIdRoomno(int roomno);
    public List<Penalty> findPenaltyByIdUid(String uid);
    public List<Penalty> findPenaltyByIdRoomnoAndIdUid(int roomno, String uid);
    public Page<Penalty> findPenaltyByIdUid(String uid, Pageable pageable);
    public Page<Penalty> findAll(Pageable pageable);
}
