drop database if exists stocks;

create database stocks;

use stocks;
	
	CREATE TABLE `brokerTypes` (
  `brokerTypeId` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`brokerTypeId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

	CREATE TABLE `reportTypes` (
  `reportTypeId` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) NOT NULL,
  `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `created` timestamp NOT NULL DEFAULT '2013-01-01 00:00:00',
  PRIMARY KEY (`reportTypeId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

	CREATE TABLE `reports` (
  `reportId` int(11) NOT NULL AUTO_INCREMENT,
  `reportTypeId` int(11) NOT NULL,
  `report` mediumtext,
  `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `created` timestamp NOT NULL DEFAULT '2013-01-01 00:00:00',
  PRIMARY KEY (`reportId`),
  KEY `reportTypeId` (`reportTypeId`),
  CONSTRAINT `reports_ibfk_1` FOREIGN KEY (`reportTypeId`) REFERENCES `reportTypes` (`reportTypeId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

	CREATE TABLE `stocks` (
  stockId int not null auto_increment,
  `ticker` varchar(255) NOT NULL DEFAULT '',
  `description` text,
  `commishFreeEtf` enum('Y','N') DEFAULT 'N',
  `brokerTypeId` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (stockId),
  KEY `brokerTypeId` (`brokerTypeId`),
  CONSTRAINT `stocks_ibfk_1` FOREIGN KEY (`brokerTypeId`) REFERENCES `brokerTypes` (`brokerTypeId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

	CREATE TABLE `stockTxTypes` (
  `stockTxTypeId` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`stockTxTypeId`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

	CREATE TABLE `txTypes` (
  `txTypeId` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`txTypeId`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

	CREATE TABLE `stockTxs` (
  `stockTxId` int(11) NOT NULL AUTO_INCREMENT,
  `stockId` int DEFAULT NULL,
  `qty` int(11) NOT NULL,
  `amount` float NOT NULL,
  `txTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `txTypeId` int(11) DEFAULT NULL,
  `brokerTypeId` int(11) DEFAULT NULL,
  `txNum` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`stockTxId`),
  UNIQUE KEY `txUniqueKey` (`stockId`,`qty`,`amount`,`txTime`,`brokerTypeId`,`txNum`,`txTypeId`),
  KEY `txTypeId` (`txTypeId`),
  KEY `brokerTypeId` (`brokerTypeId`),
  CONSTRAINT `stockTxs_ibfk_5` FOREIGN KEY (`stockId`) REFERENCES `stocks` (`stockId`),
  CONSTRAINT `stockTxs_ibfk_2` FOREIGN KEY (`txTypeId`) REFERENCES `stockTxTypes` (`stockTxTypeId`),
  CONSTRAINT `stockTxs_ibfk_3` FOREIGN KEY (`brokerTypeId`) REFERENCES `brokerTypes` (`brokerTypeId`),
  CONSTRAINT `stockTxs_ibfk_4` FOREIGN KEY (`txTypeId`) REFERENCES `txTypes` (`txTypeId`)
) ENGINE=InnoDB AUTO_INCREMENT=10011 DEFAULT CHARSET=latin1;
