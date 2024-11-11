/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Anđela
 */
public class Konekcija {
    private static Konekcija konekcija;
    private Connection conn;


    private Konekcija() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pripremaps2024","root","Comtrade.123");
            conn.setAutoCommit(false);
        } catch (SQLException ex) {
            Logger.getLogger(Konekcija.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Connection getConn() {
        return conn;
    }

    public static Konekcija getKonekcija() {
        if(konekcija == null){
            konekcija = new Konekcija();
        }
        return konekcija;
    }
    
    
    
    
}
