/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : flow

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2016-07-18 00:34:22
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_sys_role
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_role`;
CREATE TABLE `t_sys_role` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `ROLE_CODE` varchar(32) NOT NULL COMMENT '角色编号',
  `ROLE_NAME` varchar(64) NOT NULL COMMENT '角色名称',
  `CREATE_USER` varchar(32) DEFAULT NULL COMMENT '创建人',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `uq_role_code` (`ROLE_CODE`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of t_sys_role
-- ----------------------------
INSERT INTO `t_sys_role` VALUES ('1', 'ROLE_ADMIN', '系统管理员', 'system', '2016-07-17 00:00:00');
INSERT INTO `t_sys_role` VALUES ('2', 'ROLE_TEST', '测试', 'system', '2016-07-17 00:00:00');
INSERT INTO `t_sys_role` VALUES ('4', 'admin', 'test测速', 'admin', '2016-07-17 17:02:54');
INSERT INTO `t_sys_role` VALUES ('5', 'admin_123', '测试123', 'admin', '2016-07-17 17:45:06');
