/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package reservations;
import elements.Customer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import utilities.DataBase;
//import sun.util.calendar.LocalGregorianCalendar.Date;
/**
 *
 * @author aseladarshan
 */
public class Reservation {
    private final int NoOfHalls;
    private final ReceptionHall[] receptionHall;
    private ArrayList<String> dbCols;
    private final String dbTable;
    public Reservation(){
        dbCols.add("Date");
        dbCols.add("hall");
        dbTable="reservations";
        manageHalls mh=new manageHalls();
        receptionHall=manageHalls.getReceptionHall();
        NoOfHalls=manageHalls.getNoOfHalls();
            
    }
    
        /**
     *
     * @param customer
     * @param hall 
     * @param date reserving date
     * @return return true if reservation successful ,false if unsuccess
     */
    public boolean reserve(Customer customer,ReceptionHall hall,Date date){
        HashMap map=new HashMap();
        int hallID=hall.getId();
        String customerID=customer.getId();
        int insert;
        map.put(date,hallID);
        insert = DataBase.insert(dbTable,"Date",map);
        map.put(insert,customerID);
        DataBase.insert(dbTable,"CustomerID",map);
        return true;
    }
    
        /**
     *
     * @param customer
     * @param hall
     * @param from starting date of range
     * @param to ending date of range
     * @return return true if reservation successful ,false if unsuccess
     */
     public boolean reserve(Customer customer,ReceptionHall hall,Date from,Date to){
         Date reserveDate=from;
         while(reserveDate.before(to))
        {
            if(!(reserve(customer,hall,reserveDate)))
                return false;
            reserveDate.setTime(reserveDate.getTime()+(24*60*60*1000));
        }
         return true;
     }
         /**
     *
     * @param  date date which is need to check
     * @return arrayList of avaliable halls
     */
    public ArrayList<Integer> checkAvaliability(Date date){
        ArrayList<Integer> halls=new ArrayList<>();
        for(int i=1;i<=NoOfHalls;i++){
            halls.add(i);
        }
        ResultSet select;
        try {
            select= DataBase.select(dbTable, dbCols, "Date='" + date.toString() + "'");
            int[] array = (int[]) select.getArray("hall").getArray();
            for(int i=0;i<=array.length;i++){
                halls.remove(array[i]);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Reservation.class.getName()).log(Level.SEVERE, null, ex);
        }     
        return halls;
    }
        /**
     *
     * @param  from starting date of the range
     * @param  to ending date of the range 
     * @return arrayList of avaliable halls for all days in the range
     */
    public  ArrayList<Integer> checkAvaliability(Date from,Date to){
        Date checkDate=from;
        int[] hallArry=new int[NoOfHalls];
        ArrayList<Integer> halls=new ArrayList<>();
        int days=0;
        while(checkDate.before(to))
        {
            ArrayList<Integer> list = this.checkAvaliability(checkDate);
            for (Integer list1 : list) {
                hallArry[list1]++;
            }
            checkDate.setTime(checkDate.getTime()+(24*60*60*1000));
            days++;
        }
        for(int i=0;i<NoOfHalls;i++){
            if(hallArry[i]==days){
                halls.add(hallArry[i]);
            }
        }
        return halls;
    }

    
}