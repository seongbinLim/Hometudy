package com.hometudy.controller;

import java.util.List;

import com.hometudy.dto.Kick;
import com.hometudy.service.KickService;

import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/kick")
public class KickController {
    @Autowired
    KickService kickService;

    @GetMapping("/{roomno}/{uid}")
    @ApiOperation(value = "방번호와 아이디로 강퇴여부 반환")
    public ResponseEntity<Kick> getKick(@PathVariable int roomno, @PathVariable String uid) {
        Kick kick  = kickService.getKick(roomno, uid);
        if(kick == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(kick);
    }

    @GetMapping("/uid/{uid}")
    @ApiOperation(value = "아이디로 검색하여 강퇴된 리스트 반환")
    public ResponseEntity<List<Kick>> getKick(@PathVariable String uid) {
        List<Kick> list  = kickService.getKickByUid(uid);
        if(list.size()==0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("/roomno/{roomno}")
    @ApiOperation(value = "스터디 방에 강퇴된 리스트 반환")
    public ResponseEntity<List<Kick>> getKick(@PathVariable int roomno) {
        List<Kick> list  = kickService.getKickByRoomno(roomno);
        if(list.size()==0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @PostMapping
    @ApiOperation(value = "강퇴된 사람 저장")
    public ResponseEntity<Kick> insertKick(@RequestBody Kick kick) {
        Kick insert = kickService.save(kick);
        if(insert == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        return ResponseEntity.status(HttpStatus.OK).body(insert);
    }

    @PutMapping
    @ApiOperation(value = "방번호와 아이디로 강퇴여부 반환")
    public ResponseEntity<Kick> updateKick(@RequestBody Kick kick) {
        Kick update = kickService.save(kick);
        if(update == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        return ResponseEntity.status(HttpStatus.OK).body(update);
    }

    @DeleteMapping
    @ApiOperation(value = "강퇴 이력 삭제")
    public ResponseEntity deleteKick(@RequestBody Kick kick) {
        try {
            kickService.delete(kick);
        } catch(Exception e) {
            return new ResponseEntity(HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity(HttpStatus.OK);
    }
}
