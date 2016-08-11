/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : flow

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2016-07-18 00:34:28
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_role_menu`;
CREATE TABLE `t_sys_role_menu` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `ROLE_CODE` varchar(32) DEFAULT NULL COMMENT '角色编号',
  `MENU_CODE` varchar(32) DEFAULT NULL COMMENT '菜单编号',
  `CREATE_USER` varchar(32) DEFAULT NULL COMMENT '创建人',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3477 DEFAULT CHARSET=utf8 COMMENT='角色菜单关系表';

-- ----------------------------
-- Records of t_sys_role_menu
-- ----------------------------
INSERT INTO `t_sys_role_menu` VALUES ('3532', 'admin', 'SYS_MANAGE', 'admin', '2016-08-12 00:11:04');
INSERT INTO `t_sys_role_menu` VALUES ('3533', 'admin', 'SYS_ROLE', 'admin', '2016-08-12 00:11:04');
INSERT INTO `t_sys_role_menu` VALUES ('3534', 'admin', 'SYS_USER', 'admin', '2016-08-12 00:11:04');
INSERT INTO `t_sys_role_menu` VALUES ('3535', 'admin', 'FLOW_MANAGE', 'admin', '2016-08-12 00:11:04');
INSERT INTO `t_sys_role_menu` VALUES ('3536', 'admin', 'FLOW_ORDER', 'admin', '2016-08-12 00:11:04');
INSERT INTO `t_sys_role_menu` VALUES ('3537', 'admin', 'FLOW_ORDER_REPORT', 'admin', '2016-08-12 00:11:04');
INSERT INTO `t_sys_role_menu` VALUES ('3538', 'admin', 'FLOW_PROVIDER', 'admin', '2016-08-12 00:11:04');
INSERT INTO `t_sys_role_menu` VALUES ('3539', 'admin', 'FLOW_PRODUCT', 'admin', '2016-08-12 00:11:04');
INSERT INTO `t_sys_role_menu` VALUES ('3540', 'admin', 'FLOW_COST', 'admin', '2016-08-12 00:11:04');