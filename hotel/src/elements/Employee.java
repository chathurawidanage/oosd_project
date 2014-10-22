
package elements;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import ui.MainWindow;

/**
 * Derived class from the Person class 
 * @author Theekshana
 */
public class Employee extends Person {
    
    
    private Date joinedData =null;  
    private Double salary =0.0;
    private boolean isWorking = false ; // working or temp or perm signed out
    private String post ="" ;    // the post of the employer , eg: manager 
    private HashMap hMap = new HashMap() ;
     
     
     // state changing methods
     
     
     public boolean upDateSalary(Double salary) {
         
         this.salary = salary ;
         return true ;
                
     }
    
     public boolean changeState(boolean state) {
         
         this.isWorking = state ;
         return true ;
     }
     
     public boolean changePost(String post) {
         
         this.post = post ;
         return true ;
         
         
         
     }
     
    //get and set metthods
    public Date getJoinedData() {
        return joinedData;
    }

    public void setJoinedData(Date joinedData) {
        this.joinedData = joinedData;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public boolean isIsWorking() {
        return isWorking;
    }

    public void setIsWorking(boolean isWorking) {
        this.isWorking = isWorking;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }
    
    public int save() {
        
          int id = super.save() ;
          if( id >=0) {
              
              hMap.put("employeeId", id) ;
              utilities.DataBase.insert("employee", "Id", hMap) ;
              return SUCCESS_SAVE ;  // return 0 as the success save
              
              
          }else {
              
              return id ; // return the error code
          }
        
          
    }
     
     
    //constructors
    
    public Employee(String Id, String name, String address, String contact , Double salary , String post , String Details) {
        super(Id, name, address, contact , Details) ;
        this.salary = salary ;
        this.post = post ;
        hMap.put("salary", salary) ;
        hMap.put("post", post) ;
    }
    
    
    public Employee(int id) {
        
        super(id);
          try {
            ArrayList getCol = new ArrayList();
            getCol.add("Salary") ;
            getCol.add("post");
            ResultSet set = utilities.DataBase.select("employee", getCol, "Id = "+id) ;
            set.next();
            this.post = set.getString("post");
            this.salary = set.getDouble("Salary") ;
        } catch (SQLException ex) {
            MainWindow.showError("Error", "Cannot access the database");
            
        }
          
        
    }
    
    
    
}
