import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

class DoLogin extends JPanel{
    JLabel emailLabel,passwordLabel,emptyLabel;
    JButton loginButton;
    JTextField emailField,passwordField;
    JPanel jp;
    DoLogin(String userType){
        Font appFont = new Font(null).deriveFont(20.0f);

        jp=new JPanel();
        add(jp);
        jp.setLayout(new GridLayout(3,2));
        setLayout(new GridLayout(1,1));

        jp.setBorder(new EmptyBorder(400,100,400,100));

        emailField=new JTextField("");
        emailField.setFont(appFont);
        passwordField=new JTextField("");
        passwordField.setFont(appFont);
        loginButton=new JButton("Login");
        loginButton.setFont(appFont);
        emailField.setFont(appFont);
        emailLabel=new JLabel("Email:"); emailLabel.setFont(appFont);
        passwordLabel=new JLabel("Password:");
        passwordLabel.setFont(appFont);
        emailField.setFont(appFont);


        jp.add(emailLabel);
        jp.add(emailField);

        jp.add(passwordLabel);
        jp.add(passwordField);

        emptyLabel = new JLabel(" ");
        jp.add(emptyLabel);
        jp.add(loginButton);

        setVisible(true);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(emailField.getText().isEmpty() || passwordField.getText().isEmpty()){
                    JOptionPane.showMessageDialog(jp,"Please enter all the fields");
                }
                else{
                    try{
                        String str_email=emailField.getText();
                        String str_password=passwordField.getText();
                        Connection con=MoviesDatabase.getConnection();
                        String query = "Select Username,Email,password,isadmin from reg where Email='"+str_email+"'";
                        Statement s=con.createStatement();
                        ResultSet rs=s.executeQuery(query);
                        if(!rs.next()){
                            JOptionPane.showMessageDialog(jp,"Seems like you dont have account please register");
                        }
                        else{

                            if(rs.getString("password").equals(str_password)){
                                String username = rs.getString("username");
                                if(DoReg.userTypeBox.getSelectedItem().equals("Admin")){
                                    if(rs.getString("isadmin").equals("Yes")){
                                        JOptionPane.showMessageDialog(jp,"Admin login successful");
                                        DoReg.mother.removeAll();
                                        DoReg.mother.repaint();
                                        DoReg.mother.add(new Admin());
                                        DoReg.mother.revalidate();
                                        //AdminPage code here
                                        //setVisible to false then put Classname.main(null) include package name in all java
                                    }
                                    else{
                                        JOptionPane.showMessageDialog(jp,"You are not an admin. Please use the customer login page");
                                    }
                                }
                                else{
                                    JOptionPane.showMessageDialog(jp,"Welcome! User");
                                    DoReg.mother.removeAll();
                                    DoReg.mother.repaint();
                                    DoReg.mother.add(new homepage(username));
                                    DoReg.mother.revalidate();
                                    //HomePage code here
                                    //setVisible to false then put Classname.main(null) include package name in all java
                                }
                            }
                            else{
                               JOptionPane.showMessageDialog(jp,"Please enter correct password");
                            }
                        }
                    }
                    catch(Exception e1){
                        System.out.println("Error at Login: "+e1);
                    } }
            }
        });
    }
}