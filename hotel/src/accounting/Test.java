/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accounting;

import java.sql.SQLException;
import java.util.Date;
import ui.MainWindow;

/**
 *
 * @author Lasantha Ekanayake <lasantha.12@cse.mrt.ac.lk>
 */
public class Test {

   
    
    public static void main(String[] args) throws SQLException {
        Transaction test;
        Ledger ledger = new Ledger();
        System.out.println(ledger.getTransactions(new Date(2014, 07, 01), new Date(2014, 10, 20)).get(0).getTypeString());
//        System.out.println(ledger.getCurrentBalance());
//        test = new Transaction(new Date(2014, 10, 22), 10775.45,1, "Test Transaction", -1,0);
       // MainWindow.showError("Error", "Database connection failed!");
        //test = new Transaction(2);
        
        //System.out.println(test.Save());
        //System.out.println(test.deleteTransaction(2));
    }
}
