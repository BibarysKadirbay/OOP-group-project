import controllers.GradesController;
import controllers.interfaces.*;
import dostup.RoleManager;
import models.*;
import repository.GradesRepository;
import repository.interfaces.iGradesRepository;
import repository.interfaces.IUserRepository;
import repository.AttendanceRepository;
import controllers.interfaces.IAttendanceController;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Front {
    private final IUserController userController;
    private final ICourseController courseController;
    private final IScheduleController scheduleController;
    private final iGradesController gradeController;
    private final Scanner scanner = new Scanner(System.in);
    private final IAttendanceController attendanceController;
    public Front(IUserController userController, ICourseController courseController, IScheduleController scheduleController, iGradesController gradeController , IAttendanceController attendanceController) {
        this.userController = userController;
        this.courseController = courseController;
        this.scheduleController = scheduleController;
        this.gradeController = gradeController;
        this.attendanceController = attendanceController;
    }


    private void menu() {
        System.out.println("\n=== School Management System ===");
        System.out.println("1. Manage Students");
        System.out.println("2. Manage Courses");
        System.out.println("3. Manage Schedules");
        System.out.println("4. Manage Grades");
        System.out.println("5. Manage Attendance");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
    }

    public void run() {
        while (true) {
            menu();
            try {
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1 -> manageStudents();
                    case 2 -> manageCourses();
                    case 3 -> manageSchedules();
                    case 4 -> manageGrades();
                    case 5 -> manageAttendance();
                    case 6 -> {
                        System.out.println("Exiting the system...");
                        return;
                    }
                    default -> System.out.println("Invalid choice, try again.");
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
    private void manageStudents() {
        while (true) {
            System.out.println("\n=== Student Management ===");
            System.out.println("1. Add Student");
            System.out.println("2. Show All Students");
            System.out.println("3. Get Student Information");
            System.out.println("4. Delete Student");
            System.out.println("5. Get Student GPA");
            System.out.println("6. Set Student GPA");
            System.out.println("7. Back to Main Menu");
            System.out.print("Enter your choice: ");

            try {
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1 -> addNewStudent();
                    case 2 -> System.out.println(userController.showAllUsers());
                    case 3 -> getInformationByBarcode();
                    case 4 -> deleteStudent();
                    case 5 -> getGPAOfStudent();
                    case 6 -> enterGPAOfStudent();
                    case 7 -> { return; }
                    default -> System.out.println("Invalid choice, try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
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
    private void manageCourses() {
        while (true) {
            System.out.println("\n=== Course Management ===");
            System.out.println("1. Add Course");
            System.out.println("2. Show All Courses");
            System.out.println("3. Get Course by ID");
            System.out.println("4. Update Course");
            System.out.println("5. Delete Course");
            System.out.println("6. Back to Main Menu");
            System.out.print("Enter your choice: ");

            try {
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1 -> addCourse();
                    case 2 -> System.out.println(courseController.showAllCourses());
                    case 3 -> getCourseById();
                    case 4 -> updateCourse();
                    case 5 -> deleteCourse();
                    case 6 -> { return; }
                    default -> System.out.println("Invalid choice, try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        }
    }

    private void addCourse() {
        System.out.println("Enter Course Name: ");
        scanner.nextLine();
        String name = scanner.nextLine();
        System.out.println("Enter Instructor: ");
        String instructor = scanner.nextLine();

        Course course = new Course(name , instructor);
        System.out.println(courseController.addCourse(course));
    }

    private void getCourseById() {
        System.out.print("Enter Course ID: ");
        int id = scanner.nextInt();
        System.out.println(courseController.getCourseById(id));
    }

    private void updateCourse() {
        System.out.print("Enter Course ID to update: ");
        int id = scanner.nextInt();
        System.out.print("Enter New Name: ");
        String name = scanner.next();
        System.out.print("Enter New Instructor: ");
        String instructor = scanner.next();

        Course course = new Course(id, name , instructor);
        System.out.println(courseController.updateCourse(course));
    }

    private void deleteCourse() {
        System.out.print("Enter Course ID to delete: ");
        int id = scanner.nextInt();
        System.out.println(courseController.deleteCourse(id));
    }
    private void manageSchedules() {
        while (true) {
            System.out.println("\n=== Schedule Management ===");
            System.out.println("1. Add Schedule");
            System.out.println("2. Show All Schedules");
            System.out.println("3. Get Schedules by Instructor");
            System.out.println("4. Delete Schedule");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter your choice: ");

            try {
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1 -> addSchedule();
                    case 2 -> System.out.println(scheduleController.showAllSchedules());
                    case 3 -> getSchedulesByInstructor();
                    case 4 -> deleteSchedule();
                    case 5 -> { return; }
                    default -> System.out.println("Invalid choice, try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        }
    }

    private void addSchedule() {
        System.out.print("Enter Course Name: ");
        String courseName = scanner.next();
        System.out.print("Enter Instructor: ");
        String instructor = scanner.next();
        System.out.print("Enter Start Time (HH:MM:SS): ");
        String startTime = scanner.next();
        System.out.print("Enter End Time (HH:MM:SS): ");
        String endTime = scanner.next();
        System.out.print("Enter Day of the Week: ");
        String day = scanner.next();

        Schedule schedule = new Schedule(0, courseName, instructor, startTime, endTime, day);
        System.out.println(scheduleController.addSchedule(schedule));
    }

    private void getSchedulesByInstructor() {
        System.out.print("Enter Instructor: ");
        String instructor = scanner.next();
        System.out.println(scheduleController.getSchedulesByInstructor(instructor));
    }

    private void deleteSchedule() {
        System.out.print("Enter Schedule ID to delete: ");
        int id = scanner.nextInt();
        System.out.println(scheduleController.deleteSchedule(id));
    }
    private void manageGrades() {
        System.out.print("Enter your role (ADMIN, MANAGER, EDITOR, STUDENT): ");
        Role userRole;

        try {
            userRole = Role.valueOf(scanner.next().toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid role! Defaulting to STUDENT.");
            userRole = Role.STUDENT;
        }
        while (true) {
            System.out.println("\n=== Grade Management ===");
            System.out.println("1. Add Grade");
            System.out.println("2. Show All Grades");
            System.out.println("3. Get Grades by Student");
            System.out.println("4. Get Student Grades with Courses");
            System.out.println("5. Calculate GPA");
            System.out.println("6. Back to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1 -> addGrade(userRole);
                case 2 -> System.out.println(gradeController.getAllGrades(userRole));
                case 3 -> getGradesByStudentBarcode(userRole);
                case 4 -> {

                    System.out.print("Enter Student Barcode: ");
                    int barcode = scanner.nextInt();
                    System.out.println(gradeController.getStudentGradesWithCourses(barcode));
                }
                case 5 -> calculateGPA(userRole);
                case 6 -> { return; }
                default -> System.out.println("Invalid choice, try again.");
            }
        }
    }

    private void addGrade(Role userRole) {
        if (!RoleManager.hasAccess(userRole, "ADD_GRADE")) {
            System.out.println("Access Denied! Only Admins and Managers can add grades.");
            return;
        }
        try {
            System.out.print("Enter Student Barcode: ");
            int studentBarcode = scanner.nextInt();
            System.out.print("Enter Course ID: ");
            int courseId = scanner.nextInt();
            System.out.print("Enter Grade Percentage: ");
            int percentage = scanner.nextInt();
            String response = gradeController.createGrade(studentBarcode, courseId, percentage);
            System.out.println(response);
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter valid numbers.");
            scanner.nextLine();
        }
    }

    private void getGradesByStudentBarcode(Role userRole) {
        if (!RoleManager.hasAccess(userRole, "VIEW_GRADES")) {
            System.out.println("Access Denied! Only Admins and Managers can view grades.");
            return;
        }
        try {
            System.out.print("Enter Student Barcode: ");
            int studentBarcode = scanner.nextInt();

            String response = gradeController.getGradesByStudentBarcode(studentBarcode);
            System.out.println(response);
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid barcode.");
            scanner.nextLine();
        }
    }
    private void calculateGPA(Role userRole) {
        if (!RoleManager.hasAccess(userRole, "VIEW_GRADES")) {
            System.out.println("Access Denied! Only Admins and Managers can view grades.");
            return;
        }
        try {
            System.out.print("Enter Student Barcode: ");
            int studentBarcode = scanner.nextInt();

            String response = gradeController.calculateGPA(studentBarcode);
            System.out.println(response);
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid barcode.");
            scanner.nextLine();
        }
    }
    private void manageAttendance() {
        while (true) {
            System.out.println("\n=== Attendance Management ===");
            System.out.println("1. Mark Attendance");
            System.out.println("2. View Attendance by Student");
            System.out.println("3. View Attendance by Course");
            System.out.println("4. Back to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1 -> markAttendance();
                case 2 -> viewAttendanceByStudent();
                case 3 -> viewAttendanceByCourse();
                case 4 -> { return; }
                default -> System.out.println("Invalid choice, try again.");
            }
        }
    }
    private void markAttendance() {
        System.out.print("Enter Student ID: ");
        int studentId = scanner.nextInt();
        System.out.print("Enter Course ID: ");
        int courseId = scanner.nextInt();
        System.out.print("Enter Attendance Date (YYYY-MM-DD): ");
        LocalDate date = LocalDate.parse(scanner.next());
        System.out.print("Enter Status (true for Present, false for Absent): ");
        boolean status = scanner.nextBoolean();
        attendanceController.markAttendance(studentId, courseId, date, status);
    }
    private void viewAttendanceByStudent() {
        System.out.print("Enter Student ID: ");
        int studentId = scanner.nextInt();
        attendanceController.viewAttendanceByStudent(studentId);
    }
    private void viewAttendanceByCourse() {
        System.out.println("Enter Course ID: ");
        int courseId = scanner.nextInt();
        attendanceController.viewAttendanceByCourse(courseId);
    }

}