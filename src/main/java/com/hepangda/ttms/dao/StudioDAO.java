package com.hepangda.ttms.dao;

/*
CREATE TABLE Studio (
    Stu_ID SERIAL PRIMARY KEY,
    Stu_Name VARCHAR(30) UNIQUE NOT NULL,
    Stu_Type TINYINT NOT NULL,
    Stu_Rows SMALLINT NOT NULL,
    Stu_Cols SMALLINT NOT NULL
);
 */

import java.sql.*;
import java.util.ArrayList;

import com.hepangda.ttms.idao.IStudioDAO;
import com.hepangda.ttms.model.Studio;

public class StudioDAO extends BaseDAO implements IStudioDAO {
    public int Add_Studio(String name, int type, int row, int column) {
        int retval = 0;
        try {
            PreparedStatement stmt = super.getStatement(
                    "INSERT INTO Studio VALUES(default,?,?,?,?)"
            );

            stmt.setString(1, name);
            stmt.setInt(2, type);
            stmt.setInt(3, row);
            stmt.setInt(4, column);

            ResultSet rs = stmt.executeQuery();
            rs.next();
            retval = rs.getInt(1);

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {

        }
        return retval;

    }

    public int Delete_Studio(String name) {
        boolean ret;
        try {
            Statement st = super.getPureStatement();
            ret = st.execute("delete from Studio where Stu_Name" + "\'" + name + "\';");
            if (ret) {
                return 1;//成功
            } else {
                return 0;//失败
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return 0;
    }

    public int Modify_Studio(String OldName, String NewName) {
        boolean ret;
        try {
            Statement st = super.getPureStatement();
            ret = st.execute("update Studio set Stu_Name=" + NewName + "where Stu_Name=" + OldName + ";");
            if (ret) {
                return 1;//成功
            } else {
                return 0;//失败
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return 0;
    }

    public ArrayList<Studio> GetStduios(int begin, int end) {
        ArrayList<Studio> res = new ArrayList<>();
        try {
            Statement st = super.getPureStatement();
            ResultSet rs = st.executeQuery("SELECT * from Studio where Stu_ID between" + begin +
                    "AND" + end + ");");
            Studio ret = new Studio();
            while (rs.next()) {
                ret.setID(rs.getInt("Stu_ID"));
                ret.setName(rs.getString("Stu_Name"));
                ret.setType(rs.getInt("Stu_Type"));
                ret.setRow(rs.getInt("Stu_Rows"));
                ret.setColumn(rs.getInt("Stu_Cols"));
                res.add(ret);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return res;
    }
}
