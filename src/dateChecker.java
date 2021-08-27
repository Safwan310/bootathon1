import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
    public class dateChecker
    {
        public static void main(String[] args)
        {
            LocalDate ld = LocalDate.now();
            System.out.println("Current date: " + ld.getDayOfMonth());
            System.out.println("Current month: " + ld.getMonthValue());
            System.out.println("Current year: " + ld.getYear());
            System.out.println(ld);
            System.out.println(ld.plusDays(1));
            System.out.println(ld.plusDays(6));
            // get current time value
            LocalTime lt = LocalTime.now();
            System.out.println("Current time: " + lt);
            // get current date-time value
            LocalDateTime ldt = LocalDateTime.now();
            System.out.println("Current date-time: " + ldt);
        }
    }
