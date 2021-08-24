/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.sql.*;
//import bootathon.AddMovie;
//import bootathon.DeleteMovie;
//import bootathon.Showinfo;
public class Admin extends JFrame implements ActionListener,ItemListener{

    /**
     *8 @param args the command line arguments
     */
    Container cont,cont2;
    JButton addMovie,deleteMovie,showAlter;
    JPanel nav, buttons;
    JPanel slot;
    CardLayout cardLayout = new CardLayout();
    Admin()
    {
        Font adminFont = new Font(null).deriveFont(25.0f);

        setLayout(new BorderLayout());

        nav = new JPanel();
        nav.setLayout(new BorderLayout());

        JButton backButton = new JButton("Back");
        backButton.setFont(adminFont);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(slot,"Buttons");
            }
        });

        JLabel txt1=new JLabel("SK Cinemas Administration",JLabel.CENTER);
        txt1.setFont(adminFont);
        //txt1.setFont(bookingFont);
        txt1.setForeground(Color.YELLOW);


        JButton profileButton = new JButton("Profile");
        profileButton.setFont(adminFont);
        profileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        nav.add(backButton,BorderLayout.WEST);
        nav.add(txt1,BorderLayout.CENTER);
        nav.add(profileButton,BorderLayout.EAST);
        nav.setPreferredSize(new Dimension(50,50));

        nav.setBackground(Color.BLACK);
        nav.setVisible(true);
        //cont.setBackground(Color.red);

        slot = new JPanel();
        buttons = new JPanel(new GridLayout(3,1));
        buttons.setBorder(new EmptyBorder(50,50,50,50));

        slot.setLayout(cardLayout);
        AddMovie addMoviePanel = new AddMovie();
        DeleteMovie deleteMoviePanel = new DeleteMovie();
        Showinfo showinfo = new Showinfo();

        addMovie = new JButton("ADD MOVIE");
        addMovie.setFont(adminFont);
        deleteMovie = new JButton("DELETE MOVIE");
        deleteMovie.setFont(adminFont);
        showAlter = new JButton("UPDATE SHOW TIMING");
        showAlter.setFont(adminFont);

        showAlter.setBackground(Color.cyan);
        showAlter.addActionListener(this);
        showAlter.setBorder(new EmptyBorder(20,20,20,20));

        deleteMovie.setBackground(Color.red);
        addMovie.addActionListener(this);
        deleteMovie.setBorder(new EmptyBorder(20,20,20,20));

        addMovie.setBackground(Color.green);
        deleteMovie.addActionListener(this);
        addMovie.setBorder(new EmptyBorder(20,20,20,20));

        add(nav,BorderLayout.NORTH);
        buttons.add(addMovie);
        buttons.add(deleteMovie);
        buttons.add(showAlter);
        slot.add(buttons,"Buttons");
        slot.add(addMoviePanel,"Add Movie");
        slot.add(deleteMoviePanel,"Delete Movie");
        slot.add(showinfo,"Update Timing");
        add(slot,BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(950,950);
        setVisible(true);
        
    }
    public static void main(String[] args) {
        new Admin();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();
            if(source==addMovie)
            {cardLayout.show(slot,"Add Movie"); }
            if(source==deleteMovie)
            { cardLayout.show(slot,"Delete Movie");}
            if(source==showAlter)
            { cardLayout.show(slot,"Update Timing");}
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}