/**
 * Class for the general element in the hotel management system
 *
 * @author Theekshana
 */
package elements;

import java.util.HashMap;

public class Person {

    private String Id;  // NIC
    private String name = "";
    private String address = "";
    private String contact = "";
    private HashMap hash = new HashMap();

    // get set methods for the private variables
    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getName() {
        return name;
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
    public String save(String table) {

        if (isValidID(Id)) {
            // a valid Id input
            if (isValidName(name)) {
                // valid name and a ID
                if (isValidContact(contact)) {

                    // all the feids are ready to be saved
                    utilities.DataBase.insert(table, "Id", hash); // add to the table  
                    return "Success";

                } else {
                    return "Invalid Contact";
                }

            } else {
                return "Invalid Name";
            }

        } else {
            return "Invalid ID"; // inu
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
    public Person(String Id, String name, String address, String contact) {

        
        this.Id = Id;
        this.name = name;
        this.address = address;
        this.contact = contact;
        addToHashMap("NIC", Id);
        addToHashMap("Name", name);
        addToHashMap("Address", address);
        addToHashMap("Contact", contact);
       

    }

}
