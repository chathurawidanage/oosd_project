package accounting;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
    private String details = "";    // ex: "Bill Payment" , "Cash Recieved"
    private int type = 0;   // type>0 for credit and type<0 for debit transactions
    private int id = 0; // unique for each transactions
    private int user = 0;

    // constructors
    public Transaction(Date date, double amount, int user, String details, int type) {
        this.date = date;
        this.amount = amount;
        this.user = user;
        this.details = details;
        this.type = type;
    }

    public Transaction(int id) throws SQLException {
        ArrayList<String> col = new ArrayList<>();
        col.add("date");
        col.add("amount");
        col.add("user");
        col.add("details");
        col.add("type");

        ResultSet results = DataBase.select("transactions", col, "id = " + id);

        if (results.next()) {
            this.date = results.getDate("date");
            this.amount = results.getDouble("amount");
            this.user = results.getInt("user");
            this.details = results.getString("details");
            this.type = results.getInt("type");
        }

    }

    // save in the database
    public int Save() {
        HashMap<String, Object> data = new HashMap<>();

        data.put("date", this.date);
        data.put("amount", this.amount);
        data.put("details", this.details);
        data.put("type", this.type);

        this.id = DataBase.insert("transactions", "id", data);

        return this.id;
    }

}
