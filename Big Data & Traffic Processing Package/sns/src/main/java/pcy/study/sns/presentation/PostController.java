package pcy.study.sns.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pcy.study.sns.domain.post.dto.PostCommand;
import pcy.study.sns.domain.post.service.PostWriteService;

@RestController
@RequiredArgsConstructor
@RequestMapping("posts")
public class PostController {

    private final PostWriteService postWriteService;

    @PostMapping
    public Long register(PostCommand command) {
        // TODO 회원 검증
        return postWriteService.register(command);
    }
}
