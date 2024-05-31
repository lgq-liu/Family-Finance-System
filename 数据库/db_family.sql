/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 80025
Source Host           : localhost:3306
Source Database       : db_family02

Target Server Type    : MYSQL
Target Server Version : 80025
File Encoding         : 65001

Date: 2024-05-31 21:42:34
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_account
-- ----------------------------
DROP TABLE IF EXISTS `t_account`;
CREATE TABLE `t_account` (
  `aid` bigint NOT NULL AUTO_INCREMENT COMMENT '账户主键',
  `uid` bigint DEFAULT NULL COMMENT '用户ID',
  `account_des` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '账户说明',
  `account_money` decimal(10,2) DEFAULT NULL COMMENT '金额',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `deleted` tinyint DEFAULT '0' COMMENT '是否删除  0表示删除 1表示不删除',
  PRIMARY KEY (`aid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of t_account
-- ----------------------------
INSERT INTO `t_account` VALUES ('1', '2', '张三的账户', '37953.00', null, '2023-09-15 15:12:15', '2024-05-31 21:12:09', '0');
INSERT INTO `t_account` VALUES ('2', '4', '这是李娜的账户', '20301.56', '这是李娜的账户', '2023-09-15 15:55:08', '2024-05-31 20:17:52', '0');
INSERT INTO `t_account` VALUES ('4', '5', '王美丽账户', '7666.00', '王美丽账户', '2023-09-16 14:42:24', null, '0');
INSERT INTO `t_account` VALUES ('5', '6', '王小强账户', '16688.00', '程序员', '2023-09-16 14:44:57', '2024-05-31 19:37:46', '0');
INSERT INTO `t_account` VALUES ('6', '3', '李丽账户', '15563.50', '李丽账户', '2023-09-18 10:18:51', '2023-09-18 21:22:57', '0');

-- ----------------------------
-- Table structure for t_education
-- ----------------------------
DROP TABLE IF EXISTS `t_education`;
CREATE TABLE `t_education` (
  `eid` bigint NOT NULL AUTO_INCREMENT COMMENT '学历主键',
  `education_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '学历编号',
  `education_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '学历名称',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `deleted` tinyint DEFAULT NULL COMMENT '是否删除  0表示删除 1表示不删除',
  PRIMARY KEY (`eid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of t_education
-- ----------------------------
INSERT INTO `t_education` VALUES ('1', '001', '小学', '小学毕业', '2023-09-13 17:02:23', null, '0');
INSERT INTO `t_education` VALUES ('2', '002', '大专', '大专', '2023-09-14 15:29:00', null, '0');
INSERT INTO `t_education` VALUES ('3', '003', '本科', '本科', '2023-09-14 15:29:22', null, '0');

-- ----------------------------
-- Table structure for t_family
-- ----------------------------
DROP TABLE IF EXISTS `t_family`;
CREATE TABLE `t_family` (
  `fid` bigint NOT NULL AUTO_INCREMENT COMMENT '家庭主键',
  `family_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '家庭编码',
  `family_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '家庭名称',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `deleted` tinyint DEFAULT NULL COMMENT '是否删除  0表示删除 1表示不删除',
  PRIMARY KEY (`fid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of t_family
-- ----------------------------
INSERT INTO `t_family` VALUES ('1', 'ts001', '1号家庭', '1111', '2023-09-12 15:24:43', '2023-09-16 14:23:54', '0');
INSERT INTO `t_family` VALUES ('2', 'qhd01', '2号家庭22', null, '2023-09-12 15:29:44', '2023-09-12 15:31:32', '0');
INSERT INTO `t_family` VALUES ('3', 'se003', '美满一号家庭', '美满一号家庭', '2023-09-13 16:16:44', '2023-09-13 16:40:06', '0');
INSERT INTO `t_family` VALUES ('4', '008', '八号家庭', '八号家庭', '2023-09-13 16:40:31', null, '0');

-- ----------------------------
-- Table structure for t_type
-- ----------------------------
DROP TABLE IF EXISTS `t_type`;
CREATE TABLE `t_type` (
  `tid` bigint NOT NULL AUTO_INCREMENT COMMENT '类型主键',
  `type_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '类型编号',
  `type_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '类型名称',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `deleted` tinyint DEFAULT '0' COMMENT '是否删除  0表示删除 1表示不删除',
  PRIMARY KEY (`tid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of t_type
-- ----------------------------
INSERT INTO `t_type` VALUES ('1', '001', '中国工商银行', '中国工商银行', '2023-09-15 16:37:55', null, '0');
INSERT INTO `t_type` VALUES ('2', '002', '中国农业银行', '中国农业银行', '2023-09-15 17:07:12', null, '0');
INSERT INTO `t_type` VALUES ('3', '003', '中国建设银行', '中国建设银行', '2023-09-15 17:07:35', null, '0');
INSERT INTO `t_type` VALUES ('4', '004', '微信支付', '微信支付', '2023-09-15 17:07:57', null, '0');
INSERT INTO `t_type` VALUES ('5', '005', '支付宝支付', '支付宝支付', '2023-09-15 17:08:13', null, '0');
INSERT INTO `t_type` VALUES ('6', '006', '招商银行', '招商银行', '2023-09-15 17:09:03', null, '0');

-- ----------------------------
-- Table structure for t_type_account
-- ----------------------------
DROP TABLE IF EXISTS `t_type_account`;
CREATE TABLE `t_type_account` (
  `ttid` bigint NOT NULL AUTO_INCREMENT COMMENT '账户类型主键',
  `aid` bigint DEFAULT NULL COMMENT '账户ID',
  `account_description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '账户描述说明',
  `account_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '账户编码',
  `account_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '账户名称',
  `accrual_money` decimal(10,2) DEFAULT '0.00' COMMENT '发生额 默认为0',
  `balance_money` decimal(10,2) DEFAULT '0.00' COMMENT '余额  默认为0',
  `divert_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '转账对象人姓名',
  `divert_time` datetime DEFAULT NULL COMMENT '转入时间',
  `dvt_id` bigint DEFAULT NULL COMMENT '转入人ID',
  `out_id` bigint DEFAULT NULL COMMENT '转出人ID',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '用户姓名',
  `tid` bigint DEFAULT NULL COMMENT '类型id',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `deleted` tinyint DEFAULT '0' COMMENT '是否删除  0表示删除 1表示不删除',
  `class_type` tinyint DEFAULT '0' COMMENT '收入支出  1表示收入  0表示支出',
  PRIMARY KEY (`ttid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of t_type_account
-- ----------------------------
INSERT INTO `t_type_account` VALUES ('1', '1', '张三工资收入18000元', '004', '微信支付', '18000.00', '1000.00', null, null, '0', '0', '张三', '0', '张三工资收入18000元', '2023-09-17 08:47:29', null, '0', '0');
INSERT INTO `t_type_account` VALUES ('2', '1', '张三工资100元', '004', '微信支付', '100.00', '1100.00', '张三', '2023-09-18 09:09:50', null, '0', '张三', '4', '张三工资100元', '2023-09-18 09:09:50', null, '1', '0');
INSERT INTO `t_type_account` VALUES ('3', '1', '张三投资收入10000元', '004', '微信支付', '10000.00', '11100.00', '张三', '2023-09-18 09:31:58', null, '0', '张三', '4', '张三投资收入10000元', '2023-09-18 09:31:58', null, '1', '0');
INSERT INTO `t_type_account` VALUES ('4', '1', '张三收入100元', '004', '微信支付', '100.00', '11300.00', '张三', '2023-09-18 09:58:34', null, '0', '张三', '4', '张三收入100元', '2023-09-18 09:58:34', null, '1', '0');
INSERT INTO `t_type_account` VALUES ('5', '6', '李丽的账户', '004', '微信支付', '0.00', '11200.00', null, null, null, null, '李丽', '4', null, '2023-09-18 10:20:59', '2023-09-18 21:22:57', '0', '0');
INSERT INTO `t_type_account` VALUES ('12', '6', '转100元给李丽', '004', '微信支付', '100.00', '11100.00', '李丽', '2023-09-18 11:06:09', '0', null, '张三', '4', '转100元给李丽', '2023-09-18 11:06:09', null, '1', '0');
INSERT INTO `t_type_account` VALUES ('14', '6', '给李丽转账100元', '004', '微信支付', '100.00', '11000.00', '李丽', '2023-09-18 11:26:58', '0', null, '张三', '4', '给李丽转账100元', '2023-09-18 11:26:58', null, '1', '0');
INSERT INTO `t_type_account` VALUES ('15', '6', '工资收入100元', '004', '微信支付', '100.00', '11200.00', '李丽', '2023-09-18 12:57:37', '0', '0', null, '0', '工资收入100元', '2023-09-18 10:20:59', null, '1', '0');
INSERT INTO `t_type_account` VALUES ('16', '6', '工资收入100元', '004', '微信支付', '100.00', '11300.00', '李丽', '2023-09-18 13:46:38', null, '0', '李丽', '0', '工资收入100元', '2023-09-18 13:46:38', null, '1', '0');
INSERT INTO `t_type_account` VALUES ('17', '1', '张三投资收入500元', '004', '微信支付', '500.00', '19000.00', '张三', '2023-09-18 19:58:14', '0', '0', '张三', '0', '张三投资收入500元', '2023-09-18 19:58:14', null, '0', '0');
INSERT INTO `t_type_account` VALUES ('18', '1', '张三投资收入500元', '004', '微信支付', '500.00', '19500.00', '张三', '2023-09-18 20:00:30', null, '0', '张三', '0', '张三投资收入500元', '2023-09-18 20:00:30', '2023-09-18 21:05:46', '0', '0');
INSERT INTO `t_type_account` VALUES ('19', '6', '给李丽转300元', '004', '微信支付', '300.00', '19200.00', '张三', '2023-09-18 21:05:46', null, '0', '张三', '0', '给李丽转300元', '2023-09-18 21:05:46', '2023-09-18 21:09:20', '0', '0');
INSERT INTO `t_type_account` VALUES ('20', '6', '给李丽转5元', '004', '微信支付', '5.00', '19195.00', '李丽', '2023-09-18 21:09:20', null, '0', '张三', '0', '给李丽转5元', '2023-09-18 21:09:20', '2023-09-18 21:20:44', '0', '0');
INSERT INTO `t_type_account` VALUES ('21', '6', '给李丽转11.5元', '004', '微信支付', '11.50', '19183.50', '李丽', '2023-09-18 21:20:44', null, '0', '张三', '0', '给李丽转11.5元', '2023-09-18 21:20:44', null, '0', '1');
INSERT INTO `t_type_account` VALUES ('22', '1', '给张三转20元', '004', '微信支付', '20.00', '11180.00', '李丽', '2023-09-18 21:22:57', null, '0', '李丽', '0', '给张三转20元', '2023-09-18 21:22:57', '2023-09-18 21:35:56', '0', '0');
INSERT INTO `t_type_account` VALUES ('23', '1', '给张三转15元', '004', '微信支付', '15.00', '11165.00', '张三', '2023-09-18 21:35:56', null, '0', '李丽', '0', '给张三转15元', '2023-09-18 21:35:56', '2023-09-18 21:36:54', '0', '0');
INSERT INTO `t_type_account` VALUES ('24', '1', '给张三再转5元', '004', '微信支付', '5.00', '11160.00', '张三', '2023-09-18 21:36:54', null, '0', '李丽', '0', '给张三再转5元', '2023-09-18 21:36:54', '2023-09-19 15:43:34', '0', '0');
INSERT INTO `t_type_account` VALUES ('28', '4', '王美丽收入1000元', '001', '中国工商银行', '1000.00', '300.00', null, null, '5', null, '', '1', '王美丽收入1000元', '2023-09-18 23:22:18', null, '0', '0');
INSERT INTO `t_type_account` VALUES ('29', '4', '王美丽收入1000元', '001', '中国工商银行', '1000.00', '1300.00', '', '2023-09-19 09:06:42', null, null, '', '1', '王美丽收入1000元', '2023-09-19 09:06:42', null, '0', '1');
INSERT INTO `t_type_account` VALUES ('30', '1', '给张三转63.5元', '004', '微信支付', '63.50', '11096.50', '张三', '2023-09-19 15:43:34', null, '0', '李丽', '0', '给张三转63.5元', '2023-09-19 15:43:34', '2023-09-19 15:47:12', '0', '0');
INSERT INTO `t_type_account` VALUES ('31', '1', '', '004', '微信支付', '100.00', '11033.00', '张三', '2023-09-19 15:47:12', '0', '0', '李丽', '0', '', '2023-09-19 15:47:12', null, '0', '0');
INSERT INTO `t_type_account` VALUES ('32', '1', '', '004', '微信支付', '100.00', '11233.00', '李娜', '2024-05-31 21:12:09', '2', '0', '张三', '0', '', '2024-05-31 19:17:06', null, '0', '1');
INSERT INTO `t_type_account` VALUES ('33', '5', '王小强账户', '006', '招商银行', '0.00', '16888.00', null, null, '6', null, '王小强', '6', '王小强转存招商银行16888元', '2024-05-31 19:19:16', '2024-05-31 19:20:15', '0', '0');
INSERT INTO `t_type_account` VALUES ('34', '2', '向李娜转100元', '006', '招商银行', '100.00', '16788.00', '李娜', '2024-05-31 19:20:15', '6', '0', '王小强', '0', '', '2024-05-31 19:20:15', '2024-05-31 19:21:04', '0', '0');
INSERT INTO `t_type_account` VALUES ('35', '2', '', '006', '招商银行', '100.00', '16688.00', '李娜', '2024-05-31 19:21:04', '4', '0', '王小强', '0', '', '2024-05-31 19:21:04', null, '0', '1');
INSERT INTO `t_type_account` VALUES ('36', '2', '', '001', '中国工商银行', '200.00', '1000.00', null, null, '0', '0', '李娜', '0', '', '2024-05-31 19:22:00', null, '0', '0');
INSERT INTO `t_type_account` VALUES ('37', '2', '', '001', '中国工商银行', '100.00', '1200.00', '李娜', '2024-05-31 19:22:20', '0', '0', '李娜', '0', '', '2024-05-31 19:22:20', null, '0', '0');
INSERT INTO `t_type_account` VALUES ('38', '2', '', '001', '中国工商银行', '100.00', '1300.00', '李娜', '2024-05-31 19:22:43', '4', '0', '李娜', '0', '', '2024-05-31 19:22:43', '2024-05-31 19:23:39', '0', '0');
INSERT INTO `t_type_account` VALUES ('39', '2', '李娜开户行收入100元', '001', '中国工商银行', '100.00', '1200.00', '王小强', '2024-05-31 19:23:39', '0', '0', '李娜', '0', '工商银行', '2024-05-31 19:23:39', null, '0', '0');
INSERT INTO `t_type_account` VALUES ('40', '2', '李娜开户行收入100元', '001', '中国工商银行', '100.00', '1300.00', '李娜', '2024-05-31 19:34:36', '4', '0', '李娜', '0', '工商银行', '2024-05-31 19:34:36', '2024-05-31 19:36:18', '0', '0');
INSERT INTO `t_type_account` VALUES ('41', '5', '李娜向王小强账户转账1000元', '001', '中国工商银行', '1000.00', '300.00', '王小强', '2024-05-31 19:36:18', '4', '0', '李娜', '0', '', '2024-05-31 19:36:18', '2024-05-31 19:37:46', '0', '0');
INSERT INTO `t_type_account` VALUES ('42', '1', '李娜向张三支出100', '001', '中国工商银行', '100.00', '200.00', '张三', '2024-05-31 19:37:46', '6', '0', '李娜', '0', '', '2024-05-31 19:37:46', '2024-05-31 19:52:05', '0', '0');
INSERT INTO `t_type_account` VALUES ('45', '1', '', '001', '中国工商银行', '100.00', '100.00', '张三', '2024-05-31 19:52:05', '2', '0', '李娜', '0', '', '2024-05-31 19:52:05', '2024-05-31 19:54:58', '0', '0');
INSERT INTO `t_type_account` VALUES ('46', '2', '', '001', '中国工商银行', '10000.00', '0.00', '张三', '2024-05-31 19:54:58', '0', '0', '李娜', '0', '', '2024-05-31 19:54:58', null, '0', '0');
INSERT INTO `t_type_account` VALUES ('47', '2', '', '001', '中国工商银行', '10000.00', '10000.00', '李娜', '2024-05-31 20:02:50', '4', '0', '李娜', '0', '', '2024-05-31 20:02:50', '2024-05-31 20:17:52', '0', '0');
INSERT INTO `t_type_account` VALUES ('48', '1', '', '001', '中国工商银行', '10.00', '9990.00', '张三', '2024-05-31 20:17:52', '4', null, '李娜', null, null, '2024-05-31 20:17:52', '2024-05-31 21:12:09', '0', '0');
INSERT INTO `t_type_account` VALUES ('51', '1', '李娜向张三转账100元', '001', '中国工商银行', '100.00', '9890.00', '张三', '2024-05-31 21:12:09', '2', '0', '李娜', '0', '', '2024-05-31 21:12:09', null, '0', '1');

-- ----------------------------
-- Table structure for t_users
-- ----------------------------
DROP TABLE IF EXISTS `t_users`;
CREATE TABLE `t_users` (
  `uid` bigint NOT NULL AUTO_INCREMENT COMMENT '用户主键',
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '用户名',
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '密码',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '用户姓名',
  `sex` tinyint DEFAULT NULL COMMENT '用户性别  0表示男   1表示女',
  `occupation` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '用户职业',
  `eid` bigint DEFAULT NULL COMMENT '学历Id',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '用户信息描述',
  `fid` bigint DEFAULT NULL COMMENT '家庭Id',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `deleted` tinyint DEFAULT NULL COMMENT '是否删除  0表示删除 1表示不删除',
  `is_type` tinyint DEFAULT '0' COMMENT '开通账户  0表示没有开通 1表示开通',
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of t_users
-- ----------------------------
INSERT INTO `t_users` VALUES ('1', 'admin', 'e10adc3949ba59abbe56e057f20f883e', '管理员', '0', '系统管理员', null, '我是系统管理员', null, null, '2023-09-14 15:33:24', null, '0', '0');
INSERT INTO `t_users` VALUES ('2', 'zhangsan', 'e10adc3949ba59abbe56e057f20f883e', '张三', '0', 'java开发工程师', '3', '优秀的java开发工程师', '1', null, '2023-09-15 14:28:27', null, '0', '1');
INSERT INTO `t_users` VALUES ('3', 'lili', 'e10adc3949ba59abbe56e057f20f883e', '李丽', '1', '前端开发工程师', '3', '前端开发工程师', '1', '前端开发工程师', '2023-09-15 14:36:08', null, '0', '1');
INSERT INTO `t_users` VALUES ('4', 'lina', 'b0baee9d279d34fa1dfd71aadb908c3f', '李娜', '1', '产品经理', '2', '产品经理', '3', '产品经理', '2023-09-15 14:37:07', null, '0', '1');
INSERT INTO `t_users` VALUES ('5', '王美丽', 'e10adc3949ba59abbe56e057f20f883e', '', '1', 'python工程师', '3', 'python工程师', '1', 'python工程师', '2023-09-16 14:41:56', null, '0', '1');
INSERT INTO `t_users` VALUES ('6', 'xiaoqiang', 'e10adc3949ba59abbe56e057f20f883e', '王小强', '0', 'C++工程师', '3', 'C++工程师', '1', 'C++工程师', '2023-09-16 14:44:25', null, '0', '1');
