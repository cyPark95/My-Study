package pcy.study.simpleboard.reply.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pcy.study.simpleboard.reply.model.ReplyCreateRequest;
import pcy.study.simpleboard.reply.service.ReplyService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reply")
public class ReplyController {

    private final ReplyService replyService;

    @PostMapping
    public ResponseEntity<Long> create(@RequestBody @Valid ReplyCreateRequest replyCreateRequest) {
        var saveResult = replyService.save(replyCreateRequest);
        return new ResponseEntity<>(saveResult, HttpStatus.CREATED);
    }
}
