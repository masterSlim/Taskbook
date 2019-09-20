-- MySQL dump 10.13  Distrib 8.0.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: taskbook
-- ------------------------------------------------------
-- Server version	5.7.20-log

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
-- Table structure for table `tasks`
--

DROP TABLE IF EXISTS `tasks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tasks` (
  `task_id` int(11) NOT NULL AUTO_INCREMENT,
  `priority` bit(1) NOT NULL,
  `creator_id` int(11) NOT NULL,
  `title` varchar(45) NOT NULL DEFAULT 'Без темы',
  `task` varchar(5000) NOT NULL DEFAULT 'Без описания',
  `executor_id` int(11) DEFAULT NULL,
  `create_datetime` datetime DEFAULT NULL,
  `start_datetime` datetime DEFAULT NULL,
  `deadline` datetime DEFAULT NULL,
  `is_active` tinyint(4) NOT NULL DEFAULT '0',
  `is_closed` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`task_id`),
  UNIQUE KEY `task id_UNIQUE` (`task_id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tasks`
--

LOCK TABLES `tasks` WRITE;
/*!40000 ALTER TABLE `tasks` DISABLE KEYS */;
INSERT INTO `tasks` VALUES (21,_binary '\0',1,'Исправления функционала','- Закрывать окно новой задачи по нажатию кнопки \"ГОТОВО!\";\n- Заполнять по-умолчаю поле \"Дата начала\"\" сегодняшней датой;\n- Заполнять по-умолчанию дату дедлайна +3 дня от сегодняшнего дня;',2,'2019-09-19 00:00:00','2019-09-19 00:00:00','2019-09-19 00:00:00',0,1),(22,_binary '\0',1,'Исправления функционала','- Обновление задачи при выходе по кнопке \"Готово\"\n- Добавление кнопки редактирования по нажатию на задачу создателем задачиЖ\n- Добавить кнопку \"Удалить задачу\" к созданной задаче;-\n- В главном окне Руководителя добавить столбец с информацией об исполнителях;\n',2,'2019-09-19 00:00:00','2019-09-19 00:00:00','2019-09-19 00:00:00',1,0),(23,_binary '\0',1,'Исправления функционала','- Обновление задачи при выходе по кнопке \"Готово\"\n- Добавление кнопки редактирования по нажатию на задачу создателем задачиЖ\n- Добавить кнопку \"Удалить задачу\" к созданной задаче;-\n- В главном окне Руководителя добавить столбец с информацией об исполнителях;\n',2,'2019-09-19 00:00:00','2019-09-19 00:00:00','2019-09-19 00:00:00',0,1),(24,_binary '\0',1,'Расширение функционала','- Добавить настройки сервера в окно логина;\n- Добавить настройки пользователя в главных окнах пользователей, а именно:\n	Рабочая папка задачи;\n	Смена пароля;\n	\n	\n',0,'2019-09-19 00:00:00','2019-09-19 00:00:00','2019-09-21 00:00:00',1,0),(25,_binary '\0',1,'','',0,'2019-09-19 00:00:00','2019-09-19 00:00:00','2019-09-19 00:00:00',1,0),(26,_binary '\0',1,'','',0,'2019-09-19 00:00:00','2019-09-19 00:00:00','2019-09-19 00:00:00',1,0),(27,_binary '\0',1,'','',0,'2019-09-19 00:00:00','2019-09-19 00:00:00','2019-09-19 00:00:00',1,0),(28,_binary '\0',1,'','',0,'2019-09-19 00:00:00','2019-09-19 00:00:00','2019-09-19 00:00:00',1,0),(29,_binary '\0',1,'','',0,'2019-09-19 00:00:00','2019-09-19 00:00:00','2019-09-19 00:00:00',1,0),(30,_binary '\0',1,'','',0,'2019-09-19 00:00:00','2019-09-19 00:00:00','2019-09-19 00:00:00',1,0),(31,_binary '\0',1,'','',0,'2019-09-19 00:00:00','2019-09-19 00:00:00','2019-09-19 00:00:00',1,0),(32,_binary '\0',1,'','',0,'2019-09-19 00:00:00','2019-09-19 00:00:00','2019-09-19 00:00:00',1,0),(33,_binary '\0',1,'','',0,'2019-09-19 00:00:00','2019-09-19 00:00:00','2019-09-19 00:00:00',1,0),(34,_binary '\0',1,'','',0,'2019-09-19 00:00:00','2019-09-19 00:00:00','2019-09-19 00:00:00',1,0);
/*!40000 ALTER TABLE `tasks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `idUser` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) NOT NULL,
  `password` varchar(45) NOT NULL,
  `userpic` varchar(100) NOT NULL,
  `gender` tinyint(4) NOT NULL,
  `position` varchar(15) DEFAULT 'Не указано',
  `phone` varchar(11) DEFAULT '0',
  `e-mail` varchar(50) DEFAULT 'Почта не указана',
  `directory` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`idUser`),
  UNIQUE KEY `idUsers_UNIQUE` (`idUser`),
  UNIQUE KEY `username_UNIQUE` (`user_name`),
  UNIQUE KEY `phone_UNIQUE` (`phone`),
  UNIQUE KEY `e-mail_UNIQUE` (`e-mail`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='Список всех пользоателей приложения с должностями и аватарками';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Project','2517','null',1,'Руководитель','9625670554','e-mail@site.ru',NULL),(2,'Ivan','1725','null',2,'Дизайнер','8800555353','e-mail2@mail.ru',NULL);
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

-- Dump completed on 2019-09-20 22:52:03
