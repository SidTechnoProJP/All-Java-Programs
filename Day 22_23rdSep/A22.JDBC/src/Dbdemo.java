import java.util.*;
import java.sql.*;

class Dbdemo {
    static Connection link;
    static Statement stm;
    static ResultSet rs;

    Dbdemo() throws SQLException {
        link = DriverManager.getConnection("jdbc:mysql://localhost/student_data", "root", "root");
       /* try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
            link = DriverManager.getConnection("jdbc:mysql://localhost/student_data", "root", "root");
        } catch (ClassNotFoundException cnfEx) {
            System.out.println("* Unable to load driver! *");
            System.exit(1);
        } catch (SQLException sqlEx) {
            System.out.println("* Cannot connect to database! *");
            System.exit(1);
        }*/
        stm = link.createStatement();
    }

    void displayinfo() throws SQLException {
        String s = "SELECT * FROM student";
        rs = stm.executeQuery(s);
        while (rs.next()) {
            System.out.println(rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getString(4) + "\t" + rs.getString(5));
        }
    }

    void insertinfo() throws SQLException {
        int r1 = 0;
        Scanner s = new Scanner(System.in);
        System.out.println("Enter USN");
        int usn = s.nextInt();
        System.out.println("Enter Name");
        String name = s.next();
        System.out.println("Enter Age");
        int age = s.nextInt();
        System.out.println("Enter Marks 1");
        int marks1 = s.nextInt();
        System.out.println("Enter Marks 2");
        int marks2 = s.nextInt();
        try {
            String s1 = "insert into student values(" + usn + ",'" + name + "','" + age + "','" + marks1 + "','" + marks2 + "')";
            r1 = stm.executeUpdate(s1);
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println(r1 + "rows affected");
    }

    void deleteinfo() throws SQLException {
        Scanner s1 = new Scanner(System.in);
        System.out.println("Enter the USN: ");
        int usn = s1.nextInt();
        String s2 = "DELETE FROM student WHERE USN =" + usn;
        stm.executeUpdate(s2);
        System.out.println("Database deleted successfully!!!!");
    }

    void updateinfo() throws SQLException {
        Scanner s1 = new Scanner(System.in);
        System.out.println("Enter the USN: ");
        int usn = s1.nextInt();
        System.out.println("enter the new name");
        String name = s1.next();
        String s3 = "update student set Name='" + name + "' where USN =" + usn;
        stm.executeUpdate(s3);
        System.out.println("Database updated successfully!!!!");
    }

    void closecon() throws SQLException {
        rs.close();
        stm.close();
        link.close();
    }
}

class JDBCdemo {
    public static void main(String[] args) throws SQLException {
        boolean f = true;
        Dbdemo d1 = new Dbdemo();
        d1.displayinfo();
        while (f) {
            System.out.println("1:insert 2:delete 3:display 4:update 5:exit");
            System.out.println("enter your option");
            Scanner s = new Scanner(System.in);
            int op = s.nextInt();
            switch (op) {
                case 1:
                    d1.insertinfo();
                    break;
                case 2:
                    d1.deleteinfo();
                    break;
                case 3:
                    d1.displayinfo();
                    break;
                case 4:
                    d1.updateinfo();
                    break;
                case 5:
                    f = false;
            }
        }
        d1.closecon();
    }
}

/*

"C:\Program Files\Java\jdk1.8.0_92\bin\java.exe" "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2022.2.1\lib\idea_rt.jar=58431:C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2022.2.1\bin" -Dfile.encoding=UTF-8 -classpath "C:\Program Files\Java\jdk1.8.0_92\jre\lib\charsets.jar;C:\Program Files\Java\jdk1.8.0_92\jre\lib\deploy.jar;C:\Program Files\Java\jdk1.8.0_92\jre\lib\ext\access-bridge-64.jar;C:\Program Files\Java\jdk1.8.0_92\jre\lib\ext\cldrdata.jar;C:\Program Files\Java\jdk1.8.0_92\jre\lib\ext\dnsns.jar;C:\Program Files\Java\jdk1.8.0_92\jre\lib\ext\jaccess.jar;C:\Program Files\Java\jdk1.8.0_92\jre\lib\ext\jfxrt.jar;C:\Program Files\Java\jdk1.8.0_92\jre\lib\ext\localedata.jar;C:\Program Files\Java\jdk1.8.0_92\jre\lib\ext\nashorn.jar;C:\Program Files\Java\jdk1.8.0_92\jre\lib\ext\sunec.jar;C:\Program Files\Java\jdk1.8.0_92\jre\lib\ext\sunjce_provider.jar;C:\Program Files\Java\jdk1.8.0_92\jre\lib\ext\sunmscapi.jar;C:\Program Files\Java\jdk1.8.0_92\jre\lib\ext\sunpkcs11.jar;C:\Program Files\Java\jdk1.8.0_92\jre\lib\ext\zipfs.jar;C:\Program Files\Java\jdk1.8.0_92\jre\lib\javaws.jar;C:\Program Files\Java\jdk1.8.0_92\jre\lib\jce.jar;C:\Program Files\Java\jdk1.8.0_92\jre\lib\jfr.jar;C:\Program Files\Java\jdk1.8.0_92\jre\lib\jfxswt.jar;C:\Program Files\Java\jdk1.8.0_92\jre\lib\jsse.jar;C:\Program Files\Java\jdk1.8.0_92\jre\lib\management-agent.jar;C:\Program Files\Java\jdk1.8.0_92\jre\lib\plugin.jar;C:\Program Files\Java\jdk1.8.0_92\jre\lib\resources.jar;C:\Program Files\Java\jdk1.8.0_92\jre\lib\rt.jar;C:\Users\Siddhesh U S\Desktop\Assignments\Day 22_23rdSep\A22.JDBC\out\production\A22.JDBC;C:\Users\Siddhesh U S\Downloads\mysql-connector-java-8.0.30.jar" JDBCdemo
101	Sid	20	null	null
102	Jay	21	null	null
103	Max	21	null	null
104	ABC	21	null	null
105	Kim	23	null	null
106	Jim	18	null	null
107	Pam	23	null	null
108	Mat	21	null	null
1:insert 2:delete 3:display 4:update 5:exit
enter your option
1
Enter USN
109
Enter Name
Bobby
Enter Age
23
Enter Marks 1
12
Enter Marks 2
23
1rows affected
1:insert 2:delete 3:display 4:update 5:exit
enter your option
3
101	Sid	20	null	null
102	Jay	21	null	null
103	Max	21	null	null
104	ABC	21	null	null
105	Kim	23	null	null
106	Jim	18	null	null
107	Pam	23	null	null
108	Mat	21	null	null
109	Bobby	23	12	23
1:insert 2:delete 3:display 4:update 5:exit
enter your option
1
Enter USN
110
Enter Name
Rob


Enter Age
20
Enter Marks 1
10
Enter Marks 2
23
1rows affected
1:insert 2:delete 3:display 4:update 5:exit
enter your option
3
101	Sid	20	null	null
102	Jay	21	null	null
103	Max	21	null	null
104	ABC	21	null	null
105	Kim	23	null	null
106	Jim	18	null	null
107	Pam	23	null	null
108	Mat	21	null	null
109	Bobby	23	12	23
110	Rob	20	10	23
1:insert 2:delete 3:display 4:update 5:exit
enter your option
2
Enter the USN:
109
Database deleted successfully!!!!
1:insert 2:delete 3:display 4:update 5:exit
enter your option
3
101	Sid	20	null	null
102	Jay	21	null	null
103	Max	21	null	null
104	ABC	21	null	null
105	Kim	23	null	null
106	Jim	18	null	null
107	Pam	23	null	null
108	Mat	21	null	null
110	Rob	20	10	23
1:insert 2:delete 3:display 4:update 5:exit
enter your option
4
Enter the USN:
110
enter the new name
Shawty
Database updated successfully!!!!
1:insert 2:delete 3:display 4:update 5:exit
enter your option
3
101	Sid	20	null	null
102	Jay	21	null	null
103	Max	21	null	null
104	ABC	21	null	null
105	Kim	23	null	null
106	Jim	18	null	null
107	Pam	23	null	null
108	Mat	21	null	null
110	Shawty	20	10	23
1:insert 2:delete 3:display 4:update 5:exit
enter your option
5

Process finished with exit code 0

* */