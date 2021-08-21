/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootathon;

import java.awt.Color;
import java.awt.Container;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.sql.*;
import java.awt.event.*;
import javax.swing.JOptionPane;
import bootathon.Admin;
/**
 *
 * @author Abilash
 */
public class DeleteMovie extends JFrame implements ActionListener{
    Container cont1;
    JComboBox movieNameDrop;
    JButton deleteButton,baButton;
    JLabel colon1,movieNameSelect;
    DeleteMovie()
    {
        cont1 = getContentPane();
        cont1.setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(400,100,600,600);
        setVisible(true);
        
        movieNameDrop = new JComboBox();
        
        movieNameSelect = new JLabel("SELECT MOVIE NAME");
        colon1 = new JLabel(":");
        deleteButton = new JButton("DELETE");
        deleteButton.setBackground(Color.red);
        
        baButton = new JButton("BACK");
        baButton.setBackground(Color.cyan);
        colon1.setBounds(300,150,100,30);
        
        movieNameSelect.setBounds(100,150,200,30);
        //deleteButton.setBounds(150,275,100,30);
        //deleteButton.addActionListener(this);
        
        deleteButton.setBounds(190,295,100,30);
        baButton.setBounds(200,295,100,30);
        baButton.addActionListener(this);
        deleteButton.addActionListener(this);
        baButton.setBounds(320,295,100,30);
        movieNameDrop.setBounds(350,150,200,30);
        movieNameDrop.addItem("SELECT");
        add(movieNameDrop);
        add(movieNameSelect);
        add(colon1);
        add(baButton);
        add(deleteButton);
        
        
        try
        {
            Connection con = MoviesDatabase.getConnection();
            String query = "select Moviename from Movies";
            PreparedStatement prep = con.prepareStatement(query);
            ResultSet result = prep.executeQuery();
            while(result.next())
            {
                int len = result.getFetchSize();
                if(len>0){
                    movieNameDrop.addItem(result.getString("Moviename"));
                }
            }
            
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(this,ex.toString());
        }
        
    }
    public static void main(String[] args) {
        new DeleteMovie();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source==deleteButton)
        {
            try
            {
                String nameMovie = (String) movieNameDrop.getSelectedItem();
                Connection conn = MoviesDatabase.getConnection();
                String query = "delete from Movies where Moviename=?";
                
                String query2 = "delete from showInfoTable where Moviename=?";
                PreparedStatement prep = conn.prepareStatement(query);
                PreparedStatement prep2 = conn.prepareStatement(query2);
                //prep.setInt(1, id);
                
                prep.setString(1, nameMovie);
                prep.executeUpdate();
                prep2.setString(1, nameMovie);
                prep2.executeUpdate();
                conn.setAutoCommit(true);
                JOptionPane.showMessageDialog(this, "Movie Removed");
                movieNameDrop.removeItem(nameMovie);
            }
            catch(Exception excep)
            {
                JOptionPane.showMessageDialog(this,excep.toString());
            }
        }
        if(source==baButton)
        {
            new Admin();
        }
    }
}
