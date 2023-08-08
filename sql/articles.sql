-- ----------------------------
-- 1、article sheet
-- ----------------------------
drop table if exists `posts`;

create table `posts`
(
    `id`          bigint(20)        not null    auto_increment        comment 'article id',
    `author`      varchar(255)      not null                          comment 'author',
    `comment_nums`bigint(20)        not null                          comment 'comment account',
    `content`     text              not null                          comment 'content',
    `create_time` datetime                                            comment 'created time',
    `cover`       varchar(1024)     not null                          comment 'cover',
    "img_list"    text              not null                          comment 'image list',
    `likes_num`   bigint(20)        not null                          comment 'like account',
    `see_num`     bigint(20)        not null                          comment 'page views',
    `state`       int(1)            not null                          comment 'page views',
    `title`       varchar(255)      not null                          comment 'article title',
    `type`        int(1)            not null                          comment 'article type',
    primary key (`id`)
) engine=innodb auto_increment=100 comment = 'article sheet';



