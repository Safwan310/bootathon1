import sun.reflect.annotation.ExceptionProxy;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class upcomingMoviePage extends JPanel {

    Font pageFont = new Font(null).deriveFont(20.0f);


    JPanel movieInfo = new JPanel(new BorderLayout());
    JPanel contentPanel = new JPanel(new GridLayout(2,2));
    upcomingMoviePage(String imgPath,String movieName) throws IOException {
        setLayout(new BorderLayout());
        URL url = new URL(imgPath);
        Image image = ImageIO.read(url.openStream());
        JLabel movieOneImg = new JLabel(new ImageIcon(image));
        JLabel movieOneTitle = new JLabel(movieName,SwingConstants.CENTER);
        movieOneTitle.setForeground(Color.WHITE);
        movieOneTitle.setFont(new Font(null).deriveFont(20.0f));

        movieInfo.add(movieOneImg,BorderLayout.WEST);
        movieInfo.add(movieOneTitle,BorderLayout.CENTER);
        movieInfo.setPreferredSize(new Dimension(200,200));
        movieInfo.setBackground(Color.BLACK);

        String descText = "";
        try{
            Connection conn = MoviesDatabase.getConnection();
            String query = "select description from movie where moviename = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1,movieName);
            ResultSet rst = pstmt.executeQuery();
            while(rst.next()){
                descText += rst.getString("description");
                System.out.println(descText);
            }
        }
        catch(Exception e){
            System.out.println("Error at UPCM: "+e);
        }

        JLabel descLabel = new JLabel("Movie Description",JLabel.CENTER);
        descLabel.setForeground(Color.WHITE);

        descLabel.setFont(pageFont);
        JTextArea description = new JTextArea(descText);
        description.setBackground(Color.BLACK);
        description.setForeground(Color.WHITE);
        description.setLineWrap(true);
        description.setWrapStyleWord(true);
        description.setEditable(false);
        description.setFont(pageFont);

        JLabel notifLabel = new JLabel("This movie will be screened soon",JLabel.CENTER);
        notifLabel.setForeground(Color.WHITE);
        notifLabel.setFont(pageFont);
        contentPanel.add(descLabel);
        contentPanel.add(description);
        contentPanel.add(new JLabel(""));
        contentPanel.add(notifLabel);
        contentPanel.setBackground(Color.BLACK);
        contentPanel.setBorder(new EmptyBorder(50,0,20,10));

        add(movieInfo,BorderLayout.NORTH);
        add(contentPanel,BorderLayout.CENTER);
        setVisible(true);
    }
}
