/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.sql.*;
import java.awt.event.*;
// import bootathon.Admin;
/**
 *
 * @author Abilash
 */
public class DeleteMovie extends JPanel implements ActionListener{
    Container cont1;
    JComboBox<String> movieNameDrop;
    JButton deleteButton,baButton;
    JLabel colon1,movieNameSelect;
    JPanel deletePanel,deleteButtonPanel;
    DeleteMovie()
    {
        Font adminFont = new Font(null).deriveFont(25.0f);
        //cont1.setLayout(null);
        setSize(950,950);
        setVisible(true);
        
        movieNameDrop = new JComboBox<String>();
        
        movieNameSelect = new JLabel("SELECT MOVIE NAME: ");
        movieNameSelect.setFont(adminFont);

        deleteButton = new JButton("DELETE");
        deleteButton.setBackground(Color.red);
        deleteButton.setFont(adminFont);

        
        //movieNameSelect.setBounds(100,150,200,30);

        deleteButton.addActionListener(this);
        //deleteButton.setBounds(150,275,100,30);
        //deleteButton.addActionListener(this);
        
        /*deleteButton.setBounds(190,295,100,30);
        baButton.setBounds(200,295,100,30);
        baButton.addActionListener(this);

        baButton.setBounds(320,295,100,30);
        movieNameDrop.setBounds(350,150,200,30);*/
        deletePanel = new JPanel(new BorderLayout());
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(750,750));
        setBorder(new EmptyBorder(50,10,50,10));

        movieNameDrop.addItem("SELECT");
        movieNameDrop.setFont(adminFont);

        deletePanel.add(movieNameSelect,BorderLayout.WEST);
        deletePanel.add(movieNameDrop,BorderLayout.CENTER);
        deletePanel.setPreferredSize(new Dimension(250,250));
        deletePanel.setBorder(new EmptyBorder(75,0,75,0));

        deleteButtonPanel = new JPanel();
        deleteButtonPanel.setLayout(new BorderLayout());
        deleteButtonPanel.add(deleteButton,BorderLayout.NORTH);
        deleteButtonPanel.setPreferredSize(new Dimension(150,150));
        deleteButtonPanel.setBorder(new EmptyBorder(0,250,0,250));
        add(deletePanel,BorderLayout.NORTH);
        add(deleteButtonPanel,BorderLayout.CENTER);
        try
        {
            Connection con = MoviesDatabase.getConnection();
            String query = "select moviename from movie";
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

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source==deleteButton)
        {
            try
            {
                String nameMovie = (String) movieNameDrop.getSelectedItem();
                Connection conn = MoviesDatabase.getConnection();
                String query = "delete from movie where moviename=?";
                
                String query2 = "delete from showInfo where moviename=?";
                PreparedStatement prep = conn.prepareStatement(query);
                PreparedStatement prep2 = conn.prepareStatement(query2);
                System.out.println("Connection made and choice obtained: "+nameMovie);
                prep.setString(1, nameMovie);
                prep.executeUpdate();
                System.out.println("Deleting movie");
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
    }
}
