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
insert into `flow`.`t_sys_menu` ( `MENU_CODE`, `MENU_NAME`, `UP_MENU`, `MENU_PATH`, `SORT_NO`, `IS_ENABLE`, `CREATE_USER`, `CREATE_TIME`) values ( 'SYS_MANAGE', '系统管理', '0', '', '1', '1', 'admin', '2016-08-13 18:36:44');
insert into `flow`.`t_sys_menu` ( `MENU_CODE`, `MENU_NAME`, `UP_MENU`, `MENU_PATH`, `SORT_NO`, `IS_ENABLE`, `CREATE_USER`, `CREATE_TIME`) values ( 'SYS_ROLE', '角色管理', '1', '/portal/system/role!selectPage.action', '1', '1', 'admin', '2016-08-13 18:36:48');
insert into `flow`.`t_sys_menu` ( `MENU_CODE`, `MENU_NAME`, `UP_MENU`, `MENU_PATH`, `SORT_NO`, `IS_ENABLE`, `CREATE_USER`, `CREATE_TIME`) values ( 'SYS_USER', '系统用户', '1', '/portal/system/user!selectPage.action', '2', '1', 'admin', '2016-08-13 18:36:48');
insert into `flow`.`t_sys_menu` ( `MENU_CODE`, `MENU_NAME`, `UP_MENU`, `MENU_PATH`, `SORT_NO`, `IS_ENABLE`, `CREATE_USER`, `CREATE_TIME`) values ( 'FLOW_MANAGE', '流量管理', '0', null, '2', '1', 'admin', '2016-08-13 18:36:48');
insert into `flow`.`t_sys_menu` ( `MENU_CODE`, `MENU_NAME`, `UP_MENU`, `MENU_PATH`, `SORT_NO`, `IS_ENABLE`, `CREATE_USER`, `CREATE_TIME`) values ( 'FLOW_ORDER', '分销平台订单', '4', '/portal/order!selectPage.action', '1', '1', 'admin', '2016-08-13 18:36:48');
insert into `flow`.`t_sys_menu` ( `MENU_CODE`, `MENU_NAME`, `UP_MENU`, `MENU_PATH`, `SORT_NO`, `IS_ENABLE`, `CREATE_USER`, `CREATE_TIME`) values ( 'FLOW_ORDER_REPORT', '分销平台订单统计', '4', '/portal/order!statistics.action', '2', '1', 'admin', '2016-08-13 18:36:48');
insert into `flow`.`t_sys_menu` ( `MENU_CODE`, `MENU_NAME`, `UP_MENU`, `MENU_PATH`, `SORT_NO`, `IS_ENABLE`, `CREATE_USER`, `CREATE_TIME`) values ( 'FLOW_PROVIDER', '供应商管理', '4', '/portal/provider!selectPage.action', '3', '1', 'admin', '2016-08-13 18:36:48');
insert into `flow`.`t_sys_menu` ( `MENU_CODE`, `MENU_NAME`, `UP_MENU`, `MENU_PATH`, `SORT_NO`, `IS_ENABLE`, `CREATE_USER`, `CREATE_TIME`) values ( 'FLOW_PRODUCT', '流量包管理', '4', '/portal/product!selectPage.action', '4', '1', 'admin', '2016-08-13 18:36:48');
insert into `flow`.`t_sys_menu` ( `MENU_CODE`, `MENU_NAME`, `UP_MENU`, `MENU_PATH`, `SORT_NO`, `IS_ENABLE`, `CREATE_USER`, `CREATE_TIME`) values ( 'FLOW_COST', '资金流水', '4', '/portal/costflow!selectPage.action', '5', '1', 'admin', '2016-08-13 18:36:48');
insert into `flow`.`t_sys_menu` ( `MENU_CODE`, `MENU_NAME`, `UP_MENU`, `MENU_PATH`, `SORT_NO`, `IS_ENABLE`, `CREATE_USER`, `CREATE_TIME`) values ( 'FLOW_DISTRIBUTOR_MANAGE', '分销商管理', '0', null, '3', '1', 'admin', '2016-08-13 18:36:48');
insert into `flow`.`t_sys_menu` ( `MENU_CODE`, `MENU_NAME`, `UP_MENU`, `MENU_PATH`, `SORT_NO`, `IS_ENABLE`, `CREATE_USER`, `CREATE_TIME`) values ( 'FLOW_DISTRIBUTOR', '分销商管理', '10', '/portal/distributor!selectPage.action', '1', '1', 'admin', '2016-08-13 18:36:48');
insert into `flow`.`t_sys_menu` ( `MENU_CODE`, `MENU_NAME`, `UP_MENU`, `MENU_PATH`, `SORT_NO`, `IS_ENABLE`, `CREATE_USER`, `CREATE_TIME`) values ( 'FLOW_QUOTATION', '报价单管理', '10', '/portal/quotation!selectPage.action', '2', '1', 'admin', '2016-08-13 18:36:48');
insert into `flow`.`t_sys_menu` ( `MENU_CODE`, `MENU_NAME`, `UP_MENU`, `MENU_PATH`, `SORT_NO`, `IS_ENABLE`, `CREATE_USER`, `CREATE_TIME`) values ( 'FLOW_RECHARGE', '账户充值', '10', '/portal/rechargeflow!selectPage.action', '3', '1', 'admin', '2016-08-13 18:36:48');
insert into `flow`.`t_sys_menu` ( `MENU_CODE`, `MENU_NAME`, `UP_MENU`, `MENU_PATH`, `SORT_NO`, `IS_ENABLE`, `CREATE_USER`, `CREATE_TIME`) values ( 'FLOW_BATCH', '批量充值', '10', null, '4', '1', 'admin', '2016-08-13 18:36:48');
insert into `flow`.`t_sys_menu` ( `MENU_CODE`, `MENU_NAME`, `UP_MENU`, `MENU_PATH`, `SORT_NO`, `IS_ENABLE`, `CREATE_USER`, `CREATE_TIME`) values ( 'FLOW_PHONE_MANAGE', '号段管理', '0', null, '4', '1', 'admin', '2016-08-14 11:16:34');
insert into `flow`.`t_sys_menu` ( `MENU_CODE`, `MENU_NAME`, `UP_MENU`, `MENU_PATH`, `SORT_NO`, `IS_ENABLE`, `CREATE_USER`, `CREATE_TIME`) values ( 'FLOW_PHONE', '号段管理', '15', '/portal/mobile!selectPage.action', '1', '1', 'admin', '2016-08-14 11:17:46');