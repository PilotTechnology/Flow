/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : flow

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2016-07-18 00:34:15
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_menu`;
CREATE TABLE `t_sys_menu` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `MENU_CODE` varchar(32) NOT NULL COMMENT '菜单编号',
  `MENU_NAME` varchar(32) NOT NULL COMMENT '菜单名称',
  `UP_MENU` varchar(32) DEFAULT NULL COMMENT '上级菜单',
  `MENU_PATH` varchar(128) DEFAULT NULL COMMENT '菜单路径',
  `SORT_NO` int(11) DEFAULT NULL COMMENT '排序',
  `IS_ENABLE` varchar(1) DEFAULT NULL COMMENT '是否启用',
  `CREATE_USER` varchar(32) DEFAULT NULL COMMENT '创建人',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `uq_menu_code` (`MENU_CODE`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8 COMMENT='系统菜单表';

-- ----------------------------
-- Records of t_sys_menu
-- ----------------------------
INSERT INTO `t_sys_menu` VALUES ('1', 'SYS_MANAGE', '系统管理', '0', '', '1', '1', null, null);
INSERT INTO `t_sys_menu` VALUES ('2', 'SYS_ROLE', '角色管理', '1', '/portal/system/role', '1', '1', null, null);
INSERT INTO `t_sys_menu` VALUES ('3', 'SYS_USER', '系统用户', '1', '/portal/system/user', '2', '1', null, null);
INSERT INTO `t_sys_menu` VALUES ('4', 'FLOW_MANAGE', '流量管理', '0', null, '2', '1', null, null);
INSERT INTO `t_sys_menu` VALUES ('5', 'FLOW_ORDER', '分销平台订单', '4', '/portal/order', '1', '1', null, null);
INSERT INTO `t_sys_menu` VALUES ('6', 'FLOW_ORDER_REPORT', '分销平台订单统计', '4', '/portal/report/', '2', '1', null, null);
INSERT INTO `t_sys_menu` VALUES ('7', 'FLOW_PROVIDER', '供应商管理', '4', '/portal/provider/', '3', '1', null, null);
INSERT INTO `t_sys_menu` VALUES ('8', 'FLOW_PRODUCT', '流量包管理', '4', '/portal/product/', '4', '1', null, null);
INSERT INTO `t_sys_menu` VALUES ('9', 'FLOW_COST', '资金流水', '4', '/portal/cost', '5', '1', null, null);
