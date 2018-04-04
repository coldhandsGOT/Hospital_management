/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author nishan
 */
public class Database {
    
    private Connection con;
    private String url;
    private String username;
    private String password;
    
    
    public Database() {
        url = "jdbc:mysql://localhost:3306/hopital";
        username = "root";
        password = "root";
        
    }
    
    public Connection getConnection() {
        //loading driver
        try {
            Class.forName("com.mysql.jdbc");
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        
        try {
           con = DriverManager.getConnection(url, username, password); 
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        return con;
    }
    
}
