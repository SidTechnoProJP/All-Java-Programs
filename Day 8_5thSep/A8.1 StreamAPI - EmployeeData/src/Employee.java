import java.util.*;

class EmployeeData {
    public int employeeId, salary;
    public String name;

    EmployeeData(int employeeId, String name, int salary) {
        this.employeeId = employeeId;
        this.name = name;
        this.salary = salary;
    }

    void showData() {
        System.out.println("\nEmployee Id: " + employeeId);
        System.out.println("Employee Name: " + name);
        System.out.println("Employee Salary: " + salary);
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
            System.out.println
                             ("1. Insert Record" +
                            "\n2. Display Record" +
                            "\n3. Delete Record" +
                            "\n4. Employee with salary>15000" +
                            "\n5. Find the employee name starts with letter \"V\"" +
                            "\n6. Maximum salary" +
                            "\n7. Total number of employees" +
                            "\n8. Employee name length more than four" +
                            "\n9. Sort the employee details based on name" +
                            "\n10. Exit");
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
                    countSal15K();
                    break;

                case 5:
                    nameStartsWithV();
                    break;

                case 6:
                    maxSalary();
                    break;

                case 7:
                    totalEmployees();
                    break;

                case 8:
                    lengthGreaterThan4();
                    break;

                case 9:
                    nameSort();
                    break;

                case 10:
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    static void getData() {
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

    static void showData() {
        for (EmployeeData employeeData : employeeDataList) {
            employeeData.showData();
        }
    }

    static void deleteData() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Employee ID to delete: ");
        int deleteID = sc.nextInt();

        for (int i = 0; i < employeeDataList.size(); i++) {
            if (employeeDataList.get(i).employeeId == deleteID) {
                employeeDataList.remove(i);
                return;
            }
        }
        System.out.println("ID not found!!!");
    }

    static void countSal15K() {
        long countSal = employeeDataList.stream()
                .filter(sal15K -> sal15K.salary > 15000).count();

        if (countSal > 0) {
            System.out.println("\nEmployee/s with salary more than 15,000 are: ");
            employeeDataList.stream()
                    .filter(sal15K -> sal15K.salary > 15000)
                    .forEach(sal15K -> System.out.println(sal15K.name));
            return;
        }
        System.out.println("\nNO Employees with salary more than 15K found!");
    }

    static void nameStartsWithV() {
        long countV = employeeDataList.stream()
                .filter(nameV -> nameV.name.startsWith("V")).count();

        if (countV > 0) {
            System.out.println("\nEmployee/s with names starting with \"V\" are:");
            employeeDataList.stream()
                    .filter(nameV -> nameV.name.startsWith("V"))
                    .forEach(nameV -> System.out.println(nameV.name));
            return;
        }
        System.out.println("\nNO Employees with names starting with V found!");
    }

    static void maxSalary() {
        OptionalInt salMax = employeeDataList.stream()
                .mapToInt(empSalMax -> empSalMax.salary).max();
        System.out.println("\nEmployee with maximum salary is: " + salMax);
    }

    static void totalEmployees() {
        System.out.println("\nThe total number of employees are: " + employeeDataList.size());
    }

    static void lengthGreaterThan4() {
        long countName = employeeDataList.stream()
                .filter(nameL -> nameL.name.length() > 4)
                .count();
        if (countName > 0) {
            System.out.println("\nEmployee/s with name length above 4 are:");
            employeeDataList.stream()
                    .filter(nameL -> nameL.name.length() > 4)
                    .forEach(nameL -> System.out.println(nameL.name));
            return;
        }
        System.out.println("\nNO Employees with name length greater than 4!");
    }

    static void nameSort() {
        System.out.println("\nList of Employees sorted based on Name\n");
        employeeDataList.stream()
                .sorted(Comparator.comparing(o -> o.name))
                .forEach(EmployeeData::showData);
    }
}


/*
OUTPUT:
**********MENU**********
1. Insert Record
2. Display Record
3. Delete Record
4. Employee with salary>15000
5. Find the employee name starts with letter "V"
6. Maximum salary
7. Total number of employees
8. Employee name length more than four
9. Sort the employee details based on name
10. Exit

Enter your choice:
1
Enter your Employee Id:
101
Enter Employee Name:
Sid
Enter Employee Salary:
15500
Record updated successfully!

**********MENU**********
1. Insert Record
2. Display Record
3. Delete Record
4. Employee with salary>15000
5. Find the employee name starts with letter "V"
6. Maximum salary
7. Total number of employees
8. Employee name length more than four
9. Sort the employee details based on name
10. Exit

Enter your choice:
1
Enter your Employee Id:
203
Enter Employee Name:
Veena
Enter Employee Salary:
2000
Record updated successfully!

**********MENU**********
1. Insert Record
2. Display Record
3. Delete Record
4. Employee with salary>15000
5. Find the employee name starts with letter "V"
6. Maximum salary
7. Total number of employees
8. Employee name length more than four
9. Sort the employee details based on name
10. Exit

Enter your choice:
1
Enter your Employee Id:
23
Enter Employee Name:
Meena
Enter Employee Salary:
25500
Record updated successfully!

**********MENU**********
1. Insert Record
2. Display Record
3. Delete Record
4. Employee with salary>15000
5. Find the employee name starts with letter "V"
6. Maximum salary
7. Total number of employees
8. Employee name length more than four
9. Sort the employee details based on name
10. Exit

Enter your choice:
2
********EMPLOYEE DATA********

Employee Id: 101
Employee Name: Sid
Employee Salary: 15500

Employee Id: 203
Employee Name: Veena
Employee Salary: 2000

Employee Id: 23
Employee Name: Meena
Employee Salary: 25500

**********MENU**********
1. Insert Record
2. Display Record
3. Delete Record
4. Employee with salary>15000
5. Find the employee name starts with letter "V"
6. Maximum salary
7. Total number of employees
8. Employee name length more than four
9. Sort the employee details based on name
10. Exit

Enter your choice:
4

Employee/s with salary more than 15,000 are:
Sid
Meena

**********MENU**********
1. Insert Record
2. Display Record
3. Delete Record
4. Employee with salary>15000
5. Find the employee name starts with letter "V"
6. Maximum salary
7. Total number of employees
8. Employee name length more than four
9. Sort the employee details based on name
10. Exit

Enter your choice:
5

Employee/s with names starting with "V" are:
Veena

**********MENU**********
1. Insert Record
2. Display Record
3. Delete Record
4. Employee with salary>15000
5. Find the employee name starts with letter "V"
6. Maximum salary
7. Total number of employees
8. Employee name length more than four
9. Sort the employee details based on name
10. Exit

Enter your choice:
6

Employee with maximum salary is: OptionalInt[25500]

**********MENU**********
1. Insert Record
2. Display Record
3. Delete Record
4. Employee with salary>15000
5. Find the employee name starts with letter "V"
6. Maximum salary
7. Total number of employees
8. Employee name length more than four
9. Sort the employee details based on name
10. Exit

Enter your choice:
7

The total number of employees are: 3

**********MENU**********
1. Insert Record
2. Display Record
3. Delete Record
4. Employee with salary>15000
5. Find the employee name starts with letter "V"
6. Maximum salary
7. Total number of employees
8. Employee name length more than four
9. Sort the employee details based on name
10. Exit

Enter your choice:
8

Employee/s with name length above 4 are:
Veena
Meena

**********MENU**********
1. Insert Record
2. Display Record
3. Delete Record
4. Employee with salary>15000
5. Find the employee name starts with letter "V"
6. Maximum salary
7. Total number of employees
8. Employee name length more than four
9. Sort the employee details based on name
10. Exit

Enter your choice:
9

List of Employees sorted based on Name


Employee Id: 23
Employee Name: Meena
Employee Salary: 25500

Employee Id: 101
Employee Name: Sid
Employee Salary: 15500

Employee Id: 203
Employee Name: Veena
Employee Salary: 2000

**********MENU**********
1. Insert Record
2. Display Record
3. Delete Record
4. Employee with salary>15000
5. Find the employee name starts with letter "V"
6. Maximum salary
7. Total number of employees
8. Employee name length more than four
9. Sort the employee details based on name
10. Exit

Enter your choice:
10

Process finished with exit code 0
*/