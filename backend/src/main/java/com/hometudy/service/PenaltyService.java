package com.hometudy.service;

import java.util.List;
import java.util.Optional;

import com.hometudy.dao.PenaltyDao;
import com.hometudy.dao.PenaltyDao;
import com.hometudy.dto.KickId;
import com.hometudy.dto.Penalty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PenaltyService {
    @Autowired
    PenaltyDao penaltyDao;

    public List<Penalty> getPenalty(int roomno, String uid) {
        List<Penalty> Penalty = penaltyDao.findPenaltyByIdRoomnoAndIdUid(roomno, uid);
        return Penalty;
    }

    public List<Penalty> getPenaltyByUid(String uid) {
        List<Penalty> list = penaltyDao.findPenaltyByIdUid(uid);
        return penaltyDao.findPenaltyByIdUid(uid);
    }

    public Page<Penalty> findPenaltyByPage(String uid, int pageNo) {
        //Pageable pageable = PageRequest.of(pageNo,5,Sort.by("reason").descending());
        PageRequest pageRequest = PageRequest.of(pageNo, 5);
        Page<Penalty> page = penaltyDao.findPenaltyByIdUid(uid, pageRequest);
        System.out.println(page.getContent().toString());
        return penaltyDao.findAll(pageRequest);
    }

    public long count() {
        return penaltyDao.count();
    }
    public List<Penalty> getPenaltyByRoomno(int roomno) {
        return penaltyDao.findPenaltyByIdRoomno(roomno);
    }

    public Penalty save(Penalty Penalty) {
        
        return penaltyDao.save(Penalty);
    }

    public void delete(Penalty Penalty) {
        penaltyDao.delete(Penalty);
    }
}
