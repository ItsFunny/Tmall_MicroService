/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50722
 Source Host           : localhost:3306
 Source Schema         : JN

 Target Server Type    : MySQL
 Target Server Version : 50722
 File Encoding         : 65001

 Date: 13/06/2018 13:03:50
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for dlxy_article
-- ----------------------------
DROP TABLE IF EXISTS `dlxy_article`;
CREATE TABLE `dlxy_article` (
  `article_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `article_name` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL,
  `article_author` varchar(16) COLLATE utf8mb4_bin DEFAULT NULL,
  `article_content` mediumtext COLLATE utf8mb4_bin,
  `article_visit_count` int(11) DEFAULT NULL,
  `create_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_date` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`article_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Table structure for dlxy_article_picture
-- ----------------------------
DROP TABLE IF EXISTS `dlxy_article_picture`;
CREATE TABLE `dlxy_article_picture` (
  `picture_id` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL,
  `article_id` bigint(20) DEFAULT NULL,
  KEY `fk_articleid_in_picture` (`article_id`),
  CONSTRAINT `fk_articleid_in_picture` FOREIGN KEY (`article_id`) REFERENCES `dlxy_article` (`article_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Table structure for dlxy_picture
-- ----------------------------
DROP TABLE IF EXISTS `dlxy_picture`;
CREATE TABLE `dlxy_picture` (
  `picture_id` varchar(64) COLLATE utf8mb4_bin NOT NULL,
  `picture_url` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`picture_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Table structure for dlxy_portal_title
-- ----------------------------
DROP TABLE IF EXISTS `dlxy_portal_title`;
CREATE TABLE `dlxy_portal_title` (
  `title_id` int(11) NOT NULL AUTO_INCREMENT,
  `title_name` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL,
  `title_parent_id` int(10) unsigned zerofill DEFAULT NULL,
  `title_display_sequence` int(4) DEFAULT NULL,
  `create_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`title_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Table structure for dlxy_title_article
-- ----------------------------
DROP TABLE IF EXISTS `dlxy_title_article`;
CREATE TABLE `dlxy_title_article` (
  `title_id` int(11) DEFAULT NULL,
  `article_id` bigint(20) DEFAULT NULL,
  `create_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_date` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  KEY `fk_titleid_in_article` (`title_id`),
  CONSTRAINT `fk_titleid_in_article` FOREIGN KEY (`title_id`) REFERENCES `dlxy_portal_title` (`title_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Table structure for dlxy_title_picture
-- ----------------------------
DROP TABLE IF EXISTS `dlxy_title_picture`;
CREATE TABLE `dlxy_title_picture` (
  `picture_id` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL,
  `title_id` int(10) DEFAULT NULL,
  KEY `fk_titleid_in_picture` (`title_id`),
  CONSTRAINT `fk_titleid_in_picture` FOREIGN KEY (`title_id`) REFERENCES `dlxy_portal_title` (`title_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

SET FOREIGN_KEY_CHECKS = 1;
