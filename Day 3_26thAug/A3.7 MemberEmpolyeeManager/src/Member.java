class Member {
    String name;
    int age;
    String pno;
    String address;
    int salary;

    public void printSalary(){
        System.out.println("Salary of Member: " +salary);
    }

    public void showData(){
        System.out.println("Member Data:");
        System.out.println("Name - "+name);
        System.out.println("Age - "+age);
        System.out.println("Ph No - " +pno);
        System.out.println("Address - "+address);
    }
}

class Employee extends Member{
    String specialization;
    public void showSp(){
        System.out.println("Specialization - "+specialization);
        System.out.println();
    }
}

class Manager extends Member{
    String Department;
    public void showDept(){
        System.out.println("Department - "+Department);
        System.out.println();
    }
}

class Data{
    public static void main(String[] args) {
        Employee e1 = new Employee();
        e1.name = "Sid";
        e1.age = 25;
        e1.pno = "9151245957";
        e1.address = "Pune, MH";
        e1.salary = 1000000;
        e1.specialization = "SDE 1";
        e1.printSalary();
        e1.showData();
        e1.showSp();

        Employee e2 = new Employee();
        e2.name = "John";
        e2.age = 27;
        e2.pno = "1012456983";
        e2.address = "Atlanta, GA";
        e2.salary = 1500000;
        e2.specialization = "BA";
        e2.printSalary();
        e2.showData();
        e2.showSp();

        Employee e3 = new Employee();
        e3.name = "Ima";
        e3.age = 30;
        e3.pno = "8125675392";
        e3.address = "Shinjuku, TY";
        e3.salary = 2500000;
        e3.specialization = "SDE 3";
        e3.printSalary();
        e3.showData();
        e3.showSp();

        Manager m1 = new Manager();
        m1.name = "Jack";
        m1.age = 40;
        m1.pno = "1025356569";
        m1.address = "Tampa, FL";
        m1.salary = 4000000;
        m1.Department = "BA Delivery";
        m1.printSalary();
        m1.showData();
        m1.showDept();

        Manager m2 = new Manager();
        m2.name = "Rei";
        m2.age = 42;
        m2.pno = "8147574599";
        m2.address = "Roppongi, TY";
        m2.salary = 5000000;
        m2.Department = "SW Delivery";
        m2.printSalary();
        m2.showData();
        m2.showDept();

    }
}