package com.hometudy.service;

import java.util.List;
import java.util.Optional;

import com.hometudy.dao.BoardDao;
import com.hometudy.dto.Board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class BoardService {
    @Autowired
    BoardDao boardDao;
    
    public Optional<Board> getBoard(int boardNo) {
        return boardDao.findBoardByBoardNo(boardNo);
    }
    public long count() {
        return boardDao.count();
    }

    public Page<Board> findBoardByPage(int pageNo) {
        PageRequest pageRequest = PageRequest.of(pageNo, 10);
        return boardDao.findAll(pageRequest);  
    }

    public List<Board> findBoardBySubject(String subject) {
        return boardDao.findBoardBySubject(subject);  
    }

    public List<Board> findBoardByWriter(String writer) {
        return boardDao.findBoardByWriter(writer);  
    }

    public Board insert(Board board) {
        return boardDao.save(board);
    }

    public Board update(Board board) {
        return boardDao.save(board);
    }

    public Optional<Board> delete(int boardNod) {
        return boardDao.deleteByBoardNo(boardNod);
    }
}
