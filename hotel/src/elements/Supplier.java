

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
    
    public int save() {
        
          int id = super.save() ;
          if( id >= 0 ) {
              
              hMap.put("supplierId", id);
              utilities.DataBase.insert("supplier", "Id", hMap) ;
              return SUCCESS_SAVE ; // return 0 as the success code
              
          }else
               return id ; // return the error code
        
    }
    
    // constrctor
    public Supplier(String Id, String name, String address, String contact, String Details ) {
        
   
        super(Id, name, address, contact , Details);
        
 
        
    }
    
    public Supplier(int id) {
        
        super(id) ;
    }
    
}
