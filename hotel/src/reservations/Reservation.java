/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package reservations;
import accounting.Ledger;
import elements.Customer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import utilities.DataBase;
import ui.MainWindow;
//import sun.util.calendar.LocalGregorianCalendar.Date;
/**
 *
 * @author aseladarshan
 */
public class Reservation {
    private int NoOfHalls;
    private ArrayList<String> dbCols=new ArrayList<>();
    private final String dbTable;
    public Reservation(){
        dbCols=new ArrayList<>();
        dbCols.add("Date");
        dbCols.add("hall");
        dbCols.add("ID");
        dbTable="reservations";
        ArrayList<String> list=new ArrayList<>();
        list.add("id");
        int[] arr;
        try {
            // arr = (int[]) DataBase.select("halls",list,"1=1").getArray("id").getArray();
            ResultSet rs = DataBase.select("halls",list,"1=1"); //.last();//getArray("id");//.getArray();
            rs.last();
            NoOfHalls=rs.getInt("id");
            //  NoOfHalls=arr.length;
        } catch (SQLException ex) {
            MainWindow.showError("Database Error!",ex.toString());
        }
        catch(NullPointerException ex){
           MainWindow.showError("Database Error!","Error while connecting to the database.");
        }
                
    }
    
        /**
     *
     * @param customer
     * @param hall 
     * @param date reserving date
     * @param payment true if full,false if advance
     * @param amount payment amount
     * @return return true if reservation successful ,false if unsuccess
     */
    public boolean reserve(Customer customer,ReceptionHall hall,Date date,boolean payment,double amount){
        HashMap map=new HashMap();
        int hallID=hall.getId();
        int customerID=customer.getId();
        map.put("Date",date);
        map.put("hall",hallID);
        map.put("customerID",customerID);
        DataBase.insert(dbTable,"ID",map);
        Ledger.addReservation(true,amount,customerID,customer.getName(),hall.getName());
        return true;
    }

         /**
     *
     * @param  date date which is need to check
     * @return arrayList of avaliable halls
     */
    public ArrayList<Integer> checkAvaliability(Date date){
        ArrayList<Integer> halls=new ArrayList<>();
         ArrayList<String> col=new ArrayList<>();
         col.add("id");
        ResultSet rs = DataBase.select("halls",col,"1=1");
        try {
            int i=0;
            while(rs.next()){
                halls.add(rs.getInt("id"));
            }
            ResultSet select;

            long d = ((Date)date).getTime();
                java.sql.Timestamp sqlDate = new java.sql.Timestamp(d);
            select= DataBase.select(dbTable, dbCols, "Date='" + sqlDate + "'");
            while(select.next()){
                halls.remove(halls.indexOf(select.getObject("hall")));
            }
            
        } catch (SQLException ex) {
            MainWindow.showError("DataBase Error!",ex.toString());
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
                hallArry[list1-1]++;
            }
            checkDate.setTime(checkDate.getTime()+(24*60*60*1000));
            
            days++;
        }
        for(int i=0;i<NoOfHalls;i++){
            if(hallArry[i]==days){
                halls.add(i+1);
            }
        }
        return halls;
    }

    
}
