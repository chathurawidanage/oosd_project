/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

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
        String qry = "INSERT INTO " + table + "(";
        Set<String> cols = data.keySet();
        Iterator<String> colIterator = cols.iterator();
        while (colIterator.hasNext()) {
            qry += colIterator.next();
            if (colIterator.hasNext()) {
                qry += ",";
            }
        }
        qry += ") VALUES(";

        colIterator = cols.iterator();
        while (colIterator.hasNext()) {
            Object value = colIterator.next();
            if (value instanceof Number) {
                qry += value.toString();

            } else {
                qry += "'" + value.toString() + "'";
            }
        }

        qry += ")";

        if (idCol != null) {
            return con.setQuery(qry, idCol);
        }

        if (con.setQuery(qry)) {
            return 0;//successfull
        } else {
            return -1;//failed
        }

    }

}
