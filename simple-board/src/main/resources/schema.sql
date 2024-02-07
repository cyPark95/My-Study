DROP TABLE IF EXISTS board;
DROP TABLE IF EXISTS post;
DROP TABLE IF EXISTS reply;

CREATE TABLE board
(
    id         BIGINT       NOT NULL AUTO_INCREMENT,
    name       VARCHAR(100) NOT NULL,
    status     VARCHAR(50)  NOT NULL,
    created_at DATETIME(6),
    updated_At DATETIME(6),
    PRIMARY KEY (id)
) ENGINE = InnoDB;

CREATE TABLE post
(
    id         BIGINT       NOT NULL AUTO_INCREMENT,
    board_id   BIGINT       NOT NULL,
    user_name  VARCHAR(50)  NOT NULL,
    email      VARCHAR(100) NOT NULL,
    password   VARCHAR(4)   NOT NULL,
    title      VARCHAR(100) NOT NULL,
    contents   TEXT         NOT NULL,
    status     VARCHAR(50)  NOT NULL,
    created_at DATETIME(6),
    updated_At DATETIME(6),
    PRIMARY KEY (id)
) ENGINE = InnoDB;

CREATE TABLE reply
(
    id         BIGINT       NOT NULL AUTO_INCREMENT,
    post_id    BIGINT       NOT NULL,
    user_name  VARCHAR(50)  NOT NULL,
    password   VARCHAR(50)   NOT NULL,
    title      VARCHAR(100) NOT NULL,
    contents   TEXT         NOT NULL,
    status     VARCHAR(50)  NOT NULL,
    created_at DATETIME(6),
    updated_At DATETIME(6),
    PRIMARY KEY (id)
) ENGINE = InnoDB;
