/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : flow

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2016-08-12 00:15:24
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_sys_user
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_user`;
CREATE TABLE `t_sys_user` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `USER_CODE` varchar(32) NOT NULL COMMENT '用户编号',
  `ROLE_CODE` varchar(32) DEFAULT NULL COMMENT '角色编码',
  `PASSWORD` varchar(32) DEFAULT NULL COMMENT '用户密码',
  `NICK_NAME` varchar(32) DEFAULT NULL,
  `LINK_MAN` varchar(32) DEFAULT NULL COMMENT '所属部门',
  `PHONE` varchar(32) DEFAULT NULL,
  `EMAIL` varchar(32) DEFAULT NULL COMMENT '电子信箱',
  `STATE` int(11) DEFAULT '1' COMMENT '状态(0: 禁用 1：激活)',
  `CREATE_DATE` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `uq_user_code` (`USER_CODE`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of t_sys_user
-- ----------------------------
INSERT INTO `t_sys_user` VALUES ('2', 'admin', '2', 'e10adc3949ba59abbe56e057f20f883e', 'ych', '李四', '19809098888', '12@test.cc', '2', '2016-07-05 23:53:44');
