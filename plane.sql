/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : localhost:3306
 Source Schema         : plane

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 17/04/2022 19:12:44
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for flight
-- ----------------------------
DROP TABLE IF EXISTS `flight`;
CREATE TABLE `flight`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `flightNum` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `origin` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `destination` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `startTime` datetime(6) NULL DEFAULT NULL,
  `arrivalTime` datetime(6) NULL DEFAULT NULL,
  `amount` int(10) NULL DEFAULT NULL,
  `price` int(10) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of flight
-- ----------------------------
INSERT INTO `flight` VALUES (1, 'SD4001', '青岛', '北京', '2022-04-06 19:48:19.000000', '2022-04-05 19:30:00.000000', 15, 489);
INSERT INTO `flight` VALUES (2, 'NH1809', '上海', '广东', '2022-04-05 19:48:19.000000', '2022-04-06 19:48:24.000000', 24, 998);
INSERT INTO `flight` VALUES (3, 'NH1809', '上海', '广东', '2022-04-05 19:48:19.000000', '2022-04-06 19:48:24.000000', 24, 998);
INSERT INTO `flight` VALUES (4, 'NH1809', '北京', '广东', '2022-04-05 19:48:19.000000', '2022-04-06 19:48:24.000000', 24, 998);
INSERT INTO `flight` VALUES (5, 'NH1809', '四川', '上海', '2022-04-06 19:48:19.000000', '2022-04-06 19:48:24.000000', 22, 998);
INSERT INTO `flight` VALUES (6, 'NH1809', '成都', '北京', '2022-04-05 19:48:19.000000', '2022-04-06 19:48:24.000000', 24, 998);
INSERT INTO `flight` VALUES (7, 'NH1809', '北京', '广东', '2022-04-05 19:48:19.000000', '2022-04-06 19:48:24.000000', 24, 998);
INSERT INTO `flight` VALUES (8, 'NH1809', '哈尔滨', '广东', '2022-04-05 19:48:19.000000', '2022-04-06 19:48:24.000000', 24, 998);
INSERT INTO `flight` VALUES (9, 'NH1809', '西安', '哈尔滨', '2022-04-05 19:48:19.000000', '2022-04-06 19:48:24.000000', 24, 998);
INSERT INTO `flight` VALUES (10, 'NH1809', '西安', '青岛', '2022-04-05 19:48:19.000000', '2022-04-06 19:48:24.000000', 24, 998);

-- ----------------------------
-- Table structure for myorder
-- ----------------------------
DROP TABLE IF EXISTS `myorder`;
CREATE TABLE `myorder`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `flight_id` int(10) NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `idNumber` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `phoneNum` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `user_id` int(10) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of myorder
-- ----------------------------
INSERT INTO `myorder` VALUES (1, 1, 'wang', '123', '456', 1);
INSERT INTO `myorder` VALUES (2, 1, 'iPhone', '3707866620001000', '12345678910', 1);
INSERT INTO `myorder` VALUES (3, 1, '大家好', '3707866620001000', '166579786', NULL);
INSERT INTO `myorder` VALUES (4, 1, '大家好', '3707866620001000', '16566637763', NULL);
INSERT INTO `myorder` VALUES (5, 1, '啊这', '3737000009859610', '16677889910', NULL);
INSERT INTO `myorder` VALUES (6, 1, '王一辰', '3707866620001000', '12345678910', NULL);
INSERT INTO `myorder` VALUES (7, 5, '控制变量', '123', '222', NULL);
INSERT INTO `myorder` VALUES (8, 1, 'zhou', '370102030405060710', '16566637763', NULL);
INSERT INTO `myorder` VALUES (9, 5, 'xing', '3707866620001000', '16677889910', 1);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `nickname` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `sex` varchar(2) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `idNumber` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `phoneNum` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'wangyichen', '王一辰', '1', '370786200003235410', '17561737268', '123');

SET FOREIGN_KEY_CHECKS = 1;
