/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootathon;

import java.awt.*;
import javax.swing.*;
import bootathon.Admin;
import java.awt.event.*;
import java.sql.*;

/**
 *
 * @author Abilash
 */
public class AddMovie extends JFrame implements ActionListener{
    Container cont1;
    JTextField textName,textImage;
    JButton addButton,backButton;
   // JComboBox timingDrop;
    JLabel movieName,movieImage,colon1,colon2;
    AddMovie()
    {
        cont1 = getContentPane();
        cont1.setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(400,100,600,600);
        setVisible(true);
        
        textName = new JTextField();
        textImage = new JTextField();
       
        movieName = new JLabel("MOVIE NAME");
        movieImage = new JLabel("MOVIE IMAGE");
        colon1 = new JLabel(":");
        colon2 = new JLabel(":");
        addButton = new JButton("ADD");
        backButton = new JButton("BACK  ");
        addButton.setBackground(Color.green);
        backButton.setBackground(Color.cyan);
        textName.setBounds(250,150,200,30);
        textImage.setBounds(250,225,200,30);
        colon1.setBounds(200,150,100,30);
        colon2.setBounds(200,225,100,30);
        
        movieName.setBounds(100,150,100,30);
        movieImage.setBounds(100,225,100,30);
        
        addButton.setBounds(190,295,100,30);
        backButton.setBounds(310,295,100,30);
        backButton.addActionListener(this);
        addButton.addActionListener(this);
        add(backButton);
        add(movieImage);
        add(movieName);
        add(colon1);
        add(colon2);
        add(textName);
        add(textImage);
        add(addButton);
    }
    public static void main(String[] args) {
        new AddMovie();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source==addButton)
        {
            try
            {
                String nameMovie = textName.getText();
                String imageMovie = textImage.getText();
                if(nameMovie.length()>0 && imageMovie.length()>0){
                Connection conn = MoviesDatabase.getConnection();
                Statement state = conn.createStatement();
                String query = "insert into Movies values('"+nameMovie+"','"+imageMovie+"')";
                state.executeQuery(query);
                conn.setAutoCommit(true);
                JOptionPane.showMessageDialog(this, "Movie Added");}

            }
            catch(Exception exc)
            {
                JOptionPane.showMessageDialog(this,exc.toString());
            }
        }
        if(source==backButton)
        {
            new Admin();
        }
    }
}
