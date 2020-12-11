-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: localhost    Database: libmanage
-- ------------------------------------------------------
-- Server version	8.0.22

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
  `category` varchar(50) DEFAULT NULL,
  `dayadded` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `books`
--

LOCK TABLES `books` WRITE;
/*!40000 ALTER TABLE `books` DISABLE KEYS */;
INSERT INTO `books` VALUES (8,'Cơ sở lập trình',2020,'Nhập môn lập trình cơ bản','Trường đại học Mở TP.HCM','Giảng viên khoa CNTT','Khu A','Tài liệu','2020-10-21'),(9,'Lập trình giao diện',NULL,'Tài liệu môn học lập trình giao diện','Đại học quốc gia TP.HCM','Nguyễn Thị Mai Trang','Khu A','Tài liệu',NULL),(10,'13 lý do tại sao',2007,'Tiểu thuyết dành cho người lớn được viết vào năm 2007 bới Jay Asher...','Razorbill','Jay Asher','Khu D','Tiểu thuyết',NULL),(11,'GIÁO TRÌNH NHỮNG NGUYÊN LÝ CƠ BẢN CỦA CHỦ NGHĨA MÁC - LÊNIN',2018,'Thực hiện các Nghị quyết của Đảng Cộng sản Việt Nam, nhất là Nghị quyết Trung ương 5 (khóa X) về công tác tư tưởng, lý luận và báo chí trước yêu cầu mới, ngày 18/9/2008, Bộ Giáo dục và Đào tạo đã ban hành Quyết định số 52/2008/QĐ-BGĐT ban hành Chương trình môn học Những Nguyên Lý Cơ Bản Của Chủ Nghĩa Mác - Lênin dành cho sinh viên khối không chuyên ngành Mác - Lênin, tư tưởng Hồ Chí Minh và phối hợp với Nhà xuất bản Chính trị quốc gia Sự thật xuất bản Giáo Trình Những Nguyên Lý Cơ Bản Của Chủ Nghĩa Mác - Lênin (Dành cho sinh viên đại học, cao đăng khối không chuyên ngành Mác - Lênin, tư tưởng Hồ Chí Minh)','Chính trị Quốc Gia Sự Thật','Bộ giáo dục và đào tạo','Khu C','Chính trị - Pháp luật',NULL),(14,'abc',2020,'fasavzxvxzgas','d','b','àasfa','c',NULL),(15,'c',2020,'fasavzxvxzgas','dâfafafaf','dddđ','àasfa','càafafa','2020-12-06'),(16,'Cơ sở lập trình',2020,'Nhập môn lập trình cơ bản','Trường đại học Mở TP.HCM','Giảng viên khoa CNTT','Khu A','Tài liệu','2020-12-06');
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
  CONSTRAINT `fk_books_id` FOREIGN KEY (`books_id`) REFERENCES `books` (`id`),
  CONSTRAINT `fk_users_id` FOREIGN KEY (`users_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `borrow`
--

LOCK TABLES `borrow` WRITE;
/*!40000 ALTER TABLE `borrow` DISABLE KEYS */;
INSERT INTO `borrow` VALUES ('00cffd3c-da9a-4c99-bd15-6ef72606d8ba',3,9,'2020-12-10','2021-01-09',NULL),('03ad6ff5-b7ac-4937-8314-2fae6a5040d6',3,9,'2020-12-10','2021-01-09',NULL),('44062dcc-69e2-4c4a-8f02-757658987cbf',3,8,'2020-12-10','2021-01-09',NULL),('6ef52f51-038c-497d-8ea8-465fd679798c',6,8,'2020-12-10','2021-01-09','2020-12-10'),('9547783b-a13c-44a2-bf69-a68ee51a2d8a',4,10,'2020-12-10','2021-01-09',NULL),('ac35bd62-8cd1-45d9-8d65-5468278cbbec',4,8,'2020-12-10','2021-01-09',NULL),('b337e7df-46dd-4bee-ba9a-14c39e8cf0a6',1,8,'2020-12-10','2021-01-09','2020-12-10'),('cabbce01-253e-4759-b11e-4b825c8e95af',3,10,'2020-12-10','2021-01-09',NULL),('e7054210-174c-4ea3-95ea-ad4246ad9e5d',3,11,'2020-12-10','2021-01-09','2020-12-11');
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
  `department` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `manager`
--

LOCK TABLES `manager` WRITE;
/*!40000 ALTER TABLE `manager` DISABLE KEYS */;
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
  `surname` varchar(45) DEFAULT NULL,
  `firstname` varchar(60) DEFAULT NULL,
  `sex` varchar(60) DEFAULT NULL,
  `dateofbirth` varchar(50) DEFAULT NULL,
  `position` varchar(60) DEFAULT NULL,
  `department` varchar(60) DEFAULT NULL,
  `createddate` date DEFAULT NULL,
  `expirieddate` date DEFAULT NULL,
  `email` varchar(60) DEFAULT NULL,
  `address` varchar(60) DEFAULT NULL,
  `phone` varchar(50) DEFAULT NULL,
  `borrow` tinyint DEFAULT NULL,
  PRIMARY KEY (`id`,`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'minh.pq','Phạm Quang','Minh','Nam','2000-06-11','Sinh Viên','Khoa Công nghệ thông tin','2017-11-18','2020-12-31','pminh723@gmail.com','667/3 Tân Sơn, p12, quận Gò Vấp','388514487',0),(3,'A','B','C','D','E','F','G','2020-11-11','2023-11-11','aaaaaa','ccccccc','ddddddddd',0),(4,'qfqwfqfq.d',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(5,'adadadq qeqweq.q','qweqwrqraf',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(6,'qfqfqwfqf.a','asdqfqfqf','qfqfqwfqf',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(7,'dasdfqwfqf.s','sdqfqwfqwf','dasdfqwfqf','wfqwfqwqwfqf',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(8,'fasfafasfa eqweqweqwe.e','eqweqdrasfaffasfas','fasfafasfa eqweqweqwe','dqwgaf','2020-11-29',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(9,'qdqwdqrqr.f','fafqwrfqdq','qdqwdqrqr','qwfqwfqwfqwdas','2005-12-07','qdqwfq','fqwfqwfqwf',NULL,NULL,NULL,NULL,NULL,0),(10,'dqwdqdasda.d','dadqwfqwfaf','dqwdqdasda','qdqwdqwdq','2002-12-16','dqqfqwfqw','qfqwfqwfqwfwq','2020-12-07','2023-12-07',NULL,NULL,NULL,0),(11,'dqwfqwgqwgq.q','qwdqwfqgqgas','dqwfqwgqwgq','afqwgfqgqwg','2016-12-15','qwdqgqgq','qwfqwfqwgf','2020-12-07','2023-12-07','fqfqwfwfqw','qwdqwdqdqwrf',NULL,0),(12,'qwqdqwdqdqwd.q','qgfqgqgasga','qwqdqwdqdqwd','fqwfqwfqwfqw','2007-12-05','dqwdq','fqwfqfqwf','2020-12-07','2023-12-07','ffqwfqfqfqfq','fqfqfqwfqffqfqwfqqwf','dqdqfqwqwfqw',0),(14,'adqwdqdq.d','dqdqwfqfqw','adqwdqdq','qgffqwgqwfq','2020-06-15','qwfqfqwf','fqwfqfq','2020-12-07','2023-12-07','fqfqwfqwfqw','fqfqwfq','fqwfqfqw',0),(15,'dasfqfq','dqdqdqd','dqwdqwdqwdqwd','qdqwdqwfqwf','dasda','sdadqdqd','dqwdqwdqd',NULL,NULL,'qdqwdqwdq','dqwdqdqwd',NULL,0),(16,'fqwfqfqwf.d','dqwdqwdqwf','fqwfqfqwf','qqwfqfqf','2013-12-04','sdqdqdf','qwfqwf','2020-12-07','2023-12-07','fqwfqwf','fqwfqwf','fqwfqwfq',0),(18,'qfqwfqfqf.d','dâfqf','qfqwfqfqf','fqwfqwfqwf','2020-04-05','fqwfqwfqwf','sffqwfqwf','2020-12-09','2023-12-09','fqwfs','qfqfqff','qfqwdeqqd',NULL),(19,'dqdsdqdqdqwd.a','afqfqqwd','dqdsdqdqdqwd','fqfqwfqwqwdqd','2000-12-19','dqdasdqw','qdqdqdqd','2020-12-09','2023-12-09','qừqwqwrqw','qừqweqweqwd','qưdqwdqwdq',NULL),(20,'dqdqdqd.đ','đầqừ','dqdqdqd','qdqqdqd','2000-12-06','dqwdqdq','qưdqdqwd','2020-12-09','2023-12-09','dqfqwfqwfq','qfqwfqwqwd','qưdqwdqwdqw',NULL),(21,'fqwfqwqff.d','dqwqwdqwd','fqwfqwqff','qdqwdqwd','1999-12-15','qwdqwdqw','dqwdqqw','2020-12-09','2023-12-09','fqwfqwfqw','fqwfqwfqw','qdqwdqwdqd',NULL),(22,'dqwdqwdqwdqw.đ','đáqrdqửqưdq','dqwdqwdqwdqw','qdqwdqwdqwd','2000-12-04','dqwdqdq','dqwdqwd','2020-12-09','2023-12-09','qưdqwdqwd','dqdqwdq','dqwdqwdq',NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-12-11  0:57:04
