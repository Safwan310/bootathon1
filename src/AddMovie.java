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
    JTextArea movDesc;
    JButton addButton,yesButton,noButton,changeButton;
   // JComboBox timingDrop;
    JLabel movieName,movieImage,movieStatus;
    JPanel imgPanel, namePanel, buttonPanel,statusPanel,choicePanel;
    String showStatus = "";
    AddMovie()
    {
        Font adminFont = new Font(null).deriveFont(25.0f);
        choicePanel = new JPanel();
        choicePanel.setLayout(new GridLayout(1,3));
        yesButton = new JButton("Yes");
        noButton = new JButton("No");
        changeButton = new JButton("Change");
        changeButton.setEnabled(false);
        choicePanel.add(yesButton);
        choicePanel.add(noButton);
        choicePanel.add(changeButton);

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
        statusPanel.setBorder(new EmptyBorder(30,40,30,40));
        statusPanel.add(movieStatus);
        statusPanel.add(choicePanel);

        buttonPanel = new JPanel(new GridLayout(1,1));
        buttonPanel.setBorder(new EmptyBorder(90,40,90,40));
        buttonPanel.add(addButton);

        yesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showStatus = "Y";
                yesButton.setEnabled(false);
                noButton.setEnabled(false);
                changeButton.setEnabled(true);
            }
        });

        noButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showStatus = "N";
                statusPanel.removeAll();
                statusPanel.repaint();
                statusPanel.setLayout(new GridLayout(2,2));
                statusPanel.add(movieStatus);
                statusPanel.add(choicePanel);
                yesButton.setEnabled(false);
                noButton.setEnabled(false);
                changeButton.setEnabled(true);
                JLabel desc = new JLabel("Add Description",JLabel.CENTER);
                desc.setFont(adminFont);
                movDesc = new JTextArea();
                statusPanel.add(desc);
                statusPanel.add(movDesc);
                statusPanel.revalidate();
            }
        });

        changeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showStatus = "";
                statusPanel.removeAll();
                statusPanel.repaint();
                statusPanel.setLayout(new GridLayout(1,2));
                statusPanel.add(movieStatus);
                statusPanel.add(choicePanel);
                yesButton.setEnabled(true);
                noButton.setEnabled(true);
                changeButton.setEnabled(false);
                statusPanel.revalidate();
            }
        });

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

                if(nameMovie.equals("")||imageMovie.equals("")||showStatus.equals("")){
                    JOptionPane.showMessageDialog(this,"Please enter all the fields");
                }

                else{
                Connection conn = MoviesDatabase.getConnection();
                String query = "insert into movie values(?,?,?,?)";
                PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setString(1,imageMovie);
                pstmt.setString(2,nameMovie);
                pstmt.setString(3,showStatus);
                pstmt.setString(4,movDesc.getText());
                pstmt.executeQuery();
                conn.setAutoCommit(true);
                JOptionPane.showMessageDialog(this, "Movie Added");}

            }
            catch(Exception exc)
            {
                JOptionPane.showMessageDialog(this,"Error at add movies: "+exc.toString());
            }
        }
    }
}
