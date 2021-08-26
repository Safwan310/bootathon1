import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.TextEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.TextAction;
import java.awt.event.TextListener;

import static javax.swing.JFrame.EXIT_ON_CLOSE;
@FunctionalInterface
interface SimpleDocumentListener extends DocumentListener {
    void update(DocumentEvent e);

    @Override
    default void insertUpdate(DocumentEvent e) {
        update(e);
    }
    @Override
    default void removeUpdate(DocumentEvent e) {
        update(e);
    }
    @Override
    default void changedUpdate(DocumentEvent e) {
        update(e);
    }
}
class editProfile extends JFrame{
    JLabel userLabel,fullNameLabel,emailLabel,phoneLabel,passwordLabel,emptyLabel;
    JButton editButton,saveButton;
    JTextField userNameField,fullNameField,emailField,passwordField,phoneField,confirmPasswordField;//here set username
    JPanel userPanel,namePanel,emailPanel,phonePanel,passwordPanel,emptyPanel,passwordFieldPanel;
    editProfile(){

        Font appFont = new Font(null).deriveFont(20.0f);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(6,1));
        setVisible(true);

        setSize(950,950);

        userNameField=new JTextField("");
        userNameField.setFont(appFont);

        fullNameField=new JTextField("");
        fullNameField.setFont(appFont);

        emailField=new JTextField("");
        emailField.setFont(appFont);

        passwordField=new JPasswordField("");
        passwordField.setFont(appFont);

        phoneField=new JTextField("");
        phoneField.setFont(appFont);

        editButton=new JButton("Edit");
        editButton.setFont(appFont);

        saveButton=new JButton("Save");
        saveButton.setFont(appFont);

        userLabel=new JLabel("User Name:",JLabel.CENTER);
        userLabel.setFont(appFont);

        fullNameLabel=new JLabel("Full Name:",JLabel.CENTER);
        fullNameLabel.setFont(appFont);

        emailLabel=new JLabel("Email ID:",JLabel.CENTER);
        emailLabel.setFont(appFont);

        passwordLabel=new JLabel("Password:",JLabel.CENTER);
        passwordLabel.setFont(appFont);

        phoneLabel=new JLabel("Phone No:",JLabel.CENTER);
        phoneLabel.setFont(appFont);

        emptyLabel=new JLabel("");


        userPanel = new JPanel(new GridLayout(1,2));
        userPanel.setBorder(new EmptyBorder(33,0,33,25));
        userPanel.setBackground(Color.YELLOW);
        userPanel.add(userLabel);
        userPanel.add(userNameField);

        namePanel = new JPanel(new GridLayout(1,2));
        namePanel.setBorder(new EmptyBorder(33,0,33,25));
        namePanel.setBackground(Color.YELLOW);
        namePanel.add(fullNameLabel);
        namePanel.add(fullNameField);

        emailPanel = new JPanel(new GridLayout(1,2));
        emailPanel.setBorder(new EmptyBorder(33,0,33,25));
        emailPanel.setBackground(Color.YELLOW);
        emailPanel.add(emailLabel);
        emailPanel.add(emailField);

        passwordPanel = new JPanel(new GridLayout(1,2));
        passwordPanel.setBorder(new EmptyBorder(33,0,33,25));
        passwordPanel.setBackground(Color.YELLOW);
        passwordPanel.add(passwordLabel);

        passwordFieldPanel = new JPanel(new GridLayout(1,1));
        passwordFieldPanel.add(passwordField);

        passwordPanel.add(passwordFieldPanel);

        phonePanel = new JPanel(new GridLayout(1,2));
        phonePanel.setBorder(new EmptyBorder(33,0,33,25));
        phonePanel.setBackground(Color.YELLOW);
        phonePanel.add(phoneLabel);
        phonePanel.add(phoneField);

