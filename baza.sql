/*
SQLyog Community v13.1.9 (64 bit)
MySQL - 10.4.27-MariaDB : Database - hotel_ps
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`hotel_ps` /*!40100 DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci */;

USE `hotel_ps`;

/*Table structure for table `gost` */

DROP TABLE IF EXISTS `gost`;

CREATE TABLE `gost` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ime` varchar(255) NOT NULL,
  `prezime` varchar(255) NOT NULL,
  `datumRodjenja` date NOT NULL,
  `gradID` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `gradID` (`gradID`),
  CONSTRAINT `gost_ibfk_1` FOREIGN KEY (`gradID`) REFERENCES `grad` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

/*Data for the table `gost` */

insert  into `gost`(`id`,`ime`,`prezime`,`datumRodjenja`,`gradID`) values 
(2,'Ivana','Ivanovic','1972-09-02',3),
(5,'Luka','Lukic','2005-05-05',5),
(6,'Ivana','Trajkov','1972-09-02',3),
(9,'Jovan','Jovanovic','2000-06-05',1);

/*Table structure for table `grad` */

DROP TABLE IF EXISTS `grad`;

CREATE TABLE `grad` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pttBroj` int(11) NOT NULL,
  `naziv` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

/*Data for the table `grad` */

insert  into `grad`(`id`,`pttBroj`,`naziv`) values 
(1,11000,'Beograd'),
(2,21000,'Novi Sad'),
(3,19000,'Zajecar'),
(4,18000,'Nis'),
(5,17500,'Vranje');

/*Table structure for table `korisnik` */

DROP TABLE IF EXISTS `korisnik`;

CREATE TABLE `korisnik` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ime` varchar(255) NOT NULL,
  `prezime` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

/*Data for the table `korisnik` */

insert  into `korisnik`(`id`,`ime`,`prezime`,`username`,`password`) values 
(1,'Branislav','Trajkov','bane','bane'),
(2,'Anja','Nedeljkovic','anja','anja'),
(3,'Lazar','Micunovic','lazar','lazar');

/*Table structure for table `ocena` */

DROP TABLE IF EXISTS `ocena`;

CREATE TABLE `ocena` (
  `gostID` int(11) NOT NULL,
  `sobaID` int(11) NOT NULL,
  `ocenaSobe` int(11) DEFAULT NULL,
  PRIMARY KEY (`gostID`,`sobaID`),
  KEY `sobaID` (`sobaID`),
  CONSTRAINT `ocena_ibfk_3` FOREIGN KEY (`gostID`) REFERENCES `gost` (`id`),
  CONSTRAINT `ocena_ibfk_4` FOREIGN KEY (`sobaID`) REFERENCES `soba` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

/*Data for the table `ocena` */

insert  into `ocena`(`gostID`,`sobaID`,`ocenaSobe`) values 
(2,1,2),
(2,8,5),
(5,2,3),
(6,2,4),
(9,9,3);

/*Table structure for table `rezervacija` */

DROP TABLE IF EXISTS `rezervacija`;

CREATE TABLE `rezervacija` (
  `id` int(11) NOT NULL,
  `datumOd` date NOT NULL,
  `datumDo` date NOT NULL,
  `gostID` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `gostID` (`gostID`),
  CONSTRAINT `rezervacija_ibfk_1` FOREIGN KEY (`gostID`) REFERENCES `gost` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

/*Data for the table `rezervacija` */

insert  into `rezervacija`(`id`,`datumOd`,`datumDo`,`gostID`) values 
(1,'2023-01-25','2023-01-30',2),
(2,'2023-01-25','2023-01-30',2),
(3,'2023-01-01','2023-01-05',5),
(4,'2023-02-01','2023-02-15',2),
(6,'2023-05-05','2023-05-10',6),
(7,'2022-12-13','2023-01-23',6),
(8,'2023-01-05','2023-01-25',5),
(9,'2023-01-10','2023-01-15',2),
(10,'2023-02-04','2023-02-07',9),
(11,'2023-02-02','2023-02-05',5),
(12,'2023-02-04','2023-02-07',2);

/*Table structure for table `soba` */

DROP TABLE IF EXISTS `soba`;

CREATE TABLE `soba` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sprat` int(11) NOT NULL,
  `status` tinyint(1) NOT NULL,
  `vrstaSobeID` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `vrstaSobeID` (`vrstaSobeID`),
  CONSTRAINT `soba_ibfk_1` FOREIGN KEY (`vrstaSobeID`) REFERENCES `vrstasobe` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

/*Data for the table `soba` */

insert  into `soba`(`id`,`sprat`,`status`,`vrstaSobeID`) values 
(1,1,1,1),
(2,2,0,3),
(3,1,0,1),
(4,1,1,1),
(5,2,0,2),
(6,1,0,1),
(7,1,0,1),
(8,3,0,3),
(9,5,0,2),
(10,3,0,3),
(11,3,0,2),
(12,4,0,4),
(13,7,0,1);

/*Table structure for table `stavkarezervacije` */

DROP TABLE IF EXISTS `stavkarezervacije`;

CREATE TABLE `stavkarezervacije` (
  `rezervacijaID` int(11) NOT NULL,
  `rbStavke` int(11) NOT NULL,
  `gostID` int(11) NOT NULL,
  `sobaID` int(11) NOT NULL,
  PRIMARY KEY (`rezervacijaID`,`rbStavke`),
  KEY `gostID` (`gostID`),
  KEY `rbStavke` (`rbStavke`),
  KEY `sobaID` (`sobaID`),
  CONSTRAINT `stavkarezervacije_ibfk_1` FOREIGN KEY (`rezervacijaID`) REFERENCES `rezervacija` (`id`),
  CONSTRAINT `stavkarezervacije_ibfk_4` FOREIGN KEY (`gostID`) REFERENCES `gost` (`id`),
  CONSTRAINT `stavkarezervacije_ibfk_5` FOREIGN KEY (`sobaID`) REFERENCES `soba` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

/*Data for the table `stavkarezervacije` */

insert  into `stavkarezervacije`(`rezervacijaID`,`rbStavke`,`gostID`,`sobaID`) values 
(1,1,2,1),
(1,2,5,1),
(2,1,2,1),
(2,2,5,1),
(3,1,2,3),
(3,2,5,3),
(4,1,6,1),
(4,2,2,1),
(6,1,2,3),
(6,2,5,3),
(7,1,6,6),
(7,2,2,6),
(8,1,2,3),
(8,2,5,3),
(9,1,2,2),
(9,2,9,2),
(10,1,6,2),
(10,2,2,2),
(11,1,2,3),
(11,2,9,3),
(12,1,9,5),
(12,2,5,5);

/*Table structure for table `vrstasobe` */

DROP TABLE IF EXISTS `vrstasobe`;

CREATE TABLE `vrstasobe` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `brojKreveta` int(11) NOT NULL,
  `povrsina` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

/*Data for the table `vrstasobe` */

insert  into `vrstasobe`(`id`,`brojKreveta`,`povrsina`) values 
(1,1,25),
(2,2,30),
(3,3,45),
(4,4,50);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
