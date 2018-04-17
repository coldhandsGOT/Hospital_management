/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author idris
 */
public class Main_window extends javax.swing.JFrame {
      
   
private String DB ="hopital";
 private String serverAddress="jdbc:mysql://localhost/";
    

    public Main_window() {
      
        initComponents();
        getConnection();
       //JOptionPane.showMessageDialog(null, "Connected to Database: " +DB);
        ShowProductSwing();
    }
    
     // Coonection to DB
   public Connection getConnection()
    {
        Connection con = null;
        
        try {
            
            con = DriverManager.getConnection(""+serverAddress+ DB+"","root", "root");
            
            return con;
        } 
        
        catch (SQLException ex) {
            Logger.getLogger(Main_window.class.getName()).log(Level.SEVERE, null, ex);
                   //JOptionPane.showMessageDialog(null, "Failed to connect to DB");
                   return con;
        }
    }
       
    
    
    
        
 

       
    
    // Check input fields
    //txt_Speciality
     public boolean checkInputs()
     {
    return !(txt_Name.getText() == null || txt_Lastname.getText() == null || txt_Speciality.getText() == null); 
     }
     
    
    // display data in Jtable

    // 1 - remplir ArrayList avec les données
     
     public ArrayList <Docteur> getDocteurList()
     {        
        ArrayList<Docteur> docteurList = null;
                
             docteurList = new ArrayList<Docteur>();
        Connection con = getConnection();
        String query = "SELECT DISTINCT e.numero, e.nom, e.prenom, d.specialite \n" +
                        "FROM employe e\n" +
                        "\n" +
                        "JOIN docteur d \n" +
                        "ON e.numero = d.numero \n" +
                        "\n" +
                        "JOIN soigne s \n" +
                        "ON e.numero = s.no_docteur \n" +
                        "\n" +
                        "JOIN malade m \n" +
                        "ON m.numero = s.no_malade \n" +
                        "\n" +
                        "JOIN hospitalisation h \n" +
                        "ON h.no_malade = m.numero \n" +
                        "\n" +
                        "ORDER BY e.numero";
            
        Statement st;
        ResultSet rs;
    
        try 
        { 
            st= con.createStatement();
            rs = st.executeQuery(query);
            Docteur docteur;
            
            while(rs.next())
            {
                docteur = new Docteur(rs.getInt("e.numero"), rs.getString("e.nom"),rs.getString("e.prenom"),rs.getString("d.specialite"));
                docteurList.add(docteur);           
            }           
        }   
        catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Can't display the requested view");
        }
        
