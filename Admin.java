/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootathon;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import bootathon.AddMovie;
import bootathon.DeleteMovie;
import bootathon.Showinfo;
public class Admin extends JFrame implements ActionListener,ItemListener{

    /**
     * @param args the command line arguments
     */
    Container cont,cont2;
    JButton addMovie,deleteMovie,showAlter;
    
    Admin()
    {
        cont = getContentPane();
        cont.setLayout(null);
        //cont.setBackground(Color.red);
        
        addMovie = new JButton("ADD MOVIE");
        deleteMovie = new JButton("DELETE MOVIE");
        showAlter = new JButton("SHOW DETAILS");
        addMovie.setBounds(200, 200, 200, 30);
        deleteMovie.setBounds(200,250,200,30);
        showAlter.setBounds(200,300,200,30);
        showAlter.setBackground(Color.cyan);
        showAlter.addActionListener(this);
        deleteMovie.setBackground(Color.red);
        addMovie.addActionListener(this);
        addMovie.setBackground(Color.green);
        deleteMovie.addActionListener(this);
        add(deleteMovie);
        add(addMovie);
        add(showAlter);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(400,100,600,600);
        setVisible(true);
        
    }
    public static void main(String[] args) {
        new Admin();
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();
            if(source==addMovie)
            {
                try
                {
                    new AddMovie();
                }
                catch(Exception excep)
                {
                    JOptionPane.showMessageDialog(this, excep.toString());
                }
            }
            if(source==deleteMovie)
            {
                try
                {
                    new DeleteMovie();
                }
                catch(Exception excep)
                {
                    JOptionPane.showMessageDialog(this, excep.toString());
                }
            }
            if(source==showAlter)
            {
                try
                {
                    new Showinfo();
                }
                catch(Exception excep)
                {
                    JOptionPane.showMessageDialog(this, excep.toString());
                }
            }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}