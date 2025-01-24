import controllers.UserController;
import controllers.interfaces.IUserController;
import models.User;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Front {
    private final IUserController userController;
    private final Scanner scanner = new Scanner(System.in);
    public Front(IUserController userController) {
        this.userController = userController;
    }
    private void menu(){
        System.out.println("Welcome to Our Project!");
        System.out.println("Aviable operations:");
        System.out.println("1. Add new student");
        System.out.println("2. Show All students");
        System.out.println("3. Get information about student");
        System.out.println("4. Delete student");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }
    public void run(){
        while(true){
            menu();
            try {
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        addNewStudent();
                        break;
                    case 2:
                        showAllStudents();
                        break;
                    case 3:
                        getInformationByBarcode();
                        break;
                    case 4:
                        deleteStudent();
                        break;
                    case 5:
                        return;
                    default:
                        System.out.println("Invalid choice try again");
                        System.out.println("\n");
                }
            }catch (InputMismatchException e){
                e.printStackTrace();
                scanner.nextLine();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    private void addNewStudent() {
        System.out.print("Enter student barcode : ");
        int barcode ;
        while (true){
            try{
                barcode = scanner.nextInt();
                if(barcode < 100000 || barcode > 999999){
                    System.out.println("Invalid barcode.Try again!");
                    System.out.print("Enter student barcode : ");
                }
                else if(userController.getInformation(barcode).equals("User was not found\n")){
                    break;
                }else{
                    System.out.println("Student already exists");
                    System.out.println();
                    return ;
                }
            }catch (InputMismatchException e){
                e.printStackTrace();
            }
        }
        System.out.println("Enter student name : ");
        String name = scanner.next();
        System.out.println("Enter student surname : ");
        String surname = scanner.next();
        System.out.println("Enter student tg_nickname : ");
        String tgNickname = scanner.next();
        System.out.println("Enter student group : ");
        String group = scanner.next();
        System.out.println("Enter student city : ");
        String city = scanner.next();
        System.out.println("Enter student (male/female) : ");
        String gender = scanner.next();
        System.out.println("Enter student age : ");
        int age = scanner.nextInt();
        boolean genderBool = false;
        if(gender.equals("male")){ genderBool = true;}
        User user = new User(barcode , name , surname , tgNickname , group , city , genderBool , age);
        System.out.print(userController.addUser(user));
    }
    private void getInformationByBarcode() {
        System.out.print("Enter student barcode : ");
        int barcode = scanner.nextInt();
        System.out.println(userController.getInformation(barcode));
    }
    private void showAllStudents() {
        System.out.print(userController.showAllUsers());
    }
    private void deleteStudent() {
        System.out.print("Enter student barcode : ");
        int barcode = scanner.nextInt();
        System.out.println(userController.deleteUser(barcode));
    }
}
