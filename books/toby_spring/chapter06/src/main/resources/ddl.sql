DROP TABLE IF EXISTS users;

CREATE TABLE users
(
    id        varchar(10) primary key,
    name      varchar(10) not null,
    password  varchar(10) not null,
    level     tinyint     not null,
    login     int         not null,
    recommend int         not null,
    email     varchar(20) not null
);
