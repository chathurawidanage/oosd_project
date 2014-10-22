

package inventory;

import java.util.ArrayList;

/**
 *
 * @author lakshitha
 */
public class inventory {

    public static void main(String[] args) {
     ArrayList<Bill> kitchen1=new ArrayList<Bill>();
     ArrayList<Bill> maintain1=new ArrayList<Bill>();
     
     kitchen1.add(new Bill());
     maintain1.add(new Bill());
     
     kitchen1.get(0).setTitle("getting foods for kitchen 1");
     kitchen1.get(0).enterElement("orange", 20, 100);
     
        System.out.println(
                "----------"+kitchen1.get(0).getTitle()+"----------\n"+
                "type of food:"+kitchen1.get(0).getElemenType(0)+"            "+
                "amount:"+kitchen1.get(0).getElemenAmount(0)+    "            "+
                "price:"+kitchen1.get(0).getElemenPrice(0));
    }
    
}
