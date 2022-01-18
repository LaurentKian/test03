/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80027
 Source Host           : localhost:3306
 Source Schema         : weather

 Target Server Type    : MySQL
 Target Server Version : 80027
 File Encoding         : 65001

 Date: 18/01/2022 16:00:59
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for city_info
-- ----------------------------
DROP TABLE IF EXISTS `city_info`;
CREATE TABLE `city_info`  (
  `id` int(0) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `lat` double NOT NULL,
  `lon` double NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of city_info
-- ----------------------------
INSERT INTO `city_info` VALUES (101010100, '北京', 39.90498, 116.40528);
INSERT INTO `city_info` VALUES (101020100, '上海', 31.2317, 121.47264);
INSERT INTO `city_info` VALUES (101230101, '福州', 26.0753, 119.30623);

-- ----------------------------
-- Table structure for weather
-- ----------------------------
DROP TABLE IF EXISTS `weather`;
CREATE TABLE `weather`  (
  `city_id` int(0) NOT NULL,
  `fxDate` datetime(0) NOT NULL,
  `tempMax` int(0) NOT NULL,
  `tempMin` int(0) NOT NULL,
  `textDay` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`city_id`, `fxDate`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of weather
-- ----------------------------
INSERT INTO `weather` VALUES (101010100, '2022-01-18 00:00:00', 3, -7, '晴');
INSERT INTO `weather` VALUES (101010100, '2022-01-19 00:00:00', 0, -8, '多云');
INSERT INTO `weather` VALUES (101010100, '2022-01-20 00:00:00', -1, -7, '多云');
INSERT INTO `weather` VALUES (101020100, '2022-01-18 00:00:00', 11, 5, '晴');
INSERT INTO `weather` VALUES (101020100, '2022-01-19 00:00:00', 14, 5, '多云');
INSERT INTO `weather` VALUES (101020100, '2022-01-20 00:00:00', 10, 4, '多云');
INSERT INTO `weather` VALUES (101121001, '2022-01-18 00:00:00', 9, -3, '晴');
INSERT INTO `weather` VALUES (101121001, '2022-01-19 00:00:00', 8, -2, '多云');
INSERT INTO `weather` VALUES (101121001, '2022-01-20 00:00:00', 3, -3, '阴');
INSERT INTO `weather` VALUES (101230101, '2022-01-18 00:00:00', 13, 10, '阴');
INSERT INTO `weather` VALUES (101230101, '2022-01-19 00:00:00', 18, 11, '多云');
INSERT INTO `weather` VALUES (101230101, '2022-01-20 00:00:00', 16, 11, '阴');
INSERT INTO `weather` VALUES (101230401, '2022-01-18 00:00:00', 15, 12, '阴');
INSERT INTO `weather` VALUES (101230401, '2022-01-19 00:00:00', 19, 12, '多云');
INSERT INTO `weather` VALUES (101230401, '2022-01-20 00:00:00', 17, 13, '阴');

SET FOREIGN_KEY_CHECKS = 1;
