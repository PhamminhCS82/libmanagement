-- MySQL dump 10.13  Distrib 8.0.20, for Win64 (x86_64)
--
-- Host: localhost    Database: libmanage
-- ------------------------------------------------------
-- Server version	8.0.20

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `books`
--

DROP TABLE IF EXISTS `books`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `books` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `publish_year` int DEFAULT NULL,
  `describe` longtext,
  `publisher` varchar(100) NOT NULL,
  `authors` varchar(100) NOT NULL,
  `location` varchar(45) DEFAULT NULL,
  `category` varchar(50) NOT NULL,
  `dayadded` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `books`
--

LOCK TABLES `books` WRITE;
/*!40000 ALTER TABLE `books` DISABLE KEYS */;
INSERT INTO `books` VALUES (8,'Cơ sở lập trình',2020,'Nhập môn lập trình cơ bản','Trường đại học Mở TP.HCM','Giảng viên khoa CNTT','Khu A','Tài liệu','2020-10-21'),(9,'Lập trình giao diện',NULL,'Tài liệu môn học lập trình giao diện','Đại học quốc gia TP.HCM','Nguyễn Thị Mai Trang','Khu A','Tài liệu',NULL),(10,'13 lý do tại sao',2007,'Tiểu thuyết dành cho người lớn được viết vào năm 2007 bới Jay Asher...','Razorbill','Jay Asher','Khu D','Tiểu thuyết',NULL),(11,'GIÁO TRÌNH NHỮNG NGUYÊN LÝ CƠ BẢN CỦA CHỦ NGHĨA MÁC - LÊNIN',2018,'Thực hiện các Nghị quyết của Đảng Cộng sản Việt Nam, nhất là Nghị quyết Trung ương 5 (khóa X) về công tác tư tưởng, lý luận và báo chí trước yêu cầu mới, ngày 18/9/2008, Bộ Giáo dục và Đào tạo đã ban hành Quyết định số 52/2008/QĐ-BGĐT ban hành Chương trình môn học Những Nguyên Lý Cơ Bản Của Chủ Nghĩa Mác - Lênin dành cho sinh viên khối không chuyên ngành Mác - Lênin, tư tưởng Hồ Chí Minh và phối hợp với Nhà xuất bản Chính trị quốc gia Sự thật xuất bản Giáo Trình Những Nguyên Lý Cơ Bản Của Chủ Nghĩa Mác - Lênin (Dành cho sinh viên đại học, cao đăng khối không chuyên ngành Mác - Lênin, tư tưởng Hồ Chí Minh)','Chính trị Quốc Gia Sự Thật','Bộ giáo dục và đào tạo','Khu C','Chính trị - Pháp luật',NULL),(14,'Chạy án',2012,'Tiểu thuyết chạy án nổi tiếng','Tuổi Trẻ','Không rõ','Khu D','Tiểu thuyết',NULL),(16,'Kỹ thuật lập trình',2020,'Lập trình cơ bản tiếp theo','Trường đại học Mở TP.HCM','Hồng Thái','Khu A','Tài liệu','2020-12-06'),(18,'Kỹ thuật lập trình',2020,'Lập trình cơ bản tiếp theo','Trường đại học Mở TP.HCM','Hồng Thái','Khu A','Tài liệu','2020-12-14'),(19,'Ứng dụng web',2020,'Lập trình ứng dụng web','Trường đại học Mở TP.HCM','Nguyễn Thị Mai Trang','Khu A','Tài liệu','2020-12-14');
/*!40000 ALTER TABLE `books` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `borrow`
--

DROP TABLE IF EXISTS `borrow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `borrow` (
  `id` varchar(45) NOT NULL,
  `users_id` int NOT NULL,
  `books_id` int NOT NULL,
  `startdate` date NOT NULL,
  `enddate` date NOT NULL,
  `returndate` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_users_id_idx` (`users_id`),
  KEY `fk_books_id_idx` (`books_id`),
  CONSTRAINT `fk_books_id` FOREIGN KEY (`books_id`) REFERENCES `books` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_users_id` FOREIGN KEY (`users_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `borrow`
--

LOCK TABLES `borrow` WRITE;
/*!40000 ALTER TABLE `borrow` DISABLE KEYS */;
INSERT INTO `borrow` VALUES ('154597b8-27bc-4384-931f-fcdd7d07b7f9',1,9,'2020-12-13','2021-01-12',NULL),('5c587b01-7d51-48b3-b77a-0026ae55777d',1,11,'2020-12-13','2021-01-12',NULL),('64a0fd94-6d61-474b-8585-764b1eab126c',1,10,'2020-12-13','2021-01-12',NULL),('6bc38ca0-3af2-4d5c-8bf1-78dab46340db',1,10,'2020-12-13','2021-01-12',NULL),('77d1b868-4ee7-47dd-a9f6-e867b8bef756',1,8,'2020-12-13','2021-01-12',NULL),('b337e7df-46dd-4bee-ba9a-14c39e8cf0a6',1,8,'2020-12-10','2021-01-09','2021-12-10');
/*!40000 ALTER TABLE `borrow` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `manager`
--

DROP TABLE IF EXISTS `manager`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `manager` (
  `id` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `manager`
--

LOCK TABLES `manager` WRITE;
/*!40000 ALTER TABLE `manager` DISABLE KEYS */;
INSERT INTO `manager` VALUES ('admin','e19d5cd5af0378da05f63f891c7467af');
/*!40000 ALTER TABLE `manager` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `userId` varchar(50) NOT NULL,
  `surname` varchar(45) NOT NULL,
  `firstname` varchar(60) NOT NULL,
  `sex` varchar(60) NOT NULL,
  `dateofbirth` varchar(50) NOT NULL,
  `position` varchar(60) NOT NULL,
  `department` varchar(60) NOT NULL,
  `createddate` date NOT NULL,
  `expirieddate` date NOT NULL,
  `email` varchar(60) DEFAULT NULL,
  `address` varchar(60) DEFAULT NULL,
  `phone` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'minh.pq','Phạm Quang','Minh','Nam','2000-06-11','Sinh Viên','Khoa Công nghệ thông tin','2017-11-18','2020-12-31','pminh723@gmail.com','667/3 Tân Sơn, p12, quận Gò Vấp','388514487'),(10,'dqwdqdasda.d','dadqwfqwfaf','dqwdqdasda','qdqwdqwdq','2002-12-16','dqqfqwfqw','qfqwfqwfqwfwq','2020-12-07','2023-12-07',NULL,NULL,NULL),(11,'dqwfqwgqwgq.q','qwdqwfqgqgas','dqwfqwgqwgq','afqwgfqgqwg','2016-12-15','qwdqgqgq','qwfqwfqwgf','2020-12-07','2023-12-07','fqfqwfwfqw','qwdqwdqdqwrf',NULL),(14,'adqwdqdq.d','dqdqwfqfqw','adqwdqdq','qgffqwgqwfq','2020-06-15','qwfqfqwf','fqwfqfq','2020-12-07','2023-12-07','fqfqwfqwfqw','fqfqwfq','fqwfqfqw'),(16,'fqwfqfqwf.d','dqwdqwdqwf','fqwfqfqwf','qqwfqfqf','2013-12-04','sdqdqdf','qwfqwf','2020-12-07','2023-12-07','fqwfqwf','fqwfqwf','fqwfqwfq'),(18,'qfqwfqfqf.d','dâfqf','qfqwfqfqf','fqwfqwfqwf','2020-04-05','fqwfqwfqwf','sffqwfqwf','2020-12-09','2023-12-09','fqwfs','qfqfqff','qfqwdeqqd'),(19,'dqdsdqdqdqwd.a','afqfqqwd','dqdsdqdqdqwd','fqfqwfqwqwdqd','2000-12-19','dqdasdqw','qdqdqdqd','2020-12-09','2023-12-09','qừqwqwrqw','qừqweqweqwd',''),(20,'dqdqdqd.đ','đầqừ','dqdqdqd','qdqqdqd','2000-12-06','dqwdqdq','qưdqdqwd','2020-12-09','2023-12-09','','',''),(21,'fqwfqwqff.d','dqwqwdqwd','fqwfqwqff','qdqwdqwd','1999-12-15','qwdqwdqw','dqwdqqw','2020-12-09','2023-12-09','fqwfqwfqw','fqwfqwfqw','qdqwdqwdqd'),(22,'dqwdqwdqwdqw.đ','đáqrdqửqưdq','dqwdqwdqwdqw','qdqwdqwdqwd','2000-12-04','dqwdqdq','dqwdqwd','2020-12-09','2023-12-09','qưdqwdqwd','dqdqwdq','dqwdqwdq'),(23,'minh.pq','Pham Quang','Minh','Nam','2000-06-11','Sinh viên','Khoa CNTT','2020-12-13','2023-12-13','','',''),(24,'trang.ntm','Nguyễn    thị Mai','Trang','Nữ','1984-07-19','Giảng Viên','Khoa CNTT','2020-12-13','2023-12-13','','',''),(25,'trai.n','Nguyễn','Trãi','Nam','1999-12-20','Sinh Viên','Khoa Kinh Tế','2020-12-13','2023-12-13','','',''),(26,'thanh.pv','Phạm Văn','Thành','Nam','1999-12-14','Sinh Viên','Khoa Luật','2020-12-13','2023-12-13','abcdtahnh@gmail.com','112/3 Tân Sơn Gò Vấp','aaaaaaaaaaaa'),(27,'an.pth','Phạm Thị Hồng','Ân','Nữ','2000-10-04','Sinh Viên','Khoa CNTT','2020-12-14','2023-12-14','assdadqwdqd','',''),(28,'tien.n','Nguyễn','Tiến','Nam','2020-12-07','Sinh Viên','Khoa CNTT','2020-12-14','2023-12-14','','',''),(29,'quynh.nt','Nguyễn Thị','Quỳnh','Nữ','2020-12-07','Sinh Viên','Khoa CNTT','2020-12-14','2023-12-14','','',''),(30,'aaaaas.s','Sssssssa','Aaaaas','Nam','2020-12-11','SV','Khoa KTKT','2020-12-14','2023-12-14','','',''),(31,'aaasssss.a','Aaaaaa','Aaasssss','Nam','2002-12-11','SV','Khoa KTKT','2020-12-14','2023-12-14','','','');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'libmanage'
--

--
-- Dumping routines for database 'libmanage'
--
/*!50003 DROP PROCEDURE IF EXISTS `bookreturnandfinetable` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `bookreturnandfinetable`()
BEGIN
	SELECT books.name, users.surname, users.firstname, startdate, enddate, returndate, datediff(returndate, enddate) * 5000 AS fine
    FROM borrow, books, users
    where books.id = borrow.books_id
    AND users.id = borrow.users_id
    AND returndate is not null;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `countborrowbyyear` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `countborrowbyyear`(IN d int)
BEGIN
SELECT count(borrow.id) AS SLDH FROM borrow WHERE year(startdate) = d;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `getfineneedtopay` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `getfineneedtopay`()
BEGIN
	SELECT datediff(returndate, enddate) * 5000
    FROM borrow
    where returndate is not null;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `userbookstillnotreturn` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `userbookstillnotreturn`(in id int)
BEGIN
	SELECT borrow.id, books.name, books.category, books.authors, books.publisher, borrow.startdate, borrow.enddate
    FROM books, borrow
    WHERE books.id = borrow.books_id
    AND users_id = id
    AND returndate is null;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-12-14 23:37:14
