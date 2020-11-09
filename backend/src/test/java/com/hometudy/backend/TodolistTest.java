package com.hometudy.backend;

import static org.junit.Assert.assertEquals;

import java.util.List;

import com.hometudy.dao.GoalDao;
import com.hometudy.dto.Goal;
import com.hometudy.dto.Todolist;
import com.hometudy.service.TodolistService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TodolistTest {
    @Autowired
    private TodolistService todolistService;

    @Autowired
    private GoalDao goalDao;

    @Test
    public void verifyMappingTodolistAndGoal() {
        List<Todolist> list = todolistService.getTodolistByUid("ssafy");

        assertEquals(list.size(), 0);

    }

    @Test
    public void verifyInsert() {
        
         Todolist todolist = new Todolist(); todolist.setAttendance(0);
         todolist.setRoomno(1); todolist.setUid("ssafy");
         
         todolistService.insert(todolist); int todolistno =
         todolistService.getTodolistToday(1).get(0).getTodolistno(); Goal goal = new
         Goal(); goal.setGoal("pizza 먹기"); goal.setDone(false);
         goal.setTodolistno(todolistno); todolistService.insertGoal(goal);
         

        String todolistUid = todolistService.getTodolist(1).getUid();
        todolist = todolistService.getTodolist(1);
        List<Goal> goallist =  todolist.getGoalList();
        int goalsizeByTodolist = goallist.size();
        int goalsizeByGoalDao = goalDao.findGoalByTodolistno(1).size();

        assertEquals(todolistUid, "ssafy");
        assertEquals(goalsizeByTodolist, goalsizeByGoalDao);
    }
}
