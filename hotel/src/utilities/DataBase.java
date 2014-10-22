/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author Chathura Widanage<chathurawidanage@gmail.com>
 */
public class DataBase {

    private static Connect con = Connect.getInstance();

    /**
     *
     * @param table table to push the data in to
     * @param returnCol name of the auto increment column
     * @param data data to be pushed
     * @return id of the new column
     */
    public static int insert(String table, String returnCol, HashMap<String, Object> data) {
        String sql = "INSERT INTO " + table + "(";
        Set<String> cols = data.keySet();
        Iterator<String> colIterator = cols.iterator();
        while (colIterator.hasNext()) {
            sql += colIterator.next();
            if (colIterator.hasNext()) {
                sql += ",";
            }
        }
        sql += ") VALUES(";

        colIterator = cols.iterator();
        while (colIterator.hasNext()) {
            String key = colIterator.next();
            Object value = data.get(key);
            if (value instanceof Number) {
                sql += value.toString();

            } else if (value instanceof Boolean) {
                if (value.equals(Boolean.TRUE)) {
                    sql += 1;
                } else {
                    sql += 0;
                }
            } else if (value instanceof Date) {
                long d = ((Date) value).getTime();
                java.sql.Timestamp sqlDate = new java.sql.Timestamp(d);
                sql += "'" + sqlDate + "'";
            } else {
                sql += "'" + value.toString() + "'";
            }

            if (colIterator.hasNext()) {
                sql += ",";
            }
        }

        sql += ")";

        if (returnCol != null) {
            return con.setQuery(sql, returnCol);
        }

        System.out.println(sql);
        if (con.setQuery(sql)) {
            return 0;//successfull
        } else {
            return -1;//failed
        }

    }

    /**
     *
     * @param table table to select data from
     * @param cols columns to fetch
     * @param where conditions to check
     * @return
     */
    public static ResultSet select(String table, ArrayList<String> cols, String where) {
        String sql = "SELECT ";
        for (int i = 0; i < cols.size(); i++) {
            sql += cols.get(i);
            if (i != cols.size() - 1) {
                sql += ",";
            }
        }
        sql += " FROM " + table + " WHERE " + where;
        return con.getQuery(sql);
    }

    /**
     *
     * @param qry query to perform
     * @return success status
     */
    public static boolean setQuery(String qry) {
        return con.setQuery(qry);
    }

    /**
     *
     * @param qry query to perform
     * @param colName column name to return the auto generated index
     * @return auto generated index
     */
    public static int setQuery(String qry, String colName) {
        return con.setQuery(qry, colName);
    }

    /**
     *
     * @param qry query to perform
     * @return ResultSet
     */
    public static ResultSet getQuery(String qry) {
        return con.getQuery(qry);
    }

    /**
     * Deletes a record form a table
     * @param table name of the table
     * @param where conditions for the deleting record
     * @return 
     */
    public boolean delete(String table, String where) {
        String sql = "DELETE FROM " + table + " WHERE " + where;
        return con.setQuery(sql);
    }
}
