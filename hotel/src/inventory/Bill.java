/*
 * this is the bill object.
 *
 */

package inventory;

import elements.Supplier;
import java.util.ArrayList;
import java.util.Date;

/*
 * @author lakshitha
 */
public class Bill {
    private String title;
    private String description; //description for bill
    private int total=0;          //total price of the bill
    private Supplier supplier;  //supplier name
    private Date date;          //paid date
    private int paid;           //paid amount 
    private int balance;        //resived amount 
    private ArrayList<OneObject> element=new ArrayList<OneObject>();
    
    //----------------set mothodes---------------------------------------
    public void setTitle(String a){
        title=a;
    }
    public void setDescription(String a){
        description=a;
    }
    public void enterElement(String ty,int pr,int am){
        element.add(new OneObject(ty, pr, am));
        total+=pr;
    }
    public void setSupplier(String Id, String name, String address, String contact, String Details){
        supplier=new Supplier(Id,name,address,contact,Details);
    }
    public void setDate(String s){
        date=new Date(s);
    }
    public void setPaidAmount(int a){
        paid=a;
    }
    public void setBalance(int a){
        balance=a;
    }
    //get methodes----------------------------------------------------
    public String getTitle(){
        return title;
    }
    public String getDescription(String a){
        return description;
    }
    public OneObject getElement(int a){
        return element.get(a);
    }
    public int getTotal(){
        return total;
    }
    public Supplier getSupplier(){
        return supplier;
    }
    public Date getDate(){
        return date;
    }
    public int getPaidAmount(){
        return paid;
    }
    public int getBalance(){
        return balance;
    }
    //-------------edit element--------------------------------------
    public void delElement(int indx){
        int oldPrice= element.get(indx).getPrice();
        element.remove(indx);
        total-=oldPrice;
    }
    public void changeAmount(int indx,int value){
        element.get(indx).setAmount(value);
    }
    public void changePrice(int indx,int value){
        int oldPrice= element.get(indx).getPrice();
        element.get(indx).setPrice(value);
        total+=value-oldPrice;        
    }
    //------get element values-----------------
    public String getElemenType(int indx){
        return element.get(indx).getType();
    }
    public int getElemenAmount(int indx){
        return element.get(indx).getAmount();
    }
    public int getElemenPrice(int indx){
        return element.get(indx).getPrice();
    }
}
