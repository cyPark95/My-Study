DROP TABLE IF EXISTS `user`;

CREATE TABLE `user`
(
    `id`              BIGINT       NOT NULL AUTO_INCREMENT,
    `name`            VARCHAR(50)  NOT NULL,
    `email`           VARCHAR(100) NOT NULL,
    `password`        VARCHAR(100) NOT NULL,
    `status`          VARCHAR(50)  NOT NULL,
    `address`         VARCHAR(150) NOT NULL,
    `unregistered_at` DATETIME(6),
    `last_login_at`   DATETIME(6),
    `created_at`      DATETIME(6),
    `updated_at`      DATETIME(6),
    PRIMARY KEY (`id`)
) ENGINE = InnoDB;
