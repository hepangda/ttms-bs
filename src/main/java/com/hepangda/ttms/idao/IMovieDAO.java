package com.hepangda.ttms.idao;
/*
Create Table: CREATE TABLE `Movie` (
  `Mov_ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `Mov_Name` varchar(80) NOT NULL,
  `Mov_Type` tinyint(4) NOT NULL,
  `Mov_Description` text,
  `Mov_Time` time NOT NULL,
  `Mov_Image` blob,
  PRIMARY KEY (`Mov_ID`),
  UNIQUE KEY `Mov_ID` (`Mov_ID`),
  UNIQUE KEY `Mov_Name` (`Mov_Name`)
)
 */
public interface IMovieDAO {
//    int AddMovie(String name,int type,)
}
