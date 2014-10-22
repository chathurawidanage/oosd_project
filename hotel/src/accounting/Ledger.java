/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accounting;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import ui.MainWindow;
import utilities.DataBase;

/**
 *
 * @author Lasantha Ekanayake <lasantha.12@cse.mrt.ac.lk>
 */
public class Ledger {


    //return the current balance
    public double getCurrentBalance() {

        double balance = 0.0;
        ArrayList<String> cols = new ArrayList<>();
        cols.add("amount");
        cols.add("type");

        ResultSet results = DataBase.select("transactions", cols, "1=1");
        try {
            if (results.next()) {
                do {
                    balance += ((results.getDouble("amount")) * ((results.getInt("type")) > 0 ? 1 : -1));
                    System.out.println(results.getDouble("amount"));
                    System.out.println(results.getInt("type"));

                } while (results.next());
            }
        } catch (SQLException ex) {
            MainWindow.showError("Error", "Database connection failed!");
        }
        return balance;

    }

    /**
     *
     * @param complete Full payment = true. Advance = false
     * @param amount Payment amount
     * @param userId id of the client
     * @param userName name of the client
     * @param hallName the name of the hall that is reserved
     * @return true if success. otherwise false
     */
    
    public static boolean addReservation(boolean complete, double amount, int userId, String userName, String hallName){
        int finished = 0;
        int type = 2;
        String details = hallName+" reservation by "+userName;
        if(complete){
            finished = 1;
            type = 1;
        }
        
        Transaction transaction = new Transaction(new Date(), amount, userId, details, type, finished);
        
        return (transaction.Save())>0;
        
    }
}
