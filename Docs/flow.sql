/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : flow

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2016-07-12 01:04:22
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_base_city
-- ----------------------------
DROP TABLE IF EXISTS `t_base_city`;
CREATE TABLE `t_base_city` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `CITY_NAME` varchar(32) DEFAULT NULL COMMENT '地市名称',
  `PROVINCE_CODE` varchar(32) DEFAULT NULL COMMENT '所属省份',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_base_mobile
-- ----------------------------
DROP TABLE IF EXISTS `t_base_mobile`;
CREATE TABLE `t_base_mobile` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `MOBILE_CODE` varchar(11) DEFAULT NULL COMMENT '号段编号',
  `CITY_CODE` int(11) DEFAULT NULL COMMENT '地市编码',
  `OPERATOR_CODE` int(11) DEFAULT NULL COMMENT '运营商编码',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_base_operator
-- ----------------------------
DROP TABLE IF EXISTS `t_base_operator`;
CREATE TABLE `t_base_operator` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `OPERATOR_NAME` varchar(32) DEFAULT NULL COMMENT '运营商名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='运营商表';

-- ----------------------------
-- Table structure for t_base_province
-- ----------------------------
DROP TABLE IF EXISTS `t_base_province`;
CREATE TABLE `t_base_province` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `PROVINCE_NAME` varchar(32) DEFAULT NULL COMMENT '省份名称',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='省份表';

