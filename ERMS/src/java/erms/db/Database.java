/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package erms.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author eren
 */
public class Database {
    
    private Connection con;
    private String url;
    private String user;
    private String password;

    public Database() {
        
    }

    public Connection getCon() {
        url = "jdbc:mysql://localhost:3306/OOD";
        user = "root";
        password = "147852";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.con = DriverManager.getConnection(url, user, password);
            System.out.println("Database Baglandi...");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            System.out.println("Database Baglanilamadi...");
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }
    
    public void connectionClose(){
        try {
            con.close();
        } catch (SQLException ex) {
            System.out.println("Connection Bağlanılamadı...");
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
}
