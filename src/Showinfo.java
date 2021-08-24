/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//import bootathon.MoviesDatabase;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;
import java.util.*;
class Showinfo extends JPanel
{
    Font adminFont = new Font(null).deriveFont(25.0f);

 	JTabbedPane t;
 	Showinfo()
    {
  		t=new JTabbedPane(JTabbedPane.LEFT);
  		t.setFont(adminFont);
  		String title1="DAY 1",title2="DAY 2",title3="DAY 3";
  		t.addTab(title1,new A("1"));
  		t.addTab(title2,new A("2"));
  		t.addTab(title3,new A("3"));

  		setLayout(new GridLayout(1,1));
  		add(t);
  		setSize(950,950);
  		setVisible(true);
 	}
        
class A extends JPanel implements ActionListener
{
        String day = "";
        JPanel showPanel;
        JComboBox<String> movieNameDrop,screen;
        JButton insertButton;
        JComboBox<String> showField1,showField2,showField3;
        JLabel movieLabel,screenLab,show1,show2,show3;

        A(String dayInfo)
        {
            day = dayInfo;
            setLayout(new GridLayout(4,2));

            System.out.print(day);

            movieNameDrop = new JComboBox();
            movieNameDrop.setFont(adminFont);

            insertButton = new JButton("INSERT");
            insertButton.setFont(adminFont);

            movieLabel = new JLabel("SELECT MOVIE NAME (FOR DAY: "+day+")");
            movieLabel.setFont(adminFont);

            insertButton.setBackground(Color.green);
            insertButton.addActionListener(this);

            screen = new JComboBox<String>();
            screen.setFont(adminFont);
            screen.addItem("1");
            screen.addItem("2");
            screen.addItem("3");

            screenLab = new JLabel("SCREEN");
            screenLab.setFont(adminFont);

            show1 = new JLabel("PLAY DURING SHOW 1");
            show1.setFont(adminFont);
            show2 = new JLabel("PLAY DURING SHOW 2");
            show2.setFont(adminFont);
            show3 = new JLabel("PLAY DURING SHOW 3");
            show3.setFont(adminFont);

            showPanel = new JPanel(new GridLayout(3,2));
            infoFetcher();

            screen.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    if(e.getStateChange() == ItemEvent.SELECTED){
                        JOptionPane.showMessageDialog(t,"Screen changed");
                        showPanel.removeAll();
                        showPanel.repaint();
                        System.out.println("Elements removed");
                        infoFetcher();
                        showPanel.add(show1);
                        showPanel.add(showField1);

                        showPanel.add(show2);
                        showPanel.add(showField2);

                        showPanel.add(show3);
                        showPanel.add(showField3);

                        System.out.println("Elements added");
                        showPanel.revalidate();

                    }
                }
            });

            add(movieLabel);
            add(movieNameDrop);

            add(screenLab);
            add(screen);

            showPanel.add(show1);
            showPanel.add(showField1);

            showPanel.add(show2);
            showPanel.add(showField2);

            showPanel.add(show3);
            showPanel.add(showField3);

            add(showPanel);

            add(insertButton);
            
            try
            {
                Connection connect = MoviesDatabase.getConnection();
                String query = "select moviename from movie";
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
            
            setSize(950, 950);
            setVisible(true);
         
 	}

        @Override
        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();
            if(source == insertButton)
            {

                System.out.println("Elements added");
                showPanel.revalidate();
                try
                {
                    String nameMovie = movieNameDrop.getSelectedItem().toString();
                    int screenNo = Integer.parseInt(screen.getSelectedItem().toString());
                    String fshow = (String) showField1.getSelectedItem();
                    String sshow = (String) showField2.getSelectedItem();
                    String tshow = (String) showField3.getSelectedItem();

                    //System.out.println(t.getSelectedComponent());

                    String Day = day;
                    Connection conn = MoviesDatabase.getConnection();
                    String query = "insert into showinfo values(?,?,?,?,?,?)";
                    PreparedStatement pstmt = conn.prepareStatement(query);

                    pstmt.setString(1,Day);
                    pstmt.setString(2, String.valueOf(screenNo));
                    pstmt.setString(3,nameMovie);
                    pstmt.setString(4,fshow);
                    pstmt.setString(5,sshow);
                    pstmt.setString(6,tshow);

                    pstmt.executeQuery();

                    conn.setAutoCommit(true);
                    JOptionPane.showMessageDialog(this, "SHOW ALTERED");
                }
                catch(Exception exc)
                {
                    System.out.println("Error at showinfo insertion: "+exc);
                }
            }
        }
    public void infoFetcher(){
        showField1 = new JComboBox<String>();
        showField1.setFont(adminFont);
        showField1.addItem("No");
        showField1.addItem("YES");


        showField2 = new JComboBox<String>();
        showField2.setFont(adminFont);
        showField2.addItem("No");
        showField2.addItem("YES");


        showField3 = new JComboBox<String>();
        showField3.setFont(adminFont);
        showField3.addItem("No");
        showField3.addItem("YES");


        Connection conn = MoviesDatabase.getConnection();

        String query = "select * from showinfo where dayinfo = ? and screeninfo = ?";

        PreparedStatement pstmt = null;

        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1,day);
            pstmt.setString(2, (String) screen.getSelectedItem());

            System.out.println(day+"=="+(String) screen.getSelectedItem());

            ResultSet rst = pstmt.executeQuery();
            //System.out.println(rst.getFetchSize());

            while(rst.next()){
                if(rst.getString("timing1").equals("YES")){
                    showField1.setEnabled(false);
                }
                /*else{
                    showField1.setEnabled(true);
                }*/
                System.out.println(rst.getString("timing1"));
                if(rst.getString("timing2").equals("YES")){
                    showField2.setEnabled(false);
                }
                /*else{
                    showField2.setEnabled(true);
                }*/
                System.out.println(rst.getString("timing2"));
                if(rst.getString("timing3").equals("YES")){
                    showField3.setEnabled(false);
                }
                /*else{
                    showField3.setEnabled(true);
                }*/
                System.out.println(rst.getString("timing3"));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    }
}