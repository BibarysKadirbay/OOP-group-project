import controllers.UserController;
import controllers.interfaces.IUserController;
import controllers.interfaces.IUserController;
import models.User;

import java.sql.SQLOutput;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.SortedMap;

public class Front {
    private final IUserController userController;
    private final Scanner scanner = new Scanner(System.in);

    public Front(IUserController userController) {
        this.userController = userController;
    }

    private void menu() {
        System.out.println("Welcome to Our Project!");
        System.out.println("Available operations:");
        System.out.println("1. Add new student");
        System.out.println("2. Show All students");
        System.out.println("3. Get information about student");
        System.out.println("4. Delete student");
        System.out.println("5. Get GPA of student");
        System.out.println("6. Enter GPA of student");
        System.out.println("7. Exit");
        System.out.print("Enter your choice: ");
    }

    public void run() {
        while (true) {
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
                        getGPAOfStudent();
                        break;
                    case 6:
                        enterGPAOfStudent();
                        break;
                    case 7:
                        return;
                    default:
                        System.out.println("Invalid choice, try again");
                        System.out.println("\n");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private void addNewStudent() {
        System.out.print("Enter student barcode: ");
        int barcode;
        while (true) {
            try {
                barcode = scanner.nextInt();
                if (barcode < 100000 || barcode > 999999) {
                    System.out.println("Invalid barcode. Try again!");
                    System.out.print("Enter student barcode: ");
                } else if (userController.getInformation(barcode).equals("User  was not found\n")) {
                    break;
                } else {
                    System.out.println("Student already exists");
                    System.out.println();
                    return;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid barcode.");
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
                e.printStackTrace();
                return;
            }
        }

        System.out.print("Enter student name: ");
        String name = scanner.next();
        System.out.print("Enter student surname: ");
        String surname = scanner.next();
        System.out.print("Enter student tg_nickname: ");
        String tgNickname = scanner.next();
        System.out.print("Enter student group: ");
        String group = scanner.next();
        System.out.print("Enter student city: ");
        String city = scanner.next();
        System.out.print("Enter student (male/female): ");
        String gender = scanner.next();
        System.out.print("Enter student age: ");
        int age;
        while (true) {
            try {
                age = scanner.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid age.");
                scanner.nextLine();
            }
        }
        double gpa ;
        while (true) {
            try {
                gpa = scanner.nextDouble();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid age.");
                scanner.nextLine();
            }
        }
        boolean genderBool = gender.equalsIgnoreCase("male");
        User user = new User(barcode, name, surname, tgNickname, group, city, genderBool, age , gpa);
        System.out.print(userController.addUser (user));
    }

    private void getInformationByBarcode() {
        System.out.print("Enter student barcode: ");
        int barcode;
        try {
            barcode = scanner.nextInt();
            System.out.println(userController.getInformation(barcode));
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid barcode.");
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void showAllStudents() {
        try {
            System.out.print(userController.showAllUsers());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void deleteStudent() {
        System.out.print("Enter student barcode: ");
        int barcode;
        try {
            barcode = scanner.nextInt();
            System.out.println(userController.deleteUser (barcode));
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid barcode.");
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
    private void getGPAOfStudent() {
        System.out.print("Enter student barcode: ");
        int barcode;
        try{
            barcode = scanner.nextInt();
            System.out.println("GPA: " + userController.getGPA(barcode));
        }catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid barcode.");
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
    private void enterGPAOfStudent() {
        System.out.print("Enter student barcode: ");
        int barcode;
        while (true) {
            try {
                while (!scanner.hasNextInt()) {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.nextLine(); // Consume the non-integer input
                }
                barcode = scanner.nextInt();
                System.out.print("Enter GPA: ");
                double gpa = scanner.nextDouble();
                System.out.println(userController.setGPA(barcode,gpa));
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
                e.printStackTrace();
                return;
            }
        }
    }
}