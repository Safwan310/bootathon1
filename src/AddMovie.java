/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
//import bootathon.Admin;
import java.awt.event.*;
import java.sql.*;

/**
 *
 * @author Abilash
 */
public class AddMovie extends JPanel implements ActionListener{
    Container cont1;
    JTextField textName,textImage;
    JButton addButton;
    JComboBox<String> playingStatus;
   // JComboBox timingDrop;
    JLabel movieName,movieImage,movieStatus;
    JPanel imgPanel, namePanel, buttonPanel,statusPanel;
    AddMovie()
    {
        Font adminFont = new Font(null).deriveFont(25.0f);

        playingStatus = new JComboBox<>();
        playingStatus.setFont(adminFont);
        playingStatus.addItem("Yes");
        playingStatus.addItem("No");

        setLayout(new GridLayout(4,1));
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        
        textName = new JTextField();
        textName.setFont(adminFont);
        textImage = new JTextField();
        textImage.setFont(adminFont);
       
        movieName = new JLabel("MOVIE NAME: ");
        movieName.setFont(adminFont);

        movieImage = new JLabel("MOVIE IMAGE: ");
        movieImage.setFont(adminFont);

        movieStatus = new JLabel("Is the movie to be played immediately?");
        movieStatus.setFont(adminFont);

        addButton = new JButton("ADD");
        addButton.setFont(adminFont);
        addButton.setBackground(Color.green);

        addButton.addActionListener(this);

        namePanel = new JPanel(new GridLayout(1,2));
        namePanel.setBorder(new EmptyBorder(90,40,90,40));
        namePanel.add(movieName);
        namePanel.add(textName);

        imgPanel = new JPanel(new GridLayout(1,2));
        imgPanel.setBorder(new EmptyBorder(90,40,90,40));
        imgPanel.add(movieImage);
        imgPanel.add(textImage);

        statusPanel = new JPanel(new GridLayout(1,2));
        statusPanel.setBorder(new EmptyBorder(90,40,90,40));
        statusPanel.add(movieStatus);
        statusPanel.add(playingStatus);

        buttonPanel = new JPanel(new GridLayout(1,1));
        buttonPanel.setBorder(new EmptyBorder(90,40,90,40));
        buttonPanel.add(addButton);

        add(namePanel);
        add(imgPanel);
        add(statusPanel);
        add(buttonPanel);
        setSize(950,950);
        revalidate();
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
                String showStatus = "";
                String temp = (String) playingStatus.getSelectedItem();
                if(temp.equals("Yes")){
                    showStatus += "Y";
                }
                else if(temp.equals("No")){
                    showStatus += "N";
                }
                if(nameMovie.equals("")||imageMovie.equals("")||temp.equals("")){
                    JOptionPane.showMessageDialog(this,"Please enter all the fields");
                }

                else{
                Connection conn = MoviesDatabase.getConnection();
                String query = "insert into movie values(?,?,?)";
                PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setString(1,imageMovie);
                pstmt.setString(2,nameMovie);
                pstmt.setString(3,showStatus);
                pstmt.executeQuery();
                conn.setAutoCommit(true);
                JOptionPane.showMessageDialog(this, "Movie Added");}

            }
            catch(Exception exc)
            {
                JOptionPane.showMessageDialog(this,exc.toString());
            }
        }
    }
}
