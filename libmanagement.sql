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
  `category` varchar(50) DEFAULT NULL,
  `dayadded` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `books`
--

LOCK TABLES `books` WRITE;
/*!40000 ALTER TABLE `books` DISABLE KEYS */;
INSERT INTO `books` VALUES (8,'Cơ sở lập trình',2020,'Nhập môn lập trình cơ bản','Trường đại học Mở TP.HCM','Giảng viên khoa CNTT','Khu A','Tài liệu','2020-10-21'),(9,'Lập trình giao diện',NULL,'Tài liệu môn học lập trình giao diện','Đại học quốc gia TP.HCM','Nguyễn Thị Mai Trang','Khu A','Tài liệu',NULL),(10,'13 lý do tại sao',2007,'Tiểu thuyết dành cho người lớn được viết vào năm 2007 bới Jay Asher...','Razorbill','Jay Asher','Khu D','Tiểu thuyết',NULL),(11,'GIÁO TRÌNH NHỮNG NGUYÊN LÝ CƠ BẢN CỦA CHỦ NGHĨA MÁC - LÊNIN',2018,'Thực hiện các Nghị quyết của Đảng Cộng sản Việt Nam, nhất là Nghị quyết Trung ương 5 (khóa X) về công tác tư tưởng, lý luận và báo chí trước yêu cầu mới, ngày 18/9/2008, Bộ Giáo dục và Đào tạo đã ban hành Quyết định số 52/2008/QĐ-BGĐT ban hành Chương trình môn học Những Nguyên Lý Cơ Bản Của Chủ Nghĩa Mác - Lênin dành cho sinh viên khối không chuyên ngành Mác - Lênin, tư tưởng Hồ Chí Minh và phối hợp với Nhà xuất bản Chính trị quốc gia Sự thật xuất bản Giáo Trình Những Nguyên Lý Cơ Bản Của Chủ Nghĩa Mác - Lênin (Dành cho sinh viên đại học, cao đăng khối không chuyên ngành Mác - Lênin, tư tưởng Hồ Chí Minh)','Chính trị Quốc Gia Sự Thật','Bộ giáo dục và đào tạo','Khu C','Chính trị - Pháp luật',NULL);
/*!40000 ALTER TABLE `books` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `borrow`
--

DROP TABLE IF EXISTS `borrow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `borrow` (
  `users_id` int NOT NULL,
  `books_id` int NOT NULL,
  `startdate` date NOT NULL,
  `enddate` date DEFAULT NULL,
  PRIMARY KEY (`users_id`,`books_id`,`startdate`),
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
/*!40000 ALTER TABLE `borrow` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `surname` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `sex` varchar(45) DEFAULT NULL,
  `dateofbirth` varchar(45) DEFAULT NULL,
  `position` varchar(45) DEFAULT NULL,
  `department` varchar(45) DEFAULT NULL,
  `createddate` date DEFAULT NULL,
  `expirieddate` date DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `phone` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
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

-- Dump completed on 2020-12-02 17:36:20