-- ----------------------------
-- Table structure for t_flow_cost_flow
-- ----------------------------
DROP TABLE IF EXISTS `t_flow_cost_flow`;
CREATE TABLE `t_flow_cost_flow` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `ORDER_CODE` varchar(32) DEFAULT NULL COMMENT '平台订单编号',
  `DISTRIBUTOR_CODE` varchar(255) DEFAULT NULL COMMENT '下游编码',
  `COST` double(10,2) DEFAULT NULL COMMENT '流水金额',
  `Current_balance` double(10,2) DEFAULT NULL COMMENT '当前金额',
  `type` int(11) DEFAULT NULL COMMENT '类型（0：扣款，1：退款）',
  `Creat_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_flow_distributor
-- ----------------------------
DROP TABLE IF EXISTS `t_flow_distributor`;
CREATE TABLE `t_flow_distributor` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `DISTRBUTOR_CODE` varchar(32) DEFAULT NULL COMMENT '下游编码(业务主键)',
  `USER_CODE` varchar(32) DEFAULT NULL COMMENT '用户编码（外键）',
  `OPERATOR_CODE` varchar(32) DEFAULT NULL COMMENT '运营商编码',
  `CHANNEL_TYPE` int(11) DEFAULT '0' COMMENT '渠道类型( 0 :  分销  1：营销 ) ',
  `COOP_MODEL` int(11) DEFAULT '0' COMMENT '合作模式( 0 :  批发  1: 分成) ',
  `COMPANY` varchar(64) DEFAULT NULL COMMENT '公司名称',
  `BALANCE` double DEFAULT '0' COMMENT '余额',
  `FREEZING` double DEFAULT '0' COMMENT '冻结金额',
  `TOTAL` double DEFAULT '0' COMMENT '消费总额',
  `SMS_CONTENT` varchar(200) DEFAULT NULL COMMENT '短信内容模版',
  `STATE` int(4) DEFAULT '1' COMMENT '下游状态（0：禁用 1：正常）',
  `LEVEL` int(4) DEFAULT '0' COMMENT '服务级别（0：普通 1：一级 2：特技）',
  `CALLBACK_URL` varchar(100) DEFAULT NULL COMMENT '回调URL',
  `CONFINING_IP` varchar(100) NOT NULL COMMENT 'ip白名单',
  `APP_KEY` varchar(64) DEFAULT NULL COMMENT '密钥',
  `SECRET_KEY` varchar(64) DEFAULT NULL COMMENT '密钥',
  `FATHER_CODE` varchar(32) DEFAULT '0' COMMENT '二级分销商的父分销商编码',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='下游表';

-- ----------------------------
-- Table structure for t_flow_order
-- ----------------------------
DROP TABLE IF EXISTS `t_flow_order`;
CREATE TABLE `t_flow_order` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ORDER_CODE` varchar(32) DEFAULT NULL COMMENT '订单编号',
  `PROVIDER_ORDER_CODE` varchar(32) DEFAULT NULL COMMENT '上游订单编号',
  `DISTRIBUTOR_ORDER_CODE` varchar(32) DEFAULT NULL COMMENT '下游订单编号',
  `OPERATOR_CODE` varchar(32) DEFAULT NULL COMMENT '运营商编码',
  `PROVIDER_CODE` varchar(32) DEFAULT NULL COMMENT '上游编号',
  `DISTRIBUTOR_CODE` varchar(32) DEFAULT NULL COMMENT '下游编号',
  `PHONE` varchar(16) DEFAULT NULL COMMENT '手机号',
  `PHONE_PROVINCE` varchar(32) DEFAULT NULL COMMENT '手机号所在省',
  `PHONE_CITY` varchar(32) DEFAULT NULL COMMENT '手机号所在市',
  `PRODUCT_CODE` varchar(32) DEFAULT NULL COMMENT '流量包',
  `SIZE` int(11) DEFAULT NULL COMMENT '流量包大小，以M为最小单位，1G=1024M',
  `PRICE` double(10,2) DEFAULT NULL COMMENT '运营商价格(单位：元）',
  `DISCOUNT` double(4,2) DEFAULT NULL COMMENT '购买折扣（60代表6折）',
  `PURCHASED` double(10,2) DEFAULT NULL COMMENT '购买价格',
  `REAL_DISCOUNT` double(4,2) DEFAULT NULL COMMENT '实际购买折扣（60代表6折）',
  `REAL_PURCHASED` double(10,2) DEFAULT NULL COMMENT '实际购买价格',
  `STATE` int(11) DEFAULT NULL COMMENT '充值状态（0：处理中，1：充值成功，2：充值失败）',
  `PLATFORM_CODE` varchar(8) DEFAULT NULL COMMENT '平台状态码',
  `NOTICE_STATE` int(11) DEFAULT '1' COMMENT '通知下游状态(0 : 成功 ，1：失败)',
  `ERROR_TIME` int(11) DEFAULT NULL COMMENT '通知下游错误次数',
  `CREATE_DATE` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `CALLBACK_DATE` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '回调时间',
  `CALLBACK_CODE` varchar(16) DEFAULT NULL COMMENT '回调状态码',
  `CALLBACK_CODE_MESS` varchar(100) DEFAULT NULL COMMENT '回调状态码描述',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='上游订单表';

-- ----------------------------
-- Table structure for t_flow_product
-- ----------------------------
DROP TABLE IF EXISTS `t_flow_product`;
CREATE TABLE `t_flow_product` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `PRODUCT_CODE` varchar(32) DEFAULT NULL COMMENT '流量包编码',
  `PRODCUT_NAME` varchar(64) DEFAULT NULL COMMENT '流量包名称',
  `OPERATOR_CODE` varchar(32) DEFAULT NULL COMMENT '运营商ID',
  `PROVIDER_CODE` varchar(32) DEFAULT NULL COMMENT '上游编码',
  `PRO_PRODUCT_CODE` varchar(32) DEFAULT NULL COMMENT '上游流量包编码',
  `PROVINCE_CODE` varchar(32) DEFAULT NULL COMMENT '区域ID',
  `ENABLE_AREA` int(4) DEFAULT NULL COMMEN '使用范围（0：全国，2：省）'
  `ENABLE_TYPE` int(4) DEFAULT NULL COMMENT '生效类型（0：立即生效，1次月生效 ， 2：24小时后生效，3：当天生效，4：当月生效）',
  `PRIORITY` int(4) DEFAULT NULL COMMENT '优先级（0：低 1 ：中 2：高）',
  `SIZE` int(11) DEFAULT NULL COMMENT '流量包大小，以M为最小单位，1G=1024M',
  `PRICE` double(10,2) DEFAULT NULL COMMENT '运营商价格(单位：元）',
  `DISCOUNT` double(4,2) DEFAULT NULL COMMENT '折扣（60代表6折）',
  `PURCHASED` double(10,2) DEFAULT NULL COMMENT '购买价格',
  `STATE` int(4) DEFAULT NULL COMMENT '状态(0：禁用 1：激活）',
  `DESCRIPTION` varchar(200) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='流量包表';

