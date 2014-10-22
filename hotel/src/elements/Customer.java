/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package elements;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import ui.MainWindow;

/**
 * Customer class 
 * @author Theekshana
 */
public class Customer extends Person {
    
    
    HashMap hMap = new HashMap() ;


   public int save(){
       
       
       int id = super.save() ; // save at the person class
       if( id > 0 ) {
           
           hMap.put("personId", id) ;
           //now save in the customer method
           utilities.DataBase.insert("customer", "Id", hMap) ;
           return SUCCESS_SAVE ; 
                  
       }else {
           
           return id ; // return the error code for erro
           
       }
       
       
   }
 
   
   // save the details of the person in the table and the get the table id 
   
 
    // the constructor
    public Customer(String Id, String name, String address, String contact,String detailing) {
        super(Id, name, address, contact , detailing);
        
        
    }
    
    public Customer(int id ){
        
        super(id) ;
        
    }
    
} 
