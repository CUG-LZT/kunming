/*
Navicat MySQL Data Transfer

Source Server         : new
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : pro-archetype

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2019-01-26 17:11:31
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_organization
-- ----------------------------
DROP TABLE IF EXISTS `sys_organization`;
CREATE TABLE `sys_organization` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `parent_ids` varchar(100) DEFAULT NULL,
  `available` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `idx_sys_organization_parent_id` (`parent_id`),
  KEY `idx_sys_organization_parent_ids` (`parent_ids`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_organization
-- ----------------------------
INSERT INTO `sys_organization` VALUES ('1', '总公司', '0', '0/', '1');
INSERT INTO `sys_organization` VALUES ('2', '分公司1', '1', '0/1/', '1');
INSERT INTO `sys_organization` VALUES ('3', '分公司2', '1', '0/1/', '1');
INSERT INTO `sys_organization` VALUES ('4', '分公司11', '2', '0/1/2/', '1');

-- ----------------------------
-- Table structure for sys_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_resource`;
CREATE TABLE `sys_resource` (
  `resId` bigint(20) NOT NULL,
  `resName` varchar(100) DEFAULT NULL,
  `resType` varchar(50) DEFAULT NULL,
  `resUrl` varchar(200) DEFAULT '',
  `resParentId` bigint(20) DEFAULT NULL,
  `resParentIds` varchar(100) DEFAULT NULL,
  `resPermission` varchar(100) DEFAULT NULL,
  `resAvailable` tinyint(1) DEFAULT '0',
  `resIcon` varchar(255) DEFAULT '',
  PRIMARY KEY (`resId`),
  KEY `idx_sys_resource_parent_id` (`resParentId`),
  KEY `idx_sys_resource_parent_ids` (`resParentIds`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_resource
-- ----------------------------
INSERT INTO `sys_resource` VALUES ('1', '根节点', 'menu', '', '0', '0/', '', '1', null);
INSERT INTO `sys_resource` VALUES ('10', '用户角色权限模块', 'menu', '', '1', '0/1/', 'menusys:view', '1', 'icon-user_acl');
INSERT INTO `sys_resource` VALUES ('11', '个人中心模块', 'menu', '', '1', '0/1/', 'menuuser:view', '1', 'icon-linechart');
INSERT INTO `sys_resource` VALUES ('12', '数据统计模块', 'menu', ' ', '1', '0/1/', 'menudata:view', '1', ' ');
INSERT INTO `sys_resource` VALUES ('13', 'test2', 'menu', '123', '1', '0/1/', 'test:*', '1', '123123123');
INSERT INTO `sys_resource` VALUES ('101', '角色管理', 'page', 'sys/role/role.page', '10', '0/1/10/', 'pagerole:*', '1', 'icon-user');
INSERT INTO `sys_resource` VALUES ('102', '资源管理', 'page', 'sys/res/res.page', '10', '0/1/10/', 'pageresource:*', '1', 'icon-acl');
INSERT INTO `sys_resource` VALUES ('103', '用户管理', 'page', 'sys/user/user.page', '10', '0/1/10/', 'pageuser:*', '1', 'icon-user_acl');
INSERT INTO `sys_resource` VALUES ('110', '修改资料', 'page', ' ', '11', '0/1/11/', 'pageuser:*', '1', ' ');
INSERT INTO `sys_resource` VALUES ('111', '修改密码', 'page', ' ', '11', '0/1/11/', 'pageuser:*', '1', ' ');
INSERT INTO `sys_resource` VALUES ('120', '统计查询', 'page', ' ', '12', '0/1/12/', 'orderdata:*', '1', ' ');
INSERT INTO `sys_resource` VALUES ('121', '用户记录', 'page', ' ', '12', '0/1/12/', 'orderdata:*', '1', ' ');
INSERT INTO `sys_resource` VALUES ('130', '123', 'page', '', '13', '0/1/13/', '123', '1', '');
INSERT INTO `sys_resource` VALUES ('1010', '角色新增', 'button', '', '101', '0/1/10/101/', 'pagerole:create', '1', ' ');
INSERT INTO `sys_resource` VALUES ('1011', '角色修改', 'button', '', '101', '0/1/10/101/', 'pagerole:update', '1', ' ');
INSERT INTO `sys_resource` VALUES ('1012', '角色删除', 'button', '', '101', '0/1/10/101/', 'pagerole:delete', '1', ' ');
INSERT INTO `sys_resource` VALUES ('1013', '角色查看', 'button', '', '101', '0/1/10/101/', 'pagerole:view', '1', ' ');
INSERT INTO `sys_resource` VALUES ('1020', '资源新增', 'button', '', '102', '0/1/10/102/', 'pageresource:create', '1', ' ');
INSERT INTO `sys_resource` VALUES ('1021', '资源修改', 'button', '', '102', '0/1/10/102/', 'pageresource:update', '1', ' ');
INSERT INTO `sys_resource` VALUES ('1022', '资源删除', 'button', '', '102', '0/1/10/102/', 'pageresource:delete', '1', ' ');
INSERT INTO `sys_resource` VALUES ('1023', '资源查看', 'button', '', '102', '0/1/10/102/', 'pageresource:view', '1', ' ');
INSERT INTO `sys_resource` VALUES ('1030', '用户新增', 'button', '', '103', '0/1/10/103/', 'pageuser:create', '1', ' ');
INSERT INTO `sys_resource` VALUES ('1031', '用户修改', 'button', '', '103', '0/1/10/103/', 'pageuser:update', '1', ' ');
INSERT INTO `sys_resource` VALUES ('1032', '用户删除', 'button', '', '103', '0/1/10/103/', 'pageuser:delete', '1', ' ');
INSERT INTO `sys_resource` VALUES ('1033', '用户查看', 'button', '', '103', '0/1/10/103/', 'pageuser:view', '1', ' ');
INSERT INTO `sys_resource` VALUES ('1100', '资料修改', 'button', '', '110', '0/1/11/110/', 'pageuser:update', '1', ' ');
INSERT INTO `sys_resource` VALUES ('1110', '密码修改', 'button', '', '111', '0/1/11/111/', 'pageuser:update', '1', ' ');
INSERT INTO `sys_resource` VALUES ('1200', '统计查询', 'button', '', '120', '0/1/12/120/', 'orderdata:view', '1', ' ');
INSERT INTO `sys_resource` VALUES ('1210', '用户记录查询', 'button', '', '121', '0/1/12/121/', 'orderdata:view', '1', '');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `roleId` bigint(20) NOT NULL AUTO_INCREMENT,
  `roleName` varchar(100) DEFAULT NULL,
  `roleLevel` int(20) DEFAULT NULL,
  `roleDescription` varchar(100) DEFAULT NULL,
  `roleResourceIds` varchar(255) DEFAULT NULL,
  `roleAvailable` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`roleId`),
  KEY `idx_sys_role_resource_ids` (`roleResourceIds`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', 'super ', '10', '超管', '101,102,103,110,111,120,121,10,11,12', '1');
INSERT INTO `sys_role` VALUES ('2', 'user  ', '0', '用户', '101,102,103,110,111,120,121,10,11,12', '1');
INSERT INTO `sys_role` VALUES ('3', 'worker  ', '0', '工作人员', '110,111,120,121,11,12', '1');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `userId` bigint(20) NOT NULL AUTO_INCREMENT,
  `userCode` bigint(20) DEFAULT NULL,
  `userName` varchar(100) DEFAULT NULL,
  `userPsd` varchar(100) DEFAULT NULL,
  `userSalt` varchar(100) DEFAULT NULL,
  `userRoleIds` varchar(100) DEFAULT NULL,
  `userLocked` tinyint(1) DEFAULT '0',
  `userCompany` varchar(255) DEFAULT NULL,
  `userAddress` varchar(255) DEFAULT NULL,
  `userCreateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`userId`),
  UNIQUE KEY `idx_sys_user_username` (`userName`),
  KEY `idx_sys_user_organization_id` (`userCode`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', '1', 'admin', 'd3c59d25033dbf980d29554025c23a75', '8d78869f470951332959580424d4bf4f', '1', '0', '湖北省电子研究所', '湖北省', '2018-01-21 16:34:30');
INSERT INTO `sys_user` VALUES ('2', '3', '2222', '30a8a37961cc75c37106278389017db1', 'cb50e1a8df835c3f5991ef88a0da68d5', '2', '0', '1111', '北京市', '2019-01-21 16:34:34');
INSERT INTO `sys_user` VALUES ('7', '12345', '1', '41c07b6cc042bfd1d03d29dec42fc85a', '797f29ade9bbf80f75935deacdf2d033', '2', '0', '12', '其他', '2019-01-22 17:03:57');
INSERT INTO `sys_user` VALUES ('8', '12345', '2', 'fc0a01808cb8f910c98877aab590d10b', '3471b1e107b6cf898b3add52355fa47e', '2', '0', '2', '其他', '2019-01-22 17:04:02');
INSERT INTO `sys_user` VALUES ('9', '12345', '3', 'd46fdc472e9c326fcc082b452c017dbb', 'd3a78f9a5a3d6c3d029b71009adf8b17', '2', '0', '3', '其他', '2019-01-22 17:04:06');
INSERT INTO `sys_user` VALUES ('10', '12345', '4', '163d859f100a3ac15743960b199ba126', '6ac418036ce26d215384939ed7c79dbe', '2', '0', '4', '其他', '2019-01-22 17:04:12');
INSERT INTO `sys_user` VALUES ('11', '12345', '5', '5850d653af895167458c9209626b6ce5', '116794fa0758d391b8b3bc1d107a2955', '2', '0', '5', '江苏省', '2019-01-22 17:04:17');
INSERT INTO `sys_user` VALUES ('12', '12345', '6', '7d169548df39b351760b990d0360f56a', 'a65e27f98038e1bce86f930e75b1df42', '2', '0', '6', '其他', '2019-01-22 17:04:22');
INSERT INTO `sys_user` VALUES ('13', '12345', '7', '027a0ce01d8076c06dd18b51f15a617d', '702a249e5e960f16245e71832b960d59', '2', '0', '7', '其他', '2019-01-22 17:04:27');
INSERT INTO `sys_user` VALUES ('15', '12345', '9', 'b5aa40a9155bb6abf7c5f66318ff3ca3', '14a40eb5238bc134c13204c64d05188e', '2', '0', '9', '其他', '2019-01-22 17:04:38');
INSERT INTO `sys_user` VALUES ('16', '12345', '8', '6c0f699c8bb8e68f87c3da5942bfd68e', '9bdf38375990ea5dc5a06e608e4de8dd', '2', '0', '822', '其他', '2019-01-22 17:19:20');
INSERT INTO `sys_user` VALUES ('18', '12345', '123123', 'a3f3422fc93b277a7260922032b7106d', '8c763e1f4633f74a0e2a43c6221013ab', '2', '0', '123', '贵州省', '2019-01-23 16:24:15');
INSERT INTO `sys_user` VALUES ('19', '12345', '123123123', '527199f3f028a85c0f2f56eb96de4856', 'c889d498f469347a02cdf76ae36c723e', '3', '0', '123', '其他', '2019-01-23 16:32:45');
INSERT INTO `sys_user` VALUES ('20', '12345', '111', '39dc3e9427ea76a5df9b9b1dbb427df3', '0b59d4d225aaaee086be4afdb81030e9', '3', '0', '111', '其他', '2019-01-23 16:38:33');
INSERT INTO `sys_user` VALUES ('21', '12345', '222', '84ec8356dd491f1bff0da5a0ba0c816d', '9d2b3d40e0c2779798ec836d65f1581c', '3', '0', '222', '其他', '2019-01-23 16:38:39');
INSERT INTO `sys_user` VALUES ('22', '12345', '123', '4540b494fa9819531e43259f87b35401', 'a2c80a59616b1d3d42f428c04b44ac30', '4', '0', '123', '其他', '2019-01-24 16:31:05');
INSERT INTO `sys_user` VALUES ('23', '12345', '312', 'b29ebae80b2438b18561f77674dc2a1d', '699400504c2b32b053b5714169a08fcd', '3', '0', '321', '其他', '2019-01-24 17:15:41');
