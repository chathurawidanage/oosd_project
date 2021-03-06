/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reservations;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import ui.MainWindow;
import utilities.DataBase;

/**
 *
 * @author aseladarshan
 */
public class ReceptionHall {

    private String name;
    private int id;

    public ReceptionHall(String name) {
        this.name = name;
    }
    
    public ReceptionHall(int id) {
        this.id = id;
        //call db and
        System.out.println("recHllCnstctr: "+id);
        ResultSet rs = DataBase.selectAll("halls","id='" + id + "'");
        try {
            rs.next();
            name=rs.getString("name");
        } catch (SQLException ex) {
            MainWindow.showError("DataBase Error!",ex.toString());
        }
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    public int save() {      
        HashMap map = new HashMap();
        map.put("Name", name);
        int ID = DataBase.insert("halls", "id", map);
        this.id = ID;
        return ID;
    }
}
