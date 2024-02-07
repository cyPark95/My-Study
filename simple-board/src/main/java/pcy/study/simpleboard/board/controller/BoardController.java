package pcy.study.simpleboard.board.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pcy.study.simpleboard.board.model.BoardCreateRequest;
import pcy.study.simpleboard.board.service.BoardService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
public class BoardController {

    private final BoardService boardService;

    @PostMapping
    public ResponseEntity<Long> create(@RequestBody @Valid BoardCreateRequest boardCreateRequest) {
        var saveResult = boardService.save(boardCreateRequest);
        return new ResponseEntity<>(saveResult, HttpStatus.CREATED);
    }
}
