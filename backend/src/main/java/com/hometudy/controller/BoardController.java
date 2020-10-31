package com.hometudy.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import com.hometudy.dto.Board;
import com.hometudy.service.BoardService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
@RequestMapping("/board")
public class BoardController {
    @Autowired
    BoardService boardService;

    @GetMapping("/no/{boardNo}")
    @ApiOperation(value = "게시판 반환")
    public ResponseEntity<Board> getBoard(@PathVariable int boardNo) {
        Optional<Board> board = boardService.getBoard(boardNo);

        if(!board.isPresent())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(board.get());
        return ResponseEntity.status(HttpStatus.OK).body(board.get());
    }

    @GetMapping("/page/{pageNo}")
    @ApiOperation(value = "게시판 리스트 반환") //페이지네이션
    public ResponseEntity<HashMap<String, Object>> getBoardByPage(@PathVariable int pageNo) {
        HashMap<String, Object> obj = new HashMap<>();
        int totalPage = (int) boardService.count();
        Page<Board> list = boardService.findBoardByPage(pageNo);
        obj.put("totalPage", totalPage);
        obj.put("boardList", list);

        if(list == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        return ResponseEntity.status(HttpStatus.OK).body(obj);
    }

    @ApiOperation(value = "특정 게시글을 삭제한다.", response = Board.class)
    @DeleteMapping("/{boardNo}")
    public ResponseEntity deleteBoard(@PathVariable int boardNo) throws Exception {
        Optional<Board> isDeleted = boardService.delete(boardNo);
        if(!isDeleted.isPresent())
            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        return new ResponseEntity(HttpStatus.OK);
    }
    
    @ApiOperation(value = "새로운 게시글을 생성한다.", response = String.class)
    @PostMapping("/create")
    public ResponseEntity<String> createBoard(@RequestBody Board board) throws Exception {
		if (boardService.insert(board) != null) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		}
		return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "특정 게시글을 수정한다.", response = Board.class)
    @PutMapping("/{boardNo}")
    public ResponseEntity<String> updateBoard(@RequestBody Board board) throws Exception {
        
		if (boardService.update(board) != null) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		}
		return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
    }
    
    @ApiOperation(value = "게시글 작성자로 찾기", response = Board.class)
    @GetMapping("/uid/{uid}")
    public ResponseEntity <List<Board>> getBoardByUid(@PathVariable String writer) throws Exception {
        List<Board> boardList = boardService.findBoardByWriter(writer);

        if(boardList.size()==0)
            return new ResponseEntity<List<Board>>(boardList, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<List<Board>>(boardList, HttpStatus.OK);
    }

    @ApiOperation(value = "게시글 제목으로 찾기", response = Board.class)
    @GetMapping("/subject/{subject}")
    public ResponseEntity<List<Board>> getBoardBySubject(@PathVariable String subject) throws Exception {
        System.out.println(subject);
        List<Board> boardList = boardService.findBoardBySubject(subject);

        if(boardList.size()==0)
            return new ResponseEntity<List<Board>>(boardList, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<List<Board>>(boardList, HttpStatus.OK);
    }

}
