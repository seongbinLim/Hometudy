package com.hometudy.service;

import java.util.List;
import java.util.Optional;

import com.hometudy.dao.BoardDao;
import com.hometudy.dao.CommentDao;
import com.hometudy.dto.Board;
import com.hometudy.dto.Comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class BoardService {
    @Autowired
    BoardDao boardDao;
    @Autowired
    CommentDao commentDao;
    
    public Optional<Board> getBoard(int boardno) {
        return boardDao.findBoardByBoardno(boardno);
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
        Board old = boardDao.findBoardByBoardno(board.getBoardno()).get();
        board.setDay(old.getDay());
        return boardDao.save(board);
    }

    public Optional<Board> delete(int boardno) {
        List<Comment> comments = getBoard(boardno).get().getComments();
        for(Comment c : comments)
            commentDao.delete(c);
        return boardDao.deleteByBoardno(boardno);
    }

    public Comment updateComment(Comment comment) {
        return commentDao.save(comment);
    }

    public Optional<Comment> deleteComment(int commentno) {
        Comment del = commentDao.findCommentByCommentno(commentno);
        Board board = getBoard(del.getBoardno()).get();
        board.getComments().remove(del);
        return commentDao.deleteByCommentno(commentno);
    }

    public Comment insertComment(Comment comment) {
        return commentDao.save(comment);
    }
}