            return docteurList;
     }
     
     
     // 2 - remplire la JTable avec le arrayList
    
    public void ShowProductSwing()
     {        
       

        ArrayList<Docteur> list = getDocteurList();
        DefaultTableModel model = (DefaultTableModel) JTable_Products.getModel();
    
        //the following two lines of code refreshes the JTable by deleting all rows
        //then, replaced by new rows after insert/delete/update
        
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged(); 
        
        Object[] row = new Object[4];
        
        for(int i=0; i<list.size(); i++)
        {
            row[0]=list.get(i).getId();
            row[1]=list.get(i).getName();
            row[2]=list.get(i).getLastname();
            row[3]=list.get(i).getSpeciality();
            
            model.addRow(row);
             
        }   
        
      
     }
    
    //afficher les elements de la JTable sur l'interface
    public void ShowItem(int index)
    {       
        txt_Id.setText(Integer.toString(getDocteurList().get(index).getId()));
        txt_Name.setText(getDocteurList().get(index).getName());
        txt_Lastname.setText(getDocteurList().get(index).getLastname());
        txt_Speciality.setText(getDocteurList().get(index).getSpeciality());
        
    }
    
    
    
    
    
    
     String imagePath = null;
     int pos =0;

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();
        jPanel1 = new javax.swing.JPanel();
        txt_Name = new javax.swing.JTextField();
        txt_Id = new javax.swing.JTextField();
        txt_Lastname = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        JTable_Products = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        Btn_Insert = new javax.swing.JButton();
        Btn_Delete = new javax.swing.JButton();
        Btn_Update = new javax.swing.JButton();
        Btn_Previous = new javax.swing.JButton();
        Btn_Next = new javax.swing.JButton();
        Btn_Last = new javax.swing.JButton();
        Btn_First = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txt_Speciality = new javax.swing.JTextField();

        jScrollPane1.setViewportView(jTree1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));

        txt_Name.setBackground(new java.awt.Color(255, 255, 255));
        txt_Name.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txt_Name.setPreferredSize(new java.awt.Dimension(55, 50));

        txt_Id.setBackground(new java.awt.Color(255, 255, 255));
        txt_Id.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txt_Id.setPreferredSize(new java.awt.Dimension(55, 50));
        txt_Id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_IdActionPerformed(evt);
            }
        });

        txt_Lastname.setBackground(new java.awt.Color(255, 255, 255));
        txt_Lastname.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txt_Lastname.setPreferredSize(new java.awt.Dimension(55, 50));

        JTable_Products.setBackground(new java.awt.Color(255, 255, 255));
        JTable_Products.setForeground(new java.awt.Color(0, 0, 0));
        JTable_Products.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No_Doctor", "Name", "Lastname", "Speciality"
            }
        ));
        JTable_Products.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTable_ProductsMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(JTable_Products);

        jPanel2.setBackground(new java.awt.Color(0, 204, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 136, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(0, 204, 255));

        Btn_Insert.setBackground(new java.awt.Color(51, 51, 51));
        Btn_Insert.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        Btn_Insert.setForeground(new java.awt.Color(51, 204, 255));
        Btn_Insert.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/add.png"))); // NOI18N
        Btn_Insert.setText("Insert");
        Btn_Insert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_InsertActionPerformed(evt);
            }
        });

        Btn_Delete.setBackground(new java.awt.Color(51, 51, 51));
        Btn_Delete.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        Btn_Delete.setForeground(new java.awt.Color(51, 204, 255));
        Btn_Delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/delete.png"))); // NOI18N
        Btn_Delete.setText("Delete");
        Btn_Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_DeleteActionPerformed(evt);
            }
        });

        Btn_Update.setBackground(new java.awt.Color(51, 51, 51));
        Btn_Update.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        Btn_Update.setForeground(new java.awt.Color(51, 204, 255));
        Btn_Update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/update.png"))); // NOI18N
        Btn_Update.setText("Update");
        Btn_Update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_UpdateActionPerformed(evt);
            }
        });

        Btn_Previous.setBackground(new java.awt.Color(255, 255, 255));
        Btn_Previous.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        Btn_Previous.setForeground(new java.awt.Color(0, 51, 51));
        Btn_Previous.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/previous.png"))); // NOI18N
        Btn_Previous.setText("Previous");
        Btn_Previous.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_PreviousActionPerformed(evt);
            }
        });

        Btn_Next.setBackground(new java.awt.Color(255, 255, 255));
        Btn_Next.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        Btn_Next.setForeground(new java.awt.Color(0, 51, 51));
        Btn_Next.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/next.png"))); // NOI18N
        Btn_Next.setText("    Next");
        Btn_Next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_NextActionPerformed(evt);
            }
        });

        Btn_Last.setBackground(new java.awt.Color(255, 255, 255));
        Btn_Last.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        Btn_Last.setForeground(new java.awt.Color(0, 51, 51));
        Btn_Last.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/last.png"))); // NOI18N
        Btn_Last.setText("Last");
        Btn_Last.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_LastActionPerformed(evt);
            }
        });

        Btn_First.setBackground(new java.awt.Color(255, 255, 255));
        Btn_First.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        Btn_First.setForeground(new java.awt.Color(0, 51, 51));
        Btn_First.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/first.png"))); // NOI18N
        Btn_First.setText("First");
        Btn_First.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_FirstActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(112, 112, 112)
                .addComponent(Btn_Insert, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(Btn_Update)
                .addGap(14, 14, 14)
                .addComponent(Btn_Delete)
                .addGap(181, 181, 181)
                .addComponent(Btn_First, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Btn_Last, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(164, 164, 164))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Btn_Previous)
                .addGap(18, 18, 18)
                .addComponent(Btn_Next, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(270, 270, 270))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Btn_Insert)
                    .addComponent(Btn_Update)
                    .addComponent(Btn_Delete)
                    .addComponent(Btn_First)
                    .addComponent(Btn_Last))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Btn_Next)
                    .addComponent(Btn_Previous))
                .addContainerGap(55, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(0, 51, 51));

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel2.setText("No_Doctor:");

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setText("Name:");

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel3.setText("Lastname:");

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel4.setText("Speciality:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(165, 165, 165)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        txt_Speciality.setBackground(new java.awt.Color(255, 255, 255));
        txt_Speciality.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txt_Speciality.setPreferredSize(new java.awt.Dimension(55, 50));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_Name, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_Id, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_Lastname, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_Speciality, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 176, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 779, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(txt_Id, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_Name, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_Lastname, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_Speciality, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, 0)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents



    private void Btn_UpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_UpdateActionPerformed
            String updateQuery = null;
            PreparedStatement ps = null;
            Connection con = getConnection();
            
            
        if(checkInputs() && txt_Id.getText() != null)
        {
            
            
            //update without image
           /* if(imagePath ==null)
             //   {
                try 
                    {
                      
                    updateQuery = "UPDATE products SET name = ?, price = ?, add_date= ? , WHERE id = ?";
                    ps = con.prepareStatement(updateQuery);

                    ps.setString(1,txt_Name.getText());
                    ps.setString(2,txt_Price.getText());

                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String addDate = dateFormat.format(txt_Date.getDate());
                    
                    ps.setString(3,addDate);
                    
                    ps.setInt(4, Integer.parseInt(txt_Id.getText()));
                    
                    ps.executeUpdate();
                    
                    JOptionPane.showMessageDialog(null, "File updated");
                    } 
                catch (SQLException ex) 
                    {
                        JOptionPane.showMessageDialog(null, "File notupdated");
                    }    
              //  }
            */
            
            // update with image
         //   else
            //    {
                try 
                {
                    InputStream img = new FileInputStream(new File(imagePath));               
                    updateQuery = "UPDATE products SET name = ?, price = ?, add_date= ?, image= ? WHERE id = ?";
                    
                    ps = con.prepareStatement(updateQuery);

                    ps.setString(1,txt_Name.getText());
                    ps.setString(2,txt_Lastname.getText());
                   
                    ps.setString(3,txt_Speciality.getText());
                                      
                    ps.setInt(5, Integer.parseInt(txt_Id.getText()));
                    
                    ps.executeUpdate();
                    ShowProductSwing();
                    JOptionPane.showMessageDialog(null, "File updated");
                    
                
                
                }              
                catch (Exception ex) 
                {
                 JOptionPane.showMessageDialog(null, "A Field Is Missing ");
                }

                    
                    

            //    }
            
        }
        
    }//GEN-LAST:event_Btn_UpdateActionPerformed

    private void Btn_InsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_InsertActionPerformed
       
        if(checkInputs() && imagePath !=null)
        {
            try{
                Connection con = getConnection();
                PreparedStatement ps = con.prepareStatement("INSERT INTO products (name, price, add_date, image)" +"values(?,?,?,?)" );
           
                ps.setString(1, txt_Name.getText());
                ps.setString(2, txt_Lastname.getText());
             
                ps.setString(3, txt_Speciality.getText());
                
                ps.executeUpdate();
                ShowProductSwing();
                JOptionPane.showMessageDialog(null, "Data inserted Successfully");
                  
            }
           catch(SQLException ex)
           {
           JOptionPane.showMessageDialog(null, "Can't Insert New Data");
           }
            
        }
        else
        {
            JOptionPane.showMessageDialog(null, "One Or More Fields Are Empty");
        }
        
        System.out.println("Name =>"+txt_Name.getText());
        System.out.println("Lasname =>"+txt_Lastname.getText());
        System.out.println("Speciality =>"+txt_Speciality.getText());      
    }//GEN-LAST:event_Btn_InsertActionPerformed

    
    //delete
    private void Btn_DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_DeleteActionPerformed
       
        if(!txt_Id.getText().equals(""))
        {
            try
            {
               Connection con = getConnection();

               PreparedStatement ps = con.prepareStatement("DELETE FROM products where id = ?");

               int id = Integer.parseInt(txt_Id.getText());

               ps.setInt(1, id);
               ps.executeUpdate();
               
               ShowProductSwing();
               JOptionPane.showMessageDialog(null, "Entry Deleted");
           }
            catch(Exception e)
            {
               JOptionPane.showMessageDialog(null, "Can't delete entry");  
            }
        
            
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Please Enter proper ID to delete Entry");
        }
        
        
    }//GEN-LAST:event_Btn_DeleteActionPerformed

    private void Btn_FirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_FirstActionPerformed
        pos = 0;
        ShowItem(pos);
        System.out.println(pos);
    }//GEN-LAST:event_Btn_FirstActionPerformed

    private void Btn_PreviousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_PreviousActionPerformed
        try{
        pos--;
        ShowItem(pos);
        System.out.println(pos);
        }
        catch(Exception e)     
        {
            pos=0;
            System.out.println(pos);
        }
    }//GEN-LAST:event_Btn_PreviousActionPerformed

    private void Btn_NextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_NextActionPerformed
        try{
           pos++;
           ShowItem(pos);
           System.out.println(pos);
        }
        
        catch(Exception e)  
        {
            pos=getDocteurList().size()-1;
            System.out.println(pos);
        }
    }//GEN-LAST:event_Btn_NextActionPerformed

    private void Btn_LastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_LastActionPerformed
         pos = getDocteurList().size()-1;
        ShowItem(pos);
        System.out.println(pos);
    }//GEN-LAST:event_Btn_LastActionPerformed

    private void txt_IdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_IdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_IdActionPerformed

    private void JTable_ProductsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTable_ProductsMouseClicked
            int index = JTable_Products.getSelectedRow();
            ShowItem(index);
    }//GEN-LAST:event_JTable_ProductsMouseClicked

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Btn_Delete;
    private javax.swing.JButton Btn_First;
    private javax.swing.JButton Btn_Insert;
    private javax.swing.JButton Btn_Last;
    private javax.swing.JButton Btn_Next;
    private javax.swing.JButton Btn_Previous;
    private javax.swing.JButton Btn_Update;
    private javax.swing.JTable JTable_Products;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTree jTree1;
    private javax.swing.JTextField txt_Id;
    private javax.swing.JTextField txt_Lastname;
    private javax.swing.JTextField txt_Name;
    private javax.swing.JTextField txt_Speciality;
    // End of variables declaration//GEN-END:variables
}
