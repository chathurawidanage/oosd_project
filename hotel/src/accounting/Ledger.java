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
     * @param from Date when to search from<to
     * @param to Date when to search to
     * @return ArrayList of Transactions
     */
    public ArrayList<Transaction> getTransactions(Date from, Date to){
        ArrayList<Transaction> list = new ArrayList<>();
        
        long f = from.getTime();
        long t = from.getTime();
        java.sql.Timestamp sqlFrom = new java.sql.Timestamp(f);
        java.sql.Timestamp sqlTo = new java.sql.Timestamp(t);
        
        String query = "SELECT * FROM transactions WHERE date>'"+sqlFrom+"' AND date<'"+sqlTo+"';";
        
        
        ResultSet results = DataBase.getQuery(query);
        try {
            while(results.next()){
                list.add(new Transaction(results.getDate("date"), results.getDouble("amount"), results.getInt("user"), results.getString("details"), results.getInt("type"), results.getInt("completed")));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Ledger.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
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
    
    /**
     *
     * @param amount Payment amount
     * @param userId id of the supplier
     * @param details  details about the bill
     * @return true if success. otherwise false
     */
    
    public static boolean addBill( double amount, int userId, String details){
 
        Transaction transaction = new Transaction(new Date(), amount, userId, details, -1, 1);
        
        return (transaction.Save())>0;
        
    }
}
