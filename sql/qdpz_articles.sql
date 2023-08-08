-- ----------------------------
-- 1、article sheet
-- ----------------------------
drop table if exists `qdpz_articles`;

create table `qdpz_articles`
(
    `id`          bigint(20)        not null    auto_increment        comment 'article id',
    `author`      varchar(255)      not null                          comment 'author',
    `comment_nums`bigint(20)        not null                          comment 'comment account',
    `content`     text              not null                          comment 'content',
    `create_time` datetime                                            comment 'created time',
    `cover`       varchar(1024)     not null                          comment 'cover',
    `img_list`    text              not null                          comment 'image list',
    `likes_num`   bigint(20)        not null                          comment 'like account',
    `see_num`     bigint(20)        not null                          comment 'page views',
    `state`       int(1)            not null                          comment 'article state',
    `title`       varchar(255)      not null                          comment 'article title',
    `type`        int(1)            not null                          comment 'article type',
    primary key (`id`)
) engine=innodb auto_increment=100 comment = 'article sheet';


insert into `qdpz_articles` (`id`, `author`, `comment_nums`, `content`, `create_time`, `cover`, `img_list`, `likes_num`, `see_num`, `state`, `title`, `type`)
values
    (6, 'kevin', 287, '前端铺子专用设计师作品集展示，铺子对UI设计的追求一向很高，致力于为开发者、互联网科技企业提供的ui外包、软件界面设计、网站设计，用户研究、交互设计等服务；&我的们设计师一直坚持注重用户心理体验及“设计与营销”等领域的理论与实践相结合；&设计仅展示部分项目，欢迎骚扰~', sysdate(), 'https://cdn.zhoukaiwen.com/FsD6malrWDZymsmvPEyExirfqAxX', 'https://cdn.zhoukaiwen.com/FnPlYKG08VSiKZ42UAQ43ta6tcIF,https://cdn.zhoukaiwen.com/FkIVFEF773uOS1GY7bZg-QA7xdgP,https://cdn.zhoukaiwen.com/FvvL3n_p6co0HUaiG6O1KIssZK0d,https://cdn.zhoukaiwen.com/FisWZWaqeoxuSeUOsq2jJJMEPZug,https://cdn.zhoukaiwen.com/FgOY4gBoS75oTQiGnb5BZs81lfjz,https://cdn.zhoukaiwen.com/Ft22JRKuKQ0CtYga_ezYHeknWhhB,https://cdn.zhoukaiwen.com/FrcszulV0DLyeYvivDJTKXhFmqMp,https://cdn.zhoukaiwen.com/FhjYE-zGygW-hIeHcAElpM5MGroR,https://cdn.zhoukaiwen.com/FtQk9EuERy1lbHaieNAoLlaqG6l4,https://cdn.zhoukaiwen.com/FiFDK3owQxAe5OaPSnqLROQSg_dz,https://cdn.zhoukaiwen.com/FsyWGfXAIVp0Ig43Mo9Kjcfrm9n1,https://cdn.zhoukaiwen.com/FgQ5vmDjs3ZsP-lcpMdZmymHoqH9', 326, 3223, 1, '大片来袭~ 铺子专用设计师ui作品集，高清', 3),
    (7, 'kevin', 287, '前端铺子专用设计师作品集展示，铺子对UI设计的追求一向很高', sysdate(), 'https://cdn.zhoukaiwen.com/FsD6malrWDZymsmvPEyExirfqAxX', 'https://cdn.zhoukaiwen.com/FnPlYKG08VSiKZ42UAQ43ta6tcIF', 326, 3223, 1, '大片来袭~ 铺子专用设计师ui作品集，高清', 3),
    (8, 'kevin', 287, '致力于为开发者、互联网科技企业提供的ui外包', sysdate(), 'https://cdn.zhoukaiwen.com/FsD6malrWDZymsmvPEyExirfqAxX', 'https://cdn.zhoukaiwen.com/FkIVFEF773uOS1GY7bZg-QA7xdgP', 326, 3223, 1, '大片来袭~ 铺子专用设计师ui作品集，高清', 3),
    (9, 'kevin', 287, '软件界面设计、网站设计，用户研究、交互设计等服务', sysdate(), 'https://cdn.zhoukaiwen.com/FsD6malrWDZymsmvPEyExirfqAxX', 'https://cdn.zhoukaiwen.com/FvvL3n_p6co0HUaiG6O1KIssZK0d', 326, 3223, 1, '大片来袭~ 铺子专用设计师ui作品集，高清', 3),
    (10, 'kevin', 287, '设计师一直坚持注重用户心理体验及设计与营销', sysdate(), 'https://cdn.zhoukaiwen.com/FsD6malrWDZymsmvPEyExirfqAxX', 'https://cdn.zhoukaiwen.com/FisWZWaqeoxuSeUOsq2jJJMEPZug', 326, 3223, 1, '大片来袭~ 铺子专用设计师ui作品集，高清', 3),
    (11, 'kevin', 287, '设计仅展示部分项目，欢迎骚扰', sysdate(), 'https://cdn.zhoukaiwen.com/FsD6malrWDZymsmvPEyExirfqAxX', 'https://cdn.zhoukaiwen.com/FgOY4gBoS75oTQiGnb5BZs81lfjz', 326, 3223, 1, '大片来袭~ 铺子专用设计师ui作品集，高清', 3);
