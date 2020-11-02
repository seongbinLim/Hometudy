package com.hometudy.dao;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.hometudy.dto.Comment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentDao extends JpaRepository<Comment, Integer> {
    public Comment save(Comment comment);
    
    public List<Comment> findCommentByBoardno(int boardno);
    public Comment findCommentByCommentno(int commentno);
    @Transactional
    public Optional<Comment> deleteByCommentno(int commentno);
    public void delete(Comment comment);  
}
