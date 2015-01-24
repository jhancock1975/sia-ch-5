CREATE TABLE `stocks` (
  `ticker` varchar(255) NOT NULL DEFAULT '',
  `description` text,
  `commishFreeEtf` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ticker`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `txTypes` (
  `txTypeId` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`txTypeId`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

CREATE TABLE `brokerTypes` (
  `brokerTypeId` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`brokerTypeId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

CREATE TABLE `stockTxTypes` (
  `stockTxTypeId` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`stockTxTypeId`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

CREATE TABLE `stockTxs` (
  `stockTxId` int(11) NOT NULL AUTO_INCREMENT,
  `ticker` varchar(255) DEFAULT NULL,
  `qty` int(11) NOT NULL,
  `amount` float NOT NULL,
  `txTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `txTypeId` int(11) DEFAULT NULL,
  `brokerTypeId` int(11) DEFAULT NULL,
  `txNum` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`stockTxId`),
  UNIQUE KEY `ticker` (`ticker`,`qty`,`amount`,`txTime`,`brokerTypeId`,`txNum`,`txTypeId`),
  KEY `txTypeId` (`txTypeId`),
  KEY `brokerTypeId` (`brokerTypeId`),
  CONSTRAINT `stockTxs_ibfk_1` FOREIGN KEY (`ticker`) REFERENCES `stocks` (`ticker`),
  CONSTRAINT `stockTxs_ibfk_2` FOREIGN KEY (`txTypeId`) REFERENCES `stockTxTypes` (`stockTxTypeId`),
  CONSTRAINT `stockTxs_ibfk_3` FOREIGN KEY (`brokerTypeId`) REFERENCES `brokerTypes` (`brokerTypeId`),
  CONSTRAINT `stockTxs_ibfk_4` FOREIGN KEY (`txTypeId`) REFERENCES `txTypes` (`txTypeId`)
) ENGINE=InnoDB AUTO_INCREMENT=25956 DEFAULT CHARSET=latin1;

create TABLE reportTypes (
	`reportTypeId` int(11) not null AUTO_INCREMENT,
	`description` varchar(255) not null,
	`updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	`created` timestamp DEFAULT '2013-01-01',
	PRIMARY KEY(`reportTypeId`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1;

create TABLE `reports` (
	`reportId` int(11) not null AUTO_INCREMENT,
	`reportTypeId` int(11) not null,
	`report` mediumtext,
	`updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	`created` timestamp DEFAULT '2013-01-01',
	PRIMARY KEY(`reportId`),
	CONSTRAINT FOREIGN KEY (`reportTypeId`) REFERENCES `reportTypes` (`reportTypeId`)
) ENGINE=InnoDB AUTO_INCREMENT=25956 DEFAULT CHARSET=latin1;