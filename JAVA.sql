-- --------------------------------------------------------
-- 호스트:                          127.0.0.1
-- 서버 버전:                        10.4.28-MariaDB - mariadb.org binary distribution
-- 서버 OS:                        Win64
-- HeidiSQL 버전:                  12.5.0.6677
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- javaex 데이터베이스 구조 내보내기
DROP DATABASE IF EXISTS `javaex`;
CREATE DATABASE IF NOT EXISTS `javaex` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;
USE `javaex`;

-- 테이블 javaex.menus 구조 내보내기
DROP TABLE IF EXISTS `menus`;
CREATE TABLE IF NOT EXISTS `menus` (
  `m_category` varchar(5) DEFAULT NULL,
  `m_no` int(11) NOT NULL AUTO_INCREMENT,
  `m_name` varchar(20) NOT NULL,
  `m_price` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`m_name`),
  KEY `m_no` (`m_no`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 테이블 데이터 javaex.menus:~13 rows (대략적) 내보내기
DELETE FROM `menus`;
INSERT INTO `menus` (`m_category`, `m_no`, `m_name`, `m_price`) VALUES
	('Side', 18, '감자튀김', '500'),
	('Main', 14, '맘스버거', '2500'),
	('Main', 15, '버거싸이', '1860'),
	('Main', 16, '베토디', '4020'),
	('Side', 27, '비엔나볶음', '80600'),
	('Drink', 30, '사이다', '1561561'),
	('Side', 21, '사이드 랜덤박스', '60030'),
	('Main', 9, '싸이버거', '1200'),
	('Main', 17, '오징어버거', '6100'),
	('Side', 20, '치킨너겟', '500'),
	('Drink', 29, '콜라', '591916'),
	('Side', 22, '콜팝', '600'),
	('Side', 26, '피카츄돈까스', '600');

-- 테이블 javaex.orders 구조 내보내기
DROP TABLE IF EXISTS `orders`;
CREATE TABLE IF NOT EXISTS `orders` (
  `o_no` int(11) NOT NULL AUTO_INCREMENT,
  `o_table` int(11) NOT NULL DEFAULT 0,
  `o_name` varchar(20) DEFAULT NULL,
  `o_price` int(11) DEFAULT NULL,
  `o_count` int(11) DEFAULT NULL,
  `o_date` datetime DEFAULT NULL,
  `o_state` char(1) DEFAULT 'N',
  PRIMARY KEY (`o_no`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 테이블 데이터 javaex.orders:~25 rows (대략적) 내보내기
DELETE FROM `orders`;
INSERT INTO `orders` (`o_no`, `o_table`, `o_name`, `o_price`, `o_count`, `o_date`, `o_state`) VALUES
	(2, 12, '싸이버거', 520, 2, '2023-06-11 22:43:27', 'Y'),
	(3, 14, '맥너겟', 520, 2, '2023-06-12 01:49:34', 'N'),
	(4, 12, '베토디', 520, 2, '2023-06-12 01:50:54', 'Y'),
	(5, 12, '버거싸이', 520, 2, '2023-06-12 01:51:44', 'Y'),
	(6, 13, '맥너겟', 520, 2, '2023-06-12 01:54:35', 'N'),
	(7, 13, '비엔나볶음', 520, 2, '2023-06-12 01:54:35', 'N'),
	(8, 13, '버거싸이', 520, 2, '2023-06-12 01:54:35', 'N'),
	(9, 13, '베토디', 520, 2, '2023-06-12 01:54:35', 'N'),
	(10, 13, '맘스버거', 520, 2, '2023-06-12 01:54:35', 'N'),
	(11, 13, '사이드 랜덤박스', 520, 2, '2023-06-12 01:54:35', 'N'),
	(12, 13, '사이다', 520, 2, '2023-06-12 01:55:11', 'N'),
	(13, 13, '콜라', 520, 2, '2023-06-12 01:55:11', 'N'),
	(14, 13, '맘스버거', 520, 2, '2023-06-12 01:55:11', 'N'),
	(15, 13, '버거싸이', 520, 2, '2023-06-12 01:55:11', 'N'),
	(16, 13, '베토디', 520, 2, '2023-06-12 01:55:11', 'N'),
	(17, 13, '비엔나볶음', 520, 2, '2023-06-12 01:55:11', 'N'),
	(18, 12, '맘스버거', 520, 2, '2023-06-12 02:06:26', 'Y'),
	(19, 12, '콜라', 520, 2, '2023-06-12 02:08:06', 'Y'),
	(20, 12, '비엔나볶음', 520, 2, '2023-06-12 02:08:06', 'Y'),
	(21, 12, '사이드 랜덤박스', 520, 2, '2023-06-12 02:08:06', 'Y'),
	(22, 12, '치킨너겟', 520, 2, '2023-06-12 02:08:06', 'Y'),
	(23, 12, '버거싸이', 520, 2, '2023-06-12 02:08:06', 'Y'),
	(24, 1, '맘스버거', 2500, 2, '2023-06-12 02:28:41', 'N'),
	(25, 1, '버거싸이', 1860, 2, '2023-06-12 02:28:41', 'N'),
	(26, 1, '베토디', 4020, 4, '2023-06-12 02:28:41', 'N');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
