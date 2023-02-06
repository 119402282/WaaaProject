/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author BEmerson
 */
public class DBManager {

    String strUrl = "jdbc:derby://localhost:1527/dorgan;create=true";
    private static Connection conn;
    private static final DBManager db = new DBManager();
    

    public DBManager() {
        
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            conn = DriverManager.getConnection(strUrl,"conor","password");
            System.out.println("Schema: "+conn.getSchema());

        } catch (ClassNotFoundException ex) {
            
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Connection getConnection() {
        return conn;
    }
}
