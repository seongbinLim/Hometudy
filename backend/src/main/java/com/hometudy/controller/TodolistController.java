package com.hometudy.controller;

import java.util.List;
import java.util.Optional;

import com.hometudy.dto.Goal;
import com.hometudy.dto.Todolist;
import com.hometudy.service.TodolistService;

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
@RequestMapping("/todolist")
public class TodolistController {
    @Autowired
    TodolistService todolistService;

    @GetMapping("/roomno/{roomno}")
    @ApiOperation(value = "스터디 룸의 오늘의 목표 반환")
    public ResponseEntity<List<Todolist>> getTodolistToday(@PathVariable int roomno) {
        List<Todolist> list = todolistService.getTodolistToday(roomno);

        if(list.isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("/todolistno/{todolistno}")
    @ApiOperation(value = "todolist no로 todolist 반환")
    public ResponseEntity<Todolist> getTodolist(@PathVariable int todolistno) {
        Todolist todolist = todolistService.getTodolist(todolistno);
        if(todolist==null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        return ResponseEntity.status(HttpStatus.OK).body(todolist);
    }

    @GetMapping("/uid/{uid}")
    @ApiOperation(value = "아이디로 todolist 반환")
    public ResponseEntity<List<Todolist>> getTodolistByUid(@PathVariable String uid) {   
        List<Todolist> list = todolistService.getTodolistByUid(uid);
        if(list.isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        return ResponseEntity.status(HttpStatus.OK).body(list);        
    }

    @PostMapping("/insert")
    @ApiOperation(value = "todolist 저장")
    public ResponseEntity<Todolist> insert(@RequestBody Todolist todolist) {
        Todolist insert = todolistService.insert(todolist);
        if(insert==null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        return ResponseEntity.status(HttpStatus.OK).body(insert);
    }

    @PutMapping("/update")
    @ApiOperation(value = "todolist 변경")
    public ResponseEntity<Todolist> update(@RequestBody Todolist todolist) {
        Todolist update = todolistService.update(todolist);
        if(update==null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        return ResponseEntity.status(HttpStatus.OK).body(update); 
    }

    @DeleteMapping("/delete/{todolistno}")
    @ApiOperation(value = "todolist 삭제")
    public ResponseEntity delete(@PathVariable int todolistno) {
        Optional<Todolist> delete = todolistService.delete(todolistno);
        if(!delete.isPresent())
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/goal/insert")
    @ApiOperation(value = "goal 저장")
    public ResponseEntity<Goal> insertGoal(@RequestBody Goal goal) {
        Goal insert = todolistService.insertGoal(goal);
        if(insert==null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        return ResponseEntity.status(HttpStatus.OK).body(insert);
    }

    @PutMapping("/goal/update") 
    @ApiOperation(value = "goal 변경")
    public ResponseEntity<Goal> updateGoal(@RequestBody Goal goal) {
        Goal update = todolistService.updateGoal(goal);
        if(update==null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        return ResponseEntity.status(HttpStatus.OK).body(update);
    }

    @DeleteMapping("/goal/delete/{goalno}")
    @ApiOperation(value = "goal 삭제")
    public ResponseEntity deleteGoal(@PathVariable int goalno) {
        Optional<Goal> delete = todolistService.deleteGoal(goalno);
        if(!delete.isPresent())
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        
        return new ResponseEntity(HttpStatus.OK);

    }
}
