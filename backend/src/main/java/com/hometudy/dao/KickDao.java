package com.hometudy.dao;

import com.hometudy.dto.Kick;
import com.hometudy.dto.KickId;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

public interface KickDao extends JpaRepository<Kick, KickId> {
    public Kick save(Kick kick);
    @Transactional
    public void delete(Kick kick);
    public List<Kick> findKickByIdRoomno(int roomno);
    public List<Kick> findKickByIdUid(String uid);
    public Optional<Kick> findKickById(KickId kcikId);
}
