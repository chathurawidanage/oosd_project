/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package elements;

import java.util.HashMap;

/**
 * Customer class 
 * @author Theekshana
 */
public class Customer extends Person {
    
    
    HashMap hMap = new HashMap() ;


   public void Save(){
       
       
       int id = super.save() ; // save at the person class
       if( id >= 0 ) {
           
           hMap.put("personId", id) ;
           //now save in the customer method
           utilities.DataBase.insert("customer", "Id", hMap) ;
           
                  
       }else {
           
           // show a error message
           
       }
       
       
   }
 
   
   // save the details of the person in the table and the get the table id 
   
 
    // the constructor
    public Customer(String Id, String name, String address, String contact,String detailing) {
        super(Id, name, address, contact , detailing);
        
        
    }
    
} 
