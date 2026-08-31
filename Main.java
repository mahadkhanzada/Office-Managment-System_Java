import java.util.*;
interface Payable{
    double calculateSalary();
}
class InvalidSalaryExecption extends Exception{
    public InvalidSalaryExecption(String msg){
        super(msg);
    }
}


abstract class Employee implements Payable{
    protected int id;
    protected String name;
    protected double salaryPerDay;
    protected int dayWorked;
    protected int leaveTaken;
    public void setData(int id, String name, double salaryPerDay) throws InvalidSalaryExecption {
        if (salaryPerDay < 0) {
            throw new InvalidSalaryExecption("Salary cannot be negative");
        }
        this.id = id;
    this.name = name;
    this.salaryPerDay = salaryPerDay;
    }
    protected int maxDays = 31;  
    protected int maxleaves = 2;  

    public void setSalaryPerday(int id, String name, double salaryPerDay) throws InvalidSalaryExecption{
        this.id = id;
        this.name = name;
        this.salaryPerDay = salaryPerDay;
        
    }
    public void recordAttendance() throws InvalidSalaryExecption {
    if (dayWorked + 1 > maxDays)
        throw new InvalidSalaryExecption("Maximum working days reached");

    dayWorked++;
}
    public void takeLeave(int days) throws InvalidSalaryExecption{
        if(days < 0 || leaveTaken + days  > maxleaves)
        throw new InvalidSalaryExecption("Leave Limit exceded");
        leaveTaken += days;

        }
    
    void display(){
        System.out.println("ID: " + id+
        ", Name: " + name +
        ", Salary/Day: " + salaryPerDay +
        ", Day Worked: " + dayWorked + 
        ", Leaves " + leaveTaken );
    }
    }
    
    


class FullTimeEmployee extends Employee{
    public double calculateSalary(){
        int payableDays = dayWorked - leaveTaken;
        if (payableDays < 0) payableDays = 0;
         
        double salary = salaryPerDay  * payableDays;

        if(dayWorked == maxDays ){
            salary += 500;
        }
        return salary;
    }

}

class PartTimeEmployee extends Employee{
    public double calculateSalary(){
        int payableDays = dayWorked - leaveTaken;
        if (payableDays < 0) payableDays = 0;
         
        double salary = salaryPerDay * payableDays;

        if(dayWorked > 20 ){
            salary += 0.05 * salary;
        }
        return salary;
    }
    
    
}

//import java.util.Scanner;

public class Main{
    public static void main(String[] main){
         Scanner sc = new Scanner(System.in);
     ArrayList<Employee> list = new ArrayList<>();
     int choice = 0;
        

        do{
            System.out.println("\n--- Office Management System---");
            System.out.println("1. Add Employee");
            System.out.println("2. Mark Attandance");
            System.out.println("3. Apply Leave");
            System.out.println("4. Calculate Salaray");
            System.out.println("5. Show All");
            System.out.println("6. Exit");

            


            try{
                choice = sc.nextInt();
                sc.nextLine();
                switch(choice){
                    
                    case 1:
                        System.out.println("1. Full Time\n2. Part Time");
                        int type = sc.nextInt();

                        Employee emp;

                        if (type == 1)
                            emp = new FullTimeEmployee();
                        else
                            emp = new PartTimeEmployee();

                        System.out.print("Enter ID: ");
                        int id = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Enter Name: ");
                        String name = sc.nextLine();

                        System.out.print("Enter Salary/Day: ");
                        double salary = sc.nextDouble();

                        emp.setData(id, name, salary);

                        list.add(emp);
                        System.out.println("Employee Added!");
                        break;

                    case 2:
                        System.out.print("Enter ID: ");
                        int aid = sc.nextInt();

                        boolean found = false;

                    for (Employee e : list) {
                        if (e.id == aid) {
                            found = true;

                            e.recordAttendance(); // no input needed
                            System.out.println("Attendance marked for today!");
                            break;
        }
    }

    if (!found) {
        System.out.println("Employee not found!");
    }
    break;

                    case 3:
                        System.out.print("Enter ID: ");
                        int lid = sc.nextInt();

                        for (Employee e : list) {
                            if (e.id == lid) {
                                System.out.print("Enter leave days: ");
                                int ldays = sc.nextInt();
                                e.takeLeave(ldays);
                                System.out.println("Leave applied!");
                            }
                        }
                        break;

                    case 4:
                        System.out.print("Enter ID: ");
                        int sid = sc.nextInt();

                        for (Employee e : list) {
                            if (e.id == sid) {
                                System.out.println("Salary: " + e.calculateSalary());
                            }
                        }
                        break;

                    case 5:
                        for (Employee e : list)
                            e.display();
                        break;
                         case 6:
            System.out.println("Exiting program. Goodbye!");
            break; 
        default:
            System.out.println("Invalid choice! Please select a valid option.");
            break;

                }

            } catch (InvalidSalaryExecption e) {
                System.out.println("Error: " + e.getMessage());
            }

        } while (choice != 6);

        sc.close();
    }
}



