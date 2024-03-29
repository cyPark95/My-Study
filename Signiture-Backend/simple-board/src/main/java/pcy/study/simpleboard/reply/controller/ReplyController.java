package pcy.study.simpleboard.reply.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pcy.study.simpleboard.common.interceptor.Authenticated;
import pcy.study.simpleboard.reply.model.ReplyCreateRequest;
import pcy.study.simpleboard.reply.model.ReplyDeleteRequest;
import pcy.study.simpleboard.reply.service.ReplyService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/replies")
public class ReplyController {

    private final ReplyService replyService;

    @PostMapping
    public ResponseEntity<Long> create(@RequestBody @Valid ReplyCreateRequest replyCreateRequest) {
        var saveResult = replyService.save(replyCreateRequest);
        return new ResponseEntity<>(saveResult, HttpStatus.CREATED);
    }

    @Authenticated
    @PostMapping("/delete")
    public ResponseEntity<Void> delete(@RequestBody @Valid ReplyDeleteRequest replyDeleteRequest) {
        replyService.delete(replyDeleteRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
