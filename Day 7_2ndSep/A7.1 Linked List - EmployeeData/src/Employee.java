import java.util.LinkedList;
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
    static LinkedList<EmployeeData> employeeDataList;      //Using Linked List

    public static void main(String[] args) {
        employeeDataList = new LinkedList<>();


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
        Scanner scName = new Scanner(System.in);
        System.out.println("Enter your Employee Id: ");
        employeeId = sc.nextInt();
        System.out.println("Enter Employee Name: ");
        name = scName.nextLine();
        System.out.println("Enter Employee Salary: ");
        salary = sc.nextInt();
        EmployeeData employeeData = new EmployeeData(employeeId, name, salary);
        employeeDataList.add(employeeData);
    }

    static void showData(){
        for (EmployeeData employeeData : employeeDataList) {
            employeeData.showData();
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
