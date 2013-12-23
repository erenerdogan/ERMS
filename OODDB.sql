CREATE DATABASE  IF NOT EXISTS `ood` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `OOD`;
-- MySQL dump 10.13  Distrib 5.5.16, for osx10.5 (i386)
--
-- Host: localhost    Database: OOD
-- ------------------------------------------------------
-- Server version	5.5.23

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
-- Table structure for table `Tables`
--

DROP TABLE IF EXISTS `Tables`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Tables` (
  `tid` int(11) NOT NULL AUTO_INCREMENT,
  `tuid` int(11) DEFAULT NULL,
  `tname` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`tid`),
  KEY `TABLES_USERS_FK_idx` (`tuid`),
  CONSTRAINT `TABLES_USERS_FK` FOREIGN KEY (`tuid`) REFERENCES `Users` (`uid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Tables`
--

LOCK TABLES `Tables` WRITE;
/*!40000 ALTER TABLE `Tables` DISABLE KEYS */;
INSERT INTO `Tables` VALUES (1,1,'Table 1');
/*!40000 ALTER TABLE `Tables` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Products`
--

DROP TABLE IF EXISTS `Products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Products` (
  `pid` int(11) NOT NULL AUTO_INCREMENT,
  `pname` varchar(255) DEFAULT NULL,
  `pdescription` text,
  `pcalori` int(11) DEFAULT NULL,
  `pprice` double DEFAULT NULL,
  `pstatus` int(11) DEFAULT NULL,
  `piid` int(11) DEFAULT NULL,
  `pcid` int(11) DEFAULT NULL,
  PRIMARY KEY (`pid`),
  KEY `PRODUCTS_CATEGORIES_FK_idx` (`pcid`),
  KEY `PRODUCTS_IMAGES_FK` (`piid`),
  CONSTRAINT `PRODUCTS_CATEGORIES_FK` FOREIGN KEY (`pcid`) REFERENCES `Categories` (`cid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `PRODUCTS_IMAGES_FK` FOREIGN KEY (`piid`) REFERENCES `Images` (`iid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Products`
--

LOCK TABLES `Products` WRITE;
/*!40000 ALTER TABLE `Products` DISABLE KEYS */;
INSERT INTO `Products` VALUES (1,'Corned Beef Hash','Give leftover corned beef new life. Pair it with sauteed onions and potatoes, then top it off with melted cheddar cheese. Complete this classic diner meal with a fried egg.\r\n',180,12.99,1,7,2),(2,'Sunnyas Big Ole Tex-Mex Burrito Omelet ','Colorful, hearty and satisfying, this spicy omelet is overflowing with sautaed veggies, Mexican chorizo and pepper-jack cheese. Sunny tops it off with a classic salsa verde featuring cilantro, tomatillos and jalapeno.',220,15.99,1,8,2),(3,'Spanish Omelet With Romesco Sauce','Ted uses the flavors of Spain Iberico ham, Manchego cheese and spicy tomato-pepper romesco sauce for his twist on the classic omelet.\r\n',250,13.99,1,9,2),(4,'Baklava','Baklava is a rich, sweet pastry made of layers of filo pastry filled with chopped nuts and sweetened with syrup or honey. It is characteristic of the cuisines of the former Ottoman Empire, but is also found in Central and Southwest Asia.',220,15.99,1,10,4),(5,'Burma','The Burmese cuisine does not lack in desserts. The country has many fruits and one way to exploit this is to prepare various cakes, tarts and pies. The best fact is most of fruits are exotic. This way, the dishes have a unique taste, not only because of the ingredients, but also because of the preparation methods. Burmese people are not strangers to the desserts of the other nations.',170,12.99,1,11,4),(6,'Gullac','Gullacis considered as being the origin of baklava. The similarities between the two desserts are many, such as the use of thin layers of dough and nuts in between. Gullac dough is now prepared with corn starch and wheat flour, although originally it was made only with wheat starch. Gullaccontains walnuts between the layers that are put in milk.',155,14.99,1,12,4),(7,'Kadayif','Kadayif is very common Turkish dessert which differs from baklava in that shredded dough/phyllo is used. There are different types of Kadayif: tel (wire) or burma (wring) Kadayif, both of which can be prepared either with walnut or pistachio. \r\n',155,14.99,1,13,4),(8,'Kunefe','Kenafeh, also spelled knafeh, kunafeh, or kunafah, is a Levantine cheese pastry soaked in sweet sugar-based syrup, typical of the regions belonging to the former Ottoman Empire. ',180,14.99,1,14,4),(9,'Sekerpare','Sekerpare is one of the many delectable and popular desserts in the Turkish cuisine. Mainly prepared by baking some soft balls of pastry dipped in thick lemony sugar syrup, sekerpare has become one of the well loved Turkish sweets among locals and visiting tourists in Turkey.',120,9.99,1,15,4),(10,'Souffle ','A souffle is a lightly baked cake made with egg yolks and beaten egg whites combined with various other ingredients and served as a savory main dish or sweetened as a dessert.',80,8.99,1,16,4),(11,'Vodka Lemonade','Vodka lemonade',50,8.99,1,17,3),(12,'Coke','Coke',99,2.5,1,20,6);
/*!40000 ALTER TABLE `Products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Users`
--

DROP TABLE IF EXISTS `Users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Users` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `uname` varchar(255) DEFAULT NULL,
  `usurname` varchar(255) DEFAULT NULL,
  `uaddress` text,
  `uphone` varchar(255) DEFAULT NULL,
  `uemail` varchar(255) DEFAULT NULL,
  `upassword` varchar(255) DEFAULT NULL,
  `uappkey` text,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Users`
--

LOCK TABLES `Users` WRITE;
/*!40000 ALTER TABLE `Users` DISABLE KEYS */;
INSERT INTO `Users` VALUES (1,'er','ms','Kocaeli','05055055555','erms@erms.com','erms','7766-8412481267120-2411794-7916126-23-92');
/*!40000 ALTER TABLE `Users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Images`
--

DROP TABLE IF EXISTS `Images`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Images` (
  `iid` int(11) NOT NULL AUTO_INCREMENT,
  `ipath` text,
  `ithumb` text,
  PRIMARY KEY (`iid`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Images`
--

LOCK TABLES `Images` WRITE;
/*!40000 ALTER TABLE `Images` DISABLE KEYS */;
INSERT INTO `Images` VALUES (1,'20131221173111.jpg','20131221173111.jpg'),(2,'20131221173120.jpg','20131221173120.jpg'),(3,'20131221173129.jpg','20131221173129.jpg'),(4,'20131221173139.jpg','20131221173139.jpg'),(5,'20131221173152.jpg','20131221173152.jpg'),(6,'20131221173212.jpg','20131221173212.jpg'),(7,'20131221173604.jpg','20131221173604.jpg'),(8,'20131221173742.jpg','20131221173742.jpg'),(9,'20131221173833.jpg','20131221173833.jpg'),(10,'20131221174010.jpg','20131221174010.jpg'),(11,'20131221174247.jpg','20131221174247.jpg'),(12,'20131221174441.jpg','20131221174441.jpg'),(13,'20131221174537.jpg','20131221174537.jpg'),(14,'20131221174737.jpg','20131221174737.jpg'),(15,'20131221174844.jpg','20131221174844.jpg'),(16,'20131221175001.jpg','20131221175001.jpg'),(17,'20131221175121.jpg','20131221175121.jpg'),(18,'20131221175151.jpg','20131221175151.jpg'),(19,'20131221175231.jpg','20131221175231.jpg'),(20,'20131221193838.jpg','20131221193838.jpg');
/*!40000 ALTER TABLE `Images` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Orders`
--

DROP TABLE IF EXISTS `Orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Orders` (
  `oid` int(11) NOT NULL AUTO_INCREMENT,
  `opid` int(11) DEFAULT NULL,
  `onumber` double DEFAULT NULL,
  `odate` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `ostatus` int(11) DEFAULT NULL,
  `otid` int(11) DEFAULT NULL,
  PRIMARY KEY (`oid`),
  KEY `ORDERS_PRODUCTS_FK_idx` (`opid`),
  KEY `ORDERS_TABLES_FK_idx` (`otid`),
  CONSTRAINT `ORDERS_PRODUCTS_FK` FOREIGN KEY (`opid`) REFERENCES `Products` (`pid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `ORDERS_TABLES_FK` FOREIGN KEY (`otid`) REFERENCES `Tables` (`tid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Orders`
--

LOCK TABLES `Orders` WRITE;
/*!40000 ALTER TABLE `Orders` DISABLE KEYS */;
INSERT INTO `Orders` VALUES (4,1,10,'2013-12-21 22:09:06',0,1),(5,1,10,'2013-12-21 22:29:22',0,1),(6,11,1,'2013-12-21 23:01:19',0,1),(7,12,12,'2013-12-21 23:02:44',0,1),(8,2,1,'2013-12-21 23:02:44',0,1),(9,10,5,'2013-12-21 23:02:44',0,1),(10,7,1,'2013-12-21 23:12:52',0,1),(11,1,1,'2013-12-21 23:12:52',0,1),(12,1,1,'2013-12-21 23:14:20',0,1),(13,1,10,'2013-12-21 23:34:18',0,1),(14,1,1,'2013-12-21 23:54:52',0,1),(15,1,1,'2013-12-21 23:54:52',0,1),(16,2,1,'2013-12-21 23:54:52',0,1),(17,3,10,'2013-12-22 00:02:17',0,1),(18,5,1,'2013-12-22 00:02:17',0,1),(19,1,1,'2013-12-22 00:10:07',0,1),(20,11,1,'2013-12-22 00:14:45',0,1),(21,1,1,'2013-12-22 00:16:03',0,1),(22,1,1,'2013-12-22 00:18:19',0,1),(23,1,1,'2013-12-22 00:23:46',0,1),(24,1,1,'2013-12-22 00:23:46',0,1),(25,11,1,'2013-12-22 00:24:15',0,1),(26,2,1,'2013-12-22 00:37:00',0,1),(27,1,1,'2013-12-22 00:58:51',0,1),(28,1,1,'2013-12-22 00:58:51',0,1),(29,11,1,'2013-12-22 01:05:55',0,1),(30,1,1,'2013-12-22 11:11:12',0,1),(31,1,1,'2013-12-22 11:11:12',0,1),(32,2,5,'2013-12-22 11:11:53',0,1),(33,2,1,'2013-12-23 22:03:53',0,1);
/*!40000 ALTER TABLE `Orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Categories`
--

DROP TABLE IF EXISTS `Categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Categories` (
  `cid` int(11) NOT NULL AUTO_INCREMENT,
  `cname` varchar(255) DEFAULT NULL,
  `ciid` int(11) DEFAULT NULL,
  `cuid` int(11) DEFAULT NULL,
  PRIMARY KEY (`cid`),
  KEY `CATEGORIES_USERS_FK_idx` (`cuid`),
  KEY `CATEGORIES_IMAGES_FK` (`ciid`),
  CONSTRAINT `CATEGORIES_IMAGES_FK` FOREIGN KEY (`ciid`) REFERENCES `Images` (`iid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `CATEGORIES_USERS_FK` FOREIGN KEY (`cuid`) REFERENCES `Users` (`uid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Categories`
--

LOCK TABLES `Categories` WRITE;
/*!40000 ALTER TABLE `Categories` DISABLE KEYS */;
INSERT INTO `Categories` VALUES (1,'Seafood',1,1),(2,'Breakfast',2,1),(3,'Cold Drinks',3,1),(4,'Dessert',4,1),(5,'Fast Food & Sandwich',5,1),(6,'Hot Drinks',6,1),(7,'Soups',18,1),(8,'Turkish Kebap',19,1);
/*!40000 ALTER TABLE `Categories` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-12-24  0:55:03
