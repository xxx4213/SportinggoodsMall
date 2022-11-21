/*
 Navicat MySQL Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 50728
 Source Host           : localhost:3306
 Source Schema         : supermarket

 Target Server Type    : MySQL
 Target Server Version : 50728
 File Encoding         : 65001

 Date: 15/06/2022 22:35:05
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for addrs
-- ----------------------------
DROP TABLE IF EXISTS `addrs`;
CREATE TABLE `addrs`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `addr` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '收货地址',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收货人',
  `tel` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `uid` int(11) NULL DEFAULT NULL COMMENT '关联id',
  `time` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '时间',
  `isdefault` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否默认收货地址 是 否',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '收货地址表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of addrs
-- ----------------------------
INSERT INTO `addrs` VALUES (4, '上海市静安区南京西路1856号聚安大厦3楼', 'PDD', '1877000000', 1, '2022-01-23 12:35:45 ', '是');

-- ----------------------------
-- Table structure for commit
-- ----------------------------
DROP TABLE IF EXISTS `commit`;
CREATE TABLE `commit`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NULL DEFAULT NULL COMMENT '评论用户id',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '评论内容',
  `img` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '评论图片',
  `time` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '时间',
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评论订单',
  `gid` int(11) NULL DEFAULT NULL COMMENT '关联商品id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '评论表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品名',
  `img` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '图片',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分类',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '价格',
  `num` int(11) NULL DEFAULT NULL COMMENT '数量',
  `time` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '时间',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '介绍',
  `tip` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '描述',
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '上下架',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES (1, '篮球', 'http://localhost:8089/upload/2022/06/15/049d42c0-007f-462d-b7e6-1b1a0d700e03.webp', '球类', 199.00, 17, '2022-06-15 17:08:28 ', '<p>这是篮球1</p>', '这是篮球', '上架');
INSERT INTO `goods` VALUES (2, '维动专业运动护膝篮球装备男女半月板关节跑步膝盖', 'http://localhost:8089/upload/2022/06/15/750b0a1b-b378-4e09-ad87-ffe60668b7cc.webp', '保护用品', 19.00, 99, '2022-06-15 17:13:48 ', '<p><a id=\"J_Itemlist_TLink_601690959255\" href=\"https://click.simba.taobao.com/cc_im?p=%BB%A4%CF%A5&amp;s=1425075505&amp;k=685&amp;e=g1HUOTocF%2BfMwlR1TrL0ZMzeY2AzucfobM7gOlYHh4j%2BDTVBJh%2BzNGBnXDcXGf1miA6wvsLmPiySNvHWvCNfGjvpwMtxaOS5awoKHB0SPJXGVbh4BRd%2F9ywfp4jmrW%2Fd52GiZrswYQAKgtF%2FHrYkChE99bnLkLclz9cQftHNgB5MOmmQ6XxEBZAWnakglhnJJtcHxyYIJ1zFebT0LHnn%2F0iUxsFTK94VV4qWFhzj8v1cyl66WfWQrAxz3DNyelKgVEMnb0iHXMIaj14SMZ5rJwPS2MLBcpFavVVcv7MFiWIdCgSEyUk8beUlWo4zRiScnAwLA6Us2fhn1%2FKWF9%2BaYN3E%2FJH85Y7K1PwruLNSYQytOF3yYHhRtW9B6YuTdIu1%2BVFfNGiqx9PrRQ4jkS70pUdN%2FODGGwl3mc0YfN1OOX0QK9xJh4tySm2Jw2XdTVTtjtJrpMW5%2FA%2F%2FnLwm1jLSImrLfnNAv5gczh1VAo7iU6Fww8nJE3hN%2Fxr4OfCo9GRaMYs%2ForMLBkJQ3b4%2F22MNv9FBr%2BWQ0ZFhR8HPeyUVjbpwRMSrHKvtuUqrGf0QXFr%2BvOt8QuaNqPWjWvAQAJ2KBTcLgaasZ1Mkt0d7GyZVidVCYiy1tCvmbhJcgX%2BzfiRdW8ZsG%2F%2FjLfxaIC2ckfl7PwlL3L1kJUM7ELj7J7VutFc%3D\" target=\"_blank\" trace=\"msrp_auction\" traceidx=\"2\" trace-index=\"2\" trace-nid=\"601690959255\" trace-num=\"48\" trace-price=\"68.80\" trace-pid=\"\">维动专业运动护膝篮球装备男女半月板关节跑步膝盖</a><br></p>', NULL, '上架');

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `gname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品名',
  `num` int(11) NULL DEFAULT NULL COMMENT '数量',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '价格',
  `img` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '商品图片',
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单号',
  `time` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '时间',
  `uid` int(11) NULL DEFAULT NULL COMMENT '关联用户id',
  `saddr` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '收货地址',
  `gid` int(11) NULL DEFAULT NULL COMMENT '关联商品id',
  `stel` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收货电话',
  `sname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收货人',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品分类',
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态 未发货 已发货',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES (1, '篮球', 3, 199.00, 'http://localhost:8089/upload/2022/06/15/049d42c0-007f-462d-b7e6-1b1a0d700e03.webp', '37259981572', '2022-06-15 17:21:24 ', 1, '上海市静安区南京西路1856号聚安大厦3楼', 1, '1877000000', 'PDD', '球类', '已发货');
INSERT INTO `orders` VALUES (2, '维动专业运动护膝篮球装备男女半月板关节跑步膝盖', 1, 19.00, 'http://localhost:8089/upload/2022/06/15/750b0a1b-b378-4e09-ad87-ffe60668b7cc.webp', '37259981572', '2022-06-15 17:21:24 ', 1, '上海市静安区南京西路1856号聚安大厦3楼', 2, '1877000000', 'PDD', '保护用品', '已发货');

-- ----------------------------
-- Table structure for type
-- ----------------------------
DROP TABLE IF EXISTS `type`;
CREATE TABLE `type`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分类名称',
  `time` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品分类表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of type
-- ----------------------------
INSERT INTO `type` VALUES (1, '球类', '2022-06-15 17:06:10 ');
INSERT INTO `type` VALUES (2, '保护用品', '2022-06-15 17:06:17 ');
INSERT INTO `type` VALUES (3, '服饰', '2022-06-15 17:06:20 ');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `img` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '头像',
  `user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '账号',
  `pwd` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `tel` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `time` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '时间',
  `question` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码提示问题',
  `answer` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '答案',
  `addr` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '居住地',
  `sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别 男 女',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '卢本伟', 'http://localhost:8089/upload/2022/06/15/c9b568a8-34c4-4fa2-aecd-e52e487f3496.jpg', 'user', 'user', '18500000000', '2022-06-15 22:34:30 ', '我是谁', '刘谋', '1321321', '男');

SET FOREIGN_KEY_CHECKS = 1;
