package com.hometudy.dao;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.hometudy.dto.Comment;
import com.hometudy.dto.Goal;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GoalDao extends JpaRepository<Goal, Integer> {
    public Goal save(Goal goal);
    
    public List<Goal> findGoalByTodolistno(int boardno);
    public Goal findGoalByGoalno(int goalno);
    @Transactional
    public Optional<Goal> deleteByGoalno(int goalno);
    @Transactional
    public Optional<Goal> deleteByTodolistno(int todolistno);
    @Transactional
    public void delete(Goal goal);  
}
