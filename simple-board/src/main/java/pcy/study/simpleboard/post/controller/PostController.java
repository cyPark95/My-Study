package pcy.study.simpleboard.post.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pcy.study.simpleboard.post.model.PostCreateRequest;
import pcy.study.simpleboard.post.model.PostGetAllResponse;
import pcy.study.simpleboard.post.model.PostGetRequest;
import pcy.study.simpleboard.post.model.PostResponse;
import pcy.study.simpleboard.post.service.PostService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<Long> create(@RequestBody @Valid PostCreateRequest postCreateRequest) {
        var saveResult = postService.save(postCreateRequest);
        return new ResponseEntity<>(saveResult, HttpStatus.CREATED);
    }

    @PostMapping("/{id}")
    public ResponseEntity<PostResponse> getPost(
            @PathVariable("id") Long id,
            @RequestBody @Valid PostGetRequest postGetRequest
    ) {
        var findPostResult = postService.findPost(id, postGetRequest);
        return ResponseEntity.ok(PostResponse.of(findPostResult));
    }

    @GetMapping
    public ResponseEntity<PostGetAllResponse> getPostAll() {
        var findPostAllResult = postService.findPostAll();
        return ResponseEntity.ok(PostGetAllResponse.of(findPostAllResult));
    }
}
