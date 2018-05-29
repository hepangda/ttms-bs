package com.hepangda.ttms.dao;

import com.hepangda.ttms.idao.IEmployeeDAO;
import com.hepangda.ttms.model.Employee;

import java.sql.*;
import java.util.ArrayList;

/*
CREATE TABLE Employee (
    Emp_ID SERIAL PRIMARY KEY,
    Emp_LoginName VARCHAR(30) NOT NULL UNIQUE,
    Emp_Password CHAR(32) NOT NULL,
    Emp_Name VARCHAR(30) NOT NULL,
    Emp_BornYear SMALLINT NOT NULL,
    Emp_Phonenumber VARCHAR(14),
    Emp_Privilege TINYINT NOT NULL DEFAULT 0
);

CREATE VIEW User AS
    SELECT Emp_ID,Emp_LoginName,Emp_Password,Emp_Privilege FROM Employee;
*/
public class EmployeeDAO extends BaseDAO implements IEmployeeDAO {
    public int verifyLoginInfo(String username, String password) {
        int retval = 0;
        try {
            Statement stmt = super.getStatement();
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM User WHERE Emp_LoginName=\'" + username
                    + "\' AND Emp_PASSWORD=md5(\'" + password + "\');");
            rs.next();
            int retval = rs.getInt(1);
//            System.out.println(retval);
            if (retval == 1)
                return 1;

            rs.next();//不然什么数据都没有
            retval = rs.getInt(1);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return retval;
    }
    public int AddEmployee(String name,int age,int phone,String position,String password){
        int retval = 0;
        try{
            Statement st = super.getStatement();
            ResultSet rs = st.executeQuery("insert into Employee values(\'"+name+"\',"
                        +age+","+phone+",\'"+position+"\',"+"md5(\'" + password + ")\');");
            rs.next();
            retval = rs.getInt(1);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return retval;//返回序号
    }
    public int DeleteEmployee(int ID){
        boolean ret;
        try{
            Statement st = super.getStatement();
            ret = st.execute("delete from table Employee where Emp_ID="+ID+";");
            if(ret){
                return 1;//成功
            }else{
                return 0;//失败
            }

        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    public int DeleteEmployee(String name){
        boolean ret;
        try{
            Statement st = super.getStatement();
            ret = st.execute("delete from table Employee where Emp_Name=\'"+name+"\';");
            if(ret){
                return 1;//成功
            }else{
                return 0;//失败
            }

        }catch(Exception ex){
            ex.printStackTrace();
        }

    }
    public ArrayList<Employee> QueryEmployee(int ID){
        ArrayList<Employee> res = new ArrayList<Employee>();
        Employee ret =new Employee();
        try{
            Statement st = super.getStatement();
            ResultSet rs = st.executeQuery("select * from Employee where Emp_ID="+ID+";");
            while(rs.next()){
                ret.setID(rs.getInt("Emp_ID"));
                ret.setEmpLoginName(rs.getString("Emp_LoginName"));
                ret.setEmpName(rs.getString("Emp_Name"));
                ret.setBornYear(rs.getInt("Emp_BornYear"));
                ret.setPrivilege(rs.getInt("Emp_Privilege"));
                res.add(ret);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return res;
    }
    public ArrayList<Employee> QueryEmployee(String name){
        ArrayList<Employee> res = new ArrayList<Employee>();
        Employee ret =new Employee();
        try{
            Statement st = super.getStatement();
            ResultSet rs = st.executeQuery("select * from Employee where Emp_Name=\'"+name+"\';");
            while(rs.next()){
                ret.setID(rs.getInt("Emp_ID"));
                ret.setEmpLoginName(rs.getString("Emp_LoginName"));
                ret.setEmpName(rs.getString("Emp_Name"));
                ret.setBornYear(rs.getInt("Emp_BornYear"));
                ret.setPrivilege(rs.getInt("Emp_Privilege"));
                res.add(ret);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return res;
    }
}
