import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
public class confirmationMail {
    confirmationMail(bookingBeans obj,String screenInfo, String seatInfo, String dateInfo,String timeInfo) {
        final String userName = "EMAIL";
        final String password = "PASSWORD";

        final String from = "EMAIL";
        final String to = obj.getEmailInfo();

        Properties prop = new Properties();

        prop.put("mail.smtp.host","smtp.gmail.com");
        prop.put("mail.smtp.socketFactory.port","465");
        prop.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        prop.put("mail.smtp.auth","true");
        prop.put("mail.smtp.port","465");

        Authenticator a = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName,password);
            }
        };

        Session session = Session.getInstance(prop,a);

        try{
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress());
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
            message.setSubject("Confirmation Mail");
            message.setText("Hello! "+obj.getCustomerName()+" you have booked the seats "+seatInfo+" at "+screenInfo+" for the movie " +
                    obj.getMovieInfo()+" at SK Cinemas "+" on "+dateInfo+". Your showtiming is "+timeInfo+". Thanks for choosing us.... Enjoy your show!");

            Transport.send(message);
            System.out.println("Done");
        }
        catch(MessagingException e){
            System.out.println("Error at Mail:"+e);
        }
    }
}
