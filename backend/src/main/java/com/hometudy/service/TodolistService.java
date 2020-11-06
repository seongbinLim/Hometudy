package com.hometudy.service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.hometudy.dao.GoalDao;
import com.hometudy.dao.TodoListDao;
import com.hometudy.dto.Goal;
import com.hometudy.dto.Todolist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodolistService {
    @Autowired
    TodoListDao todolistDao;

    @Autowired
    GoalDao goalDao;

    public Todolist getTodolist(int todolistno) {
        Optional<Todolist> todolist = todolistDao.findTodolistByTodolistno(todolistno);
        if(!todolist.isPresent()) {
            return null;
        }
        return todolist.get();
    }

    public List<Todolist> getTodolistByUid(String uid) {
        return todolistDao.findTodolistByUid(uid);
    }
    public List<Todolist> getTodolistByRoomnoAndUid(int roomno, String uid) {
        return todolistDao.findTodolistByRoomnoAndUid(roomno, uid);
    }

    public List<Todolist> getTodolistToday(int roomno) {
        LocalDate date = LocalDate.now();
        List<Todolist> list = todolistDao.findTodoListByRoomnoAndDay(roomno, date);

        return list;
    }
    public Todolist insert(Todolist todolist) {
        return todolistDao.save(todolist);
    }

    public Todolist update(Todolist todolist) {
        return todolistDao.save(todolist);
    }

    public Optional<Todolist> delete(int todolistno) {
        List<Goal> goals = getTodolist(todolistno).getGoalList();
        for(Goal g : goals) {
            goalDao.delete(g);
        }

        return todolistDao.deleteByTodolistno(todolistno);
    }

    public Goal insertGoal(Goal goal) {
        if(getTodolist(goal.getTodolistno())==null)
            return null;
        return goalDao.save(goal);
    }

    public Goal updateGoal(Goal goal) {
        return goalDao.save(goal);
    }

    public Optional<Goal> deleteGoal(int goalno) {
        Goal del = goalDao.findGoalByGoalno(goalno);
        Todolist todolist = getTodolist(del.getTodolistno());
        todolist.getGoalList().remove(del);
        return goalDao.deleteByGoalno(goalno);
        
    }
}
