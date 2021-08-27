import javax.smartcardio.Card;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.time.LocalDate;

/*ticketInfoPanel
Name:
Movie:
Screen
Seat nos:
Date:
Time:
Amount:
 */

public class paymentsPage extends JPanel {
    JPanel ticketInfoPanel = new JPanel();
    JPanel paymentPanel = new JPanel();
    Font paymentFont = new Font(null).deriveFont(20.0f);
    paymentsPage(bookingBeans obj){
        ticketInfoPanel.setLayout(new GridLayout(8,1));
        ticketInfoPanel.setBackground(Color.BLACK);
        JLabel name = new JLabel("Name: "+obj.getCustomerName());
        name.setForeground(Color.WHITE);
        System.out.println(obj.getCustomerName());
        name.setFont(paymentFont);

        JLabel movie = new JLabel("Movie: "+obj.getMovieInfo());
        movie.setFont(paymentFont);
        movie.setForeground(Color.WHITE);

        String screen = obj.getScreenInfo();

        if(screen.equals("scre1")){
            screen = "Screen 1";
        }

        else if(screen.equals("scre2")){
            screen = "Screen 2";
        }

        else if(screen.equals("scre3")){
            screen = "Screen 3";
        }

        JLabel screenInfo = new JLabel(screen);
        screenInfo.setFont(paymentFont);
        screenInfo.setForeground(Color.WHITE);

        String[] seats = obj.getSeatNumbers().split("/");
        String s = "";
        for(int i = 1; i < seats.length; i++){
            if(i == seats.length-1){
                s += seats[i];
            }

            else{
                s += seats[i]+", ";
            }

        }

        JLabel seatsBooked = new JLabel("Seats booked: "+s);
        seatsBooked.setFont(paymentFont);
        seatsBooked.setForeground(Color.WHITE);

        String date = obj.getDayInfo();
        LocalDate ld = LocalDate.now();

        if(date.equals("Day1")){
            date = String.valueOf(ld);
        }
        else if(date.equals("Day2")){
            date = String.valueOf(ld.plusDays(1));
        }
        else{
            date = String.valueOf(ld.plusDays(2));
        }

        JLabel  dateInfo = new JLabel("Date: "+date);
        dateInfo.setFont(paymentFont);
        dateInfo.setForeground(Color.WHITE);

        String time = obj.getTimeInfo();
        if(time.equals("stat1")){
            time = "Morning: 9AM-12:00PM";
        }
        else if(time.equals("stat2")){
            time = "After noon: 2PM-5PM";
        }
        else if(time.equals("stat3")){
            time = "Night: 8PM-11PM";
        }

        JLabel timeInfo = new JLabel("Timing: "+time);
        timeInfo.setFont(paymentFont);
        timeInfo.setForeground(Color.WHITE);

        String amt = String.valueOf((seats.length-1)*100);
        JLabel amount = new JLabel("Amount: Rs. "+amt);
        amount.setFont(paymentFont);
        amount.setForeground(Color.WHITE);

        JButton payButton = new JButton("Pay");
        payButton.setEnabled(false);
        payButton.setForeground(Color.WHITE);
        payButton.setBackground(Color.DARK_GRAY);
        String finalS = s;
        String finalDate = date;
        String finalTime = time;
        String finalScreen = screen;
        payButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Class.forName("oracle.jdbc.driver.OracleDriver");
                    Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","safwan");
                    String query = "insert into bookingHistory values(?,?,?,?,?,?,?)";
                    PreparedStatement pstmt = conn.prepareStatement(query);
                    pstmt.setString(1,obj.getCustomerName());
                    pstmt.setString(2,obj.getMovieInfo());
                    pstmt.setString(3, finalScreen);
                    pstmt.setString(4, finalS);
                    pstmt.setString(5, finalDate);
                    pstmt.setString(6, finalTime);
                    pstmt.setString(7,amt);
                    pstmt.executeUpdate();
                    System.out.println("Booking history inserted");
                    String query2 = "update "+obj.getScreenInfo()+" set "+obj.getTimeInfo()+" = ? where day = ?";
                    PreparedStatement pstmt2 = conn.prepareStatement(query2);
                    pstmt2.setString(1,obj.getJoinedSeats());
                    pstmt2.setString(2, obj.getDayInfo());
                    pstmt2.executeUpdate();
                    System.out.println("Updating seats"+obj.getJoinedSeats());
                    JOptionPane.showMessageDialog(ticketInfoPanel,"Tickets have been booked and confirmation mail has been sent. Please view booking history at profile for ticketInfoPanels");
                    confirmationMail mailSender = new confirmationMail(obj,finalScreen,finalS,finalDate,finalTime);
                    payButton.setEnabled(false);
                    conn.setAutoCommit(true);
                    conn.close();

                }
                catch(Exception er){
                    System.out.println("Booking history table error: "+er);
                }
            }
        });

        ticketInfoPanel.add(name);
        ticketInfoPanel.add(movie);
        ticketInfoPanel.add(screenInfo);
        ticketInfoPanel.add(seatsBooked);
        ticketInfoPanel.add(dateInfo);
        ticketInfoPanel.add(timeInfo);
        ticketInfoPanel.add(amount);
        //ticketInfoPanel.add(payButton);

        paymentPanel.setLayout(new BorderLayout());
        paymentPanel.setBackground(Color.BLACK);

        JPanel options = new JPanel(new GridLayout(1,3));
        JButton cardButton = new JButton("Debit Card/Credit Card"),netBankingButton = new JButton("Net Banking"),
                upiButton = new JButton("UPI");

        cardButton.setForeground(Color.WHITE);
        cardButton.setBackground(Color.DARK_GRAY);
        netBankingButton.setForeground(Color.WHITE);
        netBankingButton.setBackground(Color.DARK_GRAY);
        upiButton.setForeground(Color.WHITE);
        upiButton.setBackground(Color.DARK_GRAY);

        options.add(cardButton);
        options.add(netBankingButton);
        options.add(upiButton);

        paymentPanel.add(options,BorderLayout.NORTH);

        JPanel paymentOptionPanel = new JPanel();
        CardLayout cardLayout = new CardLayout();
        paymentOptionPanel.setLayout(cardLayout);

        JPanel cardPanel = new JPanel(new GridLayout(3,2));
        cardPanel.setBackground(Color.BLACK);

        JLabel cardNumberLabel = new JLabel("Enter Card Number",JLabel.CENTER);
        cardNumberLabel.setForeground(Color.WHITE);
        cardNumberLabel.setFont(paymentFont);
        JTextField cardNumberField = new JTextField();
        cardNumberField.setFont(paymentFont);

        JLabel cardHolderLabel = new JLabel("Enter Card Holder Name",JLabel.CENTER);
        cardHolderLabel.setForeground(Color.WHITE);
        cardHolderLabel.setFont(paymentFont);
        JTextField cardHolderField = new JTextField();
        cardHolderField.setFont(paymentFont);

        JLabel cvvLabel = new JLabel("Enter CVV Label",JLabel.CENTER);
        cvvLabel.setForeground(Color.WHITE);
        cvvLabel.setFont(paymentFont);
        JPasswordField cvvField = new JPasswordField();
        cvvField.setFont(paymentFont);

        JLabel emptyLabel = new JLabel("");
        JButton selectPayment = new JButton("Select Payment");
        selectPayment.setForeground(Color.WHITE);
        selectPayment.setBackground(Color.DARK_GRAY);
        selectPayment.setFont(paymentFont);

        JButton changePayment = new JButton("Change Payment");
        changePayment.setForeground(Color.WHITE);
        changePayment.setBackground(Color.DARK_GRAY);

        selectPayment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                payButton.setEnabled(true);
                selectPayment.setEnabled(false);
                cardButton.setEnabled(false);
                upiButton.setEnabled(false);
                netBankingButton.setEnabled(false);
                changePayment.setEnabled(true);
            }
        });

        cardPanel.add(cardNumberLabel);
        cardPanel.add(cardNumberField);
        cardPanel.add(cardHolderLabel);
        cardPanel.add(cardHolderField);
        cardPanel.add(cvvLabel);
        cardPanel.add(cvvField);
        cardPanel.setBorder(new EmptyBorder(40,0,40,10));

        paymentOptionPanel.add(cardPanel,"Card");

        JPanel netBankingPanel = new JPanel(new GridLayout(3,2));
        netBankingPanel.setBackground(Color.BLACK);
        String[] bankList = {"Goldman Sachs", "JP Morgan and Chase","Morgan Stanley","Deutsche Bank"};
        JLabel bankLabel = new JLabel("Select Bank",JLabel.CENTER);
        bankLabel.setForeground(Color.WHITE);
        bankLabel.setFont(paymentFont);
        JComboBox<String> banks = new JComboBox<>(bankList);
        banks.setFont(paymentFont);

        JLabel accountNumberLabel = new JLabel("Enter Account number",JLabel.CENTER);
        accountNumberLabel.setForeground(Color.WHITE);
        accountNumberLabel.setFont(paymentFont);
        JTextField accountNumberField = new JTextField();
        accountNumberField.setFont(paymentFont);

        JLabel pinLabel = new JLabel("Enter Pin Number",JLabel.CENTER);
        pinLabel.setForeground(Color.WHITE);
        pinLabel.setFont(paymentFont);
        JPasswordField pinField = new JPasswordField();
        pinField.setFont(paymentFont);

        netBankingPanel.add(bankLabel);
        netBankingPanel.add(banks);

        netBankingPanel.add(accountNumberLabel);
        netBankingPanel.add(accountNumberField);

        netBankingPanel.add(pinLabel);
        netBankingPanel.add(pinField);
        netBankingPanel.setBorder(new EmptyBorder(40,0,40,10));

        paymentOptionPanel.add(netBankingPanel,"Net Banking");

        JPanel upiPanel = new JPanel(new GridLayout(1,2));
        upiPanel.setBackground(Color.BLACK);

        JLabel upiLabel = new JLabel("Enter UPI ID",JLabel.CENTER);
        upiLabel.setForeground(Color.WHITE);
        upiLabel.setFont(paymentFont);
        JTextField upiField = new JTextField();
        upiField.setFont(paymentFont);

        upiPanel.add(upiLabel);
        upiPanel.add(upiField);
        upiPanel.setBorder(new EmptyBorder(150,0,150,10));

        paymentOptionPanel.add(upiPanel,"UPI");

        cardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(paymentOptionPanel,"Card");
            }
        });

        netBankingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(paymentOptionPanel,"Net Banking");
            }
        });

        upiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(paymentOptionPanel,"UPI");
            }
        });

        JPanel payButtonPanel = new JPanel(new GridLayout(2,1));

        payButton.setFont(paymentFont);
        JPanel paySelectionPanel = new JPanel(new GridLayout(1,2));
        paySelectionPanel.add(selectPayment);

        changePayment.setFont(paymentFont);
        changePayment.setEnabled(false);
        changePayment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                payButton.setEnabled(false);
                selectPayment.setEnabled(true);
                cardButton.setEnabled(true);
                upiButton.setEnabled(true);
                netBankingButton.setEnabled(true);
                changePayment.setEnabled(false);
            }
        });

        paySelectionPanel.add(changePayment);
        payButtonPanel.add(paySelectionPanel);
        payButtonPanel.add(payButton);


        paymentPanel.add(paymentOptionPanel,BorderLayout.CENTER);
        paymentPanel.add(payButtonPanel,BorderLayout.SOUTH);

        ticketInfoPanel.setVisible(true);
        add(ticketInfoPanel);
        add(paymentPanel);
        setLayout(new GridLayout(2,1));
        setSize(750,750);
    }
}

