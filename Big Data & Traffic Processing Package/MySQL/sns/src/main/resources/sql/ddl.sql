DROP TABLE IF EXISTS Member;
DROP TABLE IF EXISTS MemberNicknameHistory;
DROP TABLE IF EXISTS Follow;
DROP TABLE IF EXISTS Post;
DROP TABLE IF EXISTS Timeline;

create table Member
(
    id        int auto_increment,
    email     varchar(20)  not null,
    password  varchar(100) not null,
    nickname  varchar(20)  not null,
    birthday  date         not null,
    createdAt datetime     not null,
    constraint member_id_uindex
        primary key (id)
);

create table MemberNicknameHistory
(
    id        int auto_increment,
    memberId  int         not null,
    nickname  varchar(20) not null,
    createdAt datetime    not null,
    constraint memberNicknameHistory_id_uindex
        primary key (id)
);

create table Follow
(
    id           int auto_increment,
    fromMemberId int      not null,
    toMemberId   int      not null,
    createdAt    datetime not null,
    constraint Follow_id_uindex
        primary key (id)
);

create unique index Follow_fromMemberId_toMemberId_uindex
    on Follow (fromMemberId, toMemberId);

create table Post
(
    id          int auto_increment,
    memberId    int          not null,
    contents    varchar(100) not null,
    createdDate date         not null,
    likeCount   int,
    version     int,
    createdAt   datetime     not null,
    constraint Post_id_uindex
        primary key (id)
);

create index Post__index_member_id
    on Post (memberId);

create index Post__index_created_date
    on Post (createdDate);

create table Timeline
(
    id        int auto_increment,
    memberId  int      not null,
    postId    int      not null,
    createdAt datetime not null,
    constraint Timeline_id_uindex
        primary key (id)
);

create index Timeline__index_memberId
    on Post (memberId);

create table PostLike
(
    id        int auto_increment,
    memberId  int      not null,
    postId    int      not null,
    createdAt datetime not null,
    constraint PostLike_id_uindex
        primary key (id),
    constraint PostLike_unique
        unique (memberId, postId)
);
