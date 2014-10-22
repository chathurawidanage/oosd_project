package accounting;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import utilities.DataBase;

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
    public Transaction(Date date, double amount, String details, boolean type){
        this.date = date;
        this.amount = amount;
        this.details = details;
        this.type = type;
    }
    
    // save in the database
    public int Save(){
        HashMap<String,Object> data = new HashMap<>();
        
        data.put("date", this.date);
        data.put("amount", this.amount);
        data.put("details", this.details);
        data.put("type", this.type);
        
        this.id = DataBase.insert("transactions", "id", data);
        
        return this.id;
    }
    
}
