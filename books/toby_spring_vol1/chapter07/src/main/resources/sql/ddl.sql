DROP TABLE IF EXISTS USERS;

CREATE TABLE USERS
(
    id        varchar(10) primary key,
    name      varchar(10) NOT NULL,
    password  varchar(10) NOT NULL,
    level     tinyint     NOT NULL,
    login     int         NOT NULL,
    recommend int         NOT NULL,
    email     varchar(20) NOT NULL
);
