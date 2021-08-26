import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class movieBookingPage extends JPanel {
    JPanel mother,movieInfo,dateInfo,timeInfo,screenInfo,home,buttonPanel;
    JButton day1,day2,day3,screen1,screen2,screen3,time1,time2,time3;
    String dayForScreen = "",screenForTime = "";
    movieBookingPage(String imgPath,String movieName,bookingBeans ob) throws IOException, ClassNotFoundException, SQLException {
        ob.setMovieInfo(movieName);
        Font bookingFont = new Font(null).deriveFont(20.0f);

        home = new JPanel();
        home.setLayout(new BorderLayout());

        mother = new JPanel();
        mother.setLayout(new GridLayout(4,1,5,10));

        movieInfo = new JPanel();
        movieInfo.setLayout(new BorderLayout());

        dateInfo = new JPanel();
        dateInfo.setLayout(new GridLayout(1,5,5,5));

        screenInfo = new JPanel();
        screenInfo.setLayout(new GridLayout(1,5,5,5));

        timeInfo = new JPanel();
        timeInfo.setLayout(new GridLayout(1,5,5,5));

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1,1));

        BufferedImage movieOne = ImageIO.read(new File(imgPath));
        JLabel movieOneImg = new JLabel(new ImageIcon(movieOne));
        JLabel movieOneTitle = new JLabel(movieName,SwingConstants.CENTER);
        movieOneTitle.setFont(new Font(null).deriveFont(20.0f));

        JLabel dateLabel = new JLabel("Select Date",SwingConstants.CENTER);
        dateLabel.setFont(bookingFont);
        day1 = new JButton("16/08/2021");
        day2 = new JButton("17/08/2021");
        day3 = new JButton("18/08/2021");
        JButton changeDay = new JButton("Change Date");
        changeDay.setEnabled(false);
        changeDay.setFont(bookingFont);

        List<JButton> dayButtons = new ArrayList<>();
        dayButtons.add(day1);
        dayButtons.add(day2);
        dayButtons.add(day3);

        for (JButton dayButton : dayButtons) {
            dayButton.setFont(bookingFont);
            dayButton.setEnabled(false);
        }

        day1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ob.setDayInfo("Day1");
                try {
                    screenFetcher("1",movieName);
                    dayForScreen = "1";
                } catch (ClassNotFoundException | SQLException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }
                for (JButton dayButton : dayButtons) {
                    dayButton.setEnabled(false);
                }
                changeDay.setEnabled(true);
            }
        });

        day2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ob.setDayInfo("Day2");
                try {
                    screenFetcher("2",movieName);
                    dayForScreen = "2";
                } catch (ClassNotFoundException | SQLException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }
                for (JButton dayButton : dayButtons) {
                    dayButton.setEnabled(false);
                }
                changeDay.setEnabled(true);
            }
        });

        day3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ob.setDayInfo("Day3");
                try {
                    screenFetcher("3",movieName);
                    dayForScreen = "3";
                } catch (ClassNotFoundException | SQLException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }
                for (JButton dayButton : dayButtons) {
                    dayButton.setEnabled(false);
                }
                changeDay.setEnabled(true);
            }
        });

        JLabel screenLabel = new JLabel("Select screen",SwingConstants.CENTER);
        screenLabel.setFont(bookingFont);
        screen1 = new JButton("Screen1");
        screen2 = new JButton("Screen2");
        screen3 = new JButton("Screen3");
        JButton changeScreen = new JButton("Change Screen");
        changeScreen.setEnabled(false);
        changeScreen.setFont(bookingFont);

        List<JButton> screenList = new ArrayList<>();
        screenList.add(screen1);
        screenList.add(screen2);
        screenList.add(screen3);

        JLabel timeLabel = new JLabel("Select Timing",SwingConstants.CENTER);
        timeLabel.setFont(bookingFont);
        time1 = new JButton("Morning: 9AM-12:00PM");
        time2 = new JButton("After noon: 2PM-5PM");
        time3 = new JButton("Night: 8PM-11PM");
        JButton changeTime = new JButton("Change Time");
        changeTime.setEnabled(false);
        changeTime.setFont(bookingFont);


        List<JButton> timeList = new ArrayList<>();
        timeList.add(time1);
        timeList.add(time2);
        timeList.add(time3);

        for(JButton time:timeList){
            time.setFont(bookingFont);
            time.setEnabled(false);
        }

        changeDay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ob.setDayInfo(null);
                ob.setScreenInfo(null);
                ob.setTimeInfo(null);
                for (JButton dayButton : dayButtons) {
                    dayButton.setEnabled(false);
                }
                try {
                    dateFetcher(movieName);
                } catch (ClassNotFoundException | SQLException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }
                for(JButton screen:screenList){
                    screen.setEnabled(false);
                }
                for(JButton time:timeList){
                    time.setEnabled(false);
                }
                changeDay.setEnabled(false);
                changeScreen.setEnabled(false);
                changeTime.setEnabled(false);
            }
        });

        for(JButton screen:screenList){
            screen.setFont(bookingFont);
            screen.setEnabled(false);
        }

        screen1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ob.setScreenInfo("scre1");
                screenForTime = "1";
                try {
                    timingFetcher(dayForScreen,screenForTime,movieName );
                } catch (ClassNotFoundException | SQLException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }
                for(JButton screen:screenList){
                    screen.setEnabled(false);
                }
                changeScreen.setEnabled(true);
            }
        });

        screen2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ob.setScreenInfo("scre2");
                screenForTime = "2";
                try {
                    timingFetcher(dayForScreen,screenForTime,movieName );
                } catch (ClassNotFoundException | SQLException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }
                for(JButton screen:screenList){
                    screen.setEnabled(false);
                }
                changeScreen.setEnabled(true);
            }
        });

        screen3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ob.setScreenInfo("scre3");
                screenForTime = "3";
                try {
                    timingFetcher(dayForScreen,screenForTime,movieName );
                } catch (ClassNotFoundException | SQLException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }
                for(JButton screen:screenList){
                    screen.setEnabled(false);
                }
                changeScreen.setEnabled(true);
            }
        });

        changeScreen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ob.setScreenInfo(null);
                ob.setTimeInfo(null);
                for(JButton screen:screenList){
                    screen.setEnabled(false);
                }
                try {
                    screenFetcher(dayForScreen,movieName);
                } catch (ClassNotFoundException | SQLException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }
                for(JButton time:timeList){
                    time.setEnabled(false);
                }
                changeScreen.setEnabled(false);
                changeTime.setEnabled(false);
            }
        });

        time1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ob.setTimeInfo("stat1");
                for(JButton time:timeList){
                    time.setEnabled(false);
                }
                changeTime.setEnabled(true);
            }
        });

        time2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ob.setTimeInfo("stat2");
                for(JButton time:timeList){
                    time.setEnabled(false);
                }
                changeTime.setEnabled(true);
            }
        });

        time3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ob.setTimeInfo("stat3");
                for(JButton time:timeList){
                    time.setEnabled(false);
                }
                changeTime.setEnabled(true);
            }
        });

        changeTime.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ob.setTimeInfo(null);
                for(JButton time:timeList){
                    time.setEnabled(false);
                }
                try {
                    timingFetcher(dayForScreen,screenForTime,movieName);
                } catch (ClassNotFoundException | SQLException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }
                changeTime.setEnabled(false);
            }
        });
        JButton bookTickets = new JButton("Book Tickets");
        bookTickets.setFont(bookingFont);
        bookTickets.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if((ob.getTimeInfo()==null)||(ob.getScreenInfo()==null)||(ob.getDayInfo()==null)){
                    JOptionPane.showMessageDialog(mother,"Please select all the fields");
                }
                else{
                    seatSelection obj = new seatSelection(ob);
                    obj.mother.setVisible(true);
                    home.removeAll();
                    home.repaint();
                    home.add(obj);
                    home.revalidate();
                    System.out.println("Ready to roll");
                }
            }
        });

        dateFetcher(movieName);

        movieInfo.add(movieOneImg,BorderLayout.WEST);
        movieInfo.add(movieOneTitle,BorderLayout.CENTER);
        movieInfo.setPreferredSize(new Dimension(200,200));
        System.out.println(movieOneTitle.getText());

        dateInfo.add(dateLabel);
        dateInfo.add(day1);
        dateInfo.add(day2);
        dateInfo.add(day3);
        dateInfo.add(changeDay);

        screenInfo.add(screenLabel);
        screenInfo.add(screen1);
        screenInfo.add(screen2);
        screenInfo.add(screen3);
        screenInfo.add(changeScreen);

        timeInfo.add(timeLabel);
        timeInfo.add(time1);
        timeInfo.add(time2);
        timeInfo.add(time3);
        timeInfo.add(changeTime);

        buttonPanel.add(bookTickets);

        home.add(movieInfo,BorderLayout.NORTH);

        mother.add(dateInfo);
        mother.add(screenInfo);
        mother.add(timeInfo);
        mother.add(buttonPanel);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        home.add(mother,BorderLayout.CENTER);

        movieInfo.setVisible(true);
        dateInfo.setVisible(true);
        timeInfo.setVisible(true);
        mother.setVisible(true);
        //setSize(750,750);
        setLayout(new GridLayout(1,1));

        add(home);
    }

    public void dateFetcher(String movieName) throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection conn = MoviesDatabase.getConnection();
        String query = "select dayinfo from showinfo where moviename = ?";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1,movieName);
        ResultSet rst = pstmt.executeQuery();
        while(rst.next()){
            if(rst.getString("dayinfo").equals("1")){
                day1.setEnabled(true);
            }
            if(rst.getString("dayinfo").equals("2")){
                day2.setEnabled(true);
            }
            if(rst.getString("dayinfo").equals("3")){
                day3.setEnabled(true);
            }
        }
    }
    public void screenFetcher(String day,String movieName) throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection conn = MoviesDatabase.getConnection();
        String query = "select screeninfo from showinfo where dayinfo = ? and moviename = ?";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1,day);
        pstmt.setString(2,movieName);
        ResultSet rst = pstmt.executeQuery();
        while(rst.next()){
            if(rst.getString("screeninfo").equals("1")){
                screen1.setEnabled(true);
            }
            if(rst.getString("screeninfo").equals("2")){
                screen2.setEnabled(true);
            }
            if(rst.getString("screeninfo").equals("3")){
                screen3.setEnabled(true);
            }
        }
    }
    public void timingFetcher(String day, String screen,String moviename) throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection conn = MoviesDatabase.getConnection();
        String query = "select timing1,timing2,timing3 from showinfo where dayinfo = ? and moviename = ? and screeninfo = ?";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1,day);
        pstmt.setString(2,moviename);
        pstmt.setString(3,screen);
        ResultSet rst = pstmt.executeQuery();
        while(rst.next()){
            if(rst.getString("timing1").equals("YES")){
                time1.setEnabled(true);
            }
            if(rst.getString("timing2").equals("YES")){
                time2.setEnabled(true);
            }
            if(rst.getString("timing3").equals("YES")){
                time3.setEnabled(true);
            }
        }
    }
}
