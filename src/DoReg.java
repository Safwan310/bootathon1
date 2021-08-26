import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Arrays;
import java.util.regex.Pattern;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

class DoReg extends JFrame{
    static JPanel mother;
    JPanel userSelectionPanel,regPanel,mainPanel,nav;
    JLabel usernameLabel,fullnameLabel,emailLabel,phoneLabel,passwordLabel,userTypeLabel,txt1,conPassLabel;
    JButton registerButton,loginPageButton,nextButton;
    JTextField usernameField,fullNameField,emailField,passwordField,phoneField,conPassField;
    static JComboBox userTypeBox;
    String[] s1 ={"Customer","Admin"};
    String userType = "";
    DoReg(){
        mother = new JPanel();
        mother.setLayout(new BorderLayout());
        setLayout(new GridLayout(1,1));
        CardLayout cardLayout = new CardLayout();

        Font appFont = new Font(null).deriveFont(20.0f);

        nav = new JPanel();
        nav.setLayout(new BorderLayout());

        JButton backButton = new JButton("Back");


        txt1=new JLabel("SK Cinemas",JLabel.CENTER);
        txt1.setFont(appFont);
        txt1.setForeground(Color.YELLOW);

        nav.add(backButton,BorderLayout.WEST);
        nav.add(txt1,BorderLayout.CENTER);
        nav.setPreferredSize(new Dimension(50,50));

        nav.setBackground(Color.BLACK);

        mainPanel = new JPanel(new GridLayout(1,1));
        userSelectionPanel = new JPanel(new BorderLayout());
        userSelectionPanel.setBorder(new EmptyBorder(400,100,400,100));

        regPanel = new JPanel(new GridLayout(7,2));

        userTypeBox=new JComboBox<>(s1);
        userTypeBox.setFont(appFont);
        userTypeBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED){
                    userType = (String) userTypeBox.getSelectedItem();
                }
            }
        });
        userType = (String) userTypeBox.getSelectedItem();
        mainPanel.setLayout(cardLayout);
        setTitle("Welcome to SK Cinemas");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600,600);

        mother.add(nav,BorderLayout.NORTH);
        mother.add(mainPanel,BorderLayout.CENTER);

        add(mother);

        usernameField=new JTextField();
        usernameField.setFont(appFont);
        fullNameField=new JTextField();
        fullNameField.setFont(appFont);
        emailField=new JTextField();
        emailField.setFont(appFont);
        passwordField=new JTextField();
        passwordField.setFont(appFont);
        conPassLabel = new JLabel("Confirm Password");
        conPassLabel.setFont(appFont);
        conPassField = new JTextField();
        conPassField.setFont(appFont);
        phoneField=new JTextField();
        phoneField.setFont(appFont);

        registerButton=new JButton("Register");
        registerButton.setFont(appFont);
        nextButton=new JButton("Next");
        nextButton.setFont(appFont);
        loginPageButton=new JButton("Already have Acoount?Click here to Login");
        loginPageButton.setFont(appFont);

        usernameLabel=new JLabel("Username:");
        usernameLabel.setFont(appFont);
        fullnameLabel=new JLabel("Fullname:");
        fullnameLabel.setFont(appFont);
        emailLabel=new JLabel("Email:");
        emailLabel.setFont(appFont);
        passwordLabel=new JLabel("Password:");
        passwordLabel.setFont(appFont);
        phoneLabel=new JLabel("Phone no:");
        phoneLabel.setFont(appFont);
        userTypeLabel=new JLabel("Select User Type:",SwingConstants.LEFT);
        userTypeLabel.setFont(appFont);

        userSelectionPanel.add(userTypeLabel,BorderLayout.WEST);
        userSelectionPanel.add(userTypeBox,BorderLayout.CENTER);
        userSelectionPanel.add(nextButton,BorderLayout.EAST);

        regPanel.add(usernameLabel);
        regPanel.add(usernameField);
        regPanel.add(fullnameLabel);
        regPanel.add(fullNameField);
        regPanel.add(emailLabel);
        regPanel.add(emailField);
        regPanel.add(passwordLabel);
        regPanel.add(passwordField);
        regPanel.add(conPassLabel);
        regPanel.add(conPassField);
        regPanel.add(phoneLabel);
        regPanel.add(phoneField);
        regPanel.add(registerButton);
        regPanel.add(loginPageButton);

        regPanel.setFont(appFont);

        userSelectionPanel.setName("Selection Panel");
        regPanel.setName("Registration Page");
        DoLogin loginPagePanel = new DoLogin(userType);
        loginPagePanel.setName("Login Page");
        mainPanel.add(userSelectionPanel,"Selection Panel");
        mainPanel.add(regPanel,"Registration Page");
        mainPanel.add(loginPagePanel,"Login Page");

        setVisible(true);


        backButton.addActionListener(e -> {
            if(userTypeBox.getSelectedItem().equals("Admin")){
                cardLayout.show(mainPanel,"Selection Panel");
            }
            else{
                if(!mainPanel.getComponent(0).isVisible()){
                    cardLayout.previous(mainPanel);
                }
            }

        });

        nextButton.addActionListener((ActionEvent e) -> {
            if(userTypeBox.getSelectedItem().equals(s1[1])){
                cardLayout.show(mainPanel,"Login Page");
            }
            else{
                cardLayout.show(mainPanel,"Registration Page");
            }
        });

        registerButton.addActionListener((ActionEvent e) -> {
            if(usernameField.getText().isEmpty() || fullNameField.getText().isEmpty() || emailField.getText().isEmpty() || passwordField.getText().isEmpty() || phoneField.getText().isEmpty()){
                JOptionPane.showMessageDialog(mainPanel,"Fill all details pls!");
            }
            else if(!Pattern.matches("[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$",emailField.getText())){
                JOptionPane.showMessageDialog(mainPanel,"Enter valid Email");
            }
            else if(!Pattern.matches("[A-Z]+[a-z]+[0-9]+",passwordField.getText())){
                JOptionPane.showMessageDialog(mainPanel,"Pasword must be followed as Uppercase,lower case,number");
            }
            else if(!Pattern.matches("[0-9]+",phoneField.getText())){
                JOptionPane.showMessageDialog(mainPanel,"Must be only number");
            }
            else{
                userTypeLabel.setText("");
                try{
                    Connection con=MoviesDatabase.getConnection();
                    System.out.println("Successfully connected");
                    String query = "insert into reg values(?,?,?,?,?,?)";
                    PreparedStatement pstmt = con.prepareStatement(query);
                    pstmt.setString(1,usernameField.getText());
                    pstmt.setString(2,fullNameField.getText());
                    pstmt.setString(3,emailField.getText());
                    pstmt.setString(4,passwordField.getText());
                    pstmt.setString(5,phoneField.getText());
                    pstmt.setString(6,"No");
                    pstmt.executeUpdate();
                    userTypeLabel.setText("Registered");
                    registerButton.setEnabled(false);
                    JOptionPane.showMessageDialog(mainPanel,"Registration Successful, Head to login page");
                    con.close();
                }
                catch(Exception e1){
                    userTypeLabel.setText("Try another username");
                } }
        });
        loginPageButton.addActionListener((ActionEvent e) -> {
            cardLayout.show(mainPanel,"Login Page");
        });
    }

}

class Reg{
    public static void main(String[] args){
        DoReg ob=new DoReg();
    }
}
