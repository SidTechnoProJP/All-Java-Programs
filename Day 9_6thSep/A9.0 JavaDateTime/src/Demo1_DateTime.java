public class Demo1_DateTime {
    public static void main(String[] args) {
        System.out.println("Milliseconds (since 1st Jan 1970): "
                +System.currentTimeMillis()); //no of milliseconds passed since 1st Jan 1970
        System.out.println("Seconds (since 1st Jan 1970)     : "
                +System.currentTimeMillis()/1000); //no of seconds since 1st Jan 1970
        System.out.println("Minutes (since 1st Jan 1970)     : "
                +System.currentTimeMillis()/1000/60);
        System.out.println("Hours (since 1st Jan 1970)       : "
                +System.currentTimeMillis()/1000/3600); //no of hours since 1st Jan 1970
        System.out.println("Days (since 1st Jan 1970)        : "
                +System.currentTimeMillis()/1000/3600/24); //no of days since 1st Jan 1970
        System.out.println("Years (since 1st Jan 1970)       : "
                +System.currentTimeMillis()/1000/3600/24/365); //no of years passed since 1st Jan 1970
    }
}
