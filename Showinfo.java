/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootathon;
import bootathon.MoviesDatabase;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;
import java.util.*;
class Showinfo extends JFrame
{
        
        
 	JTabbedPane t;
 	Showinfo()
 	{
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  		t=new JTabbedPane(JTabbedPane.LEFT);
                String title1="DAY 1",title2="DAY 2",title3="DAY 3";
  		t.addTab(title1,new A(title1));
  		t.addTab(title2,new B(title2));
                t.addTab(title3,new C(title3));
               
  		add(t,BorderLayout.CENTER);
  		setBounds(400,100,600,600);
  		setVisible(true);
 	}
 	public static void main(String args[])
 	{
  		new Showinfo();
 	}  
        
class A extends JPanel implements ActionListener
{
        JComboBox movieNameDrop,screen;
        JButton insertButton,backButton;
        JTextField showField1,showField2,showField3;
        JLabel colon1,colon2,colon3,colon4,colon5,movieLabel,screenLab,show1,show2,show3;
        A(String day)
        {
            
            setLayout(null);
            System.out.print(day);
            movieNameDrop = new JComboBox();
            insertButton = new JButton("INSERT");
            movieLabel = new JLabel("SELECT MOVIE NAME");
            insertButton.setBounds(200,400,100,25);
            insertButton.setBackground(Color.green);
            insertButton.addActionListener(this);
            backButton = new JButton("BACK");
            backButton.setBackground(Color.cyan);
            backButton.setBounds(350,400,100,25);
            screen = new JComboBox();
            screen.addItem(1);
            screen.addItem(2);
            screen.addItem(3);
            screen.setBounds(300,150,200,30);
            screenLab = new JLabel("SCREEN");
            screenLab.setBounds(50,150,200,30);
            
            showField1 = new JTextField();
            showField2 = new JTextField();
            showField3 = new JTextField();
            
            showField1.setBounds(300,200,200,30);
            showField2.setBounds(300,250,200,30);
            showField3.setBounds(300,300,200,30);
            
            show1 = new JLabel("SHOW 1");
            show2 = new JLabel("SHOW 2");
            show3 = new JLabel("SHOW 3");
            show1.setBounds(50,200,200,30);
            show2.setBounds(50,250,200,30);
            show3.setBounds(50,300,200,30);
            
            colon1 = new JLabel(":");
            colon2 = new JLabel(":");
            colon3 = new JLabel(":");
            colon4 = new JLabel(":");
            colon5 = new JLabel(":");
            
            backButton.addActionListener(this);
            add(backButton);
            
            movieNameDrop.setBounds(300,100,200,30);
            movieLabel.setBounds(50,100,200,30);
            colon1.setBounds(225,100,100,30);
            colon2.setBounds(225,150,100,30);
            colon3.setBounds(225,200,100,30);
            colon4.setBounds(225,250,100,30);
            colon5.setBounds(225,300,100,30);
            
            add(insertButton);
            add(colon1);
            add(colon2);
            add(colon3);
            add(colon4);
            add(colon5);
            add(show1);
            add(show2);
            add(show3);
            add(showField1);
            add(showField2);
            add(showField3);
            add(screen);
            add(screenLab);
            add(movieLabel);
            add(movieNameDrop);
            
            try
            {
                movieNameDrop.addItem("Select");
                Connection connect = MoviesDatabase.getConnection();
                String query = "select Moviename from Movies";
                PreparedStatement prep = connect.prepareStatement(query);
                ResultSet result = prep.executeQuery();
                while(result.next())
                {
                    movieNameDrop.addItem(result.getString("Moviename"));
                }
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(this, e.toString());
            }
            
            setBounds(0, 0, 650, 650);
            setVisible(true);
         
 	}

        @Override
        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();
            if(source == insertButton)
            {
                try
                {
                    String nameMovie = movieNameDrop.getSelectedItem().toString();
                    int screenNo = Integer.parseInt(screen.getSelectedItem().toString());
                    String fshow = showField1.getText();
                    String sshow = showField2.getText();
                    String tshow = showField3.getText();
                    String Day = "1";
                    Connection conn = MoviesDatabase.getConnection();
                    Statement state = conn.createStatement();
                    String query = "insert into showInfoTable values('"+Day+"','"+nameMovie+"','"+screenNo+"','"+fshow+"','"+sshow+"','"+tshow+"')";
                    state.executeQuery(query);
                    conn.setAutoCommit(true);
                    JOptionPane.showMessageDialog(this, "SHOW ALTERED");
                }
                catch(Exception exc)
                {
                    
                }
            }
            if(source == backButton)
            {
                try
                {
                    new Admin();
                }
                catch(Exception exc)
                {
                    
                }
            }
        }
    }
