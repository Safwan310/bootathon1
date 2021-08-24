import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;

public class movieList extends JFrame {
    movieList() throws IOException, SQLException, ClassNotFoundException {
        JPanel movies = new JPanel();

        movieBookingPage pageOne = new movieBookingPage("src/assets/btmn.jpg","Batman Begins");
        movieBookingPage pageTwo = new movieBookingPage("src/assets/btmn2.jpg","The Dark Knight");
        movieBookingPage pageThree = new movieBookingPage("src/assets/btmn3.jpg","The Dark Knight Rises");

        movies.setLayout(new GridLayout(3,1));

        JButton m1 = new JButton("Batman Begins");
        m1.addActionListener(e -> pageOne.setVisible(true));

        JButton m2 = new JButton("The Dark Knight");
        m2.addActionListener(e -> pageTwo.setVisible(true));

        JButton m3 = new JButton("The Dark Knight Rises");
        m3.addActionListener(e -> pageThree.setVisible(true));

        movies.add(m1);
        movies.add(m2);
        movies.add(m3);
        movies.setVisible(true);

        add(movies);
        movies.setSize(750,750);
        setVisible(true);
        setSize(750,750);
    }

    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
        new movieList();
    }
}
