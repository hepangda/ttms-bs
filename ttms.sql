-- 表：员工
CREATE TABLE Employee (
    Emp_ID SERIAL PRIMARY KEY,
    Emp_LoginName VARCHAR(30) NOT NULL UNIQUE,
    Emp_Password CHAR(32) NOT NULL,
    Emp_Name VARCHAR(30) NOT NULL,
    Emp_BornYear SMALLINT NOT NULL,
    Emp_Phonenumber VARCHAR(30) DEFAULT NULL,
    Emp_privilege TINYINT NOT NULL DEFAULT '0'
);

-- 表：影片
CREATE TABLE Movie (
    Mov_ID SERIAL PRIMARY KEY,
    Mov_Name VARCHAR(80) UNIQUE NOT NULL,
    Mov_Type TINYINT NOT NULL,
    Mov_Status TINYINT NOT NULL,
    Mov_Religon TINYINT NOT NULL,
    Mov_Description TEXT,
    Mov_Time TIME NOT NULL,
    Mov_ImageURL TEXT
);

-- 表：演出厅
CREATE TABLE Studio (
    Stu_ID SERIAL PRIMARY KEY,
    Stu_Name VARCHAR(30) UNIQUE NOT NULL,
    Stu_Description TEXT,
    Stu_Type TINYINT NOT NULL,
    Stu_Rows TINYINT NOT NULL,
    Stu_cols TINYINT NOT NULL
);

-- 表：演出计划
CREATE TABLE Schedule (
    Sch_ID SERIAL PRIMARY KEY,
    Sch_Time DATETIME NOT NULL,
    Sch_StuID BIGINT UNSIGNED NOT NULL,
    Sch_MovID BIGINT UNSIGNED NOT NULL,
    Sch_Price BIGINT NOT NULL
);
ALTER TABLE Schedule ADD FOREIGN KEY schstuid (Sch_StuID) REFERENCES Studio (Stu_ID);
ALTER TABLE Schedule ADD FOREIGN KEY schmovid (Sch_MovID) REFERENCES Movie (Mov_ID);

-- 表：座位
CREATE TABLE Seat (
    Set_ID SERIAL PRIMARY KEY,
    Set_StuID BIGINT UNSIGNED,
    Set_Row INT NOT NULL,
    Set_Col INT NOT NULL,
    Set_Type TINYINT NOT NULL
);
ALTER TABLE Seat ADD FOREIGN KEY seatstuid (Set_StuID) REFERENCES Studio (Stu_ID);

-- 表：影片
CREATE TABLE Tickets (
    TK_ID SERIAL PRIMARY KEY,
    Tk_SchID BIGINT UNSIGNED,
    TK_SeatID BIGINT UNSIGNED,
    TK_Status TINYINT NOT NULL
);
ALTER TABLE Tickets ADD FOREIGN KEY tkschid (Tk_SchID) REFERENCES Schedule (Sch_ID);
ALTER TABLE Tickets ADD FOREIGN KEY tksetid (TK_SeatID) REFERENCES Seat (Set_ID);

-- 表：销售记录
CREATE TABLE Sales_Ticket (
    ST_ID SERIAL PRIMARY KEY,
    ST_TkID BIGINT UNSIGNED,
    ST_Price BIGINT NOT NULL
);

-- 视图：获取演出计划
CREATE VIEW view_SchFetch AS (
    SELECT Schedule.Sch_ID, Schedule.Sch_Time, Schedule.Sch_Price,
           Studio.Stu_Name, Studio.Stu_ID, Movie.Mov_ID,
           Movie.Mov_Name, Movie.Mov_ImageURL
    FROM Schedule,Studio,Movie
    WHERE Schedule.Sch_StuID=Studio.Stu_ID AND Schedule.Sch_MovID=Movie.Mov_ID
);

-- 视图：获取票房纪录
CREATE VIEW view_saletotal(Total, Mov_Name, Mov_ID, Mov_ImageURL) AS
    SELECT SUM(ST_Price), Mov_Name, Mov_ID, Mov_ImageURL FROM Sales_Ticket, Movie WHERE EXISTS (
        SELECT Sch_MovID FROM Schedule WHERE EXISTS (
            SELECT Tk_SchID FROM Tickets WHERE EXISTS (
                SELECT ST_TkID FROM Sales_Ticket
            )
        )
    )
;


