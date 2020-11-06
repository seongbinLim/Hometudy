package com.hometudy.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.hometudy.dto.Board;
import com.hometudy.dto.Todolist;

public interface TodoListDao extends JpaRepository<Todolist, Integer> {
    public Todolist save(Todolist todolist);
    
    public Optional<Todolist> findTodolistByTodolistno(int todolistno);
    public List<Todolist> findTodolistByUid(String uid);
    public List<Todolist> findTodolistByRoomnoAndUid(int roomno, String uid);
    public List<Todolist> findTodoListByRoomnoAndDay(int roomno, LocalDate day);
    public List<Todolist> findTodolistByRoomnoAndUidAndDay(int roomno, String uid, LocalDate day);

    @Transactional
    public Optional<Todolist> deleteByTodolistno(int todolistno);
}
