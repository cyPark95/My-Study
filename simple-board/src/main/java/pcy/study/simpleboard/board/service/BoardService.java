package pcy.study.simpleboard.board.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pcy.study.simpleboard.board.db.BoardRepository;
import pcy.study.simpleboard.board.model.BoardCreateRequest;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public Long save(BoardCreateRequest boardCreateRequest) {
        log.info("Save Board Name = {}", boardCreateRequest.name());
        var entity = boardCreateRequest.toBoard();
        boardRepository.save(entity);
        return entity.getId();
    }
}
