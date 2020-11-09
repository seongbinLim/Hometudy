package com.hometudy.service;

import java.util.List;
import java.util.Optional;

import com.hometudy.dao.KickDao;
import com.hometudy.dto.Kick;
import com.hometudy.dto.KickId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KickService {
    @Autowired
    KickDao kickDao;

    public Kick getKick(int roomno, String uid) {
        KickId kickId = new KickId(roomno, uid);
        Optional<Kick> kick = kickDao.findKickById(kickId);
        if(!kick.isPresent()) {
            return null;
        }
        return kick.get();
    }

    public List<Kick> getKickByUid(String uid) {
        return kickDao.findKickByIdUid(uid);
    }

    public List<Kick> getKickByRoomno(int roomno) {
        return kickDao.findKickByIdRoomno(roomno);
    }

    public Kick save(Kick kick) {
        
        return kickDao.save(kick);
    }

    public void delete(Kick kick) {
        kickDao.delete(kick);
    }
}
