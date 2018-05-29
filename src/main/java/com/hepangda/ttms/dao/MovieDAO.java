package com.hepangda.ttms.dao;

import com.hepangda.ttms.idao.IMovieDAO;

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
public class MovieDAO extends BaseDAO implements IMovieDAO {
}
