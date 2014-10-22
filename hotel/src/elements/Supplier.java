

package elements;

import java.util.HashMap;

/**
 * Supplier class 
 * @author Theekshana
 */
public class Supplier extends Person {
    
  
    HashMap hMap = new HashMap() ;
    private int noOfTransactions = 0 ;
    
    
    public int getNoOfTransactions() {
        
        return noOfTransactions ;
    }
    
    //make the transaction +1
    public boolean incrementTransactions() {
        
        noOfTransactions += 1 ; 
        return true ;
    }

    // get ans set methods
    
    public void Save() {
        
          int id = super.save() ;
          if( id >= 0 ) {
              
              hMap.put("supplierId", id);
              utilities.DataBase.insert("supplier", "Id", hMap) ;
              
          }
        
    }
    
    // constrctor
    public Supplier(String Id, String name, String address, String contact, String Details ) {
        
   
        super(Id, name, address, contact , Details);
        
 
        
    }
    
    
    
}