-- ----------------------------
-- Table structure for t_flow_provider
-- ----------------------------
DROP TABLE IF EXISTS `t_flow_provider`;
CREATE TABLE `t_flow_provider` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `PROVIDER_CODE` varchar(32) DEFAULT NULL COMMENT '上游编码（业务主键）',
  `NAME` varchar(64) DEFAULT NULL COMMENT '上游名称',
  `DESCRIPTION` varchar(200) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='上游表';

-- ----------------------------
-- Table structure for t_flow_recharge_flow
-- ----------------------------
DROP TABLE IF EXISTS `t_flow_recharge_flow`;
CREATE TABLE `t_flow_recharge_flow` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `Distributor_code` varchar(32) DEFAULT NULL COMMENT '下游编码',
  `Balance_before_recharge` double DEFAULT NULL COMMENT '充值前余额',
  `Recharge` double DEFAULT NULL COMMENT '充值金额',
  `Balance_after_recharge` double DEFAULT NULL COMMENT '充值后金额',
  `Create_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `Type` int(11) DEFAULT NULL COMMENT '类型（正常加款…）',
  `USER_CODE` varchar(32) DEFAULT NULL COMMENT '操作用户',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_flow_refund_flow
-- ----------------------------
DROP TABLE IF EXISTS `t_flow_refund_flow`;
CREATE TABLE `t_flow_refund_flow` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `DISTRIBUTOR_NAME` varchar(64) DEFAULT NULL COMMENT '上游名称',
  `PROVIDER_NAME` varchar(64) DEFAULT NULL COMMENT '供应商名称',
  `PHONE` varchar(16) DEFAULT NULL COMMENT '手机号',
  `Order_CODE` varchar(32) DEFAULT NULL COMMENT '平台订单编号',
  `PRODCUT_NAME` varchar(64) DEFAULT NULL COMMENT '流量包名称',
  `PURCHASED` double(10,2) DEFAULT NULL COMMENT '购买价格',
  `Provider_is_refund` int(11) DEFAULT NULL COMMENT '上游是否已退款',
  `CREATE_DATE` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_flow_service
-- ----------------------------
DROP TABLE IF EXISTS `t_flow_service`;
CREATE TABLE `t_flow_service` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `SERVICE_CODE` varchar(32) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '报价单编码(业务主键)',
  `DISTRIBUTOR_CODE` varchar(32) DEFAULT NULL COMMENT '下游编码',
  `FATHER_CODE` varchar(32) DEFAULT '' COMMENT '父ID，默认空',
  `STATE` int(4) DEFAULT NULL COMMENT '状态(0 : 禁用 1：激活）',
  `CREATE_DATE` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `IS_DISPLAY_PROVINCE` int(11) DEFAULT NULL COMMENT '是否显示省包(1:显示  0：不显示)',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='报价单表';

-- ----------------------------
-- Table structure for t_flow_serv_prod
-- ----------------------------
DROP TABLE IF EXISTS `t_flow_serv_prod`;
CREATE TABLE `t_flow_serv_prod` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `SERVICE_CODE` varchar(32) DEFAULT NULL COMMENT '报价单编码',
  `PRODUCT_CODE` varchar(32) DEFAULT NULL COMMENT '流量包编码',
  `DISCOUNT` double DEFAULT NULL COMMENT '折扣',
  `STATE` int(11) DEFAULT NULL COMMENT '状态（0：禁用 1 激活）',
  PRIMARY KEY (`SERV_PROD_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='下游流量包表';

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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='用户表';
