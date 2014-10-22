/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accounting;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import ui.MainWindow;
import utilities.DataBase;

/**
 *
 * @author Lasantha Ekanayake <lasantha.12@cse.mrt.ac.lk>
 */
public class Ledger {

    private Transaction transaction;

    public double getCurrentBalance() {

        double balance = 0.0;
        ArrayList<String> cols = new ArrayList<>();
        cols.add("amount");
        cols.add("type");

        ResultSet results = DataBase.select("transactions", cols, "1=1");
        try {
            if (results.next()) {
                do {
                    balance+=((results.getDouble("amount")) * ((results.getInt("type"))>0 ? 1 : -1));
                    System.out.println(results.getDouble("amount"));
                    System.out.println(results.getInt("type"));
                    
                } while (results.next());
            }
        } catch (SQLException ex) {
            //error message
        }
        return balance;

    }

}
