package pcy.study.simpleboard.board.model;

import jakarta.validation.constraints.NotBlank;
import pcy.study.simpleboard.board.db.Board;

public record BoardCreateRequest(
        @NotBlank
        String name
) {

    public Board toBoard() {
        return Board.builder()
                .name(name)
                .build();
    }
}
