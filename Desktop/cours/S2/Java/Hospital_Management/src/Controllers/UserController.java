/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Database;
import Models.Doctor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author nishan
 */
public class UserController extends Doctor{
    
    Database db;
    Connection con;
    PreparedStatement pst;
    
    public UserController() {
        super();
        db = new Database();
        con = db.getConnection();
    }
    
 
    public boolean checkDoctor(Doctor d ) {
        
        String sql = "";
        ResultSet rs = null;
        
        try {
            sql = "SELECT * FROM DOCTEUR WHERE NUMERO = ? and SPECIALITE = ?";
            pst = con.prepareStatement(sql);
            
            pst.setString(1, d.getSpeciality());
           
            
            rs = pst.executeQuery();
            
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
            
            
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        
        
        return false;
    }
}