class B extends JPanel implements ActionListener
{
        JComboBox movieNameDrop,screen;
        JButton insertButton,backButton;
        JTextField showField1,showField2,showField3;
        JLabel colon1,colon2,colon3,colon4,colon5,movieLabel,screenLab,show1,show2,show3;
        B(String day)
        {
            
            setLayout(null);
            System.out.print(day);
            movieNameDrop = new JComboBox();
            insertButton = new JButton("INSERT");
            movieLabel = new JLabel("SELECT MOVIE NAME");
            insertButton.setBounds(200,400,100,25);
            insertButton.setBackground(Color.green);
            
            screen = new JComboBox();
            screen.addItem(1);
            screen.addItem(2);
            screen.addItem(3);
            screen.setBounds(300,150,200,30);
            screenLab = new JLabel("SCREEN");
            screenLab.setBounds(50,150,200,30);
            
            showField1 = new JTextField();
            showField2 = new JTextField();
            showField3 = new JTextField();
            
            showField1.setBounds(300,200,200,30);
            showField2.setBounds(300,250,200,30);
            showField3.setBounds(300,300,200,30);
            
            
            backButton = new JButton("BACK");
            backButton.setBackground(Color.cyan);
            backButton.setBounds(350,400,100,25);
            backButton.addActionListener(this);
            add(backButton);
            
            show1 = new JLabel("SHOW 1");
            show2 = new JLabel("SHOW 2");
            show3 = new JLabel("SHOW 3");
            show1.setBounds(50,200,200,30);
            show2.setBounds(50,250,200,30);
            show3.setBounds(50,300,200,30);
            
            colon1 = new JLabel(":");
            colon2 = new JLabel(":");
            colon3 = new JLabel(":");
            colon4 = new JLabel(":");
            colon5 = new JLabel(":");
            
            
            
            movieNameDrop.setBounds(300,100,200,30);
            movieLabel.setBounds(50,100,200,30);
            colon1.setBounds(225,100,100,30);
            colon2.setBounds(225,150,100,30);
            colon3.setBounds(225,200,100,30);
            colon4.setBounds(225,250,100,30);
            colon5.setBounds(225,300,100,30);
            
            insertButton.addActionListener(this);
            
            add(insertButton);
            add(colon1);
            add(colon2);
            add(colon3);
            add(colon4);
            add(colon5);
            add(show1);
            add(show2);
            add(show3);
            add(showField1);
            add(showField2);
            add(showField3);
            add(screen);
            add(screenLab);
            add(movieLabel);
            add(movieNameDrop);
            
            try
            {
                movieNameDrop.addItem("Select");
                Connection connect = MoviesDatabase.getConnection();
                String query = "select Moviename from Movies";
                PreparedStatement prep = connect.prepareStatement(query);
                ResultSet result = prep.executeQuery();
                while(result.next())
                {
                    movieNameDrop.addItem(result.getString("Moviename"));
                }
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(this, e.toString());
            }
            
            setBounds(0, 0, 650, 650);
            setVisible(true);
         
 	}

        @Override
        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();
            if(source == insertButton)
            {
                try
                {
                    String nameMovie = movieNameDrop.getSelectedItem().toString();
                    int screenNo = Integer.parseInt(screen.getSelectedItem().toString());
                    String fshow = showField1.getText();
                    String sshow = showField2.getText();
                    String tshow = showField3.getText();
                    String Day = "2";
                    Connection conn = MoviesDatabase.getConnection();
                    Statement state = conn.createStatement();
                    String query = "insert into showInfoTable values('"+Day+"','"+nameMovie+"','"+screenNo+"','"+fshow+"','"+sshow+"','"+tshow+"')";
                    state.executeQuery(query);
                    conn.setAutoCommit(true);
                    JOptionPane.showMessageDialog(this, "SHOW ALTERED");
                }
                catch(Exception exc)
                {
                    
                }
            }
            if(source == backButton)
            {
                try
                {
                    new Admin();
                }
                catch(Exception exc)
                {
                    
                }
            }
        }
    }
