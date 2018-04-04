/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author nishan
 */
public class Doctor {
    
    private int id;
    private String speciality;
   
    
    public void setID(int id) {
        this.id = id;
    }
    
    public int getID() {
        return id;
    }
    
   
    
    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }
    
    public String getSpeciality() {
        return speciality;
    }
}
