/*
Navicat MySQL Data Transfer

Source Server         : web
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : ssh

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2017-11-24 21:38:32
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_classify
-- ----------------------------
DROP TABLE IF EXISTS `t_classify`;
CREATE TABLE `t_classify` (
  `id` varchar(40) NOT NULL,
  `name` varchar(40) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_classify
-- ----------------------------
INSERT INTO `t_classify` VALUES ('aq', '爱情', '2017-09-28 08:34:52', '2017-09-28 08:34:52');
INSERT INTO `t_classify` VALUES ('dz', '动作', '2017-09-28 08:34:52', '2017-09-28 08:34:52');
INSERT INTO `t_classify` VALUES ('jq', '剧情', '2017-09-28 08:34:52', '2017-09-28 08:34:52');
INSERT INTO `t_classify` VALUES ('kb', '恐怖', '2017-09-28 08:34:52', '2017-09-28 08:34:52');
INSERT INTO `t_classify` VALUES ('kh', '科幻', '2017-09-28 08:34:52', '2017-09-28 08:34:52');
INSERT INTO `t_classify` VALUES ('mx', '冒险', '2017-09-28 08:34:52', '2017-09-28 08:34:52');
INSERT INTO `t_classify` VALUES ('wx', '武侠', '2017-09-28 08:34:52', '2017-09-28 08:34:52');
INSERT INTO `t_classify` VALUES ('xj', '喜剧', '2017-09-28 08:34:52', '2017-09-28 08:34:52');
INSERT INTO `t_classify` VALUES ('xy', '悬疑', '2017-09-28 08:34:52', '2017-09-28 08:34:52');
INSERT INTO `t_classify` VALUES ('zz', '战争', '2017-09-28 08:34:52', '2017-09-28 08:34:52');

-- ----------------------------
-- Table structure for t_film
-- ----------------------------
DROP TABLE IF EXISTS `t_film`;
CREATE TABLE `t_film` (
  `id` varchar(40) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `film_describe` varchar(255) DEFAULT NULL,
  `classify_text` varchar(40) DEFAULT NULL,
  `img` varchar(150) DEFAULT NULL,
  `url` varchar(150) DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_film
-- ----------------------------
INSERT INTO `t_film` VALUES ('4ecb323f-e8e6-49b9-a138-223b48bb8365', '测试', '狗日的代码，老子跟你拼了', '恐怖', 'http://localhost:8080/upload/3a26be26047145cab540abb51a59b00c.jpg', 'http://localhost:8080/upload/3a26be26047145cab540abb51a59b00c.jpg', '2017-09-14 00:00:00', '2017-09-26 08:42:38', '2017-09-26 08:42:38');
INSERT INTO `t_film` VALUES ('9cd5fa53-51fb-4b86-a0eb-f96839d4c3bc', '111', '666', '科幻', 'http://localhost:8080/upload/64fffe31cd774b21a692270dc1d828f8.jpg', 'http://localhost:8080/upload/64fffe31cd774b21a692270dc1d828f8.jpg', '2017-09-06 00:00:00', '2017-09-29 08:36:46', '2017-09-29 08:36:46');

-- ----------------------------
-- Table structure for t_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE `t_menu` (
  `id` varchar(40) NOT NULL DEFAULT '',
  `pid` varchar(40) DEFAULT NULL,
  `text` varchar(50) DEFAULT NULL,
  `iconCls` varchar(50) DEFAULT NULL,
  `url` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ss` (`pid`),
  CONSTRAINT `ss` FOREIGN KEY (`pid`) REFERENCES `t_menu` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_menu
-- ----------------------------
INSERT INTO `t_menu` VALUES ('0', null, '首页', 'icon-tip', null);
INSERT INTO `t_menu` VALUES ('nrgl', '0', '内容管理', null, null);
INSERT INTO `t_menu` VALUES ('xtgl', '0', '系统管理', null, null);
INSERT INTO `t_menu` VALUES ('xwgl', 'xtgl', '新闻管理', null, 'admin/newsManager.jsp');
INSERT INTO `t_menu` VALUES ('yhgl', 'xtgl', '用户管理', null, 'admin/userManager.jsp');
INSERT INTO `t_menu` VALUES ('ypfl', 'nrgl', '分类管理', null, 'admin/classifyManager.jsp');
INSERT INTO `t_menu` VALUES ('ypgl', 'nrgl', '影片管理', null, 'admin/filmManager.jsp');

-- ----------------------------
-- Table structure for t_news
-- ----------------------------
DROP TABLE IF EXISTS `t_news`;
CREATE TABLE `t_news` (
  `id` varchar(40) NOT NULL,
  `title` varchar(100) DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  `intro` varchar(100) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_news
-- ----------------------------
INSERT INTO `t_news` VALUES ('666', '习近平来访', '今天习近平来到郑、', '来访问轻院啦', null, '2017-09-29 08:35:45');
INSERT INTO `t_news` VALUES ('880710de-1e97-483f-b82d-5a5d9a6449fc', '停电啦', '禹州校园停电已超过三个小时', '禹州校区停电了', null, '2017-09-24 14:28:09');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` varchar(40) NOT NULL DEFAULT '',
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `nick` varchar(50) NOT NULL COMMENT '删除标记，未删除为0，已删除为1',
  `create_time` date DEFAULT NULL,
  `update_time` date DEFAULT NULL COMMENT '最后一次更新时间',
  `delete_flag` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('0', 'admin', '21232f297a57a5a743894a0e4a801fc3', 'admin', '2017-09-28', null, null);
INSERT INTO `t_user` VALUES ('0e330a5c-04e8-474c-a92d-84608ef4f902', 'lei', 'lei', 'lei', '2017-09-21', '2017-09-21', null);
INSERT INTO `t_user` VALUES ('4e08e773-deb2-4810-8f70-3085955374eb', 'junmoxi', 'b09eb5aba48c37c1584b0a487164633a', '君莫惜', '2017-09-22', null, '0');
INSERT INTO `t_user` VALUES ('dfs', 'df', 'fdg', 'gdf', '2017-09-13', '2017-09-29', null);
INSERT INTO `t_user` VALUES ('er', 'gw', 'grer', '66', '2017-09-12', '2017-09-22', null);
INSERT INTO `t_user` VALUES ('fwe', 'rey', 'rty', 'rtu', null, null, null);
INSERT INTO `t_user` VALUES ('gerfew', 'rehr', 'he', 'trry', null, null, null);
INSERT INTO `t_user` VALUES ('he', 'ku', 'er', 'try', '2017-09-26', '2017-09-22', null);
INSERT INTO `t_user` VALUES ('jr', 'rt', 'ht', 'rjyj', null, null, null);
INSERT INTO `t_user` VALUES ('jrgre', 'rt', 'ryt', 'hrt', null, null, null);
INSERT INTO `t_user` VALUES ('jrt', 'yk', 'ky', 'gre', null, null, null);
INSERT INTO `t_user` VALUES ('ko', 'jyt', 'ky', 'hh', '2017-09-19', null, null);
