create table menu
(
    id      int          not null
        primary key,
    pattern varchar(200) null
);

create table menu_roles
(
    menu_id int not null,
    roles_id int not null
);

create table role
(
    id     int auto_increment
        primary key,
    name   varchar(32) null,
    nameZh varchar(32) null
);

create table user
(
    id       int auto_increment
        primary key,
    username varchar(32)  null,
    password varchar(255) null,
    enabled  tinyint(1)   null,
    locked   tinyint(1)   null
);

create table user_roles
(
    id  int auto_increment
        primary key,
    uid int null,
    roles_id int null
);

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', 'dba', '数据库管理员');
INSERT INTO `role` VALUES ('2', 'admin', '系统管理员');
INSERT INTO `role` VALUES ('3', 'user', '用户');

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'root', '{noop}1', '1', '0');
INSERT INTO `user` VALUES ('2', 'admin', '{noop}1', '1', '0');
INSERT INTO `user` VALUES ('3', 'cxk', '{noop}1', '1', '0');

-- ----------------------------
-- Records of user_roles
-- ----------------------------
INSERT INTO `user_roles` VALUES ('1', '1', '1');
INSERT INTO `user_roles` VALUES ('2', '1', '2');
INSERT INTO `user_roles` VALUES ('3', '2', '2');
INSERT INTO `user_roles` VALUES ('4', '3', '3');
SET FOREIGN_KEY_CHECKS=1;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('1', '/admin/**');
INSERT INTO `menu` VALUES ('2', '/user/**');
INSERT INTO `menu` VALUES ('3', '/db/**');

-- ----------------------------
-- Records of menu_roles
-- ----------------------------
INSERT INTO `menu_roles` VALUES ('1', '2');
INSERT INTO `menu_roles` VALUES ('2', '3');
INSERT INTO `menu_roles` VALUES ('3', '1');
