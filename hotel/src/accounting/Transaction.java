package accounting;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Lasantha Ekanayake <lasantha.12@cse.mrt.ac.lk>
 */
public class Transaction {
    private double amount = 0.00;
    private Date date = null;
    private String details = "";    // ex: "Bill Payment" , "Cash Recieved from xxxxx"
    private boolean type = false;   // true for credit and false for debit transactions
    private int id = 0; // unique for each transactions
    
    // constructors
    public Transaction(Integer id, Date date, double amount, String details){
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.details = details;
        this.type = amount>0;
    }
    
    
    
}
