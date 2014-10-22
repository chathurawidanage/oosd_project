package utilities;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

class Connect {

    private Connect() {
        a = Conn();
    }
    static Connect c = new Connect();

    public static Connect getInstance() {
        return c;
    }
    Connection a;
    private String url;
    private String driver;
    private String uname;
    private String pword;
    private String dbname;

    private Connection Conn() {
        a = null;
        url = "jdbc:mysql://localhost:3306/";
        driver = "com.mysql.jdbc.Driver";
        uname = "root";
        pword = "";
        dbname = "oosd_hotel";
        try {
            Class.forName(driver).newInstance();
            a = DriverManager.getConnection(url + dbname, uname, pword);
            System.out.println("Connected");

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            //  JOptionPane.showMessageDialog(null, ex);
        }
        return a;
    }

    public ResultSet getQuery(String query) {
        ResultSet r = null;
        try {
            //  a = Conn();
            Statement s = a.createStatement();    //this was a commented line before
            //  Statement s = Conn().createStatement();    //this was the code before
            r = s.executeQuery(query);

        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);

            //  JOptionPane.showMessageDialog(null, " not found");
        }
        return r;
    }

    public boolean setQuery(String query) {

        try {
            //  a = Conn();
            Statement s = a.createStatement();
            // Statement s = Conn().createStatement();
            s.execute(query);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
            return false;
        }

    }

    public int setQuery(String query, String column_name) {
        int i = 0;
        try {
            //  a = Conn();
            Statement stmt = a.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            //  Statement stmt = Conn().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
// ...
            stmt.execute(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet res = stmt.getGeneratedKeys();
            while (res.next()) {
                System.out.println("Generated key: " + res.getInt(1));
                i = res.getInt(1);

            }

            return i;

        } catch (SQLException ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);

            return i;
        }

    }

    /*
     * public static void main(String[] args) { Connect cc = new Connect();
     * cc.Conn(); } *
     */
}
