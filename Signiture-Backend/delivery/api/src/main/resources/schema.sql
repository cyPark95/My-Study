DROP TABLE IF EXISTS account;

CREATE TABLE account
(
    id         BIGINT       NOT NULL AUTO_INCREMENT,
    created_at DATETIME(6),
    updated_At DATETIME(6),
    PRIMARY KEY (id)
) ENGINE = InnoDB;
