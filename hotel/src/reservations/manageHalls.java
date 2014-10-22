/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package reservations;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import utilities.DataBase;

/**
 *
 * @author aseladarshan
 */
public final class manageHalls {
    private static int NoOfHalls=0;
    private static ReceptionHall[] receptionHall;


    public manageHalls(){
        ArrayList<String> dbHallsCols = new ArrayList<>();
        dbHallsCols.add("Name");
        dbHallsCols.add("id");
         String[] names = null;
        int[] id = null;
        ResultSet rs = DataBase.select("halls",dbHallsCols,"1=1");
        try {
            names = (String[]) rs.getArray("Name").getArray();
            id=(int[]) rs.getArray("id").getArray();
        } catch (SQLException ex) {
            Logger.getLogger(Reservation.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for (String name : names) {
            ReceptionHall tmp=new ReceptionHall(names[getNoOfHalls()],id[getNoOfHalls()]);
            this.addReceptionHall(tmp);
            NoOfHalls++;
        }
    }
    
    /**
     * @return the receptionHall
     */
    public static ReceptionHall[] getReceptionHall() {
        return receptionHall;
    }

    /**
     * @param receptionHall the receptionHall to set
     */
    public void addReceptionHall(ReceptionHall receptionHall) {
        manageHalls.receptionHall[NoOfHalls++] = receptionHall;
    }
    
        /**
     * @return the NoOfHalls
     */
    public static int getNoOfHalls() {
        return NoOfHalls;
    }

    /**
     * @param aNoOfHalls the NoOfHalls to set
     */
    public static void setNoOfHalls(int aNoOfHalls) {
        NoOfHalls = aNoOfHalls;
    }
    
}