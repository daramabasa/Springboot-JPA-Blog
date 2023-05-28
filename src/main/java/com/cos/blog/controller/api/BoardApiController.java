package com.cos.blog.controller.api;

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.dto.ResponseDTO;
import com.cos.blog.model.Board;
import com.cos.blog.model.User;
import com.cos.blog.service.BoardService;
import com.cos.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
public class BoardApiController {

    @Autowired
    private BoardService boardService;

    @PostMapping("/api/board")
    public ResponseDTO<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principal){
        boardService.boardWrite(board, principal.getUser());
        return new ResponseDTO<Integer>(HttpStatus.OK.value(), 1);
    }

    @DeleteMapping("/api/board/{id}")
    public ResponseDTO<Integer> deleteById(@PathVariable int id) {
        boardService.boardDelete(id);

        return new ResponseDTO<Integer>(HttpStatus.OK.value(), 1);
    }

    @PutMapping("/api/board/{id}")
    public ResponseDTO<Integer> update(@PathVariable int id, @RequestBody Board board) {
        System.out.println("BoardApiController-update: " + id);
        System.out.println("BoardApiController-update: " + board.getTitle());
        System.out.println("BoardApiController-update: " + board.getContent());
        boardService.boardModify(id, board);

        return new ResponseDTO<Integer>(HttpStatus.OK.value(), 1);
    }
}
