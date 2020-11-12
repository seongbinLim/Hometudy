package com.hometudy.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.hometudy.dto.KickId;
import com.hometudy.dto.Penalty;
import com.hometudy.dto.PenaltyId;
import com.hometudy.service.PenaltyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/penalty")
public class PenaltyController {
    @Autowired
    PenaltyService PenaltyService;

    @GetMapping("/{roomno}/{uid}")
    @ApiOperation(value = "방번호와 아이디로 패널티여부 반환")
    public ResponseEntity<List<Penalty>> getPenalty(@PathVariable int roomno, @PathVariable String uid) {
        List<Penalty> Penalty  = PenaltyService.getPenalty(roomno, uid);
        if(Penalty == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(Penalty);
    }

    @GetMapping("/uid/{uid}")
    @ApiOperation(value = "아이디로 검색하여 패널티 리스트 반환")
    public ResponseEntity<List<Penalty>> getPenalty(@PathVariable String uid) {
        List<Penalty> list  = PenaltyService.getPenaltyByUid(uid);
        
        if(list.size()==0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("/uid/{uid}/{pageno}")
    @ApiOperation(value = "아이디로 검색하여 패널티 리스트 반환")
    public ResponseEntity<HashMap<String, Object>> getPenaltyByPageno(@PathVariable String uid, @PathVariable int pageno) {
        HashMap<String, Object> obj = new HashMap<>();
        Page<Penalty> list  = PenaltyService.findPenaltyByPage(uid, pageno-1);
        int totalPage = list.getTotalPages();
        obj.put("totalPage", totalPage);
        obj.put("penaltylist", list);
        
        if(list==null) 
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        
        return ResponseEntity.status(HttpStatus.OK).body(obj);
    }

    @GetMapping("/roomno/{roomno}")
    @ApiOperation(value = "스터디방에 패널티 이유, 아이디 리스트 반환")
    public ResponseEntity<List<Penalty>> getPenalty(@PathVariable int roomno) {
        List<Penalty> list  = PenaltyService.getPenaltyByRoomno(roomno);
        if(list.size()==0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @PostMapping
    @ApiOperation(value = "패널티 저장")
    public ResponseEntity<Penalty> insertPenalty(@RequestBody Map<String, String> object) {

        Penalty insert = new Penalty();
        String uid = object.get("uid");
        String roomno = object.get("roomno");
        String reason = object.get("reason");
        insert.setId(new PenaltyId(Integer.parseInt(roomno), uid, reason));

        PenaltyService.save(insert);
        if(insert == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        return ResponseEntity.status(HttpStatus.OK).body(insert);
    }

    @PutMapping
    @ApiOperation(value = "패널티 resaon 수정")
    public ResponseEntity<Penalty> updatePenalty(@RequestBody Penalty Penalty) {
        Penalty update = PenaltyService.save(Penalty);
        if(update == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        return ResponseEntity.status(HttpStatus.OK).body(update);
    }

    @DeleteMapping
    @ApiOperation(value = "패널티 이력 삭제")
    public ResponseEntity deletePenalty(@RequestBody Penalty Penalty) {
        try {
            PenaltyService.delete(Penalty);
        } catch(Exception e) {
            return new ResponseEntity(HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity(HttpStatus.OK);
    }
}
