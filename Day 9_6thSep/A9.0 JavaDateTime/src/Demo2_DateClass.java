import java.util.Date;

public class Demo2_DateClass {
    public static void main(String[] args) {
//        System.out.println(Integer.MAX_VALUE + " - Maximum Integer Value");
//        System.out.println(System.currentTimeMillis() + " - Current time in Milliseconds since 1/1/1970");
//        System.out.println(Long.MAX_VALUE + " - Maximum Long Value");
        Date d = new Date();
        System.out.println(d);
        System.out.println(d.getTime());
        System.out.println(d.getDate());
        System.out.println(d.getSeconds());
        System.out.println(d.getYear());
        System.out.println(d.getYear()+1900); //since date starts from 1900
        System.out.println(d.getMonth());
    }

}
