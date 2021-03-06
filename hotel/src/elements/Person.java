/**
 * Class for the general element in the hotel management system
 *
 * @author Theekshana
 */
package elements;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import ui.MainWindow;

public class Person {

    private String Id;  // NIC
    private String name = "";
    private String address = "";
    private String contact = "";
    private String Details = "" ;
    private int colID ;
    private HashMap hash = new HashMap();
    protected static final int SUCCESS_SAVE = 0 ; 
    protected static final int ERROR_ID = -1 ;
    protected static final int ERROR_NAME = -2 ;
    protected static final int ERROR_CONTACT = -3 ;

    // get set methods for the private variables
    public int getId() {  // return the id of in the table person
        return colID ; // return -1 if db error els the column id
    }

    public void setId(String Id) {
        this.Id = Id;
    }
    
    public String getNIC() {
        
        return Id ;
    }

    public String getName() {
        return name;
    }

    public String getDetails() {
        return Details;
    }

    public void setDetails(String Details) {
        this.Details = Details;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    // save the objects in the database calling the database object
    protected int  save() {

        if (isValidID(Id)) {
            // a valid Id input
            if (isValidName(name)) {
                // valid name and a ID
                if (isValidContact(contact)) {
                    // all the feids are ready to be saved
                    int id = utilities.DataBase.insert("person", "Id", hash); // add to the table 
                    return id;  // return the primary id of the table
         

                } else {
                    return ERROR_CONTACT;
                }

            } else {
                return ERROR_NAME;
            }

        } else {
            return ERROR_ID; // invalid NIC
        }
    }

    // to validate the Id variable
    private boolean isValidID(String ID) {

        if (ID.length() != 10) // ID has to be of length 10
        {
            return false;
        } else {

            String numpart = ID.substring(0, ID.length() - 1);  // number part of the ID
            String charac = ID.substring(ID.length() - 1, ID.length());
            // character part of the ID has to either 'v' or 'x'
            if (charac.equalsIgnoreCase("x") || charac.equalsIgnoreCase("v")) {

                try {

                    int intpart = Integer.valueOf(numpart);
                    return true;

                } catch (Exception e) {  // not a number in the number part

                    return false;
                }

            } else // invalid character at the end of the ID
            {
                return false;
            }

        }

    }

    // check the validity of the name 
    private boolean isValidName(String name) {

        for (int i = 0; i < name.length(); i++) {

            if (!Character.isAlphabetic(name.charAt(i))) {
                return false;
            }

        }
        return true;
    }

    //check the validity of the contact
    private boolean isValidContact(String contact) {

        try {

            int cont = Integer.valueOf(contact);
            return true;

        } catch (Exception e) {

            return false;
        }

    }

    // add to the hash map
    public void addToHashMap(String key, String value) {

        hash.put(key, value);

    }
    
    public void addToHashMap( String key , double value ) {
        
        hash.put( key , value ) ;
    }

    // constructor for the person class
    public Person(String Id, String name, String address, String contact , String details) {

        
        this.Id = Id;
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.Details = details ;
        addToHashMap("NIC", Id);
        addToHashMap("Name", name);
        addToHashMap("Address", address);
        addToHashMap("Contact", contact);
        addToHashMap("Details", Details);
       

    }
    
    // constructor returning the object for the id given
    public Person( int id) {
        
        
        try {
            ArrayList getCol = new ArrayList();
            getCol.add("Id") ;
            getCol.add("NIC") ;
            getCol.add("Name") ;
            getCol.add("Address") ;
            getCol.add("details") ;
            getCol.add("Details");
            ResultSet set = utilities.DataBase.select("person", getCol, "Id = "+id) ;
            set.next();
            colID = set.getInt("Id") ;
            this.Id = set.getString("NIC") ;
            this.name = set.getString("Name") ;
            this.address = set.getString("Address") ;
            this.contact = set.getString("Contact") ;
            this.Details = set.getString("details") ;
        } catch (SQLException ex) {
            MainWindow.showError("Error", "Cannot access the database");
            colID = -1 ;
        }
          
        
        
        
        
    }

}