-- 触发器：创建演出计划同时创建对应的票
DELIMITER //
CREATE PROCEDURE isrt_tk(STUID BIGINT, SCHID BIGINT)
BEGIN
	DECLARE id BIGINT default 1;
	DECLARE done INT default 0;
	DECLARE csrsch CURSOR FOR (SELECT Set_ID as id FROM Seat WHERE Seat.Set_StuID=STUID);
	DECLARE CONTINUE HANDLER FOR not found SET done=1;

	OPEN csrsch;
	pl : loop
		FETCH csrsch INTO id;
		IF done=1 THEN LEAVE pl;
		END IF;
		INSERT INTO Tickets values(default, SCHID, id, 2);
	END LOOP;
	CLOSE csrsch;
END
//
DELIMITER ;
CREATE TRIGGER insert_ticket AFTER INSERT ON Schedule FOR EACH ROW CALL isrt_tk(new.Sch_StuID, new.Sch_ID);

-- 删除演出计划同时删除对应的票
CREATE TRIGGER del_ticket BEFORE DELETE ON Schedule FOR EACH ROW
    DELETE FROM Tickets WHERE Tk_SchID=old.Sch_ID;

-- 触发器：创建演出厅时同时创建好对应的座位
DELIMITER //
CREATE PROCEDURE isrt_set(STUID BIGINT, SCHROWS TINYINT, SCHCOLS TINYINT)
BEGIN
    DECLARE i INT;
    DECLARE j INT;

    SET i=1;
    WHILE i<=SCHROWS DO
        SET j=1;
        WHILE j<=SCHCOLS DO
            INSERT INTO Seat VALUES(default, STUID, i, j, 2);
            SET j=j+1;
        END WHILE;
        SET i=i+1;
    END WHILE;
END
//
DELIMITER ;
CREATE TRIGGER insert_seat AFTER INSERT ON Studio FOR EACH ROW CALL isrt_set(new.Stu_ID, new.Stu_Rows, new.Stu_cols);

-- 触发器：删除演出厅同时删除对应的座位和演出计划
DELIMITER //
CREATE TRIGGER del_studio_link BEFORE DELETE ON Studio FOR EACH ROW
BEGIN
    DELETE FROM Schedule WHERE Sch_StuID=old.Stu_ID;
    DELETE FROM Seat WHERE Set_StuID=old.Stu_ID;
END
//
DELIMITER ;

-- 触发器：删除影片时删除对应的演出计划
CREATE TRIGGER del_movie_link BEFORE DELETE ON Movie FOR EACH ROW 
    DELETE FROM Schedule WHERE Sch_MovID=old.Mov_ID;

-- 触发器：更改座位状态时票的状态
DELIMITER //
CREATE TRIGGER seat_tk_status AFTER UPDATE ON Seat FOR EACH ROW 
BEGIN
    IF new.Set_Type=1 THEN
        UPDATE Tickets SET TK_Status=3 WHERE TK_SeatID=new.Set_ID;
    END IF;
END
//
DELIMITER ;

-- 触发器：更改演出厅时新增、删除座位
DELIMITER //
CREATE PROCEDURE isrt_newset(STUID BIGINT, OLDROWS TINYINT, NEWROWS TINYINT, OLDCOLS TINYINT, NEWCOLS TINYINT)
BEGIN
    DECLARE i INT;
    DECLARE j INT;

    SET i=OLDROWS+1;
    WHILE i<=NEWROWS DO
        SET j=OLDCOLS+1;
        WHILE j<=NEWCOLS DO
            INSERT INTO Seat VALUES(default, STUID, i, j, 2);
            SET j=j+1;
        END WHILE;
        SET i=i+1;
    END WHILE;
END
//
CREATE TRIGGER studio_seat_link AFTER UPDATE ON Studio FOR EACH ROW 
BEGIN
    DELETE FROM Seat WHERE Seat.Set_Row>new.Stu_Rows OR Seat.Set_Col>new.Stu_Cols;
    CALL isrt_newset(new.Stu_ID, old.Stu_Rows, new.Stu_Rows, old.Stu_cols, new.Stu_cols);
END
//
DELIMITER ;

-- 触发器：自动产生售票/退票记录
DELIMITER //
CREATE TRIGGER auto_salerec AFTER UPDATE ON Tickets FOR EACH ROW
BEGIN
    DECLARE price BIGINT;
    SET price=(SELECT Sch_Price FROM Schedule WHERE Sch_ID=old.Tk_SchID);
    IF (old.TK_Status=2 AND new.TK_Status=1) THEN
        INSERT INTO Sales_Ticket VALUES(default, old.TK_ID, price);
    ELSEIF (old.TK_Status=1 AND new.TK_Status=2) THEN
        INSERT INTO Sales_Ticket VALUES(default, old.TK_ID, -price);
    END IF;
END
//
DELIMITER ;