class C extends JPanel implements ActionListener
{
        JComboBox movieNameDrop,screen;
        JButton insertButton,backButton;
        JTextField showField1,showField2,showField3;
        JLabel colon1,colon2,colon3,colon4,colon5,movieLabel,screenLab,show1,show2,show3;
        C(String day)
        {
            
            setLayout(null);
            System.out.print(day);
            movieNameDrop = new JComboBox();
            insertButton = new JButton("INSERT");
            movieLabel = new JLabel("SELECT MOVIE NAME");
            insertButton.setBounds(200,400,100,25);
            insertButton.setBackground(Color.green);
            
            screen = new JComboBox();
            screen.addItem(1);
            screen.addItem(2);
            screen.addItem(3);
            screen.setBounds(300,150,200,30);
            screenLab = new JLabel("SCREEN");
            screenLab.setBounds(50,150,200,30);
            
            
            backButton = new JButton("BACK");
            backButton.setBackground(Color.cyan);
            backButton.setBounds(350,400,100,25);
            backButton.addActionListener(this);
            add(backButton);
            
            insertButton.addActionListener(this);
            
            showField1 = new JTextField();
            showField2 = new JTextField();
            showField3 = new JTextField();
            
            showField1.setBounds(300,200,200,30);
            showField2.setBounds(300,250,200,30);
            showField3.setBounds(300,300,200,30);
            
            show1 = new JLabel("SHOW 1");
            show2 = new JLabel("SHOW 2");
            show3 = new JLabel("SHOW 3");
            show1.setBounds(50,200,200,30);
            show2.setBounds(50,250,200,30);
            show3.setBounds(50,300,200,30);
            
            colon1 = new JLabel(":");
            colon2 = new JLabel(":");
            colon3 = new JLabel(":");
            colon4 = new JLabel(":");
            colon5 = new JLabel(":");
            
            
            
            movieNameDrop.setBounds(300,100,200,30);
            movieLabel.setBounds(50,100,200,30);
            colon1.setBounds(225,100,100,30);
            colon2.setBounds(225,150,100,30);
            colon3.setBounds(225,200,100,30);
            colon4.setBounds(225,250,100,30);
            colon5.setBounds(225,300,100,30);
            
            add(insertButton);
            add(colon1);
            add(colon2);
            add(colon3);
            add(colon4);
            add(colon5);
            add(show1);
            add(show2);
            add(show3);
            add(showField1);
            add(showField2);
            add(showField3);
            add(screen);
            add(screenLab);
            add(movieLabel);
            add(movieNameDrop);
            
            try
            {
                movieNameDrop.addItem("Select");
                Connection connect = MoviesDatabase.getConnection();
                String query = "select Moviename from Movies";
                PreparedStatement prep = connect.prepareStatement(query);
                ResultSet result = prep.executeQuery();
                while(result.next())
                {
                    movieNameDrop.addItem(result.getString("Moviename"));
                }
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(this, e.toString());
            }
            
            setBounds(0, 0, 650, 650);
            setVisible(true);
         
 	}

        @Override
        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();
            if(source == insertButton)
            {
                try
                {
                    String nameMovie = movieNameDrop.getSelectedItem().toString();
                    int screenNo = Integer.parseInt(screen.getSelectedItem().toString());
                    String fshow = showField1.getText();
                    String sshow = showField2.getText();
                    String tshow = showField3.getText();
                    String Day = "3";
                    Connection conn = MoviesDatabase.getConnection();
                    Statement state = conn.createStatement();
                    String query = "insert into showInfoTable values('"+Day+"','"+nameMovie+"','"+screenNo+"','"+fshow+"','"+sshow+"','"+tshow+"')";
                    state.executeQuery(query);
                    conn.setAutoCommit(true);
                    JOptionPane.showMessageDialog(this, "SHOW ALTERED");
                }
                catch(Exception exc)
                {
                    
                }
            }
            if(source == backButton)
            {
                try
                {
                    new Admin();
                }
                catch(Exception exc)
                {
                    
                }
            }
        }
}