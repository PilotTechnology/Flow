/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : flow

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2016-07-06 00:17:14
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `USER_CODE` varchar(32) NOT NULL COMMENT '用户编号',
  `PASSWORD` varchar(128) DEFAULT NULL COMMENT '用户密码',
  `NICKNAME` varchar(32) DEFAULT NULL,
  `LINKMAN` varchar(32) DEFAULT NULL COMMENT '所属部门',
  `PHONE` varchar(32) DEFAULT NULL,
  `EMAIL` varchar(32) DEFAULT NULL COMMENT '电子信箱',
  `IS_ENABLE` varchar(1) DEFAULT NULL COMMENT '是否有效',
  `CREATE_DATE` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `ROLEID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `uq_user_code` (`USER_CODE`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('2', 'admin', '22222222', 'ych', '李四', '19809098888', '12@test.cc', '2', '2016-07-05 23:53:44', '2');
INSERT INTO `t_user` VALUES ('3', 'system', '11111111', 'mk', '张三', '18711119999', '12@qq.com', '3', '2016-07-06 00:16:42', '3');
INSERT INTO `t_user` VALUES ('4', 'user', '33333333', 'test', '王五', '18611112222', '33@qq.com', '3', '2016-07-06 00:16:43', '3');
