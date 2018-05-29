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
    public int Add_Studio(String name,int type,int row,int column){
        int retval = 0;
        try{
            Statement st = super.getStatement();
            ResultSet rs = st.executeQuery("insert into Studio values(\'" + name
                    + "\', \'" + type + "\', \'" + row + "\', \'" + column + "\');");
            rs.next();
            retval = rs.getInt(1);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return retval;

    }
    public int Delete_Studio(String name){
        boolean ret;
        try{
            Statement st = super.getStatement();
            ret = st.execute("delete from Studio where Stu_Name"+"\'"+name+"\';");
            if(ret){
                return 1;//成功
            }else {
                return 0;//失败
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    public int Modify_Studio(String OldName,String NewName){
        boolean ret;
        try{
            Statement st = super.getStatement();
            ret = st.execute("update Studio set Stu_Name="+NewName+"where Stu_Name="+OldName+";");
            if(ret){
                return 1;//成功
            }else{
                return 0;//失败
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    public ArrayList<Studio> GetStduios(int begin, int end){
            ArrayList<Studio> res = new ArrayList<Studio>();
            try{
                Statement st = super.getStatement();
                ResultSet rs = st.executeQuery("select * from Studio where Stu_ID between"+begin+
                        "and"+end+");");
            Studio ret = new Studio();
            while(rs.next()) {
                ret.setID(rs.getInt("Stu_ID"));
                ret.setName(rs.getString("Stu_Name"));
                ret.setType(rs.getInt("Stu_Type"));
                ret.setRow(rs.getInt("Stu_Rows"));
                ret.setColumn(rs.getInt("Stu_Cols"));
                res.add(ret);
                }
            }catch(Exception ex){
                ex.printStackTrace();
            }
        return res;
    }
}
