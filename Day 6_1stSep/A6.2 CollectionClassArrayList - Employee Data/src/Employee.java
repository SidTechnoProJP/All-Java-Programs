import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class EmployeeData {
    public int employeeId, salary;
    public String name;

    EmployeeData(int employeeId, String name, int salary) {
        this.employeeId = employeeId;
        this.name = name;
        this.salary = salary;
    }

    void showData(){
        System.out.println("\nEmployee Id: "+employeeId);
        System.out.println("Employee Name: "+name);
        System.out.println("Employee Salary: "+salary);
    }
}


public class Employee {
    static String name;
    static int employeeId, salary;
    static List<EmployeeData> employeeDataList;

    public static void main(String[] args) {
        employeeDataList = new ArrayList<>();


        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n**********MENU**********");
            System.out.println("\n1. Insert a record" +
                    "\n2. Display the record\n3. Delete a record\n4. Exit");
            System.out.println("\nEnter your choice: ");
            int ch = sc.nextInt();

            switch (ch) {
                case 1:
                    getData();
                    System.out.println("Record updated successfully!");
                    break;

                case 2:
                    System.out.println("********EMPLOYEE DATA********");
                    showData();
                    break;

                case 3:
                    deleteData();
                    break;

                case 4:
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    static void getData(){
        Scanner sc = new Scanner(System.in);
        Scanner scname = new Scanner(System.in);
        System.out.println("Enter your Employee Id: ");
        employeeId = sc.nextInt();
        System.out.println("Enter Employee Name: ");
        name = scname.nextLine();
        System.out.println("Enter Employee Salary: ");
        salary = sc.nextInt();
        EmployeeData employeeData = new EmployeeData(employeeId, name, salary);
        employeeDataList.add(employeeData);
    }

    static void showData(){
        for (int i = 0; i<employeeDataList.size(); i++){
                employeeDataList.get(i).showData();
            }
        }

    static void deleteData(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Employee ID to delete: ");
        int deleteID = sc.nextInt();

        for (int i = 0; i<employeeDataList.size(); i++){
            if (employeeDataList.get(i).employeeId == deleteID ){
                employeeDataList.remove(i);
                return;
            }
        }
        System.out.println("ID not found!!!");
    }
}



/*
Design a class called Employee having three fields namely
Employee -name , Employee-Id and Salary . Using java collection class
Array list perform the following operations.

1. Insert a record
2. Delete a record (based on the Id)
3. Display the record

OUTPUT:

**********MENU**********

1. Insert a record
2. Display the record
3. Delete a record
4. Exit

Enter your choice:
1
Enter your Employee Id:
1
Enter Employee Name:
sas
Enter Employee Salary:
100
Record updated successfully!

**********MENU**********

1. Insert a record
2. Display the record
3. Delete a record
4. Exit

Enter your choice:
2
********EMPLOYEE DATA********
Employee Id: 1
Employee Name: sas
Employee Salary: 100

**********MENU**********

1. Insert a record
2. Display the record
3. Delete a record
4. Exit

Enter your choice:
1
Enter your Employee Id:
2
Enter Employee Name:
ddd
Enter Employee Salary:
222
Record updated successfully!

**********MENU**********

1. Insert a record
2. Display the record
3. Delete a record
4. Exit

Enter your choice:
2
********EMPLOYEE DATA********
Employee Id: 1
Employee Name: sas
Employee Salary: 100
********EMPLOYEE DATA********
Employee Id: 2
Employee Name: ddd
Employee Salary: 222

**********MENU**********

1. Insert a record
2. Display the record
3. Delete a record
4. Exit

Enter your choice:
1
Enter your Employee Id:
3
Enter Employee Name:
fff
Enter Employee Salary:
333
Record updated successfully!

**********MENU**********

1. Insert a record
2. Display the record
3. Delete a record
4. Exit

Enter your choice:
2
********EMPLOYEE DATA********
Employee Id: 1
Employee Name: sas
Employee Salary: 100
********EMPLOYEE DATA********
Employee Id: 2
Employee Name: ddd
Employee Salary: 222
********EMPLOYEE DATA********
Employee Id: 3
Employee Name: fff
Employee Salary: 333

**********MENU**********

1. Insert a record
2. Display the record
3. Delete a record
4. Exit

Enter your choice:
3
Enter Employee ID to delete:
2

**********MENU**********

1. Insert a record
2. Display the record
3. Delete a record
4. Exit

Enter your choice:
2
********EMPLOYEE DATA********
Employee Id: 1
Employee Name: sas
Employee Salary: 100
********EMPLOYEE DATA********
Employee Id: 3
Employee Name: fff
Employee Salary: 333

**********MENU**********

1. Insert a record
2. Display the record
3. Delete a record
4. Exit

Enter your choice:
4

Process finished with exit code 0

* */
