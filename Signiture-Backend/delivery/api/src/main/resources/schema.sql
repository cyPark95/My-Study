DROP TABLE IF EXISTS `user`;
DROP TABLE IF EXISTS `store`;
DROP TABLE IF EXISTS `store_menu`;
DROP TABLE IF EXISTS `user_order`;
DROP TABLE IF EXISTS `user_order_menu`;

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

CREATE TABLE `store`
(
    `id`                      BIGINT         NOT NULL AUTO_INCREMENT,
    `name`                    VARCHAR(100)   NOT NULL,
    `address`                 VARCHAR(150)   NOT NULL,
    `status`                  VARCHAR(50)    NOT NULL,
    `category`                VARCHAR(50)    NOT NULL,
    `star`                    DOUBLE DEFAULT (0),
    `thumbnail_url`           VARCHAR(200)   NOT NULL,
    `minimum_amount`          DECIMAL(11, 4) NOT NULL,
    `minimum_delivery_amount` DECIMAL(11, 4) NOT NULL,
    `phone_number`            VARCHAR(20),
    `created_at`              DATETIME(6),
    `updated_at`              DATETIME(6),
    PRIMARY KEY (`id`)
) ENGINE = InnoDB;

CREATE TABLE `store_menu`
(
    `id`            BIGINT         NOT NULL AUTO_INCREMENT,
    `store_id`      BIGINT         NOT NULL,
    `name`          VARCHAR(100)   NOT NULL,
    `amount`        DECIMAL(11, 4) NOT NULL,
    `status`        VARCHAR(50)    NOT NULL,
    `thumbnail_url` VARCHAR(200)   NOT NULL,
    `like_count`    INT DEFAULT (0),
    `sequence`      INT DEFAULT (0),
    `created_at`    DATETIME(6),
    `updated_at`    DATETIME(6),
    PRIMARY KEY (`id`),
    INDEX `fk_store_menu_store_idx` (`store_id` ASC) VISIBLE
) ENGINE = InnoDB;
