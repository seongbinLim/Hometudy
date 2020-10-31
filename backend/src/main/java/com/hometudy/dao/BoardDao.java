package com.hometudy.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

import com.hometudy.dto.Board;

public interface BoardDao extends JpaRepository <Board, Integer>{
    public Board save(Board board);
    
    public Optional<Board> findBoardByBoardNo(int boardNo);
    public List<Board> findBoardBySubject(String subject);
    public List<Board> findBoardByWriter(String writer);

    public long count();

    public Optional<Board> deleteByBoardNo(int boardNo);
}
