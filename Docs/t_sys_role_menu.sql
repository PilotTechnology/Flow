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
insert into `flow`.`t_sys_role_menu` ( `ROLE_CODE`, `MENU_CODE`, `CREATE_USER`, `CREATE_TIME`) values ( 'admin', 'SYS_MANAGE', 'admin', '2016-08-12 00:11:04');
insert into `flow`.`t_sys_role_menu` ( `ROLE_CODE`, `MENU_CODE`, `CREATE_USER`, `CREATE_TIME`) values ( 'admin', 'SYS_ROLE', 'admin', '2016-08-12 00:11:04');
insert into `flow`.`t_sys_role_menu` ( `ROLE_CODE`, `MENU_CODE`, `CREATE_USER`, `CREATE_TIME`) values ( 'admin', 'SYS_USER', 'admin', '2016-08-12 00:11:04');
insert into `flow`.`t_sys_role_menu` ( `ROLE_CODE`, `MENU_CODE`, `CREATE_USER`, `CREATE_TIME`) values ( 'admin', 'FLOW_MANAGE', 'admin', '2016-08-12 00:11:04');
insert into `flow`.`t_sys_role_menu` ( `ROLE_CODE`, `MENU_CODE`, `CREATE_USER`, `CREATE_TIME`) values ( 'admin', 'FLOW_ORDER', 'admin', '2016-08-12 00:11:04');
insert into `flow`.`t_sys_role_menu` ( `ROLE_CODE`, `MENU_CODE`, `CREATE_USER`, `CREATE_TIME`) values ( 'admin', 'FLOW_ORDER_REPORT', 'admin', '2016-08-12 00:11:04');
insert into `flow`.`t_sys_role_menu` ( `ROLE_CODE`, `MENU_CODE`, `CREATE_USER`, `CREATE_TIME`) values ( 'admin', 'FLOW_PROVIDER', 'admin', '2016-08-12 00:11:04');
insert into `flow`.`t_sys_role_menu` ( `ROLE_CODE`, `MENU_CODE`, `CREATE_USER`, `CREATE_TIME`) values ( 'admin', 'FLOW_PRODUCT', 'admin', '2016-08-12 00:11:04');
insert into `flow`.`t_sys_role_menu` ( `ROLE_CODE`, `MENU_CODE`, `CREATE_USER`, `CREATE_TIME`) values ( 'admin', 'FLOW_COST', 'admin', '2016-08-12 00:11:04');
insert into `flow`.`t_sys_role_menu` ( `ROLE_CODE`, `MENU_CODE`, `CREATE_USER`, `CREATE_TIME`) values ( 'admin', 'FLOW_DISTRIBUTOR_MANAGE', 'admin', '2016-08-13 18:22:09');
insert into `flow`.`t_sys_role_menu` ( `ROLE_CODE`, `MENU_CODE`, `CREATE_USER`, `CREATE_TIME`) values ( 'admin', 'FLOW_DISTRIBUTOR', 'admin', '2016-08-13 18:22:12');
insert into `flow`.`t_sys_role_menu` ( `ROLE_CODE`, `MENU_CODE`, `CREATE_USER`, `CREATE_TIME`) values ( 'admin', 'FLOW_QUOTATION', 'admin', '2016-08-13 18:22:14');
insert into `flow`.`t_sys_role_menu` ( `ROLE_CODE`, `MENU_CODE`, `CREATE_USER`, `CREATE_TIME`) values ( 'admin', 'FLOW_RECHARGE', 'admin', '2016-08-13 18:22:17');
insert into `flow`.`t_sys_role_menu` ( `ROLE_CODE`, `MENU_CODE`, `CREATE_USER`, `CREATE_TIME`) values ( 'admin', 'FLOW_BATCH', 'admin', '2016-08-13 18:22:19');
insert into `flow`.`t_sys_role_menu` ( `ROLE_CODE`, `MENU_CODE`, `CREATE_USER`, `CREATE_TIME`) values ( 'admin', 'FLOW_PHONE_MANAGE', 'admin', '2016-08-14 11:18:22');
insert into `flow`.`t_sys_role_menu` ( `ROLE_CODE`, `MENU_CODE`, `CREATE_USER`, `CREATE_TIME`) values ( 'admin', 'FLOW_PHONE', 'admin', '2016-08-14 11:19:27');