DROP DATABASE IF EXISTS `LibraryBook`;
create database `LibraryBook`;
use LibraryBook;
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` ( 
  `Id` int  NOT NULL AUTO_INCREMENT,  
  `username` varchar(20) NOT NULL,
  `email` varchar(20) NOT NULL,  
  `passwd` varchar(20) NOT NULL,     
  PRIMARY KEY  (`Id`)  
) ENGINE=MyISAM  DEFAULT CHARSET=utf8; 
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` ( 
  `username` varchar(20) NOT NULL,
  `email` varchar(20) NOT NULL,  
  `passwd` varchar(20) NOT NULL,  
  `studentId` int NOT NULL AUTO_INCREMENT,  
   PRIMARY KEY  (`studentId`)  
) ENGINE=MyISAM  DEFAULT CHARSET=utf8; 

DROP TABLE IF EXISTS `store`;
CREATE TABLE `store` (  
  `ISBN` varchar(20) NOT NULL,
  `bookName` varchar(20) NOT NULL,
  `count` int NOT NULL,   
  PRIMARY KEY (`ISBN`)  
) ENGINE=MyISAM  DEFAULT CHARSET=utf8; 

DROP TABLE IF EXISTS `bookClass`;
CREATE TABLE `bookClass` (  
  `classId` int NOT NULL,  
  `className` varchar(20) NOT NULL,
  PRIMARY KEY (`classId`)  
) ENGINE=MyISAM  DEFAULT CHARSET=utf8; 

DROP TABLE IF EXISTS "bookInfo";
CREATE TABLE `bookInfo` (  
  `ISBN` int NOT NULL,
  `classId` int NOT NULL,
  `bookName` varchar(20) NOT NULL,
  `author`  varchar(20) NOT NULL,
  `publisher`varchar(20) NOT NULL,
  `publishTime` date NOT NULL,
  PRIMARY KEY (`ISBN`)  
) ENGINE=MyISAM  DEFAULT CHARSET=utf8; 

DROP TABLE IF EXISTS `borrowInfo`;
CREATE TABLE `borrowInfo` ( 
  `id` int  NOT NULL,
  `ISBN` int NOT NULL,
  `studentId` varchar(20) NOT NULL,
  `returnFlag` TINYINT(1) NOT NULL,
  `borrowDate` date NOT NULL,
  `returnDate`  date NOT NULL,
 
  PRIMARY KEY (`ISBN`)  
) ENGINE=MyISAM  DEFAULT CHARSET=utf8; 