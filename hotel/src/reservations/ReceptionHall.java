/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reservations;

import java.util.HashMap;
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
        this.name = name;
        HashMap map = new HashMap();
        map.put("Name", name);
        int ID = DataBase.insert("halls", "id", map);
        this.id = ID;
        return ID;
    }
}
