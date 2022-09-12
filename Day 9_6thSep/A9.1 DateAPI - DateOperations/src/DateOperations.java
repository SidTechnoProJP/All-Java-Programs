import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.time.*;

public class DateOperations {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LocalDate currentDate = LocalDate.now();
        Date dateObj = new Date();
//        System.out.println("Today's Date: "+dateObj);
        SimpleDateFormat simpleDF = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("Today's Date: "+simpleDF.format(dateObj));

//        System.out.println("Current Day, Month and Year: "+currentDate);

        int currentDay = currentDate.getDayOfMonth();
        Month currentMonth = currentDate.getMonth();
        int currentYear = currentDate.getYear();

        System.out.println("\nCurrent Day, Month and Year: \n"+currentDay+" "
                +currentMonth+" "+currentYear);

        LocalDate date = LocalDate.of(2022, 3, 25);
        System.out.println("\nParticular date is: "+date);
//        int year = sc.nextInt();
//        int month = sc.nextInt();
//        int day = sc.nextInt();
//        LocalDate givenDate = LocalDate.of(year,month,day);
//        System.out.println("Particular Date is:"+givenDate);
        LocalDate birthDate = LocalDate.now();

        LocalDate date1 = LocalDate.of(2022, 9, 6);
        LocalDate date2 = LocalDate.of(2022, 9, 6);

        if (date1.isAfter(date2))
            System.out.printf("\n[%s] comes after [%s]%n", date1, date2);
        else if (date1.isBefore(date2))
            System.out.printf("\n[%s] comes before [%s]%n", date1, date2);
        else if (date1.isEqual(date2))
            System.out.printf("\nBoth dates [%s] & [%s] are equal\n", date1,date2);

        if(birthDate.equals(date1)){
            System.out.println("\nToday is your Birthday!");
        }
        else {
            System.out.println("\nSorry, today is not your birthday");
        }

//        boolean isLeapYear = Year.now().isLeap();
//        System.out.println("Is 2022 a Leap Year: "+isLeapYear);
        System.out.println("\nEnter year: ");
        int leapYear = sc.nextInt();

        if (birthDate.withYear(leapYear).isLeapYear()){
            System.out.println("\n"+leapYear+" is a Leap Year");
        }
        else {
            System.out.println("\nSorry "+leapYear+" is not a Leap Year!");
        }

        LocalTime localTime = LocalTime.now();
        System.out.println("\nCurrent Time is: "+localTime);

        System.out.println("\nEnter hours to add to current time: ");
        long addHours = sc.nextLong();
        System.out.println("\nAfter adding "+addHours+" to current time is: "
                +localTime.plusHours(addHours));

        Calendar cal = Calendar.getInstance();
        LocalDate birthDay = LocalDate.of(2022, 1, 13);

        LocalDate after1year = birthDay.plusYears(1);

        System.out.println("BD: "+birthDay.getDayOfWeek()+" "
                +birthDay.getDayOfMonth()+" "+birthDay.getMonth()+" "+birthDay.getYear());

        System.out.println("BD after 1 year: "+after1year.getDayOfWeek()+" "
                +after1year.getDayOfMonth()+" "+after1year.getMonth()+" "+after1year.getYear());

        LocalDate before1year = birthDay.minusYears(1);
        System.out.println("BD before 1 year: "+before1year.getDayOfWeek()
                +" "+before1year.getDayOfMonth()+" "+before1year.getMonth()+" "+before1year.getYear());

        Date current_Year = cal.getTime();
        // get next year
        cal.add(Calendar.YEAR, 1);
        Date nextYear = cal.getTime();
        //get previous year
        cal.add(Calendar.YEAR, -2);
        Date prevYear = cal.getTime();
        System.out.println("\nCurrent Date : " + current_Year);
        System.out.println("\nDate before 1 year : " + prevYear);
        System.out.println("\nDate after 1 year  : " + nextYear+"\n");

    }
}
