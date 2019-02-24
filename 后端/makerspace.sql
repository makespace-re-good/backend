-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: makerspace
-- ------------------------------------------------------
-- Server version	5.7.17-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `admin_enter`
--

DROP TABLE IF EXISTS `admin_enter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin_enter` (
  `admin_id` int(11) NOT NULL AUTO_INCREMENT,
  `admin_account` varchar(30) DEFAULT NULL,
  `password_code` varchar(255) DEFAULT NULL,
  `authority` int(11) DEFAULT '1',
  `last_enter_time` datetime DEFAULT NULL,
  PRIMARY KEY (`admin_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin_enter`
--

LOCK TABLES `admin_enter` WRITE;
/*!40000 ALTER TABLE `admin_enter` DISABLE KEYS */;
INSERT INTO `admin_enter` VALUES (1,'makerspace','software@110',10,NULL);
/*!40000 ALTER TABLE `admin_enter` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `application_round`
--

DROP TABLE IF EXISTS `application_round`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `application_round` (
  `term_id` int(11) NOT NULL,
  `team_number` smallint(6) DEFAULT NULL,
  `begin_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  PRIMARY KEY (`term_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `application_round`
--

LOCK TABLES `application_round` WRITE;
/*!40000 ALTER TABLE `application_round` DISABLE KEYS */;
/*!40000 ALTER TABLE `application_round` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `audit_step`
--

DROP TABLE IF EXISTS `audit_step`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `audit_step` (
  `audit_step` int(11) NOT NULL,
  `description` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`audit_step`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `audit_step`
--

LOCK TABLES `audit_step` WRITE;
/*!40000 ALTER TABLE `audit_step` DISABLE KEYS */;
INSERT INTO `audit_step` VALUES (30,'未分配座位'),(50,'未录入考勤号'),(60,'未签署入驻文件'),(65,'未设置账号'),(70,'已入驻'),(90,'开始中期评审，未提交材料'),(110,'中期评审进行中'),(130,'通过中期评审'),(150,'开始期满评审，未提交材料'),(170,'期满评审进行中'),(190,'通过期满评审'),(210,'允许续期'),(230,'通过续期'),(250,'正在退出'),(270,'已退出');
/*!40000 ALTER TABLE `audit_step` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `formal_member`
--

DROP TABLE IF EXISTS `formal_member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `formal_member` (
  `team_id` int(11) NOT NULL,
  `order_in_team` smallint(6) NOT NULL,
  `student_id` varchar(20) DEFAULT NULL,
  `student_name` varchar(30) DEFAULT NULL,
  `college` varchar(30) DEFAULT NULL,
  `education_background` varchar(30) DEFAULT NULL,
  `phone_number` varchar(20) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`team_id`,`order_in_team`),
  KEY `formal_member_student_student_id_fk` (`student_id`),
  CONSTRAINT `formal_member_formal_team_team_id_fk` FOREIGN KEY (`team_id`) REFERENCES `formal_team` (`team_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `formal_member_student_student_id_fk` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `formal_member`
--

LOCK TABLES `formal_member` WRITE;
/*!40000 ALTER TABLE `formal_member` DISABLE KEYS */;
/*!40000 ALTER TABLE `formal_member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `formal_team`
--

DROP TABLE IF EXISTS `formal_team`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `formal_team` (
  `team_id` int(11) NOT NULL,
  `team_name` varchar(30) DEFAULT NULL,
  `project_name` varchar(255) DEFAULT NULL,
  `belong_field` varchar(30) DEFAULT NULL,
  `project_type` varchar(30) DEFAULT NULL,
  `project_brief` text,
  `project_director` text,
  `work_foundation` text,
  `plan` text,
  `register_date` date DEFAULT NULL,
  `audit_step` int(11) DEFAULT '30',
  PRIMARY KEY (`team_id`),
  KEY `formal_team_audit_step_audit_step_fk` (`audit_step`),
  CONSTRAINT `formal_team_audit_step_audit_step_fk` FOREIGN KEY (`audit_step`) REFERENCES `audit_step` (`audit_step`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `formal_team`
--

LOCK TABLES `formal_team` WRITE;
/*!40000 ALTER TABLE `formal_team` DISABLE KEYS */;
/*!40000 ALTER TABLE `formal_team` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `middle_term_audit_result`
--

DROP TABLE IF EXISTS `middle_term_audit_result`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `middle_term_audit_result` (
  `team_id` int(11) NOT NULL,
  `tutor_id` int(11) NOT NULL,
  `grade_mark` int(11) DEFAULT NULL,
  `review_opinion` text,
  PRIMARY KEY (`team_id`,`tutor_id`),
  KEY `middle_term_audit_result_tutor_tutor_id_fk` (`tutor_id`),
  CONSTRAINT `middle_term_audit_result_formal_team_team_id_fk` FOREIGN KEY (`team_id`) REFERENCES `formal_team` (`team_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `middle_term_audit_result_tutor_tutor_id_fk` FOREIGN KEY (`tutor_id`) REFERENCES `tutor` (`tutor_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `middle_term_audit_result`
--

LOCK TABLES `middle_term_audit_result` WRITE;
/*!40000 ALTER TABLE `middle_term_audit_result` DISABLE KEYS */;
/*!40000 ALTER TABLE `middle_term_audit_result` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `middle_term_audit_undergoing`
--

DROP TABLE IF EXISTS `middle_term_audit_undergoing`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `middle_term_audit_undergoing` (
  `team_id` int(11) NOT NULL,
  `tutor_id` int(11) NOT NULL,
  `grade_mark` int(11) DEFAULT NULL,
  `review_opinion` text,
  PRIMARY KEY (`team_id`,`tutor_id`),
  KEY `middle_term_audit_undergoing_tutor_tutor_id_fk` (`tutor_id`),
  CONSTRAINT `middle_term_audit_undergoing_formal_team_team_id_fk` FOREIGN KEY (`team_id`) REFERENCES `formal_team` (`team_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `middle_term_audit_undergoing_tutor_tutor_id_fk` FOREIGN KEY (`tutor_id`) REFERENCES `tutor` (`tutor_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `middle_term_audit_undergoing`
--

LOCK TABLES `middle_term_audit_undergoing` WRITE;
/*!40000 ALTER TABLE `middle_term_audit_undergoing` DISABLE KEYS */;
/*!40000 ALTER TABLE `middle_term_audit_undergoing` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seat`
--

DROP TABLE IF EXISTS `seat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `seat` (
  `room_no` int(11) NOT NULL,
  `seat_no` int(11) NOT NULL,
  `student_id` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`room_no`,`seat_no`),
  KEY `seat_student_student_id_fk` (`student_id`),
  CONSTRAINT `seat_student_student_id_fk` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seat`
--

LOCK TABLES `seat` WRITE;
/*!40000 ALTER TABLE `seat` DISABLE KEYS */;
INSERT INTO `seat` VALUES (103,1,NULL),(103,2,NULL),(103,3,NULL),(103,4,NULL),(103,5,NULL),(103,6,NULL),(103,7,NULL),(103,8,NULL),(103,9,NULL),(103,10,NULL),(103,11,NULL),(103,12,NULL),(104,1,NULL),(104,2,NULL),(104,3,NULL),(104,4,NULL),(104,5,NULL),(104,6,NULL),(104,7,NULL),(104,8,NULL),(104,9,NULL),(104,10,NULL),(104,11,NULL),(104,12,NULL),(104,13,NULL),(104,14,NULL),(104,15,NULL),(104,16,NULL),(104,17,NULL),(104,18,NULL),(104,19,NULL),(104,20,NULL),(104,21,NULL),(104,22,NULL),(104,23,NULL),(104,24,NULL),(104,25,NULL),(104,26,NULL),(104,27,NULL),(104,28,NULL),(104,29,NULL),(104,30,NULL),(104,31,NULL),(104,32,NULL),(104,33,NULL),(104,34,NULL),(104,35,NULL),(104,36,NULL),(104,37,NULL),(104,38,NULL),(104,39,NULL),(104,40,NULL),(104,41,NULL),(104,42,NULL),(111,1,NULL),(111,2,NULL),(111,3,NULL),(111,4,NULL),(111,5,NULL),(111,6,NULL),(111,7,NULL),(111,8,NULL),(111,9,NULL),(111,10,NULL),(111,11,NULL),(111,12,NULL),(111,13,NULL),(111,14,NULL),(111,15,NULL),(113,1,NULL),(113,2,NULL),(113,3,NULL),(113,4,NULL),(113,5,NULL),(113,6,NULL),(113,7,NULL),(113,8,NULL),(113,9,NULL),(113,10,NULL),(113,11,NULL),(113,12,NULL),(113,13,NULL),(113,14,NULL),(113,15,NULL),(113,16,NULL),(113,17,NULL),(113,18,NULL),(113,19,NULL),(201,1,NULL),(201,2,NULL),(201,3,NULL),(201,4,NULL),(201,5,NULL),(201,6,NULL),(201,7,NULL),(201,8,NULL),(201,9,NULL),(201,10,NULL),(201,11,NULL),(201,12,NULL),(201,13,NULL),(201,14,NULL),(201,15,NULL),(201,16,NULL),(201,17,NULL),(201,18,NULL),(201,19,NULL),(201,20,NULL),(204,1,NULL),(204,2,NULL),(204,3,NULL),(204,4,NULL),(204,5,NULL),(204,6,NULL),(204,7,NULL),(204,8,NULL),(204,9,NULL),(204,10,NULL),(204,11,NULL),(204,12,NULL),(204,13,NULL),(204,14,NULL),(204,15,NULL),(204,16,NULL),(204,17,NULL),(204,18,NULL),(204,19,NULL),(204,20,NULL),(204,21,NULL),(204,22,NULL),(204,23,NULL),(204,24,NULL),(204,25,NULL),(204,26,NULL),(204,27,NULL),(204,28,NULL),(204,29,NULL),(204,30,NULL),(204,31,NULL),(204,32,NULL),(204,33,NULL),(204,34,NULL),(204,35,NULL),(204,36,NULL),(204,37,NULL),(204,38,NULL),(204,39,NULL),(204,40,NULL),(204,41,NULL),(204,42,NULL),(208,1,NULL),(208,2,NULL),(208,3,NULL),(208,4,NULL),(208,5,NULL),(208,6,NULL),(208,7,NULL),(208,8,NULL),(208,9,NULL),(208,10,NULL),(208,11,NULL),(208,12,NULL),(208,13,NULL),(208,14,NULL),(208,15,NULL),(208,16,NULL),(208,17,NULL),(208,18,NULL),(208,19,NULL),(208,20,NULL),(208,21,NULL),(208,22,NULL),(208,23,NULL),(208,24,NULL),(208,25,NULL),(208,26,NULL),(208,27,NULL),(208,28,NULL),(210,1,NULL),(210,2,NULL),(210,3,NULL),(210,4,NULL),(210,5,NULL),(210,6,NULL),(210,7,NULL),(210,8,NULL),(210,9,NULL),(210,10,NULL),(210,11,NULL),(210,12,NULL),(210,13,NULL),(210,14,NULL),(210,15,NULL),(210,16,NULL),(210,17,NULL),(210,18,NULL),(210,19,NULL),(210,20,NULL),(210,21,NULL),(210,22,NULL),(210,23,NULL),(210,24,NULL),(210,25,NULL),(210,26,NULL),(210,27,NULL),(210,28,NULL),(210,29,NULL),(210,30,NULL),(210,31,NULL),(210,32,NULL),(210,33,NULL),(210,34,NULL),(210,35,NULL),(210,36,NULL),(210,37,NULL),(210,38,NULL),(213,1,NULL),(213,2,NULL),(213,3,NULL),(213,4,NULL),(213,5,NULL),(213,6,NULL),(213,7,NULL),(213,8,NULL),(213,9,NULL),(213,10,NULL),(213,11,NULL),(213,12,NULL),(213,13,NULL),(213,14,NULL),(213,15,NULL),(213,16,NULL),(213,17,NULL),(213,18,NULL),(213,19,NULL);
/*!40000 ALTER TABLE `seat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student` (
  `student_id` varchar(20) NOT NULL,
  `attendance_number` int(11) DEFAULT NULL,
  PRIMARY KEY (`student_id`),
  UNIQUE KEY `attendance_number` (`attendance_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `team_enter`
--

DROP TABLE IF EXISTS `team_enter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `team_enter` (
  `password` varchar(255) DEFAULT NULL,
  `last_enter_time` datetime DEFAULT NULL,
  `team_id` int(11) NOT NULL,
  PRIMARY KEY (`team_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `team_enter`
--

LOCK TABLES `team_enter` WRITE;
/*!40000 ALTER TABLE `team_enter` DISABLE KEYS */;
/*!40000 ALTER TABLE `team_enter` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `temp_member`
--

DROP TABLE IF EXISTS `temp_member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `temp_member` (
  `team_id` varchar(80) NOT NULL,
  `order_in_team` smallint(6) NOT NULL,
  `student_id` varchar(20) DEFAULT NULL,
  `student_name` varchar(20) DEFAULT NULL,
  `college` varchar(30) DEFAULT NULL,
  `education_background` varchar(30) DEFAULT NULL,
  `phone_number` varchar(20) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`team_id`,`order_in_team`),
  CONSTRAINT `temp_member_temp_team_team_id_fk` FOREIGN KEY (`team_id`) REFERENCES `temp_team` (`team_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `temp_member`
--

LOCK TABLES `temp_member` WRITE;
/*!40000 ALTER TABLE `temp_member` DISABLE KEYS */;
/*!40000 ALTER TABLE `temp_member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `temp_team`
--

DROP TABLE IF EXISTS `temp_team`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `temp_team` (
  `team_id` varchar(80) NOT NULL,
  `team_name` varchar(30) DEFAULT NULL,
  `project_name` varchar(255) DEFAULT NULL,
  `belong_field` varchar(30) DEFAULT NULL,
  `project_type` varchar(30) DEFAULT NULL,
  `project_brief` text,
  `project_director` text,
  `work_foundation` text,
  `plan` text,
  `audit_opinion` varchar(15) DEFAULT NULL,
  `submit_date` date DEFAULT NULL,
  `enter_due_date` date DEFAULT NULL,
  PRIMARY KEY (`team_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `temp_team`
--

LOCK TABLES `temp_team` WRITE;
/*!40000 ALTER TABLE `temp_team` DISABLE KEYS */;
/*!40000 ALTER TABLE `temp_team` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tutor`
--

DROP TABLE IF EXISTS `tutor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tutor` (
  `tutor_id` int(11) NOT NULL AUTO_INCREMENT,
  `tutor_name` varchar(30) DEFAULT NULL,
  `tutor_title` varchar(20) DEFAULT NULL,
  `phone_number` varchar(20) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`tutor_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tutor`
--

LOCK TABLES `tutor` WRITE;
/*!40000 ALTER TABLE `tutor` DISABLE KEYS */;
/*!40000 ALTER TABLE `tutor` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-09-19 23:23:08
