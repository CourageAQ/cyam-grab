/*
Navicat MySQL Data Transfer

Source Server         : wang
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : testwang

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2016-10-31 16:00:06
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `coalprice`
-- ----------------------------
DROP TABLE IF EXISTS `coalprice`;
CREATE TABLE `coalprice` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `heat` varchar(255) NOT NULL,
  `nowpeace` varchar(255) NOT NULL,
  `lastpeace` varchar(255) NOT NULL,
  `Degree` varchar(255) NOT NULL,
  `huanbi` varchar(255) NOT NULL,
  `lasttime` varchar(255) NOT NULL,
  `tongbi` varchar(255) NOT NULL,
  `date` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of coalprice
-- ----------------------------
