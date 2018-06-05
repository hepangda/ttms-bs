package com.hepangda.ttms.dao;

import com.hepangda.ttms.annotation.QueryKey;
import com.hepangda.ttms.annotation.QueryTable;
import com.hepangda.ttms.exception.DangerSQLException;
import com.hepangda.ttms.exception.FrameworkException;
import com.hepangda.ttms.model.Employee;
import com.hepangda.ttms.util.QueryResult;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;

class BaseDAO {
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "980217";
    private static final String DB_CONNSTR = "jdbc:mysql://localhost:3306/ttms?serverTimezone=UTC";

    private Connection _Conn;
    BaseDAO() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            _Conn = DriverManager.getConnection(DB_CONNSTR, DB_USERNAME, DB_PASSWORD);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            System.err.println("Cannot found MySQL/JDBC Driver.");
            ex.printStackTrace();
        }
    }

    Statement getPureStatement() throws SQLException {
        return _Conn.createStatement();
    }

    PreparedStatement getStatement(String sql) throws SQLException {
        return _Conn.prepareStatement(sql);
    }

    private boolean notZeroValue(Field obj, Object x) throws IllegalAccessException {
        return !(obj.get(x) == null || obj.get(x).toString().equals("0") || obj.get(x).toString().equals("0.0"));
    }

    private <T> String getSelectSQLString(T obj) {
        QueryTable qta = obj.getClass().getAnnotation(QueryTable.class);
        if (qta == null) {
            throw new FrameworkException();
        }

        StringBuilder baseString = new StringBuilder("SELECT * FROM " + qta.value() + " WHERE");
        boolean first = true;
        try {
            Field[] fields = obj.getClass().getDeclaredFields();
            for (Field f: fields) {
                f.setAccessible(true);
                if (notZeroValue(f, obj)) {
                    QueryKey qka = f.getAnnotation(QueryKey.class);
                    if (qka == null || !qka.select()) {
                        continue;
                    }
                    if (first) {
                        baseString.append(' ');
                        first = false;
                    } else {
                        baseString.append(" AND ");

                    }
                    if (f.getType().getName().equals("java.lang.String")) {
                        baseString.append(qka.value());
                        baseString.append(" LIKE ?");
                    } else {
                        baseString.append(qka.value());
                        baseString.append("=?");
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        if (baseString.toString().endsWith("WHERE"))
            baseString.delete(baseString.length() - 6, baseString.length());

        baseString.append(';');
        return baseString.toString();
    }

    private <T> PreparedStatement getSelectStatement(T tk) throws SQLException, IllegalAccessException {
        PreparedStatement stmt = getStatement(getSelectSQLString(tk));
        int i = 1;

        Field[] fields = tk.getClass().getDeclaredFields();
        for (Field f: fields) {
            f.setAccessible(true);
            Object tko = f.get(tk);
            QueryKey qka = f.getAnnotation(QueryKey.class);
            if (qka != null && notZeroValue(f, tk) && qka.select()) {
                if (tko instanceof String) {
                    stmt.setString(i++, "%" + tko + "%");
                } else {
                    stmt.setObject(i++, tko);
                }
            }
        }

        return stmt;
    }

    <T> QueryResult<T> normalSelect(T tk, int failedErrno, int successErrno) {
        QueryResult<T> qr = new QueryResult<>(null, failedErrno);
        try {
            PreparedStatement stmt = getSelectStatement(tk);
            ResultSet rs = stmt.executeQuery();
            Field[] fields = tk.getClass().getDeclaredFields();

            ArrayList<T> alrs = new ArrayList<>();
            while (rs.next()) {
                T newone = (T) tk.getClass().getConstructor().newInstance();

                for (Field f: fields) {
                    QueryKey qka = f.getAnnotation(QueryKey.class);
                    if (qka != null) {
                        f.setAccessible(true);
                        switch (f.getType().getName()) {
                            case "java.lang.Integer": case "int":
                                f.setInt(newone, rs.getInt(qka.value()));
                                break;
                            case "java.lang.Double": case "double":
                                f.setDouble(newone, rs.getDouble(qka.value()));
                                break;
                            case "java.lang.String":
                                f.set(newone, rs.getString(qka.value()));
                                break;
                            default:
                                throw new FrameworkException();
                        }
                    }
                }
                alrs.add(newone);
            }
            qr.setResults(alrs);
            qr.setRetno(successErrno);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return qr;
    }

    private <T> String getInsertSQLString(boolean pk, T obj) {
        QueryTable qta = obj.getClass().getAnnotation(QueryTable.class);
        if (qta == null) {
            throw new FrameworkException();
        }
        StringBuilder baseString = new StringBuilder("INSERT INTO " + qta.value() + " VALUES(");
        if (pk) {
            baseString.append("default,");
        }

        try {
            Field[] fields = obj.getClass().getDeclaredFields();
            for (Field f: fields) {
                f.setAccessible(true);
                QueryKey qka = f.getAnnotation(QueryKey.class);
                if (qka == null || !qka.insert()) {
                    continue;
                }
                if (qka.specialInsert()) {
                    baseString.append(qka.insertString());
                    baseString.append(',');
                } else {
                    baseString.append("?,");
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        baseString.deleteCharAt(baseString.length() - 1);
        baseString.append(");");
        return baseString.toString();
    }

    private <T> PreparedStatement getInsertStatement(boolean pk, T obj) throws SQLException, IllegalAccessException {
        PreparedStatement stmt = getStatement(getInsertSQLString(pk, obj));
        int i = 1;

        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field f: fields) {
            f.setAccessible(true);
            Object tko = f.get(obj);
            QueryKey qka = f.getAnnotation(QueryKey.class);
            if (qka != null && qka.insert()) {
                stmt.setObject(i++, tko);
            }
        }
        return stmt;
    }

    <T> int normalInsert(T tk, int failedErrno, int successErrno) {
        try {
            PreparedStatement stmt = getInsertStatement(true, tk);
            int rs = stmt.executeUpdate();

            if (rs > 0)
                return successErrno;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return failedErrno;
    }

    private <T> String getDeleteSQLString(T obj) throws DangerSQLException {
        QueryTable qta = obj.getClass().getAnnotation(QueryTable.class);
        if (qta == null) {
            throw new FrameworkException();
        }

        StringBuilder baseString = new StringBuilder("DELETE FROM " + qta.value() + " WHERE");
        boolean first = true;
        try {
            Field[] fields = obj.getClass().getDeclaredFields();
            for (Field f: fields) {
                f.setAccessible(true);
                if (notZeroValue(f, obj)) {
                    QueryKey qka = f.getAnnotation(QueryKey.class);
                    if (qka == null || !qka.delete()) {
                        continue;
                    }
                    if (first) {
                        baseString.append(' ');
                        first = false;
                    } else {
                        baseString.append(" AND ");

                    }

                    baseString.append(qka.value());
                    baseString.append("=?");
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        if (baseString.toString().endsWith("WHERE")) {
            throw new DangerSQLException();
        }

        baseString.append(';');
        return baseString.toString();
    }

    private <T> PreparedStatement getDeleteStatement(T tk) throws SQLException, IllegalAccessException {
        PreparedStatement stmt = getStatement(getDeleteSQLString(tk));
        int i = 1;

        Field[] fields = tk.getClass().getDeclaredFields();
        for (Field f: fields) {
            f.setAccessible(true);
            Object tko = f.get(tk);
            QueryKey qka = f.getAnnotation(QueryKey.class);
            if (qka != null && notZeroValue(f, tk) && qka.delete()) {
                stmt.setObject(i++, tko);
            }
        }

        return stmt;
    }

    <T> int normalDelete(T tk, int failedErrno, int successErrno) {
        try {
            PreparedStatement stmt = getDeleteStatement(tk);
            stmt.executeUpdate();

            return successErrno;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return failedErrno;
    }

    private <T> String getUpdateSQLString(T obj) {
        QueryTable qta = obj.getClass().getAnnotation(QueryTable.class);
        if (qta == null) {
            throw new FrameworkException();
        }

        StringBuilder baseString = new StringBuilder("Update " + qta.value() + " SET");
        boolean first = true;
        try {
            Field[] fields = obj.getClass().getDeclaredFields();
            for (Field f: fields) {
                f.setAccessible(true);
                if (notZeroValue(f, obj)) {
                    QueryKey qka = f.getAnnotation(QueryKey.class);
                    if (qka == null || qka.primaryKey()) {
                        continue;
                    }
                    if (first) {
                        baseString.append(' ');
                        first = false;
                    } else {
                        baseString.append(",");

                    }
                    baseString.append(qka.value());
                    baseString.append("=?");
                }
            }

            first = true;
            for (Field f: fields) {
                f.setAccessible(true);
                if (notZeroValue(f, obj)) {
                    first = false;
                    QueryKey qka = f.getAnnotation(QueryKey.class);
                    if (qka != null && qka.primaryKey()) {
                        baseString.append(" WHERE ");
                        baseString.append(qka.value());
                        baseString.append("=?");
                        break;
                    }
                }
            }

            if (first)
                throw new DangerSQLException();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        }

        if (baseString.toString().endsWith("SET"))
            baseString.delete(baseString.length() - 4, baseString.length());

        baseString.append(';');
        return baseString.toString();
    }

    private <T> PreparedStatement getUpdateStatement(T tk) throws SQLException, IllegalAccessException {
        PreparedStatement stmt = getStatement(getUpdateSQLString(tk));
        int i = 1;

        Field[] fields = tk.getClass().getDeclaredFields();
        for (Field f: fields) {
            f.setAccessible(true);
            Object tko = f.get(tk);
            QueryKey qka = f.getAnnotation(QueryKey.class);
            if (qka != null && notZeroValue(f, tk) && !qka.primaryKey()) {
                stmt.setObject(i++, tko);
            }
        }

        for (Field f: fields) {
            f.setAccessible(true);
            Object tko = f.get(tk);
            if (notZeroValue(f, tk)) {
                QueryKey qka = f.getAnnotation(QueryKey.class);
                if (qka != null && qka.primaryKey()) {
                    stmt.setObject(i++, tko);
                }
            }
        }
        return stmt;
    }

    <T> int normalUpdate(T tk, int failedErrno, int successErrno) {
        try {
            PreparedStatement stmt = getUpdateStatement(tk);
            stmt.executeUpdate();

            return successErrno;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return failedErrno;
    }
}
