/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;


import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author Chathura
 */
public class DataBase {

    private static Connect con = Connect.getInstance();

    /**
     *
     * @param table table to push the data in to
     * @param idCol name of the auto increment column
     * @param data data to be pushed
     * @return id of the new column
     */
    public static int insert(String table, String idCol, HashMap<String, Object> data) {
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
            Object value = colIterator.next();
            if (value instanceof Number) {
                sql += value.toString();

            } else {
                sql += "'" + value.toString() + "'";
            }
        }

        sql += ")";

        if (idCol != null) {
            return con.setQuery(sql, idCol);
        }

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

}
