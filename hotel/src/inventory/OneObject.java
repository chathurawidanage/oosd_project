/*
 * this is an object for an one element of element
 */
package inventory;

public class OneObject {

    private String type;   //name of element
    private int amount;    //amount of element
    private int price;      //price of element

    public OneObject(String x, int y, int z) {
        type = x;
        amount = y;
        price = z;
    }
    public void setType(String a){
        type = a;
    }
    public void setAmount(int a){
        amount = a;
    }
    public void setPrice(int a){
        price = a;
    }
    public String getType(){
        return type;
    }
    public int getAmount(){
        return amount;
    }
    public int getPrice(){
        return price;
    }
}
