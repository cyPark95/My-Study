package pcy.study.simpleboard.post.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pcy.study.simpleboard.post.model.*;
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

    @PostMapping("/details")
    public ResponseEntity<PostDetailsResponse> details(@RequestBody @Valid PostDetailsRequest postDetailsRequest) {
        var findPostResult = postService.findPost(postDetailsRequest);
        return ResponseEntity.ok(PostDetailsResponse.of(findPostResult));
    }

    @GetMapping
    public ResponseEntity<PostGetAllResponse> getPostAll() {
        var findPostAllResult = postService.findPostAll();
        return ResponseEntity.ok(PostGetAllResponse.of(findPostAllResult));
    }

    @PostMapping("/delete")
    public ResponseEntity<Void> delete(@RequestBody @Valid PostDeleteRequest postDeleteRequest) {
        postService.delete(postDeleteRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