        emptyPanel = new JPanel(new GridLayout(1,2));
        emptyPanel.setBorder(new EmptyBorder(33,0,33,25));
        emptyPanel.setBackground(Color.YELLOW);
        emptyPanel.add(emptyLabel);
        emptyPanel.add(editButton);
        saveButton.setVisible(false);

        add(userPanel);
        add(namePanel);
        add(emailPanel);
        add(phonePanel);
        add(passwordPanel);
        add(emptyPanel);


        //add(saveButton);
        try{
            Class.forName("oracle.jdbc.OracleDriver");
            Connection con = MoviesDatabase.getConnection();
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery("select * from reg where username='dumbledore'");
            while(rs.next()){

                userNameField.setText(rs.getString(1));
                fullNameField.setText(rs.getString(2));
                emailField.setText(rs.getString(3));
                passwordField.setText(rs.getString(4));
                phoneField.setText(rs.getString(5));

                userNameField.setEditable(false);
                fullNameField.setEditable(false);
                emailField.setEditable(false);
                passwordField.setEditable(false);
                phoneField.setEditable(false);
                editButton.setVisible(true);

                setVisible(true);
                setTitle("View Mode");
                con.close();

                editButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        userNameField.setEditable(true);
                        fullNameField.setEditable(true);
                        emailField.setEditable(true);
                        passwordField.setEditable(true);

                        phoneField.setEditable(true);
                        passwordFieldPanel.removeAll();
                        passwordFieldPanel.repaint();
                        passwordFieldPanel.setLayout(new GridLayout(2,1));
                        passwordFieldPanel.add(passwordField);
                        confirmPasswordField = new JTextField();
                        confirmPasswordField.setFont(appFont);
                        confirmPasswordField.setText(passwordField.getText());
                        confirmPasswordField.setForeground(Color.LIGHT_GRAY);

                        passwordField.getDocument().addDocumentListener(new SimpleDocumentListener() {
                            @Override
                            public void update(DocumentEvent e) {
                                confirmPasswordField.setText(passwordField.getText());
                            }
                        });

                        passwordFieldPanel.add(confirmPasswordField);
                        passwordFieldPanel.revalidate();

                        setTitle("Edit Mode");

                        emptyPanel.remove(editButton);
                        emptyPanel.repaint();
                        saveButton.setVisible(true);
                        emptyPanel.add(saveButton);
                        emptyPanel.revalidate();

                    }
                });
                saveButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try{
                            Class.forName("oracle.jdbc.OracleDriver");
                            Connection con = MoviesDatabase.getConnection();
                            Statement st=con.createStatement();
                            System.out.println("Here");
                            st.executeUpdate("update reg set username='"+userNameField.getText()+"' ,fullname='"+fullNameField.getText()+"' ,Email='"+emailField.getText()+"' ,Password='"+passwordField.getText()+"' ,Phone='"+phoneField.getText()+"' where username='dumbledore'");
                            System.out.println("updated");
                            con.close();
                            //setVisible(false);

                            userNameField.setEditable(false);
                            fullNameField.setEditable(false);
                            emailField.setEditable(false);
                            passwordField.setEditable(false);
                            phoneField.setEditable(false);

                            passwordFieldPanel.removeAll();
                            passwordFieldPanel.repaint();
                            passwordFieldPanel.setLayout(new GridLayout(1,1));
                            passwordFieldPanel.add(passwordField);
                            passwordFieldPanel.revalidate();

                            emptyPanel.remove(saveButton);
                            emptyPanel.repaint();
                            emptyPanel.add(editButton);
                            emptyPanel.revalidate();
                        }
                        catch(Exception e2){
                            System.out.println("Error at Edit Profile Page"+e2);
                        }
                    }
                });
            }
            System.out.println("Here");
            con.close();
        }
        catch(Exception e1){
            System.out.println("Error at edit profile 2: "+e1);
        }

    }
}
class ViewForm{
    public static void main(String args[]){
        new editProfile();
    }
}